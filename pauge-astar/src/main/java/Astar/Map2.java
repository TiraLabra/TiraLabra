package Astar;

/**
 *
 * @author Arto
 */
public class Map2 {
    Node start;
    Node goal;
    int nr;
    Node[][] field;
    int y;
    int x;
    
    Map2(int y, String s) {
        this.y = y;
        this.x = s.length();
        nr = 0;
        field = new Node[y][x];
    
    }
    
    public void insertRow(String s) {
        for(int i = 0; i<s.length();i++) {
            Node n = new Node(s.charAt(i),nr,i);
            if(n.getValue() == 'S')
                start = n;
            if(n.getValue() == 'G')
                goal = n;
            field[nr][i] = n;
        }
        nr++;
    }
    
    public void printField() {
        for(int i = 0; i<y; i++) {
            for(int j = 0; j<x;j++) {
                System.out.print(field[i][j].getValue());
            }
            System.out.println();
        }   
    }
    
    public Node getStart() {
        return start;
    }
    
    public Node getGoal() {
        return goal;
    }
    
    
    
}
