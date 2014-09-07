##Määrittelydokumentti

Valitsin Tietorakenteiden ja algoritmien harjoitustyön aiheeksi hakupuiden vertailun.
Toteutin neljä erilaista hakupuuta, joista kolmea ei käsitelty Tietorakenteet ja algoritmit-kurssilla.

Työn tavoitteena on vertailla puiden suorituskykyä ja rakenteiden eroja.
Tutkin myös miten puiden koko, syvyys ja mahdolliset muut muuttujat vaikuttavat puiden solmujen haun, lisäyksen ja poistojen aika- ja tilavaatimuksiin.

Valitsin tarkasteltavaksi binäärisiä hakupuita, joihin kuului perinteinen binäärinen hakupuu ja sen solvelluksia.
Teoreettisesti hakupuiden aikavaativuuksissa on pieniä eroja, mutta testaan puut suurilla syötteillä, jolloin erojen tulisi olla selvempiä.
Syötteinä käytetään satunnaisia lukuja, jotka on kirjattu taulukkoon. Luvut lisätään puihin, joista niitä etsitään ja poistetaan.

###Binäärinen hakupuu
Binäärisessä hakupuun vasemmassa alipuussa on pienempiä alkioita solmuissa, kuin oikeassa alipuussa.
Haun pahimman tapauksen aikavaativuus on O(n). Kuitenkin todennäköisempi aikavaativuus on luokkaa O(log n).
Alkion lisäys ja poisto ovat aikavaativuksiltaan haun kanssa samaa luokkaa. Puusta alkion poistaminen voi johtaa epätasapainoisuuteen.

###AVL-puu
AVL-puu on itseään tasapainottava binäärinen hakupuu. Puusta haun, lisäyksen ja poiston aikavaativuus pahimmassa tapauksessa on O(log n).
Alkion lisäys tai poisto puuhun saattaa aiheuttaa puun epätasapainon. Puu pitää kirjaa solmujen tasapainokertoimista, joilla päätellään onko puu tasapainossa vai ei.
Mikäli puu epätasapainoutuu, joudutaan puun solmuja kiertämään, että tasapaino säilyy.

###Punamusta puu
Punamusta puun aikavaativuus pahimmassa tapauksessa on O(log n) ja puu on itseään tasapainottava. Puun keksijä nimitti Punamusta puuta symmetrisiseksi binääripuuksi.
Puun solmuihin liittyy väri, joka voi olla punainen tai musta. Tällä tiedolla pidetään puu tasapainoisena.
Punamustan puun lehtien alkioiden tulee olla määrittelemättömiä eli arvoltaan null.

###Splay-puu
Splay-puu mukautuva hakupuu. Puun samoihin alkioihin kohdistuvat operaatiot ovat todella nopeita. Puun alkioiden haku-, lisäys- ja poisto-operaatioiden aikavaativuus on O(log n).
Jos puun käyttökohde on oikea, on se todella tehokas.

Lähteet: wikipedia.org
