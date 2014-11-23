# Testausdokumentti

Testaus on toteutettu jUnit-testeillä. Tällä hetkellä jUnit-yksikkötestit testaavat luokkien sekä metodien toimintaa.

# Suorituskykytestaus

Suorituskykytestaus tehtiin suorittamalla ohjelma kymmenen kertaa ja laskemalla aikojen keskiarvo. Testasin A*-algoritmin toimintaa käyttäen valmiita tietorakenteita, jolloin ArrayListiä sekä PriorityQueueta käyttämällä suorituksen ajan keskiarvoksi tuli noin 4,8ms. 

Kun ArrayListin korvasi MyList-luokalla tulokset olivat hieman heikommat, sillä suorituksen keskiarvoinen aika oli n. 6,8ms.

