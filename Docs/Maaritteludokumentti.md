Tiedon pakkaus
--------------

Toteutan Huffman-koodaukseen perustuvan pakkausalgoritmin tekstitiedostojen tiivistämiseen. Pakatun tiedoston koon on tarkoitus olla 40-60 % alkuperäisen tiedoston koosta, kun tiedostot ovat riittävän isoja eivätkä merkkien esiintymistiheydet ole täysin tasaisesti jakautuneet. Pienillä tiedostoilla pakkaukseen purkamista varten liitettävät tiedot kumoavat tiivistämisestä saatavan hyödyn. Tietorakenteet, joita käytän ovat binäärpipuu ja minimikeko. 

Huffman-algoritmi toimii ajassa O(nlogn) ja vie tilaa O(n), missä n on erilaisten merkkien lukumäärä. Käytettäessä 8-bittistä ASCII-merkistöä n saa arvokseen 256. Pakattava tekstitiedosto pitää käydä läpi kaksi kertaa: ensin haetaan merkkien esiintymistiheydet ja seuraavaksi merkkien bittiesitykset vaihdetaan Huffman-algoritmin perusteella. Isoilla tiedostoilla tämä on tietenkin hidasta.

Huffman-algoritmi on yksinkertainen ja sillä saavutetaan optimaalinen tulos prefix-koodiksi, eli koodiksi, jossa minkään koodatun merkin bittiesitys ei voi olla toisen merkin bittiesityksen alkuosa. Tehokkaampiakin pakkausalgoritmeja on ja Huffman-algoritmistakin on monenlaisia muunnelmia.

Lähteet: http://en.wikipedia.org/wiki/Huffman_coding
