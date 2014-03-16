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
<p>Merkitään sanojen määrää <i>n</i> ja anagrammien määrää <i>k</i>. Jos jokainen anagrammi tarkistettaisiin käymällä lista aina läpi kokonaisuudessaan, tulisi tehtävien vertailujen määräksi pahimmillaan <i>n*k=264061*46230=10646939520</i> eli n. 10 miljardia.  Aikavaativuus on siten <i>O(n*k)</i>. Validien sanojen löytäminen saattaa tällä tapaa kestää modernilla tietokoneella jopa päälle minuutin.</p>

<h4>Ratkaisu</h4>
<p>On siis käytettävä tietorakennetta, jonka avulla ei tarvitse kahlata läpi jokaista validia sanaa. Käyttämällä hajautustaulukkoja pystytään eliminoimaan monta vaihtoehtoa. Jos käytettäviä kirjaimia on kahdeksan ja aakkostoon kuuluu 26 kirjainta, niin pelkästään ensimmäisten kirjainten hajautustaulukolla pystyttäisiin karsimaan n. <i>(26-8)/26=18/26</i> läpikäytävistä sanoista (osalle kirjaimista tietenkin painottuu enemmän sanoja). Kahdeksan kirjaimen anagrammia tarkistettaessa on pahimmillaan (jos sana on validi) etsittävä kahdeksasta eri hajautustaulukosta kirjainavainta. Eli:</p>
<ul>
<li>1. kirjaimen hajautustaulukosta on etsittävä avaimena olevaa anagrammin 1. kirjainta.</li>
<li>Jos avain löytyy jatketaan etsintää sen kohdalla olevasta hajautustaulusta samalla tavalla,...</li>
<li>kunnes löydetään anagrammin viimeinen eli 8. kirjain.</li>
</ul>
<p>Nyt jokaisen anagrammin kohdalla on tehtävä korkeintaan kahdeksan hajautustauluhakua. Ongelman aikavaativuus kutistuu luokkaan <i>8*k=O(k)</i>. Hajautustauluja käyttäen validien sanojen määrällä ei siis periaatteessa ole aikavaativuuden kannalta mitään väliä.</p>
<p>
