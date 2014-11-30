# Suorituskykytestausdokumentti

**Mitä testattiin**
Testaus tutki koneen aikaa joka kului käyttäjän syötteen jälkeen tekoälyn palauttaman käden palauttamiseen. Testi siis tutki kuinka paljon aikaa kuluu kaikkiin tekoälyn operaatioihin yhteensä. 

**Miten testatiin**
Käyttöliittymän while-loopin kohta jossa käyttäjän antama käsi on todettu validiksi asetettiin aikalaskurin alkupiste. Laskurin loppupisteenä toimi hetki kun kone on tulostanut voittajan. Alla hahmotelma testistä

käsi on validi
ASETA ALKUAIKA
aseta tekoälyn käsi
aseta pelaajan käsi
pelaa kierros ja päivitä kaikki statistiikka
palauta kierroksen tulos
ASETA LOPPUAIKA
tulosta loppuaika-alkuaika

Testissä käytetiin aluksi ajan tarkkuutena millisekunnteja. Tulokset olivat kuitenkin 0 toistuvasti, joten testeissä on käytetty nanoluokan tarkkuutta. 

**Mitä graafi esittää**
Projektin docs-kansiossa on Suorituskykytesti.png kuva. Kuvaan on merkitty 35 peräkkäisen testin viemä aika. Ensimmäisen testin suuri aikavaativuus syntyy ensimmäisen ajon yhteydessä luotavista luokista. Pientä hajontaa aikavaativuudessa on havaittavissa toisesta testistä 10:een testiin. Tämän jälkeen graafi on lähes vaakasuora. Tästä ilmenee ohjelman vakioaikainen aikavaativuus. 