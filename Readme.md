### Tässä pohjassa on mukana kaksi NetBeans-projektikansiota: Tiralabra, joka on Ant-pohjainen, ja Tiralabra_maven, joka käyttää Mavenia:

##### Ant
Pohjassa on valmiina Tiralabra-niminen NetBeans-projektipohja, joka käyttää Ant:ia (http://ant.apache.org/)[http://ant.apache.org/]
Tämän tyyppisiä pohjia käytettiin ohjelmoinnin perusteissa ja jatkokurssilla. Mikäli et käytä työssäsi ulkopuolisia kirjastoja, voit käyttää tätä pohjaa.

##### Maven
Tiralabra_maven-projektipohja käyttää puolestaan Ant:in sijaan Mavenia (http://maven.apache.org/)[http://maven.apache.org/] joka on monipuolisempi ja laajemmissa sovelluksissa käytetty vastaava, mutta monipuolisempi järjestelmä kuin Ant.
Jos käytät ulkopuolisia kirjastoja, käytä tätä pohjaa!


NetBeansissa Ant- ja Maven-projektien välillä ei eroa juuri ole.


## Muuta heti alussa run_tests.sh tiedostoon riville 
PROJECT_DIR=Tiralabra
sana Tiralabra muotoon Tiralabra_maven, mikäli käytät Maven-pohjaa.

Pull requestien yhteydessä ohjelman testit ajetaan Travis-ci:ssä, ja sen vuoksi on tärkeää, että run_tests.sh sisältää projektikansiosi nimen oikein!

Docs kansioon tulee laittaa kaikki muut dokumentit, paitsi javadoc, joka voi olla projektikansiossasi.

Viikottainen "palautus" tehdään luomalla Pull Request forkattuun repoon.

