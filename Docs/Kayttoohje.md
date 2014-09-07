#Käyttöohje

Tämä on reittialgoritmien visualisointityökalu. Suoritettava JAR-tiedosto löytyy projektin juurihakemistosta.

Sovelluksessa voidaan piirtää ruudukkoon seiniä, lattiaa ja muutamaa eri maastontyyppiä, määrittää niiden liikkumiskustannukset ja sen, pystyykö liikkumaan vinottain.

Sovelluksessa voidaan ladata maailma kuvatiedostosta. Esimerkkikuvana on labyrintti.png -niminen kuvatiedosto img -hakemistossa. Maailman tallettaminen kuvatiedostoon jäi ikävästi toteuttamatta, mutta sovellus osaa ladata millä tahansa kuvankäsittelyohjelmalla tehtyjä png-kuvia, joissa valkoinen väri (#FFFFFF) vastaa lattiaa, musta väri (#000000) vastaa seinää, vihreä väri (#00FF00) vastaa ruohikkoa, keltainen väri (#FFFF00) vastaa hiekkaa ja sininen väri (#0000FF) vastaa vettä.

Sovelluksessa voidaan valita käytettävä reittialgoritmi ja käytettävä heuristiikka.

Tiedossa olevat ongelmat/rajoitteet: 

Käyttäjän oletetaan käyttävän sovellusta likimain "järkevästi". Sovellus ei erityisemmin sisällä tarkistuksia käyttäjän antamille syötteille, joten on käyttäjän vastuulla esimerkiksi valita ruudukon koko järkevällä tavalla. Jos ruudukon kooksi syöttää valtavan isoja lukuja (tai ruudukkoa yrittää ladata isosta kuvatiedostosta), voi sovellus hidastua tai jopa kaatua. Sovellus voi kaatua tai tehdä jotain arvaamatonta niin ikään jos käyttäjä syöttää negativiisia lukuja ruudukon kooksi tai ruutujen liikkumiskustannuksiksi. Sovellus distribuoidaan valmiiksi paketoidun binääriformaatin lisäksi myös lähdekoodimuodossa, joten käyttäjä on toki vapaa halutessaan tekemään muutoksia sovelluksen sekä algoritmien toimintaan, vaikkei tämä varsinaisesti mikään tuettu käyttötapaus olekaan.
