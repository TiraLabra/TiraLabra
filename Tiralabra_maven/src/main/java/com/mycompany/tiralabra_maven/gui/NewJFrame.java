/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.tiralabra_maven.gui;

import com.mycompany.tiralabra_maven.Toiminto;
import com.mycompany.tiralabra_maven.algoritmi.Simulaatio;

/**
 *
 * @author mikko
 */
public class NewJFrame extends javax.swing.JFrame {

    private Simulaatio simulaatio;
    private int sivunPituus;

    /**
     * Creates new form NewJFrame
     *
     * @param simulaatio
     * @param piirtologiikka
     * @param sivunPituus
     */
    public NewJFrame(Simulaatio simulaatio, int sivunPituus) {
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
        }
    }

    private void asetaNappienTila(boolean enabled) {
        lattiaButton.setEnabled(enabled);
        seinaButton.setEnabled(enabled);
        aloituspisteButton.setEnabled(enabled);
        maaliButton.setEnabled(enabled);
        liikkuminenVinottainCheckBox.setEnabled(enabled);
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
        liikkuminenVinottainCheckBox.setSelected(simulaatio.saakoLiikkuaVinottain());
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
        jPanel3 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        liikkuminenVinottainCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
            .addGap(0, 645, Short.MAX_VALUE)
        );
        piirtoalustaLayout.setVerticalGroup(
            piirtoalustaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 386, Short.MAX_VALUE)
        );

        jScrollPane1.setViewportView(piirtoalusta);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Ruudukon koko:");

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
                            .addComponent(korkeusField))))
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
                .addGap(0, 12, Short.MAX_VALUE))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel4.setText("Liikkuminen");

        liikkuminenVinottainCheckBox.setText("Salli liikkuminen vinottain");
        liikkuminenVinottainCheckBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                liikkuminenVinottainCheckBoxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(liikkuminenVinottainCheckBox)
                    .addComponent(jLabel4))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(liikkuminenVinottainCheckBox)
                .addContainerGap(42, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 709, Short.MAX_VALUE)
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
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(NewJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
    private javax.swing.JToggleButton aloituspisteButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JTextField korkeusField;
    private javax.swing.JToggleButton lattiaButton;
    private javax.swing.JTextField leveysField;
    private javax.swing.JCheckBox liikkuminenVinottainCheckBox;
    private javax.swing.JToggleButton maaliButton;
    private com.mycompany.tiralabra_maven.gui.Piirtoalusta piirtoalusta;
    private javax.swing.JToggleButton seinaButton;
    private javax.swing.JToggleButton simulaatioButton;
    private javax.swing.JButton uusiRuudukkoButton;
    // End of variables declaration//GEN-END:variables
}
