
package chess.game;

import java.io.*;
import java.util.Scanner;

/** 
 * Tämä luokka sisältää menun logiikan.
 */ 
public class Menu {
             
    /** 
     * Pelitilanteen tallenuksen tiedostoon suorittava metodi.
     * 
     * @param board shakkilauta tallennus tilanteessa.
     * @param turns vuorojenmäärä tallennuksen hetkellä.
     * @param info pelin infon tilanne tallennuksen hetkellä.
     */    
    public void save(int[][] board, int turns, int info) throws  IOException {      
        FileWriter writer = new FileWriter("src/images/savedgame.txt");
        
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                writer.write(board[i][j]+" ");
            }
        }
        
        writer.close();
        saveTurns(turns);
        saveInfo(info);
    }
    
    
    /** 
     * Vuorojen määrän tallennus tiedostoon.
     * 
     * @param turns vuorojenmäärä tallennuksen hetkellä.
     */     
    public void saveTurns(int turns) throws IOException {
        FileWriter writer = new FileWriter("src/images/turns.txt");
        writer.write(turns+"");
        writer.close();
    }

    /** 
     * Vuorojen määrän lataus tiedostosta.
     * 
     * @return palauttaa vuorojen määrän.
     */    
    public int loadTurns() throws FileNotFoundException {
        int turns = 0;       
        File file = new File("src/images/turns.txt");        
        Scanner scanner = new Scanner(file); 
        
        while (scanner.hasNext()) {
            String text = scanner.next();
            turns = Integer.parseInt(text); 
        }
        
        scanner.close();
        return turns;
    }
    
    /** 
     * Infon tallennus tiedostoon.
     * 
     * @param info infon tilanne tallennus hetkellä.
     */     
    public void saveInfo(int info) throws IOException {
        FileWriter writer = new FileWriter("src/images/info.txt");
        writer.write(info+"");
        writer.close();
    }
    
    /** 
     * Infon tilanteen lataus tiedostosta.
     * 
     * @return infon tilanne.
     */    
    public int loadInfo() throws FileNotFoundException {
        int info = 0;
        File file = new File("src/images/info.txt");
        Scanner scanner = new Scanner(file);
        
        while (scanner.hasNext()) {
            String text = scanner.next();
            info = Integer.parseInt(text); 
        }
        
        scanner.close();
        return info;
    }    

    /** 
     * Pelilaudan lataus tiedostosta.
     * 
     * @return pelilauta.
     */     
    public int[][] loadBoard() throws FileNotFoundException {
        int[][] board = new int[8][8];
        File file = new File("src/images/savedgame.txt");
        Scanner scanner = new Scanner(file);
        int i = 0;
        int j = 0;
        
        while (scanner.hasNext()) {
            if (j == 8) {
                i++;
                j = 0;
            }
            String text = scanner.next();
            int number = Integer.parseInt(text); 
            board[i][j] = number;
            j++;            
        }
        
        scanner.close();
        return board;
    }
    
}
