package chess.ai;

import chess.domain.Move;
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
	 * Siirto, jolla solmuun päädyttiin, tai 0 jos kyseessä juurisolmu tai nollasiirtoa käyttävä
	 * haku.
	 */
	int move;

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
		if (move != 0)
			moveStr = Move.toString(move) + " " + -score;
		else
			moveStr = ply > 0 ? "Null move search" : "";
		return moveStr
				+ " (\u03b1=" + itostr(alpha)
				+ " \u03b2=" + itostr(beta)
				+ " s" + new String[]{"=", ">=", "<="}[nodeType] + score
				+ ")";
	}

	/**
	 * Muuntaa kokonaisluvun merkkijonoksi käyttäen ääretön-symbolia minimi- ja maksimiarvoille.
	 */
	private static String itostr(int x)
	{
		if (x == Scores.MIN)
			return "-\u221e";
		else if (x == Scores.MAX)
			return "\u221e";
		else
			return Integer.toString(x);
	}
}
