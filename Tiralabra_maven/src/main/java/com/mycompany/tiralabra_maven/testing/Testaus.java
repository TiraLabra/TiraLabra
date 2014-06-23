/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.testing;

import com.mycompany.tiralabra_maven.Pakkaaja;
import com.mycompany.tiralabra_maven.domain.Kontti;
import com.mycompany.tiralabra_maven.domain.Laatikkotyyppi;
import com.mycompany.tiralabra_maven.domain.PakkausSuunnitelma;
import com.mycompany.tiralabra_maven.structures.List;
import com.mycompany.tiralabra_maven.tools.FileHandler;
import com.mycompany.tiralabra_maven.tools.Io;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Random;

/**
 * Tämä luokka sisältää testaamiseen liittyviä metodeja
 *
 * @author szetk
 */
public class Testaus {

    private Pakkaaja pakkaaja;
    private Io io;
    private Kontti kontti;
    private SetinOminaisuudet[] setinOminaisuudet;

    public Testaus(Io io, Kontti kontti) {
        this.pakkaaja = new Pakkaaja(io);
        this.io = io;
        this.kontti = kontti;
        long l = this.kontti.getTilavuus() + this.kontti.getX()*this.kontti.getY()*this.kontti.getZ()/20;
        this.setinOminaisuudet = new SetinOminaisuudet[]{
            new SetinOminaisuudet("homo1", l, 1, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo2", l, 5, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo3", l, 20, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo4", l, 50, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo5", l, 100, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo6", l, 200, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo7", l, 400, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo8", l, 600, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo9", l, 800, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("homo10", l, 1000, 20, 100, 20, 100, 20, 100),
            new SetinOminaisuudet("koko1", l, 5, 25, 28, 25, 28, 25, 28),
            new SetinOminaisuudet("koko2", l, 5, 25, 30, 25, 30, 25, 30),
            new SetinOminaisuudet("koko3", l, 5, 30, 50, 30, 50, 30, 50),
            new SetinOminaisuudet("koko4", l, 5, 50, 80, 50, 80, 50, 80),
            new SetinOminaisuudet("koko5", l, 5, 80, 100, 80, 100, 80, 100),
            new SetinOminaisuudet("koko6", l, 5, 100, 120, 100, 120, 100, 120),
            new SetinOminaisuudet("koko7", l, 5, 120, 160, 120, 160, 120, 160),
            new SetinOminaisuudet("patukat", l, 5, 1000, 2000, 10, 30, 10, 30)
        };
    }

    /**
     * Tämä ajaa tiedostoista luettavat vakiotestit, ja tallentaa tulokset
     * tiedostoon vakiotestit.txt
     *
     * @throws IOException
     */
    public void ajaVakiotestit() throws IOException {
        List<Testisetti> setit = generoiTestisetitTiedostoista();
        String tulokset = "nimi\ttäyttösuhde\taika\tlaatikoiden määrä\n";

        for (int i = 0; i < setit.size(); i++) {
            float suhde;
            long aikaAlussa = System.currentTimeMillis();
            PakkausSuunnitelma pakkis = this.pakkaaja.pakkaaKontti(setit.get(i).getKontti(), setit.get(i).getLaatikot());
            long aikaLopussa = System.currentTimeMillis();

            suhde = (float) pakkis.getTilavuus() * 100 / setit.get(i).getKontti().getTilavuus();
            tulokset += setit.get(i).getNimi() + "\t" + suhde + "\t" + (aikaLopussa - aikaAlussa) + "\t" + pakkis.getLaatikoita() + "\n";

        }
        FileHandler.kirjoitaString(new File("testitulokset/vakiotestit.txt"), tulokset);

    }

    /**
     * Tämä ajaa useita satunnaisesti generoitavia testitapauksia, joista
     * laskettavat keskiarvot tallennetaan tiedostoon satunnaistestit.txt
     *
     * @param iteraatioidenMaara Kuinka monta iteraatiota settiä kohden tehdään.
     * Tämä kannattaa pitää melko alhaisena lukuna.
     * @throws IOException
     */
    public void ajaSatunnaistestit(int iteraatioidenMaara) throws IOException {
        String tulokset = "nimi\ttäyttösuhde\taika\tlaatikoiden määrä\n";

        for (int i = 0; i < this.setinOminaisuudet.length; i++) {
            float aika = 0;
            float suhde = 0;
            long laatikoita = 0;
            for (int j = 0; j < iteraatioidenMaara; j++) {
                List<Laatikkotyyppi> laatikot = generoiLaatikoitaTesteihin(this.setinOminaisuudet[i]);
                Testisetti setti = new Testisetti(this.setinOminaisuudet[i].getNimi(), this.kontti, laatikot);
                long aikaAlussa = System.currentTimeMillis();
                PakkausSuunnitelma pakkis = this.pakkaaja.pakkaaKontti(setti.getKontti(), setti.getLaatikot());
                long aikaLopussa = System.currentTimeMillis();
                aika += (aikaLopussa - aikaAlussa);
                suhde += (float) pakkis.getTilavuus() * 100 / setti.getKontti().getTilavuus();
                laatikoita += pakkis.getLaatikoita();

            }
            tulokset += this.setinOminaisuudet[i].getNimi() + "\t" + suhde / iteraatioidenMaara + "\t" + aika / iteraatioidenMaara + "\t" + laatikoita / iteraatioidenMaara + "\n";
        }
        FileHandler.kirjoitaString(new File("testitulokset/satunnaistestit.txt"), tulokset);

    }

    /**
     * Tämä lukee tiedostoja, ja lukee niistä listan testisettejä
     *
     * @return Lista, joka sisältää luetut testisetit
     * @throws IOException
     */
    public List<Testisetti> generoiTestisetitTiedostoista() throws IOException {
        List<Testisetti> setit = new List<Testisetti>();
        setit.add(new Testisetti("homo1", this.kontti, FileHandler.lueLaatikot(new File("listat/homo1"))));
        setit.add(new Testisetti("homo2", this.kontti, FileHandler.lueLaatikot(new File("listat/homo2"))));
        setit.add(new Testisetti("homo3", this.kontti, FileHandler.lueLaatikot(new File("listat/homo3"))));
        setit.add(new Testisetti("homo4", this.kontti, FileHandler.lueLaatikot(new File("listat/homo4"))));
        setit.add(new Testisetti("homo5", this.kontti, FileHandler.lueLaatikot(new File("listat/homo5"))));
        setit.add(new Testisetti("homo6", this.kontti, FileHandler.lueLaatikot(new File("listat/homo6"))));
        setit.add(new Testisetti("homo7", this.kontti, FileHandler.lueLaatikot(new File("listat/homo7"))));
        setit.add(new Testisetti("homo8", this.kontti, FileHandler.lueLaatikot(new File("listat/homo8"))));
        setit.add(new Testisetti("homo9", this.kontti, FileHandler.lueLaatikot(new File("listat/homo9"))));
        setit.add(new Testisetti("homo10", this.kontti, FileHandler.lueLaatikot(new File("listat/homo10"))));
        setit.add(new Testisetti("koko1", this.kontti, FileHandler.lueLaatikot(new File("listat/koko1"))));
        setit.add(new Testisetti("koko2", this.kontti, FileHandler.lueLaatikot(new File("listat/koko2"))));
        setit.add(new Testisetti("koko3", this.kontti, FileHandler.lueLaatikot(new File("listat/koko3"))));
        setit.add(new Testisetti("koko4", this.kontti, FileHandler.lueLaatikot(new File("listat/koko4"))));
        setit.add(new Testisetti("koko5", this.kontti, FileHandler.lueLaatikot(new File("listat/koko5"))));
        setit.add(new Testisetti("koko6", this.kontti, FileHandler.lueLaatikot(new File("listat/koko6"))));
        setit.add(new Testisetti("koko7", this.kontti, FileHandler.lueLaatikot(new File("listat/koko7"))));
        setit.add(new Testisetti("patukat", this.kontti, FileHandler.lueLaatikot(new File("listat/patukat"))));
        return setit;
    }

    /**
     * Tämä generoi testisettejä ja tallentaa ne tiedostoihin
     *
     * @throws FileNotFoundException
     */
    public void generoiTestitiedostot() throws FileNotFoundException {
        FileHandler.kirjoitaLaatikot(new File("listat/homo1"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[0]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo2"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[1]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo3"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[2]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo4"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[3]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo5"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[4]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo6"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[5]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo7"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[6]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo8"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[7]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo9"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[8]));
        FileHandler.kirjoitaLaatikot(new File("listat/homo10"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[9]));
        FileHandler.kirjoitaLaatikot(new File("listat/koko1"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[10]));
        FileHandler.kirjoitaLaatikot(new File("listat/koko2"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[11]));
        FileHandler.kirjoitaLaatikot(new File("listat/koko3"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[12]));
        FileHandler.kirjoitaLaatikot(new File("listat/koko4"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[13]));
        FileHandler.kirjoitaLaatikot(new File("listat/koko5"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[14]));
        FileHandler.kirjoitaLaatikot(new File("listat/koko6"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[15]));
        FileHandler.kirjoitaLaatikot(new File("listat/koko7"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[16]));
        FileHandler.kirjoitaLaatikot(new File("listat/patukat"), generoiLaatikoitaTesteihin(this.setinOminaisuudet[17]));

    }

    /**
     * Tämä generoi testejä varten laatikoita
     *
     * @param ominaisuudet
     * @return Lista laatikoista (Laatikkotyyppejä)
     */
    public List<Laatikkotyyppi> generoiLaatikoitaTesteihin(SetinOminaisuudet ominaisuudet) {
        Random random = new Random();
        List<Laatikkotyyppi> laatikot = new List<Laatikkotyyppi>();
//        this.io.sout("Generoidaan laatikoita tiedostoon:");
        long tyyppienTilavuus = 0;
        for (int i = 0; i < ominaisuudet.getTyyppienMaara(); i++) {
            Laatikkotyyppi tyyppi = new Laatikkotyyppi(random.nextInt(ominaisuudet.getMaxX() - ominaisuudet.getMinX()) + ominaisuudet.getMinX(), random.nextInt(ominaisuudet.getMaxY() - ominaisuudet.getMinY()) + ominaisuudet.getMinY(), random.nextInt(ominaisuudet.getMaxZ() - ominaisuudet.getMinZ()) + ominaisuudet.getMinZ());
            laatikot.add(tyyppi);
            tyyppienTilavuus += tyyppi.getTilavuus();
        }

        long n = ominaisuudet.getHaluttuTilavuus() / tyyppienTilavuus;
        for (int i = 0; i < ominaisuudet.getTyyppienMaara(); i++) {
            laatikot.get(i).setLaatikoita((int) n);
        }
        return laatikot;
    }
}
