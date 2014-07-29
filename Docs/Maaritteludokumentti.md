
Tämä dokumentti määrittelee ongelman "mahjong-käden shanten-arvon laskeminen".

Johdanto-kappaleessa kerrotaan minimaaliset taustatiedot ongelman
ymmärtämiseksi. Lukija joka on sinut (japanilaisen) mahjongin ja muutamien
termien kanssa (tenpai, shanten) voi haluta ohittaa sen.

# Johdanto

(Riichi) mahjong on neljän ihmisen pokeria muistuttava peli, jota pelataan
136:lla tiilellä. Jokaista tiiltä on neljä kappaletta, eli erilaisia tiiliä on
yhteensä 34: kolme maata joissa kussakin numerotiilet 1-9, neljä
ilmasuuntatiiltä (itä, etelä, länsi, pohjoinen) ja kolme lohikäärmetiiltä.

Mahjongin laajahkoa pelimekaniikkaa ei tarvitse osata ongelman ymmärtämiseksi,
ainoastaan mitä tässä johdannossa kerrotaan kädestä.

## Mahjong-käsi

Valmis mahjong-käsi koostuu (vähintään) 14 **tiilestä**, jotka muodostavat
(yleensä) 4 **settiä** ja yhden **parin**.

Settejä on kolmea eri tyyppiä.

