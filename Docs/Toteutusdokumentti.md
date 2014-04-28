### Yleistä

Projektin laajempi ongelma on kohtuullisen nopea tekoäly, joka etsii ruudukkoon sovitettavia käypiä sanoja. Lähtökohtana on täydellinen AI, jota voi myöhemmin heikentää inhimillisemmäksi. Apuna tähän on toteutettu hajautustaulukko sanojen tarkistamista varten. Jotta AI olisi nopea, on sanan löytäminen taulukosta myös oltava nopeaa.

Näiden ongelmien ratkaisuun liittyvät luokat ovat pääasiassa:  
AiController  
MyHashSet  
HashFunction  
DJB2ForStrings  
PrimeNumberUtils  

### Tavoitteet

Projektissa on pyritty etsimään hajautusfunktio, jolla saataisiin mahdollisimman vähän yhteentörmäyksiä, ja joka on kohtuullisen nopea käyttää, sillä tekoälyn nopeus riippuu paljon myös hajautusfunktion nopeudesta. Funktiota käytetään String-objektien hash-arvojen laskemiseen Sanapuuro-pelissä. Tässä projektissa on kokeiltu lähinnä TirA-materiaalin ehdotusta String-objektien hajautusarvojen laskemiseksi, djb2-, FNV-1a- , MurmurHash3- ja CRC32-hajautusfunktioita. Performanssitestauksessa djb2 vaikutti sopivalta kompromissilta, vaikka CRC32-algoritmilla yhteentörmäyksiä olisi hitusen vähemmän. Tekoälyä on myös pyritty jonkin verran optimoimaan mm. jättämällä johonkin soluun redundanttien permutaatioiden läpikäyminen pois.


### Toteutus ja aika-analyysi

Peliin on valittu käytettäväksi djb2-hajautusfunktio. Projektin alkuvaiheessa hajautusfunktiona käytettiin BigInteger-instansseja luovaa hajautusfunktiota. AI olikin kohtuuttoman hidas etsiessään sanoja, sillä läpikäytäviä permutaatioita saattoi olla jopa 40 000 solua kohden. AI on tai sen ainakin pitäisi olla täydellinen, eli se etsii pistearvoiltaan parhaimman sanan, jonka se saa sovittamalla kirjaimia 8x8 ruudukkoon. AI käy mahdolliset sanat läpi bruteforcella, jokaisen ruudukon solun kohdalla. Permutaatiot saadaan aikaiseksi rekursiivisella metodilla. Uusia permutaatio-olioita ei luoda, vaan samaa kirjaintaulukkoa permutoidaan aina uudelleen.

#### Permutaatiot

Permutaatioiden määrä riippuu pelaajien käytettävissä olevien kirjainten lukumäärästä (merkitään k). Pelissä kirjaimia on 8, joten AI käy pahimmassa tapauksessa läpi k!=8! ~ 40 000 permutaatiota jokaisessa ruudukon solussa. Permutaatioita luodaan permutoimalla aina taulukon häntää funktiolle annetusta indeksistä i eteenpäin. Joka kutsulla tehdään permutoitavalle taulukolle vakioaikaisia swap-operaatioita sekä tehdään rekursiivinen kutsu indeksillä i+1, ellei häntä ole tyhjä. k-kirjaimen permutaatioiden läpikäyminen on siten aikavaativuudeltaan O(k!). 

#### Permutaatioiden soveltaminen ruudukkoon

Jokaisen solun kohdalla AI kokeilee permutaatiosta kirjaimia ruudukkoon jokaiseen suuntaan siinä järjestyksessä, kuin ne ovat permutaatiossa ja luo listan, jonka kirjaimet tarkistetaan sanoista. Jos sana ei ole validi, otetaan listasta kirjain pois ja kokeillaan uudelleen kunnes kirjaimia on alle 3. Koska ruudukko on 8x8 (merkitään ruudukon sivun pituutta s) ja suuntia neljä, joutuu AI kokeilemaan kirjaimia s+s=8+8=16 soluun jokaisen solun kohdalla. AI siis joutuu tekemään kirjainoperaatioita k! * (s * s) * (s+s) ja tarkistamaan onko sana oikein melkein jokaisen kirjainoperaation kohdalla. Pelissä käytettävä djb2-hajautusfunktio käy läpi tarkistettavan merkkijonon merkki kerrallaan tehden yhden bitshift-operaation ja kaksi summa-operaatiota jokaisen merkin kohdalla, joten sen aikavaativuus on 3s, koska syötettävien sanojen maksimikoko on ruudukon sivun (s) pituuden verran. Operaatioita tulee siis kaiken kaikkiaan k! * (s * s) * (s+s) * 3s = k! * 6s^4 = O(k^2 * s^4). Aikavaativuus siis kasvaa hyvin nopeasti, kun käytettävien kirjainten lukumäärää nostetaan tai ruudukon kokoa nostetaan.

### Parantamista
Tekoälyn algoritmissa on varmasti melko paljon parannettavaa. Esim. nykyisellään vaikka koko ruudukko on tyhjä ja tekoälylle riittäisi selvittää parhain sana ensimmäisessä solussa, käy se silti kaikki solut läpi löytäen saman parhaan sanan. Olisi ehkä mahdollista soveltaa myös jonkinlaista heuristiikka ottamalla huomioon esim. sanojen konsonanttien tai vokaalien määrän. Usein AI:n ei myöskään ole tarkoitus olla täydellinen vaan AI:lle voisi esim. asettaa joko vähenevän mahdollisuuden jatkaa etsintää per solu per sanan pituus tai absoluuttisen rajan.

### Lähteet
[djb2](http://www.cse.yorku.ca/~oz/hash.html "djb2")  
[fnv-1a](http://www.isthe.com/chongo/tech/comp/fnv/ "fnv-1a")  
[MurmurHash3](https://code.google.com/p/smhasher/wiki/MurmurHash3 "murmurhash3")


