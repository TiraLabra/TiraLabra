Ongelman kuvaus:
Rahtifirma NopsaToimitus haluaa optimoida konttikuljetuksissa käytettävän tilan. Suunnittele miten voidaan täyttää yksi tai useampi kontti mahdollisimman tehokkaasti, jos tiedetään pakettien määrä ja koot.

Ongelma määritys:
Tehokkuus tarkoittaa sitä, että täytettävät kontit tulevat mahdollisimman täyteen. 
Konteilla on tietty vetoisuus
Paketeilla on tietty tilavuus
Pakettien tilavuuksien summa pyritään saada mahdollisimman lähelle kontin vetoisuutta.
Käytännössä tehokkaasti täytettu kontti on sellainen jossa on vähemmän kuin 5% tyhjää tilaa.

Ratkaisusuunnitelma:
Pyritään kehittää algoritmi, joka osaa laskea mahdollisimman tehokkaasti konttien täyttökokoonpanoja.
Ensiksi kehitetään algoritmi, joka käy tehokkaasti läpi mahdollisia kokoonpanoja. Tehokas tarkoittaa sitä, että ei käy läpi samaa kokoonpanoa uudelleen.
Ratkaisu on käydä siis läpi mahdollisia pakettien kokoamiskombinaatioita läpi siten, että pakettien järjestyksellä ei ole väliä.

Toiseksi pyritään parantaa algoritmia Branch and bound -menetelmällä, koska se on paras menetelmä ratkoa summan minimoinnin optimointia.

Kummatkin algortimit saavat syötteinä pakettien tilavuudet ja kontin vetoisuuden.

Algoritmien nopeutta mitataan tilastollisesti, sillä alle 5% tyhjyysasteeseen voi olla useita ratkaisua kussakin täyttötilanteessa, jolloin riittää että löydetään ensimmäinen. Tästä muodostuu satunnaismuuttuja, jota mitataan tilastollisesti.
Algoritmien performointia analysoidaan erityyppisillä syötteillä esim. pieniä ja isoja paketteja.
Vertailukohtana on ratkaisun löytämisnopeus satunnaistäytöllä.

Alustavasti tiedetään, että paraskaan algitmi ei tule olemaan erityisen nopea ja kasvaa eksponentiaalisesti esim. pakettien lukumäärän ja kontin tilavuuden kasvaessa.
Kuitenkin sillä on mahdollista löytää usein nopeita ratkaisuja. Lähde http://web.stanford.edu/class/ee364b/lectures/bb_slides.pdf

