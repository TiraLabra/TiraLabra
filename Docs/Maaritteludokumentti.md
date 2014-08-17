Jaottomien polynomien löytäminen äärellisten kuntien yli
==============

Taustaa
-------

Kunta on algebrallinen rakenne, missä jokaisella alkiolla on yhteenlaskun suhteen vasta-alkio, ja lisäksi jokaisella nollasta eroavalla alkiolla on kertolaskun käänteisalkio. Äärellinen kunta on kunta jossa on äärellisen monta alkiota. Voidaan osoittaa, että äärellisen kunnan kertaluku (sen sisältämien alkioiden määrä) on aina p^n, missä p on alkuluku. Kunnat joiden kertaluku on p voidaan muodostaa kokonaisluvuista ottamalla laskutoimitukset modulo p. Kertalukua p^n olevat kunnat konstruoidaan löytämällä astetta n oleva jaoton polynomi, ja luomalla sitten osamääräkunta tämän polynomin avulla [WikiField].

Työn tavoite
--------------

Tarkoituksena on implementoida valmis algoritmi joka testaa onko jokin polynomi jonka kertoimet ovat jossain äärellisessä kunnassa jaoton kyseisen kunnan yli. Yksi kandidaatti tähän on Rabinin jaottomuustesti [Rabin88]. Tämän algoritmin avulla tarkoitus on sitten kehittää algoritmi joka löytää tietyn asteisen jaottoman polynomin annetun äärellisen kunnan yli. Jaottomien polynomien löytämiseen ei tunneta yleisessä tapauksessa determinisistä polynomiaikaista algoritmia, joten kehitettävä algoritmi tulee käyttämään satunnaisuutta. On osoitettu, että pelkällä satunnaisella yrittämisellä tarvitaan keskimäärin O(n) kappaletta yrityksiä ennen jaottoman polynomin löytämistä [Gao97]. Tarkoituksena on yrittää kehittää jokin heuristiikka joka valitsisi polynomeja jotenkin muuten kuin tasaisen jakauman avulla. Heuristiikka voisi karsia täysin pois tiettyjä kandidaattipolynomeja ja/tai valita tietyn tyylisiä polynomeja useammin kuin toisia.

Jotta työstä ei tulisi liian laajaa, tullaan heuristiikan kehittämisessä todennäköisesti keskittymään johonkin tiettyyn kerroinkuntaan; ilmeinen vaihtoehto on kahden alkion kunta. Pienille kunnille tosin ollaan kehitetty deterministisiä polynomiaikaisia algoritmeja jaottomien polynomien löytämiseen [Shoup90], mutta tämä ei kuitenkaan poista mielenkiintoa heuristiikan kehittämisestä.
 
Tarvittavat tietorakenteet
--------------------------

Työssä pitää jollakin tavalla toteuttaa polynomien esittäminen. Tähän ajattelin käyttää kahta eri tapaa tilanteen mukaan: joko esitettään polynomi linkitettynä listana, tai sitten pelkkänä taulukkona. Taulukkoesitys on todennäköisesti nopeampi jos polynomin aste on matala, mutta sen heikkoutena on se, että laskutoimitukset joutuvat aina käymään läpi koko taulukon. Jos halutaan esittää korkea-asteinen polynomi jolla on vähän nollasta eroavia kertoimia, on linkitetty lista luultavasti tehokkaampi.

Alialgoritmeina pitää lisäksi toteuttaa polynomien yhteen-, kerto- ja jakolasku sekä Eukleideen algoritmi suurimman yhteisen tekijän löytämiseksi.

Ohjelman syötteet
-----------------

Ohjelmaan toteutetaan yksinkertainen tekstikäyttöliittymä jolle voidaan antaa haluttu kerroinkunta ja haluttu polynomin aste n, jonka jälkeen ohjelma tulostaa jonkin astetta n olevan jaottoman polynomin.

Aikavaatimustavoitteet
----------------------

On epätodennäköistä että saataisiin aikaiseksi heuristiikka joka pystyisi löytämään jaottoman polynomin keskimäärin o(n) yrityksellä. Koska täysin satunnainen yrittäminen onnistuu keskimäärin O(n) yrityksellä, on tavoitteena enemmänkin tehdä algoritmiin parannuksia ja nopeutuksia ilman aikaluokan muuttumista. Metodeja joita voidaan yrittää on polynomien järkevä valitseminen sekä sopivan polynomiesityksen (linkitetty lista/taulukko) valinta eri tapauksissa.

Lähteet
-------

[WikiField] http://en.wikipedia.org/wiki/Finite_field

[Rabin88] Rabin, M.O.: Probabilistic Algorithms in Finite Fields. SIAM Journal on Computing, 1980, Vol. 9, No. 2 : pp. 273-280.
          Katso myös http://en.wikipedia.org/wiki/Factorization_of_polynomials_over_finite_fields#Rabin.27s_test_of_irreducibility

[Gao97] Gao, S., Panario, D.: Tests and constructions of irreducible polynomials over finite fields.  Foundations of Computational Mathematics, 1997, 346-361. http://www.math.clemson.edu/~sgao/papers/GP97a.pdf

[Shoup90] Shoup, V. (1990). New algorithms for finding irreducible polynomials over finite fields. Mathematics of Computation, 54(189), 435-447. http://www.shoup.net/papers/detirred.ps

