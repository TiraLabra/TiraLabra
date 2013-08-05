/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

/**
 *
 * @author frojala
 */
public class DoubleByte extends ByteClass{
    
    private byte first;
//    private byte next;
    private byte last;
//    private byte fourth;
    
    public DoubleByte(byte first, byte last){
        this.first = first;
//        this.next = next;
//        this.fourth = fourth;
        this.last = last;
    }
    
    public byte[] getBytes(){
        return new byte[]{
            first,
//            next,
            last,
//            fourth
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
        hash = 47 * hash + this.first;
        hash = 47 * hash + this.last;
        return hash;
    }




}
