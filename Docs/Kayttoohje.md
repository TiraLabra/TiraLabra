# Käyttöohje

## Ohjelman kääntäminen

Pelin kääntämistä varten tarvitaan GCC-kääntäjä sekä GNU Make ohjelma. Ohjelman käännetään
ajamalla "make" komento src/ hakemistossa. Yksikkötestien ajaminen onnistuu komennolla
"make test".

## Pelin pelaaminen

Peli käynnistyy ajamalla komento ./chess.exe. Tekoälyn luoman pelipuun syvyyttä voi
halutessa säätää komentoriviparametrilla, esim ./chess.exe 5. Oletuksena puun syvyys on 4,
ja käytännössä 5:ttä suuremmalla syvyydellä tekoäly syö kaiken muistin ja lopulta
kaatuu. Valkoiset pelinappulat on merkitty isoin kirjaimin, ja mustat pienin. Kirjaimet
tulevat pelinappuloiden englanninkielisistä nimistä ja tarkoittavat siis seuraavaa:

P = sotilas
B = lähetti
N = ratsu
R = torni
Q = kuningatar
K = kuningas

Siirto syötetään kirjoittamalla alkukoordinaatti ja loppukoordinaatti. Esimerkiksi
vasemmanpuoleisimman sotilaan siirtäminen kahdella eteenpäin onnistuu komennolla "a2a4".
Tämän jälkeen tekoäly laskee ja suorittaa siirtonsa, ja peli kysyy uutta siirtoa.
