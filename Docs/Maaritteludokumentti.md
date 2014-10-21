Aihe: A*

Tavoitteena on yksinkertainen, helppolukuinen ja tehokas A*-toteutus harjoitusty�n teeman mukaisesti omilla tietorakenteilla h�ystettyn�.

Valitsin aiheen omien taitojeni mukaan, eli vaikeista vaihtoehdoista t�m� vaikutti toteutuskelvollisimmalta.

Ohjelmaan sy�tet��n kartta tekstitiedostona, joka koostuu seuraavista merkeist�:

aloituspiste A
lopetuspiste B
este # 
kulkemiskelpoinen maasto 0-9, miss� 0 on helpointa ja 9 vaikeinta

Esimerkkikartta (helppo piirt�� tekstieditorilla, joka k�ytt�� monospace-fonttia):

0000A000000
00000000000
00234432100
01234443200
00123321000
00002210000
00000B00000

Ohjelma tulostaa l�hdetiedostona k�ytetyn kartan, johon polku A:sta B:hen on piirretty pistein.

Maastossa liikutaan yl�s, alas, oikealle ja vasemmalle (Manhattan-et�isyys).

Alustavan suunnitelman mukaisesti aion tehd� algoritmin pseudokoodin pohjalta main-luokassa annettuun karttaan. Sen j�lkeen toteutan tarvittavat tietorakenteet omilla luokilla. Viimeiseksi toteutan kartan luvun tiedostosta.

Realistista O-analyysitavoitetta en oikein osaa antaa. Katsotaan, kuinka nopeasti saan algoritmin toimimaan ja siten kuinka paljon j�� aikaa suorituskyvyn hiomiseen.

Etsin viel� valmiita ohjelmia, joita voisi k�ytt�� oman toteutukseni testaamiseen. L�yt�m�ni koodinp�tk�t ovat olleet joko keskener�isi� tai liian pitk�lle toteutettuja valmiita ohjelmia.

L�hteit�:
http://www.cs.helsinki.fi/u/floreen/tira2014/tira.pdf - kev��n 2014 TiRa -luentomateriaali
http://en.wikipedia.org/wiki/A*_search_algorithm - perustietoa
http://www.cokeandcode.com/main/tutorials/path-finding/ - monimutkainen esimerkkiohjelma selostuksella
https://community.oracle.com/thread/2078343?start=0&tstart=0 - muutama aloittelijayst�v�llinen algoritminp�tk�, joista voisi saada muokattua testiohjelman oman algoritmin vertailuun