package Toteutus;

import java.io.FileNotFoundException;
import java.io.IOException;

//
// Tee yksikkötestejä lisää (kaikkia metodeja testataan 3:ssa eri tapauksessa)

// 1) Oikeanlainen toimiva syöte
// 2) Vääränlainen syöte (esim. keko joutuu tuplaantumaan tms.)
// 3) Rajatapaukset

public class Main {
    
    public static void main(String[] args) {
        PakkaamisenOhjaaja ohjaaja = new PakkaamisenOhjaaja();
        
        try {
            ohjaaja.suoritaPakkaaminen("huffman.txt");
        }
        catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } 
        catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
