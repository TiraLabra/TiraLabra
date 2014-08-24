
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
aika- ja tilavaativuus lähes määritelmästä johtuen luokkaa O(n<sup>2</sup>).
Käsiteltäviä tiiliä on kuitenkin aina <= 14 (neljä samaa (kutsumaton kantsu)
tekevät pienen poikkeuksen), joten reaalitapauksissa neliöllisyys ei ole
ongelma.

`tilesGroupL` käsittelee kaikki ryhmitystapaukset vasemmalta lähtien (siitä
L-suffiksi); tarkempi toiminta on kuvattu sen
[docstringissä](http://simsaladin.users.paivola.fi/TiraLabra/Mahjong-Hand-Algo.html#v:tilesGroupL).

`tilesSplitGroupL` on pieni parannus aiempaan, joka käsittelee tiilityypit
(eri maat ja honorit) erikseen käyttäen `tilesGroupL`:ää (siis klassinen hajoita
ja hallitse). Suurissa tapauksissa parannus olisi siis aikavaativuudessa 1/4
vakiokerroin, muistivaativuus on sama.

## shanten

TODO writeme

## buildGreedyWaitTree

TODO writeme
