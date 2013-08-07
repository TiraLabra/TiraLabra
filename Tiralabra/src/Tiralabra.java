
/**
 *
 * @author virta
 */
import Dictionary.HashedByteDictionary;
import MultiByteEntities.*;
import java.util.Random;

public class Tiralabra {

    public static void main(String[] args) {

        byte[] randomData = new byte[2000000];
        Random randomByteGenerator = new Random(2719); //seed is prime number for maximum randomness.
        randomByteGenerator.nextBytes(randomData);
        HashedByteDictionary dictionary = new HashedByteDictionary();

        for (int i = 2; i < 4; i++) {

            testWidth(i, dictionary, randomData);

        }

    }

    public static void testWidth(int width, HashedByteDictionary dictionary, byte[] randomData) {
        System.out.println("mode: "+width);
        for (int i = 0; i < randomData.length; i += (width)) {
            if (i + width < randomData.length) {
                MultiByte multiByte = new MultiByte(width);
                for (int j = 0; j < width; j++) {
                    multiByte.addData(randomData[i + j]);
                }

                if (dictionary.contains(multiByte) == -1){
                    dictionary.put(multiByte);
                } else if (dictionary.contains(multiByte) == 1) {
                    System.out.println("");
                    System.out.print("collision: ");
                    System.out.print(multiByte.hashCode()+" ");
                    byte[] data = multiByte.getBytes();
                    for (int k=0;k<data.length;k++){
                        System.out.print(k+": "+data[k]);
                    }
                    System.out.println("");
                    System.out.print("Stored: ");
                    data = dictionary.fetch(multiByte.hashCode()).getBytes();
                    for (int k=0;k<data.length;k++){
                        System.out.print(k+": "+data[k]+", ");
                    }
                }
            }

        }
    }
}

