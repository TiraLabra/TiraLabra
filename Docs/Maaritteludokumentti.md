Määrittelydokumentti
====================

Aihevalinta
-----------

Aihevalintana päädyin lopulta melko perinteiseen reitinetsintäaiheeseen. Aluksi suunnittelin jonkinlaista pakkausalgoritmia, mutta
juteltuani yksilötapaamisessa Javan puuttellisista bittioperaatiosta, ja hyppäys esim. vain hieman tuttuun C:hen voisi olla kuitenkin riskitekijä kurssin aikataulussa suorituksen kannalta, valitsin helpommin lähestyttävän vaihtoehdon.

Tarkoitus on siis toteuttaa ainakin A star - etsintä, ja toivottavasti rinnalle myös joitakin vaihtoehtoisia, tai variaatioita, jotta näiden suorituskykyä voi vertailla.

Tarkoitus ei ole panostaa ohjelman hienoon ulkoasuun tai käytettävyyteen, vaan pyrkiä käyttämään resursseja nimenomaan algoritmisen puolen
oppimiseen, mikä tarkoittaa tekstipohjaista käyttöliittymää. Epäselvää on vielä, että olisiko mahdollista käyttää jotain graafista muotoa verkon ja lyhyimmän reitin esitykseen, sopivalla vaivalla.


Toteutettavat algoritmit
------------------------
* A Star

A Star -algoritmi on tarkoitettu lyhimmän reitin etsimiseen annetussa tietorakenteessa (verkko, kartta). Siinä tarkastellaan alkusolmusta alkaen lähimpiä solmuja niin, että valitaan tarkasteltavaksi aina pienimmän arvon saava funktiosta f=g+h, jossa g on kuljettu matka alusta, ja h jonkinlainen heuristiikka-arvio solmun ja maalisolmun välillä. Haku siis "suuntautuu" oikeaan suuntaan, eikä evaluoi kovin paljon huonoon suuntaan johtavia solmuja.

* perus-Dijkstra

Voidaan ajatella A Starin erikoistapauksena, jossa heuristiikan h arvo on aina nolla. 

* mahdollisesti joitain erilaisia heuristiikkoja A stariin 

Ainakin Manhattan-heuristiikka yhtenä päävaihtoehtona.

* plus näiden tarvitsemat apurakenteet

Alustavasti oma toteutus ArrayListista ja Keosta.


Ohjelman syötteet
-----------------
Ratkaisematta vielä, missä muodossa ratkaistava data annetaan. Tämä täsmentyy kun pääsen konkreettisemmin aloittamaan toteutuksen miettimistä.


Tavoitteelliset aika- ja tilavaatimukset
----------------------------------------

Dijkstran aikavaatimushan on O(|E| + |V| log |V|).
Wikipedian mukaan A starin aikavaatimuus on sidoksissa valittuun heuristiikkaan, huonoimmassa tapauksessa jopa eksponentiaalinen.


Lähteet
-------

* Kevään 2014 Tira-kurssin materiaalit
* Cormen: Introduction to Algorithms
* http://en.wikipedia.org/wiki/Dijkstra%27s_algorithm
* http://en.wikipedia.org/wiki/A*_search_algorithm
