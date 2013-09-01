Mitä on testattu, miten tämä tehtiin?

Testausta varten olen tehnyt valmiita verkkoja Verkko -luokkaan. Olen pyrkinyt tekemään mahdollisimman monta eri verkkoa jotta ne testaisivat mahdollisimman montaa erikoistapausta. Tähän asti olen testannut:

- Verkot joissa negatiivinen sykli
- Verkot joissa negatiivisia painoja mutta ei sykliä
- Isot verkot,
- Pienet verkot,

Jokainen Algoritmi sisältää tulostaSolmut() -metodin joka tulostaa solmujen lopulliset painot. Näitä
voi helposti verrata testien tulostamiin oikeisiin vastauksiin. 

Alla esimerkkinä tulostus kun käydään verkko "Iso verkko" läpi.

Lyhin matka solmusta 1 solmuun 1: 0
Lyhin matka solmusta 1 solmuun 2: 5
Lyhin matka solmusta 1 solmuun 3: 7
jne...

**************************************

Oikeat loppupainot: 	// testin tuottamat oikeat vastaukset
0, 5, 7, 9, 6

Bellman-Ford tulokset: 	// Bellman-Fordin tulos
0, 5, 7, 9, 6, true
Alussa: 1376912602766
Lopussa: 1376912602770
Kesti: 4 ms

Dijkstra tulokset: 	// Dijkstran tulos
0, 5, 7, 9, 6, 
Alussa: 1376912602770
Lopussa: 1376912602773
Kesti: 3 ms

**************************************
