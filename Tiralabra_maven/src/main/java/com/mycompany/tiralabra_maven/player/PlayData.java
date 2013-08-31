package com.mycompany.tiralabra_maven.player;

import com.mycompany.tiralabra_maven.data_structures.Node;
import com.mycompany.tiralabra_maven.data_structures.Stack;
import javax.swing.JOptionPane;

/**
 *
 * @author Joel Nummelin
 */
public class PlayData {
    private int[] usedData;
    private int usedDepth;
    private boolean usedWideData;
    
    public PlayData(){
        this.usedData = new int[3];
        this.usedDepth = 0;
        this.usedWideData = false;
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
        
        
        
        JOptionPane.showMessageDialog(null, "Used wide data: " + usedWideData + "\n" 
                + youPlayed + "\n"
                + moves + "\n"
                + "Bots multipliers: \n"
                + "Rock: " + usedData[0] + "\n"
                + "Paper: " + usedData[1] + "\n"
                + "Scissors: " + usedData[2]);
    }
    
}
