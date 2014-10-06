##Testausdokumentaatio

###Mitä on testattu?
Ohjelmistolle on luotu JUnit-testit, jotka testaavat ohjelmiston logiikkaa.
Kaikki puut pystyvät myös esijärjestyksessä tulostamaan rakenteensa. Rakenteiden oikeellisuutta 
on testattu käsin piirtämällä puita ja hyödyntämällä visualisaattoreita eri lähteissä.
Projektille on myös määritelty cobertura plugin, millä pyritään varmistamaan jokaiselle
luokalle mahdollisimman suuri rivi kattavuus. Myös muihin cobertura tuloksiin on kiinnitetty huomiota.
Coberturan rinnalla toimii myös Pit, joka antaa samantyyppistä palautetta testeistä
kuin cobertura. Pit otettu käyttöön, jottei palautetta ole vain yhdestä lähteestä.
Tämä parantaa koodin toimivuuden vakuuttavuutta.

####Solmu
Solmu-luokkaa on testattu käytännössä vain yksikkötesteissä luokan yksinkertaisuuden vuoksi.
Luokka ei toteuta mitään algoritmeja, niin yksinkertainen koodi vaatii yksinkertaiset testit.
Käsitestauksessa hiukan etsitty rajoja Solmun arvon määrittelylle.

####Binäärinenhakupuu

####AVL-puu

####Splay-puu

####PunaMustaPuu

###Minkälaisilla syötteillä testaus tehtiin.
Käsitestauksessa on hyödynnetty erilaisia syötteitä. Yksikkötesteissä lähinnä String olioita ja int datatyyppejä yksinkertaisuuden vuoksi.
Erikois syötteet myös huomioitu tapauskohtaisesti. Rasitustestauksessa lähinnä keskitetty
määrään eikä sisältöön.

###Miten testit voidaan toistaa?
Ensin on edettävä projektin juureen "Tiralabra_maven".
Komento "mvn test" testaa projektin. Coberturan saa komennolla "mvn cobertura:cobertura".
Pit saadaan komennolla "mvn org.pitest:pitest-maven:mutationCoverage"
