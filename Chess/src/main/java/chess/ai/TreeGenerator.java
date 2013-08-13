package chess.ai;

/**
 * Tallentaa eksplisiittisen pelipuun käyttöliittymää varten.
 */
class TreeGenerator
{
	/**
	 * Maksimisyvyys.
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
	void startNode(int alpha, int beta, int player)
	{
		if (ply <= maxDepth) {
			lastNode = new Node(ply, alpha, beta, player, lastNode);
			if (lastNode.parent == null)
				root = lastNode;
		}
		++ply;
	}

	/**
	 * Tallentaa loput solmun informaatiosta ja palaa edelliselle tasolle.
	 */
	void endNode(int move, int score, int nodeType)
	{
		if (--ply == lastNode.ply) {
			lastNode.move = move;
			lastNode.score = score;
			lastNode.nodeType = nodeType;
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
