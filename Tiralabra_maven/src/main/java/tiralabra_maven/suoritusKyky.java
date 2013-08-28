package tiralabra_maven;

import java.util.Random;
import java.util.Scanner;

/**
 * Luokka testaa puita käyttäjän syötteilläs
 *
 * @author esaaksvu
 */
public class suoritusKyky {

    int varia = 0;
    int koko = 0;

    /**
     * Luo käyttäjän valitsemat puut
     *
     * @return arrayn puista
     */
    public PuuRajapinta[] luoPuut() {
        String puu = kysyString("Mitä testaan, AVL, RB, SP, BIN? (Voi sisältää useamman)");
        koko = kysyInt("Kuinka monta luku lisätään puuhu?");
        boolean jarjest = kysyBoolean("Järjestetty taulu? (true/false)");
        if (!jarjest) {
            varia = kysyInt("Millä välillä arvotut luvut ovat?");
        }
        try {
            System.out.println("Alustetaan puita....");
            return (jarjest) ? alustaPuut(puu, luoJarjTaulu(koko))
                    : alustaPuut(puu, luoRandTaulu(koko, varia));
        } catch (Exception e) {
            System.out.println("VIRHE! Luotaessa puuta/puita (" + e + ")");
            return luoPuut();
        }
    }

    /**
     * Tekee puille operaatioita ja palauttaa tulostuksen ajasta
     * @param puut puut joille operaatiot tehdään
     * @return tulostus ajasta
     */
    public String operoiPuita(PuuRajapinta[] puut) {
        String t = kysyString("Mitä puille tehdään? (Hae,Poista,Lisää,Nollaa)").toLowerCase();
        if (t.equals("hae")) {
            return haePuista(puut);
        }
        if (t.equals("poista")) {
            return poistaPuista(puut);
        }
        if (t.equals("lisää")) {
            return lisaaPuihin(puut);
        }
        if (t.equals("nollaa")) {
            return "nollaa";
        }
        return "";

    }

    /**
     * luo arvotun taulukon lukuja
     *
     * @param koko on lukujen määrä
     * @param variaatio on lukujen etäisyys toisistaan
     * @return int taulu
     */
    public int[] luoRandTaulu(int koko, int variaatio) {
        if (koko <= 0) {
            return null;
        }
        try {
            int[] numerot = new int[koko];
            for (int i = 0; i < koko; i++) {
                numerot[i] = getRandLuku(variaatio);
            }
            return numerot;
        } catch (Exception e) {
            return null;
        }
    }

    /**
     * luo järjestetyn taulun 0,1,2,3,..,koko
     *
     * @param koko on taulun viimeinen alkio nollasta alkaen
     * @return int taulukko
     */
    public int[] luoJarjTaulu(int koko) {
        if (koko <= 0) {
            return null;
        }
        int[] numerot = new int[koko];
        for (int i = 0; i < koko; i++) {
            numerot[i] = i;
        }
        return numerot;
    }

    /**
     * palauttaa random luvun väliltä (0-variaatio)
     *
     * @param variaatio raja luvulle
     * @return arvottu integer
     */
    public int getRandLuku(int variaatio) {
        Random r = new Random();
        return r.nextInt(variaatio);
    }

    /**
     * Kysyy käyttäjän syöttämää stringiä
     *
     * @param s käyttäjän kysymys
     * @return käyttäjän syöttämä string
     */
    public String kysyString(String s) {
        System.out.println(s);
        try {
            Scanner sc = new Scanner(System.in);
            return sc.nextLine();
        } catch (Exception e) {
            return kysyString(s);
        }
    }

    /**
     * Kysyy käyttäjältä integeriä
     *
     * @param s käyttäjän kysymys
     * @return käyttäjän syöttämä integer
     */
    public int kysyInt(String s) {
        System.out.println(s);
        try {
            Scanner sc = new Scanner(System.in);
            return sc.nextInt();
        } catch (Exception e) {
            return kysyInt(s);
        }
    }

    /**
     * Kysyy käyttäjältä booleania
     *
     * @param s käyttäjän kysymys
     * @return käyttäjän vastaus
     */
    public boolean kysyBoolean(String s) {
        System.out.println(s);
        try {
            Scanner sc = new Scanner(System.in);
            return sc.nextBoolean();
        } catch (Exception e) {
            return kysyBoolean(s);
        }
    }

