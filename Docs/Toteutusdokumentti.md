Olen pyrkinyt pitämään ohjelmani ja etenkin sen tietorakenteiden toteutuksen
mahdollisimman kevyenä siten että ne tarjoaisivat vain projektin (tai 
debuggauksen) kannalta välttämättömät operaatiot. Olen siksi myös pitänyt
esimerkiksi syötteiden tarkastukset minimissään etenkin sellaisissa paikoissa
koodia joissa syötteen on tuottanut jokin muu ohjelman komponentti eikä käyttäjä
suoraan.

Esimerkkinä tällaisesta poisjätetystä syötteen tarkistuksesta
mainittakoon luokkien Pino ja Jono alkioiden määrää kuvaavat kentät joiden arvoa
kasvatetaan alkion lisäyksen ja pienennetään poiston yhteydessä yhdellä. Mainitun
kentän arvo on tallennettu int-muuttujaan ja on teoriassa mahdollista että
esimerkiksi 2^31 peräkkäisen lisäysoperaation johdosta tapahtuu aritmeettinen
ylivuoto sillä seurauksella että muuttujan arvo pyörähtää negatiiviseksi. Pidän
tätä kuitenkin jokseenkin epätodennäköisenä kaikissa kuviteltavissa olevissa
käytännön tilanteissa joten olen päättänyt ohittaa tämäntyyppiset syötteen
tarkastukset sillä ne vain omalta osaltaan lisäisivät lisäysoperaatioiden
aikavaativuutta.


