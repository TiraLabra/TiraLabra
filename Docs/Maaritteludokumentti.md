#Tiedon tiivistys

##Aihe
Pakkaaja, joka käyttää Huffmann-koodausta pakkaukseen. 
Ohjelmalla voi myös purkaa tiivistetyn tiedon.
Ohjelman käyttöliittymä on tekstikäyttöliittymä.

##Algoritmit ja tietorakenteet
Käytän työssäni Huffaman-koodausta. Huffman-koodaus on yksi vanhimmista tiedon tiivistämiseen
tarkoitetuista algoritmeista [1].

Algoritmi vaatii tietorakenteikseen binääripuun, jonka toteutan alusta asti itse. Puu tarvitsee
solmuikseen solmu-olioita. Tämä puu on algoritmin ydin. 

Puun generoimiseen tarvitsen myös minikeon. Kekona käytän aluksi Javan valmista PriorityQueue- 
luokkaa ennen sen itse toteuttamista.

Solmu-olioita vertaillaan niiden esiintymistiheyden perusteella tekstissä, joten tarvitsen
taulukon merkkien laskemista varten. Laskemisen voisi toteuttaa myös hajautustaulukolla.

##Ohjelman toiminta
Ohjelma saa syötteekseen tekstin jonka haluaa pakata. Ohjelma palauttaa binäärikoodin käyttäjälle.

Purkamista varten käyttäjä tarvitsee binäärikoodin lisäksi puun, jolla koodi on toteutettu.
Tätä kirjoittaessa on vielä avoinna miten puu esitetään ja tallennetaan.

##Aikavaativuus tavoite
Kekoa käyttäen pakkaamisen voi saada toimimaan sillä aikavaativuudella, jolla kekokin toimii eli 
O(nlogn)

Purkaminen on nopeampaa ja puun läpikäyminen vie aikaa O(n)

##Määrittelyvaiheen luokkakaavio

![Alt text](/TiraLabra/Docs/MaarvaiheenLuokkaKaavio.jpg)


##Lähteet
http://en.wikipedia.org/wiki/Huffman_coding