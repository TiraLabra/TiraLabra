package blokus.gui;

import blokus.conf.GlobaalitMuuttujat;
import blokus.gui.kuuntelijat.AntautumisKuuntelija;
import blokus.gui.kuuntelijat.LaattojenHiiriKuuntelija;
import blokus.gui.kuuntelijat.LaudanHiiriKuuntelija;
import blokus.gui.kuuntelijat.LaudanNappaimistoKuuntelija;
import blokus.gui.kuuntelijat.OhitaVuoroKuuntelija;
import blokus.logiikka.Blokus;
import blokus.logiikka.Pelaaja;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Label;
import java.awt.image.BufferedImage;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;
import javax.swing.text.DefaultCaret;

/**
 * Nimensä mukaisesti luo käyttöliittymän ja siihen tarvittavat komponentit.
 * Päivittää myös käyttöliittymää tarpeen mukaan.
 * 
 * @author Simo Auvinen
 */
public class Kayttoliittyma extends JFrame {

    private JPanel paaPaneeli;
    private JPanel vasenPaneeli;
    private JPanel oikeaPaneeli;
    //
    private JPanel lautaPaneeli;
    private JLabel lautaLabel;
    private ImageIcon laudanKuva;
    //
    private JPanel laattaPaneeli;
    private JLabel laattaLabel;
    private ImageIcon laattaKuva;
    //
    private JPanel oikeaYlaPaneeli;
    private JLabel miniLaattaLabel1;
    private JLabel miniLaattaLabel2;
    private JLabel miniLaattaLabel3;
    private ImageIcon miniLaattaKuva1;
    private ImageIcon miniLaattaKuva2;
    private ImageIcon miniLaattaKuva3;
    //
    private JPanel oikeaKeskiPaneeli;
    private Label pelaaja1Pisteet;
    private Label pelaaja2Pisteet;
    private Label pelaaja3Pisteet;
    private Label pelaaja4Pisteet;
    //
    private JPanel oikeaAlaPaneeli;
    private JTextArea tekstialue;
    private JButton ohitaVuoroNappi;
    private JButton antauduNappi;
    //
    private Blokus blokus;
    private Lauta lauta;
    private LaattaValitsin laatat;

    /**
     * Aloittaa käyttäliittymän valmistelun
     *
     * @param blokus
     */
    public Kayttoliittyma(Blokus blokus) {
        super("Blokus");
        this.blokus = blokus;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        valmisteleKayttoliittyma();
    }

    /**
     * Valmistelee käyttöliittymän
     *
     *
     */
    private void valmisteleKayttoliittyma() {
        paaPaneeli = new JPanel();
        oikeaYlaPaneeli = new JPanel();
        oikeaKeskiPaneeli = new JPanel();
        oikeaAlaPaneeli = new JPanel();
        oikeaPaneeli = new JPanel();
        vasenPaneeli = new JPanel();
        muodostaLauta();
        muodostaLaattaPaneeli();
        muodostaPienetLaattaKuvat();
        muodostaPisteKentta();
        muodostaTapahtumaKentta();
        muodostaNapit();

        vasenPaneeli.add(lautaPaneeli);
        vasenPaneeli.add(laattaPaneeli);
        vasenPaneeli.setLayout(new BoxLayout(vasenPaneeli, BoxLayout.Y_AXIS));

        oikeaPaneeli.add(oikeaYlaPaneeli);
        oikeaPaneeli.add(Box.createRigidArea(new Dimension(0, 5)));
        oikeaPaneeli.add(oikeaKeskiPaneeli);
        oikeaPaneeli.add(Box.createRigidArea(new Dimension(0, 5)));
        oikeaPaneeli.add(oikeaAlaPaneeli);

        oikeaPaneeli.setLayout(new BoxLayout(oikeaPaneeli, BoxLayout.Y_AXIS));

        paaPaneeli.add(vasenPaneeli);
        paaPaneeli.add(oikeaPaneeli);
        getContentPane().add(paaPaneeli);

        pack();
        setResizable(false);
        setVisible(true);
    }

