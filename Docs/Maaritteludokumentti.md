Aihe: Reitinetsint�

Kieli: Java

K�ytett�v� algoritmi: A*

Tarkoituksena olisi ratkaista reitinetsint� ongelma, jossa tavoitteena l�yt�� mahdollisimman nopeasti mahdollisimman lyhyt reitti l�ht�pisteest� maaliin. T�h�n tarkoitukseen valitsin A*-algoritmin koska se on hyvin tehokas ja tunnettu reitinetsint� algoritmi.

Tietorakenteet: Keko; taulukko  tai verkko, joka sy�tteen�;

Sy�te:	Sy�tteen� n ulotteinen taulukko alueesta jonka l�pi reitti tulee etsi� sek� l�ht� ja maali alkiot.
	Reitti etsit��n k�ytt�en taulukkoa. Reitti kulkee l�ht� alkiosta maali alikoon.

tai
	l�ht� ja maali alkiot alkoioiden verkosta ja heuristiikka esim. toEnd(alkio), toStart(alkio).


Tavoitteellinen TilaVaativuus: Pahimmassa tapauksessa sama kuin taulukon tai verkon koko eli O(V). Parhaimmassa reitin pituus. Yleisess� tapauksessa l�hemp�n� reitin pituutta kuin taulukon kokoa.

Tavoitteellinen Aikavaativuus: O(E*sqrt(V)) , miss� V on kaikki mahdolliset alkiot ja E yhteydet. Osa-aikavaativuus 'n' tulee kaikkien alkioiden l�pik�ymisest� ja  'sqrt(n)' niiden lis��misest� keko tietorakenteeseen, joka toimii aikavaativuudella o(sqrt(n)).

L�hteet: http://en.wikipedia.org/wiki/A*_search_algorithm (tarviiko jonkun paremman l�hteen?)