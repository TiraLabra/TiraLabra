/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Domain;

/**
 * Toteuttaa pelialueen koordinaatistona. Pitää kirjaa peliobjekteista ja niiden
 * sijainneista.
 *
 * @author Emleri
 */
public class Luola {

    private Peliobjekti[][] luola;
    private int leveys;
    private int korkeus;

    public Luola(int x, int y) {
        luola = new Peliobjekti[x][y];
        this.leveys = x;
        this.korkeus = y;
    }

    /**
     * Lisää parametrina saadun peliobjektin sen koordinaatteihin luolastoon,
     * mikäli kyseiset koordinaatit ovat vapaana.
     *
     * @param p lisättävä peliobjekti
     * @return vahvistus lisäämisen onnistumisesta tai epäonnistumisesta
     * booleanina
     */
    public boolean lisaaObjekti(Peliobjekti p) {
        Koordinaatit k = p.getKoordinaatit();

        if (this.luola[k.getX()][k.getY()] == null) {
            this.luola[k.getX()][k.getY()] = p;
            return true;
        }

        return false;
    }

    /**
     * Siirtää parametreina saaduissa koordinaateissa sijaitsevan objektin
     * toisiin saatuihin koordinaatteihin ja päivittää uuden sijainnin myös
     * objektiin.
     *
     * @param vanha objektin nykyinen sijainti
     * @param uusi objektin uusi sijainti
     * @return true/false: operaatio onnistui/ei onnistunut
     */
    public boolean siirraObjektia(Koordinaatit vanha, Koordinaatit uusi) {
        if (luola[vanha.getX()][vanha.getY()] == null || vanha == null || uusi == null) {
            return false;
        } else if (uusi.getX() < this.leveys && uusi.getY() < this.korkeus) {
            luola[uusi.getX()][uusi.getY()] = luola[vanha.getX()][vanha.getY()];
            luola[vanha.getX()][vanha.getY()] = null;
            luola[uusi.getX()][uusi.getY()].setKoordinaatit(uusi);

            return true;
        } else {
            return false;
        }
    }

    /**
     * Getteri pelialueen korkeudelle.
     *
     * @return korkeus
     */
    public int getKorkeus() {
        return korkeus;
    }

    /**
     * Getteri pelialueen leveydelle.
     *
     * @return leveys
     */
    public int getLeveys() {
        return leveys;
    }

    /**
     * Getteri pelialueelle kaksiulotteisena taulukkona.
     *
     * @return pelialue
     */
    public Peliobjekti[][] getLuola() {
        return luola;
    }

    /**
     * Getteri hirviöiden kohteen sijainnille.
     *
     * @return sijainti koordinaatteina
     */
    public Koordinaatit getKohde() {
        // toteutus kesken
        return new Koordinaatit(0, 0);
    }
}
