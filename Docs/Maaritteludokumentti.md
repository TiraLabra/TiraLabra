#Aihemäärittely#

##Aihe: Tiedostonpakkaus##

Tiedoston pakkaus ja purkaminen häviöttömästi Lempel-Zivin algoritmeilla. Vertailen LZ77- ja Lempel-Ziv-Welchin algoritmeja. Tarkoituksena on yleinen tiedostonpakkaus. Alustavasti testaukseen käytän tekstitiedostoja. 

###LZ77###

Abraham Lempel ja Jacob Ziv esittelivät LZ77 -algoritmin vuonna 1977. LZ77 koodaa toistuvat merkkijonot lukuparina, joka ilmoittaa etäisyyden aiempana tiedostostossa löytyvään täsmäävään merkkijonon alkuun, sekä jonon pituuden. Osumat etsitään liukuvaksi ikkunaksi(engl. sliding window) kutsutusta tietorakenteesta. Ikkunan koko ja osuman suurin sallittu pituus valitaan sopivasti, esimerkiksi 4096 tavua ja pituus 16 merkkiä, jolloin koodattu merkkijono vie kaksi tavua (etäisyys 12 bittiä, pituus 4 bittiä). 

Ensimmäistä kertaa tiedostossa esiintyvää tavua ei tietenkään voida koodata viitteenä aikaisempaan. Alkuperäisessä Lempelin ja Zivin esittämässä versiossa etäisyys-pituus -parin jälkeen kirjoitetaan poikkeamatavu sellaisenaan. James Storer ja Thomas Szymanski kuitenkin huomasivat, että on parempi lisätä ylimääräinen bitti osoittamaan, tuleeko seuraavana koodattu merkkijono vai koodaamaton tavu. 

Koska hakuja tehdään paljon, pakkaaminen LZ77:llä voi olla aikavievää. Sen sijaan purkaminen sujuu nopeasti, sillä purkajan ei tarvitse koostaa erikseen hakemistoa. 

###LZW###

Lempel-Ziw-Welch -algoritmi on Terry Welchin 1984 esittämä LZ78-algoritmin parannus. LZW käyttää hakemistoa toistuvien merkkijonojen koodaamiseen. Suorituksen alussa hakemistoon alustetaan kaikki yksittäiset merkit. Alkuperäisessä Welchin versiossa hakemistossa on 4096 paikkaa, jolloin viite vie siis 12 bittiä. Pakkaus suoritetaan seuraavasti: tallenetaan edellinen hakemistosta löytyvä merkkijono osoittimeen w. Luetaan seuraava merkki k. jos merkkijono w+k löytyy hakemistosta, asetetaan w:ksi w+k. Muutoin lisätään w+k hakemistoon, tulostetaan w, ja asetetaan w:ksi k.   
  
Purettaessa hakemisto pystytään rakentamaan uudelleen viitteistä. Tällöin osoittimen merkkijono tulostetaan, ja lisätään hakemistoon w ja seuraava merkki k, paitsi jos k on vielä tuntematon viite, lisätään pelkkä w. Tällöin on k:n viitteen oltava nyt lisättyyn hakemistomerkintään. 

