Greedy Best First Search on niinsanottu heuristinen hakualgoritmi. Se tarkoittaa sitä, että se käyttää apunaan heuristiikkaa, eli arvioitua etäisyyttä maaliin. Greedy Best First Search on myös nimensä mukaisesti niinsanottu "ahne" ("greedy") algoritmi. Tämä tarkoittaa sitä, että algoritmi etenee "ahneesti" kohti maalisolmua, eikä välttämättä löydä parasta mahdollista reittiä.

Haku käyttää hyväkseen Prioriteettikeko-tietorakennetta, josta valitaan tutkittavaksi solmuksi aina sellainen solmu, josta arvioitu etäisyys maaliin on mahdollisimman pieni. Valinta toteutetaan antamalla prioriteettikeolle konstruktorissa vertailija, joka suosii solmuja edellä kuvatulla tavalla. Aluksi lisätään aloituspiste tähän listaan. 

Niin kauan kuin tutkittavien listassa riittää alkioita, otetaan sieltä ulos seuraava, ja jos ei olla maalissa, lisätään tutkittavien listaan tämän sijainnin naapurit, joissa ei olla vielä käyty. Jatketaan tätä kunnes päästään maaliin tai tutkittavien alkioiden joukko on tyhjä, jolloin reittiä ei löytynyt.

Lähde: http://www.redblobgames.com/pathfinding/a-star/introduction.html kohta Greedy Best First
