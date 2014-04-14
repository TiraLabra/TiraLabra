Koodi on toteutettu javalla ja testaukseen on kaytetty JUnit -testeja.
Testaus on suoritettu niin, etta jokaista jarjestamisalgoritmia on testattu monilla 
satunnaistaulukoilla, seka joillakin kovakoodatuilla erityistapauksilla.
Erityistapauksiin lukeutuvat esimerkiksi jo valmiiksi jarjestyksessa olevat taulukot,
todella suuret taulukot seka vain samaa lukua sisaltavat taulukot.
Joitain algoritmien kayttamia metodeita on myos testattu erikseen kayttaen junittia.

TESTAUKSESSA KAYTETYT TAULUKOT TARKEMMIN:

10 kpl 100 alkioista satunnaistaulukkoa.
100 alkioinen jarjestyksessa oleva taulukko.
100 alkioinen kaanteisessa jarjestyksessa oleva taulukko.
100 alkioinen kaikki samaa lukua oleva taulukko.
1 alkioinen taulukko.
0 alkioinen taulukko.
10 000 000 alkioinen suurikokoinen satunnaistaulukko.
Kovakoodattu taulukko: {4,4,4,2,2,8,8,325,55,55,55,55,55,55,344,1,1,1,1,1,1,5,1,1,1,1,1,1,1,576}


JUnitin lisaksi on tehty myos empiirista suorituskykytestausta, jolla on tarkastettu
etta algoritmit toimivat suurinpiirtein halutuilla aikavaatimuksilla.

Hieman empiirista suorituskykytestausta:

PIENET SATUNNAISET TAULUKOT:
Merge: 2ms
Intro: 2ms
Smooth: 3ms.
PARAS: Merge/Intro.

KESKIKOKOISET SATUNNAISET TAULUKOT:
Merge: 59ms
Intro: 39ms
Smooth: 46ms
PARAS: Intro

SUURET SATUNNAISET TAULUKOT:
Mergesort: 3260ms
Introsort: 1298ms
Smootsort: 6972ms
PARAS: Intro.

KAIKKIAAN PARAS SATUNNAISTEN TAULUKOIDEN JARJESTAJA: Intro.

-----------------------------------------------------------

PIENET JARJESTYKSESSA OLEVAT TAULUKOT:
Merge: 0ms
Intro: 0ms
Smooth: 0ms
PARAS: -

KESKIKOKOISET JARJESTYKSESSA OLEVAT TAULUKOT:
Merge: 12ms
Intro: 2ms
Smooth: 3ms

PARAS: Intro

SUURET JARJESTYKSESSA OLEVAT TAULUKOT:
Merge: 2040ms
Intro: 421ms
Smooth: 409ms

PARAS: Smooth

PARAS JARJESTYKSESSA OLEVIEN TAULUKOIDEN JARJESTAJA: Smooth.