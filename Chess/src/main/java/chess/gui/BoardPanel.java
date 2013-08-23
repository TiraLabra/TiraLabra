package chess.gui;

import chess.domain.Pieces;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * Panel-komponentti, joka hoitaa laudan ja nappuloiden renderöimisen.
 */
public class BoardPanel extends JPanel
{
	/**
	 * Sisältää nappuloiden tekstuurit.
	 */
	private BufferedImage image;

	/**
	 * Laudan sisältö. (ks. BitBoard.toArray()).
	 */
	private int[] board = new int[64];

	/**
	 * Bittimaski sallituista siirroista valitussa ruudussa.
	 */
	private long allowedMoves = 0;

	/**
	 * Kursorilla valittu peliruutu tai -1, jos ruutua ei ole valittu.
	 */
	private int selected = -1;

	/**
	 * Luo uuden BoardPanel-objektin.
	 */
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
		drawMoveIndicators(g);
		drawSelection(g);
	}

	/**
	 * Asettaa laudan sisällön.
	 *
	 * @param board laudan sisältö taulukkona, jossa alkioden arvo on nappulan tyyppi ko.
	 * ruudussa, tai -1, jos ruutu on tyhjä
	 */
	public void setBoard(int board[])
	{
		this.board = board.clone();
		repaint();
	}

	/**
	 * Asettaa sallitut siirrot, jotka renderöidään vihreää reunusta käyttäen.
	 *
	 * @param moves siirrot bittimaskina
	 */
	public void setAllowedMoves(long moves)
	{
		allowedMoves = moves;
		repaint();
	}

	/**
	 * Asettaa valitun ruudun, joka renderöidään sinistä reunusta käyttäen.
	 *
	 * @param selected valitun ruudun indeksi
	 */
	public void setSelected(int selected)
	{
		this.selected = selected;
		repaint();
	}

	/**
	 * Piirtää laudan taustakuvan.
	 */
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

	/**
	 * Piirtää kaikki nappulat.
	 */
	private void drawPieces(Graphics g)
	{
		for (int sqr = 0; sqr < 64; ++sqr) {
			if (board[sqr] >= 0) {
				drawPiece(sqr / 8, sqr % 8, board[sqr] / Pieces.COUNT,
						board[sqr] % Pieces.COUNT, g);
			}
		}
	}

	/**
	 * Piirtää yksittäisen nappulan.
	 */
	private void drawPiece(int row, int col, int player, int piece, Graphics g)
	{
		int[] dst = getCoordinates(row, col, 8, 8, getWidth(), getHeight());
		int[] src = getCoordinates(1 - player, piece, 2, 6, image.getWidth(), image.getHeight());
		g.drawImage(image, dst[0], dst[1], dst[2], dst[3], src[0], src[1], src[2], src[3], null);
	}

	/**
	 * Laskee tietyn solun left/top/right/bottom-koordinaatit taulukossa, jossa on annettu määrä
	 * rivejä/sarakkeita, ja annettu leveys/korkeus (pikseleinä).
	 */
	private int[] getCoordinates(int row, int col, int rows, int columns, int width, int height)
	{
		int[] ret = new int[4];
		ret[0] = (int) (((float) col / columns) * width); // left
		ret[1] = (int) (((float) row / rows) * height); // top
		ret[2] = (int) (((float) (col + 1) / columns) * width); // right
		ret[3] = (int) (((float) (row + 1) / rows) * height); // bottom
		return ret;
	}

	/**
	 * Piirtää indikaattorit sallituille siirroille.
	 */
	private void drawMoveIndicators(Graphics g)
	{
		((Graphics2D) g).setStroke(new BasicStroke(4));
		g.setColor(Color.GREEN);
		for (int sqr = 0; sqr < 64; ++sqr) {
			if (((allowedMoves & (1L << sqr))) != 0) {
				int[] c = getCoordinates(sqr / 8, sqr % 8, 8, 8, getWidth(), getHeight());
				g.drawRect(c[0] + 5, c[1] + 5, c[2] - c[0] - 10, c[3] - c[1] - 10);
			}
		}
	}

	/**
	 * Piirtää indikaattorin valitulle ruudulle.
	 */
	private void drawSelection(Graphics g)
	{
		if (selected >= 0) {
			((Graphics2D) g).setStroke(new BasicStroke(4));
			g.setColor(Color.BLUE);
			int[] c = getCoordinates(selected / 8, selected % 8, 8, 8, getWidth(), getHeight());
			g.drawRect(c[0] + 5, c[1] + 5, c[2] - c[0] - 10, c[3] - c[1] - 10);
		}
	}
}
