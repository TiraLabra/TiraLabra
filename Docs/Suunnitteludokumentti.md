- Ohjelman yleisrakenne

Ohjelma koostuu kolmesta lähdekoodi- ja kahdesta testipakkauksesta. Lähdekoodipakkaukset ovat ohjelma-pakkaus, joka sisältää ohjelman pää- eli suoritusluokan, ohjelma.sovelluslogiikka-pakkaus, joka sisältää ohjelman toiminnallisuudesta vastaavan luokan, ja ohjelma.domain-pakkaus, joka sisältää ohjelman tietorakenteen toteutuksesta vastaavan luokan. Testipakkaukset ovat ohjelma.sovelluslogiikka- ja ohjelma.domain-pakkaukset, jotka sisältävät lähdekoodipakkausten luokkia vastaavat JUnit-luokat.

- Saavutetut aika- ja tilavaativuudet

Ohjelman saavutetut aika- ja tilavaativuudet ovat samat kuin määrittelydokumentissa tavoitteeksi asetetut. Kaikki Pino-luokan metodit ovat aikavaativuudeltaan 0(1), ja kaikki Laskin-luokan metodit, lukuun ottamatta vakioaikaista kaynnista-metodia, ovat pahimmasssa tapauksessa lineaarisessa ajassa toimivia, joten ohjelman kokonaisaikavaativuudeksi saadaan O(n). Algoritmien sulutTasapainossa ja ratkaiseLaskutoimitus vaatimat pinot vievät puolestaan tilaa vakiomäärän, ja koska apumuuttujiakin on ohjelmassa vakiomäärä, koko ohjelman tilavaativuus on O(1). 

- Työn mahdolliset puutteet ja parannusehdotukset

Työn ehdottomasti suurimmat puutteet ovat tuottettujen laskutoimitusten ratkaisujen rajoittuminen kokonaislukutarkkuuteen, negatiivisten lukujen käytön mahdottomuus syöttövaiheessa, kokonaisluku-tietotyypin rajoittuneisuus suurten lukujen käsittelyssä ja tuetun laskuoperaatiojoukon laajentaminen nykyisessä toteutusmuodossa. Näitä puutteita voisi parantaa toteuttamalla laskin niin, että se käyttää liukuluku-tietotyyppiä, ottaa huomioon syötteen lukuvaiheessa negatiivisten lukujen käyttömahdollisuuden pelkän vähennyslaskun lisäksi, ja toteuttaa OPERANDI-lueteltu tyyppi rajapintana ja laskuoperaatiot omina luokkinaan.

- Lähteet

http://en.wikipedia.org/wiki/Shunting-yard_algorithm
