##Aihe: Nopein reitti verkossa kahden pisteen v�lill�

Harjoitusty�ss�ni tulen toteuttamaan tietorakenteena minimikeon sek� algoritmeina Dijkstran algoritmin ja A* -algoritmin.

Tarkoituksena on ratkaista kahden pisteen v�linen nopein reitti "ruudukossa", jossa voi siirty� vaaka- tai pystysuuntaan. Ruudukossa voi olla my�s esteit� joiden l�pi/yli ei voi kulkea. Siirtymiseen ruudusta toiseen kuluu 1 aikayksikk�. Ensimm�iseksi algoritmiksi valitsin Dijkstran algoritmin koska sill� voi ratkaista kaikki ruudukon kahden pisteen v�liseen siirtymiseen kuluvat ajat. Dijkstran algoritmi toimii kaaripainollisille verkoille. N�inollen esteet on toteutettavissa antamalla ruudukon ruuden arvoksi "��ret�n" esteiden kohdalla. Mahdollisia ratkaistavan ongelman laajennuksia (ks. alla) varten on my�s hyv� ett� Dijkstran algoritmi toimii suuntaamattomien verkkojen lis�ksi suunnatuille verkoille.

Dijkstran algoritmin saa toimimaan tehokkaasta k�ytt�m�ll� aputietorakenteena minimikekoa.

Vertailtavaksi algoritmiksi valitsin A* -algoritmin koska sen voi ajatella olevan Dijkstran algoritmin laajennus, jolla voi p��st�� ongelman nopeampaan ratkaisuun. Heurestiikkafunktiona tulen ainakin kokeilemaan Manhattan-et�isyytt�, jonka on helppo osoittaa olevan monotoninen, mik� on edellytyksen� sille ett� A* l�yt�� varmasti (lyhimm�n/)nopeimman reitin.

Mahdollisia laajennuksia:
- vaikeakulkuinen alue: kustannus enemm�n kuin 1 aikayksikk�
- lentokent�t joiden v�lill� voi lent�� k�ytt�en tietyn m��r�n aikayksik�it�
- tunnelit, joita pitkin pystyy siirtym��n esteiden l�pi
- joki jota pitkin p��see liikkumaan vain tiettyyn suuntaan... ??

Ohjelman saa l�ht�tietona 2-ulotteisen taulukon. Vierekk�isten "ruutujen" v�lill� voi siirty� pysty- ja vaakasuuntaan siten ett� ruutuun (taulukon kohtaan) siirtyminen vie ruudun arvon verran aikaa. Esim. esteiden arvoksi asetetaan integermax.

Molemmilla algoritmeilla tavoitellaan aikavaativuutta O ( (n+m) * log n ). Tilavaatimus on O (n).





