## Sanapuuro

### Yleiskuva

Tämä projekti on jatkoa Sanapuuro-pelille, jonka tein JavaLabraa varten. Yksinpeliä ei enää ole vaan se on korvattu kaksinpelillä. Toistaiseksi peliä pelataan lokaalisti tietokonetta vastaan. Pelissä ei voi vielä valita nimeä, joten ohjattavana on "Hessu" ja tietokonevastustajana on "Mikki".

Pelin ajettava jar löytyy [täältä](http://www.cs.helsinki.fi/u/skaipio/jars/sanapuuro.jar). -w argumentilla pääsee pelin Swing-versioon, joka on mukavampi käyttää kuin terminaaliversio.

### Käyttöohjeet

Pelin Swing-versiossa hiirellä valitaan paikka josta aloitetaan sanan syöttäminen, kun paikka on valittu, on valittava vielä suunta, joko hiirellä tai 'wasd'-näppäimillä. Tämän jälkeen voidaan alkaa syöttämään kirjaimia. Kirjaimia, jotka eivät löydy omista kirjaimista ei pysty syöttämään. Kirjaimien syöttämisen voi myös aloittaa solusta, jossa on jo kirjain. Kirjaimen, joka on korostetun solun kohdalla, voi valita syötteeseen välilyönnillä. Kirjaimen syöttämisen tai valitsemisen voi peruuttaa 'Backspace'-näppäimellä, jolla myös pääsee pois syöttötilasta. Kun haluaa antaa sanansa arvioitavaksi, voi painaa 'Submit'-nappulaa tai 'Enter'-näppäintä.

Pelin terminaali versiossa on kursori, jota voi liikutella 'w', 'a', 's' ja 'd' komentojen avulla. Kun halutaan syöttää sana kursorin osoittamasta kohdasta alkaen, annetaan komento 'q'. Peli pyytää sen jälkeen suunnan ja syötettävät kirjaimet. Pelaaja voi skipata vuoronsa syöttämällä tyhjän merkkijonon. Pelaajan pisteet sekä käytettävissä olevat kirjaimet ovat näkyvissä konsolitulosteessa. Peliä on paljon mielekkäämpää kokeilla terminaalissa, kuin esim. NetBeansissa.  Poistumiseen pelistä ei ole erillistä komentoa, vaan sen voi pysäyttää terminaalissa komennolla Ctrl+C.

* * *