    /**
     * Päivittää pelilautaa esittävän kuvan
     */
    public void paivitaLauta() {
        laudanKuva.setImage(lauta.muodostaLaudastaKuva());
        lautaLabel.repaint();
    }

    /**
     * Päivittää käytössä olevia laattoja esittävän kuvan
     */
    public void paivitaLaatat() {
        laattaKuva.setImage(laatat.muodostaValitsimestaKuva());
        laattaLabel.repaint();
    }

    /**
     * Vaihtaa vuoroa vaihtamalla esiin seuraavan pelaajan komponentit
     */
    public void vuoroVaihtuu() {
        if (blokus.onkoPeliOhi) {
            peliLoppuu();
        } else {
            laatat.setLaatat(blokus.getVuorossa().getLaatat());
            paivitaPienetValitsimet();
            paivitaPisteTekstit();
            paivitaLaatat();
            paivitaLauta();
            lisaaTeksti("--------------------------------------------");
            lisaaTeksti(" " + blokus.getIDVariTekstina(blokus.getVuorossa().getPelaajantID()) + " pelaaja on vuorossa.");
        }
        
        if (blokus.getVuorossa().getOlenkoAi()) {
            vuoroVaihtuu();
            
            
        }

    }

    /**
     * Lisää tapahtuma ruutuun parametrina annetun tekstin
     *
     * @param teksti
     */
    public void lisaaTeksti(String teksti) {
        String nykyinen = tekstialue.getText();
        if (!teksti.isEmpty()) {
            tekstialue.setText(nykyinen + "\n-" + teksti);
        }

    }

    /**
     * Päivittää pelaajien pisteet
     */
    private void paivitaPisteTekstit() {
        pelaaja1Pisteet.setText("" + blokus.getPelaajanPisteet(1));
        pelaaja2Pisteet.setText("" + blokus.getPelaajanPisteet(2));
        pelaaja3Pisteet.setText("" + blokus.getPelaajanPisteet(3));
        pelaaja4Pisteet.setText("" + blokus.getPelaajanPisteet(4));

    }

    /**
     * Päivittää muitten pelaajien jäljellä olevia laattoja esittävät valitsimet
     */
    private void paivitaPienetValitsimet() {
        Queue<Pelaaja> jono = blokus.getPelaajat();

        if (jono.size() == 3) {
            Pelaaja p1 = jono.poll();
            Pelaaja p2 = jono.poll();
            Pelaaja p3 = jono.poll();
            miniLaattaKuva1.setImage(laatat.muodostaPieniKuvaValitsimesta(p1.getLaatat()));
            miniLaattaKuva2.setImage(laatat.muodostaPieniKuvaValitsimesta(p2.getLaatat()));
            miniLaattaKuva3.setImage(laatat.muodostaPieniKuvaValitsimesta(p3.getLaatat()));
            jono.add(p1);
            jono.add(p2);
            jono.add(p3);
        } else if (jono.size() == 2) {
            Pelaaja p1 = jono.poll();
            Pelaaja p2 = jono.poll();
            miniLaattaKuva1.setImage(laatat.muodostaPieniKuvaValitsimesta(p1.getLaatat()));
            miniLaattaKuva2.setImage(laatat.muodostaPieniKuvaValitsimesta(p2.getLaatat()));
            miniLaattaKuva3.setImage(laatat.muodostaTyhjaKuva(getBackground()));
            jono.add(p1);
            jono.add(p2);

        } else if (jono.size() == 1) {
            Pelaaja p1 = jono.poll();
            miniLaattaKuva1.setImage(laatat.muodostaPieniKuvaValitsimesta(p1.getLaatat()));
            miniLaattaKuva2.setImage(laatat.muodostaTyhjaKuva(getBackground()));
            miniLaattaKuva3.setImage(laatat.muodostaTyhjaKuva(getBackground()));
            jono.add(p1);
        } else {
            miniLaattaKuva1.setImage(laatat.muodostaTyhjaKuva(getBackground()));
            miniLaattaKuva2.setImage(laatat.muodostaTyhjaKuva(getBackground()));
            miniLaattaKuva3.setImage(laatat.muodostaTyhjaKuva(getBackground()));
        }

        miniLaattaLabel1.repaint();
        miniLaattaLabel2.repaint();
        miniLaattaLabel3.repaint();

    }

