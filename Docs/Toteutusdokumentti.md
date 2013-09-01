#Ohjelman toteutus

##Yleinen rakenne

Ohjelman yleisrakenne perustuu erillisiin tulkki- ja laskinluokkiin. Varsinainen
ohjelman suoritus alkaa ja loppuu main-luokassa joka kommunikoi käyttäjän kanssa
rajapinnan Kayttoliittyma toteuttavan luokan välityksellä (tai näin ainakin
olisi tarkoitus olla). En ehtinyt saada ohjelmaani täysin valmiiksi ja
laajennettavuuden kannalta saattaisi olla järkevää delegoida main-luokan
toimintoja esimerkiksi erillisille kontrolli-, poikkeustenkäsittely-, ja
työkaluluokille.

Tulkkiluokat perivät luokan Tulkki. Niiden tarkoituksena on muuntaa käyttäjän
antama merkkijonosyöte RPN-muotoon palauttaen Jono-olion joka sisältää kaavan
operaattorit ja operandit erillisinä merkkijonoina.

Laskinluokilla viittaan luokkiin Laskin ja Regexkasittelija. Niiden tehtävänä on
käsitellä edellä kuvattuun muotoon saatetut käyttäjän syötteet nimensä
mukaisesti. Laskin käsittelee syötettä aritmeettisena laskutoimituksena ja
Regexkasittelija tutkii täsmääkö sille annetut syötteet konstruktorin
parametrina annetun säännöllisen lausekkeen avulla. Varsinaisen työn luokka
delegoi luokalle Automaatti. Tämä välissä oleva abstraktion taso mahdollistaisi
tulevaisuudessa erillisten säännöllisten lausekkeiden käsittelyalgoritmien
käyttämisen minimaalisella koodin refaktoroinnilla. Ratkaisu olisi saattanut
olla järkevä myös Aritmeettisten kaavojen laskemiseen.

* * *

##Tietorakenteet

Olen pyrkinyt pitämään ohjelmani ja etenkin sen tietorakenteiden toteutuksen
mahdollisimman kevyenä siten että ne tarjoaisivat vain projektin (tai sen
debuggauksen) kannalta välttämättömät operaatiot. Olen siksi myös pitänyt
esimerkiksi syötteiden tarkastukset minimissään etenkin sellaisissa paikoissa
koodia joissa syötteen on tuottanut jokin ohjelman komponentti eikä käyttäjä
suoraan.

Esimerkkinä tällaisesta poisjätetystä syötteen tarkistuksesta mainittakoon
luokkien Pino ja Jono alkioiden määrää kuvaavat kentät joiden arvoa kasvatetaan
alkion lisäyksen ja pienennetään poiston yhteydessä yhdellä. Mainitun kentän
arvo on tallennettu int-muuttujaan ja on mahdollista että esimerkiksi 2^31
peräkkäisen lisäysoperaation johdosta tapahtuu aritmeettinen ylivuoto sillä
seurauksella että muuttujan arvo pyörähtää negatiiviseksi. Pidän tätä kuitenkin
jokseenkin epätodennäköisenä kaikissa kuviteltavissa olevissa käytännön
tilanteissa joten olen päättänyt ohittaa tämäntyyppiset syötteen tarkastukset
sillä ne vain omalta osaltaan lisäisivät operaatioiden kompleksisuutta.

Liitteessä numero yksi näkyy diagrammeja suorituskykyvertailujeni tuloksista.

##Algoritmit
