Aihemaarittely

//huom. vertaisarvioija: Aihe vaihtui lennossa, vanhan aiheen matskut löytyy Vanha aihe -kansiosta.
//Uuteen aiheeseen en ole vielä ehtinyt tätä kirjoittaessa toteuttaa mitään.

Tarkoituksena toteuttaa se vanha kunnon A*-reitinhaku omilla tietorakenteilla.

Huonoimman tapauksen tila- ja aikavaativuus on eksponentiaalinen.

Lähteitä:
http://en.wikipedia.org/wiki/A*_search_algorithm


//Vanha aihe, jätetty kesken
Dynamic Range Compression WAV-tiedostoille.

Varsinainen algoritmi ei ole vielä muotoutunut. Tietorakenteena byte array.

Tarkoituksena on ikään kuin "tasoittaa" äänentaso johonkin tiettyyn arvoon.
Näin esim. jonkin äänitteen 'volyymia' voidaan nostaa ylemmäs (loudness), tai vaikkapa tasoittaa
hieman epätasaista laulusuoritusta.
	
Wav-tiedostomuodon takia tiedosto on mielekästä purkaa tavuihin, joita käsittelemällä
saadaan toivottavasti tehtyä varsinainen kompressointi.

Ohjelma saa syötteenä itse käsiteltävän tiedoston, sekä mahdollisesti parametreja, 
kuten toivotun kompressoinnin tason.

Algoritmi suoriutunee lineaarisessa ajassa. Tilavaativuus on samaten lineaarinen.


Lähteitä:
http://en.wikipedia.org/wiki/Dynamic_range_compression
