
# src/ rakenne

- `Mahjong.Hand`: "Hand"-tietorakenne ja rajapinta.
   - `Mahjong.Hand.Mentsu`: Mentsut.
   - `Mahjong.Hand.Value`: Käden arvon laskeminen.
   - `Mahjong.Hand.Algo`: Mentsuihin jako, shantenin laskeminen, odotuspuu.
   - `Mahjong.Hand.Algo.WaitTree`: Odotuspuun tietorakenne.
- `Mahjong.Tiles`: "Tile"-tietotyyppi.

# Algoritmit

## tiles(Split)GroupL

Algoritmit `tilesGroupL, tilesSplitGroupL :: [Tile] -> [Grouping]` palauttavat
**kaikki** mahdolliset ryhmitykset annetuille tiilille.

Yksi ryhmitys (`Grouping`) koostuu tiiliryhmistä (`TileGroup`), jolle on kolme
konstruktoria: 1) pari tiiliä jotka odottavat jotain kolmatta ollakseen ryhmä 2)
valmis kolmen tiilen ryhmä 3) yksinäinen tiili.

(`TileGroup` on hieman harhaanjohtava nimitys, koska se sisältää odotukset ja
ylijäämät, mutta jokin almostatilegroup olisi pitkä ja vaikea.)

Huomataan, että koska näiden jakoalgojen on käsiteltävä myös tapaus jossa kaikki
tiilet katsotaan ylijäämiksi (onhan sekin yksi "ryhmitys"), on näiden
aikavaativuus lähes määritelmästä johtuen luokkaa O(n<sup>2</sup>).
Käsiteltäviä tiiliä on kuitenkin aina <= 14 (neljä samaa (kutsumaton kantsu)
tekevät pienen poikkeuksen), joten reaalitapauksissa neliöllisyys ei ole
ongelma. Algot käyttävät muutamaa apumuuttujaa, jotka ovat vakiokokoisia.
Tilavaativuus on siis vakio. Tuloslistan koko on toisaalta n<sup>2</sup>, mutta
sitä ei missään vaiheessa tarvitse pitää kokonaisuudessaan muistissa (eikä ~14
tiilellä koko ole päätä huimaava muutenkaan).

