##Testausdokumentaatio

###Mitä on testattu?
Ohjelmistolle on luotu JUnit-testit, jotka testaavat ohjelmiston logiikkaa.
Kaikki puut pystyvät myös esijärjestyksessä tulostamaan rakenteensa. Rakenteiden oikeellisuutta 
on testattu käsin piirtämällä puita ja hyödyntämällä visualisaattoreita eri lähteissä.
Projektille on myös määritelty cobertura plugin, millä pyritään varmistamaan jokaiselle
luokalle mahdollisimman suuri rivi kattavuus. Myös muihin cobertura tuloksiin on kiinnitetty huomiota.

####Solmu

####Binäärinenhakupuu

####AVL-puu

####Splay-puu

###Minkälaisilla syötteillä testaus tehtiin.

###Miten testit voidaan toistaa?
Ensin on edettävä projektin juureen "Tiralabra_maven".
Komento "mvn test" testaa projektin. Coberturan saa komennolla "mvn cobertura:cobertura".
