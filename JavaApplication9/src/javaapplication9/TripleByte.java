/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

/**
 *
 * @author frojala
 */
public class TripleByte extends ByteClass{
    
    private byte first;
    private byte next;
//    private byte last;
    private byte fourth;
    
    public TripleByte(byte first, byte next, byte fourth){
        this.first = first;
        this.next = next;
        this.fourth = fourth;
//        this.last = last;
    }
    
    public byte[] getBytes(){
        return new byte[]{
            first,
            next,
//            last,
            fourth
        };
    }
    
    @Override
    public boolean equals(Object o){
        if (o.getClass().equals(this.getClass())){
            
            return this.hashCode() == o.hashCode();
            
        } else {
            return false;
        }
    }

   
//    @Override
//    public String toString(){
//        
//    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.first;
        hash = 97 * hash + this.next;
        hash = 97 * hash + this.fourth;
        return hash;
    }


}
