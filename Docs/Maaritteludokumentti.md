Webbipohjainen lyhimmän reitin haku JavaScriptillä. 

Toteutustyökaluina JavaScript objektit, joihin tallennetaan verkon Nodejen tiedot.

- sijainti verkossa (x- ja y-koordinaatit) 
- naapurisolmut
- osa yhteyksistä yksisuuntaisia
- mahdollisuus syöttää aika/etäisyys (oletuksena pikselietäisyys / 10)
- tiedot edellisestä solmusta (jotta voidaan rakentaa lyhin reitti määränpäästä)

Käsittelyn aikana tiedot tallennetaan taulukoihin.

Algoritmina muokattu versio a*'sta (ajan salliessa vertailun vuoksi jokin toinen hakualgoritmi)

Ohjelma lukee .json-tiedoston, jonka pohjalta se rakentaa visuaalisen esityksen solmuista käyttöliittymään.

Tavoitteena dynaaminen alku- ja loppusolmun valinta sekä solmujen sijainnin randomisointi.