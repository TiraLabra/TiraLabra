
package com.mycompany.tiralabra_maven;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import javax.swing.JFrame;


/**
 *
 * @author Tiina
 */
public class Hiirestys extends JFrame{
    public static int lev = 500;
    //Jframen bordereiden vuoksi lisään nyt tähän 40. se on suunnilleen niin. 
    public static int kor = 540;
    
    /**
     *
     */
    public Hiirestys(){
        add(new Board());
        setTitle("Hiiri!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(lev, kor);
        setLocationRelativeTo(null);
        setVisible(true);
        setResizable(false);
        
    
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
                new Hiirestys();
                    int [][] map= {
                 {0,0,0,0,0,0,0,0,0,0},
                 {0,0,0,0,0,0,0,0,0,0},
                 {0,0,1,1,0,0,0,0,0,0},
                 {0,0,0,1,0,0,0,1,1,1},
                 {0,0,0,1,0,0,0,1,0,0},
                 {0,0,0,1,0,0,0,1,0,0},
                 {0,0,0,1,1,1,1,1,0,0},
                 {0,0,0,0,0,0,0,0,0,0},
                 {0,0,0,0,0,0,0,0,0,0},
                 {0,0,0,0,0,0,0,0,2,0}
               };
            
        
        int y_map = 0;
        int x_map = 0;
       
        for(int i = 0; i < 10; i++){
                for(int j = 0; j < 10; j++){
                    if(map[i][j] == 0){
                        System.out.println(y_map + " " + x_map);                    
                    }
                    y_map = y_map + 50;
                } 
                y_map = 0;
                x_map = x_map + 50;
            }
        
    }


    
}
