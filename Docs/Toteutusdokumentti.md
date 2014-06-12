Ohjelma on toteutettu käyttämällä x erillistä luokkaa. Seuraavana lyhyt kuvaus kaikista:

App
Pääohjelma suoritusta varten.

Finder

A* -algoritmin toimintalogiikka ja toteutus, joka käyttää hyväkseen tarjolla olevia tietorakenteita.

Heap

Prioriteettijono (keko), jonka avulla vieraillut solmut pidetään automaattisesti järjestyksessa niin, ettei
algoritmin itsensä tarvitse tietää jonosta mitään.

Stack
Pino, johon muodostuu lopullinen polku. Node pusketaan pinoon, kun siihen siirrytään.

Map

Tietosisällöltään kartan rungon mahdollistava matriisi. Konstruktori luo uuden kartan ja printField() -metodi
tulostaa kartan ASCII -muodossa. 

Node

Solmun tarjoava tietorakenne. Mahdollistaa solmun tietosisällön ylläpidon ja tarkastelemisen.