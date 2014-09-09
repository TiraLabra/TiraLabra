## Tiralabra / Hannu Honkanen, periodi 6/2014

## M��rittelydokumentti
**Aihe:** Nopein reitti verkossa kahden pisteen v�lill�

Harjoitusty�ss�ni toteutan tietorakenteina minimikeon ja pinon sek� algoritmeina Dijkstran algoritmin ja A* -algoritmin.

Tarkoituksena on ratkaista kahden pisteen v�linen nopein reitti "ruudukossa", jossa voi siirty� vaaka- tai pystysuuntaan. Ruudukossa voi olla my�s esteit� joiden l�pi/yli ei voi kulkea. Siirtymiseen ruudusta toiseen kuluu 1 aikayksikk�. Ensimm�iseksi algoritmiksi valitsin Dijkstran algoritmin koska sill� voi ratkaista siirtymiseen kuluvat ajat ruudukon yhdest� pisteest� kaikkiin muihin pisteisiin eik� pelk�st��n kahden tietyn pisteen v�liseen siirtymiseen kuluvaan aikaan tunneta nopeampaa ratkaisua. Dijkstran algoritmi toimii kaaripainollisille verkoille. N�inollen esteet on toteutettavissa antamalla ruudukon ruuden arvoksi "��ret�n" esteiden kohdalla. Mahdollisia ratkaistavan ongelman laajennuksia (ks. alla) varten on my�s hyv� ett� Dijkstran algoritmi toimii suuntaamattomien verkkojen lis�ksi suunnatuille verkoille.

Dijkstran algoritmin saa toimimaan tehokkaasta k�ytt�m�ll� aputietorakenteena minimikekoa.

Vertailtavaksi algoritmiksi valitsin A* -algoritmin koska sen voi ajatella olevan Dijkstran algoritmin laajennus, jolla voi p��st�� ongelman nopeampaan ratkaisuun. Heurestiikkafunktiona k�yt�n Manhattan-et�isyytt�, jonka on helppo osoittaa olevan monotoninen, mik� on edellytyksen� sille ett� A* l�yt�� varmasti (lyhimm�n/)nopeimman reitin.

Mahdollisia laajennuksia:
- vaikeakulkuinen alue: kustannus enemm�n kuin 1 aikayksikk� (TOTEUTETTU: vaikeakulkuiseen harmaaseen ruutuun siirtymiseen kuluu 5 aikayksikk��)
- muut heurestiikkafunktiot
- lentokent�t joiden v�lill� voi lent�� k�ytt�en tietyn m��r�n aikayksik�it�
- tunnelit, joita pitkin pystyy siirtym��n esteiden l�pi
- joki jota pitkin p��see liikkumaan vain tiettyyn suuntaan

Ohjelman saa l�ht�tietona bmp-kuvatiedoston, joka muutetaan 2-ulotteiseksi kokonaislukutaulukoksi. Vierekk�isten "ruutujen" v�lill� voi siirty� pysty- ja vaakasuuntaan siten ett� ruutuun (taulukon kohtaan) siirtyminen vie ruudun arvon verran aikaa. Esim. esteiden arvoksi asetetaan integermax/10.

Molemmilla algoritmeilla tavoitellaan aikavaativuutta O ( (n+m) * log n ). Tilavaativuus on O (n).

L�hteet: Kurssin "58131 Tietorakenteet ja algoritmit" luentomateriaali, Kev�t 2014, Patrik Flor�en





