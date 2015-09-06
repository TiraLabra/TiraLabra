# Harjoitusty�: tietorakenteet ja algoritmit

Projektin tarkoituksena on tehd� toimiva go-botti.

## Go
Go on kiinalainen kaksin pelattava lautapeli. Peli tunnetaan my�s nimill� w�iq� kiinaksi, igo japaniksi ja baduk koreaksi. Pelin juuret sijoittuvat kiinalaisen mytologian mukaan yli 4 000 vuoden taakse, historiallisesti v�hint��n 500 eaa. edelle. S��nn�t gossa ovat hyvin yksinkertaiset, mutta pelaaminen tarjoaa runsaasti haasteita ja vaatii kaikessa monimuotoisuudessaan runsaasti strategista silm�� kuten esimerkiksi l�nsimaissa tunnetumpi shakki. (Wikipedia)
Go on ��rellinen t�yden informaation peli, eik� siin� ole lainkaan satunnaisuutta. Hyvin pelaavan go-botin ohjelmoinnista tekee haastavaa go:n korkea haarautumisaste, joka on yli 300. Esimerkiksi shakin haarautumisaste on keskim��rin n. 35. Go:ssa ei my�sk��n voi helposti arvioida yksitt�isen siirron arvoa, koska laudan yleistilanne voi vaikuttaa paikallisesti samanlaisen siirron arvoon.

## Projektin tavoitteet
Projektin aikana toteutetaan botti, joka k�ytt�� Go Text Protocol -protokollaa kommunikointiin serverin tai graafisen k�ytt�liittym�n kanssa.
Koska harjoitusty�ss� on tarkoitus keskitty� algorimeihin ja tietorakenteisiin, tulee t�rkein tavoite olemaan toimiva Monte Carlo -algoritmi, joka k�y l�pi satunnaisia pelisimulaatioita, ja valitsee niiden perusteella lupaavimman siirron. T�m�n lis�ksi toteutetaan ainakin Zobrist-hash, jolla laudan tilanteesta saadaan 64-bittinen avain, mill� nopeutetaan esimerkiksi jo toistuneen tilanteen tarkastamista.
Koska p��paino ei ole ohjelman kokonaisvaltaisessa suunnittelussa, k�ytet��n pohjana tohtori Peter Draken Orego -go-engine�, jonka l�hdekoodi on julkaistu lisenssill� Creative Commons Attribution-NonCommercial-ShareAlike 4.0 International License.