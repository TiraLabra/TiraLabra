Olen tähän mennessä toteuttanut kolme algoritmia:

Tavallinen binäärinen perseptroni
moniluokkainen perseptroni
monitasoinen perseptroni.

Lisäksi olen toteuttanut kaksi aputietorakennetta

vektori (taulukko jolla muutama tavallinen vektorioperaatio)
opetusdata (linkittää kaksiulotteiseen double-taulukkoon (joukko syötteitä taulukon muodossa) toisen taulukon joka sisältää toivotut tulokset syötteille)

1. Perseptroni

Binäärinen perseptronialgoritmini toteutus on varsin yksinkertainen. Se sisältää painovektorin jonka avulla voimme määritellä päätösrajapinnan kahden pisteryhmän välille. Algoritmi saa syötteenään opetusdataa ja kynnysarvon. Opetusdata käydään läpi syötetaulukon rivi kerrallaan, rivistä luodaan vektori ja lasketaan saadun vektorin pistetulo painovektorin kanssa. Jos tulos ylittää kynnysarvon, luokitellaan syöte joukkoon TRUE, muutoin joukkoon FALSE. Seuraavaksi katsotaan opetusdatan tulostaulukosta onko luokittelu oikein. Jos syöte luokiteltiin oikein, ei mitään tarvitse tehdä, jos taas syöte luokiteltiin väärin joko lisätään tai vähennetään painovektorista syötevektori. Tätä prosessia toistetaan kunnes kaikki opetusdatan vektorit on luokiteltu oikein. Tämän jälkeen voidaan jokin vektori luokitella yksinkertaisesti laskemalla pistetulo painovektorin kanssa, ja katsomalla ylittääkö laskettu arvo kynnysarvon.

1.2 Aika ja tilavaativuus

Vektorioperaatioiden aikavaativuudet ovat lineaariset O(n), sillä jokainen vektorien arvo käydään läpi kerran.

Aikaa vievä osa algoritmista on tietenkin oppiminen. Pahimmassa tapauksessa oppimisen aikavaativuus on ääretön, sillä jos datapisteet eivät ole lineaarisesti eroteltavissa jää perseptroni ikuisesti muuttelemaan painovektorinsa arvoja. //todo: tarkemmat aika- ja tilavaativuudet

2. Moniluokkainen perseptroni

Moniluokkainen perseptroni toimii varsin samalla tavalla kuin binäärinenkin. Tässä tapauksessa painovektoreita tarvitaan yhtä monta kuin jaettavia luokkiakin on, ja päätös tehdään laskemalla syötevektorin pistetulo kaikkien painovektorien kanssa ja valitsemalla suurimman tuloksen tuottanut luokka. Jos saadaan väärä vastaus, vähennetään saatua luokkaa vastaavasta painovektorista syötevektori ja lisätään oikeaa luokkaa vastaavaan painovektoriin. Tätä jälleen toistetaan kunnes kaikki opetusdatan syötteet luokitellaan oikein.

2.2 Aika- ja tilavaativuus

Jälleen oppimisen pahin aikavaativuus on ääretön. 

3. Monitasoinen perseptroni

Todo: monitasoisen perseptronin toteutuksen kuvaus, virheen suuruus jne.

