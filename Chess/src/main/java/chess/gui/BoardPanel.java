package chess.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Panel-komponentti, joka hoitaa laudan ja nappuloiden renderöimisen.
 */
public class BoardPanel extends JPanel
{
	private BufferedImage image;

	private int[][][] board = new int[8][8][2];

	public BoardPanel()
	{
		try {
			image = ImageIO.read(ClassLoader.getSystemResource("pieces.png"));
		} catch (IOException ex) {
		}
	}

	@Override
	protected void paintComponent(Graphics g)
	{
		drawBoard(g);
		drawPieces(g);
	}

	public void setBoard(int board[][][])
	{
		for (int i = 0; i < 8; ++i)
			for (int j = 0; j < 8; ++j)
				for (int k = 0; k < 2; ++k)
					this.board[i][j][k] = board[i][j][k];
	}

	private void drawBoard(Graphics g)
	{
		g.setColor(new Color(0xf8d8b2));
		g.fillRect(0, 0, getWidth(), getHeight());

		g.setColor(new Color(0xb08056));
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				if ((row + col) % 2 == 1) {
					int[] c = getCoordinates(row, col, 8, 8, getWidth(), getHeight());
					g.fillRect(c[0], c[1], c[2] - c[0], c[3] - c[1]);
				}
			}
		}
	}

	private void drawPieces(Graphics g)
	{
		for (int row = 0; row < 8; ++row) {
			for (int col = 0; col < 8; ++col) {
				if (board[row][col][0] >= 0)
					drawPiece(row, col, board[row][col][0], board[row][col][1], g);
			}
		}
	}

	private void drawPiece(int row, int col, int player, int piece, Graphics g)
	{
		int[] dst = getCoordinates(row, col, 8, 8, getWidth(), getHeight());
		int[] src = getCoordinates(player, piece, 2, 6, image.getWidth(), image.getHeight());
		g.drawImage(image, dst[0], dst[1], dst[2], dst[3], src[0], src[1], src[2], src[3], null);
	}

	private int[] getCoordinates(int row, int col, int rows, int columns, int width, int height)
	{
		int[] ret = new int[4];
		ret[0] = (int) (((float) col / columns) * width); // left
		ret[1] = (int) (((float) row / rows) * height); // top
		ret[2] = (int) (((float) (col + 1) / columns) * width); // right
		ret[3] = (int) (((float) (row + 1) / rows) * height); // bottom
		return ret;
	}
}