    private PuuRajapinta[] alustaPuut(String puuLista, int[] taulu) {
        PuuRajapinta[] puut = new PuuRajapinta[4];
        if (puuLista.toUpperCase().contains("BIN")) {
            puut[0] = new BinaariHakupuu();
        }
        if (puuLista.toUpperCase().contains("RB")) {
            puut[1] = new PunMusPuu();
        }
        if (puuLista.toUpperCase().contains("SP")) {
            puut[2] = new SplayPuu();
        }
        if (puuLista.toUpperCase().contains("AVL")) {
            puut[3] = new AVLHakuPuu();
        }

        for (PuuRajapinta puu : puut) {
            if (puu != null) {
                for (int i : taulu) {
                    puu.lisaaSolmu(new Solmu(i));
                }
            }
        }
        return puut;
    }

    private String haePuista(PuuRajapinta[] puut) {
        String s = "";
        int kerrat = kysyInt("Montako kertaa haetaan?");
        for (int i = 0; i < puut.length; i++) {
            if (puut[i] != null) {
                long t = 0;
                for (int j = 0; j < kerrat; j++) {
                    t += (varia == 0) ? hakuAika(puut[0], koko)
                            : hakuAika(puut[0], varia);
                }
                if (i == 0) {
                    s += "BIN: " + t / kerrat + " ns\n";
                }
                if (i == 1) {
                    s += "RB: " + t / kerrat + " ns\n";
                }
                if (i == 2) {
                    s += "SP: " + t / kerrat + " ns\n";
                }
                if (i == 3) {
                    s += "AVL: " + t / kerrat + " ns\n";
                }
            }
        }
        return s;
    }

    private String poistaPuista(PuuRajapinta[] puut) {
        String s = "";
        int kerrat = kysyInt("Montako kertaa poistetaan?");
        for (int i = 0; i < puut.length; i++) {
            if (puut[i] != null) {
                long t = 0;
                for (int j = 0; j < kerrat; j++) {
                    if (varia == 0) {
                        t += poistoAika(puut[0], koko);
                    } else {
                        t += poistoAika(puut[0], varia);
                    }
                }
                if (i == 0) {
                    s += "BIN: " + t / kerrat + " ns\n";
                }
                if (i == 1) {
                    s += "RB: " + t / kerrat + " ns\n";
                }
                if (i == 2) {
                    s += "SP: " + t / kerrat + " ns\n";
                }
                if (i == 3) {
                    s += "AVL: " + t / kerrat + " ns\n";
                }

            }
        }
        return s;
    }

    private String lisaaPuihin(PuuRajapinta[] puut) {
        String s = "";
        int kerrat = kysyInt("Montako kertaa lisätään?");
        for (int i = 0; i < puut.length; i++) {
            if (puut[i] != null) {
                long t = 0;
                for (int j = 0; j < kerrat; j++) {
                    if (varia == 0) {
                        t += lisaaAika(puut[0], koko);
                    } else {
                        t += lisaaAika(puut[0], varia);
                    }
                }
                if (i == 0) {
                    s += "BIN: " + t / kerrat + " ns\n";
                }
                if (i == 1) {
                    s += "RB: " + t / kerrat + " ns\n";
                }
                if (i == 2) {
                    s += "SP: " + t / kerrat + " ns\n";
                }
                if (i == 3) {
                    s += "AVL: " + t / kerrat + " ns\n";
                }

            }
        }
        return s;
    }

    private long hakuAika(PuuRajapinta puu, int n) {
        int i = getRandLuku(n);
        long start = System.nanoTime();
        puu.hae(i);
        return System.nanoTime() - start;
    }

    private long poistoAika(PuuRajapinta puu, int n) {
        Solmu pois = puu.hae(getRandLuku(n));
        long start = System.nanoTime();
        puu.poistaSolmu(pois);
        return System.nanoTime() - start;
    }

    private long lisaaAika(PuuRajapinta puu, int n) {
        Solmu uusi = new Solmu(getRandLuku(n));
        long start = System.nanoTime();
        puu.lisaaSolmu(uusi);
        return System.nanoTime() - start;
    }
}