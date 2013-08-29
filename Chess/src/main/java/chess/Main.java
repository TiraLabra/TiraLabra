package chess;

import chess.gui.UserInterface;
import javax.swing.SwingUtilities;

/**
 * Projektin pääluokka.
 */
public class Main
{
	public static void main(String[] args)
	{
		SwingUtilities.invokeLater(new UserInterface());
	}
}
