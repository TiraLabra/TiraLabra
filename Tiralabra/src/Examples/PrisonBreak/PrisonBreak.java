package Examples.PrisonBreak;

import Algorithms.AStar;
import Algorithms.Heuristics;
import Algorithms.Path;
import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.LinkedList.LinkedList;
import Utils.Iterator;

/**
 * Prisoner needs to escape the prison. 
 * He can sneak in corridor (.) but he shouldn't dig his way through wall (#) unless there's no other choice (first priority is to sneak and the second is to dig).
 * The path is indicated by his action. "s" indicates sneaking and "D" digging.
 */
public class PrisonBreak implements Heuristics{
    private char[][] map;
    private Vertex[][] gMap;
    private Graph g;
    private Vertex escape;
    public PrisonBreak(){
        this.escape = new Vertex("FREE!");
        this.g = new Graph();
        generateMap();
        generateGraph();
    }
    public PrisonBreak(char[][] map){
        this.escape = new Vertex("FREE!");
        this.map=map;
        this.gMap=new Vertex[this.map.length][this.map[0].length];
        initializeMap();
        this.g = new Graph();
        generateGraph();
    }
    private void initializeMap(){
        for(int i=0; i<this.map.length; i++){
            for(int t=0; t<this.map[i].length; t++){
                gMap[i][t]=new Vertex(t+","+i);
            }
        }
    }
    private void generateMap(){
        int width=(int)Math.round(Math.random()*50)+40;
        int height=(int)Math.round(Math.random()*50)+40;
        System.out.println("Size: " + width*height);
        this.gMap=new Vertex[height][width];
        char[][] generatedMap = new char[height][width];
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                if(x==0 || y==0 || x==width-1 || y==height-1){
                    generatedMap[y][x]='#';
                }else{
                    if(Math.round(Math.random()*3)==1){
                        generatedMap[y][x]='#';
                    }else{
                        generatedMap[y][x]='.';     
                    }
                }
                gMap[y][x]=new Vertex(x+","+y);
            }
        }
        this.map=generatedMap;
    }
    private void generateGraph(){ 
        int weight=map.length*map[0].length+1;
        int currWeight;
        for(int y=0; y<gMap.length; y++){
            for(int x=0; x<gMap[y].length; x++){
                if(y==0 || x==0 || y==(gMap.length-1) || x==(gMap[y].length-1)){
                    g.connect(gMap[y][x],escape,0);
                    g.connect(escape,gMap[y][x],0);
                }
                if(y!=0){
                    if(map[y-1][x]=='#'){
                        currWeight=weight;
                    }else{
                        currWeight=1;
                    }
                    g.connect(gMap[y][x],gMap[y-1][x],currWeight);
                }
                if(x!=0){
                    if(map[y][x-1]=='#'){
                        currWeight=weight;
                    }else{
                        currWeight=1;
                    }
                    g.connect(gMap[y][x],gMap[y][x-1],currWeight);
                }
                if(y!=(gMap.length-1)){
                    if(map[y+1][x]=='#'){
                        currWeight=weight;
                    }else{
                        currWeight=1;
                    }
                    g.connect(gMap[y][x],gMap[y+1][x],currWeight);
                }
                if(x!=(gMap[y].length-1)){
                    if(map[y][x+1]=='#'){
                        currWeight=weight;
                    }else{
                        currWeight=1;
                    }
                    g.connect(gMap[y][x],gMap[y][x+1],currWeight);
                }
            }
        }
    }
    public void renderMap(){
        for(int y=0; y<this.map.length; y++){
            for(int x=0; x<this.map[y].length; x++){
                System.out.print(this.map[y][x]);
            }
            System.out.println("");
        }
    }
    public void escape(){
        System.out.println("---");
        long start = System.currentTimeMillis();
        Path pathA = g.shortestPath(gMap[map.length/2][map[0].length/2],escape);
        long end = System.currentTimeMillis();
        System.out.println("Dijkstra:");
        System.out.println("Path length: " + pathA.getLength());
        System.out.println("Execution time: " + (end-start) + "ms");
        start = System.currentTimeMillis();
        Path pathB = g.shortestPath(gMap[map.length/2][map[0].length/2],escape, new AStar(this.g,this));
        end = System.currentTimeMillis();
        System.out.println("A*:");
        System.out.println("Path length: " + pathB.getLength());
        System.out.println("Execution time: " + (end-start) + "ms");
        LinkedList<Vertex> path = g.shortestPath(gMap[map.length/2][map[0].length/2],escape).getPath();
        Iterator<Vertex> i = new Iterator<Vertex>(path);
        while(i.hasNext()){
            Vertex v = i.getNext();
            int x = Integer.parseInt(v.getName().split(",")[0]);
            int y = Integer.parseInt(v.getName().split(",")[1]);
            if(map[y][x]=='#'){
                map[y][x]='D';
            }else{
                map[y][x]='s';
            }
        }
        renderMap();
    }

    @Override
    public int getHeuristics(Vertex a, Vertex b) {
        if(b.getName().equals(("FREE!")) || a.getName().equals("FREE!")){
            return 0;
        }
        int x1 = Integer.parseInt(a.getName().split(",")[0]);
        int y1 = Integer.parseInt(a.getName().split(",")[1]);
        int x2 = Integer.parseInt(b.getName().split(",")[0]);
        int y2 = Integer.parseInt(b.getName().split(",")[1]);
        return (int)Math.abs((x1-x2))+(int)Math.abs((y1-y2));
    }
}
