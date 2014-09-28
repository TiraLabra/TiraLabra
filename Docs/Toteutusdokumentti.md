Ohjelman rakenne:
 --------------
Ohjelman keskeisin komponentti on PuunTutkija luokka, joka vastaa hakupuiden suorituskyvyn testauksesta. Luokalle annetaan luomisen yhteydess� tutkittavat hakupuut, joihin suoritettavat mittaukset sitten kohdistetaan. Suorituskyvyn tutkimiseen luokka k�ytt�� Sekuntikello luokkaa, jolla voidaan mitata suoritukseen kuluvaa aikaa nanosekunteina.
Mitatut ajat talletetaan Mittaustulos olioon, joka kirjaa tulokset ja laskee n�ist� tilastoja. Useita mittaustuloksia voidaan tallettaa Vertailu olioon helppoa siirt�mist� ja tulostusta varten. Yhteen vertailu olioon on tarkoitus tallettaa joko saman teht�v�n suoritusta koskevat Mittaustulos-oliot eri puilla tai useiden eri suoritusten Mittaustulokset tietyll� puulla.
Ohjelman k�ytt�liittym� saa parametrina Puuntutkija olion. K�ytt�liittym�n vastuulla on antaa PuunTutkijalle tietojoukot ja kuvaukset suoritettaviin mittauksiin.

Saavutetut tila ja aika vaativuudet:
 ----------------
(Tyhj��)

Suorituskyvyvertailu:
 ----------------
(Tyhj��)


Puutteet ja parannusehdotukset:
* T�yt� yll� olevat kohdat
* Lis�� tietojoukkojen yms haku tiedostosta
* Refaktoroi kaikki tietorakenteet
* Implementoi solmuille ja puille yli-ala -luokka rakenne
* Lis�� kommentteja
* Paranna JUnitin rivikattavuutta
* Etsi tapa testata tulostetta JUnitilla
* Graafinen k�ytt�liittym� ja/tai puiden tulostus


L�hteet:
* www.wikipedia.com/*
* www.wikipedia.fi/*