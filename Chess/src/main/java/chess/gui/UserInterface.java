package chess.gui;

import chess.ai.AI;
import chess.ai.MinMaxAI;
import chess.ai.PerformanceTest;
import chess.domain.GameState;
import chess.domain.Pieces;
import chess.domain.Players;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingConstants;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymän pääikkuna.
 */
public class UserInterface implements Runnable, MouseListener, ActionListener
{
	/**
	 * Pääikkuna.
	 */
	private JFrame frame;

	/**
	 * Label pelin lopputuloksen ilmoittamiseksi.
	 */
	private JLabel resultLabel;

	/**
	 * Pelilaudan renderöinti.
	 */
	private BoardPanel board;

	/**
	 * Pelitilanne.
	 */
	private GameState state;

	/**
	 * Valittu peliruutu tai -1 jos ei valittu.
	 */
	private int selectedSquare = -1;

	/**
	 * Ihmispelaajan nappuloiden väri.
	 */
	private int player = Players.WHITE;

	/**
	 * Tietokonevastustaja.
	 */
	private AI ai;

	/**
	 * Lokialue.
	 */
	private LogArea logArea;

	/**
	 * Valikkoelementit.
	 */
	private JMenuItem newGameItem, exitItem, perfTestItem, perfTest2Item;

	private JCheckBoxMenuItem debugInfoItem;

	@Override
	public void run()
	{
		createFrame();

		startNewGame();
	}

	/**
	 * Luo pääikunan.
	 */
	private void createFrame()
	{
		frame = new JFrame("Chess");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		createComponents(frame.getContentPane());
		createMenu();

		frame.setMinimumSize(new Dimension(200, 200));
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Luo komponentit.
	 */
	private void createComponents(Container container)
	{
		container.setLayout(new BorderLayout());

		board = new BoardPanel();
		board.setPreferredSize(new Dimension(600, 600));
		board.addMouseListener(this);
		board.setLayout(new BorderLayout());
		container.add(board, BorderLayout.CENTER);

		resultLabel = new JLabel();
		resultLabel.setHorizontalAlignment(SwingConstants.CENTER);
		resultLabel.setVisible(false);
		resultLabel.setFont(new Font(resultLabel.getFont().getFontName(), Font.BOLD, 30));
		board.add(resultLabel);

		logArea = new LogArea();
		logArea.setPreferredSize(new Dimension(180, 0));
		container.add(logArea, BorderLayout.EAST);
	}

	/**
	 * Luo valikon.
	 */
	private void createMenu()
	{
		JMenuBar menuBar = new JMenuBar();

		JMenu gameMenu = new JMenu("Game");
		menuBar.add(gameMenu);

		newGameItem = createMenuItem(gameMenu, "New game");
		exitItem = createMenuItem(gameMenu, "Exit");

		JMenu debugMenu = new JMenu("Debug");
		menuBar.add(debugMenu);

		perfTestItem = createMenuItem(debugMenu, "Performance test");
		perfTest2Item = createMenuItem(debugMenu, "Performance test 2");
		debugInfoItem = createCheckBoxMenuItem(debugMenu, "Show debug info");

		frame.add(menuBar, BorderLayout.NORTH);
	}

	/**
	 * Luo yhden tavallisen valikkoelementin.
	 */
	private JMenuItem createMenuItem(JMenu menu, String caption)
	{
		JMenuItem item = new JMenuItem(caption);
		item.addActionListener(this);
		menu.add(item);
		return item;
	}

	/**
	 * Luo yhden checkbox-valikkoelementin.
	 */
	private JCheckBoxMenuItem createCheckBoxMenuItem(JMenu menu, String caption)
	{
		JCheckBoxMenuItem item = new JCheckBoxMenuItem(caption);
		item.addActionListener(this);
		menu.add(item);
		return item;
	}

	public void mouseClicked(MouseEvent me)
	{
	}

	/**
	 * Mouse-down-tapahtumien käsittely.
	 */
	public void mousePressed(MouseEvent me)
	{
		if (state.isCheckMate())
			return;

		int sqr = getSquareFromCoordinates(me.getX(), me.getY());
		if (selectedSquare >= 0) {
			if ((state.getLegalMoves(selectedSquare) & (1L << sqr)) != 0) {
				state.move(selectedSquare, sqr);
				board.setBoard(state.getBoard());

				if (state.isCheckMate()) {
					setResult(player);
					return;
				} else if (state.isStaleMate()) {
					setResult(-1);
					return;
				}

				ai.move(state);
				board.setBoard(state.getBoard());

				if (state.isCheckMate()) {
					setResult(1 - player);
					return;
				} else if (state.isStaleMate()) {
					setResult(-1);
					return;
				}
			}
		}

		if (state.getBoard()[sqr] / Pieces.COUNT == player) {
			selectedSquare = sqr;
			long moves = state.getLegalMoves(sqr);
			board.setAllowedMoves(moves);
			board.setSelected(selectedSquare);
		} else {
			board.setAllowedMoves(0);
			board.setSelected(-1);
		}
	}

	public void mouseReleased(MouseEvent me)
	{
	}

	public void mouseEntered(MouseEvent me)
	{
	}

	public void mouseExited(MouseEvent me)
	{
	}

	/**
	 * Palauttaa ruudun indeksin annetuissa pikselikoordinaateissa.
	 */
	private int getSquareFromCoordinates(int x, int y)
	{
		int row = 8 * y / board.getHeight();
		int col = 8 * x / board.getWidth();
		return row * 8 + col;
	}

	/**
	 * Näyttää pelin lopputuloksen.
	 *
	 * @param winner voittanutpelaaja tai -1 jos pattitilanne
	 */
	void setResult(int winner)
	{
		resultLabel.setVisible(true);
		if (winner == -1)
			resultLabel.setText("Stale mate!");
		else
			resultLabel.setText("Check mate! " + (winner == 0 ? "White" : "Black") + " wins.");
		board.setAllowedMoves(0);
		board.setSelected(-1);
	}

	/**
	 * Uusi peli.
	 */
	private void startNewGame()
	{
		selectedSquare = -1;
		state = new GameState();
		board.setBoard(state.getBoard());
		board.setSelected(-1);
		board.setAllowedMoves(0);
		resultLabel.setVisible(false);
		ai = new MinMaxAI(logArea);
	}

	/**
	 * Suorituskykytesti.
	 */
	private void runPerformanceTest()
	{
		new Thread(new PerformanceTest(logArea, 2, 5.0)).start();
	}

	/**
	 * Suorituskykytesti.
	 */
	private void runPerformanceTest2()
	{
		new Thread(new PerformanceTest(logArea, 7, 10.0)).start();
	}

	/**
	 * Tapahtumien käsittely.
	 */
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == newGameItem)
			startNewGame();
		else if (ae.getSource() == perfTestItem)
			runPerformanceTest();
		else if (ae.getSource() == perfTest2Item)
			runPerformanceTest2();
		else if (ae.getSource() == debugInfoItem)
			ai.setLoggingEnabled(debugInfoItem.getState());
		else if (ae.getSource() == exitItem)
			System.exit(0);
	}
}
