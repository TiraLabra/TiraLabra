Testausdokumentti

*Mit� on testattu, miten t�m� tehtiin
*Mink�laisilla sy�tteill� testaus tehtiin (vertailupainotteisissa t�iss� t�rk�t�)
*Miten testit voidaan toistaa
*Ohjelman toiminnan empiirisen testauksen tulosten esitt�minen graafisessa muodossa.
*Testaus on ideaalitapauksessa suoritettava ohjelma. T�ll�in testi on helposti toistettavissa, mik� helpottaa toteutuksen tekoa jo varhaisessa vaiheessa. On eritt�in suositeltavaa k�ytt�� testaukseen JUnitia.

Testit

Itse toteutettujen tietorakenteiden (tira-paketti) kaikki julkiset metodit on yksikk�testattu, lukuunottamatta Pari-luokkaa. 

A*-haulla on muutama testi (haku-paketti): haku ylip��ns� toimii ja eri laskimet reiteille toimivat.

App-luokassa (k�ynnistysluokka) on kattavasti debug-metodeja, joilla olen toteutusvaiheessa varmistanut luokkien toimivuutta.

Useissa luokissa on lis�ksi mukana debug-metodeja, jotka ker��v�t ja tulostavat/palauttavat tietoa luokkien toiminnasta.