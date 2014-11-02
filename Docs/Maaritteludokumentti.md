Aihe: Reitinetsintä

Kieli: Java

Käytettävä algoritmi: A*

Tarkoituksena olisi ratkaista reitinetsintä ongelma, jossa tavoitteena löytää mahdollisimman nopeasti mahdollisimman lyhyt reitti lähtöpisteestä maaliin. Tähän tarkoitukseen valitsin A*-algoritmin koska se on hyvin tehokas ja tunnettu reitinetsintä algoritmi.

Tietorakenteet: Keko; taulukko  tai verkko, joka syötteenä;

Syöte:	Syötteenä n ulotteinen taulukko alueesta jonka läpi reitti tulee etsiä sekä lähtö ja maali alkiot.
	Reitti etsitään käyttäen taulukkoa. Reitti kulkee lähtö alkiosta maali alikoon.

tai
	lähtö ja maali alkiot alkoioiden verkosta ja heuristiikka esim. toEnd(alkio), toStart(alkio).


Tavoitteellinen TilaVaativuus: Pahimmassa tapauksessa sama kuin taulukon tai verkon koko eli O(V). Parhaimmassa reitin pituus. Yleisessä tapauksessa lähempänä reitin pituutta kuin taulukon kokoa.

Tavoitteellinen Aikavaativuus: O(E*sqrt(V)) , missä V on kaikki mahdolliset alkiot ja E yhteydet. Osa-aikavaativuus 'n' tulee kaikkien alkioiden läpikäymisestä ja  'sqrt(n)' niiden lisäämisestä keko tietorakenteeseen, joka toimii aikavaativuudella o(sqrt(n)).

Lähteet: http://en.wikipedia.org/wiki/A*_search_algorithm (tarviiko jonkun paremman lähteen?)