#Käyttöohje

*Tämä ohje on kirjoitettu toistamatta paljoa asioita joten suosittelen sen
lukemista alusta loppuun.*

##Ohjelman suoritus

Ohjelman suoritus tapahtuu komentotulkissa seuraavalla käskyllä:  
`java -jar Tiralabra-1.4.jar <argumentti>`  
Jar-tiedosto löytynee projektin juurikansiosta.

###Käynnistysargumentit:

|Argumentti|Selitys|
|-------|----------------------------------------------------------------------|
|`al`	|Käynnistää aritmeettisten kaavojen tulkin ja laskimen.				   |
|`kal`	|Käynnistää aritmeettisten kaavojen laskimen.						   |
|`sl`	|Käynnistää säännöllisten lausekkeiden tulkin ja käsittelijän.		   |
|`ksl`	|Käynnistää säännöllisten lausekkeiden käsittelijän.				   |
|`hkp`	|Käynnistää luokan Hajautuskartta suorituskykyä toiseen luokkaan  |
||vertaavan aliohjelman.|
|`jp`	|Käynnistää luokan Jono suorituskykyä toiseen luokkaan  |
||vertaavan aliohjelman.|
|`pp`	|Käynnistää luokan Pino suorituskykyä toiseen luokkaan  |
||vertaavan aliohjelman.|

* * *

##Aritmeettisten kaavojen tulkki

Aritmeettisten kaavojen tulkki pyytää käyttäjältä kaavaa joka voi koostua
kohtuullisen kokoisista (kymmenkantaisista) luonnollisista luvuista,
sulkumerkeistä `(` ja `)` sekä laskutoimitusten operaattoreista `+`, `-`, `*`,
`/`, `%` sekä `^`. Tulkki jättää välilyönnit käsittelemättä. Kaava annetaan
tulkille syötteenä näppäimistöltä ja painamalla enteriä.

Tulkin käsittelemät syötteet annetaan infiksinotaatiolla, jossa Kaava tulee
antaa muodossa *A* o *B*, missä *A* ja *B* ovat (ali-) kaavoja ja o operaattori.
Esimerkki: kaava `(1+2)*3`. Atomikaavoissa molemmat kaavat *A* ja *B* koostuvat
ainoastaan yhdestä luonnollisesta luvusta jota merkitään numeroin 0-9.
Esimerkki: kaava `1+2`. 

###Aritmeettiset operaattorit:

|Operaattori|Selitys	|
|---|-------------------|
|`+`|Yhteenlasku		|
|`-`|Vähennyslasku		|
|`*`|Kertolasku			|
|`/`|Jakolasku			|
|`%`|Jakojäännöslasku	|
|`^`|Potenssilasku\*	|

\* = Potenssilaskua koskee rajoitus jonka mukaan ainoastaan atomikaavat ovat
	sallittuja. Varsinaisesti kyseessä on lyhennysmerkintä kertolaskuille.

Lopuksi vielä esimerkki pidemmästä kaavasta:
`((43 - 7) * 2^2 / 3 + 325) % 6`
Tämän kaavan lukuarvo on 1.

Ohjelman suoritus loppuu kun käyttäjä antaa syötteen `lopeta`.

* * *

##Aritmeettisten kaavojen laskin

Kun aritmeettisten kaavojen tulkki on muuttanut käyttäjän antaman kaavan
käänteiseen puolalaiseen notaatioon (käynnistysargumentti `al`), annetaan tämä
kaava automaattisesti laskimen käsiteltäväksi. Laskin ilmoittaa kaavan
lukuarvon.

Vaihtoehtoisesti (käynnistysargumentilla `kal`) voidaan käynnistää pelkkä
laskin, joka käsittelee käänteisellä puolalaisella notaatiolla (RPN) annettuja
kaavoja. Tällaiset kaavat ovat kuten aiemmin esitellyt infiksikaavat mutta
niiden kieleen ei kuulu sulkumerkit ja kaavat ovat muotoa *A B* o. On syytä
huomata että RPN-kaavassa kokonaisluvut tulee erottaa toisistaan välilyönneillä.

Ohjelman suoritus loppuu kun käyttäjä antaa syötteen `lopeta`.

* * *

##Säännöllisten lausekkeiden tulkki

Säännöllisten lausekkeiden käsittely ohjelmassa tapahtuu ensin lausekkeen
syöttämisellä tulkille (tai suoraan käsittelijälle). Tämän jälkeen siirrytään
vertailemaan käyttäjän antamia merkkijonoja säännöllisen lausekkeen kanssa.
Ohjelma ilmoittaa mikäli säännöllinen lauseke täsmää annetun merkkijonon kanssa.
Myös merkkijonojen ja säännöllisen lausekkeen vertailutilasta poistutaan
syötteellä "lopeta". Seuraavaksi käsittelen ohjelman säännöllisten lausekkeiden
syntaksia.

Säännöllisten lausekkeiden tulkin ymmärtämät säännölliset lausekkeet koostuvat
sulkumerkeistä (ominaisuus ei toimi oikein tällä hetkellä), seuraavaksi
lueteltavista operaattoreista sekä muista unicode-merkeistä jotka tulkitaan
operandeina. Lisäksi merkillä `'\u03b5'` (kreikkalaisen aakkoston kirjain
epsilon) on oman erityismerkityksensä tyhjän merkin (`'\0'`) symbolina.

