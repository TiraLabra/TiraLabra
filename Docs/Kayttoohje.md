Kääntäminen
===========

Kääntäminen tapahtuu ajamalla komento `make` hakemistossa SalausSoppa. Tämän pitäisi tuottaa binääri ssoppa.

Tiedoston salaaminen/purkaminen
===============================

Salaaminen tapahtuu seuraavasti: 

* ECB-moodissa: `./ssoppa -e --ecb key input output`
* CBC-moodissa: `./ssoppa -e --cbc key input output`

Purkaminen tapahtuu seuraavasti: 

* ECB-moodissa: `./ssoppa -d --ecb key input output`
* CBC-moodissa: `./ssoppa -d --cbc key input output`

Yksikkötestien ajaminen
================

Yksikkötestit voi ajaa komentamalla `make test` hakemistossa SalausSoppa.


Salausmoodien vertailu BMP-tiedostoilla
=======================================

ECB- ja CBC-moodien tuottaman salaustuloksen vertailuun löytyy apuohjelma copy_bmp_headers hakemistosta SalausSoppa/sample/. Koska BMP-tiedoston otsakkeet salautuvat myös, eivät kuvankatseluohjelmat osaa näyttää salattua tiedostoa. Salattu pikselidata saadaan kuitenkin helposti selville arvaamalla otsakkeet - tai kopioimalla ne alkuperäisestä ;)

Esimerkki:

    cd SalausSoppa/sample

	# salataan input.bmp
	../ssoppa -e --ecb keyfile.key input.bmp encrypted.bmp

	# kopioidaan salattuun versioon otsakkeet, jotta kuva saadaan näkyviin
	./copy_bmp_headers input.bmp encrypted.bmp

