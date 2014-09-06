AStarAlgoritmi, joka oli tämän harjoitustyön alkuperäinen aihe, on eräänlainen yhdistelmä Dijkstran algoritmia ja GreedyBestFirst-algoritmia. Algoritmi ottaa huomioon sekä jo kuljetun etäisyyden että arvioidun jäljellä olevan matkan.

A* on myös niinsanottu heuristinen algoritmi, mutta siitä erikoinen, että se todellakin osaa löytää parhaan mahdollisen reitin. Se, että tapahtuuko näin käytännössä, riippuu käytössä olevasta heuristiikasta. Heuristiikoista on kerrottu tarkemmin tiedostossa Heuristiikat.md.

Haku käyttää hyväkseen Prioriteettikeko-tietorakennetta, josta valitaan tutkittavaksi solmuksi aina sellainen solmu, josta jo kuljetun matkan ja arvioidun etäisyyden maaliin summa on mahdollisimman pieni. Valinta toteutetaan antamalla prioriteettikeolle konstruktorissa vertailija, joka suosii solmuja edellä kuvatulla tavalla. Aluksi lisätään aloituspiste tähän listaan. 

Niin kauan kuin tutkittavien listassa riittää alkioita, otetaan sieltä ulos seuraava, ja jos ei olla maalissa, lisätään tutkittavien listaan tämän sijainnin naapurit, joihin ei olla vielä päästy vähintään yhtä hyvää reittiä pitkin. Jatketaan tätä kunnes päästään maaliin tai tutkittavien alkioiden joukko on tyhjä, jolloin reittiä ei löytynyt.

Lähde: http://www.redblobgames.com/pathfinding/a-star/introduction.html kohta A*
