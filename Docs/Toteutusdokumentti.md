#Ohjelman toteutus

*Haluaisin kehottaa lukijaa tutustumaan ensin ohjelman luokkakaavioon.*

##Yleinen rakenne

Ohjelman yleisrakenne perustuu erillisiin tulkki- ja laskinluokkiin. Varsinainen
ohjelman suoritus alkaa ja loppuu main-luokassa joka kommunikoi käyttäjän kanssa
rajapinnan Kayttoliittyma toteuttavan luokan välityksellä. En ehtinyt saada
ohjelmaani täysin valmiiksi ja laajennettavuuden kannalta saattaisi olla
järkevää delegoida main-luokan toimintoja esimerkiksi erillisille kontrolli-,
poikkeustenkäsittely-, ja työkaluluokille.

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

En ole ehtinyt perehtyä kovin tarkasti käyttämieni algoritmien suorituskykyyn
mutta kaikilla kokeilemillani syötteillä niiden suoritusajat ovat olleet
mikrosekuntien luokassa, jota pidän hyvänä tuloksena varsinkin säännöllisten
lausekkeiden käsittelyn osalta. Koodin rakenteen perusteella minulla on
kuitenkin syytä olettaa että saavutetut kompleksisuusluokat ovat määrittelyn
mukaiset.

Lisäksi olen toteuttanut omat tietorakenteet Hajautuskartta, Jono, Pino ja
Tilasiirtymajono jotka olen suunnitellut tarjoamaan mahdollisimman tehokkaasti
aiemmin mainittujen luokkien tarvitsemat tiedon säilömispalvelut.

* * *

##Tietorakenteet

###Suunnitteluperiaatteita

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

Joka tapauksessa kaikki tietorakenteeni ovat linkitettyjä ja niiden voi olettaa
pystyvän säilömään 0..2^31-1 alkiota ja tätä kapasiteettia voi helposti lisätä
alkioiden määrää kuvaavien kenttien vaihtamisella esimerkiksi long- tai
BigInteger-tyyppisiksi. Yleisesti ottaen ohjelman voi myös olettaa pystyvän
käsittelemään laskutoimituksia joiden tulos mahtuu int-tyyppiin tai varsin
pitkiä säännöllisiä lausekkeita ja merkkijonoja.

###Kompleksisuus

