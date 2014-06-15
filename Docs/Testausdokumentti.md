#Testaus#

Testasin ohjelmaa sek‰ k‰sin ett‰ jUnit -testein. jUnit -testeill‰ testasin luokkia, k‰sin koko ohjelman toimintaa. Varsinaisen pakkauksen testaamiseen k‰ytin l‰hinn‰ tekstitiedostoja, joiden koko vaihtelee 5-70 kilotavun v‰lill‰. Tekstitiedostot ovat genereoitua lorem ipsumia. Suorituskyvyn testaukseen on performanceTest- jUnit-testi, joka kirjoittaa lokia tiedostoon Tiralabra_maven/suoritusaikaloki.txt. Testi k‰ytt‰‰ 70,4 kt:n kokoista isolorem.txt -tiedostoa, purkajat samaa tiedostoa pakattuna.      

Hankalaa testauksessa oli se, ett‰ pakkausta ja purku piti yleens‰ testata yhdess‰, mik‰ laajensi testattavaa aluetta. Purettujen tekstitiedostojen virheist‰ oli tosin usein helppo p‰‰tell‰ bugien aiheuttaja. 

####Tilastoja####

######isolorem.txt, 70,4 kt######
|algoritmi | pakkaus ka | pakkaus paras | purku ka | purku paras | pakattu koko | suhde %        |
|----------|------------|---------------|----------|-------------|--------------|----------------|
|LZ77      | 529 ms     | 437 ms        | 605 ms   | 343 ms      | 23,8 kt      | 33,8%          |
|LZW       | 465 ms     | 359 ms        | 594 ms   | 375 ms      | 24,4 kt      | 34,7 %         |