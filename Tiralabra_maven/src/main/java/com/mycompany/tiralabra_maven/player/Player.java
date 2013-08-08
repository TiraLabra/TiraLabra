package com.mycompany.tiralabra_maven.player;

import java.io.File;

/**
 *
 * @author Joel Nummelin
 */
public class Player {
    private File file;
    
    public Player(){
    }

    public Player(File file) {
        this.file = file;
    }
    
    public boolean isGuest(){
        if (file == null){
            return true;
        }
        return false;
    }
    
}
