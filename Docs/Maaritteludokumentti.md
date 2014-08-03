Tietorakennevertailut
=====================
 
Vertaan eri tasapainotettujen hakupuiden tehokkuutta. Toteutan työssäni punamustan puun, AA-puun, splay-puun ja B-puun:
 
- Punamustassa puussa jokaisella solmulla on väri, punainen tai musta, jota käytetään apuna puun tasapainottamisessa.
- AA-puu on punamustan puun variaatio, jossa punainen solmu voi olla vain vanhempansa oikeana lapsena.
- Splay-puussa solmut järjestetään siten, että useimmin käsitellyt siirretään puun yläosaan, jolloin ne ovat nopeiten saatavilla.
- B-puu eroaa edeltävistä siten, ettei se ole binäärinen vaan solmulla voi olla useampia lapsia.

Puiden tehokkuutta vertailen eri kokoisilla syötteillä koskien lisäys-, poisto- ja haku-operaatioita ja mittaamalla niihin kuluvaa aikaa. Kaikkien näiden aikavaativuudet ovat luokkaa O(logn) ja puiden tilavaativuudet O(n). 

Lähteet:

http://en.wikipedia.org/wiki/Red_black_tree

http://en.wikipedia.org/wiki/AA_tree

http://web.eecs.umich.edu/~sugih/courses/eecs281/f11/lectures/12-AAtrees+Treaps.pdf

http://en.wikipedia.org/wiki/Splay_tree

http://en.wikipedia.org/wiki/B_tree
