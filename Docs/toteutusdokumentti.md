Projektin laajempi ongelma on kohtuullisen nopea tekoäly, joka etsii ruudukkoon sovitettavia käypiä sanoja. Lähtökohtana on täydellinen AI, jota voi myöhemmin heikentää inhimillisemmäksi. Apuna tähän on toteutettu hajautustaulukko sanojen tarkistamista varten. Jotta AI olisi nopea, on sanan löytäminen taulukosta myös oltava nopeaa.

Näiden ongelmien ratkaisuun liittyvät luokat ovat pääasiassa:
AiController
MyHashSet
HashFunction
DJB2ForStrings
PrimeNumberUtils

Lähdekoodista löytyy muitakin hashfunktio-toteutuksia, mutta 

Projektissa on pyritty etsimään hash-funktio, jolla saataisiin mahdollisimman vähän yhteentörmäyksiä, ja joka on kohtuullisen nopea käyttää. Funktiota käytetään String-objektien hash-arvojen laskemiseen Sanapuuro-pelissä.

Projektin alkuvaiheessa käytettiin hash-funktiota, joka oli toteutettu suoraan TirA-kalvon materiaaleista. Toteutuksessa käytettiin Javan BigInteger-luokkaa suurien numeroiden käsittelemiseksi.

Toinen toteutus samaisesta TirA-kalvojen algoritmista käyttää Javan long-primitiiviä BigIntegerien sijaan, joka on huomattavasti kevyempää laskennan kannalta. Kahdeksan kirjaimisten sanojen hash-arvot ilman moduloa ovat kokoluokkaa 10^16. Longin positiivinen on maksimiarvo kokoluokkaa 10^18, joten ylivuotoa ei tapahdu.
