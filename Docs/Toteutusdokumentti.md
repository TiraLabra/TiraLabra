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

Suorituskyky- ja O-analyysivertailu:

Lähteet:

