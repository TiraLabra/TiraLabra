package Astar;

/**
 *
 * @author Arto
 */
public class Map {
    public Node[][] field;
    
    public Map() {
        field = new Node[8][8];
        for(int i=0; i<8;i++) {   
            for(int j=0; j<8;j++) {
                if(i==0)
                    field[i][j] = new Node('*', i, j);
                
                if(j == 0 || j == 7)
                    field[i][j] = new Node('*', i, j);
                
                if(0<i && i<=7)
                    field[i][j] = new Node('0', i, j);
                if(j==0 || j==7)
                    field[i][j] = new Node('*', i, j);
                if(i==7)
                    field[i][j] = new Node('*', i, j);
            }
        }
        field[0][6].setValue('0');
        field[7][2].setValue('0');
    }
   
    public void printField() {
        for(int i = 0; i<8; i++) {
            for(int j = 0; j<8;j++) {
                System.out.print(field[i][j].getValue());
            }
            System.out.println();
        }   
    }
    
}
