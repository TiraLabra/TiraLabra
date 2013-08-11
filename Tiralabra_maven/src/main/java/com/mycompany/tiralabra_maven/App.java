package com.mycompany.tiralabra_maven;
import java.util.Scanner;
import java.util.Arrays;


public class App 


{
    private static int labyLeveys;
    private static int labyKorkeus; 
    private static Scanner lukija = new Scanner(System.in);

    public static int getLabyLeveys() {
        return labyLeveys;
    }

    public static int getLabyKorkeus() {
        return labyKorkeus;
    }

    public static void setLabyLeveys(int labyLeveys) {
        App.labyLeveys = labyLeveys;
    }

    public static void setLabyKorkeus(int labyKorkeus) {
        App.labyKorkeus = labyKorkeus;
    }
    
    
    
    
    
    
    public static void main( String[] args )
    {
        luoLaby(4,4);
        System.out.println(getLabyLeveys());

    }
    

    
    public static void luoLaby(int Leveys, int Korkeus){
        setLabyLeveys(Leveys);
        setLabyKorkeus(Korkeus);
        
        String[][] laby = new String[labyLeveys][labyKorkeus];
        for(int i = 0; i<labyLeveys; i++){
            for(int j = 0; j < labyKorkeus; j++){
                laby[i][j] = "0";
            }
        }
        
        laby[0][1] = "1";
        laby[1][1] = "1";
        laby[1][2] = "1";
        laby[2][2] = "1";
        laby[3][2] = "1";

        
        for(int i = 0; i<4; i++){
            for(int j = 0; j < 4; j++){
                System.out.print(laby[i][j]);
            }
            System.out.println();
        }
       
    return;   
    }
    
    
    
    
}