###Operaattorit ja lyhenteet:

|Operaattori|Selitys														   |
|-----------|------------------------------------------------------------------|
|`.`		|Konkatenointi													   |
|&#124;		|Haarautuminen\*												   |
|`?`		|Nolla tai yksi	toistoa											   |
|`*`		|Nolla tai useampi toistoa										   |
|`+`		|Yksi tai useampi toistoa										   |
|`\<merkki>`|Literaali. Seuraava merkki tulkitaan operandina. (Esimerkiksi  |
||kaava `a\+` merkitsee kieltä joka sisältää merkkijonon `a+` eikä silmukkaa.) |												   |
|`[a0-am]{n}`|Mikä tahansa merkki väliltä `a0 ... am`. Tulkataan kaavaksi  |
||`"a1 ... am` &#124; toistettuna n kertaa. Jos parametri `{n}` puuttuu,|
||suoritetaan yksi toisto. \*\*|
|`[a0a1...am]`|Jokin vaihtoehdoista `a0, a1 ... am`. Tulkataan kaavaksi  |
||`a0, a1 ... am` &#124;. \*\*|


\* = On suositeltavaa käyttää lyhennysmerkintää `[...]` eri vaihtoehtojen
	luetteluun sillä tulkki käsittelee kaikkia operaattoreja binäärisinä eikä
	siksi osaa tulkita yli kahden operandin haarautumisia oikein operaattorilla
	`|`. Lisäksi on syytä huomata että RPN-regex `a | b | c |` merkitsee samaa
	kuin `a . b . c .` (vrt. `a b c |`). Tämä ei ole bugi, sillä kyseessä on
	kolme peräkkäistä tilaa joista jokaiseen voidaan siirtyä vain yhdellä
	vaihtoehdolla (ensin `a`, sitten `b`, lopuksi `c`).  
\*\*=	Myös `[...]*` on mahdollinen. Tällöin kuitenkin tulkataan ensin lyhenne
	`[...]`, jonka jälkeen lisätään tavallinen `*`-operaattori. Tämän tyyppiset
	kaavat eivät kuitenkaan toimi oikein tällä hetkellä.

Esimerkkejä säännöllisten lausekkeiden tulkin tunnistamista kielistä:  
`a`, `a*`, `[a-k]{3}`, `a|b`, `7|k.c`

Ohjelman suoritus loppuu kun käyttäjä antaa syötteen `lopeta`. (Säännöllistä 
kieltä `lopeta` pääsee tutkimaan esimerkiksi lausekkeella `\lopeta.`. Ohjelmassa
on kuitenkin bugi jonka johdosta mikään merkkijono ei täsmää sen kanssa.)

* * *

##Säännöllisten lausekkeiden käsittelijä

On syytä huomata, että edellisistä syötettä käsittelevistä ohjelman palveluista
poiketen säännöllisten lausekkeiden käsittelijä ei tulkitse operaattoreja
binäärisinä vaan n-paikkaisina missä n on positiivinen kokonaisluku. Laskimen
tavoin säännöllisten lausekkeiden käsittelijän käyttämässä notaatiossa operandit
luetellaan välilyönnein eroteltuina ennen operaattoria.

Tässä yhteydessä operandeiksi lasketaan unicode-merkkien lisäksi myös niistä
muodostetut merkkijonot (jotka konkatenoidaan implisiittisesti). Esimerkiksi
kaavan `ab c |` kanssa täsmäävät (vain) merkkijonot `ab` ja `c`, ei esimerkiksi
pelkkä `a`. Joissain tapauksissa konkatenointi pitää eksplisiittisesti merkitä 
operaattorilla `.`. Jos säännöllisessä lausekkeessa ei ole muita operaattoreita,
on loppuun syytä lisätä konkatenointia merkitsevä operaattori. Esimerkiksi
säännöllisen lausekkeen `sfg` kanssa ei täsmää mikään merkkijono, mutta 
lausekkeen `sfg .` kanssa täsmää merkkijono `sfg`. Aiemmin mainitun
esimerkkikaavan `ab c |` kohdalla on kuitenkin muistettava että kaavalla
`a b . c |` on erilainen semantiikka sillä sen kanssa täsmää vain merkkijono
`abc`. Tämä asia on selitetty säännöllisten lausekkeiden tulkkia käsittelevän
kappaleen operaattorien listauksen yhteydessä.

RPN-muotoisia säännöllisiä lausekkeita syötettäessä on huomioitava välilyöntien
merkitys erottimena. Aikaisemmin tulkin yhteydedssä mainittu esimerkki
`\lopeta.` tulee esittää säännöllisten lausekkeiden käsittelijälle muodossa
`\l opeta .`. Literaalina välilyöntiä ` ` ei voi kuitenkaan syöttää suoraan
käsittelijälle vaan tällöin on käytettävä tulkkia. Esimerkiksi `H 7` ja `H\ 7`
tulkataan molemmat samalla tavalla siten, että ensimmäinen tarkasteltava merkki
on iso H-kirjain, seuraava välilyönti ja viimeinen merkki numero 7.

Säännöllisen lausekkeen käsittely loppuu kun käyttäjä antaa syötteen `lopeta`.
Tällöin palataan takaisin uuden säännöllisen lausekkeen syöttöön.