`tilesGroupL` käsittelee kaikki ryhmitystapaukset vasemmalta lähtien (siitä
L-suffiksi); tarkempi toiminta on kuvattu sen
[haddockissa](http://simsaladin.users.paivola.fi/TiraLabra/Mahjong-Hand-Algo.html#v:tilesGroupL).

### -Split-

`tilesSplitGroupL` on pieni parannus aiempaan, joka käsittelee tiilityypit
(eri maat ja honorit) erikseen käyttäen `tilesGroupL`:ää (siis klassinen hajoita
ja hallitse). Suurissa tapauksissa parannus olisi siis aikavaativuudessa 1/4
vakiokerroin, muistivaativuus on sama.

### Parannusehdotuksia

Jakoalgoritmien tuloksissa on duplikaatteja, joiden syy ja korjausehdotus on
annettu haddockissa. Ne eivät kuitenkaan vaikuta ryhmitysten oikeellisuuteen
(mutta aiheuttavat huomattavasti redundantteja haaroja odotuspuihin).

Implementoidut jakoalgoritmit palauttavat laiskan listan kaikista ryhmityksistä
järjestyksessä suosien montaa samaa ja shuntsuja edessä, ja orpoja yritetään
vasta viimeisenä. Tästä ei kuitenkaan oikeastaan ole mitään apua, koska joka
tapauksessa koko lista joudutaan käymään läpi jotta esim. tiedetään varmasti
käden shanten, esimerkiksi tapauksessa jossa kädessä on vain yksi orpo tiili -
sen ensimmäinen tiili.

Eräs parannus olisi aina suosia alemman shantenin ryhmityksiä, mutta tällaisen
lisääminen jakoalgoihin ei olisi sen arvoista. Nyt jakoalgot ovat yksinkertaisia
ja koostuu yhden ryhmityksen ottavista aliohjelmista jotka eivät riipu
toisistaan. Alemman shantenin suosiminen tarkoittaisi tästä luopumista, ja koska
nyt listaa voi käydä läpi laskemalla elementti kerrallaan on nykyinen ratkaisu
luultavasti myös nopeampi, mutta vähintään yksinkertaisempi.

Mutta jakoalgon tuloslistan yli voisi foldaa siten, että arvioi ryhmityksen
shantenia sitä prosessoidessa; kun ryhmityksestä on käsitelty osa, voidaan
sen shantenille antaa ylä- ja erityisesti alaraja evaluoimatta ryhmityksen
viimeisiä elementtejä ollenkaan. Mikäli ollaan kiinnostuneita vain pienimmän
shantenin ryhmityksistä, voidaan kyseinen ryhmitys heittää pois mikäli sen
alaraja on korkeampi kuin siihen mennessä prosessoitujen ryhmitysten minimi.

## Shanten

Shantenin laskeminen on toteutettu nk. vähennysmenetelmällä: Luvusta 8
vähennetään 2 jokaista valmista settiä kohden ja yksi jokaista kahden toisiinsa
liittyvää tiiltä kohden. Lopuksi tulokseen lisätään `max 0 (suorat + melkein
suorat - 4)`, koska kädessä voi olla korkeintaan 4 suoraa.
[Tarkempi selitys (groupingShanten)](http://simsaladin.users.paivola.fi/TiraLabra/Mahjong-Hand-Algo.html#v:groupingShanten).

Koska kädessä voi olla avoimia mentsuja, tai voidaan haluta laskea shanten
jollakin tietyllä ryhmityksellä jne. on `shanten`-funktio [ylikuormitetty
ShantenOf-luokassa](http://simsaladin.users.paivola.fi/TiraLabra/Mahjong-Hand-Algo.html#t:ShantenOf).

Huom. Aiemmassa jakoalgojen parannusehdotuksissa mainittu fold voisi nopeuttaa
shantenin laskemista.

**Bonus:** Shanten-arvojen jakauma 100000 satunnaiselle aloituskädelle.


![Shanten-arvojen jakauma 100000 satunnaiselle aloituskädelle](https://rawgit.com/SimSaladin/TiraLabra/master/Docs/shanten_distribution.svg)

Mielenkiintoista on, että 2- ja 3-shanten ovat vastaavasti yleisempiä kuin 6- ja
5-shanten, mutta 1-shanten ei ole yleisempi kuin 7-shanten, ja tenpai on
harvinaisempi kuin "8-shanten" (joka olisi itseasiassa chiitoitsu 7-shanten).
Alla on vielä taulukko simuloiduista arvoista.

    shanten  määrä
          0  5
          1  484
          2  6688
          3  24873
          4  37662
          5  23684
          6  6030
          7  563
          8  11

## buildGWT\*

`buildGWT`, `buildGWTs` ja `buildGWTs'` muodostavat odotuspuun/t, jossa jokainen
lehti on tenpai kädelle ja jokainen solmukohta jokin shantenia pienentävä tai
samana pitävä yksittäinen muutos käteen. Tarkoituksellisesti puut muodostetaan
niin, että jokaisella tasolla on *kaikki* siinä kohtaa mahdolliset muutokset;
**tämä osoittautui kombinatorilliseksi räjähdyksi ison shantenin käsillä.**

Räjähdykseen kontribuoi osaltaan jakoalgojen duplikaatit, mutta fakta on että
kun puun *minimi*korkeus on shanten (jonka maksimi on 7), sen maksimikorkeus
vielä korkeampi (koska shantenin samana pitäviä muutoksia on paljon) ja
jokaiseen mahdolliseen (ahneeseen) tenpai-käteen liittyvät muutosjonot
esiintyvät puussa useasti - jokainen permutaatio *ja* erilaiset shantenia
muuttamattomat muutokset eri kohdissa jokaisessa permutaatiossa - on lähes
selvää ettei puuta ole mahdollista evaluoida kokonaisuudessaan järkevässä
ajassa.

Tämä puukokeilu osoittaa miten monimutkainen peli riichi mahjong onkaan; jopa
ahnetta puuta on mahdoton evaluoida järkevässä ajassa, ja on hyvin tavallista
että oikeat pelaajat muuttavat kättään väliaikaisesti pienempään shanteniin
parempien pisteiden/odotusten toivossa. Tällaiset muutokset lisäävät
mahdollisten muutosten määrää monikymmenkertaiseksi ahneeseen taktiikkaan
verrattuna - siis kombinatorillisesti täysin hallitsemattomaksi!
