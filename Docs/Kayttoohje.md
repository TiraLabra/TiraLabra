Graafinen k�ytt�liittym�

Huom! K�ytt�liittym� on v�h�n sekava, mutta jokseenkin toimiva. Ei toimi v�ltt�m�tt� oikein muilla kuin Windows-koneilla.

Satunnainen verkko

Oletusn�kym�n� on satunnaisesti generoitu verkko. Vasemmalla hiirenpainikkeella asetetaan l�ht�solmu ja oikealla maalisolmu. Jos arvot ovat kelvollisia, reitti lasketaan ja piirret��n. Kuljettu reitti piirret��n punaisella ja solmut joissa on k�yty haun aikana v�rj�t��n vaaleanpunaisella. Laskimen tyypin voi vaihtaa: leveyssuuntainen haku ja heuristinen haku.

Uusi satunnainen verkko luodaan Haku-valikosta painikkeella Satunnainen verkko. Ponnahdusikkunaan annetaan sy�te, jossa parametrit on eroteltu kaksoispisteell�:

rivit:sarakkeet:minimiPaino:kerroinPaino:tyyppi:liikkuminen

Valinta minimiPaino on pienin kustannus, jolla solmuun voi p��st�, kerroinPainoon satunnaisluvun kerroin. Kustannus on siis a+b*X oletusarvoisesti. Valinnalla tyyppi on kolme arvoa: 0 verkon painot py�ristet��n kokonaislukuihin, 1 painot ovat desimaalilukuja, 2 painoissa k�ytet��n lis�ksi katkaisua: vain jos satunnaisluvun arvo ylitt�� rajan, lis�t��n minimiPainoon b*X, lis�ksi jos satunnaisluku on riitt�v�n suuri, asetetaan paino niin korkeaksi ett� se muodostaa k�yt�nn�ss� esteen verkkoon. Valinta liikkuminen voi saada kaksi arvo: arvolla 1 liikkua voi vain koordinaattiakselien suuntaan, muutoin liikkua voi my�s sivuttain. Sy�tteen ei tarvitse sis�lt�� mit��n n�ist� arvoista. Oletusarvoisesti luodaan 300x300-verkko, joka sis�lt�� esteit� ja jossa voi liikkua vain akselien suuntaisesti. T�m� vastaa sy�tett� 300:300:1:2:2:1. 

Sy�tteen arvoja ei varsinaisesti tarkasteta. Valinnan minimiPaino tulisi olla arvoltaan v�hint��n yksi ja valinnan kerroinPaino tulisi olla ep�negatiivinen. Valintojen rivit ja sarakkeet pit�� my�s olla positiivisia.

Verkko, joka ei mahdu ruudulle voi luoda. T�ll�in k�ytt�liittym�st� tehdyt haut eiv�t v�ltt�m�tt� toimi oikein, jos ikkunaa skrollataan. 

Suuria verkkoja luodessa verkon piirt�minen hidastuu.

Pys�kkiverkko

Haku-valikosta valitsemalla Pys�kkiverkko vaihtuu n�kym�ksi HSL:n raitiovaunukartta. Hakuja voi tehd� samoin kuin satunnaisellakin verkolla ja laskimen voi valita.

Testej�

Haku-valikosta valitsemalla Testej� suoritetaan satunnaisia hakuja valitussa verkossa. Avautuvassa kehotteessa valitaan kaksi parametria:

otosKoko:testienLkm

Valinta otosKoko m��r��, kuinka monta satunnaista reittihakua tehd��n. Valinta testienLkm m��r��, kuinka monta  kertaa kukin haku suoritetaan. Valinnat voi j�tt�� tyhj�ksi, jolloin suoritetaan 20 satunnaista hakua, jokainen 10 kertaa. T�m� vastaa sy�tett� 20:10.
 
Suoritusajat lasketaan, jonka j�lkeen ohjelma piirt�� kuvan: vaaka-akselilla on kuljetun reitin pituus, pystyakselilla kulunut aika millisekunteina. Isoilla verkoilla ja otoskoolla t�ss� saattaa kest�� huomattavan pitk��n: samoin voi olla, ett� ruutuun ei mahdu kaikkia pisteit�. Komentoriville tulostuu lis�ksi tabulaattorilla reitin pituus, eroteltuna aika ja k�siteltyjen solmujen lukum��r� jonka voi kopioida sielt� vaikkapa taulukkolaskimeen analyysi� varten.