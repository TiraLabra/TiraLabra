# Suunnitteludokumentti

Pidän tässä dokumentissa kirjaa projektisuunnitelmista, ja erityisesti listaa
vielä tekemättömistä asioista.

## TODO

Dokumentointi ja testaus:
- Trielle kattava testaus toiminnallisuuden suhteen
- Builderin testaus
- Performanssitestaus
- Koodin kommentointi ja godocin tuottaminen.

Muistinkäyttö
- Nodejen allokointi taulukkoon ja 64-bittisten pointtereiden korvaaminen 16-bittisillä indekseillä.
- Harvoin käytettyjen nodejen karsinta
- Kielitaulukon toteutus tehokkaammin

- Lisää oppimisdataa

## DONE

### 2014-08-10
- Koodin sisäiset kommentit kuntoon

### 2014-08-05-10
- Statistiikkojen duunausta
- Satunnaista parantelua

### 2014-08-04
- Projektin perusrakenne 
- Trien perustoiminnallisuus

### 2014-08-05

- Trielle lisätoiminnallisuutta: esim. funktiot jotka palauttavat viittauksen nodeen
jolle operaatio on suoritettu.
- Builderin perustoiminnallisuus

## POHDINTOJA

- Projektin dokumentaation kieli voi olla suomi, mutta koodin ja kommenttien kieleksi
kyllä englanti.

###2014-08-06

- Yhden noden koko on aivan liian suuri. 2080 tavua per node johtaa esim. pienellä testausdatalla syntyvän 2227 noden määrälläkin 4,4 megatavun muistinkäyttöön. Jos n-grammien pituutta pidennetään ja testausdataa kasvatetaan niin muistinkäyttö riistäytyy käsistä.
-- ratkaisu: Tällä hetkellä node käyttää 64-bittisiä pointtereita, joita 256 kpl vie muistia 2KB. Jos nodeja tarvitsee osoittaa kuitenkin vain esim. alle 64000 kpl, niin 16-bittinen taulukkoindeksi riittäisi. Allokoidaan siis itse jättimäinen taulukko, ja kirjoitetaan nodet siihen. Näin saadaan muistinkäyttö neljäsosaan.
-- toinen seikka: nykyisellään kielifrekvenssien säilyttämiseen käytetään hashmappia, joka on kömpelö rakenne noin simppeliin käyttöön. (Plus se pitäisi kuitenkin toteuttaa itse tulevaisuudessa koska Tira.) Se kannattaa korvata taulukolla joka vie vain muutamia tavuja (esim. 8kpl short inttiä = 16 tavua)
-- muistinkäyttöä voi rajoittaa vielä karsimalla "turhaa" dataa. n-grammit jotka esiintyvät suuressakin aineistossa vain hyvin harvoja kertoja eivät tuo oikein mitään hyödyllistä. Niitä voinee karsia.
--- Karsiminen voidaan toteuttaa pitämällä aputietorakennetta nodeista, joissa on käyty vain tietyn raja-arvon alle, eräänlaista "roskalistaa". Kun roskalista täyttyy, siltä karsitaan kamaa vanhimmasta päästä.