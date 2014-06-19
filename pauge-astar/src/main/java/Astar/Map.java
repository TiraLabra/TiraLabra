package Astar;

/**
 *
 * @author Arto
 */
public class Map {
    private Node start;
    private Node goal;
    private int nr;
    public Node[][] field;
    private int y;
    private int x;
    
    Map(int y, String s) {
        this.y = y;
        this.x = s.length();
        nr = 0;
        field = new Node[y][x];
    
    }
    
    /**
     * Draws the map to the matrix one row at a time
     * 
     * @param s A row found in the map file
     */
    public void insertRow(String s) {
        for(int i = 0; i<s.length();i++) {
            Node n = new Node(s.charAt(i),nr,i);
            if(n.getValue() == 'S')
                start = n;
            if(n.getValue() == 'G')
                goal = n;
            field[nr][i] = n;
            if(Character.isDigit(n.getValue()) && n.getValue()!='0')
                n.setCost(Character.getNumericValue(n.getValue()));
        }
        nr++;
    }
    
    /**
     * Print the map
     */
    public void printField() {
        for(int i = 0; i<y; i++) {
            for(int j = 0; j<x;j++) {
                System.out.print(field[i][j].getValue());
            }
            System.out.println();
        }   
    }
    
    /**
     * 
     * @return Starting node
     */
    public Node getStart() {
        return start;
    }
    
    /**
     * 
     * @return Goal Node
     */
    public Node getGoal() {
        return goal;
    }
    
    
    
}
