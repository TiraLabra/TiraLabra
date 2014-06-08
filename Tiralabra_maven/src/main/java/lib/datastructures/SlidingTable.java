
package lib.datastructures;

import lib.utils.ByteAsBits;

/**
 * Päällekirjoittava taulukko. Taulukosta ei voi erikseen poistaa alkioita, vaan ylikirjoitettu alkio palautetaan add-kutsun yhteydessä.
 * @author Iiro
 */
public class SlidingTable {
        private int size;
        private byte[] table;
        private int lastIndex;
        private int length;
        
        /**
         *  @param size Taulukon maksimikoko. Kun maksimikoko on saavutettu, aletaan päällekirjoittaminen alkaen pienimmistä indekseistä. 
         */
        public SlidingTable(int size){
            this.size = size;
            table = new byte[size];
            lastIndex = -1;
            length = 0;
        }
        /**
         * Lisää tavun taulukkoon, suurimpaan indeksiin. Mikäli taulukon maksimikoko on saavutettu, kirjoitettaan ensimmäisenä lisätyn päälle.
         * @param b Lisättävä tavu.
         * @return Päällekirjoitetun tavun. Jos taulukko ei ole täysi, palauttaa null.
         */
        public ByteAsBits add(byte b){            
            ByteAsBits ret = null;
            lastIndex++;
                     
            if(lastIndex >= size){
                lastIndex = 0;
            } if(length >= size){
                ret = new ByteAsBits(table[lastIndex]);
            } else{ 
                length++; 
            }            
            table[lastIndex] = b;

            return ret;
        }
        /** Palauttaa alkion.
         * @param offset alkion indeksi suhteessa ensimmäisenä lisättyyn.
         * @return 
         */
        public byte get(int offset){
            int index = lastIndex+1 + offset;
            if (index >= length){
                index = index-length ;
            }
            return table[index];
        }
        /**
         * Palauttaa taulukon kaikki alkiot järjestyksessä. Nollaa taulukon.
         * @return Koko taulukon, lisäysjärjestyksessä.
         */
        public byte[] pollAll(){
            byte[] ret = new byte[length];
            for (int i = 1; length > 0; i++){
                int index = lastIndex+i;
                if(index >= size){
                    index = index-size;
                }
                length--;
                ret[i-1] = table[index];
                table[index] = -128;
            }
            return ret;
        }
        /**
         * Palauttaa todellisen indeksin suhteellisen indeksin verrattuna viimeeksi lisättyyn. 
         * @param index
         * @return 
         */
        public int relativeIndex(int index){
            int zero = lastIndex + 1;
            if(zero == length){zero = 0;}
            int ret = index - zero;
            if(ret < 0){
                ret = length + ret;
            }
            if(index >= length){
                throw new IndexOutOfBoundsException();
            }
            return ret;            
        }
        
        public int getLastIndex(){
            return lastIndex;
        }

        public int getLength() {
            return length;
        }
        
        
}
