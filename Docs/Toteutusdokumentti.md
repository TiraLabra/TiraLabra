Ohjelmassa on kolme isoa komponenttia ja joitakin pieniä sivussa. Yksi on käyttöliittymä. Toinen on itse pelilogiikka, joka huolehtii pelin kulusta. Kolmas on tekoäly, joka on se kiinnostavin osuus. Yksi peluukerta lyhyesti sanottuna toimii siis siten, että käyttäjä painaa painiketta, jonka kuuntelija käskee peliä pelaamaan kierroksen. Peli käskee tekoälyä antamaan siirron ja näyttää tulokset. 

* * * 

Tekoälyn rakenne

Tekoäly käyttää tietorakenteenaan puuta, jonka korkeus on 5. Solmullan on enimmillään yhdeksän lasta eli jokaiselle peluu-lopputulos -kombinaatiolle on oma solmu. Pinorakennetta käytetään pelin muistamiseen. 

Tekoäly päättää siirron seuraavalla tavalla:

1a. Jos peliä on pelattu alle 5 siirtoa, niin pelaa satunnaisesti.

syvyys h on 3

1b. Etsi solmu puun syvyydeltä h ja summaa lapsisolmujen peluumäärät taulukkoon (kivet kiviin jne.)

2. Jos peluumääriä on alle 3, niin summaa etsi kaikki solmut vastaavat solmut syvyydeltä h jättäen huomiotta pelin lopputuloksen ja summaa lapsisolmujen peluumäärät taulukkoon

3. Jos peluumääriä on alle 3, niin pienennä muuttujaa h yhdellä ja palaa kohtaan 1b. 

4. Vaihda siirtojen paikkoja taulukossa siten, että jokainen luku on sen peluun paikalla, mille häviäisi. 

5. kerro taulukon luvut satunnaisluvulla väliltä [0-1] ja pelaa suurinta lukua vastaava siirto. 

Tämä lisäksi tekoälyssä on käänteinen peluu -toiminto, joka aktvoituu, jos vastustaja pelaajatkuvasti kohdassa 4 muokatun talukon pienintä lukua vastaavaa siirtoa. 

* * *

Aika- ja tilavaativuudet

Tämä ei ole mitenkään kriittinen osa-alue tekoälyn toiminnassa, koska puun korkeus on vakio eli tekoäly pelaa vakioajassa. Ainut asia, mikä ei tapahdu vakioajassa on pelaajaprofiilin lataaminen. Tämä vaatii n syötteen käsittelyn sekä lisäämisen puuhun. Puuhun lisääminen vie aikaa puun korkeuden verran eli tässä tapauksessa vakioajan. Profiilin lataaminen on siis O(n) aikavaativuudeltaan. Tilaa ohjelma varaa lisää suhteessa peluukertojen määrään (pino kasvaa), joten sen aikavaativuus on myös O(n). 

* * *

Parannettavaa

Välillä minulla on sellainen tunne, että tekoäly pelaa hieman eri tavalla, mitä olen tarkoittanut sen pelaavan. Tämän tarkistaminen voi olla hankalaa, koska 1. tekoäly pelaa mielestäni todella hyvin 2. tekoäly käyttää paljon satunnaisuutta 3. tekoälyn käyttämässä puussa on hurjan paljon solmuja. 

Koodasin pikaisesti ominaisuuden, jonka tarkoitus on vaikeuttaa tekoälyä vastaan pelaamista laskemalla. Tätä en ole oikein onnistunut testaamaan, koska en ole päässyt sitä vastaan sillä tavalla niskan päälle, että toiminnallisuus aktivoituisi. Silti uskon sen toimivan. Käyttämääni ideaa voisi ehkä myös laajentaa kääntämään ylimääräisiä tasapelejä voitoiksi. 

Jos joku tekisi toisen tekoälyn, niin voisi kokeilla kaksintaiselua koneiden välillä. 

En käyttänyt lainkaan kirjallisia lähteitä työssäni. En kokenut kauheasti tarvitsevani, mutta varmasti kirjallisuuden lukeminen olisi tuonut lisää ideoita. Ohjaajalta sain kuitenkin hyviä ideoita. 
