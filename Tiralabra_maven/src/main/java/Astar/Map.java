package Astar;

/**
 *
 * @author Arto
 */
public class Map {
    public Node[][] field;
    int y = 8;
    int x = 12;
    
    public Map() {
        field = new Node[8][12];
        for(int i=0; i<y;i++) {   
            for(int j=0; j<x;j++) {
                if(i==0)
                    field[i][j] = new Node('*', i, j);
                
                if(j == 0 || j == (x-1))
                    field[i][j] = new Node('*', i, j);
                
                if(0<i && i<=x-1)
                    field[i][j] = new Node('0', i, j);
                if(j==0 || j==x-1)
                    field[i][j] = new Node('*', i, j);
                if(i==y-1)
                    field[i][j] = new Node('*', i, j);
            }
        }
        field[0][10].setValue('0');
        //field[7][2].setValue('0');
    }
   
    public void printField() {
        for(int i = 0; i<y; i++) {
            for(int j = 0; j<x;j++) {
                System.out.print(field[i][j].getValue());
            }
            System.out.println();
        }   
    }
    
}
