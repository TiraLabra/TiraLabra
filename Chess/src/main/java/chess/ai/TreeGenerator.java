package chess.ai;

/**
 * Tallentaa eksplisiittisen pelipuun käyttöliittymää varten.
 */
final class TreeGenerator
{
	/**
	 * Syvyys, johon asti puu tallennetaan.
	 */
	private final int maxDepth;

	/**
	 * Juurisolmu.
	 */
	private Node root;

	/**
	 * Edellinen lisätty solmu.
	 */
	private Node lastNode;

	/**
	 * Tämänhetkinen puun taso.
	 */
	private int ply;

	/**
	 * Konstruktori.
	 *
	 * @param maxDepth maksimisyvyys
	 */
	TreeGenerator(int maxDepth)
	{
		this.maxDepth = maxDepth;
		clear();
	}

	/**
	 * Luo uuden solmun.
	 */
	void startNode(int alpha, int beta, int player, int move)
	{
		if (ply <= maxDepth) {
			lastNode = new Node(ply, alpha, beta, player, move, lastNode);
			if (lastNode.parent == null)
				root = lastNode;
		}
		++ply;
	}

	/**
	 * Tallentaa loput solmun informaatiosta ja palaa edelliselle tasolle.
	 */
	void endNode(int score, int nodeType)
	{
		if (--ply == lastNode.ply) {
			lastNode.setResult(score, nodeType);
			lastNode = lastNode.parent;
		}
	}

	/**
	 * Tyhjentää puun sisällön.
	 */
	void clear()
	{
		ply = 0;
		lastNode = null;
	}

	/**
	 * Palauttaa puun juurisolmun.
	 *
	 * @return
	 */
	Node getTree()
	{
		return root;
	}
}
