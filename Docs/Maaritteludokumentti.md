
KOLMIOPELIN ALGORITMIEN LISÄYS JA PARANNUS
-------------------------------------------
	
	Tämä TiraLabra projekti kerkittyy luomaan ja parantelemaan 
aiemmin Ohjelmoinnin harjoitustyössä luotua Kolmiopeliä, erityisesti 
sen eri algoritmejä. Tässä vaiheessa olen suunnitellut lisäävän seuraavia
ominaisuuksia peliin TiraLabran aikana:


1. Kombojen muodostumisten mahdollistaminen
	
	Tällä hetkellä kolmiopelissä ei ole mahdollista tulla yhden siirron
	jälkeen ns. "domino"-efektiä jossa viereiset kolmiot räjähtävät
	uusien ollessa sopivia. Tätä varten peliin tarvitaan jonkinlaista 
	uusien kolmioiden läheisten kolmioiden läpikäyntiä joka selvittää
	muodostuiko pelilaudalle uusia pistetilanteita.
		Koska pelilauta voidaan ajatella verkkona niin läpikäynti
	voidaan suorittaa konkinlaisella leveyssuuntaisella läpikäynnillä,
	joka etenee niin pitkälle kun samanvärisiä kolmioita löytyy.
		Aikavaativuutta en vielä osaa arvioida tarkemmin, mutta 
	tavoitteena tietenkin on ettei tämä läpikäynti käy turhaan esim.
	koko pelilautaa läpi vaan lopettaa kun sopivan värisiä kolmioita 
	ei löydy.

2. Pelilaudan romahtamis-efekti
	
	Tällä hetkellä uudet kolmiot arvotaan suoraan räjähtäneiden paikalle,
	mutta tavoitteena olisi että räjähtäneiden paikalle romahtaisi yläpuolelta
	kolmioita ja uudet kolmiot arvottaisiin pelilaudan yllä.	
		Romahtaminen tapahtuu "linjoina" (kuva Docseissa) ja aikavaativuus
	on pahimmillaan tuhoutuneiden kolmioiden leveys kertaa niitten syvyys peliruudukossa.

Nämä kaksi ovat tässä vaiheessa TiraLabraa 
suunnittelemani tavoitteet. Koska pelilauta on aika pieni niin algoritmien 
tehokkuus ei ehkä tule näkyviin tai ole olennaista mutta ensisijaisena 
tavoitteena on taata algoritmien oikeellisuus ja toimivuus.