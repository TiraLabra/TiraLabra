package labyrintti.sovellus;

import java.util.ArrayList;
import java.util.Collections;
import java.util.PriorityQueue;
import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;

public class Etsija {
    //tira pruju s. 613 http://www.cs.helsinki.fi/u/floreen/tira2013/tira.pdf

    private PriorityQueue<Ruutu> kaymattomat;
    private Pohja pohja;
//    private ArrayList<Ruutu> reitti;

    public Etsija(Pohja p) {
        pohja = p;
        kaymattomat = new PriorityQueue();
//        reitti = new ArrayList();
    }

    private void alustus() {
        for (int i = 0; i < pohja.getKorkeus(); i++) {
            for (int j = 0; j < pohja.getLeveys(); j++) {
                Ruutu ruutu = pohja.getRuutu(i, j);
                ruutu.laskeEtaisyysMaaliin(pohja.getMaaliX(), pohja.getMaaliY());
                if (ruutu.equals(pohja.getLahto())) {
                    ruutu.setEtaisyysAlkuun(0);
                }
                kaymattomat.add(ruutu);
            }
        }
    }

    /**
     * A*-algoritmi, joka etsii lyhimmän reitin lähdöstä maaliin.
     */
    public void aStar() {
        alustus();
        while (!pohja.getMaali().onkoKayty()) {
            Ruutu kasiteltava = kaymattomat.poll();
            kasiteltava.setKayty(true);
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((i == 0 && j != 0) || (i != 0 && j == 0)) { //käydään läpi viereiset ruudut
                        int x = kasiteltava.getX() + i;
                        int y = kasiteltava.getY() + j;
                        if (ruutuPohjansisalla(x, y)) { //tarkistetaan että ei mennä kartan ulkopuolelle
                            Ruutu viereinen = pohja.getRuutu(x, y);
                            if (viereinen.getEtaisyysAlkuun() > kasiteltava.getEtaisyysAlkuun() + viereinen.getArvo() && viereinen.getArvo()!=9) {
                                viereinen.setEtaisyysAlkuun(kasiteltava.getEtaisyysAlkuun() + viereinen.getArvo());
                                viereinen.setEdellinen(kasiteltava);
                                kaymattomat.remove(viereinen);
                                kaymattomat.add(viereinen);
                            }
                        }
                    }
                }
            }
        }
    }

    /**
     * Tarkistetaan, että koordinaatit eivät mene taulukon ulkopuolelle.
     * @param x, kertoo ruudun rivin.
     * @param y, kertoo ruudun sarakkeen.
     * @return false, jos ruutu on ulkopuolella, muuten true.
     */
    private boolean ruutuPohjansisalla(int x, int y) {
        if (x < 0 || y < 0) {
            return false;
        }
        if (x >= pohja.getKorkeus() || y >= pohja.getLeveys()) {
            return false;
        }
        return true;
    }
    
    public ArrayList<Ruutu> getReitti(){
        ArrayList<Ruutu> reitti = new ArrayList();
        Ruutu kasiteltava = pohja.getMaali();
        while(kasiteltava!=null){
            reitti.add(kasiteltava);
            kasiteltava = kasiteltava.getEdellinen();
        }
        Collections.reverse(reitti);
        return reitti;
    }
    
    public void tulostaReitti(ArrayList<Ruutu> reitti){
        for (Ruutu ruutu : reitti) {
            System.out.println(ruutu.getX() + "," + ruutu.getY());
        }
    }
}
