Tiedon tiivistys

Tarkoitus on luoda tekstitiedostojen tiivistykseen tarkoitettu pakkaus- ja purkutyökalu.

Ongelmana on tiedon pakkaaminen niin, että data on pakatun tiedoston purkamisen jälkeen identtinen alkuperäiseen tiedostoon. Tavoitteena on myös pakata tiedosto n. 40-60% alkuperäisen tiedoston koosta.

Pakkaamiseen ja purkamiseen käytetään "Huffman coding" periaatetta, joka käyttää tietorakenteena binääripuuta sekä algoritmina Huffmanin algoritmia. Algoritmin perusidea on, että tekstitiedoston kirjaimista tehdään binääripuu merkkien esiintymisfrekvenssin mukaan. Se merkki, millä on korkein frekvenssi, sijoitetaan puun juureksi. 

Koska Huffman koodaus on yksinkertainen ja nopea, sekä minkään tekstitiedoston merkin frekvenssi ei pitäisi keskiarvoltaan olla lähellä 1:stä, on tämä parempi valinta kuin Aritmeettinen koodaus, millä päästäisiin lähimmäksi optimoitua pakkaussuhdetta jossain tietyssä staattisessa tekstissä. Huffmanin etu on se, että voidaan ottaa keskiarvo esimerkiksi Suomen, Englannin tai Ruotsin kielen merkkien frekvenssistä erikseen ja silti päästä hyvään pakkaussuhteeseen.

Ohjelma saa syötteenään tekstitiedoston ja tiedon siitä, minkä kielinen tekstitiedosto on. Tämän jälkeen valitaan oikea kieli ja otetaan käyttöön kyseisen kielen merkkien frekvenssit binääripuussa. Koska binääripuussa eteneminen muuttaa jokaisen merkin yksikäsitteiseksi binääriesitykseksi, voidaan teksti pakata binäärimuotoon alusta loppuun merkki kerrallaan. Pakatun tiedoston purkamisessa käännetään binääriesitykset kirjaimiksi yksi kerrallaan alusta loppuun.

Binääripuun luomisen aikavaativuus on luokkaa O(n) ja tietyn merkin etsiminen puusta vie aikaa O(log n). Koska jokaiselle eri kielelle luodaan oma binääripuu, menee näihin kerran aikaa O(n). Koska näitä kuitenkin tämän jälkeen käytetään valmiina tietorakenteina ohjelmassa, voidaan sanoa, että uuden tiedoston pakkaamiseen (jos kieli tiedetään), menee O(log n) aikaa.

Tilavaativuus on luokkaa O(n), koska merkit tallennetaan taulukkoon, jonka koko on merkkien yhteismäärä.

Lähteet:
http://en.wikipedia.org/wiki/Lossless_data_compression
http://en.wikipedia.org/wiki/Huffman_coding
http://en.wikipedia.org/wiki/Entropy_encoding



