package com.mycompany.tiralabra_maven;

import haku.AStar;
import haku.Laskin;
import haku.ReittiLaskin;
import haku.SatunnainenLaskin;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import tira.DynaaminenLista;
import tira.Hajautustaulu;
import tira.Lista;
import verkko.Pysakki;
import verkko.Reitti;
import verkko.Verkko;
import verkko.rajapinnat.Graph;
import verkko.rajapinnat.Node;
import verkko.rajapinnat.Value;
import verkko.satunnainen.Polku;
import verkko.satunnainen.SatunnainenVerkko;
import verkko.satunnainen.V;

/**
 *
 * @author E
 */
public class Gui extends JFrame {

    /**
     * Heuristiikan kÃ¤yttÃ¤mÃ¤ tieto keskinopeudesta. Jos arvo on liian pieni,
     * heuristiikka yliarvioi matkojen aikakustannuksia. Jos arvo liian suuri,
     * aliarvioidaan & joudutaan laskemaan ylimÃ¤Ã¤rÃ¤isiÃ¤ reittejÃ¤
     */
    public static double sporanNopeus = 526; // pistettÃ¤/min
    /**
     * Leveyssuuntainen haku tÃ¤llÃ¤ laskimella: heuristiikan arvo aina nolla
     */
    public static ReittiLaskin bfs = new ReittiLaskin(1, 0, 0, 0, 0, sporanNopeus);
    /**
     * Leveyssuuntainen haku, vaihdoton
     */
    public static ReittiLaskin bfsVaihdoton = new ReittiLaskin(1, 0, 3, 0, 0, sporanNopeus);
    /**
     * Matka-aikaa minimoiva laskin
     */
    public static ReittiLaskin normaali = new ReittiLaskin(1, 0, 0, 1, 0, sporanNopeus);
    /**
     * Vaihtoja vÃ¤lttelevÃ¤ laskin
     */
    public static ReittiLaskin vaihdoton = new ReittiLaskin(1, 0, 3, 1, 0, sporanNopeus);
    /**
     * Matkaa ja matka-aikaa minimoiva laskin
     */
    public static ReittiLaskin normaaliMatkaaMinimoiva = new ReittiLaskin(1, 0.001, 0, 1, 0, sporanNopeus);
    /**
     * Matkaa ja matka-aikaa minimoiva laskin
     */
    public static ReittiLaskin vaihdotonMatkaaMinimoiva = new ReittiLaskin(1, 0.001, 3, 1, 0, sporanNopeus);

    /**
     * Satunnaisen verkon BFS
     */
    public static SatunnainenLaskin satunnainenBFS = new SatunnainenLaskin(0);
    /**
     * Satunnaisen verkon heuristinen laskin
     */
    public static SatunnainenLaskin satunnainen = new SatunnainenLaskin(1);
    /**
     * Verkon graafinen esitys
     */
    private Piirto piirto;

    private Graph verkko;
    private AStar aStar;
    private Laskin laskin;
    private Node reitti;
    private Value alku, loppu;

    /**
     *
     */
    private boolean valittuPysakki, valittuSatunnainen;
    /**
     * Menukomponentit
     */
    final JRadioButton satunnainenVerkko = new JRadioButton("Satunnainen verkko");
    final JCheckBoxMenuItem l1 = new JCheckBoxMenuItem("BFS");
    final JCheckBoxMenuItem l2 = new JCheckBoxMenuItem("Heuristinen");
    final ButtonGroup satunnainenLaskin = new ButtonGroup();
    final JRadioButton pysakkiVerkko = new JRadioButton("PysÃ¤kkiverkko");
    final JCheckBoxMenuItem p1 = new JCheckBoxMenuItem("BFS");
    final JCheckBoxMenuItem p2 = new JCheckBoxMenuItem("Vaihdoton BFS");
    final JCheckBoxMenuItem p3 = new JCheckBoxMenuItem("Normaali");
    final JCheckBoxMenuItem p4 = new JCheckBoxMenuItem("Vaihdoton");
    final JCheckBoxMenuItem p5 = new JCheckBoxMenuItem("Normaali matkaa minimoiva");
    final JCheckBoxMenuItem p6 = new JCheckBoxMenuItem("Vaihdoton matkaa minimoiva");
    final ButtonGroup reittiLaskin = new ButtonGroup();
    final JMenuItem testaus = new JMenuItem("TestejÃ¤");

