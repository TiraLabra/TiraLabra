Ohjelman rakenne:
 --------------
Ohjelman keskeisin komponentti on PuunTutkija luokka, joka vastaa hakupuiden suorituskyvyn testauksesta. Luokalle annetaan luomisen yhteydessä tutkittavat hakupuut, joihin suoritettavat mittaukset sitten kohdistetaan. Suorituskyvyn tutkimiseen luokka käyttää Sekuntikello luokkaa, jolla voidaan mitata suoritukseen kuluvaa aikaa nanosekunteina.
Mitatut ajat talletetaan Mittaustulos olioon, joka kirjaa tulokset ja laskee näistä tilastoja. Useita mittaustuloksia voidaan tallettaa Vertailu olioon helppoa siirtämistä ja tulostusta varten. Yhteen vertailu olioon on tarkoitus tallettaa joko saman tehtävän suoritusta koskevat Mittaustulos-oliot eri puilla tai useiden eri suoritusten Mittaustulokset tietyllä puulla.
Ohjelman käyttöliittymä saa parametrina Puuntutkija olion. Käyttöliittymän vastuulla on antaa PuunTutkijalle tietojoukot ja kuvaukset suoritettaviin mittauksiin.

Saavutetut tila ja aika vaativuudet:
 ----------------
(Tyhjää)

Suorituskyvyvertailu:
 ----------------
(Tyhjää)


Puutteet ja parannusehdotukset:
* Täytä yllä olevat kohdat
* Lisää tietojoukkojen yms haku tiedostosta
* Refaktoroi kaikki tietorakenteet
* Implementoi solmuille ja puille yli-ala -luokka rakenne
* Lisää kommentteja
* Paranna JUnitin rivikattavuutta
* Etsi tapa testata tulostetta JUnitilla
* Graafinen käyttöliittymä ja/tai puiden tulostus


Lähteet:
* www.wikipedia.com/*
* www.wikipedia.fi/*