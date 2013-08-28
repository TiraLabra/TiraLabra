Toteutusdokumentti
==================

## Ohjelman yleisrakenne

Ohjelmalla on kaksi ominaisuutta:
* Huffman-koodaus, joka pakkaa US-ASCII-enkoodattua tekstiä
* Steganografia-koodaus, joka piilottaa sarjan ykkösiä ja nollia kuvan RGB-kanaviin

Ohjelmalla on lisäksi kaksi eri moodia: koodaus ja purku.
Kun koodataan niin ensin luetaan selkokielinen viesti, tehdään Huffman-koodaus ja sen tuotoksena tuleva binäärimössö piilotetaan steganografisesti kuvaan ja koodaaukseen käytetty Huffman-muunnostaulu kirjoitetaan erilliseen tiedostoon.

Purkaessa suunta on päinvastainen.

Ohjelmaa voi käyttää joko komentoriviltä ```HuffmanSteganoClient``` luokan avulla tai käyttäen luokkia ```Huffman``` ja ```Steganographer``` kirjastoina.

```
$ java -jar Tiralabra_maven.jar 

Welcome to a Huffman/Steganographer encoder/decoder program
Choose next action:
1) Huffman-Stegano encode
2) Stegano-Huffman decode
q) Quit

```

## Saavutetut aika- ja tilavaativuudet (m.m. O-analyysi pseudokoodista)

### Huffman

Wikipedia-artikkelin mukaan Huffman-koodauksen vaativuus on O(n log n), omien testien perusteella performanssi on ainakin n=20M asti melko lineaarista.

## Suorituskyky- ja O-analyysivertailu (mikäli työ vertailupainotteinen)

Huffman-pakkauksesta voisin sanoa sen verran, että 4.2 MB kokoinen raamatun englanninkielinen versio (33978 riviä, 840245 sanaa, 4397206 merkkiä) pakkautuu noin 4 sekunnissa 57%:iin alkuperäiskoosta.

```
Original message length: 35177648 bits
Compressed message length: 20058638 bits
Compressed size: 57 %
```

## Työn mahdolliset puutteet ja parannusehdotukset

Ohjelma hyväksyy vain US-ASCII-tekstiä, joten ääkköset eivät toimi. Otin tämän rajauksen lähinnä siksi, että itselläni on kokemusta eri tekstimerkistöjen kanssa tappelusta ja halusin rajata ohjelman scopea.

Toinen puute on ```Steganographer```in uloskirjoittaman tiedoston formaatti: vain png:t on tuettu, joten kuvan koko paisuu moninkertaiseksi vrt. jpeg vaikka piilotettavaa sisältöä ei olisikaan.

Yksi parannusehdotus olisi tehdä graafinen käyttöliittymä tälle, ja sen pitäisikin onnistua helposti koska ```Huffman``` ja ```Steganographer``` APIt pitäisi olla sen verran selkeät että ohjelmaa voi laajentaa.

## Lähteet
