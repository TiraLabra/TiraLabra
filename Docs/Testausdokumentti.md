- Mitä on testattu, miten tämä tehtiin

Ohjelmasta on testattu JUnit-testiluokilla Laskin-luokan metodit tarkastaSyote, sulutTasapainossa, ratkaiseLaskutoimitus ja suoritaOperaatiot sekä Pino-luokan konstruktori ja metodit push, pop, empty, peek, getSize, getOlioidenLkm ja getTop. Ohjelman keskeisimmän metodin, ratkaiseLaskutoimitus, suorituskykyä erilaisilla laskutoimituksilla on myös testattu erikseen ohjelman pääluokassa.  

- Miten testit voidaan toistaa

Testit ovat toistettavissa ajamalla ne NetBeans-ohjelmointiympäristössä (JUnit-testien osalta testaamalla projekti ja suorityskykytestien osalta suorittamalla ohjelma). Suorituskykytestien osalta törmättiin kuitenkin tilanteeseen, jossa 1. testistä saatiin lukema, mutta sitä seuraavat, raskaatkin, testit eivät antaneet millisekuntien tarkkuudella mitattuna kuin nollatulosta. Tämä ilmiö liian nopeasti tehtävistään suoriutuvista metodeista tuli esiin myös muissa harjoitustyön projekteissa, ja yleinen mielipide oli se, että se johtuu Javan luonteesta vahvasti optimoivana kielenä.