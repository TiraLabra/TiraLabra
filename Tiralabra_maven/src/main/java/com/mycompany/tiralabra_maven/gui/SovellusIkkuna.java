/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Toiminto;
import com.mycompany.tiralabra_maven.algoritmi.AlgoritmiTyyppi;
import com.mycompany.tiralabra_maven.algoritmi.Simulaatio;
import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author mikko
 */
public class SovellusIkkuna extends javax.swing.JFrame {

    private Simulaatio simulaatio;
    private int sivunPituus;

    /**
     * Creates new form NewJFrame
     *
     * @param simulaatio
     * @param sivunPituus
     */
    public SovellusIkkuna(Simulaatio simulaatio, int sivunPituus) {
        this.simulaatio = simulaatio;
        this.sivunPituus = sivunPituus;
        initComponents();
        paivitaKomponentit();
    }

    public Piirtoalusta getPiirtoalusta() {
        return this.piirtoalusta;
    }

    private void poistaValinnat() {
        lattiaButton.setSelected(false);
        seinaButton.setSelected(false);
        vesiButton.setSelected(false);
        ruohoButton.setSelected(false);
        hiekkaButton.setSelected(false);
        aloituspisteButton.setSelected(false);
        maaliButton.setSelected(false);
    }

    private void paivitaValinnat() {
        poistaValinnat();
        switch (simulaatio.getValittuToiminto()) {
            case SEINA:
                seinaButton.setSelected(true);
                break;
            case LATTIA:
                lattiaButton.setSelected(true);
                break;
            case ALKU:
                aloituspisteButton.setSelected(true);
                break;
            case MAALI:
                maaliButton.setSelected(true);
                break;
            case VESI:
                vesiButton.setSelected(true);
                break;
            case RUOHO:
                ruohoButton.setSelected(true);
                break;
            case HIEKKA:
                hiekkaButton.setSelected(true);
        }
    }

    private void asetaLiikkumisKustannukset() {
        int lattiaKustannus = -1;
        int ruohoKustannus = -1;
        int hiekkaKustannus = -1;
        int vesiKustannus = -1;
        try {
            lattiaKustannus = Integer.parseInt(lattiaKustannusField.getText());
            ruohoKustannus = Integer.parseInt(ruohoKustannusField.getText());
            hiekkaKustannus = Integer.parseInt(hiekkaKustannusField.getText());
            vesiKustannus = Integer.parseInt(vesiKustannusField.getText());
        } catch (Exception e) {
            paivitaKustannusKentat();
            return;
        }
        simulaatio.asetaRuudunKustannus(Ruutu.LATTIA, lattiaKustannus);
        simulaatio.asetaRuudunKustannus(Ruutu.RUOHO, ruohoKustannus);
        simulaatio.asetaRuudunKustannus(Ruutu.HIEKKA, hiekkaKustannus);
        simulaatio.asetaRuudunKustannus(Ruutu.VESI, vesiKustannus);
    }
    
    private void asetaHidaste() {
        int hidaste = simulaatio.getHidaste();
        try {
            hidaste = Integer.parseInt(hidasteField.getText());
        } catch (NumberFormatException e) {
            
        }
        simulaatio.setHidaste(hidaste);
    }

    private void paivitaKustannusKentat() {
        this.lattiaKustannusField.setText(Ruutu.LATTIA.getHinta() + "");
        this.ruohoKustannusField.setText(Ruutu.RUOHO.getHinta() + "");
        this.hiekkaKustannusField.setText(Ruutu.HIEKKA.getHinta() + "");
        this.vesiKustannusField.setText(Ruutu.VESI.getHinta() + "");

    }

    private void asetaNappienTila(boolean enabled) {
        lattiaButton.setEnabled(enabled);
        seinaButton.setEnabled(enabled);
        vesiButton.setEnabled(enabled);
        hiekkaButton.setEnabled(enabled);
        ruohoButton.setEnabled(enabled);
        aloituspisteButton.setEnabled(enabled);
        maaliButton.setEnabled(enabled);
        liikkuminenVinottainCheckBox.setEnabled(enabled);
        leveysField.setEnabled(enabled);
        korkeusField.setEnabled(enabled);
        uusiRuudukkoButton.setEnabled(enabled);
        lataaTiedostostaButton.setEnabled(enabled);
        liikkuminenVinottainCheckBox.setEnabled(enabled);
        lattiaKustannusField.setEnabled(enabled);
        ruohoKustannusField.setEnabled(enabled);
        hiekkaKustannusField.setEnabled(enabled);
        vesiKustannusField.setEnabled(enabled);
        asetaLiikkumisKustannuksetButton.setEnabled(enabled);
        algoritminValintaBox.setEnabled(enabled);
        heuristiikanValintaBox.setEnabled(enabled);
        hidasteField.setEnabled(enabled);
    }

