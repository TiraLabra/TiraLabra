/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Structures.Grid;

import Algorithms.AStar;
import Algorithms.Dijkstra;
import Algorithms.Heuristics;
import Algorithms.Path;
import Algorithms.PathFinder;
import Structures.Graph.Graph;
import Structures.Graph.Vertex;
import Structures.Hashtable.Hashtable;

/**
 * Greates a width*height sized grid, which presents coordinate points that are 1 unit apart from each other and have x between 0 and width-1 and y between 0 and height-1.
 */
public class Grid implements Heuristics{
    private Hashtable<Coordinate,Vertex> grid;
    private Graph graph;
    private int width;
    private int height;
    public Grid(int width, int height){
        this.width=width;
        this.height=height;
        this.grid=new Hashtable<Coordinate,Vertex>();
        this.graph=new Graph();
        initializeGrid(width,height);
        generateGrid(width,height);
    }
    private void initializeGrid(int width, int height){
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                Vertex v = new Vertex(x+","+y);
                v.setData(new Coordinate(x,y));
                this.grid.put(new Coordinate(x,y), v);
            }
        }
    }
    private void generateGrid(int width, int height){
        for(int y=0; y<height; y++){
            for(int x=0; x<width; x++){
                Vertex v = this.grid.get(new Coordinate(x,y));
                if(x>0){
                    this.graph.connect(v,this.grid.get(new Coordinate(x-1,y)),1);
                }
                if(x<(width-1)){
                    this.graph.connect(v,this.grid.get(new Coordinate(x+1,y)),1);
                }
                if(y>0){
                    this.graph.connect(v,this.grid.get(new Coordinate(x,y-1)),1);
                }
                if(y<(height-1)){
                    this.graph.connect(v,this.grid.get(new Coordinate(x,y+1)),1);
                }
            }
        }
    }
    /**
    * Sets the weight between point x1,y1 and x2,y2. Points have to be adjacents.
    */
    public void setWeight(int x1, int y1, int x2, int y2, int weight){
        if(y2>this.height-1 || y2<0 || x2>this.width-1 || x2<0 || Math.abs(x1-x2)>1 || Math.abs(y1-y2)>1){
            return;
        }
        this.graph.setWeight(this.grid.get(new Coordinate(x1,y1)), this.grid.get(new Coordinate(x2,y2)), weight);
    }
    /**
    * Finds the shortest path between point x1,y1 and x2,y2, if points are within grid
    * @param Point x1,y1 and point x2,y2
    * @return The shortest path between x1,y1 and x2,y2 if points are within grid, otherwise null
    */
    public Path shortestPath(int x1, int y1, int x2, int y2){
        if(x1<0 || x2<0 || y1<0 || y2<0 || x1>(width-1) || x2>(width-1)  || y1>(height-1) || y2>(height-1)){
            return null;
        }
        PathFinder pf = new AStar(this.graph,this);
        return pf.shortestPath(this.grid.get(new Coordinate(x1,y1)), this.grid.get(new Coordinate(x2,y2)));
    }
    /**
    * Calculates the manhattan distance between two vertices
    * @param Vertex a and b which both hold their coordinates in the grid
    * @return The manhattan distance
    */
    @Override
    public int getHeuristics(Vertex a, Vertex b) {
        int x1 = ((Coordinate)a.getData()).getX();
        int y1 = ((Coordinate)a.getData()).getY();
        int x2 = ((Coordinate)b.getData()).getX();
        int y2 = ((Coordinate)b.getData()).getY();
        return (int)Math.abs((x1-x2))+(int)Math.abs((y1-y2));
    }
}