    /**
     * Pelin loppuessa purkaa komponentit jo tuo esiin lopputulokset.
     */
    private void peliLoppuu() {
        getContentPane().removeAll();
        JPanel loppuTulokset = new JPanel();
        loppuTulokset.setLayout(new GridLayout(0, 4, 0, 0));
        JLabel loppu = new JLabel("LOPPUTULOKSET", JLabel.CENTER);
        loppu.setFont(new Font("Arial", Font.BOLD, 30));
        TreeMap<Integer, Integer> tulokset = blokus.getLopputulokset();
        int sija = 0;
        int edellinen = 100;
        JLabel sijoitus;
        JLabel pelaaja;
        JLabel tulos;

        for (Map.Entry<Integer, Integer> entry : tulokset.entrySet()) {
            if (entry.getValue() != edellinen) {
                sija++;
                sijoitus = new JLabel(sija + ".");
            } else {
                sijoitus = new JLabel("");
            }
            pelaaja = new JLabel(blokus.getIDVariTekstina(entry.getKey()));
            tulos = new JLabel("" + entry.getValue());
            tulos.setFont(new Font("Arial", Font.BOLD, 18));
            pelaaja.setFont(new Font("Arial", Font.BOLD, 18));
            sijoitus.setFont(new Font("Arial", Font.BOLD, 18));
            tulos.setForeground(getVari(entry.getKey()));
            pelaaja.setForeground(getVari(entry.getKey()));
            loppuTulokset.add(sijoitus);
            loppuTulokset.add(pelaaja);
            loppuTulokset.add(new JLabel("   "));
            loppuTulokset.add(tulos);
            edellinen = entry.getValue();
        }
        JPanel loppuTuloksetMaster = new JPanel();
        loppuTuloksetMaster.setLayout(new GridLayout(2, 1, 0, 0));
        loppuTuloksetMaster.add(loppu);
        loppuTuloksetMaster.add(loppuTulokset);
        loppuTuloksetMaster.setPreferredSize(new Dimension(350, 300));
        getContentPane().add(loppuTuloksetMaster);
        repaint();
        pack();
        setVisible(true);

    }

    private Color getVari(int iD) {
        switch (iD) {
            case 1:
                return GlobaalitMuuttujat.SININEN_VARI;
            case 2:
                return GlobaalitMuuttujat.ORANSSI_VARI;
            case 3:
                return GlobaalitMuuttujat.PUNAINEN_VARI;
            case 4:
                return GlobaalitMuuttujat.VIHREA_VARI;
            default:
                return Color.BLACK;
        }
    }

    /**
     * Muodostaa pelilaudan komponentit ja liittää tarvittavat kuuntelijat
     * siihen
     */
    private void muodostaLauta() {
        lautaPaneeli = new JPanel();
        lautaPaneeli.setCursor(new Cursor(Cursor.HAND_CURSOR));
        lauta = new Lauta(blokus.getPeliLauta(), GlobaalitMuuttujat.LAUDAN_RESOLUUTIO);
        laudanKuva = new ImageIcon(lauta.muodostaLaudastaKuva());
        lautaLabel = new JLabel(laudanKuva);
        LaudanHiiriKuuntelija kuuntelija = new LaudanHiiriKuuntelija(blokus, lauta, this);
        lautaLabel.addMouseListener(kuuntelija);
        lautaLabel.addMouseMotionListener(kuuntelija);
        lautaLabel.addMouseWheelListener(kuuntelija);
        LaudanNappaimistoKuuntelija nKuuntelija = new LaudanNappaimistoKuuntelija(blokus, kuuntelija);
        lautaLabel.addKeyListener(nKuuntelija);
        lautaLabel.setFocusable(true);
        lautaPaneeli.add(lautaLabel);
    }

