# Testausdokumentti

Testaukseen käytettiin JUnitia. 

Algoritmia testattiin ajamalla automaattisia yksikkötestejä eri alku ja loppupysäkeillä sekä alkuajalla. Kehityksen helpottamiseksi käytössä oli myös testi, joka tarkkaili algoritmin palauttaman reitin kestoaikaa ja hajosi, jos aika ylitti aiemman ennalta määritellyn rajan.

Reittejä testattiin myös manuaalisesti ajamalla ohjelmaa eri reiteillä. Koska ohjelma tulostaa löydetyn reitin WKT-muodossa, voidaan reittiä tarkastella kartalla esimerkiksi tällä työkalulla: http://arthur-e.github.io/Wicket/sandbox-gmaps3.html

Ohjelman tuottamaa tulosta voi verrata suoraab tosielämän ratkaisuihin ja aikatauluihin.

## Suorituskykytestaus
Parhaiten sovelluksen suorituskykyä voi tutkia hakemalla eri pituisia reittejä.

### Automaattiset testit
Joillekkin tietorakenteille on automaattiset suorituskykytestit

Nelipuu tietorakenteelle on JUnit testi, jolla nähdään että se toimii nopeammin kuin O(n) toteutus.

Listalle on vertailu ArrayListin kanssa listalle lisäämisestä. Oma toteutus on ArrayListiä 10 kertaa nopeampi lisäyksen suhteen.

Hajautustaululle on vertailu HashMapin kanssa. Oma toteutus on lisäämisen suhteen huomattavasti hitaampi kuin Javan valmis, mutta haun suhteen ero ei ole yhtä dramaattinen.
