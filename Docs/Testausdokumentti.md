Mitä on testattu, miten tämä tehtiin?

Testausta varten olen tehnyt valmiita verkkoja Verkko -luokkaan. Olen pyrkinyt tekemään mahdollisimman monta eri verkkoa jotta ne testaisivat mahdollisimman montaa erikoistapausta. Tähän asti olen testannut:

- Verkot joissa negatiivinen sykli
- Verkot joissa negatiivisia painoja mutta ei sykliä
- Isot verkot,
- Pienet verkot,

Jokainen Algoritmi sisältää tulostaSolmut() -metodin joka tulostaa solmujen lopulliset painot järjestyksessä. Solmu 1 toimii alkusolmuna.

Alla esimerkkinä tulostus:

**************************************

Oikeat loppupainot: 	// testin tuottamat oikeat vastaukset
0, 5, 7, 9, 6

Bellman-Ford tulokset: 	// Bellman-Fordin tulos
0, 5, 7, 9, 6, true	// true tarkoittaa ettei negatiivisia syklejä löytynyt
Alussa: 1376912602766
Lopussa: 1376912602770
Kesti: 4 ms

Dijkstra tulokset: 	// Dijkstran tulos
0, 5, 7, 9, 6, 
Alussa: 1376912602770
Lopussa: 1376912602773
Kesti: 3 ms

**************************************

Painot [0, 5, 7, 9, 6] tarkoittavat siis seuraavaa:

Solmusta 1 lyhyin pituus solmuun 1 = 0 (lyhyin matka itseensä)
Solmusta 1 lyhyin pituus solmuun 2 = 5
Solmusta 1 lyhyin pituus solmuun 3 = 7
Solmusta 1 lyhyin pituus solmuun 4 = 9
Solmusta 1 lyhyin pituus solmuun 5 = 6
