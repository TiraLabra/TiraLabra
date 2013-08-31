package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Node;
import com.mycompany.tiralabra_maven.data_structures.Stack;
import java.util.Random;
import javax.swing.JOptionPane;

/**
 *
 * @author Joel Nummelin
 */
public class PlayData {
    private int[] usedData;
    private int usedDepth;
    private boolean usedWideData;
    private int alertCounter;
    private boolean usedCounterAntiStategy;
    
    public PlayData(){
        this.usedData = new int[3];
        this.usedDepth = 0;
        this.usedWideData = false;
        this.alertCounter = 0;
    }

    public void setUsedData(int[] usedData) {
        this.usedData = usedData;
    }


    public void setUsedDepth(int usedDepth) {
        this.usedDepth = usedDepth;
    }
    
    public void setUsedWideData(boolean b){
        this.usedWideData = b;
    }
    
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
                + "Bots multipliers: \n"
                + "Rock: " + usedData[0] + "\n"
                + "Paper: " + usedData[1] + "\n"
                + "Scissors: " + usedData[2]);
    }
    
    public boolean counterAntiStrategy(){
        if (alertCounter < 3){
            usedCounterAntiStategy = false;
            return false;
        }
        int random = new Random().nextInt(3) + 2;
        if (random < alertCounter){
            usedCounterAntiStategy = true;
            return true;
        }
        return false;
    }
    
    
    public void update(int move){
        if (usedData[0] == usedData[1] && usedData[1] == usedData[2]){
            return;
        }
        check01(move);
        check02(move);
        check12(move);
        
        int smallest = 0;
        
        for (int i = 0; i < 3; i++) {
            if (usedData[i] < smallest){
                smallest = i;
            }
        }
        
        int biggest = 0;
        
        for (int i = 0; i < 3; i++) {
            if (usedData[i] > biggest){
                smallest = i;
            }
        }
        
        if (move == smallest){
            alertCounter++;
        }
        if (move == biggest){
            alertCounter = 0;
        }
        
    }

    private void check01(int move) {
        if (usedData[0] == usedData[1]){
            if (move == 0 || move == 1){
                if (usedData[0] < usedData[2]){
                    alertCounter++;
                } else{
                    alertCounter = 0;
                }
            } else {
                if (usedData[0] > usedData[2]){
                    alertCounter++;
                } else{
                    alertCounter = 0;
                }
            }
        }
    }
    private void check02(int move) {
        if (usedData[0] == usedData[2]){
            if (move == 0 || move == 2){
                if (usedData[0] < usedData[2]){
                    alertCounter++;
                } else{
                    alertCounter = 0;
                }
            } else {
                if (usedData[0] > usedData[2]){
                    alertCounter++;
                } else{
                    alertCounter = 0;
                }
            }
        }
    }
    private void check12(int move) {
        if (usedData[1] == usedData[2]){
            if (move == 1 || move == 2){
                if (usedData[1] < usedData[0]){
                    alertCounter++;
                } else{
                    alertCounter = 0;
                }
            } else {
                if (usedData[1] > usedData[0]){
                    alertCounter++;
                } else{
                    alertCounter = 0;
                }
            }
        }
    }
    
}
