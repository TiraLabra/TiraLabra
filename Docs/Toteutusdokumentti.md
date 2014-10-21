##Toteutusdokumentaatio

###Ohjelman yleisrakenne
Ohjelman rakenne on mavenin määrittelemä projektirakenne. Kaikki työssä 
implementoidut hakupuut hyödyntävät Solmu-luokkaa. Solmulle annetaan arvoksi Obejct olio, mistä päätellään solmun avain.
Solmujen viitteet toisiin solmuihin päätellään puihin määriteltyjen ehtojen mukaisesti. 

Hakupuut on toteutettu tiedostoihin ```BinaarinenHakupuu.java```, ```AVLpuu.java```, ```PunaMustaPuu.java``` ja ```SplayPuu.java```. Lisäksi on tiedosto ```HakupuuRajapinta.java``` rajapinnalle, jonne on
määritelty hakupuun operaatiot lisäys,haku ja poisto. AVL, punamusta ja splaypuu ovat binäärisen hakupuun aliluokkia, koska ne hyödyntävät, laajentavat tai tehostavat binäärisen hakupuun metodeja.

###Saavutetut aika- ja tilavaativuudet

###Suorituskyky ja O-analyysivertailu

###Puutteet ja parannusehdotukset
Perintä ei välttämättä ole paras mahdollinen tapa välttää toistuvan koodin kirjoittamista. Jos työssäni tulisi muutos binäärisen hakupuun koodiin, niin se vaikuttaisi kaikkiin sen periviin luokkiin. Tämä voi muuttaa perivän olion koodin käyttäytymisen vääränlaiseksi. Pahimmassa tapauksessa tulee korjata kaikki oliot, jotka perivät binäärisen hakupuun. Toinen lähestysmistapa olisi ollut kirjoittaa toistuvat metodit erilliseen luokkaan ja asettaa kaikki hakupuut suoraan implementoimaan rajapintaa.

###Lähteet
* wikipedia.org
* Tietorakenteet ja algoritmit -kurssin materiaali
* https://noppa.oulu.fi/noppa/kurssi/811312a/luennot/811312A_opiskelumateriaali_-_perustietorakenteet.pdf
