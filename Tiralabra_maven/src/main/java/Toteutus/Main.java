package Toteutus;

import Toteutus.Huffman.Purkaminen.TiedostonPurkaja;
import java.io.IOException;

//
// Tee yksikkötestejä lisää (kaikkia metodeja testataan 3:ssa eri tapauksessa)

// 1) Oikeanlainen toimiva syöte
// 2) Vääränlainen syöte (esim. keko joutuu tuplaantumaan tms.)
// 3) Rajatapaukset

public class Main {
    
    public static void main(String[] args) throws IOException {
        TiedostonPurkaja purkaja = new TiedostonPurkaja();
        purkaja.pura("arrow.txt.hemi");
    }
}
