package Examples.Adventurer;

import Algorithms.Path;
import Structures.Graph.Vertex;
import Structures.Grid.Coordinate;
import Structures.Grid.Grid;
import Structures.LinkedList.LinkedList;
import Utils.Iterator;
import java.util.Scanner;

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
    private int mapHeight;
    private int mapWidth;
    public Adventurer(){
        getUserInput(); 
    }
    private void getUserInput(){
        this.images=new LinkedList<Image>();
        this.waypoints=new LinkedList<Coordinate>();
        Scanner scanner = new Scanner(System.in);
        int[] widthAndHeight=getSize(scanner);
        this.mapHeight=widthAndHeight[0];
        this.mapWidth=widthAndHeight[1];
        int[] startAndEnd=getStartAndEnd(scanner);
        this.grid=new Grid(mapWidth,mapHeight);
        initializeObjects();
        images.add(new Image("images/jamppa.png",startAndEnd[0]*20,startAndEnd[1]*20));
        images.add(new Image("images/linna.png",startAndEnd[2]*20,startAndEnd[3]*20));
        startTheAdventure(startAndEnd[0],startAndEnd[1],startAndEnd[2],startAndEnd[3]);
    }
    private void initializeObjects(){
        for(int y=0; y<mapHeight; y++){
            for(int x=0; x<mapWidth; x++){
                int random = (int)Math.round(Math.random()*10);
                int weight;
                if(random>=0 && random<3){
                    weight=1;
                    images.add(new Image("images/tie.png",x*20,y*20));    
                }else if(random>=3 && random<4){
                    weight=15;
                    images.add(new Image("images/vuori.png",x*20,y*20));
                }else if(random>=5 && random<6){
                    weight=10;
                    images.add(new Image("images/meri.png",x*20,y*20));    
                }else if(random>=6 && random<8){
                    weight=6;
                    images.add(new Image("images/metsa.png",x*20,y*20)); 
                }else{
                    weight=3;
                    if(Math.round(Math.random()*3)==1){
                        images.add(new Image("images/nurmi.png",x*20,y*20));  
                    }else{
                        images.add(new Image("images/nurmi2.png",x*20,y*20));                            
                    }    
                }
                grid.setWeight(x, y, x+1, y, weight);
                grid.setWeight(x, y, x-1, y, weight);
                grid.setWeight(x, y, x, y+1, weight);
                grid.setWeight(x, y, x, y-1, weight);
            }
        }
    }
    private void startTheAdventure(int x1,int y1,int x2,int y2){
        shortestPath(x1,y1,x2,y2);
    }
    private void shortestPath(int x1, int y1, int x2, int y2) {
        long start = System.currentTimeMillis();
        Path p = this.grid.shortestPath(x1, y1, x2, y2);
        long end = System.currentTimeMillis();
        System.out.println("Polku: ");
        System.out.println(p.toString());
        System.out.println("Suoritusaika: " + (end-start) +  " millisekunttia");
        LinkedList<Vertex> path = p.getPath();
        Iterator<Vertex> i = new Iterator<Vertex>(path);
        while(i.hasNext()){
            Vertex v = i.getNext();
            Coordinate c = (Coordinate)v.getData();
            waypoints.add(new Coordinate(c.getX()*20+6,c.getY()*20+6));
        }
        Window window = new Window(this.mapWidth*20,this.mapHeight*20,images,waypoints);
        window.initialize();
    }

    private int[] getStartAndEnd(Scanner scanner) {
        int[] startAndEnd = new int[4];
        String regex = "[0-9]+,[0-9]+";
        System.out.println("Anna lähtöpaikka muodossa x,y: ");
        String coords = scanner.nextLine();
        while(!coords.matches(regex)){
            System.out.println("Anna lähtöpaikka muodossa x,y: ");
            coords = scanner.nextLine();
        }
        startAndEnd[0]=Integer.parseInt(coords.split(",")[0]);
        startAndEnd[1]=Integer.parseInt(coords.split(",")[1]);
        while(startAndEnd[0]<0 || startAndEnd[0]>mapWidth-1 || startAndEnd[1]<0 || startAndEnd[1]>mapHeight-1){
            System.out.println("Anna lähtöpaikka muodossa x,y, joka on kartan sisällä!: ");
            coords = scanner.nextLine();
            startAndEnd[0]=Integer.parseInt(coords.split(",")[0]);
            startAndEnd[1]=Integer.parseInt(coords.split(",")[1]);
        }
        regex = "[0-9]+,[0-9]+";
        System.out.println("Anna määränpää muodossa x,y: ");
        coords = scanner.nextLine();
        while(!coords.matches(regex)){
            System.out.println("Anna määränpää muodossa x,y: ");
            coords = scanner.nextLine();
        }
        startAndEnd[2]=Integer.parseInt(coords.split(",")[0]);
        startAndEnd[3]=Integer.parseInt(coords.split(",")[1]);
        while(startAndEnd[2]<0 || startAndEnd[2]>mapWidth-1 || startAndEnd[3]<0 || startAndEnd[3]>mapHeight-1 || (startAndEnd[0]==startAndEnd[2] && startAndEnd[1]==startAndEnd[3])){
            System.out.println("Anna määränpää muodossa x,y, joka on kartan sisällä, eikä sama kuin lähtöpaikka!: ");
            coords = scanner.nextLine();
            startAndEnd[2]=Integer.parseInt(coords.split(",")[0]);
            startAndEnd[3]=Integer.parseInt(coords.split(",")[1]);
        }
        return startAndEnd;
    }
    private int[] getSize(Scanner scanner){
        int[] size = new int[2];
        String regex = "[0-9]+";
        System.out.println("Anna kartan mitat: ");
        System.out.println("Korkeus: ");
        String measure = scanner.nextLine();
        while(!measure.matches(regex)){
            System.out.println("Korkeus: ");
            measure = scanner.nextLine();
        }
        size[0]=Integer.parseInt(measure);
        while(size[0]<3 || size[0]>50){
            System.out.println("Anna korkeus, joka on suurempi kuin 2 ja pienempi kuin 50: ");
            System.out.println("Korkeus: ");
            measure = scanner.nextLine();
            size[0]=Integer.parseInt(measure);
        }
        regex = "[0-9]+";
        System.out.println("Leveys: ");
        measure = scanner.nextLine();
        while(!measure.matches(regex)){
           System.out.println("Leveys: ");
           measure = scanner.nextLine(); 
        }
        size[1]=Integer.parseInt(measure);
        while(size[1]<3 || size[1]>50){
            System.out.println("Anna leveys, joka on suurempi kuin 2 ja pienempi kuin 50: ");
            System.out.println("leveys: ");
            measure = scanner.nextLine();
            size[1]=Integer.parseInt(measure);
        }
        return size;
    }
}
