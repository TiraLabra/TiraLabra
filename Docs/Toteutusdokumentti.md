Toteutusdokumentti

Ohjelman rakenne pääpiirteittäin:

Ohjelma on toteutettu com.company.tiralabra_maven pakettiin, josta kaikki ohjelman luokkatiedostot löytyvät. 

Suoritettava main-metodi on App.java tiedostossa.

Rajapinta, jonka ohjelmassa käytettävät hajautustaulut toteuttavat on tiedostossa Hajaustustaulu.java

Tietorakenteita on kolme: Hajautustaulu avoimella hajautuksella, hajautustaulu ylivuotolistoilla ja sen tarvitsema kahteen suuntaan linkitetty lista.

Linkitettyä listaa apunaan käyttävä hajautustaulu löytyy tiedostosta
HajautustauluLinkitetyllaListalla.java
	->sen käyttämä linkitetty lista on tiedostossa KahteenSuuntaanLinkitettyLista.java.
		->Yksittäistä solmua linkitetyllä listalla kuvaa tiedosto Solmu.java
			->Solmussa oleva yksittäinen merkintä kuvataan tiedostossa TaulunMerkinta.java


Avointa hajautusta käyttävä hajautustaulu löytyy tiedostosta
HajautustauluAvoimellaHajautuksella.java
	->sen käyttämä yksittäinen taulunmerkintä löytyy tiedostosta TaulunMerkinta.java


Molemmissa hajautustauluissa siis käytetään TaulunMerkinta.java luokkaa kuvaamaan yksittäistä merkintää taulussa. Tämä on tehty, jotta metodit voisivat palauttaa samoja olioita. Se kun on erittäin hyödyllistä rajapintaa ajatellen.

Saavutetut aika- ja tilavaativuudet:
Aikavaatimukset saavutettiin, koska määrittelydokumentissa on määritelty hajautustaulujen operaatioiden aikavaatimuksiksi keskimääräisesti O(1), mutta pahimmassa tapauksessa O(n). Ylivuotolistoilla toteutettu hajautustaulu on melko selvästi O(1) empiiristen testausten perusteella (katso aikavaatimuksien_toteutuminen_graafinen_esitys.pdf), kun taas avoimella hajautuksella toteutettuja hajautustauluja ei voi kutsua vakioaikaisiksi. Niiden operaatiot ovat lähempänä luokkaa O(n) suurilla syötteillä kokeilujonojen kasvaessa ja jatkuvien uudelleenhajautuksien takia, molemmat ongelmia joista ylivuotolistoilla toteutettu hajautustaulu ei juurikaan kärsi.

Hajautustaulujen tilavaativuudet O(n) saavutettiin myös, ikävä kyllä asiaa oli hankala testata javalla, enkä saanut erittäin luotettavia tuloksia empiirisillä testeillä. Koodia analysoiden näämme kuitenkin, että ylivuotolistoja käyttävä hajautustaulu vie noin 2n tilaa, koska jokaisella merkinnällä hajautustauluun on itse syötettyjen arvojen lisäksi viitteet edeltävään ja seuraavaan solmuun, jotka vievät myös tilaa. Avoimella hajautuksella toteutetut hajautustaulut sen sijaan voivat viedä yli 2n tilaa uudelleenhajatuksien kohdalla. Silti ne vievät keskimäärin vähemmän tilaa kuin aina noin 2n vievä ylivuotolistoja käyttävä hajautustaulu.



Suorituskyky- ja O-analyysivertailu: katso aikavaatimuksien_toteutuminen_graafinen_esitys.pdf

Lähteet:
Docs/aikavaatimuksien_toteutuminen_graafinen_esitys.pdf
http://www.cs.helsinki.fi/u/floreen/tira2013syksy/tira.pdf 
https://github.com/TiraLabra/Loppukesa-2014/wiki/Dokumentaatio

tekemäni ohjelma
