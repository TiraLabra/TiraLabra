##Aihe: Nopein reitti verkossa kahden pisteen välillä

Harjoitustyössäni toteutan tietorakenteina minimikeon ja pinon sekä algoritmeina Dijkstran algoritmin ja A* -algoritmin.

Tarkoituksena on ratkaista kahden pisteen välinen nopein reitti "ruudukossa", jossa voi siirtyä vaaka- tai pystysuuntaan. Ruudukossa voi olla myös esteitä joiden läpi/yli ei voi kulkea. Siirtymiseen ruudusta toiseen kuluu 1 aikayksikkö. Ensimmäiseksi algoritmiksi valitsin Dijkstran algoritmin koska sillä voi ratkaista siirtymiseen kuluvat ajat ruudukon yhdestä pisteestä kaikkiin muihin pisteisiin eikä pelkästään kahden tietyn pisteen väliseen siirtymiseen kuluvaan aikaan tunneta nopeampaa ratkaisua. Dijkstran algoritmi toimii kaaripainollisille verkoille. Näinollen esteet on toteutettavissa antamalla ruudukon ruuden arvoksi "ääretön" esteiden kohdalla. Mahdollisia ratkaistavan ongelman laajennuksia (ks. alla) varten on myös hyvä että Dijkstran algoritmi toimii suuntaamattomien verkkojen lisäksi suunnatuille verkoille.

Dijkstran algoritmin saa toimimaan tehokkaasta käyttämällä aputietorakenteena minimikekoa.

Vertailtavaksi algoritmiksi valitsin A* -algoritmin koska sen voi ajatella olevan Dijkstran algoritmin laajennus, jolla voi päästää ongelman nopeampaan ratkaisuun. Heurestiikkafunktiona käytän Manhattan-etäisyyttä, jonka on helppo osoittaa olevan monotoninen, mikä on edellytyksenä sille että A* löytää varmasti (lyhimmän/)nopeimman reitin.

Mahdollisia laajennuksia:
- vaikeakulkuinen alue: kustannus enemmän kuin 1 aikayksikkö (TOTEUTETTU: vaikeakulkuiseen harmaaseen ruutuun siirtymiseen kuluu 5 aikayksikköä)
- muut heurestiikkafunktiot
- lentokentät joiden välillä voi lentää käyttäen tietyn määrän aikayksiköitä
- tunnelit, joita pitkin pystyy siirtymään esteiden läpi
- joki jota pitkin pääsee liikkumaan vain tiettyyn suuntaan

Ohjelman saa lähtötietona bmp-kuvatiedoston, joka muutetaan 2-ulotteiseksi kokonaislukutaulukoksi. Vierekkäisten "ruutujen" välillä voi siirtyä pysty- ja vaakasuuntaan siten että ruutuun (taulukon kohtaan) siirtyminen vie ruudun arvon verran aikaa. Esim. esteiden arvoksi asetetaan integermax/10.

Molemmilla algoritmeilla tavoitellaan aikavaativuutta O ( (n+m) * log n ). Tilavaatimus on O (n).

Lähteet:
Kurssin "58131 Tietorakenteet ja algoritmit" luentomateriaali
Kevät 2014
Patrik Floréen





