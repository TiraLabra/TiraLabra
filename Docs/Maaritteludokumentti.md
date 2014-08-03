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
Branch and bound -menetelmä on valittu alkgoritmin toiminnan perustaksi, koska se on paras menetelmä ratkoa summan minimoinnin optimointia.
Algortimi saa syötteinä pakettien tilavuudet ja kontin vetoisuuden.

Algoritmin nopeutta mitataan tilastollisesti, koska alle 5% tilavuuteen voi olla useita ratkaisua kussakin täyttötilanteessa.
Algoritmin performointia analysoidaan erityyppisillä syötteillä esim. pieniä ja isoja paketteja
Vertailukohtana on ratkaisun löytämisnopeus satunnaistäytöllä.

Alustavasti tiedetään, että algitmi ei tule olemaan erityisen nopea ja kasvaa eksponentiaalisesti esim. pakettien lukumäärän ja kontin tilavuuden kasvaessa.
Kuitenkin sillä on mahdollista löytää usein nopeita ratkaisuja. Lähde http://web.stanford.edu/class/ee364b/lectures/bb_slides.pdf

