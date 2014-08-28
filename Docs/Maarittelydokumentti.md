A*-haku

Harjoitustyön aiheena on A* -reitinetsintäalgoritmi, joka on eräänlainen laajennus Dijkstran reittialgoritmista. Siinä missä Dijkstran algoritmissa haku laajenee joka suuntaan kunnes lyhin reitti löydetään, A* -haussa otetaan huomioon kuljetun matkan lisäksi arvioitu jäljellä oleva matka. Lisäksi toteutetaan graafinen sovellus, jossa käyttäjä voi määritellä maailman (ruudukkona) ja tarkastella reittialgoritmin toimintaa tässä ympäristössä.

Syötteenä hakualgoritmi saa siis ruudukon (2-ulotteisen taulukon) ennalta määritettyjä ruutuja, joilla on oma liikkumiskustannus (esimerkiksi lattiaa pitkin kulkemisen kustannus voi olla 1, kun taas vaikkapa vettä pitkin kulkemisen kustannus voi olla 20), ja tiedon lähtö- ja maalipisteiden koordinaateista. 

A*-haussa lähdetään liikkeelle lähtösolmusta, ja lisätään sen naapurisolmut läpikäytävien solmujen listaan. Läpikäytävien solmujen listasta valitaan vuorollaan aina se solmu, jonka yhteenlaskettu jo kuljetun reitin pituuden ja arvioidun jäljelläolevan matkan summa on pienin. Siirrytään tähän solmuun, lisätään sen naapurit läpikäytävien solmujen listaan, ja jatketaan listan läpikäyntiä. Tätä läpikäyntiä jatketaan, kunnes ollaan saavuttu maaliin.

A*-hakua varten toteutetaan tietorakenne prioriteettikeko, eli keko josta saadaan poimittua alkiot priorisoidussa järjestyksessä.

Prioriteettikeko toteutetaan 1-uloitteiseen taulukkoon (array) talletetun binaaripuun avulla. Binaaripuussa on "kekoehto" joka sanelee, että jokaisen solmun pitää olla "prioriteetiltaan" suurempi kuin sen lapset. Solmujen prioriteetti katsotaan joko niihin talletettujen alkioiden luonnollisesta järjestyksestä (eli jos ne toteuttavat javan Comparable -rajapinnan, tämän vertailun tuloksen perusteella) tai sitten erillisellä Comparator-rajapinnan toteutavalla oliolla. Prioriteettikekoon lisääminen ja keosta poistaminen saadaan tehtyä nyt ajassa O(log(n)) eli logaritmisessa ajassa suhteessa alkioiden määrään eli lineaarisessa suhteessa binaaripuun korkeuteen.

Valitsin aiheen, koska reittialgoritmit ovat kiinnostavia teoreettisessa mielessä ja hyvin hyödynnettävissä tietokonepeleissä. 

Lähteet: JohTek-materiaali, TiRa-materiaali, http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