    /**
     * Muodostaa käytössä olevia laattoja esittävän valitsimen
     */
    private void muodostaLaattaPaneeli() {
        laattaPaneeli = new JPanel();
        laattaPaneeli.setCursor(new Cursor(Cursor.HAND_CURSOR));
        laatat = new LaattaValitsin(blokus.getVuorossa().getLaatat());
        BufferedImage vKuva = laatat.muodostaValitsimestaKuva();
        laattaKuva = new ImageIcon(vKuva);
        laattaLabel = new JLabel(laattaKuva);
        LaattojenHiiriKuuntelija lKuuntelija = new LaattojenHiiriKuuntelija(blokus, laatat, this);
        laattaLabel.addMouseListener(lKuuntelija);
        laattaLabel.addMouseMotionListener(lKuuntelija);
        laattaPaneeli.add(laattaLabel);
    }

    /**
     * Muodostaa muitten kuin vuorossa olevan pelaajan laattavalitsimista
     * näköiskuvat
     */
    private void muodostaPienetLaattaKuvat() {
        miniLaattaKuva1 = new ImageIcon(laatat.muodostaTyhjaKuva(getBackground()));
        miniLaattaKuva2 = new ImageIcon(laatat.muodostaTyhjaKuva(getBackground()));
        miniLaattaKuva3 = new ImageIcon(laatat.muodostaTyhjaKuva(getBackground()));
        miniLaattaLabel1 = new JLabel(miniLaattaKuva1);
        miniLaattaLabel2 = new JLabel(miniLaattaKuva2);
        miniLaattaLabel3 = new JLabel(miniLaattaKuva3);
        paivitaPienetValitsimet();
        JPanel paneeli1 = new JPanel();
        JPanel paneeli2 = new JPanel();
        JPanel paneeli3 = new JPanel();
        paneeli1.add(miniLaattaLabel1);
        paneeli2.add(miniLaattaLabel2);
        paneeli3.add(miniLaattaLabel3);
        oikeaYlaPaneeli.add(paneeli1);
        oikeaYlaPaneeli.add(paneeli2);
        oikeaYlaPaneeli.add(paneeli3);
        oikeaYlaPaneeli.setLayout(new BoxLayout(oikeaYlaPaneeli, BoxLayout.PAGE_AXIS));
    }

    /**
     * Muodostaa pelaajien sen hetkisiä pisteitä kuvastavan tilanteen
     */
    private void muodostaPisteKentta() throws HeadlessException {
        oikeaKeskiPaneeli.setLayout(new GridLayout(5, 2));
        oikeaKeskiPaneeli.setBorder(new LineBorder(Color.BLACK));
        Label pelaaja1 = new Label("Sininen: ");
        pelaaja1Pisteet = new Label("" + blokus.getPelaajanPisteet(GlobaalitMuuttujat.SININEN));
        pelaaja1.setForeground(GlobaalitMuuttujat.SININEN_VARI);
        Label pelaaja2 = new Label("Oranssi: ");
        pelaaja2Pisteet = new Label("" + blokus.getPelaajanPisteet(GlobaalitMuuttujat.ORANSSI));
        pelaaja2.setForeground(GlobaalitMuuttujat.ORANSSI_VARI);
        Label pelaaja3 = new Label("Punainen: ");
        pelaaja3Pisteet = new Label("" + blokus.getPelaajanPisteet(GlobaalitMuuttujat.PUNAINEN));
        pelaaja3.setForeground(GlobaalitMuuttujat.PUNAINEN_VARI);
        Label pelaaja4 = new Label("Vihreä: ");
        pelaaja4Pisteet = new Label("" + blokus.getPelaajanPisteet(GlobaalitMuuttujat.VIHREA));
        pelaaja4.setForeground(GlobaalitMuuttujat.VIHREA_VARI);
        Label pisteet = new Label("Pisteet: ");
        pisteet.setFont(new Font("Arial", Font.BOLD, 15));
        oikeaKeskiPaneeli.add(pisteet);
        oikeaKeskiPaneeli.add(new Label(""));
        oikeaKeskiPaneeli.add(pelaaja1);
        oikeaKeskiPaneeli.add(pelaaja1Pisteet);
        oikeaKeskiPaneeli.add(pelaaja2);
        oikeaKeskiPaneeli.add(pelaaja2Pisteet);
        oikeaKeskiPaneeli.add(pelaaja3);
        oikeaKeskiPaneeli.add(pelaaja3Pisteet);
        oikeaKeskiPaneeli.add(pelaaja4);
        oikeaKeskiPaneeli.add(pelaaja4Pisteet);
        oikeaKeskiPaneeli.setPreferredSize(new Dimension(190, 100));
    }

