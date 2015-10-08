Käynnistä main ja seuraa Output ruutuun tulevia ohjeita. Koordinaattien rajat ovat nollan ja korkeus-1 tai leveys-1 riippuen kumpaa kysytään. Eli jos määrität kartan leveydeksi 10, niin koordinaatin voi syöttää väliltä 0-9. (Ohjelma ei kuitenkaan kaadu väärän syötteen takia, vaan korjaa syötteen automaattisesti toimivalle välille).

Koordinaattien kysymisen jälkeen ohjelma kysyy mitä reitinhakualgoritmiä haluaisit käyttää. Kirjoittamalla "1" saat a-star- ja kirjoittamalla "2" Dijkstra-algoritmin jne.

Nyt ohjelma kysyy tahdotko käyttää hienompaa satunnaiskarttageneraattoria. Kannattaa kuitenkin varoa käyttämistä suuremmilla kartoilla suuren aikavaativuuden vuoksi. (Ohjelma kysyy varmistusta jos kartan koko alkaa olla turhan suuri).

Oma suositukseni ohjelman asetuksille ovat Korkeus = 100, Leveys = 50, Astar haulla ja satunnaiskarttageneraattorilla.
