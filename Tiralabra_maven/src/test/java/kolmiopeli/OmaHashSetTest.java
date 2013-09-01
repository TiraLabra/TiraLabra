package kolmiopeli;

import kolmiopeli.domain.Koordinaatti;
import kolmiopeli.logiikka.tiralabraAlgoritmit.omatTietorakenteet.OmaHashSet;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class OmaHashSetTest {

    private Koordinaatti testiK1;
    private Koordinaatti testiK2;
    private OmaHashSet joukko;

    public OmaHashSetTest() {
    }

    @Before
    public void setUp() {
        joukko = new OmaHashSet<Koordinaatti>();
        testiK1 = new Koordinaatti(1, 1);
        testiK2 = new Koordinaatti(2, 2);
    }

    @Test
    public void tyhjaJoukkoEiSisallaAlkiota() {
        assertTrue("Tyhjasta joukosta alkion etsiminen palautti true.", !joukko.contains(testiK1));
    }

    @Test
    public void joukkoonLisattyLoytyyJoukosta() {
        joukko.add(testiK1);
        assertTrue("Joukkoon lisatty ei loytynyt joukosta.", joukko.contains(testiK1));
    }

    @Test
    public void joukonKokoAluksiNolla(){
        assertTrue("Joukon koko ei ollut aluksi nolla.", joukko.size() ==0 );
    }
    
    @Test
    public void kaksiKertaaSamanAlkionLisaaminenOnnistuuEnsimmaisellaJaEpaonnistuuToisella() {
        assertTrue("Ensimmainen lisays tyhjaan joukkoon epaonnistui.", joukko.add(testiK1));
        assertTrue("Toinen lisays onnistui, vaikka joukossa oli jo sama alkio", !joukko.add(testiK1));
    }

    @Test
    public void kaksiKertaaLisattyAlkioKasvattaaJoukonKokoaVainYhdella() {
        joukko.add(testiK1);
        joukko.add(testiK1);
        assertTrue("Joukon koko kasvoi kun siihen lisattiin sama alkio kaksi kertaa perakkain.", joukko.size() == 1);
    }
    
    @Test
    public void kahdenEriAlkionLisaaminenJoukkoonOnnistuu() {
        assertTrue("Ensimmaisen alkion lisaaminen tyhjaan joukkoon epaonnistui.", joukko.add(testiK1));
        assertTrue("Toisen eri alkion lisaaminen yhden alkion joukkoon epaonnistui.", joukko.add(testiK2));
    }
    
    @Test
    public void kahdenEriAlkionLisaaminenJoukkoonKasvattaaSenKokoaKahdella() {
        joukko.add(testiK1);
        joukko.add(testiK2);
        assertTrue("Joukon koko ei kasvanut kahdella, vaan oli " + joukko.size(), joukko.size() == 2);
    }
    
    
}
