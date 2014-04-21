### JUnit-testaus
JUnit-testejä on kirjoitettu kattavasti MyHashSet-luokalle. Testeissä on erityisesti pyritty ottamaan huomioon objektien, jotka saavat samoja hasharvoja, lisäys, haku ja poisto. Syötteinä on käytetty sekä String-arvoja, jotka saavat eri hajautusarvoja, että String-arvoja, jotka saavat samoja hajautusarvoja. Hajautusarvot lasketaan testejä varten tehdyllä HashFunctionStubilla. AI:n testauksessa keskitytään lähinnä siihen, että se löytää parhaimman tuloksen kirjaimistaan tyhjällä laudalla ja laudalla, jossa on jo valmiina kirjaimia. Testausta varten AI:lle on annettu LetterPoolStub-instanssi, josta AI saa syötteenä ennaltamäärätyt kirjaimet.

Yritin generoida cobertura-raportteja, mutta jostain syystä Maven ei lataa versiota 2.6 repositoriosta ja vanhempi versio 2.5.2 ei generoi raportteja oikein, vaan jättää testejä huomiotta. Tesit ovat puutteellisia lähinnä eri HashFunktioiden osalta.

### Performanssitestaus

Projektissa on testattu performanssin osalta sekä hajautusfunktioiden nopeutta että yhteentörmäysten määrää. Lopuksi on vielä testattu pelin tekoälyn nopeutta, joka riippuu paljolti hajautustaulukon ja siinä käytettävän hajautusfunktion nopeudesta. Nopeuksien testaus on tehty JUnitBenchmarks-työkalulla.

Ensimmäiseksi on testattu hajautusfunktioiden yhteentörmäysten määrää, yhteentörmäysketjuiden keskivertopituutta ja suurinta yhteentörmäysten määrää. Syötteenä käytetään enintään kahdeksan kirjaimen pituisia englannin kielisiä sanoja, joita on yhteensä 111 414. M-arvona eli hajautustaulukon kokona on käytetty alkulukua, joka on noin 1.33 kertainen hajautettavien objektien lukumäärään nähden. Hajautustaulukon täyttösuhde on siis 0.75. Tulokset eivät hirveästi eroa toisistaan. Alla kuitenkin näkyy, että parhain tulos on djb2-hajautusfunktiolla.

<b>General hash function using BigInts</b>  
Total collisions: 33124  
Average collision chain length for hash: 1.8461872  
Max collisions for same hash: 7  

<b>General hash function using longs</b>  
Total collisions: 33124  
Average collision chain length for hash: 1.8461872  
Max collisions for same hash: 7  

<b>djb2</b>  
Total collisions: 32813  
Average collision chain length for hash: 1.8349258  
Max collisions for same hash: 6  

<b>FNV-1a</b>  
Total collisions: 32930  
Average collision chain length for hash: 1.839152  
Max collisions for same hash: 7  

<b>MurmurHash3</b>  
Total collisions: 32926  
Average collision chain length for hash: 1.8390073  
Max collisions for same hash: 6  

Toiseksi on testattu hajautustaulukkoon lisäämisen nopeutta. Sanoja lisätään n. 260000. Alla olevien tuloksien perusteella djb2 ja FNV-1a ovat nopeimmat hajautusfunktiot. Kummallakin sanojen lisäämiseen menee n. 0.03s. Hajautusfunktio BigInteillä vaatii aikaa jo pelkästään roskien keräämiseen ja onkin nopeudeltaan huonoin.

<b>eneral hash function using BigInts (10 rounds)</b>  
round: 0.38 [+- 0.09], GC.calls: 32, GC.time: 0.18, time.bench: 3.58

<b>General hash function using longs (10 rounds)</b>  
 round: 0.11 [+- 0.01], GC.calls: 0, GC.time: 0.00, time.bench: 1.12

<b>djb2 (10 rounds)</b>  
 round: 0.03 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.34

<b>FNV-1a (10 rounds)</b>  
 round: 0.03 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.32

<b>MurmurHash3 (10 rounds)</b>  
 round: 0.04 [+- 0.01], GC.calls: 1, GC.time: 0.00, time.bench: 0.44

Myös objektien hakeminen hajautustaulukosta on testattu. Tulokset eivät juurikaan eroa objektien lisäysajoista. Parhaimmiksi erottuvat jälleen kerran djb2 ja FNV-1a.

<b>General hash function using BigInts (10 rounds)</b>  
 round: 0.31 [+- 0.00], GC.calls: 31, GC.time: 0.08, time.bench: 3.06

<b>General hash function using longs (10 rounds)</b>  
 round: 0.08 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.81

<b>djb2 (10 rounds)</b>  
 round: 0.01 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.08

<b>FNV-1a (10 rounds)</b>  
 round: 0.01 [+- 0.00], GC.calls: 0, GC.time: 0.00, time.bench: 0.06

<b>MurmurHash3 (10 rounds)</b>  
 round: 0.02 [+- 0.00], GC.calls: 1, GC.time: 0.01, time.bench: 0.18

Testien perusteella djb2-hajautusfunktio toimii parhaiten sekä yhteentörmäyksien määrien että nopeuden puolesta.

Lopuksi testataan vielä pelin tekoälyn nopeutta löytää paras sana kirjaimistaan, kun ruudukko on tyhjä, jolloin etsittävää on eniten. Kirjaimiksi annetaan rxinxoge, joista on mahdollista muodostaa vain yksi validi sana "xeroxing". Testissä AI:lla kestää n. 1.09s löytää oikea sana "xeroxing".

<b>AiBenchmark (10 rounds)</b>  
 round: 1.09 [+- 0.01], GC.calls: 135, GC.time: 0.09, time.bench: 10.88


