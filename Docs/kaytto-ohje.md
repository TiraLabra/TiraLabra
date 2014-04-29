Ohjelma voidaan suorittaa komentoriviltä, jar tiedosto löytyy Tiralabra_maven kansiosta (koska kuvatiedostot ovat sieltä saatavilla) ja se voidaan suorittaa antamalla komento java -jar tron.jar

Ohjelmalla on yksinkertainen tekstikäyttöliittymä, joka pyytää käyttäjää syöttämään seuraavat tiedot (suluissa syötteen muoto):

-halutaanko käyttää monitasoista perseptronia (M) vai tavallista perseptronia (P) luokitteluun 
-kuinka monta näytettä otetaan "false" ryhmästä (kokonaisluku väliltä 0-850)
-kuinka monta näytettä otetaan "true" ryhmästä (kokonaisluku väliltä 0-120)
Jos alussa on valittu monitasoinen perseptroni, on lisäksi syötettävä: 

-tasojen määrä (kokonaisluku)
-eri tasojen neuronien määrät (kokonaisluku)
-opetusaskeleen suuruus (desimaaliluku väliltä 0-1)
-maksimaalinen virhe (desimaaliluku väliltä 0-1)
-suoritusaskelien yläraja (kokonaisluku)

Tämän jälkeen ohjelma suorittaa jonkin aikaa neuroverkon koulutusta (voi kestää hyvin pitkään), jonka jälkeen siirrytään testausvaiheeseen. Neuroverkko käyttää loppuja "true" ja "false" kansioissa olevia kuvia opetuksen tason testaukseen. Kuvat ovat 27 pikseliä leveitä ja 27 pikseliä korkeita, "false" kansio sisältää kokoelman käsin kirjoitettuja kirjaimia, ja "true" kansio vastaavasti tietokoneella arial black fontilla. Halutessaan käyttäjä voi lisätä kansioihin kuvia.


