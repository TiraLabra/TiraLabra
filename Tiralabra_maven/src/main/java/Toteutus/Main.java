package Toteutus;

import java.io.FileNotFoundException;

public class Main {
    
    public static void main(String[] args) {
        PakkaamisenOhjaaja ohjaaja = new PakkaamisenOhjaaja();
        
        try {
            ohjaaja.suoritaPakkaaminen("testi.txt");
        }
        catch (Exception e) {
            if (e.getClass() == FileNotFoundException.class) {
                System.out.print("Tiedoston luku ei onnistunut. Annoit sen polun väärin.\nOhjelma suljetaan.");
            }
        }
        
    }
}
