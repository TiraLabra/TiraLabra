

package com.mycompany.tiralabra_maven;


import java.awt.Image;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;

/**
 *Hiiri-luokka 
 * 
 *Hiiren piirtämistä ja sijaintia varten. Lisätty myös mahdollisuus liikuttaa 
 * hiirtä näppäimistöllä mutta sitä ei ohjelmassa tällä hetkellä käytetä.
 * 
 */
public class Hiiri {
    
    private String hii = "maus.png";

    private  int dx;
    private  int dy;
    private  int x, x_coord;
    private  int y, y_coord;
    private Image image;
    private Wall wall;

    /**
     *Konstruktori. Luo hiiren kuvan ja alkusijainnin Boardilla.
     * @param wall
     */
    public Hiiri(Wall wall){
        ImageIcon ii = new ImageIcon("maus.png");
        image = ii.getImage();
        x = 0;
        y = 0;
        this.wall = wall;
    }

    /**
     *Hiiren liikkumismetodi. 
     * 
     * Hiiri liikkuu Boardilla dx ja dy:n verran, jotka määritellään keyPressed
     * metodilla. 
     * 
     * Koordinaatit x_coord ja y_coord käytetään apuna seinän löytämisessä. 
     * Tämä ei toimi vielä.
     */
       public void move() {

        if(dx < 0){
            if(x_coord == 0){
                x_coord = 0;
            }else{
                x_coord = x_coord - 1;
            }
        }   
            
        
        if(dx > 0){
            if(x_coord == Wall.leveys - 1){
                x_coord = x_coord;
            }else{
                x_coord = x_coord + 1;
            }            
        }
        if(dy < 0){
            if(y_coord == 0){
                y_coord = 0;
            }else{
                y_coord = y_coord - 1;
            }
        }
        if(dy > 0){
            if(y_coord == Wall.korkeus - 1){
                y_coord = y_coord;
            }else{
                y_coord = y_coord + 1;
            }
        }
        //hiiri pysyy pelilaudan sisällä. korkeudessa on Jframen bordereiden vuoksi 90.
        if (y >= Hiirestys.kor - 90){
            y = Hiirestys.kor - 90;
            
        }
        if(y <= 0){
            y = 0;
        }
        if (x >= Hiirestys.lev - 50){
            x = Hiirestys.lev - 50;
        }
        if(x <= 0){
           x = 0;
        }   
        //hiiri liikkuu aina 50 kerrallaan.
        x += dx;
        y += dy;
        
   }

    /**
     *Palauttaa arvonaan hiiren x-koordinaatin taustalla (boardilla). Hiiri 
     * piirretään tähän kohtaan.
     * @return
     */
    public  int getX() {
      return x;
    }

    /**
     *Palauttaa arvonaan hiiren y-koordinaatin taustalla (boardilla). HIiri 
     * piirretään tähän kohtaan.
     * @return
     */
    public  int getY() {
        return y;
    }
    
    /**
     *Palauttaa arvonaan hiiren x-koordinaatin taulukossa
     * @return
     */
    public  int getXcoord(){
        return x_coord;
    }
    
    /**
     *Palauttaa arvonaan hiiren y-koordinaatin taulukossa
     * @return
     */
    public  int getYcoord(){
        return y_coord;
    }
    
    /**
     * Asettaa hiiren x-koordinaatin taulukossa
     * @param x_coord
     */
    public void setXcoord(int x_coord){
        this.x_coord = x_coord;
    }
    
    /**
     * Asettaa hiiren y-koordinaatin taulukossa
     * @param y_coord
     */
    public void setYcoord(int y_coord){
        this.y_coord = y_coord;
    }
    
    /**
     * Asettaa koordinaatit x ja y taulukon arvoina (Wall luokan taulukko)
     * @param x_coord
     * @param y_coord
     */
    public void setCoord(int x_coord, int y_coord){
        this.x_coord = x_coord;
        this.y_coord = y_coord;
    }

    /**
     *
     * @return
     */
    public Image getImage() {
        return image;
    }

    /**
     * Tätä luokkaa ei ohjelmassa tarvita mutta on valmiina jatkoa varten
     * jos hiiri pääsee lopulta liikkumaan myös näppäimistöllä.
     * @param e
     */
    public  void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
                 
        if (key == KeyEvent.VK_LEFT) {
            if(wall.getMap()[x_coord -1][y_coord]==1){
                dx = 0;
                dy = 0;
            }else{
                dy = 0;
                dx = -50;
            }
        }
        

        if (key == KeyEvent.VK_RIGHT) {
            if(wall.getMap()[x_coord +1][y_coord]==1){
                dx = 0;
                dy = 0;
            }else{
                dy = 0;
                dx = 50;
            }
//            if(x_coord == 9){
//                x_coord = x_coord;
//            }else if(Wall.getMap()[x_coord + 1][y_coord] == 1){
//                x_coord = x_coord;
//                dx = 0;
//            }
//            else{
//                x_coord = x_coord + 1;
//            }
        }

        if (key == KeyEvent.VK_UP ) {
            if(wall.getMap()[x_coord][y_coord-1]==1){
                dx = 0;
                dy = 0;
            }else{
                dy = -50;
                dx = 0;
            }

//            if(y_coord == 0 || Wall.getMap()[x_coord][y_coord] == 1){
//                y_coord = 0;
//            }else{
//                y_coord = y_coord - 1;
//            }
        }

        if (key == KeyEvent.VK_DOWN) {
            if(wall.getMap()[x_coord][y_coord + 1]==1){
                dx = 0;
                dy = 0;
            }else{
                dy = 50;
                dx = 0;
            }
//            if(y_coord == 9 || Wall.getMap()[x_coord][y_coord] == 1){
//                y_coord = y_coord;
//            }else{
//                y_coord = y_coord + 1;
//            }
        }
    }

    /**
     * Tätä luokkaa ei ohjelmassa tarvita mutta on valmiina jatkoa varten
     * jos hiiri pääsee lopulta liikkumaan myös näppäimistöllä.
     * @param e
     */
    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_UP) {
            dy = 0;
        }

        if (key == KeyEvent.VK_DOWN) {
            dy = 0;
        }
    }

    
}
 
 