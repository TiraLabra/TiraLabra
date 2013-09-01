Ohjelman toimivuuden testaamisessa on käytetty JUnit testejä. Niillä katetaan iso osa perustoiminnallisuudesta sekä yksinkertainen peluutilanne, mutta käyttöliittymää ei ollenkaan (en osaa enkä näe sitä kovin tarpeellisena). 
Tärkeintä ohjelmassa on kuitenkin se, että tekoäly toimii ihmistä vastaan hyvin ja olen laittanut useita eri ihmisiä kokeilemaan peliä. 

* * *

JUnit testit voi suorittaa vaikka run_tests.sh:lla. Yksinlertaisia peluutilanteita suorittavilla testeillä on hyvin pieni mahdollisuus epäonnistua, vaikka ohjelma olisikin kunnossa. 

Käytännön testaamiseen voin olla tyytyväinen. Tuloksien perusteella arvioisin, että tyypillinen voittoprosentti tekoälyllä asettuu noin neljäänkymmeneen. Huonoimmassakin tapauksessa tekoäly voitti noin kolmanneksen. huomattavaa on kuitenkin se, jotta voidaan osoittaa tekoälyn toimivan oikeasti hyvin, niin tarvitaan paljon dataa. Toisaalta, jos yksi pelaaja pelaa pitkään ja tekoäly ottaa yliotteen pelkästään sen vuoksi, että pelaaja ei jaksa enää keskittyä. Tällöin voitto ei ole yhtä "ansaittu". 
