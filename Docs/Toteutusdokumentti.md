## Toteutus

### Luokkarakenne

Sovellus koosituu seuraavista luokista:
* FileIO - tietostojen tallennus ja avaaminen
* HuffmanCompressor - tiedostojen pakkaamiseen (Huffman-koodaamiseen) liittyv�t osat
* HuffmanNode - Huffmanin puun solmu
* NodeHeap - minimikeko Huffmanin solmuille (k�ytet��n puun luonnissa)
* TreeOperator - Operaatioita Huffmanin puun k�sittelyyn (l�hinn� luonti)
* Main - p��sovellus

### Luokkien suhteen ja sovelluksen toiminta

Main-luokassa luodaan FileIO ja HuffmanCompressor-oliot, joilla luetaan (ja kirjoitetaan) tiedostot sek� suoritetaan pakkaus. HuffmanCompressor-luokka k�ytt�� apunaan HuffmanNode- ja TreeOperator-oliota. TreeOperatorilla luodaan Huffmanin puu (juuri on HuffmanNode) ja apuna t�ss� k�ytet��n NodeHeap-minimikekoa. Luodun puun avulla HuffmanCompressor pakkaa datan ja palauttaa sen p��ohjelmalle. Purkuvaiheessa suhteet ovat k�yt�nn�ss� samat; puu ainoastaan luodaan eri tavalla, mutta samaa TreeOperator-luokkaa k�tt�en.

### Aikavaativuudet

Pakkaaminen:
Tiedosto luetaan l�pi ensin kertalleen kun lasketaan kunkin tavun frekvenssi. T�m�n osan aikavaativuus on O(n), miss� n on tavujen lukum��r�. T�m�n j�lkeen rakennetaan keko eri tavujen frekvenssien mukaan. Keon rakentamiseen kuluu aikaa b log b, miss� b on tavujen m��r�. Koska tavuja on kuitenkin maksimissaan 256, on aika vakio suhteessa teidoston pituuteen, siisp� aikavaativuus on O(vakio + n) eli O(n). Keon avulla luodaan Huffmanin (bin��ri)puu ottamalla keosta aina kaksi p��llim�ist� solmua, luomalla n�ille uusi juurisolmu (jonka frekvenssi on lapsien yhteenlaskettu frekvenssi) ja lis��m�ll� se takaisin kekoon. T�t� toteutetaan kunnes keossa ei en�� ole kuin yksi solmu. T�h�n kuluu aikaa t�ll�in b log b, miss� b on edelleen eri tavujen lukum��r� (256), ja kokonaisaikavaativuus on siis edelleen O(n).
Jokainen tiedoston tavu korvataan uudella koodilla, jolloin aikaa kuluu lineaarisesti eli uusi aikavaativuus on O(n + n) eli siis O(n). Lopuksi lis�t��n tiedostoon puu, joka on vakiokokoinen eli kokonaisaikavaativuus pysyy edelleen samana. Siisp� lopullinen aikavaativuus on O(n)

Purkaminen:
Purkaminen eroaa Huffmanin puun k�yt�n kohdalla: Koodeja ei lasketa puusta valmiiksi, vaan puuta luetaan jokaisen pakatun tavukoodin kohdalla, kunnes koodia vastaava tavu selvi��. Aikaa t�h�n menee siis n log b, miss� n on pakattujen tavujen m��r� ja b puun solmujen m��r�. Koska puulla on kuitenkin edelleen maksimikoko, voidaan log b mielt�� vakioksi, jolloin aikavaativuus on O(vakio*n) eli O(n).

### Tilavaativuudet:

Pakkaaminen:
Kuten aikavaativuuden kohdalla huomasimme, puiden k�sittely ei oleellisesti kasvattanut aikavaativuutta, eiv�tk� my�sk��n t�ll�in vaikuta tilavaativuuteen suhteessa tiedoston kokoon. Pakkaamisen tilaa vievin kohta onkin uuden tiedoston sis�ll�n luominen. T�ss� jokaista pakattua BITTI� k�sitell��n yhten� merkkin�. Pahimmassa tapauksessa, jossa jokaista 256 tavua l�ytyy yht� monta, eik� tiedosto pakkaannu yht��n, voi siis olla 8*n tallennettavaa bitti�. T�m� on kuitenkin edelleen vakiokertoimista eli my�s tilavaativuus on O(vakio*n) eli O(n), miss� n on sy�tteen koko.

Purkaminen:
Purkamisessa vastaavasti tilaa vievin kohta on uuden tiedoston luominen, joka samoin periaattein on my�s tilavaativuudeltaan O(n).
