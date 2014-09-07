#Käyttöohje

Tämä on reittialgoritmien visualisointityökalu. Suoritettava JAR-tiedosto löytyy projektin juurihakemistosta.

Sovelluksessa voidaan piirtää ruudukkoon seiniä, lattiaa ja muutamaa eri maastontyyppiä, määrittää niiden liikkumiskustannukset ja sen, pystyykö liikkumaan vinottain.

Sovelluksessa voidaan ladata maailma kuvatiedostosta. Esimerkkikuvana on labyrintti.png -niminen kuvatiedosto img -hakemistossa.

Sovelluksessa voidaan valita käytettävä reittialgoritmi ja käytettävä heuristiikka.

Tiedossa olevat ongelmat/rajoitteet: 

Käyttäjän oletetaan käyttävän sovellusta likimain "järkevästi". Sovellus ei erityisemmin sisällä tarkistuksia käyttäjän antamille syötteille, joten on käyttäjän vastuulla esimerkiksi valita ruudukon koko järkevällä tavalla. Jos ruudukon kooksi syöttää valtavan isoja lukuja, voi sovellus hidastua tai jopa kaatua. Sovellus voi kaatua tai tehdä jotain arvaamatonta niin ikään jos käyttäjä syöttää negativiisia lukuja ruudukon kooksi tai ruutujen liikkumiskustannuksiksi. Sovellus distribuoidaan valmiiksi paketoidun binääriformaatin lisäksi myös lähdekoodimuodossa, joten käyttäjä on toki vapaa halutessaan tekemään muutoksia sovelluksen sekä algoritmien toimintaan, vaikkei tämä varsinaisesti mikään tuettu käyttötapaus olekaan.
