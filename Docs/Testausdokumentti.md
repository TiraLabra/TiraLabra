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
Kaikki yksikkötestit löytyvät projektin juuren src haaran test haarasta. Test-kansion lehdet ovat JUnit-testejä.

####Solmu
Solmu-luokkaa on testattu käytännössä vain yksikkötesteissä luokan yksinkertaisuuden vuoksi.
Luokka ei toteuta mitään algoritmeja, niin yksinkertainen koodi vaatii yksinkertaiset testit.
Käsitestauksessa hiukan etsitty rajoja Solmun arvon määrittelylle.

####Binäärinenhakupuu
Testiin on määritelty kaksi valmista oliota Binäärisestä hakupuusta. Testeissä on pyritty testaamaan kaikki metodit.
Rivikattavuuteen on kiinnitetty erityistä huomiota ja tästä syystä on pyritty testaamaan
metodien ehtolauseiden eri tilanteita. Tulosta-metodilla on tarkistettu puun rakennetta. 

####AVL-puu
Testit toimivat hyvinkin samaan tapaan kuin Binäärisellä hakupuulla. Erona on suurempi huomio puun rakenteessa
ja sen muutoksissa. Puu on itseääntasapainoittava, joten puun rakenne muuttuu useammin ja enemmän kuin binäärisellä hakupuulla. Tasapainoitus toteutetaan puuta kääntämällä. Testeissä on huomioitu käännösten toiminnan tarkistaminen.
Puussa esitellään myös metodi, joka päättelee suuremman kahdesta annetusta arvosta. Muut puut eivät tarvitse vastaavaa toiminnallisuutta, joten se on määritelty AVL-puussa ja testattu sen testeissä. 

####Splay-puu
Splay-puun toiminnallisuus poikkeaa eniten kaikista puista. Puun rakenne voi vaihdella hyvinkin paljon.
Esimerkiksi useamman arvon lisäys aiheuttaa puun rakenteen muistuttamaan pikemmin linkitettyä listaa kuin puuta. Kuten AVL-puussa on Splay-puun rakenteen muutoksiin
kiinnitetty eniten huomiota testeissä. Erityisesti poisto-operaatiossa, mikä tuntuu aiheuttavan puun rakenteella suurimpia muutoksia.

####PunaMustaPuu
PunaMustaPuu on AVL-puun tapaan itseääntasapainoittava. Tasapainotila päätellään kuitenkin eri tavalla kuin AVL-puussa. 
Erikoisuus puussa on sisarsolmun päättelevä metodi, minkä toiminnallisuutta on testattu.

###Minkälaisilla syötteillä testaus tehtiin.
Käsitestauksessa on hyödynnetty erilaisia syötteitä. Yksikkötesteissä lähinnä String olioita ja int datatyyppejä yksinkertaisuuden vuoksi.
Erikoissyötteet myös huomioitu tapauskohtaisesti. Rasitustestauksessa lähinnä keskitetty
määrään eikä sisältöön.

###Miten testit voidaan toistaa?
Ensin on edettävä projektin juureen ```Tiralabra_maven```.
Komento ```mvn test``` testaa projektin. Cobertura-raportti saadaan komennolla ```mvn cobertura:cobertura```.
Pit-raportti saadaan komennolla ```mvn org.pitest:pitest-maven:mutationCoverage```.
