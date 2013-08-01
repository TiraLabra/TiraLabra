Harjoitustyö: tietorakenteet ja algoritmit
Harjoitustyön aihe: image compression, eli kuvapakkaus

Todennäköisesti tehtävä muuntuu yleisen häviöttömän pakkaajan toteutukseen.

RAW-kuvaformaatti ORF, jolle alunperin ajattelin tehdä pakkaajan osoittautui suljetuksi, Olympus Raw Format:ksi, joten sille en tee pakkaajaa. Toinen ilmeisen suosittu formaatti on DNG, joka on käsittääkseni kattavasti documentoitu, mutta bittitason esitystä on ollut vaikeaa löytää, lisää tietoa tarvitaan.

Harjoitustyön periaatteellinen idea on lähteä toteuttamaan yleistä pakkaajaa Lempel-Ziw-kaltaisella pakkausalgoritmillä ja tilastollisella analyysillä. Lempel-Ziw algortmit käyttävät pääasiassa kirjastoa joka muodostetaan suoraan pakattavasta materiaalista. Suorittamalla tilastollinen analyysi pakattavaan materiaaliin on mahdollista löytää "palasia" jotka toistuvat useimmiten, ja täten saada aikaan todennäköisesti suurempia pakkaussuhteita.

Tarkoituksena on myös toteuttaa ohjelmalle yksinkertainen graafinen käyttöliittymä (1-2 ikkunaa).

Jos em. osoittautuu hankalaksi ja lähdemateriaalia löytyy, on madollista, että toteutan Lempel-Ziw-Markov-ketjutus algortitmin.

https://en.wikipedia.org/wiki/Lossless_data_compression
https://en.wikipedia.org/wiki/Lempel%E2%80%93Ziv%E2%80%93Markov_chain_algorithm
https://en.wikipedia.org/wiki/Statistical_Lempel_Ziv
https://en.wikipedia.org/wiki/Huffman_coding
