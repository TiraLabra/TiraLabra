package util;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Juri Kuronen
 */
public class TreeNodeTest {

    @Test
    public void treeNodeProperlyLinksNodes() {
        TreeNode tn = new TreeNode(null, 0);
        TreeNode tn2 = new TreeNode(tn, 0);
        TreeNode tn3 = new TreeNode(tn2, 0);
        TreeNode tn4 = new TreeNode(tn, 0);
        assertEquals(null, tn.getParent());
        assertEquals(tn, tn2.getParent());
        assertEquals(tn2, tn3.getParent());
        assertEquals(tn, tn4.getParent());
    }
}
