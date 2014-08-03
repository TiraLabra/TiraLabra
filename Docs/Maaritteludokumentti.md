##Aihe: Nopein reitti verkossa kahden pisteen välillä

Harjoitustyössäni tulen toteuttamaan tietorakenteena minimikeon sekä algoritmeina Dijkstran algoritmin ja A* -algoritmin.

Tarkoituksena on ratkaista kahden pisteen välinen nopein reitti "ruudukossa", jossa voi siirtyä vaaka- tai pystysuuntaan. Ruudukossa voi olla myös esteitä joiden läpi/yli ei voi kulkea. Siirtymiseen ruudusta toiseen kuluu 1 aikayksikkö. Ensimmäiseksi algoritmiksi valitsin Dijkstran algoritmin koska sillä voi ratkaista kaikki ruudukon kahden pisteen väliseen siirtymiseen kuluvat ajat. Dijkstran algoritmi toimii kaaripainollisille verkoille. Näinollen esteet on toteutettavissa antamalla ruudukon ruuden arvoksi "ääretön" esteiden kohdalla. Mahdollisia ratkaistavan ongelman laajennuksia (ks. alla) varten on myös hyvä että Dijkstran algoritmi toimii suuntaamattomien verkkojen lisäksi suunnatuille verkoille.

Dijkstran algoritmin saa toimimaan tehokkaasta käyttämällä aputietorakenteena minimikekoa.

Vertailtavaksi algoritmiksi valitsin A* -algoritmin koska sen voi ajatella olevan Dijkstran algoritmin laajennus, jolla voi päästää ongelman nopeampaan ratkaisuun. Heurestiikkafunktiona tulen ainakin kokeilemaan Manhattan-etäisyyttä, jonka on helppo osoittaa olevan monotoninen, mikä on edellytyksenä sille että A* löytää varmasti (lyhimmän/)nopeimman reitin.

Mahdollisia laajennuksia:
- vaikeakulkuinen alue: kustannus enemmän kuin 1 aikayksikkö
- lentokentät joiden välillä voi lentää käyttäen tietyn määrän aikayksiköitä
- tunnelit, joita pitkin pystyy siirtymään esteiden läpi
- joki jota pitkin pääsee liikkumaan vain tiettyyn suuntaan... ??

Ohjelman saa lähtötietona 2-ulotteisen taulukon. Vierekkäisten "ruutujen" välillä voi siirtyä pysty- ja vaakasuuntaan siten että ruutuun (taulukon kohtaan) siirtyminen vie ruudun arvon verran aikaa. Esim. esteiden arvoksi asetetaan integermax.

Molemmilla algoritmeilla tavoitellaan aikavaativuutta O ( (n+m) * log n ). Tilavaatimus on O (n).