    public Gui() {

        dev(); // oletusarvot

        piirto = new Piirto();
        this.setSize(1000, 600);

        this.setJMenuBar(teeMenu());
        JScrollPane jsp = new JScrollPane(piirto);
        piirto.setAutoscrolls(true);
        jsp.setPreferredSize(piirto.getPreferredSize());
        // jsp.add(piirto);
        this.add(jsp);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setTitle("AStar");

        piirto.addMouseListener(piirto);

        // this.addMouseListener(piirto);
        this.setVisible(true);
    }

    /**
     * Testiprofiilin asetukset
     */
    private void dev() {
        SatunnainenVerkko satunnainenVerkko = new SatunnainenVerkko(20, 40, 1, 2, 2);
        SatunnainenLaskin satunnainenLaskin = new SatunnainenLaskin(1);
        valittuPysakki = false;
        valittuSatunnainen = true;
        verkko = satunnainenVerkko; //
        laskin = satunnainenLaskin;
        aStar = new AStar(verkko, laskin);
        // alku = satunnainenVerkko.getSolmu(0, 0);
        // loppu = satunnainenVerkko.getSolmu(7, 6);
        // reitti = aStar.etsiReittiOma(alku, loppu);
        this.satunnainenVerkko.setSelected(true);
        l2.setSelected(true);
    }

