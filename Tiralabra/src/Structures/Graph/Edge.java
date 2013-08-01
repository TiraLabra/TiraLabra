package Structures.Graph;

public class Edge {
    private Vertex a;
    private Vertex b;
    public Edge(Vertex a, Vertex b){
        this.a=a;
        this.b=b;
    }
    public Vertex getA(){
        return this.a;
    }
    public Vertex getB(){
        return this.b;
    }
    @Override
    public int hashCode(){
        String hash = a.getKey()+"$"+b.getKey();
        return hash.hashCode();
    }
    @Override
    public boolean equals(Object o){
        if(o.getClass()!=this.getClass()){
            return false;
        }
        Edge vp = (Edge)o;
        if(vp.getA()!=this.a || vp.getB()!=this.b){
            return false;
        }
        return true;
    }
}
