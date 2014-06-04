package Toteutus;

import Toteutus.Huffman.Pakkaaminen.PakkaamisenOhjaaja;
import Toteutus.Huffman.Purkaminen.TiedostonPurkaja;
import java.io.IOException;

//
// Tee yksikkötestejä lisää (kaikkia metodeja testataan 3:ssa eri tapauksessa)

// 1) Oikeanlainen toimiva syöte
// 2) Vääränlainen syöte (esim. keko joutuu tuplaantumaan tms.)
// 3) Rajatapaukset

public class Main {
    
    public static void main(String[] args) throws IOException {
//        PakkaamisenOhjaaja ohjaaja = new PakkaamisenOhjaaja();
//        ohjaaja.suoritaPakkaaminen("abcdaa.txt");
        
        
        TiedostonPurkaja purkaja = new TiedostonPurkaja();
        purkaja.pura("abcdaa.txt");
    }
}



// 00010111001100
// b c  g f a  b

// Huffman puun tekstiesitys suoraan "BittiEsityksistä".

//     a = 011
//     b = 00
//     c = 010
//     f = 10
//     g = 11

// Jonoa ei tarvitse toteuttaa laisinkaan. StringBuilderia saa käyttää.
// Purkaminen tapahtuu siten että luetaan Huffman puu (= Bittiesitykset) läpi, viedään ne hajautustauluun (bittijono --> char) ja
// tämän jälkeen puretaan tiedosto lukemalla bitti bitiltä. Jos ei löydy vastinetta haj. taulusta, luetaan lisää.

// Jos Huffman puun tallettaa tiedoston alkuun loppuun, ei tarvitse muodostaa pointteria laisinkaan.
// Puu kannattaa tallettaa siten että jokaisen merkin perään lisätään tavuja 00 ja 01 (vastaamaan 0 ja 1 bittejä siitä, millainen esitys merkillä on). Ei vie liikaa tilaa :)
// Puu päättyy siten että sen lopussa on esim. FF FF koska jokaista merkkiä vastaava seur. merkki pitäisi olla 00 tai 01.