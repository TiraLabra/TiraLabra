package chess.gui;

import chess.domain.GameState;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Käyttöliittymän pääikkuna.
 */
public class UserInterface implements Runnable
{
	private JFrame frame;

	private BoardPanel board;

	private GameState state;

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

		container.add(board);
	}
}
