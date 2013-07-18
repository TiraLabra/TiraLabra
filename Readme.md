### Tässä pohjassa on mukana kaksi NetBeans projektikansiota; Tiralabra, joka on ant pohjainen, ja Tiralabra_maven, joka käyttää mavenia:

##### ant
Pohjassa on valmiina Tiralabra niminen NetBeans projektipohja, joka käyttää ant:ia (http://ant.apache.org/)[http://ant.apache.org/]
Tämän tyyppisiä pohjia käytettiin ohjelmoinnin perusteissa ja jatkokurssilla.

##### Maven
Tiralabra_maven projektipohja käyttää puolestaan Ant:in sijaan Mavenia (http://maven.apache.org/)[http://maven.apache.org/] joka on monipuolisempi ja laajemmissa sovelluksissa käytetty vastaava, mutta monipuolisempi järjestelmä kuin ant.


NetBeanssissa ant ja maven projektien välillä ei eroa juuri ole.


## Muuta heti alussa run_tests.sh tiedostoon riville 
PROJECT_DIR=Tiralabra
sana Tiralabra oman projektipohjasi nimeksi.

Pull requestien yhteydessä ohjelman testit ajetaan Travis-ci:ssä, ja sen vuoksi on tärkeää, että run_tests.sh sisältää projektikansiosi nimen oikein!

Docs kansioon tulee laittaa kaikki muut dokumentit, paitsi javadoc, joka voi olla Projektikansiossasi.

Viikottainen "palautus" tehdään luomalla Pull Request forkattuun repoon.

