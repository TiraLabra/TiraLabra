Ohjelma on toteutettu NetBeansia ja Mavenia hyväksikäyttäen ja käyttöohjeessa oletetaankin, että 
käyttäjällä on käytössä NetBeans tai vaihtoehtoisesti, että käyttäjä osaa itse kääntää ja suorittaa 
Maven -projektin.

1. 	Tee omalle omalle koneelle klooni repositoriosta https://github.com/pauge/TiraLabra
2. 	Etsi klooni NetBeansin "Open Project..." valikon kautta ja avaa Maven -projekti (pauge-astar).
3. 	Valitse NetBeansissa Astar -projekti, avaa hiirellä valikko ja valitse "Run"
4. 	Syötä tiedostopolku tekemääsi ASCII -karttaan ja paina enter. Kartan vaatimukset ovat tämän dokumentin 
	lopussa.
5. 	Katso tuloste ja syötä uusi tiedostopolku TAI paina '0' (nolla) lopettaaksesi ohjelman.




Karttavaatimukset:

Kartan rivit ovat keskenään samanpituisia (useampiriviset kartat muodostavat siis aina suorakulmion).
Seinät merkitään isolla X:llä.
Tavallinen "maa" merkitään 0:lla (nolla).
Hidaskulkuinen maasto merkitään numerolla 1-9:llä (isompi numero, isompi kustannus).
Lähtö merkitään isolla S:llä (start),
Maali merkitään isolla G:llä (goal).

Esimerkkikarttoja:

XXXXXXXXXXXXXXXXXGXX
X000000000000000X00X
X000000000000000X00X
X000000000000000000X
X000000XXXXX0000000X
X0000000000X0000000X
X0000000000X0000000X
X0000000010X0000000X
X000001110100000000X
XXSXXXXXXXXXXXXXXXXX


XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
X0000000000000000000000000000000000000X
X000000000000000000X000000000000000000G
X000000000000000000X000000000000000000X
S000000000000000000X0000000X0000000000X
X000000000000000000X0000000X0000000000X
X000000000000000000X0000000X0000000000X
X00000000000000X000X0000000X0000000000X
X00000000000000X000X0000000X0000000000X
X00000000000000X00000000000X0000000000X
X00000000000000X00000000000X0000000000X
X00000000000000X00000000000X0000000000X
X00000000000000000000000000X0000000000X
X0XXXXXXXXXX000000000000000X0000000000X
X0000000000000000000000000000000000000X
X0000000000000000000000000000000000000X
X0000000000000000000000000000000000000X
X0000000000000000000000000000000000000X
X0000000000000000000000000000000000000X
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
