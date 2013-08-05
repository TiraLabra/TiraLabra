package domain;


/**
 *
 * @author jukkapit
 */
public class Pino {    
    private int top;
    private Object[] table;
/**
 * Konstruktori
 * @param pinonKoko 
 */
    public Pino(int pinonKoko) {
        top = -1;
        table = new Object[pinonKoko];
    }
    /**
     * 
     * @param lisattava 
     */
    public void push(Object lisattava){
        top++;
        table[top] = lisattava;        
    }
    /**
     * 
     * @return 
     */
    public Object pop(){
        Object poistettava = table[top];
        top--;
        return poistettava;
    }
    /**
     * 
     * @return 
     */
    public boolean empty(){
        return top==-1;
    }
    /**
     * 
     * @return 
     */
    public Object peek(){
        return table[top];
    }
    
}
