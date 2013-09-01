#Käyttöohjeet

##Miten ohjelma suoritetaan?
Ohjelma käännetään käyttäen komentoa "mvn install" Tiralabra_maven kansiossa. Tämä luo kansion sisään target-hakemiston, josta löytyy tiedosto Tiralabra_maven-1.0-jar-with-dependencies.jar. Tämä tiedosto on mahdollista käynnistää komennolla "java -jar (tiedosto)".
Toinen vaihtoehto on käyttää käskyä "mvn exec:java" Tiralabra_maven hakemistossa. Ohjelmisto vaatii vähintään 1.6 jren.


##Ohjelman syötteet

###Puiden alustus
####Puiden valinta:
Ohjelma kysyy mitä puita käyttäjä haluaa testata, valinnat syötetään "tageinä":

AVL = AVL-hakupuu

BIN = Binäärihakupuu (ei tasapainotettu)

SP = Splaypuu

RB = Punamustapuu

Tägit voidaan erotella pilkuilla tai välilyönnillä

#### Puun koko:
Ohjelma kysyy kuinka monta solmua puuhun lisätään, numeron täytyy olla suurempi kuin 0, jotta syötteellä voidaan testata operaatioiden kestoa.

#### Järjestys:
Ohjelma kysyy halutaanko solmujen arvot järjestykseen vai lisätyt luvut arvotusti. Ohjelma haluaa boolean-arvon, eli true tai false. True vastaa järjestettyä puuta ja false arvottua.

#### Arvojen välit (Vain jos luvut arvotaan)
Ohjelma kysyy miltä väliltä puun solmut arvotaan. Tämä mahdollistaa pelkästään saman luvun lisäämisen. Syötteen tulee kuitenkin olla suurempi kuin 0.

Ohjelma palauttaa tulostuksen alustuksien kestosta. Virheen sattuessa ohjelma aloittaa alustuksen alusta. Jos ohjelma kaatuu suurilla syötteillä, kannattaa kokeilla komentoa 'export MAVEN_OPTS="-Xmx(Keskusmuistimäärä)m -Xms(Keskusmuistimäärä/4)m"' ja sitten "mvn exec:java" Tiralabra_maven hakemistossa

###Puun operaatiot
Onnistuneen puiden alustuksen jälkeen ohjelma kysyy mitä puille halutaan tehdä. Käyttäjän on mahdollista syöttää operaatiot: hae, poista, lisaa ja nollaa. Tyhjä syöte tai virheellinen syöte lopettaa ohjelman.

####Hakeminen
Hakuoperaatio tapahtuu käskyllä hae. Ohjelma kysyy kuinka montako kertaa eri arvottuja avaimia haetaan. Arpominen tapahtuu kahdella tavalla. Jos luvut ovat arvottu niin ohjelma käyttää alustuksessa annettua väliä. Jos taas luvut ovat suorassa järjestyksessä niin ohjelma arpoo 0 - alkioiden määrä-1 lukuja.
####Poistaminen
Poistaminen tapahtuu käskyllä poista. Ohjelma kysyy montako kertaa eri arvottuja avaimia poistetaan. Ohjelma arpoo avaimet samanlailla kuin hakemisessa. 
####Lisääminen
Lisääminen tapahtuu käskyllä lisaa. Ohjelma kysyy montako kertaa eri arvottuja avaimia lisätään. Ohjelma arpoo avaimet samanlailla kuin hakemisessa.
####Nollaaminen
Nollaaminen tapahtuu käskyllä nollaa. Tämä käsky poistaa nykyiset puut ja alottaa ohjelman suorituksen alusta.
