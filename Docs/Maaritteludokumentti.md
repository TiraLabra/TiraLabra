Tietorakenteiden harjoitus työssä vertaan erilaisten hakupuiden
suorituskykyä keskenään.

Tutkin eri rakenteiden kuten puun koon, syvyyden sekä mahdollisten
muiden muuttujien vaikutusta puun solmujen haun, lisäyksen ja
poiston aika- ja tilavaativuuksiin.

Käytän ainakin seuraavia neljää puualgoritmia:
* Normaali binäärihakupuu
    Puu on toteutettu käyttäen perus ideaa, vasemmassa oksassa on
    pienemmät alkiot ja oikeassa suuremmat. Aikavaatimukseltaan haku
    on O(n) puun korkeuden suhteen. Parhaassa tapauksessa log2(n). Lisäys
    on luultavasti hitaampaa.
* Punamustapuu
    Puussa juurisolmu on musta ja jokaisen mustan solmun lapsisolmut ovat
    punaisia ja päinvastoin. Juuresta NILiin sisältää saman määrän mustia
    riippumatta reitistä. Aikavaatimukseltaan haku on O(n) puun korkeuden
    suhteen. Poiston ja lisäyksen punamustapuuhun tulisi olla nopeampaa kuin
    normaalissa binäärihakupuussa.
* AVL-puu 
    Puu toimii aikalailla samalailla kuin tavallinen binäärihakupuu, mutta
    pitää kirjaa tasapainokertoimesta ja kieräyttää epätasapainossa olevaa
    haraa tarvittaessa pitäen puun tasapainossa.
* Splay-puu
    Puu pitää itsensä tasapainossa kuten AVL-puu, mutta samalla pitää kirjaa
    viimeaikaisista solmuista, jotta ne ovat nopeasti saatavilla.

Valitsin puualgoritmit perustuen niiden erilaisuuteen, jotta
saataisiin erilaisia tuloksia. Kaikkien tuli olla kuitenkin
eri versioita binäärihakupuusta jotta vertailu olisi tasapuolinen.

Ohjelma voi mahdollisesti saada käyttäjältä eri asetuksia ajan salliessa,
muuten ohjelma toteutetaan käyttäen valmiita syötteitä.



Lähteet:
Wikipedia.org
