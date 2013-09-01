package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Node;
import com.mycompany.tiralabra_maven.data_structures.Stack;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 * This class is for showing info about ai's moves and and to follow if player tries to break ai's strategy. 
 * @author Joel Nummelin
 */
public class PlayData {
    private int[] usedData;
    private int usedDepth;
    private boolean usedWideData;
    private int alertCounter;
    private boolean usedCounterAntiStategy;

    /**
     * Constructor
     */
    public PlayData(){
        this.usedData = new int[3];
        this.usedDepth = 0;
        this.usedWideData = false;
        this.alertCounter = 0;
    }

    /**
     * Setter
     * @param usedData 
     */
    public void setUsedData(int[] usedData) {
        this.usedData = usedData;
    }


    /**
     * Setter
     * @param usedDepth 
     */
    public void setUsedDepth(int usedDepth) {
        this.usedDepth = usedDepth;
    }
    
    /**
     * Setter
     * @param b 
     */
    public void setUsedWideData(boolean b){
        this.usedWideData = b;
    }
    
    /**
     * Shows pop up window about ai's decisions. 
     * @param stack 
     */
    public void showWindow(Stack stack){
        String youPlayed = "Last " + usedDepth + " moves you played: ";
        String[] moveList = new String[4];
        Node node = stack.peek();
        for (int i = 0; i < usedDepth; i++) {
            moveList[i] = node.toString();
            node = node.getNext();
        }
        String moves = "";
        for (int i = moveList.length - 1; i >= 0; i--) {
            if (moveList[i] == null){
                continue;
            }
            moves += moveList[i] + "\n";
        }
        
        JOptionPane.showMessageDialog(null, "Used counter anti-strategy: " + usedCounterAntiStategy + "\n" 
                + "Used wide data: " + usedWideData + "\n" 
                + youPlayed + "\n"
                + moves + "\n"
                + "Your multipliers: \n"
                + "Rock: " + usedData[0] + "\n"
                + "Paper: " + usedData[1] + "\n"
                + "Scissors: " + usedData[2]);
    }
    
    
    /**
     * Determines whether there ai plays opposite or not. 
     * @return b
     */
    public boolean counterAntiStrategy(){
        usedCounterAntiStategy = false;
        if (alertCounter < 3){
            return false;
        }
        int random = new Random().nextInt(3) + 2;
        if (random < alertCounter){
            usedCounterAntiStategy = true;
            return true;
        }
        return false;
    }
    
    
    /**
     * Raises alertCounter if opponent played the least expected move. 
     * @param move 
     */
    public void update(int move){
        if (usedData[0] == usedData[1] && usedData[1] == usedData[2]){
            if (alertCounter > 2){
                alertCounter = 2;
            }
            return;
        }
           
        int smallest = 0;
        
        for (int i = 0; i < 3; i++) {
            if (usedData[i] < usedData[smallest]){
                smallest = i;
            }
        }
        
        int biggest = 0;
        
        for (int i = 0; i < 3; i++) {
            if (usedData[i] > usedData[biggest]){
                biggest = i;
            }
        }
        
        if (move == smallest){
            alertCounter++;
        }
        if (move == biggest){
            alertCounter -= 2;
        }
        
        if (alertCounter < 0){
            alertCounter = 0;
        }
        if (alertCounter > 5){
            alertCounter = 5;
        }
    }
}
