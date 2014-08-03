A*-haku

Harjoitustyön aiheena on A* -reitinetsintäalgoritmi, joka on eräänlainen laajennus Dijkstran reittialgoritmista. Siinä missä Dijkstran algoritmissa haku laajenee joka suuntaan kunnes lyhin reitti löydetään, A* -haussa otetaan huomioon kuljetun matkan lisäksi arvioitu jäljellä oleva matka.

A*-haussa lähdetään liikkeelle lähtösolmusta, ja lisätään sen naapurisolmut läpikäytävien solmujen listaan. Läpikäytävien solmujen listasta valitaan vuorollaan aina se solmu, jonka yhteenlaskettu jo kuljetun reitin pituuden ja arvioidun jäljelläolevan matkan summa on pienin. Siirrytään tähän solmuun, lisätään sen naapurit läpikäytävien solmujen listaan, ja jatketaan listan läpikäyntiä. Tätä läpikäyntiä jatketaan, kunnes ollaan saavuttu maaliin.

A*-hakua varten pitää implementoida ainakin prioriteettikeko, eli keko josta saadaan poimittua alkiot priorisoidussa järjestyksessä.

Valitsin aiheen, koska reittialgoritmit ovat kiinnostavia teoreettisessa mielessä ja hyvin hyödynnettävissä tietokonepeleissä. 

Lähteet: JohTek-materiaali, TiRa-materiaali, http://theory.stanford.edu/~amitp/GameProgramming/AStarComparison.html
