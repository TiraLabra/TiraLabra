package labyrintti.sovellus;

import java.util.ArrayList;
import java.util.PriorityQueue;
import labyrintti.osat.Pohja;
import labyrintti.osat.Ruutu;

public class Etsija {
    //tira pruju s. 613 http://www.cs.helsinki.fi/u/floreen/tira2013/tira.pdf

    private PriorityQueue<Ruutu> kaymattomat;
    private Pohja pohja;
    private ArrayList<String> reitti;

    public Etsija(Pohja p) {
        pohja = p;
        kaymattomat = new PriorityQueue();
        reitti = new ArrayList();
    }

    private void alustus() {
        for (int i = 0; i < pohja.getKorkeus(); i++) {
            for (int j = 0; j < pohja.getLeveys(); j++) {
                Ruutu ruutu = pohja.getRuutu(i, j);
                ruutu.laskeEtaisyysMaaliin(pohja.getMaaliX(), pohja.getMaaliY());
                kaymattomat.add(ruutu);
            }
        }
        Ruutu alku = pohja.getLahto();
        alku.setEtaisyysAlku(0);
        alku.laskeEtaisyysMaaliin(pohja.getMaaliX(), pohja.getMaaliY());
    }

    public void laskeRuutujenEtaisyysPisteeseen(Ruutu piste) {
        
    }

    public void aStar() {
        alustus();
        while (!pohja.getMaali().onkoKayty()) {
            Ruutu kasiteltava = kaymattomat.poll();
            kasiteltava.setKayty(true);
            for (int i = -1; i < 2; i++) {
                for (int j = -1; j < 2; j++) {
                    if ((i == 0 && j != 0) || (i != 0 && j == 0)) {
                        Ruutu viereinen = pohja.getRuutu(kasiteltava.getX() + i, kasiteltava.getY() + j);
                        //if(viereinen.getEtaisyysAlku()>)
                    }
                }
            }
        }
    }

}
