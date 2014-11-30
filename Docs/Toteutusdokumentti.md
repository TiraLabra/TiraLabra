# Toteutusdokumentti

**Ohjelman yleisrakenne**
Ohjelma yritt�� voittaa pelajaan k�den kivi-paperi-sakset(-lisko-spock) -peliss�. Toteutus on hyvin suoraviivainen:
* Ensimm�isell� kierroksella kone valitsee ennalta m��r�tyn k�den pelattavaksi
* Jokaisen kierroksen j�lkeen kone tallentaa kierroksen tilanteen (pelaajan k�si, tietokoneen k�si ja voittaja)
* Kierroksilla 2-5 kone olettaa pelaajan pelaavan aina samalla tavalla (Jos pelaaja voitti edellisen kierroksen, pelaaja pelaa uudelleen samaa k�tt�. Jos pelaaja h�visi kierroksen, pelaaja valitsee h�vinnytt� k�tt� seuraavan k�den rotaatiosta kivi-paperi-sakset(-lisko-spock))
* Kierroksen 5 j�lkeen kone k�ytt�� heuristiikkaa (johon toivottavasti on ker��ntynyt riitt�v�sti dataa). Heuristiikka etsii edellist� kierrosta vastaavan jo pelatun tilanteen ja kertoo mit� k�tt� pelaaja pelasi t�m�n j�lkeen. Mik�li tilanteita on useita, heuristiikka palauttaa pelaajan useimmiten pelaamaan k�den em. tilanteessa. Kone asettaa t�t� k�tt� voittavan k�den tietokoneen seuraavaksi k�deksi. Heuristiikka pit�� muistissaan ennalta m��r�tyn m��r�n statistiikkaa (t�ll� hetkell� 20 viimeisint� kierrosta)

**Saavutetut aika- ja tilavaativuudet**
Ohjelma toimii vakioajassa ja -tilassa. 

* Statistiikka tallentaa pelattujen kierrosten lukum��r�n, joka siis kasvaa yhdell� per kierros -> aika O(1), tila O(1)
* Statistiikka tallentaa pelaajan voittojen m��r�n -> aika O(1), tila O(1)
* Teko�ly valitsee alussa rotaation perusteella koneen pelaaman k�den. Maksimim��r� rotaatiota on 4 (kivi-paperi-sakset-lisko-spock) jonka j�lkeen ollaan samassa k�dess� mist� rotaatio aloitettiin -> aika O(4) = O(1), tila O(5) = O(1)
* Heuristiikka tallentaa linkitettyyn listaan pelej�. Kun pelien m��r� on saavuttanut 20 alkiota, seuraava talletettava peli laitetaan listan loppuun ja lista ensimm�inen alkio poistetaan (lista koko siis on maksimissaan 20). Sijoitusoperaatiot ovat vakioaikaisia -> aika O(1), tila O(20) = O(1)

**Mahdolliset puutteet ja parannusehdotukset**
Ohjelma k�sittelee pelin k�si� sek� k�si-olioina, joilla on tekstimuotoinen kentt� nimi, ett� numeroina (heuristiikkassa). Selvyyden vuoksi yrit�n saada koko ohjelman k�sittelem��n k�si� ainoastaan numeraalisessa muodossa ja itse k�ytt�liittym� hoitaa numeroiden muuttamisen tekstiksi. 

Heuristiikan laajentaminen parantaisi koneen voittosuhdetta joten talletettavien k�sien m��r�n nostaminen voi tulla kyseeseen. 
