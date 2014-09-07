Testausdokumentti

Ohjelman unit-testaus on suoritettu. Jokaisen toteutetun tietorakenteen julkisia metodeja on testattu.

Hajautustauluille on tehty seuraavat jUnit testit:
-Hajautustaulu tyhjä aluksi
-Merkintöjen lisääminen (yksi, useampi)
-Merkintöjen poistaminen
-Merkitöjen hakeminen

Kahteen suuntaan linkitetylle listalle on tehty seuraavat jUnit testit:
-lista tyhjä aluksi
-Avaimen lisääminen ensimmäiseen solmuun
-Arvon lisääminen ensimmäiseen solmuun
-Usean solmun lisääminen
-Solmun poistaminen
-Solmun etsiminen listalta

Yksittäiselle linkitetyn listan solmulle on tehty seuraavat jUnit testit:
-Avaimen lisääminen
-Arvon lisääminen
-Seuraava solmu oikein
-Edellinen solmu oikein

TaulunMerkinta-luokalle ei tehty erillisiä testejä, koska luokka sisältää pelkästään avain-arvo -parin settereineen ja gettereineen ja on siten triviaalista todeta se toimivaksi. Myös muiden luokkien testeissä testataan lähes aina myös kyseistä luokkaa.

Loppuja testausdokumenttiin kuuluvia asioita löytyy tiedostosta Docs/aikavaatimuksien_toteutuminen_graafinen_esitys.pdf   Siinä tehdyt testit voidaan toistaa suorittamalla ohjelma käyttöohjeiden mukaisesti, koska itse ohjelman tarkoitus on suorittaa kyseinen vertailu. Ohjelmassa hajautustauluille syötteenä generoitavat merkkijonot ovat kymmenen merkin mittaisia isoja kirjaimia ja lukuja sisältäviä alfanumeerisia merkkijonoja.
