### JUnit-testaus
JUnit-testejä on kirjoitettu kattavasti MyHashSet-luokalle. Cobertura tosin näyttää punaista muutamalle metodille, jotka on implementoitava Set-perinnän takia. Testeissä on erityisesti pyritty ottamaan huomioon objektien, jotka saavat samoja hasharvoja, lisäys, haku ja poisto. Syötteinä on käytetty sekä String-arvoja, jotka saavat eri hajautusarvoja, että String-arvoja, jotka saavat samoja hajautusarvoja. Hajautusarvot lasketaan testejä varten tehdyllä HashFunctionStubilla. AI:n testauksessa keskitytään lähinnä siihen, että se löytää parhaimman tuloksen kirjaimistaan tyhjällä laudalla ja laudalla, jossa on jo valmiina kirjaimia. Testausta varten AI:lle on annettu LetterPoolStub-instanssi, josta AI saa syötteenä ennaltamäärätyt kirjaimet.

### Performanssitestaus

Projektissa on testattu performanssin osalta sekä hajautusfunktioiden nopeutta että yhteentörmäysten määrää. Lopuksi on vielä testattu pelin tekoälyn nopeutta, joka riippuu paljolti hajautustaulukon ja siinä käytettävän hajautusfunktion nopeudesta. Nopeuksien testaus on tehty JUnitBenchmarks-työkalulla.

#### Yhteentörmäykset

Yhteentörmäyksiä testasin sekä suoraan hajautusfunktioista saaduilla int32-arvoilla englanninkielisille sanoille että arvoilla, joita lopulta käytetään hajautustaulukon indeksin määrittämisessä (ts. hajautusarvon itseisarvo modulo taulukon koko). Jälkimmäisen testaukseen käytin 111 414 sanan listaa, jossa sanojen maksimipituus on 8 merkkiä.

![Alt text](/Docs/collisions-int32.jpg?raw=true "Total collisions for int32 hash values")

Ylläolevasta kuvasta näkyy, että Tira-kalvojen suosittelema algoritmi ei toimi ainakaan int32-arvoja käyttämällä kovin hyvin. Algoritmissa kertoimet kasvavat hyvin isoiksi, joten osa biteistä katoaa. Testauksessa sama algoritmi, mutta BigIntegereillä, tuottaa 0 yhteentörmäystä, mikä on melko yllättävää, koska myös BigInteger on muutettava takaisin int32-arvoksi. Testauksessa myös huomasin, että jos character-tyyppien arvoja offsettaa -97 sijaan luvulla -96, niin yhteentörmäykset kasvavat monella sadalla em. hajautusfunktiossa. Javan oma .hashCode()-metodi näkyy toimivan yhtä mallikkaasti kuin djb2 ja MurmurHash3. Ei ehkä yllättävää, että CRC32:lla ei ole yhtään törmäystä.

![Alt text](/Docs/collisions-words.jpg?raw=true "Total collisions for int32 hash values")

Ns. real-life skenaariona testasin englanninkielisille sanoille onko sana MyHashSet-tietorakenteessa käyttäen contains()-metodia. Erona on, että hajautusfunktoiden arvoja ei voi sinällään käyttää hajautustaulukoiden indekseinä. Modulona käytetään taulukon kokoa, joka tässä tapauksessa on n 1.33*111414., jolloin hajautustaulukon loadrate on enintään 0.75 (Javan HashSetin loadrate). Yhteentörmäyksiä sattuu paljon, niin kuin ylläolevassa kuvassa näkyy. Testeissä kaikilla hajautusfunktioilla pisin törmäysketju oli 7-8 törmäystä. Tässäkin kohtaa CRC32 vaikuttaa toimivan parhaiten.

#### Nopeus

Toiseksi on testattu hajautustaulukosta löytymisen nopeutta. Sanoja lisätään 111 414 MyHashSet-hajautustaulukkoon.

![Alt text](/Docs/benchmarks.jpg?raw=true "Total collisions for int32 hash values")

TirA-kalvon esimerkkien toteutukset pärjäsivät huonoiten. Integer-versiossa joutuu käyttämään potenssiin korotusta ja castamaan double-tyypistä int-tyyppiin joka kirjaimen kohdalla, joka osittain selittänee hitautta. Kaikki muut algoritmit toimivat kiitettävällä nopeudella, tosin CRC32 näyttää olevan raskain näistä.

#### Yhteenveto

Testien perusteella CRC32 vaikuttaisi tuottavan vähiten yhteentörmäyksiä. Se on hieman hitaampi kuin muut algoritmit, lukuunottamatta TirA-toteutuksia, mutta nopeusero ei useimmissa tapauksissa ole merkittävä ellei prosessoitavana ole aivan valtava määrä dataa.


#### Pelin tekoäly

Lopuksi testataan vielä pelin tekoälyn nopeutta löytää paras sana kirjaimistaan, kun 8x8 ruudukko on tyhjä, jolloin etsittävää on eniten. Kirjaimiksi annetaan rxinxoge, joista on mahdollista muodostaa vain yksi validi sana "xeroxing". 

<b>AiBenchmark (10 rounds)</b>  
 round: 1.09 [+- 0.01], GC.calls: 135, GC.time: 0.09, time.bench: 10.88

AI on melko hidas. Sillä kestää n. 1.09s löytää oikea sana "xeroxing", mutta ottaen huomioon, että ihmisvastustajalla kestäisi huomattavan paljon kauemmin löytää edes jokin sana kirjaimistaan, on tulos ihan kohtuullinen.

### Testien toisto omalla koneella
Performanssi- ja törmäystestit löytyvät kansiosta Sanapuuro/src/test/java/sanapuuro/benchmarks JUnit-testeinä, eli ne ovat ajettavissa NetBeansin tai jonkin muun IDE:n kautta omalla koneella.
