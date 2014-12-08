package structures;

import map.Node;

/**ClosedSet is used for keeping track of the nodes in the map which have 
 * already been accessed. So far it only works as a simple 2D integer array,
 * providing O(1) time checks and additions to it.
 * 
 */
public class ClosedSet {
    int[][] map;
    
    public ClosedSet(int x, int y) {
        this.map = new int[y][x];
    }
    
    public void add(Node node) {
        map[node.getY()][node.getX()] = 1;
    }
    
    public boolean contains(Node node) {
        if (map[node.getY()][node.getX()] == 1) {
            return true;
        }
        return false;
    }
}
