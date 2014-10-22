package viidensuora.gui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import viidensuora.ai.AlphaBetaKarsinta;
import viidensuora.ai.Etsintametodi;
import viidensuora.ai.MinMax;
import viidensuora.ai.MunEvaluoija;
import viidensuora.logiikka.Ristinolla;

/**
 * Ennen peliä näutettävä ikkuna, jossa valitaan asetukset peliin.
 *
 * @author juha
 */
public class Asetusruutu extends JPanel implements ActionListener, ChangeListener {

    private static final int DEF_LAUDAN_KOKO = 8;
    private static final int MAX_LAUDAN_KOKO = 15;
    private static final int MIN_LAUDAN_KOKO = 3;

    private static final int DEF_SUORAN_PITUUS = 5;
    private static final int MAX_SUORAN_PITUUS = 15;
    private static final int MIN_SUORAN_PITUUS = 3;

    private JComboBox pelaajaLista1;
    private JComboBox pelaajaLista2;
    private JCheckBox dataikkunaCB;
    private JLabel kokoLabel;
    private JSpinner kokoSP;
    private JLabel pituusLabel;
    private JSpinner pituusSP;
    private JButton pelaaBtn;

    private final Kayttoliittyma kayttoliittyma;

    public Asetusruutu(Kayttoliittyma kali) {
        this.kayttoliittyma = kali;
        setPreferredSize(new Dimension(300, 300));
        luoKomponentit();
    }

    /**
     * Luo komponentit ja asettaa ne paneeliin.
     */
    private void luoKomponentit() {
        setLayout(new GridLayout(0, 1, 0, 10));

        String[] pelaajaValinnat = {"Ihminen", "AI - MinMax", "AI - AlphaBeta"};

        pelaajaLista1 = new JComboBox(pelaajaValinnat);
        pelaajaLista1.setSelectedIndex(0);
        lisaaPaneeliin(true, "Risti", pelaajaLista1);

        pelaajaLista2 = new JComboBox(pelaajaValinnat);
        pelaajaLista2.setSelectedIndex(2);
        lisaaPaneeliin(true, "Nolla", pelaajaLista2);

        dataikkunaCB = new JCheckBox("Näytä AI-dataikkuna");
        add(dataikkunaCB);

        kokoLabel = new JLabel(" Laudan koko: ");
        kokoSP = new JSpinner(new SpinnerNumberModel(DEF_LAUDAN_KOKO,
                MIN_LAUDAN_KOKO, MAX_LAUDAN_KOKO, 1));
        kokoSP.addChangeListener(this);
        lisaaPaneeliin(false, null, kokoLabel, kokoSP);

        pituusLabel = new JLabel(" Suoran pituus: ");
        pituusSP = new JSpinner(new SpinnerNumberModel(DEF_SUORAN_PITUUS,
                MIN_SUORAN_PITUUS, MAX_SUORAN_PITUUS, 1));
        pituusSP.addChangeListener(this);
        lisaaPaneeliin(false, null, pituusLabel, pituusSP);

        pelaaBtn = new JButton("Pelaa!");
        pelaaBtn.addActionListener(this);
        add(pelaaBtn);
    }

    /**
     * Laittaa komponentit Box layouttiin ja boxin paneliin.
     *
     * @param reunusta TRUE jos Boxille asetetaan reuna, FALSE jos ei
     * @param otsikko Reunassa oleva otsikkoteksti
     * @param komponentit Boxiin laitettavat komponentis
     */
    private void lisaaPaneeliin(boolean reunusta, String otsikko, Component... komponentit) {
        Box box = Box.createHorizontalBox();
        for (Component c : komponentit) {
            box.add(c);
        }
        if (reunusta) {
            box.setBorder(BorderFactory.createTitledBorder(otsikko));
        }
        add(box);
    }

    /**
     * Annetaan asetuksia vastaavat tiedot pääikkunalle kun "Pelaa!" nappia on
     * painettu.
     *
     * @param e
     */
    public void actionPerformed(ActionEvent e) {
        int laudanKoko = (Integer) kokoSP.getValue();
        int suoranPituus = (Integer) pituusSP.getValue();
        Ristinolla rn = new Ristinolla(laudanKoko, laudanKoko, suoranPituus);
        Etsintametodi ristinAI = luoTekoaly(pelaajaLista1.getSelectedIndex(), rn);
        Etsintametodi nollanAI = luoTekoaly(pelaajaLista2.getSelectedIndex(), rn);
        kayttoliittyma.luoPeliruutu(rn, ristinAI, nollanAI, dataikkunaCB.isSelected());
    }

    /**
     * Huolehtii ettei voittavan suoran pituus voi olla JSpinnerissä suurempi
     * kuin pelilaudan koko.
     *
     * @param e
     */
    public void stateChanged(ChangeEvent e) {
        int koko = (Integer) kokoSP.getValue();
        int pituus = (Integer) pituusSP.getValue();
        if (pituus > koko) {
            pituusSP.setValue(koko);
        }
    }

    /**
     * Luo asetuksia vastaavan tekoälyn.
     *
     * @param tyyppi Valitun tekoälytyypin numero valintalistassa.
     * @param rn Pelitilanne Etsintämetodille
     * @return Asetuksia vastaava Tekoäly tai NULL jos pelaajaksi on valittu
     * ihminen.
     */
    private Etsintametodi luoTekoaly(int tyyppi, Ristinolla rn) {
        if (tyyppi == 1) {
            System.out.println("MinMax");
            return new MinMax(rn, new MunEvaluoija());
        }
        if (tyyppi == 2) {            
            System.out.println("AlphaBeta");
            return new AlphaBetaKarsinta(rn, new MunEvaluoija());
        }
        return null;
    }

}
