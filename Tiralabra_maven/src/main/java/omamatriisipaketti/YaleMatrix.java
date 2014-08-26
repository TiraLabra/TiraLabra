
package omamatriisipaketti;

/**
 * Yale-formaatissa oleva matriisi. Tämä formaatti on tarkoitettu tallentamaan matriiseja, joissa 
 * on hyvin vähän muita kuin nollia. 
 * @author risto
 */
public class YaleMatrix implements Matrix {
    /**
     * Non-zero values. Taulukkoon on koottu matriisin arvot, jotka eivät ole nollia.
     */
    private double[] nonzerovalues;
    /**
     * Rivin ensimmäisen non-zero-alkion indeksi. Taulukossa on jokaisen rivin vasemmalta katsoen ensimmäisen
     * ei-nolla-alkion arvo. Taulukon viimeinen alkio kertoo kaikkien matriisin alkioiden määrän.
     */
    private int[] rivinEnsimmaisenNZAlkionIndeksi;
    /**
     * Jokaisen non-zero alkion sarakeindeksi. Taulukkoon on koottu jokaisen ei-nolla-alkion sarake.
     */
    private int[] jokaisenNZAlkionSarakeIndeksi;
    
    public YaleMatrix(double[][] arvot) {
        int nzalkiot = laskeNZAlkiot(arvot);
        this.nonzerovalues = keraaNZAlkiot(arvot, nzalkiot);
        this.rivinEnsimmaisenNZAlkionIndeksi = haeEnsimmaistenNZAlkioidenIndeksit(arvot, nzalkiot);
    
    }
    
    
    /**
     * Laske non-zero alkiot. Metodi laskee moniko kaksiulotteisin taulukoista on arvoltaan
     * muuta kuin nolla.
     * @param arvot kaksiulotteinen double-taulukko.
     * @return Muiden kuin nolla alkioiden määrä int-arvona.
     */
    private int laskeNZAlkiot(double[][] arvot) {
        int nZAlkioita = 0;
        for (int i = 0; i < arvot[0].length;i++) {
            for (int j = 0; j <arvot[1].length; j++) {
                if (arvot[i][j]!=0) {
                    nZAlkioita++;
                } 
            }
        }
        return nZAlkioita;
    }
    
    /**
     * Kerää non-zero alkiot. Metodi kokoaa kaksiulotteisen taulukon kaikki alkiot yhteen yksiulotteiseen taulukkoon.
     * 
     * @param arvot double arvoja sisältävät kaksiulotteinen taulukko.
     * @param nZAlkioita kokonaisluku, joka kertoo muiden kuin nolla-alkioiden määrän.
     * @return Metodi palauttaa muut kuin nolla koottuna yhdeksi listaksi, siten että arvot ovat järjestyksessä
     * vasemmalta oikealle ja ylhäältä alas.
     */
    private double[] keraaNZAlkiot(double[][] arvot, int nZAlkioita) {
        double[] palautettava = new double[nZAlkioita];
        int k = 0;
        
        for (int i = 0; i < arvot[0].length; i++) {
            for (int j = 0; j < arvot[1].length; j++) {
                if (arvot[i][j]!= 0) {
                    palautettava[k]=arvot[i][j];
                    k++;
                }
            }
        }
        return palautettava;
    }
    
    
    /**
     * Hae ensimmäisten non-zero-alkioiden indeksit. Metodi tutkii kunkin matriisin rivin ja
     * selvittää sen ensimmäisen ei-nolla-alkion sarakeindeksin. 
     * @param arvot double arvoja sisältävät kaksiulotteinen taulukko.
     * @param nzalkiot ei-nolla-alkioiden lukumäärä.
     * @return Yksiulotteinen lista, jossa on kunkin rivin osalta tieto ensimmäisen nz-alkion paikasta
     * sekä viimeisenä alkiona kaikkien matriisin alkioiden määrä.
     */
    private int[] haeEnsimmaistenNZAlkioidenIndeksit(double[][] arvot, int nzalkiot) {
        int[] palautettava = new int[nzalkiot+1];
        int palautettavanIndeksi = 0;
        for (int i = 0; i < arvot[0].length; i++) {
            int k = 0;
            while(arvot[i][k]==0) {
                k++;
            }
            palautettava[palautettavanIndeksi]=k;
            palautettavanIndeksi++;
        }
        
        palautettava[palautettavanIndeksi]=arvot[0].length*arvot[1].length;
        return palautettava;
    }
    
    /**
     * Hae kaikkien non-zero-alkioiden indeksit. Metodi tuottaa listan kaikkien 
     * ei-nolla alkioiden sijainneista sarakkeiden suhteen.
     * @param arvot
     * @param nzalkiot
     * @return 
     */
    private int[] haeKaikkienNZAlkioidenIndeksit(double[][]arvot, int nzalkiot) {
        int[] palautettava = new int[nzalkiot];
        int palautettavanIndeksi = 0;
        for (int i = 0; i < arvot[0].length;i++) {
            for (int k=0; k < arvot[1].length;k++) {
                if (arvot[i][k]!=0) {
                    palautettava[palautettavanIndeksi]=k;
                    palautettavanIndeksi++;
                }
            }
        }
        return palautettava;
        
        
    }
    
    /**
     * Skalaarimonikerta. Metodi kertoo jokaisen matriisin ei-nolla-alkion 
     * parametrina annettulla skalaarilla.
     * @param skalaari double tyyppinen reaaliluku
     * @return metodi palauttaa matriisin Yale-formaatissa.
     */
    public YaleMatrix smoni(double skalaari) {
        for (int i = 0; i < this.nonzerovalues.length;i++) {
            nonzerovalues[i] = nonzerovalues[i]*skalaari;
        }
        
        return this;
    }

    public Matrix plus(Matrix matrix) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
