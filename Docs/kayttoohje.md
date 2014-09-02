
Ohjelman jar-tiedosto löytyy repositoryn juuresta. Ohjelman ajamiseen tai testaamiseen ei tarvita erityisiä tiedostoja, jos haluaa käyttää omia matriisejaan niin LibreOfficella saa helposti generoitua satunnaisia matriiseja.


Ohjelmaa käytetään komentoriviltä. Kun komentoriville kirjoittaa "komennot" ohjelma tulostaa listan kaikista käytettävissä olevista komennoista. Tässä on käytetty itse keksittyä notaatiota, jossa hakasulkuihin kirjoitetetut kohdat ovat yleisiä ilmauksia, jotka käyttäjän pitää korjata tekstin mukaisella asialla. Esim. "kaanna [matriisi] [tulosmatriisi]", tarkoittaa, että kääntömatriisin laskemiseksi tulee antaa syötteenä "kaanna", jonkin aiemmin syötetyn matriisin nimi ja uuden tulosmatriisin nimi. Yleisesti ottaen ohjelman syntaksissa ensin nimetään operaatio, sitten operandi(t) ja lopuksi annetaan nimi tulosmatriisille. Tuloksen saa sitten halutessaan nähtäville komennolla "tulosta". Poikkeuksena esim. determinantti, jonka tulos tulostetaan suoraan ruudulle. Ohjelman suorituksen voi keskeyttää komennolla "quit".  


Matriiseja voi syöttää ohjelmaan joko käsin kirjoittamalla niiden alkioita (jolloin käytetään matriisi komentoa), tai lukemalla .csv-tyyppisen tiedoston tiedosto-komennolla. On tärkeää, että tiedosto on täsmälleen oikeanlainen: siinä ei saa olla mitään ylimääräisiä merkkejä, matriisin sarakkeiden tulee olla eroteltuja pilkulla ja rivien rivinvaihdolla. Esim. LibreOffice Calc -ohjelman ".csv(text)"-formaatti on oletuksena sopiva. 

Käyttäjän operaatioiden tulokset tallennetaan ohjelman suorituksen ajaksi listaan, ja niihin viitataan nille annettujen nimien perusteella. Kaikki listalla olevat matriisit saa näkyviin komennolla "lista". Matriiseja ei ole mahdollista tallentaa kiintolevylle.

Tavallisen taulukkona tallennetun matriisin voi muuntaa Yale-matriisi muotoon komennolla "sparse [matriisin nimi]". Tällöin kertolaskun yale-matriisin ja tavallisen matriisin kanssa saa laskettua hieman tehokkaammin. Laskun tulos on jälleen matriisimuodossa.

Käyttäjän kannattaa huomata, että ohjelman antamat virheilmoitukset ovat aivan mitä sattuu, niitä ei siis kannata uskoa. Tyypillisesti virheet johtuvat matriisien vääristä dimensioista, kertoi ohjelma tästä tai ei.
