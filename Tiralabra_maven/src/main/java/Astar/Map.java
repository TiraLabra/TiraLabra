package Astar;

/**
 *
 * @author Arto
 */
public class Map {
    Node[][] field;
    
    public Map() {
        field = new Node[8][8];
        for(int i=0; i<8;i++) {   
            for(int j=0; j<8;j++) {
                if(i==0)
                    field[i][j] = new Node('*');
                
                if(j == 0 || j == 7)
                    field[i][j] = new Node('*');
                
                if(0<i && i<=7)
                    field[i][j] = new Node('0');
                if(j==0 || j==7)
                    field[i][j] = new Node('*');
                if(i==7)
                    field[i][j] = new Node('*');
            }
        }
        field[0][6] = new Node('0');
        field[7][2] = new Node('0');
        printField();
    }
   
    void printField() {
        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8;j++) {
                System.out.print(field[i][j].getValue());
            }
            System.out.println();
        }   
    }
    
}
