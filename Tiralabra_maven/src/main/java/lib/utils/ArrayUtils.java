
package lib.utils;

/**
 *Staattisia metodeja taulukkojen muunnoksiin.
 * @author Iiro
 */
public class ArrayUtils {
    /**
     * Palauttaa binääriluvun mukaisen boolean-taulukon. 
     * @param k 
     * @param length Binääriesityksen(taulukon) pituus.
     * @return 
     */
    public static boolean[] intToBooleanArray(int k, int length){
        
        boolean[] bits = new boolean[length];
        int i = 0;
        
        String str = Integer.toBinaryString(k);
        if(str.length() > length){
            throw new IllegalArgumentException();
        }
        
        while(str.length() < length){
            str = "0" + str;
        }
        
        for(char c : str.toCharArray()){
            if(c == '1'){
                bits[i] = true;
            } else {bits[i] = false;}
            i++;
        }
        
        return bits;
    }
    /**
     * Palauttaa boolean-taulukkoa vastaavan binääriesityksen integerinä. 
     * @param bits 
     * @return 
     */
    public static int booleanArrayToInt(boolean[] bits){
        String str = "";
        
        for(boolean b: bits){
            if(b){ str = str + "1";} 
            else { if(!str.isEmpty()) str = str + "0";}
        }
        if(str == ""){str = "0";}
        
        return Integer.parseInt(str,2);        
    }
    /**
     * Yhdistää kaksi taulukkoa. a ensin, sitten b. 
     * @param a
     * @param b
     * @return Yhdistetty taulukko.
     */
    public static byte[] combine(byte[] a, byte[] b){
        int alen = 0;
        int blen = 0;        
        
        if(a != null){
            alen = a.length;
        } if(b != null){
            blen = b.length;
        }
        
        byte[] ret = new byte[alen+blen];        
        int i = 0;
        for(int j = 0; j < alen; j++){
            ret[i] = a[j] ;
            i++;
        } for (int j = 0; j < blen; j++){
            ret[i] = b[j];
            i++;        
        }
        
        return ret;
    }
    /**
     * Muuttaa byte-taulukon stringiksi.
     * @param bytes
     * @return 
     */
    public static String byteArrayToString(byte[] bytes){
        String ret = null;
        if(bytes != null){
            ret = "";

            for(byte b: bytes){
                ret += (char)b;
            }
        }
        return ret;
        
    }
    
    /**
     * Muuttaa stringin byte-taulukoksi.
     * @param str
     * @return null, jos string on tyhjä.
     */
    public static byte[] stringToByteArray(String str){
        byte[] ret = null;
        if(str != null && !str.isEmpty()){
            ret = new byte[str.length()];
            for(int i = 0; i < str.length(); i++){
                ret[i] = (byte)str.toCharArray()[i];
            }
        }
        return ret;
        
    }
}
