Kun pelin käynnistäessä ilmestyy ikkuna, jossa on kolme valintaa:
1. Play vs bot
2. Play vs bot as guest
3. Bot vs bot

1: valinta kysyy, että luodaanko uusi profiili vai ladataanko olemassa oleva. Tämän jälkeen haetaan oma profiili listasta. 
2: valinta menee suoraan peliin. 
3: valinta menee suoraan peliin, jossa peluutetaan kahta tekoälyä toisiaan vastaan. 

* * * 

Ihminen vastaan tekoäly:
Pelaaminen on hyvin yksinkertaista. Valitset haluamasi sirron painikkeista. Lopputulos näkyy ruudulta (lopputulos ilmoitetaan tekoälyn näkökulmasta). "..." -painikkeella näet tekoälyn datan jota tekoäly on käyttänyt viimeksi: 

Used counter anti-strategy: Jos pelaat monta kertaa peräkkäin tekoälyn "vähiten odottamaa" siirtoa, niin tekoäly saattaa muuttaa valintansa käänteiseksi. Jos näin käy, niin tämä kohta on true.

Used wide data: Tekoäly huomioi aina sen, minkä siirron olet tehnyt. Mikäli dataa on riittävästi, niin tekoäly myös huomioi pelin looputuloksen. Tällöin arvo on true.

Last x moves you played: Näyttää, minkä kokoista peluulinjaa tekoäly käytti viimeisimmässä päätöksessään. 

Your multipliers: Näyttää, mitkä kertoimet (lisättynä vakiolla 2) tekoäly antoi siirroillesi. 

* * *

tekoäly vastaan tekoäly:
Käytä "..." -painiketta ja valitse pelattavien kierrosten määrä. Peli näyttää lopputuloksen "paremman" (lähdekoodissa primary bot) tekoälyn näkökulmasta. Tämä pelimuoto on tylsä, ellei joku koodaa toista hienompaa tekoälyä nykyistä vastaan. 
