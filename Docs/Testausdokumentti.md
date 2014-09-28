Koodin testaus:
 -------------
Ohjelman koodin testaamiseen k‰ytet‰‰n JUnit yksikkˆtestausta. JUnitilla testataan ett‰ luokat k‰ytt‰ytyv‰t odotetulla tavalla tilanteissa, joista ohjelman edellytet‰‰n selviytyv‰n. Toistaiseksi JUnitin rivi- ja haarakattavuus ei useimmille luokille ole l‰hellek‰‰n 100%, mutta t‰t‰ on paikattu ajamalla ohjelmaa debug tilassa ja t‰ten varmistamalla ett‰ se k‰ytt‰ytyy odotetulla tavalla.
Tulostukseen liittyvi‰ toimintoja tai k‰yttˆliittym‰‰ ei ole testattu koneellisesti lainkaan, mutta niille on tehty edell‰ mainittua ohjelman ajamista ja tulosteen silm‰m‰‰r‰ist‰ vertaamista odotettun.
Suurin osa testeist‰ voidaan helposti toistaa ajamalla JUnit testit.


Suorituskyvyn testaus:
 --------------
Ohjelman suorituskyvyn testaukseen on toistaiseksi k‰ytetty vain luodun Sekuntikello luokan antamia aikoja toimintojen suoritusajoille.