1. Kolmikko: kolme samaa tiiltä (**koutsu**), esimerkiksi:

   ![](http://upload.wikimedia.org/wikipedia/commons/4/4a/MJt1.png)
   ![](http://upload.wikimedia.org/wikipedia/commons/4/4a/MJt1.png)
   ![](http://upload.wikimedia.org/wikipedia/commons/4/4a/MJt1.png)

2. Nelikko: neljä samaa tiiltä (**kantsu**):
   
   ![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)
   ![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)
   ![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)
   ![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)

3. Suora: Kolmen tiilen suora (**shuntsu**):

   ![](http://upload.wikimedia.org/wikipedia/commons/1/1c/MJw1.png)
   ![](http://upload.wikimedia.org/wikipedia/commons/c/c3/MJw2.png)
   ![](http://upload.wikimedia.org/wikipedia/commons/9/9e/MJw3.png)

Pari (**jantou**) muodostuu mistä vain kahdesta samanlaisesta tiilestä.

Esimerkki valmiista kädestä:

![](http://upload.wikimedia.org/wikipedia/commons/1/1c/MJw1.png)
![](http://upload.wikimedia.org/wikipedia/commons/1/1c/MJw1.png)
![](http://upload.wikimedia.org/wikipedia/commons/1/1c/MJw1.png)
![](http://upload.wikimedia.org/wikipedia/commons/e/ed/MJw5.png)
![](http://upload.wikimedia.org/wikipedia/commons/e/ed/MJw5.png)
![](http://upload.wikimedia.org/wikipedia/commons/e/ed/MJw5.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/df/MJs4.png)
![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)
![](http://upload.wikimedia.org/wikipedia/commons/c/cb/MJs6.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/dc/MJf3.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/dc/MJf3.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/dc/MJf3.png)
![](http://upload.wikimedia.org/wikipedia/commons/1/1b/MJd1.png)
![](http://upload.wikimedia.org/wikipedia/commons/1/1b/MJd1.png)

Käden setit ovat: 1-man koutsu, 5-man koutsu, 456-bambu shuntsu, länsikolmoset
ja pari punaisia lohikäärmeitä.

## Tenpai

Mahjong muistuttaa hyvin paljon pokeria: pelaajat aloittavat satunnaisella
kädellä, jossa on 13 tiiltä. Pelaaja muokkaa kättään aina yksi tiili kerrallaan.
Hän nostaa käteensä yhden tiilen lisää muurista ja heittää yhden tiilen pois
(voi olla myös juuri nostettu tiili), tavoitteenaan päästä lähemmäs valmista
kättä.

Valmiiseen käteen vaaditaan 14 tiiltä, mutta pelatessa kädessä on vain 13
tiiltä. Käsi valmistuu, kun sopiva 14. tiili lisätään ja käsi paljastetaan
muille. Eli kun pelaaja nostaa (tai huutaa) käden viimeistelevän
14. tiilen, hän ei heitä sitä pois vaan julistaa voittonsa ja paljastaa kätensä.

Kun käsi voi valmistua lisäämällä siihen jokin tiili, sanotaan että
käsi (tai pelaaja) on **tenpai**, yhden päässä voitosta.

Tenpai-käsi, joka voi voittaa joko 4- tai 7-bambulla:

![](http://upload.wikimedia.org/wikipedia/commons/1/1c/MJw1.png)
![](http://upload.wikimedia.org/wikipedia/commons/1/1c/MJw1.png)
![](http://upload.wikimedia.org/wikipedia/commons/1/1c/MJw1.png)
![](http://upload.wikimedia.org/wikipedia/commons/e/ed/MJw5.png)
![](http://upload.wikimedia.org/wikipedia/commons/e/ed/MJw5.png)
![](http://upload.wikimedia.org/wikipedia/commons/e/ed/MJw5.png)
![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)
![](http://upload.wikimedia.org/wikipedia/commons/c/cb/MJs6.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/dc/MJf3.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/dc/MJf3.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/dc/MJf3.png)
![](http://upload.wikimedia.org/wikipedia/commons/1/1b/MJd1.png)
![](http://upload.wikimedia.org/wikipedia/commons/1/1b/MJd1.png)

## Shanten

Kun tenpailla tarkoitetaan, että käsi on yhden päässä voitosta, niin
***n*-shanten** tarkoittaa että käsi on *n* tiilen päässä voitosta.

# Työn tavoitteet

Työn päätavoitteena on toteuttaa, analysoida ja testata algoritmeja, jotka
selvittävät mielivaltaiselle mahjong-kädelle...

- sen setit ja mahdolliset setit
- shanten-arvon
- odotukset, eli tiilet jotka pienentävät käden shanten-arvoa.

Toteutuskohteina ovat nämä algoritmit ja niiden käyttämät tietorakenteet ja
algoritmit.  Lopuksi saatetaan pohtia odotusten painottamista esim. niiden
tuovien fu-pisteiden suhteen.

# Asetetut rajoitteet

Työ toteutetaan staattisesti tyypitetyllä puhtaalla funktionaalisella
ohjelmointikielellä Haskell. Kurssin tavoitteiden mukaisesti ainoat käytetyt
valmiit tietorakenteet ovat primitiivit ja linkitetyt listat. Muut
(apu)-tietorakenteet ja -algoritmit toteutetaan itse, erityisesti tietotyyppi
käden esitykselle.

# Käden tietorakenne

Käden esitykselle on algoritmien kannalta olennaista, että tiilet voidaan
eritellä tyypin perusteella ja käydä tehokkaasti läpi järjestyksessä.

# Algoritmi settien ja mahdollisten settien määrittämiseen

Tämä on shanten-algoritmien (alla) ja odotustiilten määrittämistä varten
oleellinen algoritmi.

**Syöte:** mielivaltainen mahjong-käsi
<br>
**Tuloste:** käden setit ja/tai mahdolliset setit (Huom! mahdollisuuksia voi olla monta)
<br>
**Avut:** Algoritmi tiilten järjestämiseen maan ja numeron perusteella, ja tähän
sopiva tietorakenne (minimikeko?).

Miten mielivaltaisesta kokoelmasta mahjong-tiiliä saadaan selville kolmikot,
nelikot, suorat ja pari? Entä ne tiiliparit, joista muodostuisi setti
korvaamalla jokin muu tiili jollain toisella tiilellä?

Ongelma on itseasiassa aika mielenkiintoinen, sillä jako (kandidaatti)setteihin
ei aina ole yksikäsitteinen, sillä esimerkiksi seuraavat tiilet muodostavat
kaksi koutsua (444 ja 555) ja parin (66), tai kaksi shuntsua (456) ja yhden
shuntsu-odotuksen (45):

![](http://upload.wikimedia.org/wikipedia/commons/d/df/MJs4.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/df/MJs4.png)
![](http://upload.wikimedia.org/wikipedia/commons/d/df/MJs4.png)
![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)
![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)
![](http://upload.wikimedia.org/wikipedia/commons/b/bf/MJs5.png)
![](http://upload.wikimedia.org/wikipedia/commons/c/cb/MJs6.png)
![](http://upload.wikimedia.org/wikipedia/commons/c/cb/MJs6.png)

# Algoritmi shanten-arvon määrittämiseen

**Syöte:** Mielivaltainen mahjong-käsi.
<br>
**Tuloste:** Shanten-arvo.
<br>
**Avut:** (kandidaatti)-settien määrittäminen.

## Vähennysmenetelmä


Vähennysmenetelmä on varsin nerokas, ihmiselle nopea ja helppo tapa shantenin
laskemiseen. Se on kuvattu tässä [StackOverflow-vastauksessa] [vahennys].
Lyhyesti:

> Luvusta 8 vähennetään 2 jokaista valmista settiä kohden ja 1 jokaista yhtä
> vaille valmista settiä kohden. Jokainen tiili saa liittyä vain yhteen
> vähennykseen.

Ei ole aivan ilmiselvää, että vähennysmenetelmä todellakin toimii aina, ja olisi
mielenkiintoista nähdä formaali todistus että näin todellakin on.
Intuitiivisesti menetelmä tuntuu korrektilta.

[vahennys]: http://boardgames.stackexchange.com/questions/11877/how-can-i-quickly-calculate-the-shanten-number-in-mahjong#answer-13592
[hajong]:   http://github.com/SimSaladin/hajong
