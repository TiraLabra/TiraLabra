/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package IgnoreThisMoFo;

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
public class MainClass {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException, InterruptedException {

        HashMap<Integer, HashMap> mapit = new HashMap<>();

        Path path = Paths.get("joku");
        byte[] data = Files.readAllBytes(path);
        
        System.out.println(data.length);

//        HashMap<Integer, HashMap> freqit = new HashMap<>();
//        LinkedList<encodeStatistics> statsit = new LinkedList<>();
//        for (int i = 2; i < 5; i++) {
//            HashMap<Integer, ByteClass> mappi = new HashMap<>();
//            HashMap<Integer, Integer> freq = new HashMap<>();
//            mapit.put(i, mappi);
//            freqit.put(i, freq);
//            
//            encodeStatistics stats = new encodeStatistics(mappi, freq, i);
//            Thread t = new Thread(stats);
//            statsit.add(stats);
//            t.start();
//            
//        }
//        
//        for (int i = 0; i < statsit.size(); i++) {
//            System.out.println(statsit.get(i));
//            Thread.sleep(200);
//            if (!statsit.get(i).isReady() && i==statsit.size()-1){
//                i = -1;
//            }
//        }
//        printTheFuckers(mapit, freqit);        
    }

    private static void printTheFuckers(HashMap<Integer, HashMap> mapit, HashMap<Integer, HashMap> freqit) {
        for (int mappinro : mapit.keySet()) {

            HashMap<Integer, ByteClass> mappi = mapit.get(mappinro);
            HashMap<Integer, Integer> freq = freqit.get(mappinro);

            System.out.println("Byte Width: " + mappinro + " permutations: " + Math.pow(2, (8 * mappinro)));
            System.out.println("total entries: " + mappi.size());
            int freqSum = 0;
            for (int word : freq.keySet()) {
                freqSum += freq.get(word);
            }
            freqSum /= freq.size();
            System.out.println("Average reference frequency with all refs: " + freqSum);

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
            System.out.println("Average reference frequency without all refs under 4: " + freqSum);
            System.out.println("Max refs: " + maxRefs);
            System.out.println("");
        }
    }

    public static class encodeStatistics implements Runnable {

        private HashMap<Integer, ByteClass> mappi;
        private HashMap<Integer, Integer> freq;
        private int width;
        private boolean ready;
        private int percentDone;

        public encodeStatistics(HashMap<Integer, ByteClass> mappi, HashMap<Integer, Integer> freq, int width) {
            this.freq = freq;
            this.mappi = mappi;
            this.width = width;
            this.ready = false;
        }

        @Override
        public String toString() {
            return "Mode: " + this.width + ": " + this.percentDone + "%";
        }

        @Override
        public void run() {
            Path path = Paths.get("joku");
            byte[] data;
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

                    double k = i;
                    double j = data.length;
                    this.percentDone = (int) ((k / j) * 100);
                }
                this.percentDone = 100;

            } catch (IOException ex) {
                Logger.getLogger(MainClass.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.ready = true;
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

        public boolean isReady() {
            return this.ready;
        }
    }
}
