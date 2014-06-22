#Testaus#

Testasin ohjelmaa sekä käsin että jUnit -testein. jUnit -testeillä testasin luokkia, käsin koko ohjelman toimintaa. Varsinaisen pakkauksen testaamiseen käytin lähinnä tekstitiedostoja, joiden koko vaihtelee 5-400 kilotavun välillä. Tekstitiedostot ovat genereoitua lorem ipsumia sekä Guthenberg-projektista ladattu lyhyehkö kirja. Suorituskyvyn testaukseen on performanceTest- jUnit-testi, joka kirjoittaa lokia tiedostoon Tiralabra_maven/suoritusaikaloki.txt. Testi käyttää 377:n kt:n kokoista iso.txt -tiedostoa, purkajat samaa tiedostoa pakattuna.      

Hankalaa testauksessa oli se, että pakkausta ja purku piti yleensä testata yhdessä, mikä laajensi testattavaa aluetta. Purettujen tekstitiedostojen virheistä oli tosin usein helppo päätellä bugien aiheuttaja. 

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

LZ77:ssa käytin 4kt:n ikkunaa ja 16:sta merkin enimmäispituutta, eli esitys muodostuu 12 bitistä etäisyyttä ja 4 bitistä pituutta. 2^16 tavun ikkunalla saman tiedoston pakkaaminen kesti noin kymmenen sekuntia, mutta purku suurin piirtein saman verran kuin 4kt:n ikkunalla. Tiedosto pakkautui tällöin 149 kilotavuun, eli 44.2% alkuperäisestä. Suuremmat ikkunakoot ja siten paremmat pakkaussuhteet eivät siis dramaattisesti hidasta ohjelmaa. Purkamisnopeus on kuitenkin lähes riippumaton pakkaussuhteesta, mistä voi olla hyötyä joissain sovelluksissa.

LZW:ssa käytin 4096:n merkinnän hakemistoa, eli 12 bitin pituisia viitteitä. LZW:n hakemiston kasvattaminen 2^14 merkintään ei hidastanut pakkaamista eikä purkua: pakkaaminen sujui noin 2,8:ssa sekunnissa ja purku noin 4,2:ssa sekunnissa. Tiedosto pakkautui 156 kilotavuun. Tällä pakkaussuhteella LZW on siis selkeästi tehokkaampi, mutta LZ77 purkaa yhä nopeammin. 

####Bugit####

LZ77:n jäi bugi, jonka takia satunnaisia merkkejä jää pois. Suuremmalla ikkunalla bugit vaikuttavat harvenevan. Koska bugi ilmeni ensimmäisen kerran toteutettuani SlidingTable-luokan, ongelma on ilmeisesti siinä. SlidingWindow:stakin löytyi ongelma, mutta tämän korjaaminen ei poistanut bugia kokonaan. 

LZW toimii moitteetta. Kokeilin myös kuvatiedostoilla, niissäkään ei ilmennyt ongelmaa.  