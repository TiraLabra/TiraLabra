package chess.gui;

import chess.ai.MinMaxAI;
import chess.ai.PerformanceTest;
import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import chess.domain.Players;
import chess.game.Game;
import chess.game.Observer;
import chess.game.Player;
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
public class UserInterface implements Runnable, MouseListener, ActionListener, Player, Observer
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
	private Player aiPlayer;

	/**
	 * Runnable-peliobjekti, jota suoritetaan omassa säikeessään.
	 */
	private Game game;

	/**
	 * Säie, jossa peliä suoritetaan.
	 */
	private Thread gameThread;

	/**
	 * Pelaajan valitsema siirto.
	 */
	private int humanPlayerMove;

	/**
	 * Dummy-objekti, jonka avulla pelisäie odottaa, että käyttöliittymä palauttaa pelaajan siirron.
	 * Null, jos pelisäie ei odota pelaajan siirtoa.
	 */
	private Object humanPlayerMoveNotifier;

	/**
	 * Sallitut siirrot valitusta ruudusta.
	 */
	int[] moves;

	/**
	 * Lokialue.
	 */
	private LogArea logArea;

	/**
	 * Valikkoelementit.
	 */
	private JMenuItem newGameItem, exitItem, perfTestItem, perfTest2Item, showGameTreeItem;

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
		logArea.setPreferredSize(new Dimension(200, 0));
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
		showGameTreeItem = createMenuItem(debugMenu, "Show search tree for last AI move");
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

	@Override
	public void mouseClicked(MouseEvent me)
	{
	}

	/**
	 * Mouse-down-tapahtumien käsittely.
	 */
	@Override
	public void mousePressed(MouseEvent me)
	{
		if (humanPlayerMoveNotifier == null)
			return;

		int sqr = getSquareFromCoordinates(me.getX(), me.getY());
		if (selectedSquare >= 0)
			attemptMove(sqr);
		updateSquareSelection(sqr);
	}

	/**
	 * Jos klikattu ruutu on sallittujen siirtojen joukossa, siirtää pelaajan nappulan.
	 *
	 * @param sqr ruutu
	 */
	private void attemptMove(int sqr)
	{
		for (int i = 0; i < moves.length; ++i) {
			if (Move.getToSqr(moves[i]) == sqr) {
				humanPlayerMove = moves[i];
				synchronized (humanPlayerMoveNotifier) {
					humanPlayerMoveNotifier.notify();
				}
			}
		}
	}

	/**
	 * Päivittää käyttöliittymän valitun ruudun mukaisesti. (Sallitut siirrot ko. ruudusta jne.)
	 *
	 * @param newSelectedSqr pelaajan klikkaama ruutu
	 */
	private void updateSquareSelection(int newSelectedSqr)
	{
		if (game.getState().getBoard()[newSelectedSqr] / Pieces.COUNT == player) {
			selectedSquare = newSelectedSqr;
			moves = game.getState().getLegalMoves(newSelectedSqr);
			long movesMask = 0;
			for (int i = 0; i < moves.length; ++i)
				movesMask |= 1L << Move.getToSqr(moves[i]);
			board.setAllowedMoves(movesMask);
			board.setSelected(selectedSquare);
		} else {
			board.setAllowedMoves(0);
			board.setSelected(-1);
		}
	}

	@Override
	public void mouseReleased(MouseEvent me)
	{
	}

	@Override
	public void mouseEntered(MouseEvent me)
	{
	}

	@Override
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
	private void setResult(int winner)
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
		if (gameThread != null)
			gameThread.interrupt();

		aiPlayer = new MinMaxAI(logArea);
		refreshLoggingEnabledState();
		game = new Game(this, aiPlayer, this);

		selectedSquare = -1;
		board.setBoard(game.getState().getBoard());
		board.setSelected(-1);
		board.setAllowedMoves(0);
		resultLabel.setVisible(false);
		showGameTreeItem.setEnabled(false);

		gameThread = new Thread(game);
		gameThread.start();
	}

	/**
	 * Suorituskykytesti.
	 */
	private void runPerformanceTest()
	{
		new Thread(new PerformanceTest(logArea, 2, 5.0)).start();
	}

	/**
	 * Toinen suorituskykytesti (alkaa syvyydestä 8 + pitemmät iteraatiot).
	 */
	private void runPerformanceTest2()
	{
		new Thread(new PerformanceTest(logArea, 8, 15.0)).start();
	}

	private void showGameTree()
	{
		if (((MinMaxAI) aiPlayer).getGameTree() != null)
			new GameTreeViewer(((MinMaxAI) aiPlayer).getGameTree());
	}

	/**
	 * Tapahtumien käsittely.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == newGameItem)
			startNewGame();
		else if (ae.getSource() == perfTestItem)
			runPerformanceTest();
		else if (ae.getSource() == perfTest2Item)
			runPerformanceTest2();
		else if (ae.getSource() == showGameTreeItem)
			showGameTree();
		else if (ae.getSource() == debugInfoItem)
			refreshLoggingEnabledState();
		else if (ae.getSource() == exitItem)
			System.exit(0);
	}

	@Override
	public int getMove(GameState state) throws InterruptedException
	{
		humanPlayerMoveNotifier = new Object();
		synchronized (humanPlayerMoveNotifier) {
			humanPlayerMoveNotifier.wait();
			humanPlayerMoveNotifier = null;
		}
		return humanPlayerMove;
	}

	@Override
	public void notifyMove(GameState state, int ply, Player player, int move)
	{
		board.setBoard(state.getBoard());
		if (player instanceof MinMaxAI)
			showGameTreeItem.setEnabled(true);
		int turn = (ply / 2 + 1);
		logArea.logMessage("" + turn + (ply % 2 == 0 ? ". " : "... ") + Move.toString(move));
	}

	@Override
	public void notifyEnd(GameState state, int result)
	{
		setResult(result);
	}

	/**
	 * Asettaa MinMaxAI:n lokiviestit valinnan mukaisesti päälle tai pois.
	 */
	private void refreshLoggingEnabledState()
	{
		if (aiPlayer instanceof MinMaxAI)
			((MinMaxAI) aiPlayer).setLoggingEnabled(debugInfoItem.getState());
	}
}
