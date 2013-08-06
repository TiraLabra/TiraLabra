package Structures.Graph;

import Structures.LinkedList.LinkedList;


public class Vertex {
    private int key;
    private String name;
    private LinkedList<Vertex> adjacencyVertices;
    private Object data;
    /*
     * Vertex without a name
     */
    public Vertex(){
        this.key=-1;
        this.name="vertex_"+this.key;
        this.adjacencyVertices=new LinkedList<Vertex>();
    }
    /*
     * Vertex with a name
     */
    public Vertex(String name){
        this.name=name;
        this.key=-1;
        this.adjacencyVertices=new LinkedList<Vertex>();
    }
    /*
     * @param object to be stored in this vertex
     */
    public void setData(Object o){
        this.data=o;
    }
    /*
     * @return data stored in this vertex
     */
    public Object getData(){
        return this.data;
    }
    protected int getKey(){
        return this.key;
    }
    public String getName(){
        return this.name;
    }
    protected void setKey(int key){
        this.key=key;
    }
    /*
     * Connects this vertex to another vertex
     * @param Vertex to be connected with this vertex
     */
    protected void addAdjencyVertex(Vertex a){
        this.adjacencyVertices.add(a);
    }
    /*
     * Get all adjacency vertices of this vertex
     * @return list of adjancecy vertices
     */
    protected LinkedList<Vertex> getAdjencyVertices(){
        return this.adjacencyVertices;
    }
    @Override
    public String toString(){
        return this.name;
    }
    @Override
    public int hashCode(){
        return this.key;
    }
    @Override
    public boolean equals(Object o){
        if(o.getClass()!=this.getClass()){
            return false;
        }
        Vertex v = (Vertex)o;
        if(v.getKey()!=this.key){
            return false;
        }
        return true;
    }
}
