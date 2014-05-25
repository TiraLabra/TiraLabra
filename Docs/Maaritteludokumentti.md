Määrittelydokumentti
====================

<a href="http://fi.wikipedia.org/wiki/Othello_%28lautapeli%29">Othello</a>

<h2>Käytetyt algoritmit ja tietorakenteet</h2>
* Alfa-/beta-pruunaus
* Lista
* Jonkinlainen järjestysalgoritmi

<h2>Ongelma</h2>
Toteuttaa tekoäly, joka osaa pelata Othelloa riittävän hyvin voittaakseen amatöörin (ei välttämättä 100% ajasta). Alfa-/beta-pruunauksella yritetään löytää paras siirto kullakin vuorolla tutkimalla pelipuuta niin syvälle kuin vain mahdollista, jättäen käymättä läpi selvästi huonot haarat. Haarojen läpikäynti-järjestys valitaan siirrolla saatujen kiekkojen mukaan.

<h2>Syötteet</h2>
Ohjelma saa syötteekseen pelaajan tekemiä siirtoja, alkuun ainakin tekstikäyttöliittymässä merkkijonoina (A-H)(1-8) missä kirjaimet esittävät sarakkeita ja numerot rivejä. Ohjelma osaa kertoa ovatko siirrot laillisia, jonka lisäksi ohjelma löytää ja kääntää kunkin siirron kääntämät kiekot.

<h2>Aika- ja tilavaativuudet</h2>
Aikavuutta on tässä vaiheessa vaikea arvioida, sillä ei ole vielä täysin selvää millaisen alfa-/beta-pruunauksen toteutan, mitä asioita otan huomioon siirtoa arvioitessa jne.. Tavoitteena kuitenkin olisi, että mikään vuoro ei kestäisi muutamaa sekuntia pitempään. Tilavaativuuden tavoitteena olisi lineearinen.

<h2>Lähteet</h2>
- http://en.wikipedia.org/wiki/Computer_Othello
- http://en.wikipedia.org/wiki/Alpha%E2%80%93beta_pruning
- http://www.radagast.se/othello/howto.html

