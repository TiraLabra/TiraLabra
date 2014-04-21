Olen tähän mennessä toteuttanut kolme algoritmia:

Tavallinen binäärinen perseptroni
moniluokkainen perseptroni
monitasoinen perseptroni.

Lisäksi olen toteuttanut kaksi aputietorakennetta

vektori (taulukko jolla muutama tavallinen vektorioperaatio)
opetusdata (linkittää kaksiulotteiseen double-taulukkoon (joukko syötteitä taulukon muodossa) toisen taulukon joka sisältää 
toivotut tulokset syötteille)

1. Perseptroni

Binäärinen perseptronialgoritmini toteutus on varsin yksinkertainen. Se sisältää painovektorin jonka avulla voimme määritellä
päätösrajapinnan kahden pisteryhmän välille. Algoritmi saa syötteenään opetusdataa ja kynnysarvon. Opetusdata käydään läpi 
syötetaulukon rivi kerrallaan, rivistä luodaan vektori ja lasketaan saadun vektorin pistetulo painovektorin kanssa. Jos tulos
ylittää kynnysarvon, luokitellaan syöte joukkoon TRUE, muutoin joukkoon FALSE. Seuraavaksi katsotaan opetusdatan tulostaulukosta 
onko luokittelu oikein. Jos syöte luokiteltiin oikein, ei mitään tarvitse tehdä, jos taas syöte luokiteltiin väärin joko 
lisätään tai vähennetään painovektorista syötevektori. Tätä prosessia toistetaan kunnes kaikki opetusdatan vektorit on 
luokiteltu oikein. Tämän jälkeen voidaan jokin vektori luokitella yksinkertaisesti laskemalla pistetulo painovektorin kanssa, ja
katsomalla ylittääkö laskettu arvo kynnysarvon.

2. Moniluokkainen perseptroni

Moniluokkainen perseptroni toimii varsin samalla tavalla kuin binäärinenkin. Tässä tapauksessa painovektoreita tarvitaan yhtä 
monta kuin jaettavia luokkiakin on, ja päätös tehdään laskemalla syötevektorin pistetulo kaikkien painovektorien kanssa ja 
valitsemalla suurimman tuloksen tuottanut luokka. Jos saadaan väärä vastaus, vähennetään saatua luokkaa vastaavasta 
painovektorista syötevektori ja lisätään oikeaa luokkaa vastaavaan painovektoriin. Tätä jälleen toistetaan kunnes kaikki 
opetusdatan syötteet luokitellaan oikein.

3. Monitasoinen perseptroni

Toteutukseni koostuu kolmesta luokasta: MLPNeuron, MLPLayer ja MLPNetwork. MLPNeuron on nimensä mukaisesti yksittäinen neuroni,
joka tuntee vain omat painonsa ja osaa laskea aktivaatiofunktion arvon tietylle syötteelle. Aktivaatiofunktiona käytän
sigmoidifunktiota f(x) = 1/(1+e^(-x)), jonka arvot kuuluvat välille ]0,1[. MLPLayer verkon taso, joka sisältää yhden tai useamman
neuronin, sekä muista neuroneista erillisen bias-neuronin. MLPLayer hoitaa omien neuroniensa tulosten laskemisen. MLPNetwork
hoitaa varsinaisen backpropagation-pohjaisen oppimisen. Se sisältää toiminnallisuudet syötteen eteenpäin syöttämiseen,
virhefunktion arvon laskemiseen, virhefunktion gradienttien laskemiseen jne. Toisin kuin tavallinen perseptroni ja moniluokkainen 
perseptroni, ottaa monitasoinen toteutukseni syötteenään (tällä hetkellä) vain taulukoita, eikä toteuttamiani opetusdata ja 
vektori-tietorakenteita. Jouduin muuttamaan alkuperäistä toteutustani useampaan otteeseen algoritmissa olleiden virheiden takia, ja
siksi päädyin toteuttamaan algoritmin pelkästään taulukoita käyttäen.

4. Aika ja tilavaativuus

Vektorioperaatioiden aikavaativuudet ovat lineaariset O(n), sillä jokainen vektorien arvo käydään läpi kerran.

Aikaa vievä osa algoritmeissa on tietenkin oppiminen. Oppimisen aikavaativuuden analysointia hankaloittaa se, ettei algoritmin
kuluttama suoritusaika ole välttämättä riippuvainen opetusdatan määrästä, vaan pikemminkin siitä miten datapisteet ovat jakautuneet
avaruuteen, eli kuinka vaativa on neuroverkon ratkaisema ongelma. Monitasoisen perseptronin tapauksessa pakkaa sekoittavat vielä
mm. learn rate - vakion suuruus ja neuronien määrä eri tasoissa, sekä tietysti miten satunnaisesti valitut alkuperäiset painot
ovat asettuneet virhefunktion suhteen. Keskityn siis työssäni lähinnä aikavaativuuden empiiriseen tutkimiseen.





