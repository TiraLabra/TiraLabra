
package Structures.Grid;


public class Coordinate {
    private int x;
    private int y;
    public Coordinate(int x, int y){
        this.x=x;
        this.y=y;
    }
    public int getX(){
        return this.x;
    }
    public int getY(){
        return this.y;
    }
    @Override
    public boolean equals(Object o){
        if(o.getClass()!=this.getClass()){
            return false;
        }
        Coordinate c = (Coordinate)o;
        if(c.getX()!=x || c.getY()!=y){
            return false;
        }
        return true;
    }
    @Override
    public int hashCode(){
        return (x+"$"+y).hashCode();
    }
}
