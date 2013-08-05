
KOLMIOPELIN ALGORITMIEN LIS�YS JA PARANNUS
-------------------------------------------
	
	T�m� TiraLabra projekti kerkittyy luomaan ja parantelemaan 
aiemmin Ohjelmoinnin harjoitusty�ss� luotua Kolmiopeli�, erityisesti 
sen eri algoritmej�. T�ss� vaiheessa olen suunnitellut lis��v�n seuraavia
ominaisuuksia peliin TiraLabran aikana:


1. Kombojen muodostumisten mahdollistaminen
	
	T�ll� hetkell� kolmiopeliss� ei ole mahdollista tulla yhden siirron
	j�lkeen ns. "domino"-efekti� jossa viereiset kolmiot r�j�ht�v�t
	uusien ollessa sopivia. T�t� varten peliin tarvitaan jonkinlaista 
	uusien kolmioiden l�heisten kolmioiden l�pik�ynti� joka selvitt��
	muodostuiko pelilaudalle uusia pistetilanteita.
		Koska pelilauta voidaan ajatella verkkona niin l�pik�ynti
	voidaan suorittaa konkinlaisella leveyssuuntaisella l�pik�ynnill�,
	joka etenee niin pitk�lle kun samanv�risi� kolmioita l�ytyy.
		Aikavaativuutta en viel� osaa arvioida tarkemmin, mutta 
	tavoitteena tietenkin on ettei t�m� l�pik�ynti k�y turhaan esim.
	koko pelilautaa l�pi vaan lopettaa kun sopivan v�risi� kolmioita 
	ei l�ydy.

2. Pelilaudan romahtamis-efekti
	
	T�ll� hetkell� uudet kolmiot arvotaan suoraan r�j�ht�neiden paikalle,
	mutta tavoitteena olisi ett� r�j�ht�neiden paikalle romahtaisi yl�puolelta
	kolmioita ja uudet kolmiot arvottaisiin pelilaudan yll�.	
		En ole viel� p��tt�nyt/keksinyt miten toteutan kolmioiden 
	romahtamisen joten t�ss� vaiheessa t�h�n kohtaan ei ole viel� sanottavaa
	tarkemmista algoritmeista tai aikavaativuuksista.

(3. Olemassaolevan pistesiirtojen etsimisalgoritmin parantaminen)

	T�m� on yksi mahdollinen lis�ys/parannus mit� peliin voi TiraLabran
	aikana tehd�. T�ll� hetkell� peli k�y jokaisen siirron j�lkeen koko
	laudan l�pi p�ivitt�ess��n miss� kohtaa lautaa on mahdollisia siirtoja,
	vaikka siirtojen oikean m��r�n yll�pitoon paljon pienemm�n alueen 
	l�pik�ynti riitt�isi.


N�m� kolme (kaksi ensimm�ist� ensisijaisesti) ovat t�ss� vaiheessa TiraLabraa 
suunnittelemani tavoitteet. Koska pelilauta on aika pieni niin algoritmien 
tehokkuus ei ehk� tule n�kyviin tai ole olennaista mutta ensisijaisena 
tavoitteena on taata algoritmien oikeellisuus ja toimivuus.