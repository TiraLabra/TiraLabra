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
<p>Merkitään sanojen määrää <i>n</i> ja anagrammien määrää <i>k</i>. Jos jokainen anagrammi tarkistettaisiin käymällä lista aina läpi kokonaisuudessaan, tulisi tehtävien vertailujen määräksi pahimmillaan <i>n*k</i>=264061*46230=10646939520 eli n. 10 miljardia.  Aikavaativuus on siten O(<i>n*k</i>). Validien sanojen löytäminen saattaa tällä tapaa kestää modernilla tietokoneella jopa päälle minuutin.</p>

<h4>Ratkaisu</h4>
<p>On siis käytettävä tietorakennetta, jonka avulla ei tarvitse kahlata läpi jokaista validia sanaa. Käyttämällä hajautustaulukkoja pystytään eliminoimaan monta vaihtoehtoa. Jos käytettäviä kirjaimia on kahdeksan ja aakkostoon kuuluu 26 kirjainta, niin pelkästään ensimmäisten kirjainten hajautustaulukolla pystyttäisiin karsimaan n. (26-8)/26=18/26 läpikäytävistä sanoista (osalle kirjaimista tietenkin painottuu enemmän sanoja). Kahdeksan kirjaimen anagrammia tarkistettaessa on siis pahimmillaan (jos sana on validi) etsittävä kahdeksasta eri hajautustaulukosta kirjainavainta. Eli:</p>
<ul>
<li>1. kirjainten hajautustaulukosta on etsittävä avaimena olevaa anagrammin 1. kirjainta.</li>
<li>Jos avain löytyy jatketaan etsintää sen viittaamassa hajautustaulussa samalla tavalla,...</li>
<li>kunnes löydetään anagrammin viimeinen eli 8. kirjain.</li>
</ul>
<p>Nyt jokaisen anagrammin kohdalla on tehtävä korkeintaan kahdeksan hajautustauluhakua. Ongelman aikavaativuus kutistuu luokkaan <i>8*k=O(k)</i>. Hajautustauluja käyttäen validien sanojen määrällä ei siis periaatteessa ole aikavaativuuden kannalta mitään väliä.</p>
<p>Tilavaativuus ei hajautustaulujen käytön myötä nouse merkittävästi. Oletetaan, että kaikki 264061 sanaa ovat kahdeksan kirjaimisia. Tällöin viimeisen eli 8. kirjaimen alkioita eri hajautustauluissa on <i>n</i>=264061. 7. kirjaimen alkioita on vielä vähemmän kuin 8. kirjaimen alkioita ja 6. kirjaimen alkioita vielä vähemmän kuin 7. kirjaimen alkioita, jne. Esim. 2. kirjaimen hajautustauluja on 26 ja niiden alkioita on yhteensä &lt;=26², 3. kirjaimen hajautustauluja on &lt;=26<sup>2</sup> ja niiden alkioita &lt;=26<sup>3</sup>. Hajautustaulujen tilavaativuus on siis pienempi kuin 8<i>n</i> eli O(<i>n</i>).</p>
