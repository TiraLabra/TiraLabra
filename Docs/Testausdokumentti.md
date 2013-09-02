Yksikkötestaus
==============

Ohjelma on yksikkötestattu CuTest-nimisellä kirjastolla. Funktioita on testattu pääasiassa <http://orlingrabbe.com/des.htm> -sivulta kopioiduilla esimerkkisyötteillä. Lisäksi toteutin [testin](http://people.csail.mit.edu/rivest/pubs/Riv85.txt), joka löytäisi ainakin 36 568 mahdollista yksittäistä virhettä. Myös kokonaisen tiedoston salaaminen ja avaaminen on testattu automaattisesti.

Yksikkötestit voi ajaa SalausSoppa-kansiossa komennolla `make test`.

Suorituskykytestaus
===================

Koska DES toimii lähes samalla tavalla sekä salatessa että purkaessa, tulisi molempien operaatioiden suorituskyvyn olla suunnilleen sama. Testasin suorituskykyä salaamalla sekä purkamalla satunnaisgeneroituja tiedostoja, joiden koko oli 1-10 megatavua. Salaamiseen ja purkamiseen kulunut aika oli aina suunnilleen sama, joten niitä ei ole taulukossa eritelty. Aika mitattiin time-komennolla.

<table>
	<thead>
		<th>Koko</th>
		<th>Aika</th>
		<th>Nopeus</th>
		<th>Salausmoodi</th>
	</thead>
	<tbody>
		<tr>
			<td>1M</td>
			<td>0,4 s</td>
			<td>2,5 MB/s</td>
			<td>ECB</td>
		</tr>
		<tr>
			<td>1M</td>
			<td>0,4 s</td>
			<td>2,5 MB/s</td>
			<td>CBC</td>
		</tr>
		<tr>
			<td>2M</td>
			<td>0,8 s</td>
			<td>2,5 MB/s</td>
			<td>ECB</td>
		</tr>
		<tr>
			<td>2M</td>
			<td>0,8 s</td>
			<td>2,5 MB/s</td>
			<td>CBC</td>
		</tr>
		<tr>
			<td>5M</td>
			<td>2,0 s</td>
			<td>2,5 MB/s</td>
			<td>ECB</td>
		</tr>
		<tr>
			<td>5M</td>
			<td>2,0 s</td>
			<td>2,5 MB/s</td>
			<td>CBC</td>
		</tr>
		<tr>
			<td>10M</td>
			<td>4,1 s</td>
			<td>2,4 MB/s</td>
			<td>ECB</td>
		</tr>
		<tr>
			<td>10M</td>
			<td>4,1 s</td>
			<td>2,4 MB/s</td>
			<td>CBC</td>
		</tr>

	</tbody>
</table>

Suorituskykyyn vaikuttanee hiukan ainakin permutaatioiden toteutus, joka voisi olla tehokkaampi. Nykyisellään jokainen bitti siirretään ensin uint_64:n alkuun, josta se siirretään paikalleen. Pienellä muutoksella voisi bitin siirtää helposti suoraan oikealle paikalleen. Tämän vaikutus lienee kuitenkin vähäinen.
