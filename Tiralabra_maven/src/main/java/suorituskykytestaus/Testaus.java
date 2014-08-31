

package suorituskykytestaus;
import yleismetodeja.*;
import java.io.*;
import omamatriisipaketti.*;

public class Testaus {
    
    public static void testaaStrassen() throws Exception {
        double[][] matriisi1;
        double[][] matriisi2;
        File tiedosto = new File("/home/risto/TiraLabra/Docs/strassen.csv");
        FileWriter kirjoitin = new FileWriter(tiedosto);
        double[][] tulos;
        for (int i = 4; i <=1100; i=i+100) {
            matriisi1=Taulukko.luoSatunnainenNelioMatriisi(i);
            matriisi2=Taulukko.luoSatunnainenNelioMatriisi(i);
            long aikaAlussa = System.currentTimeMillis();
            tulos = Peruslasku.yleinenStrassen(matriisi1, matriisi2, i, 4);
            long aikaLopussa = System.currentTimeMillis();
            long aika =aikaLopussa-aikaAlussa;
            String tulostettava = i + "," + aika+"\n";
            kirjoitin.append(tulostettava);
            System.out.println("kierros " + i + "kesti " + aika + "ms");
        }
        kirjoitin.close();
    }
    
    public static void testaaDeterminantti() throws Exception {
        double[][] m1;
        File tiedosto = new File("/home/risto/TiraLabra/Docs/determinantti.csv");
        FileWriter kirjoitin = new FileWriter(tiedosto);
        for (int i = 4; i < 1200; i=i+100) {
            m1 = Taulukko.luoSatunnainenNelioMatriisi(i);
            long aikaAlussa = System.currentTimeMillis();
            double tulos = Peruslasku.det(m1);
            long aikalopussa = System.currentTimeMillis();
            long aika = aikalopussa - aikaAlussa;
            String tulostettava = i + "," + aika+"\n";
            kirjoitin.append(tulostettava);
            System.out.println("kesti " + aika);
        }
        kirjoitin.close();
    }
    
    public static void testaaSparseDense() throws Exception {
        double[][] sparse;
        File tiedosto = new File("/home/risto/TiraLabra/Docs/sparsedense.csv");
        FileWriter kirjoitin = new FileWriter(tiedosto);   
        double[][] dense;
        
        for (int i = 4; i < 2000; i=i+100) {
            sparse = new double[i][i];
            dense = new double[i][i];
            for (int h = 0; h < i; h++) {
                for (int k = 0; k < i; k++) {
                    if (Math.random()<0.05) {
                        sparse[h][k]=Math.floor(Math.random()*100);
                    }
                    dense[h][k] = Math.floor(Math.random()*100);
                }
            }
            YaleMatrix ya = new YaleMatrix(sparse);
            
            long aikaAlussa = System.currentTimeMillis();
            double[][] tulos = ya.kerro(dense);
            long aikalopussa = System.currentTimeMillis();
            long aika = aikalopussa - aikaAlussa;
            String tulostettava = i + "," + aika+"\n";
            kirjoitin.append(tulostettava);
            System.out.println("kesti " + aika);            
            
        }
        kirjoitin.close();
    }
    
    public static void testaaNaivi() throws Exception {
        double[][] matriisi1;
        double[][] matriisi2;
        File tiedosto = new File("/home/risto/TiraLabra/Docs/naivi.csv");
        FileWriter kirjoitin = new FileWriter(tiedosto);
        double[][] tulos;
        for (int i = 4; i <=1100; i=i+100) {
            matriisi1=Taulukko.luoSatunnainenNelioMatriisi(i);
            matriisi2=Taulukko.luoSatunnainenNelioMatriisi(i);
            long aikaAlussa = System.currentTimeMillis();
            tulos = Peruslasku.naivemultiply(matriisi1, matriisi2);
            long aikaLopussa = System.currentTimeMillis();
            long aika =aikaLopussa-aikaAlussa;
            String tulostettava = i + "," + aika+"\n";
            kirjoitin.append(tulostettava);
            System.out.println("kierros " + i + "kesti " + aika + "ms");
        }
        kirjoitin.close();       
    }
    
    public static void testaaNaiviSparseDense() throws Exception {
        double[][] sparse;
        File tiedosto = new File("/home/risto/TiraLabra/Docs/naivisparsedense.csv");
        FileWriter kirjoitin = new FileWriter(tiedosto);   
        double[][] dense;
        
        for (int i = 4; i < 2000; i=i+100) {
            sparse = new double[i][i];
            dense = new double[i][i];
            for (int h = 0; h < i; h++) {
                for (int k = 0; k < i; k++) {
                    if (Math.random()<0.05) {
                        sparse[h][k]=Math.floor(Math.random()*100);
                    }
                    dense[h][k] = Math.floor(Math.random()*100);
                }
            }
            
            long aikaAlussa = System.currentTimeMillis();
            double[][] tulos = Peruslasku.naivemultiply(sparse, dense);
            long aikalopussa = System.currentTimeMillis();
            long aika = aikalopussa - aikaAlussa;
            String tulostettava = i + "," + aika+"\n";
            kirjoitin.append(tulostettava);
            System.out.println("kesti " + aika);            
            
        }
        kirjoitin.close();
        
    }
    
    public static void testaaKaanto() throws Exception{
        double[][] m1;
        File tiedosto = new File("/home/risto/TiraLabra/Docs/kaanto.csv");
        FileWriter kirjoitin = new FileWriter(tiedosto);
        for (int i = 4; i < 1200; i=i+100) {
            m1 = Taulukko.luoSatunnainenNelioMatriisi(i);
            long aikaAlussa = System.currentTimeMillis();
            double[][] tulos = Peruslasku.inv(m1);
            long aikalopussa = System.currentTimeMillis();
            long aika = aikalopussa - aikaAlussa;
            String tulostettava = i + "," + aika+"\n";
            kirjoitin.append(tulostettava);
            System.out.println("kesti " + aika);
        }
        kirjoitin.close();        
    }
    
}
