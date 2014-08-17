Työn aihe: Hajautustaulun eri toteutuksien suorituskykyvertailu. Ylivuotolistat vs Avoin hajautus (lineaarinen ja neliöllinen)



Työssä käytettävät algoritmit ja tietorakenteet

Työssä toteutetaan hajautustauluja eri törmäyksenkäsittelymetodein (Ylivuotolista ja avoin hajautus (lineaarinen ja neliöllinen)). Ylivuotolistallisen hajautustaulun tapauksessa sitä tukevaksi tietorakenteeksi tarvitaan myös (kahteen suuntaan) linkitetty lista. Avoimessa hajautuksessa sitä ei tarvita. Koska kyseessä ovat hajautustaulut tulee luonnollisesti toteuttaa myös hajautusfunktio. Hajautustauluihin toteutetaan ainakin lisäys, poisto ja haku. Minimi/maksimi/seuraava/edellinen operaatioita ei ainakaan aluksi toteuteta, ne kun ovat myös melko hitaita hajautustauluissa ( O(n) ).



Ratkaistava ongelma

Työssä ei niinkään ole ratkaistavaa ongelmaa, vaan hajautustaulun eri toteutustapojen operaatioiden suorituskykyä vertaillaan erilaisilla testidatoilla. Mielenkiinnon kohteena on kuinka paljon hajautustaulun operaatiot vievät aikaa ja muistia eri hajautustauluissa. 
Valitsin työn aiheeksi hajautustaulut, koska ne vaikuttivat mielenkiintoisilta jo Tietorakenteet ja Algoritmit -kurssilla ja olen usein törmännyt niihin myös töissä sitä ennen (ja sen jälkeen).



Ohjelman syötteet

Ohjelman syötteenä on testidataa, joka sijoitetaan hajautustauluihin. Testidata voi olla esimerkiksi tuhat, kymmenentuhatta tai satatuhatta alkiota hajautustauluun generoiva luuppi. Testidatan avulla voimme käyttää hajautustaulun operaatioita, kuten hakuja, ja vertailla niiden nopeutta ja hajautustaulujen muistinkäyttöä. Esimerkiksi pseudorandomilla testidatalla saamme aikaan todennäköisesti jonkin verran yhteentörmäyksiä hajautustaulussa. On varmastikin tarpeen mukaista myös tarkoituksella syöttää testidataa joka aiheuttaa paljon yhteentörmäyksiä, jotta saamme esiin eri yhteentörmäyksenkäsittelymetodien suorituskykyerot. Voimme kuitenkin testata eroja myös säätelemällä taulukon täyttösuhdetta, eli rajoittamalla sen kokoa verrattuna testidataan.



Tavoitteena olevat aika- ja tilavaativuudet

Hajautustaulujen operaatioiden aikavaativuudet ovat seuraavat:
-Lisääminen = Jos hajautusfunktion arvon laskeminen oletetaan vakioaikaiseksi, on lisääminen vakioaikainen eli O(1), koska lisääminen linkitettyyn listaan tai taulukkoon on vakioaikainen operaatio. Pitkän kokeilujonon tai uudelleenhajautuksen tapahtuessa tämänkin operaatio aikavaatimus tosin voi kasvaa suureksikin ( O(n) ), riippuen myös siitä kuinka usein uudelleenhajautus tehdään.
-Poistaminen = Jos hajautusfunktion arvon laskeminen oletetaan vakioaikaiseksi, operaatio on vakioaikainen eli O(1) jos viite linkitetyn listan solmuun on tallessa, koska poistaminen linkitetystä listasta on vakioaikainen operaatio. Muuten voi olla jopa O(n), koska täytyy suorittaa haku ennen kuin poisto on mahdollista.
-Haku = tavoitteena keskimäärin vakioaikainen eli O(1), mutta kuten tiedämme pahimmassa tapauksessa voi viedä aikaa jopa O(n), jos kaikkien avainten kohdalla on sattunut yhteentörmäys.

Jokainen operaatio on siis aikavaativuudeltaan keskimäärin O(1), mutta pahimmassa tapauksessa O(n). Ohjelmassa pyritään siis saavuttamaan nämä hajautustaulun normaalit aikavaativuudet. Hajautustauluihin valitaan todennäköisesti satunnaiselle alfanumeeriselle datalle sopiva hajautusfunktio. Tarkoituksena kun ei tietenkään ole eliminoida yhteentörmäyksiä, joka tosin on mahdollista jos testidata tiedetään. Työn tarkoituksena on kuitenkin pitkälti vertailla yhteentörmäyksien käsittelyn suorituskykyä eri hajautustaulujen kesken.

Hajautustaulujen tilavaativuus on O(n), koska jokainen alkio täytyy tallettaa hajautustauluun. Avoimella hajautuksella toteutettu hajautustaulu vienee enemmän tilaa kuin ylivuotolistoilla toteutettu, koska taulukon täytyy olla suurempi tai vähintään yhtä suuri kuin siihen tallennettavien alkioiden määrä. Lähes aina kuitenkin huomattavasti suurempi, koska täyttösuhteen lähestyessä yhtä avoimella hajautuksella toteutetun hajautustaulun operaatiot hidastuvat huomattavasti.



Lähteet:
http://www.cs.helsinki.fi/u/floreen/tira2013syksy/tira.pdf
https://github.com/TiraLabra/Loppukesa-2014/wiki/Dokumentaatio
