Aihe: A*

Tavoitteena on yksinkertainen, helppolukuinen ja tehokas A*-toteutus harjoitustyön teeman mukaisesti omilla tietorakenteilla höystettynä.

Valitsin aiheen omien taitojeni mukaan, eli vaikeista vaihtoehdoista tämä vaikutti toteutuskelvollisimmalta.

Ohjelmaan syötetään kartta tekstitiedostona, joka koostuu seuraavista merkeistä:

aloituspiste A
lopetuspiste B
este # 
kulkemiskelpoinen maasto 0-9, missä 0 on helpointa ja 9 vaikeinta

Esimerkkikartta (helppo piirtää tekstieditorilla, joka käyttää monospace-fonttia):

0000A000000
00000000000
00234432100
01234443200
00123321000
00002210000
00000B00000

Ohjelma tulostaa lähdetiedostona käytetyn kartan, johon polku A:sta B:hen on piirretty pistein.

Maastossa liikutaan ylös, alas, oikealle ja vasemmalle (Manhattan-etäisyys).

Alustavan suunnitelman mukaisesti aion tehdä algoritmin pseudokoodin pohjalta main-luokassa annettuun karttaan. Sen jälkeen toteutan tarvittavat tietorakenteet omilla luokilla. Viimeiseksi toteutan kartan luvun tiedostosta.

Realistista O-analyysitavoitetta en oikein osaa antaa. Katsotaan, kuinka nopeasti saan algoritmin toimimaan ja siten kuinka paljon jää aikaa suorituskyvyn hiomiseen.

Etsin vielä valmiita ohjelmia, joita voisi käyttää oman toteutukseni testaamiseen. Löytämäni koodinpätkät ovat olleet joko keskeneräisiä tai liian pitkälle toteutettuja valmiita ohjelmia.

Lähteitä:
http://www.cs.helsinki.fi/u/floreen/tira2014/tira.pdf - kevään 2014 TiRa -luentomateriaali
http://en.wikipedia.org/wiki/A*_search_algorithm - perustietoa
http://www.cokeandcode.com/main/tutorials/path-finding/ - monimutkainen esimerkkiohjelma selostuksella
https://community.oracle.com/thread/2078343?start=0&tstart=0 - muutama aloittelijaystävällinen algoritminpätkä, joista voisi saada muokattua testiohjelman oman algoritmin vertailuun