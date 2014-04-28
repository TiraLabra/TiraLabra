Ohjelma jäi aika yksinkertaiseksi.

O-analyysi ja pseudokoodi pakkaukselle:
huffmanEncode:
	initialize huffman nodes O(n)
	generate huffman tree ~O(c) (max node count = 512)
	sort huffman nodes by frequency ~O(c)
	
	write original data size and node count O(c)
	
	set huffman nodes' codes ~O(c)
	write node frequencies and character codes ~O(c)
	
	sort huffman nodes by character code ~O(c)
	
	write input to output using huffman codes O(n log n) <-- All time wasted here, bit twiddling is expensive

huffmanDecode:
	read original data size and node count O(c)
	read node frequencies and character codes ~O(c) (max node count = 512)
	
	generate huffman tree ~O(c)
	
	write input to output using huffman tree O(n log n)

Kerkesin muutamalla tiedostolla testaamaan ohjelmaa:
Vanha Ubuntu Net Install iso-tiedosto:	12.5137 MiB -> 12.3623 MiB (merkityksetöntä)
Pitkästi lorem ipsumia:					2.87585 MiB -> 1.51886 MiB (53% alkuperäisestä, hyvin pakkautuvaa)

Testasin samojen tiedostojen pakkaamista 7z ohjelmalla:
Vanha Ubuntu Net Install iso-tiedosto:	12.5137 MiB -> 12.5307 MiB (merkityksetöntä, oma pakkaus ohjelma pärjäsi paremmin ha ha)
Pitkästi lorem ipsumia:					2.87585 MiB -> 1.322   MiB (46% alkuperäisestä, hyvin pakkautuvaa)

Noin puolet harjoitustyöhön käyttämästäni ajasta meni tavallaan hukkaan kun yritin tehdä omaa algoritmia,
idea vaikutti hyvältä mutta osoittautu aika hankalaksi toteuttaa.
Olisin halunnut vielä kokeilla pakkaamista sanakirjan avulla tai vastaavaa.