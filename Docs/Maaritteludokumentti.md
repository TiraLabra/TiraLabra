#Määrittelydokumentti

Ohjaajien palautteesta määrittely on jaettu kahteen osaan - PLAN A ja B, joista A on kunnianhimoisempi. B on subsetti PLAN A:n ominaisuuksista, johon on tyytyminen jos taakka tuntuu liian kovalta.

##PLAN A

###Toteutettavat algoritmit ja tieto

- Trie-tietorakenne ja sen apualgoritmit
- Viterbin algoritmi

###Ongelman speksaus

Aion toteuttaa tekstipätkien kielen tunnistavan ja niitä kielen perusteella lajittelevan ohjelman.

Käytännössä esimerkiksi irc-logi voi sisältää monella eri kielellä puhuttua keskustelua. Ohjelmani lukee tekstiä, ja lajittelee, mitä kieltä kukin yksittäin pätkä on.

Lajitteluun se käyttää oppivaa algoritmia, jota koulutetaan oppimisdatan avulla.

###Tarkempi selitys

Oppimisdatasta tehdään tilastollinen malli tallentamalla, kuinka usein kussakin kielessä tietyt n-grammit, eli n:n merkin mittaiset merkkijonot esiintyvät. Mallin tallentamiseen käytetään trie-tietorakennetta. (Mikä tahansa sanakirja- tai map-tyylinen rakenne sinänsä kelpaisi, mutta trie soveltuu tähän tarkoitukseen ominaisuuksiensa puolesta erityisen hyvin.)

Oppimisvaiheen jälkeen ohjelma voi alkaa tunnistaa kieliä. Se lukee syötettä ja käyttää Viterbin algoritmia arvioimaan, mikä kieli todennäköisimmin tuottaa sen merkkijonon, mitä ohjelma on lukenut.

Valitsin yllä mainitut algoritmit, koska ne ovat standardikalustoa, kun käsitellään ns. Markovin piilomallia.

####Ongelman mallintaminen Markovin piilomallilla

Markovin piilomalli on matemaattinen malli, jolla voidaan kuvata ongelmamme kaltaista tilannetta: On arvoltaan tuntematon, satunnaisesti arvoaan vaihtava muuttuja, joka vastaa ongelmassamme "kieltä". (Muuttujan arvo voi olla esim. "suomi", "ruotsi", "englanti", "kiina", "japani", ja se saattaa joskus vaihtua esim. suomesta englanniksi.)

Tämä arvoltaan tuntematon muuttuja tuotta myös "havaintoja", minkä voimme havaita suoraan. Ongelmassamme "havainnot" ovat teksti, mitä luetaan. Se, mikä kieli tekstipätkän "taustalla" piilee, vaikuttaa havaintotekstin ominaisuuksiin, ja näiden ominaisuuksien perusteella voimme tilastollisesti päätellä/arvioida, mikä kieli tekstin taustalla on. Käytännössä ominaisuudet mistä kieltä päätellään, on tekstissä esiintävien merkkijonojen (n-grammien) tilastollinen yleisyys.

Markovin piilomallia ja siihen liittyviä algoritmeja on käytetty menestyksekkäästi samantyylisten ongelmien ratkaisuun. Cavnar and Trenkle (1994) ovat käyttäneet n-grammeista luotuja tilastollisia profiileja tämän ongelman ratkaisuun, joka muistuttaa tätä ratkaisutapaa. Dunning (1994) on myös ratkaissut ongelmaa luomalla n-grammiprofiileja suoraan tekstintiedoston tavuista, mikä myös liippaa läheltä. Harmikseni en ole voinut tutustua hänen tutkimukseensa tarkemmin.

### Syötteet

Ohjelma saa syötteenä harjoitusdataa, joka on tekstiä, jossa on tagattu erityisellä syntaksilla kieli.

Harjoitusvaiheen jälkeen ohjelma saa tekstisyötteen, jonka se prosessoi. Ulos se antaa todennäköisimmän vaihtoehdon mitä kieltä teksti on tagattuna samanlaisella syntaksilla kuin harjoitusdata.

### Aika- ja tilavaativuudet

Viterbin algoritmin aikavaativuus on O( T x |S|^2 ) missä T on tarkasteltavien peräkkäisten n-grammien määrä ja S mahdollisten eri kielten lukumäärä.

Aputietorakenne trie vie haku- ja tallennusoperaatioihin aikaa avaimen pituuden verran. Siis jos avaimen pituus on k, ja alkioiden määrä on n, niin haku ja tallennus ovat O(k).

### Lähteet

- Cavnar, William B. and John M. Trenkle. "N-Gram-Based Text Categorization". Proceedings of SDAIR-94, 3rd Annual Symposium on Document Analysis and Information Retrieval (1994)