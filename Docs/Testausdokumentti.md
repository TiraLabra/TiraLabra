#Testaus#

Testasin ohjelmaa sek� k�sin ett� jUnit -testein. jUnit -testeill� testasin luokkia, k�sin koko ohjelman toimintaa. Varsinaisen pakkauksen testaamiseen k�ytin l�hinn� tekstitiedostoja, joiden koko vaihtelee 5-400 kilotavun v�lill�. Tekstitiedostot ovat genereoitua lorem ipsumia sek� Guthenberg-projektista ladattu lyhyehk� kirja. Suorituskyvyn testaukseen on performanceTest- jUnit-testi, joka kirjoittaa lokia tiedostoon Tiralabra_maven/suoritusaikaloki.txt. Testi k�ytt�� 377:n kt:n kokoista iso.txt -tiedostoa, purkajat samaa tiedostoa pakattuna.      

Hankalaa testauksessa oli se, ett� pakkausta ja purku piti yleens� testata yhdess�, mik� laajensi testattavaa aluetta. Purettujen tekstitiedostojen virheist� oli tosin usein helppo p��tell� bugien aiheuttaja. 

####Tilastoja####

######isolorem.txt, 70,4 kt######
|algoritmi | pakkaus ka | pakkaus paras | purku ka | purku paras | pakattu koko | suhde %        |
|----------|------------|---------------|----------|-------------|--------------|----------------|
|LZ77      | 529 ms     | 437 ms        | 605 ms   | 343 ms      | 23,8 kt      | 33,8%          |
|LZW       | 465 ms     | 359 ms        | 594 ms   | 375 ms      | 24,4 kt      | 34,7 %         |

###### .txt 337 kt ######
|algoritmi | pakkaus ka | pakkaus paras | purku ka | purku paras | pakattu koko | suhde %        |
|----------|------------|---------------|----------|-------------|--------------|----------------|
|LZ77      | 3735 ms    | 3720 ms       | 4233 ms  | 4190 ms     | 182 kt       | 54,0%          |
|LZW       | 2953 ms    | 2930 ms       | 4288 ms  | 4210 ms     | 171 kt       | 50,7%          |

LZ77:ssa k�ytin 4kt:n ikkunaa ja 16:sta merkin enimm�ispituutta, eli esitys muodostuu 12 bitist� et�isyytt� ja 4 bitist� pituutta. 2^16 tavun ikkunalla saman tiedoston pakkaaminen kesti noin kymmenen sekuntia, mutta purku suurin piirtein saman verran kuin 4kt:n ikkunalla. Tiedosto pakkautui t�ll�in 149 kilotavuun, eli 44.2% alkuper�isest�. Suuremmat ikkunakoot ja siten paremmat pakkaussuhteet eiv�t siis dramaattisesti hidasta ohjelmaa. Purkamisnopeus on kuitenkin l�hes riippumaton pakkaussuhteesta, mist� voi olla hy�ty� joissain sovelluksissa.

LZW:ssa k�ytin 4096:n merkinn�n hakemistoa, eli 12 bitin pituisia viitteit�. LZW:n hakemiston kasvattaminen 2^14 merkint��n ei hidastanut pakkaamista eik� purkua: pakkaaminen sujui noin 2,8:ssa sekunnissa ja purku noin 4,2:ssa sekunnissa. Tiedosto pakkautui 156 kilotavuun. T�ll� pakkaussuhteella LZW on siis selke�sti tehokkaampi, mutta LZ77 purkaa yh� nopeammin. 

####Bugit####

LZ77:n j�i bugi, jonka takia satunnaisia merkkej� j�� pois. Suuremmalla ikkunalla bugit vaikuttavat harvenevan. Koska bugi ilmeni ensimm�isen kerran toteutettuani SlidingTable-luokan, ongelma on ilmeisesti siin�. SlidingWindow:stakin l�ytyi ongelma, mutta t�m�n korjaaminen ei poistanut bugia kokonaan. 

LZW toimii moitteetta. Kokeilin my�s kuvatiedostoilla, niiss�k��n ei ilmennyt ongelmaa.  