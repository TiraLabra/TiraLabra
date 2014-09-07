#Heuristiikkojen toteutuksesta

A\*-haussa käytettävällä heuristiikalla on olennaisesti merkitystä algoritmin toiminnan kannalta. Jos heuristiikka on optimistinen, eli ei koskaan arvioi etäisyyttä suuremmaksi kuin mikä se on todellisuudessa, algoritmi löytää aina lyhimmän mahdollisen reitin. Toisaalta mitä lähempänä heuristiikka on nollaa, sitä merkityksettömämmäksi se muuttuu ja sitä enemmän A\*-haku alkaa muistuttaa Dijkstran hakualgoritmia. Toisaalta jos heuristiikka on pessimistinen eli se yliarvioi jäljellä olevan matkan, A\*-haku ei enää välttämättä löydä lyhintä mahdollista reittiä, mutta se voi tutkia reitin nopeammin.

Tässä projektissa on toteutettu kaiken kaikkiaan neljä erilaista heuristiikkaa: Manhattan-heuristiikka, Diagonaalinen heuristiikka, Tasapelinrikkova (tie-breaking) Manhattan-heuristiikka, ja Tasapelinrikkova (tie-breaking) Diagonaalinen heuristiikka.

##Manhattan-heuristiikka

Manhattan-heuristiikassa arvioidaan jäljellä oleva etäisyys perustuen pisteiden manhattan-etäisyyteen, eli x-koordinaattien erotuksen itseisarvon ja y-koordinaattien erotuksen itseisarvon summaan. Manhattan-heuristiikan käyttö on järkevää, jos mahdolliset liikkumissuunnat ovat kohtisuoria suuntia, eli vinottain liikkuminen ei ole mahdollista.

##Diagonaalinen heuristiikka

Diagonaalinen heuristiikka tulee kysymykseen siinä vaiheessa, kun liikkuminen vinottain on mahdollista. Vinottain liikkuminen maksaa saman verran kuin vaakasuoriin suuntiin liikkuminen. Heuristiikassa huomioidaan tämä arvioimalla jäljellä oleva etäisyys olevan joko x-koordinaattien erotuksen itseisarvo tai y-koordinaattien erotuksen itseisarvo, riippuen siitä kumpi on suurempi eli kumpi asettuu jäljellä olevassa matkassa "pullonkaulaksi".

##Tasapelinrikkovat heuristiikat

Kumpikin yllä olevasta toimii erittäin mainiosti omissa tilanteissaan, paitsi että molemmissa on eräs pieni ikävä piirre: Jos liikutaan yksi ruutu maalia kohti, heuristinen arvio etäisyydestä maaliin vähenee tasan yhdellä ja toisaalta kuljettu matka kasvaa tasan yhdellä. Tämä johtaa eräällä tavalla ikävään "tasapelitilanteeseen" sellaisten tilanteiden kanssa, joissa etäisyys maalista on suurempi mutta joissa ei olla vielä liikuttu niin monta ruutua; jos siis lyhimpiä polkuja on useampia, algoritmi tulee tutkineeksi ylimääräisiä polkuja, pahimmassa tapauksessa kaikki mahdolliset "lyhimmät" polut ennen pääsyä maaliin. Tämä korjataan "tasapelin rikkovalla" ("tie breaking") -heuristiikalla, jossa heuristiikan palauttamaa arvoa nostetaan hienovaraisesti ylöspäin. Nyt algoritmi suosii sellaisia polkuja joissa ollaan otettu askel oikeaan suuntaan niiden edellä, joissa ollaan kuljettu vähemmän oikeaan suuntaan.

Käytännössä on siis toteutettu Tie-Breaking Manhattan-heuristiikka ja Tie-Breaking Diagonaalinen heuristiikka, jotka ovat muuten samanlaisia kuin esikuvansa mutta niiden palauttama arvo on tuhannesosan niitä suurempi (1.001*palautus). Tämä johtaa siihen, että nämä heuristiikat ovat aavistuksen verran pessimistisiä, mutta se tulee merkitykseen vain reiteillä, joiden pituus on yli tuhat. 

Lähde: http://theory.stanford.edu/~amitp/GameProgramming/Heuristics.html#breaking-ties
