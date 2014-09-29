package Käyttöliittymä;



import Algoritmi.Reitinhaku;
import Algoritmi.Verkko;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
 
public class Aloitus extends JPanel implements ActionListener {
    

    private static String Piirrä = "Piirrä";
    private static String Puhdista = "Puhdista";
    private static String Viivat_pois = "Viivat pois";
     
    private Kuva ikkuna;
    private Verkko verkko;
    private Reitinhaku reitti;
 
    public Aloitus() {
        super(new BorderLayout());
         
        ikkuna = new Kuva();

 
        JButton Piirränappi = new JButton("Piirrä");
        Piirränappi.setActionCommand(Piirrä);
        Piirränappi.addActionListener(this);
         
        JButton Puhdistanappi = new JButton("Puhdista");
        Puhdistanappi.setActionCommand(Puhdista);
        Puhdistanappi.addActionListener(this);
         
        JButton Viivat_poisnappi = new JButton("Viivat pois");
        Viivat_poisnappi.setActionCommand(Viivat_pois);
        Viivat_poisnappi.addActionListener(this);
 
        ikkuna.setPreferredSize(new Dimension(800, 800));
        add(ikkuna, BorderLayout.CENTER);
 
        JPanel panel = new JPanel(new GridLayout(0,3));
        panel.add(Piirränappi);
        panel.add(Puhdistanappi);
        panel.add(Viivat_poisnappi);
        add(panel, BorderLayout.SOUTH);
    }
 
     
    public void actionPerformed(ActionEvent e) {
        String komento = e.getActionCommand();
         
        if (Piirrä.equals(komento)) {
            
            try{
                verkko = new Verkko(ikkuna.kuva, ikkuna.haeMaali());
                reitti = new Reitinhaku(verkko, ikkuna.haeLahto());
                
                reitti.Haku();   
                ikkuna.repaint();
            }catch(NullPointerException ex){
            }
            
        } else if (Puhdista.equals(komento)) {
            
            ikkuna.haeKuva();
            ikkuna.repaint();
            
        } else if (Viivat_pois.equals(komento)) {
            
            try{
                putsaa();
                ikkuna.repaint();
            }catch(NullPointerException ex){
            }
            
        }
    }
 
    /**
     * 
     */
    private static void LuoIkkuna() {

        JFrame frame = new JFrame("Shift+klik = alkupiste        CTRL+klik = loppupiste");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Aloitus newContentPane = new Aloitus();
        frame.setContentPane(newContentPane);
 
        frame.pack();
        frame.setVisible(true);
    }
 
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                 LuoIkkuna();

            }
        });
    }
    
    public void putsaa(){
        
        int rgb=new Color(255,255,255).getRGB();
        for (int i = 0; i < verkko.taulukko[0].length; i++) {
            for (int j = 0; j < verkko.taulukko.length; j++) {
                if(verkko.taulukko[i][j].haeSeina()==false){
                    
                    ikkuna.kuva.setRGB(j, i, rgb);
                }
            }
        }
    }
}