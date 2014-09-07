Käyttöohje
==============

Ohjelman tekstikäyttöliittymän käynnistys
-------

Ohjelman tekstikäyttöliittymän saa käyntiin ajamalla juurihakemistosta löytyvä skripti run_cli.sh, tai komennolla java -jar Tiralabra.jar cli. Tekstikäyttöliittymä pyytää syötteenä halutun polynomin karakteristikan sekä asteen, ja tulostaa sitten kokeillut polynomit sekä lopullisen löydetyn polynomin. Tekstikäyttöliittymä käyttää aina tehokkainta mahdollista heuristiikkaa.

Syötteen antaminen tiedostossa
-------

Ohjelman varsinainen käyttötapa on antaa sille syötteenä tiedosto joka sisältää haluttujen polynomien asteet ja karakteristikat. Syötetiedostossa annetaan jokaisella rivillä yhden polynomin aste ja karakteristika pilkulla erotettuna. Karakteristikan pitää olla alkuluku. Esimerkiksi jos halutaan generoida jaottomat polynomit karakteristikalla 3 ja astella 50 sekä karakteristikalla 2 ja asteella 98 sisältäisi syötetiedosto seuraavat rivit (ilman riviä välissä):

3,50

2,98

Esimerkkisyötetiedostoja löytyy hakemistosta test_input_files.

Itse algoritmin käynnistys tapahtuu seuraavasti:

java -jar Tiralabra.jar tiedoston_nimi heuristiikan_nimi > tuloste_tiedosto.csv

Ensimmäinen argumentti on siis syötetiedoston nimi. Toisena argumenttina annetaan haluttu heuristiikka, joka voi olla yksi seuraavista:

- naive : Generoi polynomeja tasaisen jakauman avulla, kuitenkin siten että polynomilla on aina vakiokerroin.
- sparse : Generoi lyhyempiä polynomeja laskennan nopeuttamiseksi
- checkroots : Tarkistaa onko polynomilla juuria ennen kuin se annetaan Rabinin jaottomuusalgoritmille
- smartchar2 : Mikäli karakteristika on 2, varmistaa vakioajassa että generoidulla polynomilla ei ole juuria
- sparse_checkroots : Yhdistelmä heuristiikoista sparse ja checkroots
- sparse_smartchar2 : Yhdistelmä heuristiikoista sparse ja smartchar2
- sparse_checkroots_smartchar2 : Yhdistelmä heuristiikoista sparse, checkroots ja smartchar2

Testien nojalla heuristiikoista nopein on sparse_checkroots_smartchar2.

Ohjelma tulostaa löydetyt polynomit stdout:iin, joten on suositeltavaa (kuten esimerkkikäynnistyskomennossa) uudelleenohjata tuloste tiedostoon. Tuloste annetaan csv-muodossa, joten sen voi helposti avata esim. Excelissä tai LibreOfficessa.

Demonstraatiopolynomien generointi
-------

Juurikansiossa on skripti generate_mixed_polynomials.sh, joka generoi muutamia polynomeja ja kirjoittaa tulokset tiedostoon mixed_output.csv.

Yksikkötestien ajaminen
-------

Ohjelman yksikkötestit voi suorittaa juurikansion skriptillä run_tests.sh.
