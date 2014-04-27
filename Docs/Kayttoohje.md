Käyttöohje
==========

Käyttäjä voi syöttää oman kartan tai käyttää valmista karttaa, johon on asetettu lähtö- ja maaliruutu.
Ohjelma laskee lyhimmän reitin lähdöstä maaliin. Lisäksi se näyttää reitin (punaiset pisteet) ja ruudut (keltaiset pisteet), joissa piti käydä lyhimmän reitin löytämikseksi.

Ohjelma neuvoo käyttäjää sallituissa syötteissä ja kuinka ohjelmassa edetään.

Ohjelma kysyy ensin käyttäjältä, haluaako hän käyttää valmista vai omaa karttaa. Valmiin kartan saa painamalla enteriä, muuten syötetään kartan korkeus.

Omassa kartassa annetaan ensin korkeus ja leveys numeroina väliltä 1-30, jonka jälkeen syötetään kartta rivi kerrallaan. Ohjelma huomauttaa, jos korkeus tai leveys ei ole sallituissa rajoissa. Kartan koon on kuitenkin oltava vähintään 2 x 1 tai 1 x 2.

Rivi hyväksytään enterillä. Ruudut asetetaan antamalla ruudun arvo (1 pienin, 9 suurin). Lisäksi pitää sijoittaa lähtö 'L' ja maali 'M' johonkin kartalla. Kartan ruutujen arvojen on oltava väliltä 1-9, sillä ohjelma lukee kartan merkki kerrallaan (siis negatiiviset tai 9 suuremmat luvut eivät käy). Nolla-arvoiset ruudut muutetaan ykkösiksi. Lähdön ja maalin arvoiksi tulee nolla.
Jos syötetty rivi on liian pitkä tai lyhyt, rivi pitää syöttää uudelleen.
Jos käyttäjä ei aseta lähtöä 'L' ja maalia 'M', hän joutuu syöttämään koko kartan uudelleen.

Kartan syöttämisen ja samalla koko ohjelman voi keskeyttää kirjoittamalla 'exit'.

*Huom!* Mahdollisia käyttäjän virhetilanteita, joihin ei ole ohjeistusta:
- Useampi lähtö tai maali. Ohjelma lukee karttaa riveittän ja ottaa lähdöksi/maaliksi ruudun, joka on merkitty viimeisimpänä.
- Kartta, jonka koko on 1 x 1. Tämä ei ole sallittu tilanne, koska lähtö ja maali eivät mahdu tämänkokoiseen karttaan. Lähdön ja maalin on siis oltava eri ruuduissa. Ohjelman voi keskeyttää, kirjoittamalla 'exit'.
- Leveydeksi annetaan tyhjä merkkijono. Ohjelma luulee leveyttä nollaksi, jolloin kartan syöttäminen on mahdotonta. Ohjelman voi keskeyttää, kirjoittamalla 'exit'.

*Huom!* Ohjelma kaatuu, jos:
- Korkeus tai leveys ei ole luku.
- Reittiä ei ole mahdollista löytää. Ohjelma olettaa että reitti löytyy aina.
- Kartta sisältää muita merkkejä kuin L,M tai luvut 0-9.

Esim. seuraavat eivät ole sallittuja karttoja

	L91   Lab
	991   123
	11M   gfM

Kartan antamisen jälkeen ohjelma aukeaa graafiseen käyttöliittymään, josta nappia painamalla ohjelma piirtää reitin.

Jar-tiedosto löytyy projektin juuresta.
