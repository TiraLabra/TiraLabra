# Toteutusdokumentti

## Yleistä
Ohjelma lukee käynnistyessään aikataulutiedot HSL:n zip tiedostosta ja rakentaa tarvittavat tietorakenteet siitä. Tämän jälkeen voidaan hakea reitti tietyllä lähtöhetkellä kahden pysäkin välille. Ohjelma tulostaa listan pysäkiestä ja kellonajoista jolloin niillä vieraillaan ja millä linjalla niille päästääm, sekä koordinaatit reitille. Ohjelma hyödyntää kaikkia liikennevälineitä ja osaa käyttää myös kävelyä yhtenä kulkumuotona.

## Käytetyt algoritmit
Reititys on toteutettu käyttäen A*-algoritmia. Avoimien solmujen joukko on toteutettu minimikekona. Heuristiikkana käytetään reittiin kulunutta aikaa ja arviota ajasta maaliin. Arvio on toteutettu ottamalla etäisyys solmusta maaliin ja laskemalla sinne kuluva aika olettaen, että voidaan edeteä 50 km/h. (Jälkeen päin ajatellen tuon nopeuden pitäisi ehkä olla hitaampi) Lisäksi pyritään suosimaan reittejä, joilla on mahdollisimman vähän vaihtoja.
A*-algoritmin aikavaativuus on tällä toteutuksella pahimmassa tapauksessa sama kuin Djikstran algoritmissa eli O((|E| + |V|) log |V|)

Kävely yhtenä kulkumuotona on toteutettu siten, että pysäkillä katsotaan kaikki noin 100 m säteellä olevat pysäkit. Tässä tapauksessa "noin", sillä pysäkin ympärille muodostetaan suorakulmio, jonka sivujen keskipisteet ovat 100 m päässä keskipisteestä.
Pysäkit on tallennettu Nelipuu-tietorakenteeseen, josta niitä voidaan hakea tämän suorakulmion avulla. Puun solmut kattavat tietyn koordinaattien rajaaman suorakulmasen alueen ja juurisolmu määrää puun kattaman alueen. Jokaiseen puun solmuun mahtuu neljä koordinaattia. Kun solmu on täynnä sille luodaan neljä lapsisolmua ja ne jakavat vanhempansa alueen neljään osaan. Puun rakentaminen ohjelmaa varten tarvitsee suorittaa vain kerran.
Hakuehdon tarkastus solmukohtaisesti sujuu ajassa O(1). Pahimmassa tapauksessa kaikki puun solmut on käytävä läpi. Tämä ei tietenkään ikinä toteudu tässä sovelluksessa. Puun solmujen määrä on siis n/4 eli aikavaativuus olisi O (n). Tutkittavien solmujen määrä riippuu nyt pisteiden jakautumisesta alueelle.

Koska tämä haku suoritetaan jokaiselle pysäkille tulee A*-algoritmin aikavaativuudeksi O (n ((|E| + |V|) log |V|))

## Puuttet
Reititys ei toimi aivan parhaimmalla tavalla aikataulujen kanssa, vaan ehdotettu reitti saattaa kestää useita tunteja kauemmin kuin oikeasti järkevin reitti kestäisi.
