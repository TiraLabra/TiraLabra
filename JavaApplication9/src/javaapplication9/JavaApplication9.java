/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication9;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author frojala
 */
public class JavaApplication9 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {

        HashMap<Integer, DoubleByte> mappi = new HashMap<>();
        HashMap<Integer, Integer> freq = new HashMap<>();

        Path path = Paths.get("joku");
        byte[] data = Files.readAllBytes(path);
        int numero = 0;
        for (int i = 0; i < data.length; i = i + 3) {
            if (i + 3 < data.length) {
                byte a = data[i];
                byte b = data[i + 1];
                byte c = data[i + 2];
//                byte d = data[i + 3];

                DoubleByte byteWord = new DoubleByte(a, b, c);



                if (mappi.containsKey(byteWord.hashCode())) {
                    int frequ = freq.remove(byteWord.hashCode());
                    frequ++;
                    freq.put(byteWord.hashCode(), frequ);
//                        System.out.println("match: " + byteWord.hashCode() + " : " + frequ + "     block "+i/4+" of "+data.length/4);
                } else {
                    mappi.put(byteWord.hashCode(), byteWord);
                    freq.put(byteWord.hashCode(), 1);
//                        System.out.println("new" + "     block "+i/4+" of "+data.length/4);
                    numero++;

                }
            }

        }

        System.out.println("total entries: " + mappi.size());
        int freqSum = 0;
        for (int word : freq.keySet()) {
            freqSum += freq.get(word);
        }
        freqSum /= freq.size();
        System.out.println("Average reference frequency with single refs: " + freqSum);

        List<Integer> toRemove = new LinkedList<>();

        int maxRefs = 0;
        for (int word : freq.keySet()) {
            int wordFreq = freq.get(word);
            if (wordFreq > maxRefs) {
                maxRefs = wordFreq;
            }
            if (wordFreq < 4) {
                toRemove.add(word);
            }
        }

        for (int word : toRemove) {
            mappi.remove(word);
            freq.remove(word);
        }



        System.out.println("total entries: " + mappi.size());
        freqSum = 0;
        for (int word : freq.keySet()) {
            freqSum += freq.get(word);
        }
        freqSum /= freq.size();
        System.out.println("Average reference frequency without single refs: " + freqSum);
        System.out.println(maxRefs);
    }

    public class encodeStatistics implements Runnable {

        private HashMap<Integer, ByteClass> mappi;
        private HashMap<Integer, Integer> freq;
        private int width;

        public encodeStatistics(HashMap<Integer, ByteClass> mappi, HashMap<Integer, Integer> freq, int width) {
            this.freq = freq;
            this.mappi = mappi;
            this.width = width;
        }

        @Override
        public void run() {
            Path path = Paths.get("joku");
            byte[] data;
            boolean fileOK = true;
            try {
                data = Files.readAllBytes(path);

                for (int i = 0; i < data.length; i = i + width) {
                    if (i + width < data.length) {

                        LinkedList<Byte> byteList = new LinkedList<>();
                        for (int k = 0; k < width; k++) {
                            byteList.add(data[i + k]);
                        }

                        switch (width) {
                            case 2:
                                DoubleByte doubleByte = new DoubleByte(byteList.pollFirst(), byteList.pollFirst());
                                dealWithIt(doubleByte);
                                break;
                            case 3:
                                TripleByte tripleByte = new TripleByte(byteList.pollFirst(), byteList.pollFirst(), byteList.pollFirst());
                                dealWithIt(tripleByte);
                                break;
                            case 4:
                                QuadByte quadByte = new QuadByte(byteList.pollFirst(), byteList.pollFirst(), byteList.pollFirst(), byteList.pollFirst());
                                dealWithIt(quadByte);
                                break;
                        }
                        
                    }

                }

            } catch (IOException ex) {
                Logger.getLogger(JavaApplication9.class.getName()).log(Level.SEVERE, null, ex);
                fileOK = false;
            }


        }

        private void dealWithIt(ByteClass byteWord) {
            if (mappi.containsKey(byteWord.hashCode())) {
                int frequ = freq.remove(byteWord.hashCode());
                frequ++;
                freq.put(byteWord.hashCode(), frequ);
//                        System.out.println("match: " + byteWord.hashCode() + " : " + frequ + "     block "+i/4+" of "+data.length/4);
            } else {
                mappi.put(byteWord.hashCode(), byteWord);
                freq.put(byteWord.hashCode(), 1);
//                        System.out.println("new" + "     block "+i/4+" of "+data.length/4);

            }
        }
    }
}
