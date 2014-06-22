Tämä dokumentti on turha, ei vaadittu palautukseen, jätin runkona kuitenkin lojumaan vielä..


Suorituskykytestauksen alustava suunnitelma

Perustestauksessa voisi käyttää ainakin kahta eri karttakokoa. Pienempänä max 100x100 olevaa, ja suurempi useita satoja pikseleitä. Ainakin suuremmasta mahdollisesti useampia varioituja versioita, joissa reitinhaussa tulee paljon eri solujen läpikäyntiä. Karttoja voi olla ainakin sellainen, jossa on paljon seiniä, ja versio, johon sisältyy paljon erilaisia kustannuksia.

Jokaista karttaa kohden voisi ajaa vaikkapa viisi ajoa jokaisella algoritmilla, laskea suoritusajoista keskiarvot ja tehdä pääasiallinen vertailu niiden pohjalta.

Vertailtavia algoritmeja/heuristiikkoja tulee olemaan ainakin kolme. Dijkstra, Manhattan-heurustiikka, ja lisäksi vielä yks tällä hetkellä toteuttamaton.

Testausaikojen saamiseksi rakennetaan yksinkertainen tekstikäli, jolla pystyy valitsemaan haluamansa heuristiikan, ja suoritettavan kartan, ja käyttöliittymä kertoo sitten tarkan suoritukseen kuluneen ajan. Toistot kirjataan sitten ulkoiseen taulukkoon talteen tilastointia varten. Ainakin tämä on suunnitelmana, jos ei suorituskykytestausta tarvitse täysin automatisoida.

