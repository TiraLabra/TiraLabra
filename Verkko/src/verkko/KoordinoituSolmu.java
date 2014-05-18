package verkko;

/**
 *
 * @author Arvoitusmies
 */
public class KoordinoituSolmu extends Solmu {

    private final Double[] koordinaatit;

    /**
     *
     * @param koordinaatit
     */
    public KoordinoituSolmu(Double[] koordinaatit) {
        this.koordinaatit = koordinaatit;
    }

    /**
     *
     * @return
     */
    public Integer dimensio() {
        return koordinaatit.length;
    }

    /**
     *
     * @param i
     * @return
     */
    public Double koordinaatti(Integer i) {
        return koordinaatit[i];
    }

    /**
     *
     * @param ks
     * @return
     */
    public Double euklidinenEtaisyys(KoordinoituSolmu ks) {
        int pienempi = koordinaatit.length < ks.dimensio() ? koordinaatit.length : ks.dimensio();
        int erotus = koordinaatit.length - ks.dimensio();
        int kumpi = (int) Math.signum(erotus);
        erotus = Math.abs(erotus);
        Double summa = 0.0;
        for (int i = 0; i < pienempi; i++) {
            summa += Math.pow(koordinaatit[i] - ks.koordinaatti(i), 2);
        }
        for (int i = pienempi; i < pienempi + erotus; i++) {
            if (kumpi == 1) {
                summa += Math.pow(koordinaatit[i], 2);
            } else {
                summa += Math.pow(ks.koordinaatti(i), 2);
            }
        }
        return Math.sqrt(summa);
    }

    public Double taksimiehenEtaisyys(KoordinoituSolmu ks) {
        Double summa = 0.0;
        int pienempi = koordinaatit.length < ks.dimensio() ? koordinaatit.length : ks.dimensio();
        int erotus = koordinaatit.length - ks.dimensio();
        int kumpi = (int) Math.signum(erotus);
        erotus = Math.abs(erotus);
        for (int i = 0; i < pienempi; i++) {
            summa += Math.abs(koordinaatit[i] - ks.koordinaatti(i));
        }
        for (int i = pienempi; i < pienempi - erotus; i++) {
            if (kumpi == 1) {
                summa += Math.abs(koordinaatit[i]);
            } else {
                summa += Math.abs(ks.koordinaatti(i));
            }
        }
        return summa;
    }
}
