Harjoitustyö: tietorakenteet ja algoritmit
Harjoitustyön aihe: image compression, eli kuvapakkaus

Todennäköisesti tehtävä muuntuu yleisen häviöttömän pakkaajan toteutukseen.

RAW-kuvaformaatti ORF, jolle alunperin ajattelin tehdä pakkaajan osoittautui suljetuksi, Olympus Raw Format:ksi, joten sille en tee pakkaajaa. Toinen ilmeisen suosittu formaatti on DNG, joka on käsittääkseni kattavasti documentoitu, mutta bittitason esitystä on ollut vaikeaa löytää, lisää tietoa tarvitaan.

Harjoitustyön periaatteellinen idea on lähteä toteuttamaan yleistä pakkaajaa Lempel-Ziw-kaltaisella pakkausalgoritmillä ja tilastollisella analyysillä. Lempel-Ziw algortmit käyttävät pääasiassa kirjastoa joka muodostetaan suoraan pakattavasta materiaalista. Suorittamalla tilastollinen analyysi pakattavaan materiaaliin on mahdollista löytää "palasia" jotka toistuvat useimmiten, ja täten saada aikaan todennäköisesti suurempia pakkaussuhteita.

O-analyysi:
Pakkaaminen:
Tilastollisen analyysin menetelmiä ei ole vielä päätetty. Todennäköinen lähestymistapa:
1. Otetaan N-pituinen syöte, lisätään se hajautustauluun ja poistetaan se syötteestä. Lisäksi hajautustaulun tupleeseen liitetään esiintymiskerta.
2. Hajautustaulun lyhennettä käytetään tulosteena. (kompressio)
3. Otetaan seuraava N-pituinen syöte ja verrataan sitä hajautustaulussa oleviin arvoihin, jos arvo löytyy ei lisätä sitä uudestaan, vaan käytetään jo olemassa olevaa lyhennettä tulosteena.
4. Toistetaan 2-3 kunnes viimeinen syöte on lyhyempi kuin N. Käytetään jäljelle jäänyttä syötettä (literaalia) sellaisenaan.

Em. prosessi toistetaan muutamalla eri N-arvolla (4-16 bit ?).
Syntyneistä hajautustauluista ja tulosteista poistetaan vain kerran esiintyvät literaalit jotka korvataan tulosteeseen sellaisenaan. Lasketaan syntyneistä hajautustauluista ja tulosteista pakkaussuhde jonka jälkeen käytetään parhainta. Yhteentörmäyksien käsittelemiseksi käytetään joko täydellistä hajautusfunktiota tai avaimeen liitetään törmäysnumero. Törmäysnnumeroa käytettäessä numerointi alkaa nollasta, kun tapahtuu törmäys sitä kasvatetaan ja arvo voidaan törmäyksen perusteella yksilöidä myös pakatussa tiedossa.

Tupleen lisääminen hajautustauluun on keskimäärin vakioaikainen operaatio. Tästä seuraa, että hajautustaulun muodostaminen on lineaarinen operaatio suhteessa syötteen kokoon. Vain kerran esiintyvien literaalien korvaus tulosteeseen on pahimmassa tapauksessa kaikki hajautustaulun tupleet ja täten lineaarinen syötteen kokoon verrattuna. Kukin N:n koko jolla syöte analysoidaan on aina em. operaatiot toistettuna, eli jos N:n kokoja on 3, on (jos hajautustaulujen muodostamista eri N:llä ei yhdistetä) em. prosessi tehtävä kolmasti ja prosessin aikavaativuus on lineaarinen N:n kokojen lukumäärään.
Kaikenkaikkiaan prosessin aikavaativuuden tulisi kuitenkin olla lineaarinen syötteen kokoon nähden.

Jos menetelmä ei ole tehokas pakattavalle tiedolle hajautustaulun avaimia on enintään N:n permutaatioiden verran. Tästä seuraa, että kukin avain vie yhtä paljon tilaa kuin sen osoittama arvo. Tilavaativuus menetelmällä on siis pahimmassa tapauksessa:

p + (2N)

jossa p on pakattavan tiedon alkuperäinen koko ja N arvon koko (4-16 bit ?).

Purkaminen:
1. Syötteestä puretaan hajautustaulu.
2. Syötteestä luetaan avain, ja tulosteeseen lisätään sen arvo hajautustaulusta. Avain poistetaan syötteestä.
3. Toistetaan 2 kunnes saavutetaan viimeinen literaali.

Em. prosessin aikavaativuus riippuu täysin siitä, kuinka hyvä pakkaussuhde on alunperin saavutettu. Jos siis alkuperäisessä syötteessä on ollut paljon toistoa on hajautustaulu pieni, mutta viittauksia samoihin arvoihin on monta. Jos taas alkuperäinen syöte on lähes satunnaista tietoa, on hajautustaulussa joko paljon avaimia (joihin viitataan vähän), tai hyvin vähän avaimia sekä pakatussa tiedossa paljon literaaleja. Pakkauksen purku on jokatapauksessa aikavaativuudeltaan lineaarinen alkuperäisen tiedoston kokoon nähden, sillä arvon noutaminen hajautustaulusta avaimen perusteella on keskimäärin vakioaikainen operaatio.

Tilavaativuus on purussa irrelevantti tieto, mutta algoritmi vienee tilaa

p + l

jossa p on pakatun tiedon koko ja l pakkaamattoman tiedon koko.

Muuta:
Tarkoituksena on myös toteuttaa ohjelmalle yksinkertainen graafinen käyttöliittymä (1-2 ikkunaa).

Jos em. osoittautuu hankalaksi ja lähdemateriaalia löytyy, on madollista, että toteutan Lempel-Ziw-Markov-ketjutus algortitmin.

https://en.wikipedia.org/wiki/Lossless_data_compression
https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Markov_chain_algorithm
https://en.wikipedia.org/wiki/Statistical_Lempel_Ziv
https://en.wikipedia.org/wiki/Huffman_coding
