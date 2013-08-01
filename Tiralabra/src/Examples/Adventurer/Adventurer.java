package Examples.Adventurer;

import Algorithms.Path;
import Structures.Graph.Vertex;
import Structures.Grid.Coordinate;
import Structures.Grid.Grid;
import Structures.LinkedList.LinkedList;
import Utils.Iterator;

/**
 * (Graphics: "Tiny 16: Basic" by Sharm in OpenGameArt.org).
 * Adventurer walks from top-left corner to this castle in right-bottom corner.
 * He hast to find the easiest way there by avoiding rough terrain.
 * Terrain varies from mountain (roughest), water, forest and grass to road (easiest).
 * The read circles on the map indicates his path.
 */
public class Adventurer {
    private Grid grid;
    private LinkedList<Image> images;
    private LinkedList<Coordinate> waypoints;
    private int mapHeight=40;
    private int mapWidth=40;
    private boolean rendering;
    public Adventurer(){
        this.rendering=true;
        this.grid=new Grid(mapHeight,mapWidth);
        this.images=new LinkedList<Image>();
        this.waypoints=new LinkedList<Coordinate>();
        initializeObjects(); 
    }
    public void setRendering(boolean rendering){
        this.rendering=rendering;
    }
    private void initializeObjects(){
        for(int y=0; y<mapHeight; y++){
            for(int x=0; x<mapWidth; x++){
                int random = (int)Math.round(Math.random()*10);
                int weight;
                if(random>=0 && random<3){
                    weight=1;
                    images.add(new Image("src\\tie.png",x*20,y*20));    
                }else if(random>=3 && random<4){
                    weight=15;
                    images.add(new Image("src\\vuori.png",x*20,y*20));
                }else if(random>=5 && random<6){
                    weight=10;
                    images.add(new Image("src\\meri.png",x*20,y*20));    
                }else if(random>=6 && random<8){
                    weight=6;
                    images.add(new Image("src\\metsa.png",x*20,y*20)); 
                }else{
                    weight=3;
                    if(Math.round(Math.random()*3)==1){
                        images.add(new Image("src\\nurmi.png",x*20,y*20));  
                    }else{
                        images.add(new Image("src\\nurmi2.png",x*20,y*20));                            
                    }    
                }
                grid.setWeight(x, y, x+1, y, weight);
                grid.setWeight(x, y, x-1, y, weight);
                grid.setWeight(x, y, x, y+1, weight);
                grid.setWeight(x, y, x, y-1, weight);
            }
        }
        if(rendering==true){
            images.add(new Image("src\\jamppa.png",0,0));
            images.add(new Image("src\\linna.png",(mapWidth-1)*20,(mapHeight-1)*20));
        }
    }
    public void startTheAdventure(){
        shortestPath(0,0,mapWidth-1,mapHeight-1);
    }
    private void shortestPath(int x1, int y1, int x2, int y2) {
        Path p = this.grid.shortestPath(x1, y1, x2, y2);
        System.out.println(p.toString());
        LinkedList<Vertex> path = p.getPath();
        Iterator<Vertex> i = new Iterator<Vertex>(path);
        while(i.hasNext()){
            Vertex v = i.getNext();
            Coordinate c = (Coordinate)v.getData();
            waypoints.add(new Coordinate(c.getX()*20+6,c.getY()*20+6));
        }
        if(this.rendering==true){
            Window window = new Window(800,800,images,waypoints);
            window.initialize();
        }
    }
}
