
package omamatriisipaketti;
import yleismetodeja.*;
/**
 *
 * @author risto
 */
public class BasicMatrix implements Matrix {
    private double[][] matriisi;
    private int m;
    private int n;
    
    public BasicMatrix(double[][] arvot){
        this.matriisi = arvot;
        this.m = arvot[0].length;
        this.n = arvot[1].length;
    }
    
    

    
    
    
    
    
    public Matrix rref() throws Exception {
        return new BasicMatrix(Peruslasku.gaussjordan(this.matriisi));
    }
    
    
    
    public Matrix smoni(double skalaari) {
        return new BasicMatrix(Peruslasku.smoni(this.getArray(),skalaari));
    }

    
        
    
    
    
    public Matrix plus(Matrix matrix) {
        return new BasicMatrix(Peruslasku.plus(this.getArray(), matrix.getArray()));
        
    }

    
    
    public int getM() {
        return this.m;
    }
    
    public int getN() {
        return this.n;
    }
    
    public double det() throws Exception {
        return Peruslasku.det(this.getArray());
    }

    public Matrix kerro(Matrix matrix) throws Exception {
        if (this.n!=matrix.getM()) {
            throw new Exception("Matriisien ulottuvuudet eivät ole yhteensopivat");
        }
        if (this.n==this.m && matrix.getM()==matrix.getN()) {
            return new BasicMatrix(Peruslasku.yleinenStrassen(this.getArray(), matrix.getArray(), this.n, 8));
        }
        else return new BasicMatrix(Peruslasku.naivemultiply(this.getArray(), matrix.getArray()));
    }

    public Matrix minus(Matrix matrix) throws Exception {
        if (this.n!=matrix.getN() || this.m!=matrix.getM()) {
            throw new Exception("Matriisien ulottuvuudet eivät täsmää");
        }
        return new BasicMatrix(Peruslasku.minus(this.getArray(),matrix.getArray()));
    }

    public Matrix inv() throws Exception {
        return new BasicMatrix(Peruslasku.inv(this.getArray()));
    }

    public double[][] getArray() {
        return this.matriisi;
    }
    
    public LUPdecomposition lup() throws Exception {
        return new LUPdecomposition(this.getArray());
    }
    
    public void print() {
        System.out.print(Taulukko.toString(this.getArray()));
    }
}
