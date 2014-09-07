/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import junit.framework.TestCase;
import omamatriisipaketti.*;
import yleismetodeja.Peruslasku;
import yleismetodeja.Taulukko;
/**
 *
 * @author risto
 */
public class YaleMatrixTest extends TestCase {
    
    public YaleMatrixTest(String testName) {
        super(testName);
    }
    
    @Override
    protected void setUp() throws Exception {
        super.setUp();
    }
    
    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
    }

    
    
    public void testIAToimiiOikein() {
        double[][] m1 = {{0,0,1},{0,0,0},{2,2,2}};
        int[] ratkaisu = {0,-1,1,9};
        int[] tulos = YaleMatrix.muodostaIAlista(m1);
        boolean testi = true;
        for (int i = 0; i < 4; i++) {
            System.out.println("ia: " + tulos[i] + "\t ratkaisu: "+ratkaisu[i]);
            if (tulos[i] != ratkaisu[i]) {
                testi = false;
            }
        }
        assertTrue(testi);
    }
    
    public void testLaskeNZAlkiotRivilta() {
        double[][] m1 = {{0,0,0},{1,1,1}};
        assertEquals(3,YaleMatrix.laskeNZAlkiotRiviltaI(m1, 1));
    }
    
    public void testLaskeNZAlkiotRiviltaOsa2() {
        double[][] m1 = {{0,0,0},{1,1,1}, {1,0,5}};
        assertEquals(2,YaleMatrix.laskeNZAlkiotRiviltaI(m1, 2));
    }
    
    public void testNZAlkioidenLaskeminenOnnistuu() {
        double[][] m1 = {{0,1,1},{1,1,1},{0,0,7}};
        int nz = YaleMatrix.laskeNZAlkiot(m1);
        assertEquals(nz,6);
    }
    
    public void testKeraaNZAlkiotToimiiOikein() {
        double[][] m1 = {{0,0,1},{2,3,4},{4,5,0}};
        double[] ratkaisu = {1,2,3,4,4,5};
        double[] tulos = YaleMatrix.keraaNZAlkiot(m1, 6);
        boolean testi = true;
        for (int i = 0; i < 5; i++) {
            if (tulos[i] != ratkaisu[i]) {
                testi = false;
            }
        }
        assertTrue(testi);
    }
    
    public void testMuodostaJAlistaToimiiOikein() {
        double[][] m1 = {{0,0,1},{0,2,3},{4,5,0}};
        int[] ratkaisu = {2,1,2,0,1};
        int[] tulos = YaleMatrix.muodostaJAlista(m1, 5);
        boolean testi = true;
        for (int i = 0; i < 5; i++) {
            if (ratkaisu[i] != tulos[i]) {
                testi = false;
            }
        }
        assertTrue(testi);
    }
    
   
    
    public void testKonstruktoriToimiiOikein() {
        double[][] m1 = {{0,0,1},{0,0,0},{4,5,6}};
        YaleMatrix ya = new YaleMatrix(m1);
        double[] a = ya.getA();
        int[] ia = ya.getIA();
        int[] ja = ya.getJA();
        boolean testi = true;
        double[] aratkaisu = {1,4,5,6};
        int[] iaratkaisu = {0,-1,1,9};
        int[] jaratkaisu = {2,0,1,2};
        for (int i = 0; i < 4; i++) {
            if (aratkaisu[i]!=a[i] || iaratkaisu[i]!=ia[i] || jaratkaisu[i]!=ja[i]) {
                testi = false;
            }
        }
        assertTrue(testi);
    }
    
    public void testDenseMatrixKertolaskuToimiiOikein() {
        double[][] m1 = {{0,2,0},{0,0,0},{7,0,0}};
        double[][] m2 = {{1,2,7},{9,4,6},{7,6,6}};
        double[][] ratkaisu = Peruslasku.naivemultiply(m1, m2);
        YaleMatrix ya = new YaleMatrix(m1);
        double[][] tulos = ya.kerro(m2);
        System.out.println("Ratkaisu: ");
        System.out.print(Taulukko.toString(ratkaisu));
        System.out.println("Tulos:");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(Taulukko.toString(ratkaisu).equals(Taulukko.toString(tulos)));
    }
    
    public void testDenseMatrixKertolaskuToimiiNollaMatriisilla() {
        double[][] m1 = {{0,0},{0,0}};
        double[][] m2 = {{1,2},{5,7}};
        YaleMatrix ya = new YaleMatrix(m1);
        double[][] tulos = ya.kerro(m2);
        boolean testi = true;
        System.out.print(Taulukko.toString(tulos));
        System.out.print(Taulukko.toString(m1));

        if (!Taulukko.toString(tulos).equals(Taulukko.toString(m1))) {
            testi=false;
        }
        assertTrue(testi);
    }


    public void testGetArray() {
        double[][] m1 = {{0,2,0},{2,0,0},{7,0,0}};
        YaleMatrix ya = new YaleMatrix(m1);
        double[][] tulos = ya.getArray();
        assertTrue(Taulukko.toString(tulos).equals(Taulukko.toString(m1)));
    }
    
    public void testDenseMatrixKertolaskuToimiiOikeinToisellaMatriisilla() {
        double[][] m1 = {{0,2,0},{2,0,0},{7,0,0}};
        double[][] m2 = {{1,2,7},{9,4,6},{7,6,6}};
        double[][] ratkaisu = Peruslasku.naivemultiply(m1, m2);
        YaleMatrix ya = new YaleMatrix(m1);
        double[][] tulos = ya.kerro(m2);
        System.out.println("Ratkaisu: ");
        System.out.print(Taulukko.toString(ratkaisu));
        System.out.println("Tulos:");
        System.out.print(Taulukko.toString(tulos));
        assertTrue(Taulukko.toString(ratkaisu).equals(Taulukko.toString(tulos)));
    }


}
