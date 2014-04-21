Projektin laajempi ongelma on kohtuullisen nopea tekoäly, joka etsii ruudukkoon sovitettavia käypiä sanoja. Lähtökohtana on täydellinen AI, jota voi myöhemmin heikentää inhimillisemmäksi. Apuna tähän on toteutettu hajautustaulukko sanojen tarkistamista varten. Jotta AI olisi nopea, on sanan löytäminen taulukosta myös oltava nopeaa.

Näiden ongelmien ratkaisuun liittyvät luokat ovat pääasiassa:
AiController
MyHashSet
HashFunction
DJB2ForStrings
PrimeNumberUtils

Projektissa on pyritty etsimään hash-funktio, jolla saataisiin mahdollisimman vähän yhteentörmäyksiä, ja joka on kohtuullisen nopea käyttää. Funktiota käytetään String-objektien hash-arvojen laskemiseen Sanapuuro-pelissä. Tässä projektissa on kokeiltu lähinnä TirA-materiaalin ehdotusta String-objektien hajautusarvojen laskemiseksi, djb2-, FNV-1a- ja MurmurHash3-hajautusfunktioita. Performanssitestauksen perusteella djb2-hajautusfunktio on nopein vaihtoehdoista ja se myös antaa vähiten yhteentörmääviä arvoja. On mahdollista, että MurmurHash3-funktiolla tapahtuisi vähemmän yhteentörmäyksiä, mikäli siinä kokeilisi eri vakioita.

Peliin on valittu käytettäväksi djb2-hajautusfunktio. Projektin alkuvaiheessa hajautusfunktiona käytettiin BigInteger-instansseja luovaa hajautusfunktiota. AI olikin kohtuuttoman hidas etsiessään sanoja, sillä läpikäytäviä permutaatioita saattoi olla jopa 40 000. AI on tai sen ainakin pitäisi olla täydellinen, eli se etsii pistearvoiltaan parhaimman sanan, jonka se saa sovittamalla kirjaimia 8x8 ruudukkoon. AI käy mahdolliset sanat läpi bruteforcella, jokaisen ruudukon solun kohdalla. Permutaatiot saadaan aikaiseksi rekursiivisella metodilla. Uusia permutaatio-olioita ei luoda, vaan samaa kirjaintaulukkoa permutoidaan aina uudelleen.

Permutaatioiden määrä riippuu pelaajien käytettävissä olevien kirjainten lukumäärästä (merkitään k). Pelissä kirjaimia on 8, joten AI käy pahimmassa tapauksessa läpi k!=8! ~ 40 000 permutaatiota, jokaisessa ruudukon solussa. Jokaisen solun kohdalla AI kokeilee permutaatiosta kirjaimia ruudukkoon jokaiseen suuntaan siinä järjestyksessä, kuin ne ovat permutaatiossa. Koska ruudukko on 8x8 (merkitään ruudukon sivun pituutta s) ja suuntia neljä, joutuu AI kokeilemaan kirjaimia s+s=8+8=16 soluun. AI siis joutuu tekemään kirjainoperaatioita k! x s x s x (s+s) ja tarkistamaan onko sana oikein s x s x k! kertaa. Operaatioita tulee siis kaiken kaikkiaan k! x s x s x (s+s) + k! x s x s = k! x 2s^3 + k! x k^2 = k! x (2s^3 + s^2) = O(k^2 x s^3). Aikavaativuus siis kasvaa hyvin nopeasti, kun käytettävien kirjainten lukumäärää nostetaan tai ruudukon kokoa nostetaan.
