Projektissa on testattu sekä hajautusfunktioiden nopeutta että yhteentörmäysten määrää. Lopuksi on vielä testattu pelin tekoälyn nopeutta, joka riippuu paljolti hajautustaulukon ja siinä käytettävän hajautusfunktion nopeudesta. Nopeuksien testaus on tehty JUnitBenchmarks työkalulla.

Ensimmäiseksi on testattu hajautusfunktioiden yhteentörmäysten määrää, yhteentörmäysketjuiden keskivertopituutta ja suurinta yhteentörmäysten määrää. M-arvona on käytetty alkulukua, joka on noin 1.33 kertainen hajautettavien objektien lukumäärään nähden. Tulokset eivät hirveästi eroa toisistaan. Alla kuitenkin näkyy, että parhain tulos on djb2-hajautusfunktiolla.

General hash function using BigInts
Total collisions: 33124
Average collision chain length for hash: 1.8461872
Max collisions for same hash: 7

General hash function using longs
Total collisions: 33124
Average collision chain length for hash: 1.8461872
Max collisions for same hash: 7

djb2
Total collisions: 32813
Average collision chain length for hash: 1.8349258
Max collisions for same hash: 6

FNV-1a
Total collisions: 32930
Average collision chain length for hash: 1.839152
Max collisions for same hash: 7

MurmurHash3
Total collisions: 32926
Average collision chain length for hash: 1.8390073
Max collisions for same hash: 6

Toiseksi on testattu hajautustaulukkoon lisäämisen nopeutta. Sanoja lisätään n. 260000. Alla olevien tuloksien perusteella djb2 ja FNV-1a ovat nopeimmat hajautusfunktiot. Kummallakin sanojen lisäämiseen menee n. 0.03s. Hajautusfunktio BigInteillä vaatii aikaa jo pelkästään roskien keräämiseen ja onkin nopeudeltaan huonoin.

General hash function using BigInts (10 rounds)
round: 0.38 [+- 0.09], GC.calls: 32, GC.time: 0.18, time.bench: 3.58

General hash function using longs (10 rounds)
 round: 0.11 [+- 0.01], GC.calls: 0, GC.time: 0.00, time.bench: 1.12

djb2 (10 rounds)
 round: 0.03 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.34

FNV-1a (10 rounds)
 round: 0.03 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.32

MurmurHash3 (10 rounds)
 round: 0.04 [+- 0.01], GC.calls: 1, GC.time: 0.00, time.bench: 0.44

Myös objektien hakeminen hajautustaulukosta on testattu. Tulokset eivät juurikaan eroa objektien lisäysajoista. Parhaimmiksi erottuvat jälleen kerran djb2 ja FNV-1a.

General hash function using BigInts (10 rounds)
 round: 0.31 [+- 0.00], GC.calls: 31, GC.time: 0.08, time.bench: 3.06

General hash function using longs (10 rounds)
 round: 0.08 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.81

djb2 (10 rounds)
 round: 0.01 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.08

FNV-1a (10 rounds)
 round: 0.01 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.06

MurmurHash3 (10 rounds)
 round: 0.02 [+- 0.00], GC.calls: 1, GC.time: 0.01, time.bench: 0.18

Testien perusteella djb2-hajautusfunktio toimii parhaiten sekä yhteentörmäyksien määrien että nopeuden puolesta.

Lopuksi testataan vielä pelin tekoälyn nopeutta löytää paras sana kirjaimistaan, kun ruudukko on tyhjä, jolloin etsittävää on eniten. Kirjaimiksi annetaan rxinxoge, joista on mahdollista muodostaa vain yksi validi sana "xeroxing". Testissä AI:lla kestää n. 1.09s löytää oikea sana "xeroxing".

AiBenchmark (10 rounds)
 round: 1.09 [+- 0.01], GC.calls: 135, GC.time: 0.09, time.bench: 10.88