    /**
     * Muodostaa tapahtumat kertovan tekstilaatikon
     */
    private void muodostaTapahtumaKentta() throws HeadlessException {
        tekstialue = new JTextArea();
        tekstialue.setText("Peli alkaa. \nAloita omasta kulmastasi. "
                + "\nLaatta saa koskea vain kulmittain\njo asettamaasi laattaan nähden. "
                + "\nSe jolla on lopussa vähiten pisteitä voittaa pelin.\nOnnea peliin!");
        tekstialue.setEditable(false);
        Label tapahtumat = new Label("Tapahtumat: ");
        tapahtumat.setFont(new Font("Arial", Font.BOLD, 15));
        oikeaAlaPaneeli.add(tapahtumat);
        tekstialue.setWrapStyleWord(true);
        tekstialue.setLineWrap(true);
        tekstialue.setFocusable(false);
        JScrollPane scroll = new JScrollPane(tekstialue);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setPreferredSize(new Dimension(190, 120));
        DefaultCaret caret = (DefaultCaret) tekstialue.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        scroll.setFocusable(false);
        scroll.getVerticalScrollBar().setFocusable(false);
        oikeaAlaPaneeli.setFocusable(false);
        oikeaAlaPaneeli.add(scroll);
    }

    /**
     * Muodostaa antautumis ja vuoron ohittamis napit
     */
    private void muodostaNapit() {
        oikeaAlaPaneeli.add(Box.createRigidArea(new Dimension(0, 4)));
        JPanel napit = new JPanel();
        ohitaVuoroNappi = new JButton("Ohita vuoro");
        ohitaVuoroNappi.setFont(new Font("Arial", Font.PLAIN, 13));
        ohitaVuoroNappi.setPreferredSize(new Dimension(100, 30));
        ohitaVuoroNappi.setAlignmentX(JButton.CENTER_ALIGNMENT);
        ohitaVuoroNappi.addActionListener(new OhitaVuoroKuuntelija(blokus, this));
        ohitaVuoroNappi.setFocusable(false);
        napit.add(ohitaVuoroNappi);
        antauduNappi = new JButton("Antaudu");
        antauduNappi.setFont(new Font("Arial", Font.PLAIN, 13));
        antauduNappi.setPreferredSize(new Dimension(85, 30));
        antauduNappi.setAlignmentX(JButton.CENTER_ALIGNMENT);
        antauduNappi.addActionListener(new AntautumisKuuntelija(blokus, this));
        antauduNappi.setFocusable(false);
        napit.add(antauduNappi);
        oikeaAlaPaneeli.add(napit);
        oikeaAlaPaneeli.setLayout(new BoxLayout(oikeaAlaPaneeli, BoxLayout.PAGE_AXIS));
    }
}
