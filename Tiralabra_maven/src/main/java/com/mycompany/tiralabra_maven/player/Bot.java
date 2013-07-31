package com.mycompany.tiralabra_maven.player;

import java.io.File;

/**
 *
 * @author Joel Nummelin
 */
public class Bot {
    private Ai ai;
    
    public Bot(){
        ai = new Ai();
    }
    
    
    public int makeAMove(){
        return 1;
    }
    
    public void upDateAi(int result){
        
    }
    
    public void loadProfile(File file){
        
    }
    
}