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
     *
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

        for (int i = 0; i < puut.length; i++) {
            if (puut[i] != null) {
                long t = 0;
                for (int j : taulu) {
                    t += lisaaAika(puut[i], j);
                }
                if (i == 0) {
                    System.out.println("BIN: " + t + "ms");
                }
                if (i == 1) {
                    System.out.println("RB: " + t + "ms");
                }
                if (i == 2) {
                    System.out.println("SP: " + t + "ms");
                }
                if (i == 3) {
                    System.out.println("AVL: " + t + "ms");
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
                    t += (varia == 0) ? hakuAika(puut[i], koko)
                            : hakuAika(puut[i], varia);
                }
                if (i == 0) {
                    s += "BIN: " + t + " ms\n";
                }
                if (i == 1) {
                    s += "RB: " + t + " ms\n";
                }
                if (i == 2) {
                    s += "SP: " + t + " ms\n";
                }
                if (i == 3) {
                    s += "AVL: " + t + " ms\n";
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
                        t += poistoAika(puut[i], koko);
                    } else {
                        t += poistoAika(puut[i], varia);
                    }
                }
                if (i == 0) {
                    s += "BIN: " + t + " ms\n";
                }
                if (i == 1) {
                    s += "RB: " + t + " ms\n";
                }
                if (i == 2) {
                    s += "SP: " + t + " ms\n";
                }
                if (i == 3) {
                    s += "AVL: " + t + " ms\n";
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
                        t += lisaaAika(puut[i], koko);
                    } else {
                        t += lisaaAika(puut[i], varia);
                    }
                }
                if (i == 0) {
                    s += "BIN: " + t + " ms\n";
                }
                if (i == 1) {
                    s += "RB: " + t + " ms\n";
                }
                if (i == 2) {
                    s += "SP: " + t + " ms\n";
                }
                if (i == 3) {
                    s += "AVL: " + t + " ms\n";
                }

            }
        }
        return s;
    }

    private long hakuAika(PuuRajapinta puu, int n) {
        int i = getRandLuku(n);
        long start = System.currentTimeMillis();
        puu.hae(i);
        return System.currentTimeMillis() - start;
    }

    private long poistoAika(PuuRajapinta puu, int n) {
        int i = getRandLuku(n);
        long start = System.currentTimeMillis();
        puu.poistaSolmu(puu.hae(i));
        return System.currentTimeMillis() - start;
    }

    private long lisaaAika(PuuRajapinta puu, int n) {
        Solmu uusi = new Solmu(n);
        long start = System.currentTimeMillis();
        puu.lisaaSolmu(uusi);
        return System.currentTimeMillis() - start;
    }
}