Rakenne
=======

Ohjelma on jaettu pienehköihin funktioihin. process_file-funktiosta tuli pitkähkö, mutta sen osat on kommentoitu. Tein myös pääohjelman, jolla voi salata tai purkaa tiedoston käyttäen ECB- (Electronic codebook) tai CBC (Cipher-block chaining) -moodeja. 


Tiedostomuoto
=============

Salattu tiedosto ei ole missään standardiformaatissa, sillä sellaista ei ole. Tiedostoon kirjoitetaan ensin salatun datan pituus tavuina (8 tavua) sekä viimeisen lohkon pituus tavuina (1 tavu). Loput tiedostosta on itse salattua dataa. CBC-moodissa Initialization Vector tallennetaan vain salatun datan alkuun.


Aika- ja tilavaativuudet
========================

Process_file-funktiota lukuunottamatta kaikissa silmukoissa käytettävät arvot kiinteitä (eli silmukat suoritetaan aina yhtä monta kertaa), joten aikavaativuus yhden lohkon osalta on O(1). Myös kaikki taulukot ovat kiinteän kokoisia, joten tilavaativuus on myös vakio.

Koska yhden lohkon salaaminen/purkaminen tapahtuu vakioajassa, on tiedoston salaamisen/purkamisen aikavaativuus O(n). Tämä näkyi myös suorituskykytestauksessa, salaus/purkamisnopeuden ollessa suunnilleen vakio. Tilavaativuus on vakio tiedostoa prosessoitaessa, sillä se tapahtuu vain yhdelle lohkolle kerrallaan.

Puutteet
========

Toteutuksesta jäi tekemättä Initalization Vectorin satunnaisgenerointi, tällä hetkellä ohjelma käyttää vakiota IV:nä (IV kuitenkin luetaan tiedostosta sitä purkaessa).
