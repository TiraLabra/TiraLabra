Ohjelma on toteutettu käyttämällä 5 erillistä luokkaa. Seuraavana lyhyt kuvaus kaikista:

App

	Pääohjelma suoritusta varten. Tarjoaa käyttöliittymän, jonka avulla ohjelmalle voidaan syöttää karttoja.
	App:n on sisällytetty myös algoritmin läpimenoajan tarkistus (sisältää funktiokutsuja).
	
	main()																					Aika
	1	in = Scanner																	1	1
	2	while(true)																		2	
	3		Print(Syötä tiedoston nimi, nolla lopettaa ohjelman)						3	1
	4		s is in.nextLine()															4	1
	5		if(s.equals("0"))															5	1
	6			Print("Exit")															6	1
	7			break																	7	1
	8		try																			8	1
	9			br is BufferedReader(FileReader(s))										9	1
	10			i = 0																	10	1
	11			while ts=br.readLine() not null											11	h (map.height)
	12				i++																	12	1
	13			br is BufferedReader(FileReader(s))										13	1
	14			ts is br.readLine()														14	1
	15			m = new Map(i,ts);														15	1
	16			m.insertRow(ts);														16	w (map.width)
	17			while ts is br.readLine() not null										17	h-1
	18				m.insertRow(ts)														18	w							
	19			m.printField();															19	hw
	20			duration = performSearch(m);											20	n^(2(n+log n)+n+hw)
	21			Print("Aikaa reitin etsimiseen meni: "+duration+"ms")					21	1
	22			catch(Exception e)														22	1
	23			Print("Tiedoston lukeminen ei onnistunut: "+e);							23	1
	
	Aikavaativuus on luokkaa O(h+2hw+n^(2(n+log n)+n+hw))
	
	
	performSearch(Map m)						Aika
	1	Print(Löydetty reitti:)				1	1
	2	f = Finder()						2	1
	3	start = start-time					3	1
	4	f.findPath(Map, Start, Goal)		4	n^(2(n+log n)+n+hw)
	5	lend = ent-time						5	1
	6	return end-start					6	1
	
	Aikavaativuus O(n^(2(n+log n)+n+hw)).
	
	
	
