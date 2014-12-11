Graafinen käyttöliittymä

Huom! Käyttöliittymä on vähän sekava, mutta jokseenkin toimiva. Ei toimi välttämättä oikein muilla kuin Windows-koneilla.

Satunnainen verkko

Oletusnäkymänä on satunnaisesti generoitu verkko. Vasemmalla hiirenpainikkeella asetetaan lähtösolmu ja oikealla maalisolmu. Jos arvot ovat kelvollisia, reitti lasketaan ja piirretään. Kuljettu reitti piirretään punaisella ja solmut joissa on käyty haun aikana värjätään vaaleanpunaisella. Laskimen tyypin voi vaihtaa: leveyssuuntainen haku ja heuristinen haku.

Uusi satunnainen verkko luodaan Haku-valikosta painikkeella Satunnainen verkko. Ponnahdusikkunaan annetaan syöte, jossa parametrit on eroteltu kaksoispisteellä:

rivit:sarakkeet:minimiPaino:kerroinPaino:tyyppi:liikkuminen

Valinta minimiPaino on pienin kustannus, jolla solmuun voi päästä, kerroinPainoon satunnaisluvun kerroin. Kustannus on siis a+b*X oletusarvoisesti. Valinnalla tyyppi on kolme arvoa: 0 verkon painot pyöristetään kokonaislukuihin, 1 painot ovat desimaalilukuja, 2 painoissa käytetään lisäksi katkaisua: vain jos satunnaisluvun arvo ylittää rajan, lisätään minimiPainoon b*X, lisäksi jos satunnaisluku on riittävän suuri, asetetaan paino niin korkeaksi että se muodostaa käytännössä esteen verkkoon. Valinta liikkuminen voi saada kaksi arvo: arvolla 1 liikkua voi vain koordinaattiakselien suuntaan, muutoin liikkua voi myös sivuttain. Syötteen ei tarvitse sisältää mitään näistä arvoista. Oletusarvoisesti luodaan 300x300-verkko, joka sisältää esteitä ja jossa voi liikkua vain akselien suuntaisesti. Tämä vastaa syötettä 300:300:1:2:2:1. 

Syötteen arvoja ei varsinaisesti tarkasteta. Valinnan minimiPaino tulisi olla arvoltaan vähintään yksi ja valinnan kerroinPaino tulisi olla epänegatiivinen. Valintojen rivit ja sarakkeet pitää myös olla positiivisia.

Verkko, joka ei mahdu ruudulle voi luoda. Tällöin käyttöliittymästä tehdyt haut eivät välttämättä toimi oikein, jos ikkunaa skrollataan. 

Suuria verkkoja luodessa verkon piirtäminen hidastuu.

Pysäkkiverkko

Haku-valikosta valitsemalla Pysäkkiverkko vaihtuu näkymäksi HSL:n raitiovaunukartta. Hakuja voi tehdä samoin kuin satunnaisellakin verkolla ja laskimen voi valita.

Testejä

Haku-valikosta valitsemalla Testejä suoritetaan satunnaisia hakuja valitussa verkossa. Avautuvassa kehotteessa valitaan kaksi parametria:

otosKoko:testienLkm

Valinta otosKoko määrää, kuinka monta satunnaista reittihakua tehdään. Valinta testienLkm määrää, kuinka monta  kertaa kukin haku suoritetaan. Valinnat voi jättää tyhjäksi, jolloin suoritetaan 20 satunnaista hakua, jokainen 10 kertaa. Tämä vastaa syötettä 20:10.
 
Suoritusajat lasketaan, jonka jälkeen ohjelma piirtää kuvan: vaaka-akselilla on kuljetun reitin pituus, pystyakselilla kulunut aika millisekunteina. Isoilla verkoilla ja otoskoolla tässä saattaa kestää huomattavan pitkään: samoin voi olla, että ruutuun ei mahdu kaikkia pisteitä. Komentoriville tulostuu lisäksi tabulaattorilla reitin pituus, eroteltuna aika ja käsiteltyjen solmujen lukumäärä jonka voi kopioida sieltä vaikkapa taulukkolaskimeen analyysiä varten.