    /**
     * Luo kÃ¤yttÃ¶liittymÃ¤n valikon
     *
     * @return
     */
    private JMenuBar teeMenu() {
        JMenuBar menubar = new JMenuBar();
        JMenu menu = new JMenu("Haku");

        menu.add(satunnainenVerkko);

        menu.add(l1);
        menu.add(l2);
        satunnainenLaskin.add(l1);
        satunnainenLaskin.add(l2);

        menu.add(new JSeparator());

        menu.add(pysakkiVerkko);
        menu.add(p1);
        menu.add(p2);
        menu.add(p3);
        menu.add(p4);
        menu.add(p5);
        menu.add(p6);

        reittiLaskin.add(p1);
        reittiLaskin.add(p2);
        reittiLaskin.add(p1);
        reittiLaskin.add(p2);
        reittiLaskin.add(p1);
        reittiLaskin.add(p2);

        menu.add(new JSeparator());

        menu.add(testaus);

        menubar.add(menu);

        // tapahtumakuuntelijat
        satunnainenVerkko.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                pysakkiVerkko.setSelected(false);
                reittiLaskin.clearSelection();
                valittuPysakki = false;
                valittuSatunnainen = true;
                uusiSatunnainenVerkko();
            }
        });
        pysakkiVerkko.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                satunnainenVerkko.setSelected(false);
                satunnainenLaskin.clearSelection();
                valittuPysakki = true;
                valittuSatunnainen = false;
                p3.setSelected(true);

                setVerkko(new Verkko());
                setLaskin(normaali);
            }
        });
        l1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valittuSatunnainen) {
                    setLaskin(satunnainenBFS);
                }
            }
        });
        l2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valittuSatunnainen) {
                    setLaskin(satunnainen);
                }
            }
        });

        p1.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                reittiLaskin.clearSelection();
                if (valittuPysakki) {
                    setLaskin(bfs);
                }
            }
        });
        p2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valittuPysakki) {
                    setLaskin(bfsVaihdoton);
                }
            }
        });
        p3.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valittuPysakki) {
                    setLaskin(normaali);
                }
            }
        });
        p4.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valittuPysakki) {
                    setLaskin(vaihdoton);
                }
            }
        });
        p5.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valittuPysakki) {
                    setLaskin(normaaliMatkaaMinimoiva);
                }
            }
        });
        p6.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (valittuPysakki) {
                    setLaskin(vaihdotonMatkaaMinimoiva);
                }
            }
        });

        testaus.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                laskeToimintaa();
            }

        });
        menubar.setVisible(true);
        return menubar;
    }

    /**
     * Luo syÃ¶tteen pohjalta uuden satunnaisen verkon
     */
    private void uusiSatunnainenVerkko() {
        String options = JOptionPane.showInputDialog("rivit:sarakkeet:minimipaino:kerroinpaino:tyyppi:liikkuminen");
        String[] opt = {};
        try {
            opt = options.split(":");
        } catch (Exception ex) {
        }

        int r = 300, s = 300, moodi = 1;
        double minimi = 1, kerroin = 2;
        boolean liikkuminen = true;

        try {
            r = Integer.parseInt(opt[0]);
        } catch (Exception ex) {
        }
        try {
            s = Integer.parseInt(opt[1]);
        } catch (Exception ex) {
        }
        try {
            minimi = Double.parseDouble(opt[2]);
        } catch (Exception ex) {
        }
        try {
            kerroin = Double.parseDouble(opt[3]);
        } catch (Exception ex) {
        }
        try {
            moodi = Integer.parseInt(opt[4]);
        } catch (Exception ex) {
        }
        try {
            int valinta = Integer.parseInt(opt[5]);
            if (valinta == 1) {
                liikkuminen = true;
            } else {
                liikkuminen = false;
            }
        } catch (Exception ex) {
        }

        try {
            SatunnainenVerkko v = new SatunnainenVerkko(r, s, minimi, kerroin, moodi, liikkuminen);
            l2.setSelected(true);
            setLaskin(new SatunnainenLaskin());
            setVerkko(v);

        } catch (Exception ex) {
        }
    }

    /**
     * Etsii reitin alku- ja loppusolmujen vÃ¤lille
     */
    private void etsiReitti() {
        if (alku != null && loppu != null) {
            reitti = aStar.etsiReittiOma(alku, loppu);
            piirto.repaint();
            System.out.println("" + reitti);
        }
    }

    /**
     * Vaihdetaan laskinta
     */
    private void setLaskin(Laskin laskin) {
        this.laskin = laskin;
        this.aStar = new AStar(verkko, laskin);
    }

    /**
     * Vaihdetaan verkkoa
     */
    private void setVerkko(Graph verkko) {
        this.verkko = verkko;
        this.reitti = null;
        this.alku = null;
        this.loppu = null;
        this.aStar = new AStar(verkko, laskin);
    }

    /**
     * Tekee Tulokset-paneelista uuden ikkunan
     */
    private void laskeToimintaa() {

        String options = JOptionPane.showInputDialog("otosKoko:testienLkm");
        String[] opt = {};
        int otosKoko = 20, testienLkm = 10;
        try {
            opt = options.split(":");
        } catch (Exception ex) {
        }
        try {
            otosKoko = Integer.parseInt(opt[0]);
        } catch (Exception ex) {
        }
        try {
            testienLkm = Integer.parseInt(opt[1]);
        } catch (Exception ex) {
        }
        JFrame jframe = new JFrame();

        jframe.setTitle("Reitinhaun tehokkuus");
        Tulokset tulokset = new Tulokset(otosKoko, testienLkm);
        jframe.add(tulokset);
        jframe.setSize(tulokset.getPreferredSize());
        tulokset.testaa();
        jframe.setVisible(true);

    }

    /**
     * Satunnaisen verkon visualisointi
     */
    private class Piirto extends JPanel implements MouseListener {

        /**
         * Satunnaisen verkon piirtÃ¤minen
         */
        private int blockW = 18, blockH = 11;

        /**
         * PysÃ¤kkiverkon piirtÃ¤minen
         */
        private int pW = 10, pH = 10, modX = 20, modY = 15, skaala = 8, isoLuku = 2000;
        /**
         * PysÃ¤kkiverkon koordinaatit-pysÃ¤kit
         */
        private Hajautustaulu<Integer, Pysakki> hitboksit; // = new Hajautustaulu(140);
        /**
         * PysÃ¤kkiverkon pysÃ¤kit-koordinaatit
         */
        private Hajautustaulu<Pysakki, Integer> drawboksit; // = new Hajautustaulu(140);

        @Override
        public void paint(Graphics g) {
            super.paint(g);
            // int blocksize=10;
            // g.setFont(null);
            if (verkko != null) {
                if (valittuSatunnainen) {
                    piirraSatunnainen(g);
                    hitboksit = null;
                    drawboksit = null;
                } else if (valittuPysakki) {
                    this.setPreferredSize(new Dimension(800, 800));
                    this.setMaximumSize(new Dimension(800, 800));
                    alustaPysakkiverkko();
                    piirraPysakkiverkko(g);
                }
            }

            // g.fillRect(33, 66, 555, 333);
        }

        /**
         * Esitys satunnaiselle verkolle
         *
         * @param g
         */
        public void piirraSatunnainen(Graphics g) {
            SatunnainenVerkko v = (SatunnainenVerkko) verkko;
            int rivit = v.getRivit();
            int sarakkeet = v.getSarakkeet();
            this.setPreferredSize(new Dimension(sarakkeet * blockW, rivit * blockH));
            this.setMaximumSize(new Dimension(sarakkeet * blockW, rivit * blockH));

            double[][] painot = v.getPainot();

            // piirretÃ¤Ã¤n solmut, joissa on kÃ¤yty
            g.setColor(Color.PINK);
            if (aStar.getKaydytSolmut() != null) {
                for (Value value : aStar.getKaydytSolmut()) {
                    V solmu = (V) value;
                    g.fillRect(blockW * solmu.getY(), blockH * (solmu.getX()), blockW, blockH);
                }
            }
            // piirretÃ¤Ã¤n reitti
            g.setColor(Color.red);
            if (reitti != null) {
                Polku polku = (Polku) reitti;
                Lista<Value> kuljettuReitti = polku.solmut();
                for (Value value : kuljettuReitti) {
                    V solmu = (V) value;

                    g.fillRect(blockW * solmu.getY(), blockH * (solmu.getX()), blockW, blockH);

                }
            }
            // piirretÃ¤Ã¤n alkusolmu
            g.setColor(Color.YELLOW);
            if (alku != null) {
                V solmu = (V) alku;
                g.fillRect(blockW * solmu.getY(), blockH * (solmu.getX()), blockW, blockH);
            }
            // piirretÃ¤Ã¤n loppusolmu
            g.setColor(Color.GREEN);
            if (loppu != null) {
                V solmu = (V) loppu;
                g.fillRect(blockW * solmu.getY(), blockH * (solmu.getX()), blockW, blockH);
            }
            // piirretÃ¤Ã¤n verkon painot
            g.setColor(Color.BLACK);
            for (int i = 0; i < rivit; i++) {
                for (int j = 0; j < sarakkeet; j++) {
                    String merkki = "" + painot[i][j];
                    if (merkki.length() > 3) {
                        merkki = merkki.substring(0, 3);
                    }
                    g.drawString("" + merkki, blockW * j, blockH * (i + 1));
                }
            }
            // piirretÃ¤Ã¤n gridi
            for (int i = 0; i <= rivit; i++) {
                g.drawLine(0, i * blockH, sarakkeet * blockW, i * blockH);
            }

            for (int i = 0; i <= sarakkeet; i++) {

                g.drawLine(i * blockW, 0, i * blockW, rivit * blockH);
            }
        }

        /**
         * Esitys pysÃ¤kkiverkolle
         *
         * @param g
         */
        public void piirraPysakkiverkko(Graphics g) {

            BufferedImage img = null;
            // huhhuh, huh
            // kartta
            try {
                img = ImageIO.read(new File("kartta.jpg"));
            } catch (Exception ex) {
            }

            g.drawImage(img, pH, pH, rootPane);

            g.setColor(Color.red);
            if (drawboksit != null) {
                for (Pysakki p : drawboksit.keySet()) {
                    Color k;
                    if (p == alku) {
                        k = Color.YELLOW;
                    } else if (p == loppu) {
                        k = Color.GREEN;
                    } else {
                        k = Color.RED;
                    }
                    int avain = drawboksit.get(p);
                    g.setColor(k);
                    g.drawRect(avain % isoLuku, avain / isoLuku, pW, pH);
                }
            }

            if (aStar != null && aStar.getKaydytSolmut() != null) {
                for (Value v : aStar.getKaydytSolmut()) {
                    Pysakki p = (Pysakki) v;
                    int avain = drawboksit.get(p);
                    g.setColor(Color.PINK);
                    g.fillRect(avain % isoLuku, avain / isoLuku, pW, pH);
                }
            }
            if (reitti != null) {
                Reitti r = (Reitti) reitti;
                Pysakki prev = null;
                for (Value v : r.solmut()) {
                    Pysakki p = (Pysakki) v;
                    int avain = drawboksit.get(p);
                    g.setColor(Color.RED);
                    g.fillRect(avain % isoLuku, avain / isoLuku, pW, pH);
                    if (prev != null) {
                        int edellinen = drawboksit.get(prev);
                        g.drawLine(avain % isoLuku, avain / isoLuku, edellinen % isoLuku, edellinen / isoLuku);
                    }
                    prev = p;
                }
            }

        }

        /**
         * Muuttaa pysÃ¤kin koordinaatit (ruudun x,y) sellaisiksi, ettÃ¤
         * yksilÃ¶ivÃ¤t pysÃ¤kin
         *
         * @param x
         * @param y
         * @return
         */
        private int avain(int x, int y) {
            x /= pW;
            y /= pH;
            x *= pW;
            y *= pH;
            int avain = y * isoLuku + x;
            return avain;
        }

        /**
         * Etsii ruutukoordinaattien perusteella pysÃ¤kin
         *
         * @param x
         * @param y
         * @return
         */
        private Pysakki haePysakki(int x, int y) {
            Pysakki p = null;

            p = hitboksit.get(avain(x, y));

            return p;
        }

        /**
         * Alustetaan pysÃ¤kkiverkko: luodaan jotakuinkin ruudulle ja karttaan
         * sopivat koordinaatit, tÃ¤ytetÃ¤Ã¤n hitboksit-taulu
         */
        private void alustaPysakkiverkko() {
            if (hitboksit == null) {
                hitboksit = new Hajautustaulu(140);
            } else {
                return;
            }
            if (drawboksit == null) {
                drawboksit = new Hajautustaulu(140);
            } else {
                return;
            }
            Verkko v = (Verkko) verkko;

            Pysakki[] pysakit = v.getPysakit();
            // normalisoidaan nÃ¤mÃ¤
            int minX = Integer.MAX_VALUE, minY = Integer.MAX_VALUE, maxX = Integer.MIN_VALUE, maxY = Integer.MIN_VALUE;
            for (Pysakki p : pysakit) {
                int x = p.getX(), y = p.getY();
                // System.out.println(""+p.getX()+" "+p.getY());
                if (x < minX) {
                    minX = x;
                }
                if (x > maxX) {
                    maxX = x;
                }
                if (y < minY) {
                    minY = y;
                }
                if (y > maxY) {
                    maxY = y;
                }
            }
            for (Pysakki p : pysakit) {
                int x = p.getX() - minX, y = maxY - p.getY();//-minY;
                x /= skaala;
                x += modX;
                y /= skaala;
                y += modY;

                hitboksit.put(avain(x, y), p);
                drawboksit.put(p, avain(x, y));

            }
        }

        /**
         * Hiirikuuntelija. Vaihtaa vasemmalla klikkauksella lÃ¤htÃ¶solmua,
         * oikealla klikkauksella maalisolmua
         *
         * @param e
         */
        public void mouseClicked(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            // System.out.println("bttn: " + e.getButton() + ", x:" + e.getX() + ", y:" + e.getY());
            int sarake = (e.getX() - this.getX()) / blockW; // sarake
            int rivi = (e.getY() - this.getY()) / blockH; // rivi

            if (e.getButton() == 1) { // left click
                if (valittuSatunnainen) {
                    alku = ((SatunnainenVerkko) verkko).getSolmu(rivi, sarake);
                } else if (valittuPysakki) {
                    Value value = this.haePysakki(e.getX(), e.getY());
                    alku = (value != null) ? value : alku;
                }

            } else if (e.getButton() == 3) { // left click
                if (valittuSatunnainen) {
                    loppu = ((SatunnainenVerkko) verkko).getSolmu(rivi, sarake);
                } else if (valittuPysakki) {
                    Value value = this.haePysakki(e.getX(), e.getY());
                    loppu = (value != null) ? value : loppu;
                }
            } else {

                return;
            }

            etsiReitti();

        }

        public void mousePressed(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void mouseReleased(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void mouseEntered(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

        public void mouseExited(MouseEvent e) {
            // throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }

    /**
     * Tulosten visualisointi
     */
    private class Tulokset extends JPanel {

        private int otosKoko, testienLkm;
        private int w, h;
        private int blockW = 3, blockH = 3, pituusBlokki = 3, starttiBlokki = 50, aikaBlokki = 3;

        private Lista<Lista<Long>> tulokset;
        private Lista<Integer> pituudet;

        public Tulokset() {
            this.setPreferredSize(new Dimension(500, 500));
        }

        public Tulokset(int otosKoko, int testienLkm) {
            w = 500;
            h = 900;
            this.setPreferredSize(new Dimension(w, h));
            this.otosKoko = otosKoko;
            this.testienLkm = testienLkm;
        }

        /**
         * Testaa reitinhakua satunnaisesti valituilla alku- ja loppusolmuilla
         */
        public void testaa() {

            AStar aStar = new AStar(verkko, laskin);

            tulokset = new DynaaminenLista();
            pituudet = new DynaaminenLista();
            loop:
            for (int i = 0; i < otosKoko; i++) {
                Lista<Long> testinAjat = new DynaaminenLista();
                Value a = null, b = null;
                if (valittuSatunnainen) {
                    SatunnainenVerkko v = (SatunnainenVerkko) verkko;
                    int r = v.getRivit(), s = v.getSarakkeet();

                    try {
                        a = v.getSolmu((int) (r * Math.random()), (int) (s * Math.random()));
                        b = v.getSolmu((int) (r * Math.random()), (int) (s * Math.random()));
                    } catch (Exception ex) {
                    };
                } else if (valittuPysakki) {
                    Verkko v = (Verkko) verkko;
                    Pysakki[] pysakit = v.getPysakit();
                    int r = pysakit.length;
                    a = pysakit[(int) (r * Math.random())];
                    b = pysakit[(int) (r * Math.random())];
                }
                if (a == b || a == null || b == null) {
                    continue;
                }
                Node polku = null;
                long start, stop, summa = 0;
                for (int j = 0; j < testienLkm; j++) {
                    // ajastus
                    start = System.currentTimeMillis();
                    try {
                        polku = aStar.etsiReittiOma(a, b);
                    } catch (Exception ex) {
                        System.out.println("Error, continue loop");
                        continue loop;
                    }
                    stop = System.currentTimeMillis();
                    summa += ((stop - start));
                }

                if (polku == null) {
                    System.out.println("Error, reitti tyhjÃ¤");
                    continue;
                }
                

                System.out.println("" + polku.size() + "\t" + summa / testienLkm+"\t"+aStar.getKaydytSolmut().size());
                testinAjat.add(summa / testienLkm);
                pituudet.add(polku.size());
                tulokset.add(testinAjat);
            }
            repaint();
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g);

            if (tulokset == null) {
                return;
            }

            for (int i = 0; i < tulokset.size(); i++) {
                g.setColor(Color.BLUE);
                int x = (pituusBlokki) * pituudet.get(i) + starttiBlokki;
                for (int j = 0; j < tulokset.get(i).size(); j++) {
                    double y = h - aikaBlokki * tulokset.get(i).get(j); // keskiajat
                    g.drawRect(x - pituusBlokki, (int) (y) - aikaBlokki, blockW, blockH);
                }
            }
            int screenWidth = (int) this.getParent().getSize().getWidth();

            g.setColor(Color.BLACK);
            g.drawLine(starttiBlokki, h, starttiBlokki, 0);
            g.drawLine(starttiBlokki, h, screenWidth, h);

            g.setColor(Color.GREEN);

            int ms = 10;
            for (int i = ms * aikaBlokki; i < h; i = i + aikaBlokki * ms) {
                g.setColor(Color.GREEN);
                g.drawLine(starttiBlokki, h - i, screenWidth, h - i);
                g.setColor(Color.BLACK);
                g.drawString("" + i / aikaBlokki + " ms", starttiBlokki - 50, h - i);
            }

            int pituus = 10;
            for (int i = starttiBlokki + pituus * pituusBlokki; i < screenWidth; i = i + pituusBlokki * pituus) {
                g.setColor(Color.GREEN);
                g.drawLine(i, 0, i, h);
                g.setColor(Color.BLACK);
                g.drawString("" + (i - starttiBlokki) / pituusBlokki + "", i, h + 15);
            }
        }

    }

}
