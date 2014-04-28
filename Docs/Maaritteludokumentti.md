##Blokus tekoäly määrittelydokumentti

Tarkoituksena on toteuttaa Ohjelmoinnin harjoitus työnä toteutettuun Blokus peliin https://github.com/Kuinka5/OhHa tekoäly.  Peliin täytyy myös toteuttaa aloitus, missä valitaan pelaajat.

Blokuksen ideana on saada enemmän laattoja asetettua laudalle kuin vastustajat. Laatan voi asettaa vain niin että se koskee jo asetettua laattaa kulmasta. Muunlainen kosketus ei ole sallittua. Laattoja voi käännellä mielensä mukaan. 

Tekoälyn tavoitteeksi tulee maksimoida ensisijaisesti maksimoida pisteensä samalla estäen vastustajia. 
Jokaisella pelaajalla on 21 laattaa, jotka voi asettaa 8 eri tavalla laudalle. Mahdollisia laattojen asettamispaikkoja on aluksi yksi, mutta määrä vaihtelee pelitilanteen mukaan. Laattaa asettaessa tulee siis painottaa siihen, että se tuottaa mahdollisimman paljon uusia mahdollisia asettamispakkoja. 

Työssä käytetään yksinkertaista parhaimman mahdollisimman siirron laskevaa algoritmia, joka käy yksinkertaisesti kaikki variaatiot läpi. Työssä hyödynnetään minimikekoa, linkitettyä listaa ja hashmappia. 
