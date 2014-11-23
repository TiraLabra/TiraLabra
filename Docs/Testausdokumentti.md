# Testausdokumentti

Testaus on toteutettu jUnit-testeillä. Tällä hetkellä jUnit-yksikkötestit testaavat luokkien sekä metodien toimintaa.

Hakualgoritmin toimintaa testattiin myös havainnollistavilla testeillä:
Kartalla o on aloituspiste, x loppupiste ja # on seinä. P-kirjaimilla on merkitty algoritmin laskema polku.

Molemmat puolet auki:

![Kuva3](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic3.png)

Yläreitti on tukittu:

![Kuva 1](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic1.png)

Alareitti on tukittu:

![Kuva 2](https://raw.githubusercontent.com/manuligit/TiraLabra/master/Docs/kuvat/pic2.png)

Algoritmi osaa hakea polun oikein pienien muutostenkin jälkeen. 


# Suorituskykytestaus

Suorituskykytestaus tehtiin suorittamalla ohjelma kymmenen kertaa ja laskemalla aikojen keskiarvo. Testasin A*-algoritmin toimintaa käyttäen valmiita tietorakenteita, jolloin ArrayListiä sekä PriorityQueueta käyttämällä suorituksen ajan keskiarvoksi tuli noin 4,8ms. 

Kun ArrayListin korvasi MyList-luokalla tulokset olivat hieman heikommat, sillä suorituksen keskiarvoinen aika oli n. 6,8ms.

