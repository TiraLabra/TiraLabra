Määrittelydokumentti
====================

Projektin aiheena on shakkitekoäly, sekä siihen graafinen käyttöliittymä. Käyttöliittymän kautta voi pelata tekoälyä vastaan tai antaa kahden tekoälypelaajan pelata keskenään.

Tekoälyn lähtökohtana on minimax-algoritmi, jonka kanssa sovelletaan alfa-beeta-karsintaa. Algoritmi käy läpi kaikki päätösvaihtoehdot syvyyssuuntaisesti tiettyyn syvyyteen asti pelipuussa, jossa kukin solmu vastaa yhtä pelitilannetta. Alfa-beeta karsinnan ansiosta kaikkia alipuita ei tarviste analysoida, mikä mahdollistaa syvemmät haut.

Algoritmi palauttaa parhaan strategian, kun molemmat pelaajat pelaavat optimaalisesti. Pelitilanteille annetaan pistearviot kummankin pelaajan nappuloiden lukumäärän ja tyypin mukaan (sotilas 1p, ratsu 3p jne.), sekä mahdollisesti esim. sijainnin mukaan. Mattitilanteiden arvoiksi voidaan valita jokin itseisarvoltaan hyvin iso luku.

Tärkeänä optimointina alfa-beeta-karsintaa varten alipuut käydään läpi sellaisessa järjestyksessä, että nopeasti arvioituna parhaat siirrot analysoidaan ensin (esim. lyönnit). 

Algoritmi toteutetaan ns. iterative-deepening-versiona, eli se suoritetaan useita kertoja, ja jokaisella kerralla syvyyttä kasvatetaan yhdellä. Suoritus lopetaan kun käytettävissä oleva aika (esim. 1s) on lopussa, tai jos löydetään voittava strategia.

Lisäksi tarkoitus on toteuttaa ns. nollasiirtoheuristiikka, jossa siirto jätetään kokonaan väliin, ja analysoidaan uutta tilannetta vastaava matalampi pelipuun haara. Sen perusteella päätetään, tarvitseeko syvempää versiota tarkastella.

Lisäksi mahdollisesti toteutetaan transpositiotaulu, jossa jokainen analysoitu pelitilanne tallennetaan hajautustauluun. Analysoinnin tulosta voidaan käyttää uudestaan, jos sama pelitilanne toistuu useassa kohtaa pelipuuta, ja tuloksia voidaan hyödyntää myös puun läpikäyntijärjestyksen optimoinnissa.

Kehittyneemmistä shakkiohjelmista poiketen suunnitelmissa ei ole toteuttaa minkäänlaista avauskirjastoa. Joko minimaxin tulosta tullaan käyttämään sellaisenaan myös alkupelissä, tai sitten yhdistetään siihen jonkinlainen naiivi heuristiikka, joka mukailee tyypillisiä avauksia.

Koska shakkiohjelmia varten on olemassa lukuisia optimointeja (esim. laudan esittäminen 64-bittisen maskin avulla, "killer heuristic" yms.), ei tässä vaiheessa ole vielä tarkkaa tietoa, mitä kaikkia niistä toteutetaan. Tarkoituksena on saada aikaan niin paljon kuin kohtuudella on mahdollista.

Shakkitekoälyn aika- ja tilavaativuus riippuu paljon pelitilanteesta, läpikäyntijärjestyksestä ja optimointien tasosta. Yleisesti minimaxin aikavaativuus on O(b^d), jossa b on haarautumiskerroin, ja d on haun syvyys. Alfa-beta karsinnan avulla syvyys voidaan parhaassa tapauksessa kaksinkertaistaa (O(b^(d/2))). Lisäksi transpositiotaulu pienentää haarautumista entisestään. Tavoitteena on, että haun pystyisi suorittamaan ainakin 10 siirron päähän parissa sekuntissa. Tilavaativuus ilman tarnspositiotaulua on O(d) ja transpositiotaulun kanssa se on sama kuin aikavaativuuskin.

Lähteet:

- http://chessprogramming.wikispaces.com

- http://en.wikipedia.org/wiki/Computer_chess

- http://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