Liitteessä numero yksi näkyy diagrammeja suorituskykyvertailujeni tuloksista.
Tulokset ovat suuntaa-antavia eivätkä juurikaan vertailukelpoisia Hajautuskartan
ja kahden muun tietorakenteeni kesken. Lisäksi on syytä huomata ettei
Hajautuskartan avain-arvoparit ole uniikkeja eikä niitä voi poistaa. Tarkemmat
yksityiskohdat testeistä selviää katsomalla pakkauksen "suorituskykytestit"
luokkien koodia. Mainittakoon, että vertailussa käyttämäni HashMultimap
-tietorakenne on peräisin Googlen Guava-kirjastosta
(http://code.google.com/p/guava-libraries/) joka on Apache License versio 2.0
alainen.

Lisäksi suoritin hieman vertailua Tilasiirtymakartan ja Tilasiirtymajonon
välillä tilasiirtymien säilömisessä. Havaitsin että Tilasiirtymakarttaa
käyttävillä tiloilla rakennettu Automaatti pystyy käsittelemään
haarautumispainotteisia säännöllisiä lausekkeita 33-50% nopeammin kuin vastaava
Tilasiirtymajonoja käyttävä Automaatti. Toisaalta paljon konkatenointia
sisältävissä lausekkeissa Tilasiirtymakartta oli noin 10% hitaampi. Joka
tapauksessa kokeilemani säännölliset lausekkeet ja merkkijonot olivat hyvin
yksinkertaisia ja pidemmälle menevien johtopäätösten tekeminen esimerkiksi
aikavaativuusluokkien suhteen vaatisi järjestelmällisempää tutkimusta.

Uskon että teoriassa molempien tietorakenteiden lisäysoperaatioiden
aikavaativuus on luokkaa O(1) mikä onkin linkitetyn jonotietorakenteen kohdalla
varsin selvä asia sillä uudet alkiot lisätään aina jonon hännille ja tämä
onnistuu parin viittteen muuttamisella. Tilasiirtymakartta käyttää linkitettyjä
ylivuotolistoja jotka löytyvät hajautustaulun indekseistä jotka puolestaan
löydetään vakioaikaisen jakojäännösmenetelmää käyttävän hajautusfunktion avulla.
Näin ollen lisäysoperaatioiden yhteydessä suoritettavan byrokratian määrä ei
riipu tietorakenteiden alkioiden määristä.

Hakemisen aikavaativuus puolestaan lienee sekin (ainakin pahimmassa tapauksessa)
samassa aikavaativuusluokassa molemmilla tietorakenteilla. Tämä luokka on O(n),
sillä pahimmassa tapauksessa haluttu tilasiirtymä löydetään Tilasiirtymajonon
taikka Tilasiirtymakartan ylivuotolistan viimeisestä solmusta.

En pystynyt luotettavasti mittaamaan tietorakenteiden tilavaativuutta koska en
saanut Netbeansin profiloijaa käynnistettyä. (Profiloijalla olisi luultavasti
saanut paljon luotettavampia aikamittauksiakin tehtyä.) Epäilen kuitenkin
ylivuotolistoja käyttäviä hajautustauluja koskevan teoriatiedon perusteella että
pidemmillä lausekkeilla Tilasiirtymakarttakartta kuluttaisi paljon enemmän tilaa
kuin Tilasiirtymajono sillä se joutuu aina alustamaan kaikki hajautustaulun
ylivuotolistat riippumatta siitä tallennetaanko niihin alkioita vai ei. Hyvin
useasti (kaikissa tapauksissa haarautumista lukuunottamatta) automaatissa on
yhtä tilaa kohden 0-2 tilasiirtymää. Tällöin jos hajautustaulun pituus on
esimerkiksi alkuluku 23* ja säännöllinen kieli on `a?`, menee hukkaan melkeen
kaikki ylivuotolistat. Tämä johtuu siitä, että kyseisen kielen automaatin
alkutilasta on kaksi tilasiirtymaa (a ja tyhjä merkki) hyväksyvään lopputilaan.
Jokaisella Tila-oliolla on kuitenkin oma Tilasiirtymakarttansa joilla on omat
23 ylivuotolistaansa. Toisin sanoen tässä tapauksessa 2 merkkiä varten luodaan
56 ylivuotolistaa. Tästä syystä olen katsonut parhaaksi tinkiä hieman nopeudesta
tilan säästämiseksi ja laittanut Tila:n käyttämään Tilasiirtymajono:a mutta olen
silti jättänyt luokan Tilasiirtymakartta tavallaan vaihtoehtoiseksi
tietorakenteeksi.

Nähdäkseni molempien tietorakenteiden tilavaativuus on luokkaa
O(n) mikä on varsin triviaalisti selvää Tilasiirtymajonon kohdalla. Sama pätee
myös Tilasiirtymakarttaan, sillä vaikka aluksi alustetaankin turhia
ylivuotolistoja, niitä ei myöhemmin enää luoda lisää (paitsi ehkä
uudelleenhajautuksessa) joten tarpeeksi monen lisäyksen jälkeen
Tilasiirtymakartan alkioiden määrä ylittää ylivuotolistojen määrän ja alkaa
dominoida tilavaativuutta.

\* = joka sopinee hyvin esimerkiksi `[a-z]`-tyyppisille lausekkeille tai
pikemminkin lausekkeiden lyhenteille

##Puutteet ja parannusehdotukset

Ohjelmaan jäi ainakin yksi silmäänpistävä bugi, nimittäin Automaatti ei osaa
käsitellä oikein säännöllisiä lausekkeita, jotka ovat muotoa `[...]*`.
Uskoakseni tämä pätee yleisemminkin niissä tapauksissa joissa lausekkeen lopussa
on useampi kuin yksi peräkkäinen operaattori. Syynkin uskon tietäväni tälle
ilmiölle - nimittäin automaatti rakentaa itsensä niin, että viimeisen
operaattorin käsittely päättyy hyväksyvään tilaan (ks. esim. Automaatti.java:126
, `LAUSEKE.onTyhja()`). Luullakseni ongelma saattaisiin korjautua siten, että
välittömästi viimeistä operandia seuraavan operaattorin käsittely päättyisi
hyväksyvään tilaan, koska tämän jälkeen voisi käytännössä tulla enää
silmukkaoperaattoreita. Viimeisen operandin tunnistaminen voisi onnistua siten,
että Regextulkki laskisi ensin operandit, jonka jälkeen tieto välitettäisiin
Automaatille joka puolestaan pitäisi itse kirjaa näiden käsittelystä.

Lisäksi olisi ollut mielenkiintoista toteuttaa myös aritmeettisten kaavojen
laskeminen äärellisellä automaatilla.

Lopuksi vielä yhtenä todennäköisesti aika vaikeasti toteutettavissa olevana
parannusideana haluan mainita mahdollisuuden piirtää Automaattia kuvaava
tilasiirtymäkaavio esimerkiksi graafiseen käyttöliittymään tai kuvatiedostoon.
