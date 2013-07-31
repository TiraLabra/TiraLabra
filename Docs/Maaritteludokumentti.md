Määrittelydokumentti
====================

## Yleiskuvaus

Rakennan ohjelmen, joka pystyy tallentamaan annetun tekstin annettuun kuvaan kayttaen steganografiaa.
Jotta paastaan mahdollisimman pienella maaralla stegano-koodattavia bitteja, pakkaan tekstin ensin 
Huffman-koodauksella.

Samalla ohjelmalla voi myos purkaa taman tuotetun kuvan alkuperaiseksi.

## Mitä algoritmeja ja tietorakenteita toteutat työssasi

Huffman-koodaus vaatii tietorakenteina binaaripuun seka prioriteettijonon.
Steganografinen koodaus ei vaadi muuta kuin pikseleiden kolmen varikanavan kasittelya bittitasolla, 
jotta viesti saadaan piilotettua.

Piilotuksen algoritmi on yksinkertainen: kaytetaan kolmea 8-bittista varikanavaa joista jokaisesta otetaan
vahiten merkitseva bitti ja valjastetaan se viestin piilottamiseen. Taten maksimivalkoinen on RGB(254,254,254) 
koska viimeinen bitti ei ole varikoodauksessa mkana.


## Mitä ongelmaa ratkaiset ja miksi valitsit kyseiset algoritmit/tietorakenteet

Steganografialla on mahdollisuus harrastaa salaista viestintaa ja viesti pitaa pystya pakkaamaan, jotta
voidaan kayttaa mahdollisimman pienia kuvia.

Valitsin Huffmanin, koska Wikipediaa selatessa loysin tiedon etta se on oleellisena osana esim bzip2-pakkauksessa
joten halusin oppia sen. Tietorakenteet tulivat algoritmin maarittelyn mukana.

Steganografiakoodausalgoritmin valitsin Wikipedian ja tuon Data genetics -blogiartikkelin perusteella ja se
vaikutti tarpeeksi yksinkertaiselta.

## Mitä syötteitä ohjelma saa ja miten näitä käytetään

Ohjelma saa koodausvaiheessa kuvan ja tekstin (8-bittisia tavuja), jonka se Huffman-koodaa ja piilottaa siten bitteina kuvaan.
Purkuvivuilla ohjelma saa vain kuvan ja tuottaa tekstin.

*Huom.* En ole viela paattanyt miten Huffman-puun arvot jaetaan kahden osapuolen kesken -- kirjoitetaanko se esimerkiksi
kuvan metatietoihin vai tiedostonimeen vai oletetaanko se vain jaetuksi ohjelman ulkopuolella.

## Tavoitteena olevat aika- ja tilavaativuudet (m.m. O-analyysi)

Wikipedian mukaan Huffmanin aikavaativuus on  O(n log n) ja tilavaativuus O(n), koska on mahdollista etta 
sisaltona on pelkastaan uniikkeja symboleita, jolloin suuri osa Huffman-koodeista on 8-bittisia ja saastoa 
ei juuri tule. Pahimmassa tapauksessa tosiaan ei voida menna O(n) yli koska alkup. materiaali on 8-bittisia merkkeja.

## Lähteet

* http://en.wikipedia.org/wiki/Huffman_coding
* http://www.datagenetics.com/blog/march12012/index.html
