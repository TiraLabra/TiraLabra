package View;

import Controller.Controller;

public class Kayttoliittyma extends javax.swing.JFrame {

    private Controller controller;
//    private Integer[] matriisiDimensiot = {1,2,3,4,6};

    /**
     * Mahdollistaa controllerin injektoinnin testejä varten.
     * 
     * @param controller 
     */
    public Kayttoliittyma(Controller controller) {
        initComponents();
        this.controller = controller;


    }

    /**
     * Käynnistää käyttöliittymän.
     */
    public void run() {
        setVisible(true);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        jRadioButton4 = new javax.swing.JRadioButton();
        skalaari = new javax.swing.JTextField();
        Laske = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        virheRuutu = new javax.swing.JTextArea();
        jScrollPane2 = new javax.swing.JScrollPane();
        matriisiB = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        tulosruutu = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        matriisiA = new javax.swing.JTextArea();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(850, 550));
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        jLabel2.setText("Matriisilaskin");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(366, 11, -1, -1));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel1.setText("Laskutoimitukset");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 379, 326, 27));

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Kerro matriisi A skalaarilla");
        jRadioButton1.setActionCommand("1");
        getContentPane().add(jRadioButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 412, -1, -1));

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("Laske yhteen matriisit A ja B");
        jRadioButton2.setActionCommand("2");
        getContentPane().add(jRadioButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(29, 459, -1, -1));

        buttonGroup1.add(jRadioButton3);
        jRadioButton3.setText("Kerro matriisi B matriisilla A");
        jRadioButton3.setActionCommand("3");
        getContentPane().add(jRadioButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 412, -1, -1));

        buttonGroup1.add(jRadioButton4);
        jRadioButton4.setText("Laske Matriisin A determinantti");
        jRadioButton4.setActionCommand("4");
        getContentPane().add(jRadioButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(281, 444, -1, -1));

        skalaari.setText("Anna skalaari");
        getContentPane().add(skalaari, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 437, 87, -1));

        Laske.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Laske.setText("Laske");
        Laske.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LaskeActionPerformed(evt);
            }
        });
        getContentPane().add(Laske, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 400, 143, 62));

        jLabel3.setText("Tulos");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 110, 72, 20));

        jLabel4.setText("Matriisi A");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 72, 23));

        virheRuutu.setEditable(false);
        virheRuutu.setColumns(20);
        virheRuutu.setRows(5);
        jScrollPane1.setViewportView(virheRuutu);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 460, 400, 50));

        matriisiB.setColumns(20);
        matriisiB.setRows(5);
        jScrollPane2.setViewportView(matriisiB);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 130, 240, 251));

        tulosruutu.setEditable(false);
        tulosruutu.setColumns(20);
        tulosruutu.setRows(5);
        jScrollPane3.setViewportView(tulosruutu);

        getContentPane().add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 130, 300, 251));

        matriisiA.setColumns(20);
        matriisiA.setRows(5);
        jScrollPane4.setViewportView(matriisiA);

        getContentPane().add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 130, 240, 251));

        jLabel7.setText("Matriisi B");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 110, 72, 20));

        jTextPane1.setText("Anna matriisin alkiot pilkulla erotettuna esim:\n2,2\n1,3");
        jScrollPane5.setViewportView(jTextPane1);

        getContentPane().add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 310, 60));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Lähettää Controllerille parametrit, joka palauttaa tuloksen tulosruutuun.
     *
     * @param evt Laske-napin painaminen käyttöliittymässä
     */
    private void LaskeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LaskeActionPerformed
        try {
            tulosruutu.setText(null);
            tulosruutu.setText(controller.laske(matriisiA.getText(), matriisiB.getText(), buttonGroup1.getSelection().getActionCommand(), skalaari.getText()));
            virheRuutu.setText(null);
        } catch (Exception e) {
            virheRuutu.setText(e.getMessage());
        }

    }//GEN-LAST:event_LaskeActionPerformed
    /**
     * @param args the command line arguments
     */
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Laske;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JRadioButton jRadioButton4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JTextPane jTextPane1;
    private javax.swing.JTextArea matriisiA;
    private javax.swing.JTextArea matriisiB;
    private javax.swing.JTextField skalaari;
    private javax.swing.JTextArea tulosruutu;
    private javax.swing.JTextArea virheRuutu;
    // End of variables declaration//GEN-END:variables

    /**
     *
     * @return Tarkistaa mikä laskutoimitus on valittuna ja palauttaa sen
     * indeksinumeron.
     */
    private int getLaskutoimitus() {
     return 1;
    }
}
