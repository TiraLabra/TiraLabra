/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tiralabra.utilities;

/**
 * TODO
 * @author atte
 */
public class TranspositionTable {
    
    public class BoardValues {
        int value;
        int depth;
        long bestMove;
    }
    
    private BoardValues[] table;
    private int values;
    
    public TranspositionTable() {
        table = new BoardValues[10000];
        values = 0;
    }
    
 }
