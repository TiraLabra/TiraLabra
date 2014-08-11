package huffmanKoodaaja.kasittely.tietorakenteet;

/**
 * Binääripuu, joka luodaan jonon avulla.
 */
public class Puu {

    private Solmu juuri;

    public void luo(Jono jono) {
        while (true) {
            Solmu oikea = jono.pop();
            Solmu vasen = null;
            if (!jono.empty()) {
                vasen = jono.pop();
            } else {
                juuri = oikea;
                break;
            }
            Solmu solmu = new Solmu(-1, vasen.getFrekvenssi() + oikea.getFrekvenssi());
            jono.push(solmu);
        }
    }

}
