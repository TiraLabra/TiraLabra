Ohjelmaa on testattu kahdella tavalla: Automaattisin JUnit-testein, 
sekä manuaalisesti ajamalla ohjelma ja toivomalla parasta. Esimerkiksi 
käyttöliittymän toimintaa testattiin vain ajamalla se, ja katsomalla 
näyttääkö se oikealta.

Tilanteissa, joissa oli vaikea määritellä, mitä automaattisia testejä
kannattaa tehdä, ajettiin ohjelma ensin, ja sitten suunniteltiin
automaattiset testit löydettyjen ongelmien diagnosointia varten. Tällä
tavalla löydettiin ja korjattiin mm. heuristiikan laskennassa löytynyt 
huolimattomuusvirhe, jota kolme ihmistä ei huomannut suoraan koodista.

Ongelmia diagnosoitiin myös manuaalisesti lisäämällä ohjelmakoodiin
aputulosteita, joista näkyi mm. jäätiinkö ikuiseen silmukkaan tai 
päästiinkö silmukkaan ylipäätään. Tämä selittää mm. miksi hakualgotitmin
polunmerkinnässä on >= merkintä pseudokoodin vaatiman > merkinnän sijaan.
Jälkimmäisessä tapauksessa kyseisen if-lauseen sisään ei oltaisi koskaan
menty, ellei algoritmi olisi löytänyt sokkelosta silmukkaa.

Hakualgoritmin toiminnan testausta varten kehitettiin ohjelmaan myös 
ominaisuus, joka visualisoi algoritmin toimintaa samalla kun polunetsintä
on käynnissä. Vaikka ominaisuus kehitettiinkin alunperin algoritmin 
toiminnan manuaaliseen tarkasteluun, se jätetään valmiiseen ohjelmaan
vain siitä syystä, että se näyttää hyvältä ja tekee valmiista ohjelmasta
astetta mielenkiintoisemman.
