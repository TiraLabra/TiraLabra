##Reittialgoritmien visualisointi

Harjoitustyön aiheena on toteuttaa graafinen sovellus, jossa voidaan visualisoida ja vertailla eri reittialgoritmien käyttäytymistä ja toimintaa. Sovelluksessa on toteutettu A\* -reitinetsintäalgoritmi ja sille jollain tavalla sukua olevat Breadth first search (Leveyssuuntainen haku), Dijkstram algoritmi ja Greedy Best First (Ahne paras ensin) algoritmi. Graafisessa sovelluksessa käyttäjä voi määritellä maailman (ruudukkona), tai ladata sellaisen jostain valmiista kuvatiedostosta, ja tarkastella ja vertailla reittialgoritmien toimintaa tässä ympäristössä.

A\* on eräänlainen yhdistelmä Dijkstran reittialgoritmista ja Greedy Best First -algoritmista: Siinä missä Dijkstran algoritmissa haku laajenee joka suuntaan, eli valitaan aina solmu johon jo kuljettu matka on mahdollisimman pieni, kunnes lyhin reitti löydetään, ja Greedy Best first-haussa valitaan solmu josta arvioitu etäisyys maaliin on pienin, A\* -haussa hyödynnetään näitä molempia: Seuraavaksi käsittelyyn valitaan solmu, jolle jo kuljetun matkan ja arvioidun etäisyyden maaliin summa on mahdollisimman pieni.

Syötteenä hakualgoritmi saa siis ruudukon (2-ulotteisen taulukon) ennalta määritettyjä ruutuja, joilla on oma liikkumiskustannus (esimerkiksi lattiaa pitkin kulkemisen kustannus voi olla 1, kun taas vaikkapa vettä pitkin kulkemisen kustannus voi olla 20), ja tiedon lähtö- ja maalipisteiden koordinaateista. 

A*-haussa lähdetään liikkeelle lähtösolmusta, ja lisätään sen naapurisolmut läpikäytävien solmujen listaan. Läpikäytävien solmujen listasta valitaan vuorollaan aina se solmu, jonka yhteenlaskettu jo kuljetun reitin pituuden ja arvioidun jäljelläolevan matkan summa on pienin. Siirrytään tähän solmuun, lisätään sen naapurit läpikäytävien solmujen listaan, ja jatketaan listan läpikäyntiä. Tätä läpikäyntiä jatketaan, kunnes ollaan saavuttu maaliin.

A*-hakua varten toteutetaan tietorakenne prioriteettikeko, eli keko josta saadaan poimittua alkiot priorisoidussa järjestyksessä.

Prioriteettikeko toteutetaan 1-uloitteiseen taulukkoon (array) talletetun binaaripuun avulla. Binaaripuussa on "kekoehto" joka sanelee, että jokaisen solmun pitää olla "prioriteetiltaan" suurempi kuin sen lapset. Solmujen prioriteetti katsotaan joko niihin talletettujen alkioiden luonnollisesta järjestyksestä (eli jos ne toteuttavat javan Comparable -rajapinnan, tämän vertailun tuloksen perusteella) tai sitten erillisellä Comparator-rajapinnan toteutavalla oliolla. Prioriteettikekoon lisääminen ja keosta poistaminen saadaan tehtyä nyt ajassa O(log(n)) eli logaritmisessa ajassa suhteessa alkioiden määrään eli lineaarisessa suhteessa binaaripuun korkeuteen.

Breadth First-hakua varten toteutetaan perinteinen Jono, johon voidaan tallettaa alkioita ja josta saadaan ulos alkiot samassa järjestyksessä kuin ne sinne laitettiinkin. Jono toteutetaan tavallisen yksiuloitteisen taulukon avulla, ja samalla pidetään kirjaa jonon alkupään ja loppupään indekseistä.

Valitsin aiheen, koska reittialgoritmit ovat kiinnostavia teoreettisessa mielessä ja hyvin hyödynnettävissä tietokonepeleissä. 

Lähteet: JohTek-materiaali, TiRa-materiaali, http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
