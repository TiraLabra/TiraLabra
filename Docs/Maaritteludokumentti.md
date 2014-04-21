Toteutetaan useamman (3-4) jarjestamisalgoritmin implementaatio javalla ja vertaillaan niita keskenaan
toteutuksen tehokkuuden kannalta. Lopullisesta tyosta kay ilmi, minkalaiset aika- ja tilavaatimukset
eri jarjestamisalgoritmeilla on ja missa tilanteessa kutakin algoritmia on tehokkainta kayttaa.

Implementoitavat jarjestamisalgoritmit:

1. Mergesort AIKA: O(n log(n)) aina. TILA: O(n) pahimmassa tapauksessa.
2. Smoothsort AIKA: O(n log(n)) pahin. TILA: O(1)
3. Introsort AIKA: O(n log(n))  TILA: O(1) 
(4. Heapsort (toteutus jo introsortissa))
(5. Quicksort (toteutus jo introsortissa))
 
 
 Jokainen algoritmi jarjestaa javan perinteisia kokonaislukutaulukoita. 
 Lahteina on kaytetty Wikipedian Sorting Algorithms -sivua seka Tietorakenteet ja Algoritmit -kurssin kurssimateriaalia.
 
 
 Ohjelman suoritus:
 
 Lopullisessa muodossaan ohjelman on tarkoitus suorittaa jokainen jarjestamisalgoritmi usealle eri taulukolle.
 Taulukot vaihtelevat pienista suuriin seka taysin satunnaisgeneroiduista melkein jarjestyksessa oleviin. 
 Myos kaanteisessa jarjestyksessa olevia taulukoita testataan.
 Ohjelma kertoo kuinka kauan kussakin jarjestyksessa meni ja lopuksi kokoaa tilastonakyman, josta on helppo vertailla
 eri jarjestamisalgoritmien suorituskykya erilaisissa tilanteissa.