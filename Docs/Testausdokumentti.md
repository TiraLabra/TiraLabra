# Testausdokumentti

**Mitä on testattu**
Yksikkötestien lisäksi tehty empiiristä testausta tekoälyn toiminnasta. Toistaiseksi tekoäly ei toimi riittävän suurella voittosuhteella (tavoite 80%). Voittosuhde on tällä hetkellä yli 50% ja lähes 70% mikäli tasapelit sisältyvät voittosuhteeseen.

**Mitä syötteitä käytetty**
* Toistuvasti saman käden pelaaminen (k, k, k, k, k...... k)
* Oletettu rotaatio (k, p, s, k, p, s, k..... s)
* Käänteinen rotaatio (k, s, p, k, s, p, k.... p)
* Satunnaisuus

Testit voi toistaa kirjaimellisesti 1:1 ja lopputulos on riittävän pitkällä pelillä aina sama (yli 30 kättä).

**Tulevaisuuden suunnitelmia**
Yksikkötesti, jolla testataan yllä mainittuja syötteitä. Tällä saavutettaisiin konkreettinen koneen voittosuhteen kasvun (tai laskun) toteaminen.