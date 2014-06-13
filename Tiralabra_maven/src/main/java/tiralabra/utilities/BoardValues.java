/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tiralabra.utilities;

import java.math.BigInteger;
import java.util.Objects;

/**
 * Values for each board.
 */
public class BoardValues {

    BigInteger hash;
    int value;
    int depth;
    long bestMove;

    @Override
    public String toString() {
        return "" + hash;
    }

    @Override
    public int hashCode() {
        return hash.intValue();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final BoardValues other = (BoardValues) obj;
        if (!Objects.equals(this.hash, other.hash)) {
            return false;
        }
        return true;
    }
    
}
