<h4>Tavoite</h4>
<p>Sanapuuro-peliä on tarkoitus laajentaa kaksinpeliksi. Kaksinpeliin tulee mahdollisuus pelata tekoälyä vastaan.</p>
<p>Kaksinpelin pelimekaniikka eroaa vain muutamilta osin yksinpelistä:</p>
<ul>
<li>Molemmat pelaajat pelaavat samalla pelilaudalla.</li>
<li>Pelaajilla on vuorot. Kummallakin alustavasti 30s aikaa muodostaa sana laudalle omia kirjainpaloja käyttäen.</li>
<li>Pelaajat pyrkivät keräämään mahdollisimman paljon pisteitä ja se kummalla on enemmän pisteitä pelin päättyessä voittaa.</li>
</ul>
<p>Tavoitteena on toteuttaa tekoäly, joka pystyy tehokkaasti määrittämään kahdeksasta kirjainpalikastaan muodostettavat validit sanat. Tekoälyn on myös tarkoitus maksimoida omat pisteensä, eli ei riitä, että se vain hakee 1. validin sanan ja tyytyy siihen. Ensisijainen tavoite on kuitenkin toteuttaa tietorakenne sanojen nopealle haulle.</p>
<h4>Ongelma</h4>
<p>Ongelmana on siis toista pelaajaa ohjaavan tekoälyn toteuttaminen siten, että se pystyy nopeasti määrittämään kahdeksasta kirjainpalikastaan muodostettavat validit sanat. Ongelman kokoluokka:</p>
<ul>
<li>Validien englanninkielisten sanojen määrä: 264 061</li>
<li>Kahdeksasta kolmeen kirjaimesta muodostettavien mahdollisten anagrammien määrä (olettaen, että kaikki aina eri kirjaimia): 8!+7!+6!+5!+4!+3!=46230</li>
</ul>
<p>Merkitään sanojen määrää <b><i>n</i></b> ja anagrammien määrää <b><i>k</i></b>. Jos jokainen anagrammi tarkistettaisiin käymällä lista aina läpi kokonaisuudessaan, tulisi tehtävien vertailujen määräksi pahimmillaan <i>n*k</i>=264061*46230=10646939520 eli n. 10 miljardia.  Aikavaativuus on siten O(<i>n*k</i>). Validien sanojen löytäminen saattaa tällä tapaa kestää modernilla tietokoneella jopa päälle minuutin.</p>

<h4>Ratkaisu</h4>
<p>On siis käytettävä tietorakennetta, jonka avulla ei tarvitse kahlata läpi jokaista validia sanaa. Käyttämällä hajautustaulukkoa ja hyvää hajautusfunktiota haku nopeutuu huomattavasti.</p>
<h5>1. tapa</h5>
<p>Koska valideja sanoja on pelin aikana aina vakio määrä, niin jos käytetään avoimen hajautuksen hajautustaulua, voidaan sen kooksi periaatteessa valita <i>n</i>. Hajautusfunktion kannalta on kuitenkin parempi käyttää isompaa taulukkoa. Sopivan hajautusfunktion löytäminen on hieman haastavampaa, koska avaimet ovat merkkijonoja.</p>
<h5>2. tapa</h5>
<p>Yksi vaihtoehto on käyttää sisäkkäisi hajautustauluja niin, että indeksi lasketaan käyttäen neljää sanan kirjainta. Ensin lasketaan paikka sanan neljästä ensimmäisestä kirjaimesta ja jos sana on pidempi niin etsitään paikan osoittamasta hajautustaulusta seuraavien neljän kirjaimen avulla. Koska yksi ASCII-kirjain on mahdollista ilmaista yhdellä tavulla eli neljällä bitillä, saadaan etumerkittömällä 32-bit integer-tyypillä neljälle kirjaimelle uniikki kokonaisluku väliltä 0,...,2<sup>32</sup>-1, josta otetaan vielä modulo taulukon koolla. Taulukon koon laskeminen ei ole ongelma, sillä avaimet eli sanat tiedetään ennalta. Javassa tämä tapa ei kuitenkaan ole mahdollinen ilman BigInteger-tyypin käyttöä, koska kielessä ei ole etumerkitöntä 32-bit integer-tyyppiä.</p>
<h5>Tilavaativuus</h5>
<p>Tilavaativuus ei avoimen hajautuksen hajautustaulua käyttäessä nouse merkittävästi. Sanoja on <i>n</i>-määrä, joten hajautustaulun kooksi voitaisiin valita 2<i>n</i>, jolloin täyttösuhde on 0.5. Avaimet eli sanat talletetaan suoraan taulukkoon. Tilavaativuus on siten O(<i>n</i>).</p>
<h5>Aikavaativuus</h5>
<p>Lisäys, haku ja poisto on mahdollista tehdä O(<i>1</i>) ajassa, jos hajautusfunktio on sopiva. Jos hajautusfunktio on huono, niin pahimmillaan operaatiot kestävät O(<i>n</i>).</p>
<h4>Toteutettavat tietorakenteet</h4>
<p>Validien sanojen nopeaa hakua varten toteutan hajautustaulun avoimella hajautuksella. Hajautustaululle toteutan seuraavat operaatiot:</p>
<ul>
<li>Lisäys</li>
<li>Poisto</li>
<li>Haku</li>
</ul>
<p>Anagrammeille ei ole tarvetta toteuttaa tietorakennetta, sillä niitä ei tarvitse säilyttää, kun niitä yksi kerrallaan tarkistetaan.</p>
<h4>Muuta huomioitavaa</h4>
<p>Toinen vaihtoehto tämän tyyppiseen hakuongelmaan olisi käyttää Trie-tietorakennetta, mutta koska hajautustaulu on tutumpi niin toteutan sen mieluummin. Tekoälyn toteutuksessa on myös jossain vaiheessa huomioitava sanojen järkevä sijoittelu. Mikäli kaksinpeli toteutetaan niin, että pelaajat eivät näe toistensa kirjaimia, niin tekoälyn ei tarvitse huolehtia muusta kuin omien pisteidensä maksimoimisesta. Jos pelaajat kuitenkin näkevät toistensa kirjaimet, on mahdollista taktikoida niin, että toinen pelaaja ei välttämättä pysty muodostamaan jotakin korkean pistemäärän sanaa. En kuitenkaan tiedä ajattelisivatko ihmispelaajat niin pitkälle ottaen huomioon, että aikaa on vain 30s.</p>
