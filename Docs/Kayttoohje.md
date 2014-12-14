# K�ytt�ohje

## Ohjelman k��nt�minen

Pelin k��nt�mist� varten tarvitaan GCC-k��nt�j� sek� GNU Make ohjelma. Ohjelman k��nnet��n
ajamalla "make" komento src/ hakemistossa. Yksikk�testien ajaminen onnistuu komennolla
"make test".

## Pelin pelaaminen

Peli k�ynnistyy ajamalla komento ./chess.exe. Teko�lyn luoman pelipuun syvyytt� voi
halutessa s��t�� komentoriviparametrilla, esim ./chess.exe 5. Oletuksena puun syvyys on 4,
ja k�yt�nn�ss� 5:tt� suuremmalla syvyydell� teko�ly sy� kaiken muistin ja lopulta
kaatuu. Valkoiset pelinappulat on merkitty isoin kirjaimin, ja mustat pienin. Kirjaimet
tulevat pelinappuloiden englanninkielisist� nimist� ja tarkoittavat siis seuraavaa:

P = sotilas
B = l�hetti
N = ratsu
R = torni
Q = kuningatar
K = kuningas

Siirto sy�tet��n kirjoittamalla alkukoordinaatti ja loppukoordinaatti. Esimerkiksi
vasemmanpuoleisimman sotilaan siirt�minen kahdella eteenp�in onnistuu komennolla "a2a4".
T�m�n j�lkeen teko�ly laskee ja suorittaa siirtonsa, ja peli kysyy uutta siirtoa.
