## Reitinhakualgoritmitutkielma
### A*, JPS?

Tarkoituksena on toteuttaa reitinhakualgoritmeja omin tietorakentein.  Projektin alussa tähtäimessä on tehdä A*-implementaatio omaa kekoa käyttäen, ja vertailla sitä esim. javan valmiita tietorakenteita käyttävään malliin.

A* toimii kuten Dijkstra, muistamalla miten kuhunkin solmuun on saavuttu lyhintä reittiä, ja backtrackaamalla maalista takaisin alkuun kun maali on löytynyt.  Erona Dijkstran algoritmiin A* käyttää heuristiikkaa, joka lasketaan manhattan -etäisyytenä esteettömästä verkosta.  Jos heuristiikkafunktio toimii oikein, on löydetty polku varmasti lyhin.

Algoritmin aikavaativuus riippuu paitsi heuristiikasta, käytännössä myös käytetyistä tietorakenteista.  Dijkstran algoritmin kompleksisuus on O([E| + [V| log |V|), tosin fibonacci-keolla.  A* -algoritmin aikavaativuuden analysointi on heuristiikan vuoksi käytännössä vaikeampi analysoitava käytännössä, mutta Wikipedia sanoo sekä tila- että aikavaativuuden olevan eksponentiaalinen; tilavaativuuden noodien ja aikavaativuuden kaarien suhteen.

Jos jää aikaa ja kiinnostusta, toteutetaan myös Jump Point Search -laajennus.  JPS on laajennos A* -algoritmiin, joka kykenee ns. hyppäämään suoria linjoja pitkin, jos niiden varrella ei tapahdu mitään sellaista joka tulisi ottaa huomioon reittiä laskettaessa.  JPS:n kompleksisuusanalyysi ohittaa oman kykenevyyteni algoritmien analysoinnissa, joskin lupaan yrittää jos sinne asti ehdin.

Vaihtoehtoisia laajennuksia voisivat olla esim. erilaisten kekojen vertailut tai liikkuvan maalin löytämiseen liittyvät heuristiikat.

Samalla toteutetaan BufferedImage -luokalla visualisointeja havainnollisuuden vuoksi.


