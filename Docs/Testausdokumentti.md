Testaamisessa on käytetty JUnitia, joten testit voi ajaa helposti vaikkapa NetBeansissa.
Luokkatestit ovat pääsääntöisesti hyvin yksinkertaisia ja niissä käytännössä tarkistetaan luokkien luomisen, sekä 
metodien tuloksien oikeellisuus.

Erittäin alkeellinen suorituskykytesti tuotti seuraavanlaisia tuloksia:

Ruudukon koko		Alkioiden määrä edelliseen nähden		Reitinhaun kesto
10 x 10 			-										<1ms 	peräkkäisillä toistokerroilla
100 x 100			100-kertainen							~14ms 	peräkkäisillä toistokerroilla
1000 x 1000			100-kertainen							~1400ms	peräkkäisillä toistokerroilla

Yksinkertaisen testin perusteella voidaan arvella, että suoritusaika on (~suoraan)verrannollinen syötteen kokoon.

