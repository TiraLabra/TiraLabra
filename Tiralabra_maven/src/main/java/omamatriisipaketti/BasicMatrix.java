
package omamatriisipaketti;

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
    
    
    /**
     * Kertolasku naivisti toteutettuna. 
     * @param matrix
     * @return
     * @throws Exception 
     */
    public Matrix naivemultiply(Matrix matrix) throws Exception {
        
        double[][] kertoja = matrix.getArray();
        int mkertoja = kertoja[0].length;
        int nkertoja = kertoja[1].length;
        
        if (this.n != mkertoja) {
            throw new IllegalArgumentException("Ulottuvuudet eivät ole yhteensopivat");
        }
        
        double[][] tulos = new double[this.m][nkertoja];
        
        for (int i = 0; i < this.m;i++) {
            for (int k = 0; k < nkertoja; k++) {
                double summa = 0;
                for (int j = 0; j < this.n; j++) {
                    for (int h = 0; h < mkertoja; h++) {
                        summa = summa+this.matriisi[k][j]*kertoja[h][k];
                    }
                }
                tulos[i][k]=summa;
            }
             
        }
        return new BasicMatrix(tulos);
    }
    
    
    
    
    public Matrix rref() {
        
        double[][] rref = this.matriisi;
        
        
        
        
        return null;
    }
    
    
    
    public Matrix smoni(double skalaari) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Matrix plus(Matrix matrix) throws Exception {
        double[][] lisattava = matrix.getArray();
        double[][] tulos = new double[this.m][this.n];
        if (this.m != lisattava[0].length || this.n != lisattava[1].length) {
            throw new IllegalArgumentException("Ulottuvuudet eivät ole yhteensopivat");
        }
        for (int i = 0; i < this.m; i++) {
            for (int j = 0; j < this.n; j++) {
                tulos[i][j]=this.matriisi[i][j]+lisattava[i][j];
            }
        }
        return new BasicMatrix(tulos);
        
    }

    
    
    
    public double det() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Matrix multiplication(Matrix matrix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Matrix minus(Matrix matrix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Matrix inv() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double[][] getArray() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
