## Toteutus

### Luokkarakenne

Sovellus koosituu seuraavista luokista:
* FileIO - tietostojen tallennus ja avaaminen
* HuffmanCompressor - tiedostojen pakkaamiseen (Huffman-koodaamiseen) liittyvät osat
* HuffmanNode - Huffmanin puun solmu
* NodeHeap - minimikeko Huffmanin solmuille (käytetään puun luonnissa)
* TreeOperator - Operaatioita Huffmanin puun käsittelyyn (lähinnä luonti)
* Main - pääsovellus

### Luokkien suhteen ja sovelluksen toiminta

Main-luokassa luodaan FileIO ja HuffmanCompressor-oliot, joilla luetaan (ja kirjoitetaan) tiedostot sekä suoritetaan pakkaus. HuffmanCompressor-luokka käyttää apunaan HuffmanNode- ja TreeOperator-oliota. TreeOperatorilla luodaan Huffmanin puu (juuri on HuffmanNode) ja apuna tässä käytetään NodeHeap-minimikekoa. Luodun puun avulla HuffmanCompressor pakkaa datan ja palauttaa sen pääohjelmalle. Purkuvaiheessa suhteet ovat käytännössä samat; puu ainoastaan luodaan eri tavalla, mutta samaa TreeOperator-luokkaa kättäen.

### Aikavaativuudet

Pakkaaminen:
Tiedosto luetaan läpi ensin kertalleen kun lasketaan kunkin tavun frekvenssi. Tämän osan aikavaativuus on O(n), missä n on tavujen lukumäärä. Tämän jälkeen rakennetaan keko eri tavujen frekvenssien mukaan. Keon rakentamiseen kuluu aikaa b log b, missä b on tavujen määrä. Koska tavuja on kuitenkin maksimissaan 256, on aika vakio suhteessa teidoston pituuteen, siispä aikavaativuus on O(vakio + n) eli O(n). Keon avulla luodaan Huffmanin (binääri)puu ottamalla keosta aina kaksi päällimäistä solmua, luomalla näille uusi juurisolmu (jonka frekvenssi on lapsien yhteenlaskettu frekvenssi) ja lisäämällä se takaisin kekoon. Tätä toteutetaan kunnes keossa ei enää ole kuin yksi solmu. Tähän kuluu aikaa tällöin b log b, missä b on edelleen eri tavujen lukumäärä (256), ja kokonaisaikavaativuus on siis edelleen O(n).
Jokainen tiedoston tavu korvataan uudella koodilla, jolloin aikaa kuluu lineaarisesti eli uusi aikavaativuus on O(n + n) eli siis O(n). Lopuksi lisätään tiedostoon puu, joka on vakiokokoinen eli kokonaisaikavaativuus pysyy edelleen samana. Siispä lopullinen aikavaativuus on O(n)

Purkaminen:
Purkaminen eroaa Huffmanin puun käytön kohdalla: Koodeja ei lasketa puusta valmiiksi, vaan puuta luetaan jokaisen pakatun tavukoodin kohdalla, kunnes koodia vastaava tavu selviää. Aikaa tähän menee siis n log b, missä n on pakattujen tavujen määrä ja b puun solmujen määrä. Koska puulla on kuitenkin edelleen maksimikoko, voidaan log b mieltää vakioksi, jolloin aikavaativuus on O(vakio*n) eli O(n).

### Tilavaativuudet:

Pakkaaminen:
Kuten aikavaativuuden kohdalla huomasimme, puiden käsittely ei oleellisesti kasvattanut aikavaativuutta, eivätkä myöskään tällöin vaikuta tilavaativuuteen suhteessa tiedoston kokoon. Pakkaamisen tilaa vievin kohta onkin uuden tiedoston sisällön luominen. Tässä jokaista pakattua BITTIÄ käsitellään yhtenä merkkinä. Pahimmassa tapauksessa, jossa jokaista 256 tavua löytyy yhtä monta, eikä tiedosto pakkaannu yhtään, voi siis olla 8*n tallennettavaa bittiä. Tämä on kuitenkin edelleen vakiokertoimista eli myös tilavaativuus on O(vakio*n) eli O(n), missä n on syötteen koko.

Purkaminen:
Purkamisessa vastaavasti tilaa vievin kohta on uuden tiedoston luominen, joka samoin periaattein on myös tilavaativuudeltaan O(n).
