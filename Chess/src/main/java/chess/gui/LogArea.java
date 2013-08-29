package chess.gui;

import chess.util.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Tekstialue peli- ja debug-informaation näyttämistä varten.
 */
public class LogArea extends JScrollPane implements Logger
{
	/**
	 * Tekstikenttä.
	 */
	private JTextArea logTextArea;

	/**
	 * Konstruktori.
	 */
	public LogArea()
	{
		logTextArea = new JTextArea();
		logTextArea.setEditable(false);
		setViewportView(logTextArea);
	}

	@Override
	public void logMessage(String message)
	{
		logTextArea.append(message + "\n");
		logTextArea.setCaretPosition(logTextArea.getDocument().getLength());
	}

	/**
	 * Tyhjentää lokin.
	 */
	public void clear()
	{
		logTextArea.setText("");
	}
}
