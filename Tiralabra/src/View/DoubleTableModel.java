package View;

/**
 * Luokan tarkoituksena on luoda käyttöliittymää varten JTablen modeli joka hyväksyy ainoastaan
 * liukulukujan taulukon arvoiksi. Ei toistaiseksi ole käytössä.
 */
import javax.swing.table.DefaultTableModel;


public class DoubleTableModel extends DefaultTableModel {

    public DoubleTableModel(int i, int i1) {
        super(i, i1);
        
    }
    

    @Override  
      public Class getColumnClass(int col) {  
        return Double.class;  
    }  

   
   
    
    
    

    
    
    
}
