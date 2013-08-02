package Examples.Pipes;

import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.LinkedList.LinkedList;
import Structures.Stack.Stack;
import Utils.Iterator;

/**
 * House is full of water pipes (P). Breaking a pipe will cause the water (@) to spred all over the room (#).
 */
public class Pipes {
    private char[][] house;
    private Vertex[][] gMap;
    private Graph graph;
    private Stack<String> pipes;
    public Pipes(char[][] house){
        this.house=house;
        this.graph=new Graph();
        this.gMap=new Vertex[house.length][house[0].length];
        initializeMap();
    }
    public Pipes(){
        this.pipes=new Stack<String>();
        generateHouse();
        this.graph=new Graph();
        initializeMap();
    }
    private void generateHouse(){
        int width=(int)Math.round(Math.random()*30)+20;
        int height=(int)Math.round(Math.random()*30)+20;
        this.gMap=new Vertex[height][width];
        char[][] generatedHouse = new char[height][width];
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                if(x==0 || y==0 || x==width-1 || y==height-1){
                    generatedHouse[y][x]='#';
                }else{
                    if(Math.round(Math.random()*3)==1){
                        generatedHouse[y][x]='#';
                    }else{
                        generatedHouse[y][x]='.';     
                    }
                    if(Math.round(Math.random()*6)==1){
                        generatedHouse[y][x]='P';
                        this.pipes.push(x+","+y);
                    }
                }
                gMap[y][x]=new Vertex(x+","+y);
            }
        }
        this.house=generatedHouse;
    }
    private void initializeMap() {
        for(int y=0; y<this.gMap.length; y++){
            for(int x=0; x<this.gMap[0].length; x++){
                if(this.house[y][x]!='#'){
                    this.gMap[y][x]=new Vertex(x+","+y);
                }
            }
        }
        for(int y=0; y<this.gMap.length; y++){
            for(int x=0; x<this.gMap[y].length; x++){
                if(this.house[y][x]=='#'){
                    continue;
                }
                if(x>0 && this.house[y][x-1]!='#'){
                    this.graph.connect(this.gMap[y][x], this.gMap[y][x-1]);
                }
                if(x<this.gMap[y].length-1 && this.house[y][x+1]!='#'){
                    this.graph.connect(this.gMap[y][x], this.gMap[y][x+1]);
                }
                if(y>0 && this.house[y-1][x]!='#'){
                    this.graph.connect(this.gMap[y][x], this.gMap[y-1][x]);
                }
                if(y<this.gMap.length-1 && this.house[y+1][x]!='#'){
                    this.graph.connect(this.gMap[y][x], this.gMap[y+1][x]);
                }
            }
        }
    }
    public void breakPipe(int x, int y){
        LinkedList<Vertex> flood = new LinkedList<Vertex>();
        if(house[y][x]=='P'){
            flood=this.graph.floodFill(this.gMap[y][x]);
            System.out.println("PIPE IN "+ x+","+y + " IS BROKEN!");
            System.out.println("---");
            Iterator<Vertex> i = new Iterator<Vertex>(flood);
            while(i.hasNext()){
                Vertex v = i.getNext();
                int x0 = Integer.parseInt(v.getName().split(",")[0]);
                int y0 = Integer.parseInt(v.getName().split(",")[1]);
                this.house[y0][x0]='@';
            }
            render();
        }
    }
    public void breakPipe(){
        if(this.pipes.isEmpty()){
            return;
        }
        String coord = this.pipes.pop();
        int x0 = Integer.parseInt(coord.split(",")[0]);
        int y0 = Integer.parseInt(coord.split(",")[1]);
        breakPipe(x0,y0);
    }
    private void render() {
        for(int y=0; y<this.house.length; y++){
            for(int x=0; x<this.house[y].length; x++){
                System.out.print(this.house[y][x]);
            }
            System.out.println("");
        }
    }
}
