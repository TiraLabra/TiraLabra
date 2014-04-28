Testausdokumentti


Testaus toteutettiin Seleniumin avulla, joka on testing framework for web applications. Lisäksi käytin AngularJS:n testaukseen kehitettyä Protractoria.


Käytetyt teknologiat

-Selenium
-Protractor
-Node.js


Toteutus

Kirjoitin testausscriptit Node.js – Protractor -muotoon, jotka ajoin Selenium serverillä. 

Ohjelmalla ei ole varsinaista controlleria, joten tein kaikki testit e2e painoitteisesti. Samalla tuli kuitenkin testattua kaikki ohjelman käyttämät funktiot.

Testit imitoivat käyttäjän toimintaa selaimessa.

Testiesimerkki: 

Testiscripti avaa annetun osoitteen selaimessa ja klikkaa Search-nappia. Testin oletettu lopputulos on että haku ei käynnisty vaan 'error'-kenttään ilmestyy teksti 'Choose Start and End Nodes'.

Muut ohjelmalle tehdyt testit:

-should not execute search if End Node is not selected
-should not run new search without initialization
-should not ramdomize layout without initialization after a search
-destination should not be reachable
-function randomize should change nodes locations
-function randomize should change nodes' locations
-should execute test case without errors