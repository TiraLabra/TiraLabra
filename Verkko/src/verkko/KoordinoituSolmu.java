

package verkko;

/**
 *
 * @author Arvoitusmies
 */
public class KoordinoituSolmu extends Solmu{
    private final Double[] koordinaatit;

    /**
     *
     * @param koordinaatit
     */
    public KoordinoituSolmu(Double[] koordinaatit){
        this.koordinaatit=koordinaatit;
    }

    /**
     *
     * @return
     */
    public Integer dimensio(){
        return koordinaatit.length;
    }

    /**
     *
     * @param i
     * @return
     */
    public Double koordinaatti(Integer i){
        return koordinaatit[i];
    }

    /**
     *
     * @param ks
     * @return
     */
    public Double euklidinenEtaisyys(KoordinoituSolmu ks){
        int pienempi = koordinaatit.length<ks.dimensio()?koordinaatit.length:ks.dimensio();
        int erotus=Math.abs(koordinaatit.length-ks.dimensio());
        Double summa=0.0;
        for (int i = 0; i < pienempi; i++) {
            summa+=Math.pow(koordinaatit[i]-koordinaatti(i), 2);
        }
        for (int i = pienempi; i <pienempi+erotus; i++) {
            summa+=Math.pow(koordinaatit[i], 2);
        }
        return Math.sqrt(summa);
    }
}
