package chess.gui;

import chess.ai.AI;
import chess.ai.RandomAI;
import chess.domain.GameState;
import chess.domain.Pieces;
import chess.domain.Players;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymän pääikkuna.
 */
public class UserInterface implements Runnable, MouseListener
{
	private JFrame frame;

	private BoardPanel board;

	private GameState state;

	private int selectedSquare = -1;

	private int player = Players.WHITE;

	private AI ai = new RandomAI();

	@Override
	public void run()
	{
		createFrame();
		state = new GameState();
		board.setBoard(state.getBoard());
	}

	private void createFrame()
	{
		frame = new JFrame("Chess");

		frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

		createComponents(frame.getContentPane());

		frame.setMinimumSize(new Dimension(200, 200));
		frame.pack();
		frame.setVisible(true);
	}

	private void createComponents(Container container)
	{
		container.setLayout(new BorderLayout());

		board = new BoardPanel();
		container.add(board);
		board.setPreferredSize(new Dimension(600, 600));
		board.addMouseListener(this);

		container.add(board);
	}

	public void mouseClicked(MouseEvent me)
	{
	}

	public void mousePressed(MouseEvent me)
	{
		int sqr = getSquareFromCoordinates(me.getX(), me.getY());
		if (selectedSquare >= 0) {
			if ((state.getAllowedMoves(selectedSquare) & (1L << sqr)) != 0) {
				state.move(selectedSquare, sqr);
				ai.move(state);
				board.setBoard(state.getBoard());
			}
		}

		if (state.getBoard()[sqr] / Pieces.COUNT == player) {
			selectedSquare = sqr;
			long moves = state.getAllowedMoves(sqr);
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

	private int getSquareFromCoordinates(int x, int y)
	{
		int row = 8 * y / board.getHeight();
		int col = 8 * x / board.getWidth();
		return row * 8 + col;
	}
}
