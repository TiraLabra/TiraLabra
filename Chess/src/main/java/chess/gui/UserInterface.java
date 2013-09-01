package chess.gui;

import chess.ai.BalancedGameGenerator;
import chess.ai.MinMaxAI;
import chess.ai.Node;
import chess.domain.GameState;
import chess.domain.Move;
import chess.domain.Pieces;
import chess.domain.Players;
import chess.game.Game;
import chess.game.Observer;
import chess.game.Player;
import chess.testing.PerformanceTest;
import chess.testing.Simulation;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
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
	 * Runnable-peliobjekti, jota suoritetaan omassa säikeessään.
	 */
	private Game game;

	/**
	 * Säie, jossa peliä suoritetaan.
	 */
	private Thread gameThread;

	/**
	 * Säie, jossa nopeustestiä/simulaatiota suoritetaan.
	 */
	private Thread testThread;

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
	 * Dummy-objekti jolla pelisäie odottaa "Execute AI move" valikkokomentoa.
	 */
	private final Object nextMoveNotifier = new Object();

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
	private JMenuItem newGameItem, simulStartItem, stopTestItem, exitItem, perfTestItem,
			perfTest2Item, perfTest3Item, showGameTreeItem, nextMoveItem, demo1Item, demo2Item;

	private JCheckBoxMenuItem debugInfoItem, randomItem, pauseItem;

	/**
	 * Valikot pelaaja-asetuksien konfigurointiin.
	 */
	private final PlayerConfigMenu[] playerConfigs = new PlayerConfigMenu[2];

	/**
	 * Edellisen tekoälysiirron hakupuu.
	 */
	private Node previousSearchTree;

	@Override
	public void run()
	{
		createFrame();
		startNewGame(null);
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
		newGameItem.setAccelerator(KeyStroke.getKeyStroke("F2"));
		randomItem = createCheckBoxMenuItem(gameMenu, "Random start position");
		pauseItem = createCheckBoxMenuItem(gameMenu, "Pause before AI move");
		pauseItem.setAccelerator(KeyStroke.getKeyStroke("F3"));
		nextMoveItem = createMenuItem(gameMenu, "Execute AI move");
		nextMoveItem.setAccelerator(KeyStroke.getKeyStroke("F4"));
		exitItem = createMenuItem(gameMenu, "Exit");

		menuBar.add(playerConfigs[0] = new PlayerConfigMenu("Player 1", this, logArea, false));
		menuBar.add(playerConfigs[1] = new PlayerConfigMenu("Player 2", this, logArea, true));

		JMenu miscMenu = new JMenu("Misc");
		menuBar.add(miscMenu);

		JMenu testMenu = new JMenu("Run test");
		miscMenu.add(testMenu);
		perfTestItem = createMenuItem(testMenu, "Performance test (5s)");
		perfTest2Item = createMenuItem(testMenu, "Performance test (60s)");
		perfTest3Item = createMenuItem(testMenu, "Performance test (60s, no QS)");
		simulStartItem = createMenuItem(testMenu, "Simulation with selected players");
		stopTestItem = createMenuItem(testMenu, "Stop test");
		stopTestItem.setEnabled(false);

		showGameTreeItem = createMenuItem(miscMenu, "View search tree for last AI move...");
		debugInfoItem = createCheckBoxMenuItem(miscMenu, "Show debug info");

		JMenu demoMenu = new JMenu("Demo");
		//miscMenu.add(demoMenu);

		demo1Item = createMenuItem(demoMenu, "Demo 1");
		demo2Item = createMenuItem(demoMenu, "Demo 2");

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
				if (Move.getNewType(moves[i]) != Move.getPieceType(moves[i])
						&& Move.getNewType(moves[i]) != Pieces.QUEEN)
					continue;
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
		if (newSelectedSqr >= 0) {
			selectedSquare = newSelectedSqr;
			moves = game.getState().getLegalMoves(newSelectedSqr);
			long movesMask = 0;
			for (int i = 0; i < moves.length; ++i)
				movesMask |= 1L << Move.getToSqr(moves[i]);
			board.setAllowedMoves(movesMask);
			board.setSelected(selectedSquare);
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
	 * Uusi peli.
	 */
	private void startNewGame(GameState state)
	{
		if (gameThread != null)
			gameThread.interrupt();

		Player whitePlayer = playerConfigs[0].getPlayer();
		Player blackPlayer = playerConfigs[1].getPlayer();
		game = new Game(createGame(state), whitePlayer, blackPlayer, this);

		refreshLoggingEnabledState();
		logArea.logMessage("--- Game started ---");
		selectedSquare = -1;
		board.setBoard(game.getState().getBoard());
		board.setSelected(-1);
		board.setAllowedMoves(0);
		resultLabel.setVisible(false);
		showGameTreeItem.setEnabled(false);
		updateEnabledStatus();

		gameThread = new Thread(game);
		gameThread.start();
	}

	/**
	 * Luo uuden pelitilanteen. (Joko standardialoitustilanne tai satunnainen tilanne asetuksen
	 * mukaan).
	 */
	private GameState createGame(GameState state)
	{
		if (state != null)
			return state;
		else if (randomItem.getState()) {
			try {
				return BalancedGameGenerator.createGame(new Random().nextLong(), 1.0);
			} catch (InterruptedException ex) {
				return null; // Ei tapahdu koskaan. (ohjelman pääsäie)
			}
		} else
			return new GameState();
	}

	/**
	 * Suorituskykytesti.
	 */
	private void runPerformanceTest(int startDepth, double length, boolean qs)
	{
		stopTest();
		startTest(new PerformanceTest(logArea, startDepth, length, qs));
	}

	/**
	 * Käynnistää simulaation kahden tekoälypelaajan välillä.
	 */
	private void runSimulation()
	{
		stopTest();
		Player player1 = playerConfigs[0].getPlayer();
		Player player2 = playerConfigs[1].getPlayer();
		if (player1 instanceof UserInterface || player2 instanceof UserInterface)
			JOptionPane.showMessageDialog(frame, "Both players must be AI players.");
		else
			startTest(new Simulation(logArea, 0, player1, player2));
	}

	/**
	 * Suorittaa annetun testin/simulaation uudessa säikeessä.
	 */
	private void startTest(Runnable testRunnable)
	{
		testThread = new Thread(testRunnable);
		testThread.start();
		stopTestItem.setEnabled(true);
	}

	/**
	 * Keskeyttää tämänhetkisen testin/simulaation.
	 */
	private void stopTest()
	{
		if (testThread != null) {
			if (testThread.isAlive())
				logArea.logMessage("Stopped.");
			testThread.interrupt();
		}
		testThread = null;
		stopTestItem.setEnabled(false);
	}

	/**
	 * Suorittaa seuraavan tekoälysiirron.
	 */
	private void nextMove()
	{
		synchronized (nextMoveNotifier) {
			nextMoveNotifier.notify();
		}
	}

	/**
	 * "Pause before AI move" disabloitu tai enabloitu.
	 */
	private void changePausedStatus()
	{
		updateEnabledStatus();
		if (!pauseItem.isSelected())
			nextMove();
	}

	/**
	 * Tapahtumien käsittely.
	 */
	@Override
	public void actionPerformed(ActionEvent ae)
	{
		if (ae.getSource() == newGameItem)
			startNewGame(null);
		else if (ae.getSource() == demo1Item)
			startNewGame(new GameState("Ke5", "Ka8 Bb8 Bc8", Players.WHITE));
		else if (ae.getSource() == demo2Item)
			startNewGame(new GameState("Kg3 e4", "Kc6 e5", Players.WHITE));
		else if (ae.getSource() == perfTestItem)
			runPerformanceTest(2, 5.0, true);
		else if (ae.getSource() == perfTest2Item)
			runPerformanceTest(2, 60.0, true);
		else if (ae.getSource() == perfTest3Item)
			runPerformanceTest(2, 60.0, false);
		else if (ae.getSource() == simulStartItem)
			runSimulation();
		else if (ae.getSource() == stopTestItem)
			stopTest();
		else if (ae.getSource() == pauseItem)
			changePausedStatus();
		else if (ae.getSource() == nextMoveItem)
			nextMove();
		else if (ae.getSource() == showGameTreeItem)
			new SearchTreeViewer(previousSearchTree);
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
			throws InterruptedException
	{
		if (pauseItem.isSelected() && player instanceof MinMaxAI)
			waitForNextMoveCommand();

		board.setBoard(state.getBoard());
		updateSquareSelection(selectedSquare);
		if (player instanceof MinMaxAI) {
			previousSearchTree = ((MinMaxAI) player).getSearchTree();
			showGameTreeItem.setEnabled(true);
		}
		int turn = (ply / 2 + 1);
		logArea.logMessage("" + turn + (ply % 2 == 0 ? ". " : ". ... ") + Move.toString(move));
	}

	/**
	 * Odottaa, että käyttäjä antaa valikkokomennon siirron suorittamiseksi.
	 */
	private void waitForNextMoveCommand() throws InterruptedException
	{
		synchronized (nextMoveNotifier) {
			nextMoveNotifier.wait();
		}
	}

	@Override
	public void notifyEnd(GameState state, int result)
	{
		resultLabel.setVisible(true);
		String msg;
		if (result == -1)
			msg = "Stale mate!";
		else
			msg = "Check mate! " + (result == 0 ? "White" : "Black") + " wins.";
		resultLabel.setText(msg);
		logArea.logMessage(msg);
		board.setAllowedMoves(0);
		board.setSelected(-1);
	}

	/**
	 * Asettaa MinMaxAI:n lokiviestit valinnan mukaisesti päälle tai pois.
	 */
	private void refreshLoggingEnabledState()
	{
		for (int player = 0; player < Players.COUNT; ++player) {
			if (game.getPlayer(player) instanceof MinMaxAI)
				((MinMaxAI) game.getPlayer(player)).setLoggingEnabled(debugInfoItem.getState());
		}
	}

	/**
	 * Disabloi "Execute AI move" -valikkokomennon jos "Pause before AI move" on disabloitu.
	 */
	private void updateEnabledStatus()
	{
		nextMoveItem.setEnabled(pauseItem.isSelected());
	}
}
