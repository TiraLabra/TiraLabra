/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.mycompany.tiralabra_maven;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

/**
 *
 * Luokassa määritellään seinien paikat ja haetaan kuva tiilelle.
 */
public class Wall {
    
//    private int dx;
//    private int dy;
//    private int x;
//    private int y;
    private Image image;
    static int leveys, korkeus;

    /**
     *Hakee seinän kuvan.
     */
    public Wall(){
        ImageIcon ii = new ImageIcon("tiili.png");
        image = ii.getImage();   
    }
    
    

//     ei voi vielä itse määritellä leveyttä ja korkeutta.
//    public static int[][] getMap(int leveys, int korkeus

//          int[][] map2 = new int[leveys][korkeus];

    /**
     * map-taulukkoon merkitään labyrinttiin halutut seinien paikat ykkösillä.
     * @return
     */
   public static int[][] getMap(){
        korkeus = (Hiirestys.kor - 40) / 50;
        leveys = Hiirestys.lev / 50;
        int [][] map= {
                 {0,0,0,0,0,0,0,0,0,0,},
                 {0,0,0,0,0,0,0,0,0,0,},
                 {1,1,1,0,0,0,0,0,0,0,},
                 {0,0,0,1,0,0,0,1,1,1,},
                 {0,0,0,0,0,0,0,1,0,0,},
                 {0,0,0,0,0,0,0,1,0,0,},
                 {0,0,0,1,1,0,0,1,0,0,},
                 {0,0,0,0,0,0,0,0,0,0,},
                 {0,0,0,0,0,0,0,1,0,0,},
                 {0,0,0,0,0,0,0,1,0,0,}
               };
        //hiiren alkupaikka on aina seinätön.
        map[0][0] = 0;
    return map;
    }
    
   
    /**
     *
     * @return
     */
        
//    public int getX() {
//        return x;
//    }

    /**
     *
     * @return
//     */
//    public int getY() {
//        return y;
//    }

    /**
     * Palauttaa arvonaan tiilen kuvan.
     * @return
     */
    public Image getImage() {
        return image;
    }
}
