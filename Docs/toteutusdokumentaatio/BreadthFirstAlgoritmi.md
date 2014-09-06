Leveyssuuntainen haku eli BreadthFirstAlgoritmi on yksinkertaisin toteutetuista reittialgoritmeista. Algoritmi ei ota lainkaan huomioon ruutujen liikkumiskustannuksia ja etenee leveyssuuntaisesti joka suuntaan kunnes maaliruutu on löydetty. Tämä algoritmi ei siis löydä aina lyhintä mahdollista reittiä, jos ruudukossa on ruutuja, joilla on keskenään erit liikkumiskustannukset. Tämän algoritmin käyttäminen onkin mielekästä vain, jos ruudukossa on seinien lisäksi vain yhden tyyppisiä ruutuja (esim. lattiaa).

Haku käyttää hyväkseen Jono-tietorakennetta, jossa pidetään yllä listaa toistaisekti tutkimattomista koordinaateista. Aluksi lisätään alkupiste tähän listaan.

Niin kauan kuin tutkittavien listassa riittää alkioita, otetaan sieltä ulos seuraava, ja jos ei olla maalissa, lisätään tutkittavien listaan tämän sijainnin naapurit , joissa ei olla vielä käyty. Jatketaan tätä kunnes päästään maaliin tai tutkittavien alkioiden joukko on tyhjä, jolloin reittiä ei löytynyt.

Lähde: http://www.redblobgames.com/pathfinding/a-star/introduction.html kohta Breadth First Search
