package chess.ai;

import chess.domain.Move;
import chess.util.CustomArrayList;
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
	public final List<Node> nodes = new CustomArrayList<Node>();

	/**
	 * Solmun pistemäärä.
	 */
	int score;

	/**
	 * Siirto, jolla solmuun päädyttiin, tai 0 jos kyseessä juurisolmu tai nollasiirtoa käyttävä
	 * haku.
	 */
	final int move;

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
	Node(int ply, int alpha, int beta, int player, int move, Node parent)
	{
		this.ply = ply;
		this.alpha = alpha;
		this.beta = beta;
		this.player = player;
		this.move = move;
		this.parent = parent;
		if (parent != null)
			parent.nodes.add(this);
	}

	void setResult(int score, int nodeType)
	{
		this.score = score;
		this.nodeType = nodeType;
	}

	@Override
	public String toString()
	{
		String str;
		if (move != 0)
			str = Move.toString(move);
		else
			str = ply > 0 ? "Null move search" : "";
		if (ply > 0)
			str += " " + getIneqSign(-score, -beta, -alpha) + itostr(-score);
		return str
				+ " (\u03b1=" + itostr(alpha)
				+ " \u03b2=" + itostr(beta)
				+ " s" + getIneqSign(score, alpha, beta) + itostr(score)
				+ ")";
	}

	/**
	 * Muuntaa kokonaisluvun merkkijonoksi käyttäen ääretön-symbolia minimi- ja maksimiarvoille.
	 */
	private static String itostr(int x)
	{
		if (x == Scores.MIN)
			return "-\u221e";
		else if (x == Scores.MIN + 1)
			return "-\u221e+1";
		else if (x == Scores.MAX)
			return "\u221e";
		else if (x == Scores.MAX - 1)
			return "\u221e-1";
		else
			return Integer.toString(x);
	}

	/**
	 * Palauttaa epäyhtälösuuruusmerkin solmun tyypin mukaan.
	 */
	private static String getIneqSign(int score, int alpha, int beta)
	{
		if (score <= alpha)
			return "\u2264";
		else if (score >= beta)
			return "\u2265";
		else
			return "=";
	}
}
