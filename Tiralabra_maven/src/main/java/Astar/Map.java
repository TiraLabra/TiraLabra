package Astar;

/**
 *
 * @author Arto
 */
public class Map {
    public Node[][] field;
    int y = 10;
    int x = 20;
    /**
     * Constructor
     */
    public Map() {
        field = new Node[y][x];
        for(int i=0; i<y;i++) {   
            for(int j=0; j<x;j++) {
                if(i==0)
                    field[i][j] = new Node('X', i, j);
                if(j == 0 || j == (x-1))
                    field[i][j] = new Node('X', i, j);
                if(0<i && i<=x-1)
                    field[i][j] = new Node('0', i, j);
                if(j==0 || j==x-1)
                    field[i][j] = new Node('X', i, j);
                if(i==y-1)
                    field[i][j] = new Node('X', i, j);
            }
        }
        field[0][17].setValue('0');         //setting up map specifics
        field[4][2].setValue('0');
        field[4][7].setValue('X');
        field[4][8].setValue('X');
        field[4][9].setValue('X');
        field[4][10].setValue('X');
        field[4][11].setValue('X');
        field[5][11].setValue('X');
        field[6][11].setValue('X');
        field[7][11].setValue('X');
        field[1][16].setValue('X');
        field[2][16].setValue('X');
        
        
    }
   /**
    * Print the field as it currently is
    */
    public void printField() {
        for(int i = 0; i<y; i++) {
            for(int j = 0; j<x;j++) {
                System.out.print(field[i][j].getValue());
            }
            System.out.println();
        }   
    }
    
}
