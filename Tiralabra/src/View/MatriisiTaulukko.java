package View;

import javax.swing.JTable;


/**
 * MatriisiTaulukko toteuttaa JTablen muutoin, mutta käytää itse tehtyä Modelia ja sisältää
 * toiminnon matriisin palauttamiseen liukulukutaulukkona. Ei toistaiseksi käytössä.
 * 
 */
public class MatriisiTaulukko extends JTable {

    
    public MatriisiTaulukko(int rivit, int sarakkeet) {
        super(new DoubleTableModel(rivit, sarakkeet));       
        setTableHeader(null);
        setRowHeight(20);
        for (int i = 0; i < getColumnCount(); i++) {
            getColumnModel().getColumn(i).setPreferredWidth(40);
        }
        
    }

    public double[][] getMatriisi() {
        double[][] matriisi = new double[getRowCount()][getColumnCount()];
        for (int i = 0; i < matriisi.length; i++) {
            for (int j = 0; j < matriisi[0].length; j++) {
                matriisi[i][j] = Double.parseDouble(getValueAt(i, j).toString());
            }
        }

        return matriisi;
    }
}
