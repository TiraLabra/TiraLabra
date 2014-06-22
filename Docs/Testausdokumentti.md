Testit on toteutettu JUnit:illa ja ne testaavat kovinkin laajasti ainakin
numeroiden toteutukset (testejä kaikenkaikkiaan on 220!).

Kaikki numeroita toteuttavat luokat ajavat samat perustestit, joihin kuuluu
vähintään yksi jokaista toteutettavaa operaatiota varten. Lisäksi ne tarkistavat
nollan ja negatiivisten lukujen käyttätymistä.

Integer-luokkaa testataan jokaiselle operaatiolle lisäksi lukuja, jotka
ylittävät yhden sanan määrän, jolloin yleensä tapahtuu jotain kiinnostavaa
ja/tai kaikki menee rikki.

Real-luokka lyö viellä Integerin päälle desimaalilukuja, joita pitää
luonnollisesti myös testata kaikilla operaatiolla.

Matriiseja ja vektoreita testataan parilla eri arvoyhdistelmillä. Vektoreita
myös testataan kaikilla numeroiden testeillä 4-uloitteisilla vektoreilla.

Testit voi ajaa helpoiten run_tests.sh-skriptillä, vaikkakin se toimii vain
olosuhteissa missä shellskriptejä voi edes ajaa. Ant pystyy myös ajamaan testit
komennolla ant tests. Vähintäänkin tukevat IDEt kuten Netbeans pystyy testien
ajamiseen.