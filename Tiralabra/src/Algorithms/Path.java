package Algorithms;

import Structures.Graph.Vertex;
import Structures.LinkedList.LinkedList;
import Utils.Iterator;

public class Path {
    private LinkedList<Vertex> path;
    private int length;
    public Path(LinkedList<Vertex> path, int length){
        this.path=path;
        this.length=length;
    }
    /*
     * @return The path as a list
     */
    public LinkedList<Vertex> getPath(){
        return this.path;
    }
    /*
     * @return Length of the path
     */
    public int getLength(){
        return this.length;
    }
    @Override
    public String toString(){
        String description="";
        description+="Path length: " + this.length + "\nPath: ";
        Iterator<Vertex> i = new Iterator<Vertex>(this.path);
        int n=0;
        while(i.hasNext()){
            Vertex a = i.getNext();
            if(n==0){
                description+=a.getName();
            }else{
                description+="->"+a.getName();
            }
            n++;
        }
        return description;
    }
    @Override
    public boolean equals(Object o){
        if(o.getClass()!=this.getClass()){
            return false;
        }
        Path p = (Path)o;
        return p.length==this.length;
    }
}
