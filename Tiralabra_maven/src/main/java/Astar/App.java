package Astar;

/**
 * Hello world!
 *
 */
public class App 
{
    public Finder f;
    App() { 
    }
    /**
     * Main program
     * 
     * @param args 
     */
    public static void main( String[] args )
    {
        Finder f = new Finder();
        Map kartta1 = new Map();
        f.findOptimal(kartta1, kartta1.field[9][2], kartta1.field[0][17]);
        
        
        /*Node n = new Node('*',0,0);
        Node n1 = new Node('x',0,1);
        Node n2 = new Node('z',0,2);
        Node n3 = new Node('h',0,3);
        
        n.setToStart(0);
        n.setToGoal(0, 4);
        n1.setToStart(1);
        n1.setToGoal(0, 4);
        n2.setToStart(1);
        n2.setToGoal(0, 4);
        n3.setToStart(2);
        n3.setToGoal(0, 4);
        
        Heap h = new Heap();
        h.insertNode(n);
        h.insertNode(n1);
        h.insertNode(n2);
        h.insertNode(n3);
        for(int i = 1;i<=h.getSize();i++) {
            System.out.println(h.get(i).getValue());
        }
        System.out.println("");
        for(int i = h.getSize();i>0;i--) {
            System.out.print(h.getSize()+", ");
            h.removeNode();
            System.out.println(h.getHighest().getValue());
        }
        System.out.println("");
        System.out.print(h.isEmpty());
        System.out.println("\n"+h.getSize());*/
    }
}
