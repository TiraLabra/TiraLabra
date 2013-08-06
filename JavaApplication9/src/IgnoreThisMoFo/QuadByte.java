/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IgnoreThisMoFo;

/**
 *
 * @author frojala
 */
public class QuadByte extends ByteClass{
    
    private byte first;
    private byte next;
    private byte last;
    private byte fourth;
    
    public QuadByte(byte first, byte next, byte fourth, byte last){
        this.first = first;
        this.next = next;
        this.fourth = fourth;
        this.last = last;
    }
    
    public byte[] getBytes(){
        return new byte[]{
            first,
            next,
            last,
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
        hash = 73 * hash + this.first;
        hash = 73 * hash + this.next;
        hash = 73 * hash + this.last;
        hash = 73 * hash + this.fourth;
        return hash;
    }


}
