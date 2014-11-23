Testausdokumentti

*Mitä on testattu, miten tämä tehtiin
*Minkälaisilla syötteillä testaus tehtiin (vertailupainotteisissa töissä tärkätä)
*Miten testit voidaan toistaa
*Ohjelman toiminnan empiirisen testauksen tulosten esittäminen graafisessa muodossa.
*Testaus on ideaalitapauksessa suoritettava ohjelma. Tällöin testi on helposti toistettavissa, mikä helpottaa toteutuksen tekoa jo varhaisessa vaiheessa. On erittäin suositeltavaa käyttää testaukseen JUnitia.

Testit

Itse toteutettujen tietorakenteiden (tira-paketti) kaikki julkiset metodit on yksikkötestattu, lukuunottamatta Pari-luokkaa. 

A*-haulla on muutama testi (haku-paketti): haku ylipäänsä toimii ja eri laskimet reiteille toimivat.

App-luokassa (käynnistysluokka) on kattavasti debug-metodeja, joilla olen toteutusvaiheessa varmistanut luokkien toimivuutta.

Useissa luokissa on lisäksi mukana debug-metodeja, jotka keräävät ja tulostavat/palauttavat tietoa luokkien toiminnasta.