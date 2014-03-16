<h4>Tavoite</h4>
<p>Sanapuuro-peliä on tarkoitus laajentaa kaksinpeliksi. Kaksinpeliin tulee mahdollisuus pelata tekoälyä vastaan.</p>
<p>Kaksinpelin pelimekaniikka eroaa vain muutamilta osin yksinpelistä:</p>
<ul>
<li>Molemmat pelaajat pelaavat samalla pelilaudalla.</li>
<li>Pelaajilla on vuorot. Kummallakin alustavasti 30s aikaa muodostaa sana laudalle omia kirjainpaloja käyttäen.</li>
<li>Pelaajat pyrkivät keräämään mahdollisimman paljon pisteitä ja se kummalla on enemmän pisteitä pelin päättyessä voittaa.</li>
</ul>

<h4>Ongelma</h4>
<p>Keskeisenä ongelmana on toista pelaajaa ohjaavan tekoälyn toteuttaminen siten, että se pystyy nopeasti määrittämään kahdeksasta kirjainpalikastaan muodostettavat validit sanat. Ongelman kokoluokka:</p>
<ul>
<li>Validien englanninkielisten sanojen määrä: 264 061</li>
<li>Kahdeksasta kolmeen kirjaimesta muodostettavien mahdollisten anagrammien määrä (olettaen, että kaikki aina eri kirjaimia): 8!+7!+6!+5!+4!+3!=46230</li>
</ul>
<p>Merkitään sanojen määrää <b><i>n</i></b> ja anagrammien määrää <b><i>k</i></b>. Jos jokainen anagrammi tarkistettaisiin käymällä lista aina läpi kokonaisuudessaan, tulisi tehtävien vertailujen määräksi pahimmillaan <i>n*k</i>=264061*46230=10646939520 eli n. 10 miljardia.  Aikavaativuus on siten O(<i>n*k</i>). Validien sanojen löytäminen saattaa tällä tapaa kestää modernilla tietokoneella jopa päälle minuutin.</p>

<h4>Ratkaisu</h4>
<p>On siis käytettävä tietorakennetta, jonka avulla ei tarvitse kahlata läpi jokaista validia sanaa. Käyttämällä hajautustaulukkoa ja hyvää hajautusfunktiota haku nopeutuu huomattavasti. Pääasiallinen ongelma on sopivan hajautusfunktion löytäminen.</p>
<h5>Tilavaativuus</h5>
<p>Tilavaativuus ei avoimen hajautuksen hajautustaulua käyttäessä nouse merkittävästi. Hajautustaulu on <i>n</i>-alkion kokoinen ja itse sanoja on <i>n</i>-määrä, joten kokonaistilavaativuus on O(<i>n</i>).</p>
<h5>Aikavaativuus</h5>
<p>Lisäys, haku ja poisto on mahdollista tehdä O(<i>n</i>) ajassa, jos hajautusfunktio on sopiva.</p>
<h4>Toteutettavat tietorakenteet</h4>
<p>Validien sanojen nopeaa hakua varten toteutan hajautustaulun avoimella hajautuksella. Hajautustaululle toteutan seuraavat operaatiot:</p>
<ul>
<li>Lisäys</li>
<li>Poisto</li>
<li>Haku</li>
</ul>
<p>Anagrammeille ei ole tarvetta toteuttaa tietorakennetta, sillä niitä ei tarvitse säilyttää, kun niitä yksi kerrallaan tarkistetaan.</p>
<h4>Mahdollinen laajennus</h4>
<p>
