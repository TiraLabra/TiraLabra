# Järjestämisalgoritmien testaus ja analysointi

## Työnkuvaus

Tämän työn tavoitteena on ohjelmoida muutamia tunnettuja järjestämisalgoritmeja, ja analysoida näiden toimintaa erilaisissa järjestämistilanteissa. Järjestämisalgoritmien toiminnasta määritellään aika- ja tilavaativuus, sekä mitataan aikaa neljällä järjestysskenaariolla; epäjärjestys, melkein järjestetty, melkein päinvastainen järjestys ja järjestetty, jossa vain viimeiset luvut ovat epäjärjestyksessä. 

Testattavia algoritmeja ovat ainakin Quicksort, Insertion Sort ja Selection Sort. Jos aikaa jää, kasvatetaan testattavien algoritmien määrää huonommin tunnetuilla algoritmeilla, kuten hitaudestaan tunnetulla Bogo-sortilla. Ensisijaisesti testattavat algoritmit on valittu niiden yleisyyden perustella, sekä tiedetyillä erilaisilla käyttäytymisillä erikokoisilla ja erilaisilla syötteillä. 

Tämän analyysin tavoitteena on selvittää, mikä algoritmi on nopein missäkin järjestämistilanteessa. Tässä analyysissä ei mitata vaativampia arvoja, kuten muistin tai prosessorin käyttöä, vaan keskitytään vain nopeuden mittaamiseen. 

## Testiskenaariot

- Epäjärjestys: Javan Random generaattoria hyödyntämällä luodaan 32-bittisiä lukuja. 
- Melkein järjestetty: Järjestetty lukujono, josta 5-10% on sekoitettu.
- Melkein päinvastainen: Sama kuin melkein järjestetty, mutta luvut suurimmasta pienimpään. 
- Järjestetty: Suuri järjestetty lukujono, jonka loppuun lisätään satunnaisia lukuja 

Testiskenaarioita luodaan eri kokoisille syötteille, jotka alustavasti ovat kooltaan 5, 10, 100, 500, 1000 ja 100000 lukua.  

## Testaus ympäristö

Kaikki ohjelmointi toteutetaan Javalla. Jokainen testi suoritetaan useasti. Tietokoneena toimii Macbook Air 1.8Gh Intel core i5, 4Gt 1600mhz DDR3, OSX 10.9.

Ajanmittaamiseen käytetään Javan valmiita kirjastoja currentTimeMillis tai nanoTime.
Tulokset tallennetaan tekstitiedostoon, josta ne viedään Exceliin graafien luontia varten. 

## Lähteet
Työ pohjautuu Helsingin yliopiston kurssin ´Tietorakenteet ja algoritmit´ luentomateriaaliin. 