    private void paivitaLeveysJaKorkeus() {
        leveysField.setText(simulaatio.getLeveys() + "");
        korkeusField.setText(simulaatio.getKorkeus() + "");
        jScrollPane1.revalidate();
        jScrollPane1.repaint();
    }

    private void paivitaKomponentit() {
        simulaatioButton.setSelected(simulaatio.onkoSimulaatioKaynnissa());
        if (simulaatioButton.isSelected()) {
            simulaatioButton.setText("Lopeta simulaatio");
            asetaNappienTila(false);
        } else {
            simulaatioButton.setText("Aloita simulaatio");
            asetaNappienTila(true);
        }
        paivitaValinnat();
        paivitaLeveysJaKorkeus();
        paivitaKustannusKentat();
        hidasteField.setText(simulaatio.getHidaste() + "");
        liikkuminenVinottainCheckBox.setSelected(simulaatio.saakoLiikkuaVinottain());
        algoritminValintaBox.setSelectedItem(simulaatio.getAlgoritmiTyyppi());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        simulaatioButton = new javax.swing.JToggleButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        lattiaButton = new javax.swing.JToggleButton();
        ruohoButton = new javax.swing.JToggleButton();
        hiekkaButton = new javax.swing.JToggleButton();
        vesiButton = new javax.swing.JToggleButton();
        seinaButton = new javax.swing.JToggleButton();
        aloituspisteButton = new javax.swing.JToggleButton();
        maaliButton = new javax.swing.JToggleButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        piirtoalusta = new Piirtoalusta(simulaatio, sivunPituus);
        HiirenKuuntelija hiirenkuuntelija = new HiirenKuuntelija(sivunPituus, simulaatio);
        piirtoalusta.addMouseListener(hiirenkuuntelija);
        piirtoalusta.addMouseMotionListener(hiirenkuuntelija);
        jPanel2 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        leveysField = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        korkeusField = new javax.swing.JTextField();
        uusiRuudukkoButton = new javax.swing.JButton();
        lataaTiedostostaButton = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        liikkuminenVinottainCheckBox = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        lattiaKustannusField = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        ruohoKustannusField = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        hiekkaKustannusField = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        vesiKustannusField = new javax.swing.JTextField();
        asetaLiikkumisKustannuksetButton = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        algoritminValintaBox = new javax.swing.JComboBox(AlgoritmiTyyppi.values());
        jLabel11 = new javax.swing.JLabel();
        heuristiikanValintaBox = new javax.swing.JComboBox();
        jLabel12 = new javax.swing.JLabel();
        hidasteField = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Reittialgoritmit");

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        simulaatioButton.setText("Aloita simulaatio");
        simulaatioButton.setFocusable(false);
        simulaatioButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        simulaatioButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        simulaatioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                simulaatioButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(simulaatioButton);
        jToolBar1.add(jSeparator1);

        lattiaButton.setText("Lattia");
        lattiaButton.setFocusable(false);
        lattiaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lattiaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lattiaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lattiaButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(lattiaButton);

        ruohoButton.setText("Ruoho");
        ruohoButton.setFocusable(false);
        ruohoButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ruohoButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ruohoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ruohoButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(ruohoButton);

        hiekkaButton.setText("Hiekka");
        hiekkaButton.setFocusable(false);
        hiekkaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        hiekkaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        hiekkaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hiekkaButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(hiekkaButton);

        vesiButton.setText("Vesi");
        vesiButton.setFocusable(false);
        vesiButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        vesiButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        vesiButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vesiButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(vesiButton);

        seinaButton.setText("Seinä");
        seinaButton.setFocusable(false);
        seinaButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        seinaButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        seinaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seinaButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(seinaButton);

        aloituspisteButton.setText("Aloituspiste");
        aloituspisteButton.setFocusable(false);
        aloituspisteButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        aloituspisteButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        aloituspisteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aloituspisteButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(aloituspisteButton);

        maaliButton.setText("Maali");
        maaliButton.setFocusable(false);
        maaliButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        maaliButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        maaliButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                maaliButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(maaliButton);

        javax.swing.GroupLayout piirtoalustaLayout = new javax.swing.GroupLayout(piirtoalusta);
        piirtoalusta.setLayout(piirtoalustaLayout);
        piirtoalustaLayout.setHorizontalGroup(
            piirtoalustaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 552, Short.MAX_VALUE)
        );
        piirtoalustaLayout.setVerticalGroup(
            piirtoalustaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 667, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(piirtoalusta);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Ruudukko:");

        leveysField.setText("jTextField1");
        leveysField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                leveysFieldActionPerformed(evt);
            }
        });

        jLabel2.setText("leveys:");

        jLabel3.setText("korkeus:");

        korkeusField.setText("jTextField2");

        uusiRuudukkoButton.setText("Tee uusi ruudukko");
        uusiRuudukkoButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                uusiRuudukkoButtonActionPerformed(evt);
            }
        });

        lataaTiedostostaButton.setText("Lataa tiedostosta");
        lataaTiedostostaButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lataaTiedostostaButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(uusiRuudukkoButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(leveysField)
                            .addComponent(korkeusField)))
                    .addComponent(lataaTiedostostaButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(leveysField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(korkeusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(uusiRuudukkoButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lataaTiedostostaButton)
                .addGap(0, 38, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Liikkuminen:");

        liikkuminenVinottainCheckBox.setText("Salli liikkuminen vinottain");
        liikkuminenVinottainCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liikkuminenVinottainCheckBoxActionPerformed(evt);
            }
        });

        jLabel5.setText("Liikkumiskustannukset:");

        jLabel6.setText("Lattia:");

        lattiaKustannusField.setText("jTextField1");

        jLabel7.setText("Ruoho:");

        ruohoKustannusField.setText("jTextField2");

        jLabel8.setText("Hiekka:");

        hiekkaKustannusField.setText("jTextField3");

        jLabel9.setText("Vesi:");

        vesiKustannusField.setText("jTextField4");

        asetaLiikkumisKustannuksetButton.setText("Aseta arvot");
        asetaLiikkumisKustannuksetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asetaLiikkumisKustannuksetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(asetaLiikkumisKustannuksetButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(25, 25, 25)
                        .addComponent(hiekkaKustannusField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addGap(43, 43, 43)
                        .addComponent(vesiKustannusField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(31, 31, 31)
                        .addComponent(lattiaKustannusField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(28, 28, 28)
                        .addComponent(ruohoKustannusField))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(liikkuminenVinottainCheckBox)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(liikkuminenVinottainCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lattiaKustannusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(ruohoKustannusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(4, 4, 4)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(hiekkaKustannusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(vesiKustannusField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(asetaLiikkumisKustannuksetButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel10.setText("Algoritmi:");

        algoritminValintaBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                algoritminValintaBoxActionPerformed(evt);
            }
        });

        jLabel11.setText("Heuristiikka:");

        heuristiikanValintaBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Manhattan" }));

        jLabel12.setText("Hidaste(ms):");

        hidasteField.setText("jTextField1");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(algoritminValintaBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(heuristiikanValintaBox, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(4, 4, 4)
                        .addComponent(hidasteField)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(algoritminValintaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(heuristiikanValintaBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(hidasteField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(44, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(100, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 807, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void seinaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seinaButtonActionPerformed
        simulaatio.setToiminto(Toiminto.SEINA);
        paivitaValinnat();
    }//GEN-LAST:event_seinaButtonActionPerformed

    private void maaliButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_maaliButtonActionPerformed
        // TODO add your handling code here:
        simulaatio.setToiminto(Toiminto.MAALI);
        paivitaValinnat();
    }//GEN-LAST:event_maaliButtonActionPerformed

    private void simulaatioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_simulaatioButtonActionPerformed
        // TODO add your handling code here:
        asetaLiikkumisKustannukset();
        asetaHidaste();
        if (simulaatioButton.isSelected()) {
            simulaatio.etsiReitti();
        } else {
            simulaatio.lopetaReitinEtsiminen();
        }
        paivitaKomponentit();

    }//GEN-LAST:event_simulaatioButtonActionPerformed

    private void lattiaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lattiaButtonActionPerformed
        // TODO add your handling code here:
        simulaatio.setToiminto(Toiminto.LATTIA);
        paivitaValinnat();

    }//GEN-LAST:event_lattiaButtonActionPerformed

    private void aloituspisteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aloituspisteButtonActionPerformed
        // TODO add your handling code here:
        simulaatio.setToiminto(Toiminto.ALKU);
        paivitaValinnat();
    }//GEN-LAST:event_aloituspisteButtonActionPerformed

    private void leveysFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_leveysFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_leveysFieldActionPerformed

    private void uusiRuudukkoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_uusiRuudukkoButtonActionPerformed
        // TODO add your handling code here:
        int leveys = -1;
        int korkeus = -1;
        try {
            leveys = Integer.parseInt(leveysField.getText());
            korkeus = Integer.parseInt(korkeusField.getText());
        } catch (Exception e) {
            paivitaKomponentit();
            return;
        }
        simulaatio.teeUusiRuudukko(leveys, korkeus);
        paivitaKomponentit();
    }//GEN-LAST:event_uusiRuudukkoButtonActionPerformed

    private void liikkuminenVinottainCheckBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_liikkuminenVinottainCheckBoxActionPerformed
        // TODO add your handling code here:
        simulaatio.asetaVinottainLiikkuminenSallituksi(liikkuminenVinottainCheckBox.isSelected());
    }//GEN-LAST:event_liikkuminenVinottainCheckBoxActionPerformed

    private void lataaTiedostostaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lataaTiedostostaButtonActionPerformed
        // TODO add your handling code here:
        JFileChooser openFile = new JFileChooser();
        openFile.setDialogTitle("Lataa kuvatiedosto");

        int palautus = openFile.showOpenDialog(null);
        if (palautus != JFileChooser.APPROVE_OPTION) {
            System.out.println("Peruutettu");
            return;
        }
        File file = openFile.getSelectedFile();
        if (file == null) {
            return;
        }
        try {
            simulaatio.lataaRuudukkoKuvasta(file);
            //editori.loadMap(file);
        } catch (Exception e) {

        }
    }//GEN-LAST:event_lataaTiedostostaButtonActionPerformed

    private void hiekkaButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_hiekkaButtonActionPerformed
        // TODO add your handling code here:
        simulaatio.setToiminto(Toiminto.HIEKKA);
        paivitaValinnat();
    }//GEN-LAST:event_hiekkaButtonActionPerformed

    private void vesiButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vesiButtonActionPerformed
        // TODO add your handling code here:
        simulaatio.setToiminto(Toiminto.VESI);
        paivitaValinnat();
    }//GEN-LAST:event_vesiButtonActionPerformed

    private void ruohoButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ruohoButtonActionPerformed
        // TODO add your handling code here:
        simulaatio.setToiminto(Toiminto.RUOHO);
        paivitaValinnat();
    }//GEN-LAST:event_ruohoButtonActionPerformed

    private void asetaLiikkumisKustannuksetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asetaLiikkumisKustannuksetButtonActionPerformed
        // TODO add your handling code here:
        asetaLiikkumisKustannukset();
        paivitaKomponentit();

    }//GEN-LAST:event_asetaLiikkumisKustannuksetButtonActionPerformed

    private void algoritminValintaBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_algoritminValintaBoxActionPerformed
        // TODO add your handling code here:
        simulaatio.asetaAlgoritmi((AlgoritmiTyyppi)algoritminValintaBox.getSelectedItem());
    }//GEN-LAST:event_algoritminValintaBoxActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SovellusIkkuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SovellusIkkuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SovellusIkkuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SovellusIkkuna.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new NewJFrame().setVisible(true);
