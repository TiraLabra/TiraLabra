package chess.gui;

import chess.ai.Node;
import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Ikkuna pelipuun näyttämiseen. Käyttää hyväksi puuta, jonka MinMaxAI tallentaa tiettyyn syvyyteen
 * asti (oletus 3).
 */
class GameTreeViewer extends JFrame
{
	/**
	 * Luo uuden ikkunan käyttäen annettua pelipuuta.
	 *
	 * @param rootNode MinMaxAI:n tallentama puu
	 */
	GameTreeViewer(Node rootNode)
	{
		setTitle("Search tree");

		DefaultMutableTreeNode root = getTreeNodes(rootNode);
		JTree tree = new JTree(root);
		JScrollPane treePane = new JScrollPane(tree);
		getContentPane().add(treePane, BorderLayout.CENTER);

		setMinimumSize(new Dimension(200, 200));
		treePane.setPreferredSize(new Dimension(500, 600));
		pack();
		setVisible(true);
	}

	/**
	 * Generoi JTree-komponentin sisällön rekursiivisesti.
	 */
	private DefaultMutableTreeNode getTreeNodes(Node node)
	{
		DefaultMutableTreeNode treeNode = new DefaultMutableTreeNode(node);
		for (int i = 0; i < node.nodes.size(); ++i)
			treeNode.add(getTreeNodes(node.nodes.get(i)));
		return treeNode;
	}
}
