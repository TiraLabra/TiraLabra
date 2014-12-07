Testausdokumentti

*Mit� on testattu, miten t�m� tehtiin
*Mink�laisilla sy�tteill� testaus tehtiin (vertailupainotteisissa t�iss� t�rk�t�)
*Miten testit voidaan toistaa
*Ohjelman toiminnan empiirisen testauksen tulosten esitt�minen graafisessa muodossa.
*Testaus on ideaalitapauksessa suoritettava ohjelma. T�ll�in testi on helposti toistettavissa, mik� helpottaa toteutuksen tekoa jo varhaisessa vaiheessa. On eritt�in suositeltavaa k�ytt�� testaukseen JUnitia.

Testit

* Itse toteutettujen tietorakenteiden (tira-paketti) kaikki julkiset metodit on yksikk�testattu, lukuunottamatta Pari-luokkaa. 

* AStar-haulla on muutama testi (haku-paketti): haku ylip��ns� toimii ja eri laskimet reiteille toimivat.

* App-luokassa (k�ynnistysluokka) on kattavasti debug-metodeja, joilla olen toteutusvaiheessa varmistanut luokkien toimivuutta.

* Useissa luokissa on lis�ksi mukana debug-metodeja, jotka ker��v�t ja tulostavat/palauttavat tietoa luokkien toiminnasta.

* App-luokan testik�ytt�liittym�ll� voi testata reitinhakua sek� pys�kkiverkossa ett� satunnaisgeneroidussa verkossa. Reittihaku suoritetaan aina kymmenen kertaa, ja suorituksen j�lkeen tulostetaan saatu reitti ja keskiarvo kuluneesta ajasta, pienin aika sek� suurin aika.

* Gui-luokan testik�ytt�liittym�ss� voi luoda uuden satunnaisen verkon sek� suorittaa hakuja pys�kki- ja satunnaisessa verkossa. Haun tulokset piirret��n.

Testien tuloksia: suorituskykytestausta

Kuvissa kuljettu reitti on punaisella, k�ydyt solmut vaaleanpunaisella, alkusolmu keltaisella ja loppusolmu vihre�ll�.

1. AStar-haku ja heuristiikka

Toistaminen:
Asetetaan aluksi k�ytt��n uusi satunnainen verkko sy�tteell� 300:300:1:2:2:1. Valitaan sitten laskimeksi BFS, suoritetaan haku ja sitten valitaan heuristinen laskin ja suoritetaan haku uudestaan.

Solmujen k�sittelyj�rjestyksen m��r�� niiden hyvyys g(n): reitin kustannus f(alku,n) ja arvioitu j�ljell� oleva kustannus h(n).

Heuristisen funktion h(n) arvo vaikuttaa haun toimintaan. Jos h(n)=0, saadaan leveyssuuntainen haku: 

 
Sama haku suoritettuna siten, ett� h(n)=|n.x-maali.x|+|n.y-maali.y|:
 
Solmuja ei ole siis k�sitelty niin paljoa turhaan. 

Mit� paremmin h(n) vastaa oikeaa j�ljell� olevaa kustannusta f(n,maali), sit� tehokkaammin haku toimii. T�t� voi kokeilla valitsemalla verkon siten, ett� minimiPaino on hyvin suuri.

Jos heuristinen arvio yliarvioi j�ljell� olevaa kustannusta, ei AStar-haku anna parasta mahdollista ratkaisua. Omassa toteutuksessani koko haku saattaa kaatua t�ll�in (johtuu k�sittelyj�rjestyksen toteutustavasta: nykysell��n vaatimuksena on, ett� g(n) ovat kasvavia k�sittelyj�rjestyksess� edetess�). T�m�n voi toistaa valitsemalla minimiPainon, joka on arvoltaan alle yksi.

2. AStar-haku ja verkon tiheys

Toistaminen: sy�tteell� 300:300:1:2:2:1 luotu verkko ja sy�tteell� 300:300:1:2:2:0 luotu verkko. Suoritetaan n�ill� testej�-painikkeen mukaiset testit.

Oletusarvona verkossa voi kulkea vain koordinaattiakselien suuntaisesti. Valinnalla 300:300:1:2:2:0 luotu verkko sallii liikkumisen my�s sivuittain.

Minimiaikavaatimus verkossa kulkemiseen on O(k^d), jossa k on naapureiden lukum��r� kullekkin solmulle ja d on reitin pituus. Sivuttaisen liikkumisen sallivassa verkossa naapureita on 8, vain akselien suuntaisen liikkumisen sallivassa verkossa 4. Annetulla reitin pituudella d minimiaikavaatimuksien suhde on siis 2^d. Jos sivuttaisuuntainen kulkeminen sallitaan, lyhyin mahdollinen reitti on puolet siit� jos kulkeminen estet��n. Kuitenkin suoritusajassa n�kyy selv� ero.

Vain akselien suuntaan:  
My�s sivusuunnassa:
 
 
500x500 verkossa 500 n�ytteell� ero korostuu entisest��n. Vain akselien suuntaan:

 
My�s sivusuunnassa:
 

3. Verkon ja polun koko

Toistaminen: luodaan sy�tteell� 1000:1000:1:2:2:1 verkko, jonka koko on 1000x1000. Nyt ohjelman piirt�m��n kuvaajaan ei mahdu kaikki pisteet. My�s kokonaissuoritusaika on melkoinen.

Suurilla verkoilla ja siten my�s pitkill� poluilla aikavaatimus kasvaa eksponentiaalisesti:

 