//            }
//        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox algoritminValintaBox;
    private javax.swing.JToggleButton aloituspisteButton;
    private javax.swing.JButton asetaLiikkumisKustannuksetButton;
    private javax.swing.JComboBox heuristiikanValintaBox;
    private javax.swing.JTextField hidasteField;
    private javax.swing.JToggleButton hiekkaButton;
    private javax.swing.JTextField hiekkaKustannusField;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField korkeusField;
    private javax.swing.JButton lataaTiedostostaButton;
    private javax.swing.JToggleButton lattiaButton;
    private javax.swing.JTextField lattiaKustannusField;
    private javax.swing.JTextField leveysField;
    private javax.swing.JCheckBox liikkuminenVinottainCheckBox;
    private javax.swing.JToggleButton maaliButton;
    private com.mycompany.tiralabra_maven.gui.Piirtoalusta piirtoalusta;
    private javax.swing.JToggleButton ruohoButton;
    private javax.swing.JTextField ruohoKustannusField;
    private javax.swing.JToggleButton seinaButton;
    private javax.swing.JToggleButton simulaatioButton;
    private javax.swing.JButton uusiRuudukkoButton;
    private javax.swing.JToggleButton vesiButton;
    private javax.swing.JTextField vesiKustannusField;
    // End of variables declaration//GEN-END:variables
}
