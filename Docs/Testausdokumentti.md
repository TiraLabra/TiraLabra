#Testausdokumetti
##Mitä on testattu?
Ohjelmiston loogista osuutta on testattu junit-testeillä ja CLI:tä on testattu erilaisilla syötteillä.
Logiikkaa on myös testattu runsaasti samalla kun puita on kehitetty ja tutkittu erilaisia tapauksia ja
mahdollisia bugeja, joita ei ole testattu junit-testeillä. Junit testit painottuvat enemmän suoraan toimintaan ja tutkivat ettei mikään ominaisuus ole hajonnut. Private metodeja ei ole testattu koska ne ovat kapseloitu tietylle luokalle.

###AVL-hakupuu
AVL-hakupuussa ehtojen tarkistukseen on tehty junit testi. Jos puun korkeus on 3 niin puussa on vähintään 7 solmua. Myös poisto, lisäys sekä haku metodeita on testattu.

###Splaypuu
Splaypuussa on testattu yleiset puun metodit.

###Binäärihakupuu
Binäärihakupuussa on testattu min, lisäys, haku, poista sekä toString.

###Punamustapuu
Punamustapuussa on testattu samat metodit kuin Splaypuussa

###Solmu
Solmuluokan gettereitä ja settereitä on testailtu junit-testeillä.

##Minkälaisilla syötteillä testaus tehtiin.
Alussa puihin lisättiin vain muutamia alkioita ja tutkittiin tulosta, myöhemmin kasvatettiin luvut kymmeniin ja katsottiin että kaikki toimii vieläkin. Yksi perus testi oli lisätä 50 alkiota puuhun ja sitten poistaa ne kaikki ja tutkia tuloksia. Puihin myös lisättiin samaa alkiota useampaan kertaan ja katsottin että algorimit toimivat vieläkin.

Myös CLI on kokeiltu lisätä miljoonia samaan puuhun. Tämän jälkeen on tehty eri operaatioita ja katsottu että puu toimii oikein.

##Miten testit voidaan toistaa?
Tiralabra_maven kansiossa "mvn test"

##Graafisten komponenttien testaus
Graafisten komponenttien testaus on tehtävä CLIssa. Ohjelma on pyritty toteuttamaan erillisenä osana ohjelmaa ilman että se aiheuttaisi bugeja.
