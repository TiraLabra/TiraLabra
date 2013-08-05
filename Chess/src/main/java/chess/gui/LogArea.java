package chess.gui;

import chess.util.Logger;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class LogArea extends JScrollPane implements Logger
{
	private JTextArea logTextArea;

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
	}

	public void clear()
	{
		logTextArea.setText("");
	}
}
