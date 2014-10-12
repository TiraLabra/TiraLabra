##Käyttöohje
###Ohjelman suoritus
Ohjelmiston hakupuita voi hyödyntää erillisesti tai yhdessä. Kaikki puut toteuttavat rajapinnan HakupuuRajapinta määrittelemät metodit. Ainoa rajoitus on, että AVL, punamusta ja splay-puuta varten tarvitaan BinaarinenHakupuu-luokka, koska ne kaikki perivät sen.
Ohjelmistolle on myös määritetty Main-luokka, joka suorittaa suorituskykytestaamista.
Tätä voi käyttää esimerkiksi ajamalla tiedoston run_tiralabra.sh komennolla "sh run_tiralabra.sh". Skriptin tarkoitus on vähentää samojen komentojen toistoa.
Manuaalisesti on mahdollista myös tehdä kaikki edellä mainittu. Siihen tarvitaan ainakin komennot:
* mvn clean install
* mvn jar:jar
* java -jar target/Tiralabra_maven-1.0-SNAPSHOT.jar
Komennot tulee suorittaa maven projektin juuressa: /Tiralabra_maven.

###Jar-tiedosto
Kun maven projektin juuressa ("Tiralabra_maven") on annettu komennot "mvn clean", "mvn install" ja "mvn jar:jar", niin jar-tiedosto löytyy:
*/target/Tiralabra_maven-1.0-SNAPSHOT.jar
Jar-tiedoston generointi on myös automatisoitu työssä. Ajamalla run_tiralabra.sh tiedoston komennolla "sh run_tiralabra.sh", löytyy jar samasta paikasta kuin manuaalisesti generoimalla jar-tiedoston.
