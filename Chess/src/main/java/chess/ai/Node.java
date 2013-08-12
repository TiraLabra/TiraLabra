package chess.ai;

import chess.domain.Pieces;
import java.util.ArrayList;
import java.util.List;

/**
 * Tietue informaation tallentamiseen pelitietueen pelipuun solmuista. Tällä ei ole vaikutusta
 * algoritmin toimintaan, vaan tarkoitus on pelkästään informaation tuottaminen.
 */
public class Node
{
	/**
	 * Solmun vanhempi.
	 */
	final Node parent;

	/**
	 * Lapsisolmut.
	 */
	public final List<Node> nodes = new ArrayList<Node>();

	/**
	 * Solmun pistemäärä.
	 */
	public int score;

	/**
	 * Lähtöruutu siirrolle, jolla solmuun tultiin.
	 */
	int fromSqr = -1;

	/**
	 * Kohderuutu siirrolle, jolla solmuun tultiin.
	 */
	int toSqr;

	/**
	 * Nappulatyyppi siirrosse, jolla solmuun tultiin.
	 */
	int pieceType;

	/**
	 * Lyödyn nappulan tyyppi siirrossa, jolla solmuun tultiin.
	 */
	int capturedPiece;

	/**
	 * Puolisiirtojen määrä pelipuun juuresta.
	 */
	final int ply;

	/**
	 * Alfa-beta-karsinnan alfa-arvo solmuun tultaessa.
	 */
	final int alpha;

	/**
	 * Alfa-beta-karsinnan beta-arvo solmuun tultaessa.
	 */
	final int beta;

	/**
	 * Seuraavana siirtävä pelaaja.
	 */
	final int player;

	/**
	 * Solmun tyyppi (PV/Cut/All).
	 */
	int nodeType;

	/**
	 * Luo uuden solmun annetuilla tiedoilla
	 *
	 * @param parent solmun vanhempi tai null jos juurisolmu
	 */
	Node(int ply, int alpha, int beta, int player, Node parent)
	{
		this.ply = ply;
		this.alpha = alpha;
		this.beta = beta;
		this.player = player;
		this.parent = parent;
		if (parent != null)
			parent.nodes.add(this);
	}

	@Override
	public String toString()
	{
		String moveStr;
		if (fromSqr >= 0)
			moveStr = moveToStr(pieceType, fromSqr, toSqr, capturedPiece) + " " + -score;
		else
			moveStr = ply > 0 ? "Null move search" : "";
		return moveStr
				+ " (\u03b1=" + itostr(alpha)
				+ " \u03b2=" + itostr(beta)
				+ " s" + new String[]{"=", ">=", "<="}[nodeType] + score
				+ ")";
	}

	/**
	 * Muuntaa siirron merkkijonoksi.
	 */
	private static String moveToStr(int pieceType, int fromSqr, int toSqr, int capturedPiece)
	{
		String ret = Pieces.symbols[pieceType] + sqrToStr(fromSqr);
		ret += (capturedPiece >= 0 ? "x" + Pieces.symbols[capturedPiece] : "-");
		return ret + sqrToStr(toSqr);
	}

	/**
	 * Muuntaa kokonaisluvun merkkijonoksi käyttäen ääretön-symbolia minimi- ja maksimiarvoille.
	 */
	private static String itostr(int x)
	{
		if (x == Integer.MIN_VALUE + 1)
			return "-\u221e";
		else if (x == Integer.MAX_VALUE)
			return "\u221e";
		else
			return Integer.toString(x);
	}

	/**
	 * Muuntaa ruudun merkkijonoksi (a1-h8).
	 */
	private static String sqrToStr(int sqr)
	{
		return "" + (char) ('a' + sqr % 8) + (char) ('8' - sqr / 8);
	}
}
