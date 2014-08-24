Testaus
=======

Samoilla heuristiikoilla, hakusyvyyksillä ja algoritmeilla toteutettuna tekoälyt pelaavat aina tismalleen samanlaisen pelin, sillä niiden toteutuksessa ei ole satunnaisuutta. Toistuvat testit eivät siis ainakaan vielä ole mielekkäitä. Saattaa olla että lisään tekoälyn toimintaan vielä satunnaisuutta esimerkiksi siten että useista yhtä hyvistä siirroista ei valitakaan ensimmäistä vaan arvotaan joku. Luulisin että tällä olisi merkitystä lähinnä pelin alussa, jolloin pelissä on todella useita samanarvoisia siirtoja. Myöhemmin eri siirtojen arvot taitavat lähes aina poiketa toisistaan. Tällöinkin voisi ehkä arpoa jonkin siirron esimerkiksi muutaman parhaan siirron joukosta, jos haluaisi satunnaisuutta.

Aluksi testasin ohjelman toimintaa ilman satunnaisuutta. Laitoin eri hakusyvyyksillä varustetut minimax-algoritmia käyttävät tekoälyt pelaamaan toisiaan vastaan ja sain seuraavanlaiset tulokset:
v= valkoinen voitti, m= musta voitti, p= pattitilanne

Valkoisen syvyys Mustan syvyys-> | 1 | 3 |5 |7 |
1 | v | p | p | m |
3 | v | m | m | p | 
5 | p | v | p | - |

Nyt peli näyttäisi päättyvän usein pattitilanteeseen, jossa molemmat pelaajat liikuttavat kuninkaitaan edestakaisin. Jos toinen pelaajista tekisi jotain muuta, se todennäköisesti myös häviäisi pelin. Pattitilanteita varten tekoälyyn pitäisi ehkä yrittää kehittää joku ratkaisu.

Muuten tulokset näyttäisivät melko ennalta odotettujen mukaisilta. Suurempi hakusyvyys on avain voittoon. Muilla hakusyvyyksillä tekoäly toimii riittävän ripeästi, mutta 7 alkaa olla jo turhan hidas. Tällä hetkellä ohjelmassa voi valita hakusyvyydet 1-7, mutta ehkä joudun vielä muuttamaan tätä mikäli en onnistu nopeuttamaan algoritmin toimintaa. Toki algoritmin toiminta nopeutuu pelin loppua kohti, kun nappuloiden määrä laudalla pienenee. Ehkä algoritmi voisikin kasvattaa hakusyvyyttä pelin edetessä.