Finder

	A* -algoritmin toimintalogiikka ja toteutus, joka käyttää hyväkseen tarjolla olevia tietorakenteita.
	Finderin vastuulla on myös päivittää noodin arvot (käyttämällä Node:n tarjoamia metodeja) tilanteen mukaan
	sekä huolehtia siitä, onko noodi löydetty tai onko siinä jo vierailtu (käyttämällä Heap:n tarjoamia metodeja).
	Lopuksi Finder tulostaa reitin maalinoodista lähtien käymällä läpi jokaisen noodin kohdalla mistä noodista
	siihen on tultu.
	
	public void findPath(Map, Start, Goal)				Aika
	1	initializeStart(start,goal)					1	1
	2	while accessed not empty					2	0...n
	3		handleCurrentNode()						3	n + log n
	4		if current is goal						4	1	
	5			break;								5	1
	6		for i= -1 to 1							6	1
	7			for i=-1 to 1						7	1
	8		markNeighbour(m,goal,i,j);}				8	~2n + log n
	9		printPath(goal);						9	n
	10		m.printField();							10	O(hw)

	findPath pitää käytännössä sisällään jokaisen yksittäisen reitinhaun annetusta kartasta, joten aikavaativuuden
	hahmottaminen on hyvin hankalaa johtuen kekojen mahdollisista sisällöistä. Se lienee kuitenkin luokkaa 
	O(n^(2(n+log n)+n+hw)).
	
	
	public handleCurrentNode()							Aika
	1	current = accessed.getHighest();			1	1
	2	accessed.removeNode();						2	log n
	3	checked.insertNode(current)					3	n + log n
	4	current.setValue('-')						4	1
	
	Funktiokutsut voivat pahimmillaan aiheuttaa n + log n aikavaativuuden. Koska noodit eivät voi olla sekä accessed
	ja checked -keoissa samanaikaisesti ei aiheudu 2(log n).
	
	
	public markNeighbour(Map, Goal, y, x)																	Aika
	1	try neighbor = m.field[current.y+y][current.x+x]												1	1
	2		if neighbor.value is X																		2	1
	3			checked.insertNode(neighbor)															3	n + log n
	4		if !checked.hasNode(neighbor)																4	n
	5			if !accessed.hasNode(neighbor)															5	n
	6				setNodeVariables(neighbor,goal.getY(),goal.getX())									6	1
	7				accessed.insertNode(neighbor)														7	n + log n
	8			if accessed.hasNode(neighbor) & neighbor > current.getToStart()+1+neighbor.getToGoal()	8	n
	9				accessed.updateNode(neighbor,current.getToStart()+1)								9	n + log n
    10   	catch(Exception e)																			10	1
	
	Pahimmassa tapauksessa noodia ei ole vielä tavattu, jolloin sekä checked ja accessed käydään läpi ja lisätään 
	uusi noodi kekoon. Tällöin aikavaativuus olisi luokkaa 2n + log n. Analyysia vaikeuttaa se, ettei molemmissa
	keoissa voi olla kaikkia noodeja ja on hyvin vaikea arvioida miten ne pahimmassa tapauksessa jakautuvat.
	
	
	public printPath(Node)					Aika
	1	while(true){					1	n
	2		Print Node					2	1
	3		Node.value is P				3	1
	4		if Node.previous is Node	4 	1
	5			break					5	1
	6		Node = Node.previous		6	1
	7	Print(empty)					7	1
	
	Aikavaativuus ei ole yleensä ole riippuvainen syötteen koosta, mutta pahin tapaus on sellainen, jossa paras
	reitti on kaikkien noodien kautta. Tällöin aikavaativuus on O(n).
	
	
Heap

	Minimikeko, jonka avulla vieraillut solmut pidetään automaattisesti järjestyksessa niin, ettei
	algoritmin itsensä tarvitse tietää jonosta mitään. Minimikeko on toteutettu Patrik Floréenin sekä Aalto
	Ylipiston Teknillisen korkeakoulun materiaaleista löytyvien pseudokoodien pohjalta.
	Keko ylläpitää järjestyksen eheyttä jokaisen operaation kohdalla (lisäys ja poisto) ja tarjoaa metodeja
	tarpeellisiin kyselyihin (onko tyhjä, koko, sisältääkö tietyn noodin).
	
	Aikavaativuusanalyysi

	sortHeap(A,i)									Aika
	1	l = left(i)								1	1
	2	r = right(i)							2	1
	3	if r ≤ A.heap-size						3	1
	4		if A[l] > A[r] largest = l			4	1
	5		else largest = r					5	1
	6		if A[i] < A[largest]				6	1
	7 			switch A[i] and A[largest]		7	1
	8 			sortHeap(A,largest)				8	0...h
	9	elsif l == A.heap-size and A[i]<A[l]	9	1
	10 	switch A[i] ja A[l]						10	1
	
	Rekursiivisia kutsuja korkeintaan keon korkeuden verran. Keon ollessa binääripuu sen korkeus on log n, jossa n
	on alkioiden määrä. Aikavaativuus on siis O(log n).
	Pseudokoodissa käytetty soveltuilta osin Floréenin materiaalia.
	
	
	increaseHeapSize								Aika
	1	newtable = 2*oldtable					1	1
	2	for i=1	to heap-size					2	n
	3		newtable[i] = oldtable[i]			3	n-1
	4	return newtable
	
	Keon täyttyessä (taulukko, jossa Nodet pidetään) luodaan kaksinkertainen taulukko, johon "kopioidaan" vanhan
	taulun sisältö. For-loop käydään läpi kerran jokaista alkiota kohden, joten aikavaativuus on O(n)
	
	
	insertNode(A,k)										Aika
	1	if heap!=empty								1
	2 		A.heap-size = A.heap-size+1				2	1
	3 		i = A.heap-size							3	1
	4		while i>1 and A[parent(i)] < k			4	1...h
	5 			A[i] = A[parent(i)]					5	0...h
	6 			i = parent(i)						6	0...h
	7 		A[i] = k								7	1
	8	else										8	1
	9		increaseHeapSize						9	n
	10		insertNode								10	1...h
	
	Noodi syötetään keon pohjalle ja pahimmassa tapauksessa se joudutaan viemään keon juureen, jolloin sitä on
	kuljetettu keon korkeuden verran. Mikäli kekoa ei tarvitse kasvattaa on aikavaativuus siis O(log n). Mikäli
	keko on täynnä joudutaan kutsumaan increaseHeapSize -metodia, jolloin aikavaativuus on O(n + log n) edellisiin
	laskelmiin pohjautuen.
	Pseudokoodissa käytetty soveltuilta osin Floréenin materiaalia.
	
	
	removeNode()								Aika
	1	if size>0							1	1
	2 		rootnode = lastnode				2	1
	3 		heap-size = heap-size-1			3	1
	4 		sortHeap(rootnode)				4	1...h

	
	Pahimmassa tapauksessa sortHeap käy läpi koko keon korkeussuunnassa, joten aiempaan analyysiin nojaten
	aikavaativuus on O(log n)
	
	
	hasNode(Node)							Aika
	1	for i=1 to heap-size			1	n
	2 		if heap[i] is Node			2	1
	3			return true				3	1
	4	return false					4	1
	
	Pahimmillaan käydään läpi koko keko, jolloin aikavaativuus on O(n).
	
	
	updateNode(Node, i)								Aika
	1	Node.setToStart(i)						1	1
    2	for i=1 to heap.size					2	n
    3		if heap[i] is Node 					3	1
    4		while i>1 and Node[parent] > Node	4	0...h
    5			tmp = Node						5	1
    6			Heap[Node] = parent				6	1
    7			heapt[parent] = tmp				7	1
    8			i=i/2							8	1
	9		break								9	1
	
	Pahimmassa tapauksessa käydään läpi koko keko ja nostetaan se keon juureksi. Aikavaativuus tällöin O(n+log n).
	
	
	Viitteet:
	
	*Tietorakenteet -kurssimateriaali,Helsingin yliopisto., 
	 Tietojenkäsittelytieteen laitos: http://www.cs.helsinki.fi/u/floreen/tira2012/tira.pdf
	
	*TKK - http://www.cse.hut.fi/en/research/SVG/TRAKLA2/tutorials/kekotutoriaali/

	
	
Map

	Tietosisällöltään kartan rungon mahdollistava noodimatriisi. Map saa App:lta parametreinä kartan ulottuvuudet
	luonnin yhteydessä, jonka jälkeen insertRow -metodia käyttäen App täydentää noodimatriisia karttaa vastaavaksi
	rivi kerrallaan. insertRow tutkii parametrina saadun String -olion merkki kerrallaan ja luo merkkiä vastaavan
	noodin matriisiin. Noodin mahdollinen kustannus annetaan jo tässä vaiheessa.
	Map tarjoaa myös metodin kartan tulostamiseksi.
	
	Aikavaativuusanalyysi
	
	public void insertRow(String)						Aika
	1	for i=0 to String.length					1	w	(map.width)
	2		create Node(params)						2	1
	3		if Node.value is S						3	1
	4			start = Node						4	1
	5		if Node.value is G						5	1
	6			goal = Node							6	1
	7		field[nr][i] = Node						7	1
	8		if Node.value is Digit and not 0		8	1
	9			Node.cost is Node.value.asDigit		9	1
    10	nr++										10	1

	For-looppia käydään syöteen pituuden verran, joten aikavaativuus on O(w).
	
	
	public void printField()			Aika
	1	for i=0 to map.height		1	h
	2		for j=0 to map.width	2	w
	3			Print map[i][j]		3	1
	4		Print empty row			4	1
	
	Aikavaativuus kasvaa suhteessa rivien ja sarakkeiden määrään. O(hw).
	
	
	
Node

	Noodin tarjoava luokka. Mahdollistaa noodin tietosisällön ylläpidon ja tarkastelemisen. A*:n löytämä reitti
	saadaan lopuksi kyselemällä maalinoodista lähtien edellisiä noodeja. 
	
	Aikavaativuusanalysi
	
	Nodessa ei ole ollenkaan looppeja, jolloin kaikki operaatiot ovat aikavaativuudeltaan O(1)
	
	
Puutteet ja parannusehdotukset

	Testausta voisi monimuotoistaa ja laajentaa kattavammaksi.
	Koodi tuskin on suorituskyvyn kannalta optimaalisin mahdollinen.