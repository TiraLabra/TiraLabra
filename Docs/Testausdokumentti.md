#Testausdokumentaatio

Projektia on testattu sekä käsin että JUnit-testeillä. 

JUnit-testejä on laadittu sekä tietorakenteille että algoritmille.

##Reittialgoritmien testaus

Algoritmeille on kehitetty läjä JUnit-testejä, joilla testataan useamman tyyppisiä algoritmeja kuitenkin vaihtelevin kriteerein; osa testeistä koskee kaikkia algoritmeja (esim. "Algoritmi löytää suoran reitin perille", jossa on ainoastaan yksi suora reitti maaliin, jota pitkin pitää kulkea), ja toisaalta osa testeistä ei koske kaikkia (esim. Greedy Best First-algoritmilta, joka on ahne, ei voida edellyttää että se löytäisi lyhimmän mahdollisen reitin maaliin). Niitten algoritmien kohdalla, joissa ollaan kiinnostuneita lyhimmän reitin löytymisestä, ei myöskään oteta kantaa siihen, mitä reittiä pitkin perille kuljettiin, sillä lyhimpiä mahdollisia reittejä voi olla useita. Joitakin algoritmeja (käytännössä heuristisia eli A*-hakua ja Greedy Best First-hakua) testattaessa varmistetaan, että algoritmi ei lähde tutkimaan liikaa ruutuja väärään suuntaan, siis tarkistetaan onko se merkinnyt tietyt ruudut tutkituiksi vai ei. Kaikissa on varauduttu algoritmin jumiutumiseen (jota ei pitäisi normaaliolosuhteissa tapahtua lainkaan) ja jos algoritmi jää jumiin, testi hylätään.


###Suorituskykytestauksesta: 
Reittialgoritmeille on myös tehty suorituskykytestaus, jossa reittialgoritmille annetaan tahallaan 1 ms viive (jotta testi ei olisi liian riippuvainen käytetystä laitteistosta ja muista tekijöistä) ja sitten mitataan käytettyä ajan määrää ja varmistetaan, että se täyttää molemmat seuraavista kriteereistä: 1) Aikaa ei saa käyttää liikaa; jos reitin löytämiseen menee yli ylärajan, testi hylätään. 2) Aikaa ei saa käyttää liian vähän. Jos reitin löytämiseen menee aikaa alle alarajan, hidaste ei todennäköisesti ollut käytössä (kuten olisi pitänyt olla) joten suoritus hylätään. Siis: pystyäksemme toteamaan algoritmin nopeaksi on siinä oltava hidaste päällä ja sen on toimittava silti tarpeeksi nopeasti. Riippuen käytettävästä algoritmista ylä- ja alarajat vaihtelevat. Esimerkiksi Dijkstran algoritmi ei monessakaan tilanteessa yllä suorituskyvyllisesti lähellekään Greedy Best First-algoritmia, joten Dijkstran algoritmi ei pääse Greedy Best Firstin testeistäkään läpi. Esimerkiksi Greedy Best First-algoritmin hyväksyttävän toiminta-ajan yläraja on tätä dokumenttia kirjoitettaessa 1 ms hidasteen kanssa 500 ms, kun taas Dijkstran algoritmin yläraja on 2000 ms.

Algoritmien toiminta-ajoista ja reiteistä ei laadittu eksaktia vertailua tarkkojen tilastojen tai visuaalisten graafien muodossa, sen sijaan projektityössä keskityttiin käyttöliittymän toteuttamiseen niin, että käyttäjä voi itse vertailla algoritmien toimintaa erilaisissa ympäristöissä erilaisilla heuristiikoilla.

##Tietorakenteiden testaus

Prioriteettikeolle, Jonolle ja Listalle tehtiin testejä, jollaiset ovat tyypillisiä alkioita varastoiville tietorakenteille. Testissä prioriteettikekoon työnnetään Integer ja String -tyyppisiä olioita, ja katsotaan että ne tulevat ulos oikeassa järjestyksessä. Testejä laadittaessa referenssinä käytettiin valmista Javan PriorityQueue -luokkaa, ja testit laadittiin siten että ne menevät läpi jos testattava luokka toimii samaan tapaan kuin PriorityQueue. Myös Jonoon työnnetään olioita ja katsotaan että ne tulevat ulos jonomaisessa järjestyksessä. Samalla pidetään huolta siitä, että jonon koko kasvaa ja pienenee alkioiden määrän mukaan. Listalle tehtiin testejä, jossa testataan että listaan työnnetyt alkiot löytyvät listasta indeksin perusteella, listasta poistaminen toimii ja listan alkioita voidaan käydä läpi (iteroida) iteraattorin avulla.
