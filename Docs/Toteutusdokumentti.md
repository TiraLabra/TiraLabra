Toteutusdokumentti

* Ohjelman yleisrakenne
* Saavutetut aika- ja tilavaativuudet (m.m. O-analyysit pseudokoodista)
* Suorituskyky- ja O-analyysivertailu (mik�li ty� vertailupainotteinen)
* Ty�n mahdolliset puutteet ja parannusehdotukset
* L�hteet


Ohjelman yleisrakenne

Ohjelma on jaettu nelj��n pakettiin: 
1) haku: A*-haku ja sen apuluokat
2) tira: Itse toteutetut tietorakenteet
3) verkko: Verkko ja sen apuluokat: kaari, solmu ja linja
	- verkko.esimerkki: Pys�kkiverkon lukeminen JSON-datasta
4) com.mycompany.tiralabra_maven: K�ynnistysmetodi ja debug-metodeja App-luokassa

Saavutetut aika- ja tilavaativuudet

Omat tietorakenteet

-PrioriteettijonoListalla
K�ytt�m�ll� t�ss� erikoistunutta prioriteettijonoa (http://en.wikipedia.org/wiki/Priority_queue#Specialized_heaps)ohjelman kannalta tarpeelliset toiminnot ovat nyt mahdollisimman tehokkaita:
*Lis�ys O(1) jos samalla prioriteetill� on jo arvoja. Jos joudutaan luomaan uusi jono prioriteetille tai kasvattamaan jonon kokoa, on vaatimus O(k) jossa k on jonon koko.
*Seuraavan arvon lukeminen (poll)