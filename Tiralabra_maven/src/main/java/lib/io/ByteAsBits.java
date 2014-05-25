package lib.io;

/** Tavujen muuntamiseksi biteiksi ja toisinpäin.
 *
 */
public class ByteAsBits {
    
    private byte b;
    
    /**
     * Alustaa tavun arvon kokonaisluvusta 0-256.
     * @param b integer 0-256
     */
    public ByteAsBits(int b){
        try{
            this.b = (byte)(b - 128);
        } catch(Exception e){
            throw new IllegalArgumentException();
        }    
    }
    /**
     * Alustaa tavun arvon boolean-taulukosta.
     * @param bits 1-8 paikkainen taulukko. 
     */
    public ByteAsBits(boolean[] bits){
        if(bits.length > 8){
            throw new IllegalArgumentException();
        }
        
        String str = "";
        for(boolean b: bits){
            if(b){ str = str + "1";} 
            else { if(!str.isEmpty()) str = str + "0";}
        }
        if(str == ""){str = "0";}
        this.b = (byte)(Integer.parseInt(str,2)-128);

    }
    /**
     * Alustaa tavun arvon byte-tyypin mukaan, siis arvot -128 - 127. 
     * @param b
     */
    public ByteAsBits(byte b){
        this.b = b;
    }
    
    @Override
    public String toString(){
        int n = b+128;
        String str = Integer.toBinaryString(n);
        while(str.length() < 8){
            str = "0" + str;
        }        
        
        return str;
    }
    /**
     * Palauttaa bitin kohdassa i.
     * @param i 
     * @return true = 1, false = 0;
     */
    public boolean getBitAt(int i){
        String str = this.toString();
        char c = str.charAt(i);
        return c == '1';
    }
    /**
     * Palauttaa tavun 8-paikkaisena boolean-taulukkona.
     * @return 
     */
    public boolean[] getAllBits(){
        boolean[] bits = new boolean[8];
        String str = this.toString();
        for(int i = 0; i < str.length(); i++){
            bits[i] = str.charAt(i) == '1';
        }
        return bits;
    }
    /**
     * Palauttaa tavun arvon byte-tyyppisenä( -128 - 127).
     * @return 
     */
    public byte getByte(){
        return b;
    }
    
}
