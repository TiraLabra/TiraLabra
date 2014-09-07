#Tiedon Tiivistäminen

##Toiminta
Pakkaaja käy läpi annetun tekstin ja laskee merkkien esiintymistiheyden. Merkeistä tehdään solmuja,
jotta niistä voidaan tehdä puu. Solmuista tehdään minimikeko, josta otetaan kaksi merkkiä kerrallaan ja
merkeistä tehdään uusi solmu, jonka esiintymistiheys on solmujen esiintymistiheyksien summa. Uusi
solmu on solmujen vanhempi binääripuussa, joka solmuista tehdään.
Alkuperäiset solmut poistetaan keosta. Uusi solmu lisätään kekoon. Kun keossa on jäljellä vain yksi solmu,
on se binääripuun juuri. Nyt saadussa binääripuussa juurta lähimpänä olevat lehdet esiintyvät alkuperäisessä 
tekstissä useiten. Jokainen merkki saa oman binäärilukunsa paikkansa suhteen puussa.
Kun merkki etsitään puusta rekursiivisesti, lähdetään liikkeelle juuresta. Vasemmalle mentäessä tulee binäärilukuun
numero nolla ja oikealle mentäessä numero yksi. Kun vastaan tulee solmu, jolla ei ole lapsia,
on se merkki. Eli jos merkki on löydetty menemällä juuresta ensin vasemmalle sitten oikealle, on sen 
binääriluku 01.

Purkamiseen tarvitsee puun, josta pakkaus on generoitu. Puuta mennään numeroiden mukaan alaspäin,
kunnes kohdataan solmu, jolla ei ole lapsia.

##Suorituskyky

##Puutteet ja parannusehdotukset

Ohjelma toimii nyt ascii-merkistöllä eikä siis esimerkiksi laajennetulla ascii-merkistöllä.
ääkköset tuottavat siis ongelmia. Ongelman varmasti pystyy helposti ratkaisemaan charsettiä 
vaihtamalla, mutta täysin triviaalia se ei itselleni ole. Merkkien tallentamiseen käytetty taulukko on 
kuitenkin jo valmiiksi 256 indeksiä pitkä.

Myös ohjelman rakennetta ja metodien paikkaa luokissa voisi muokata hyvinkin paljon.
Rakenne muuten on suhteellisen hyvä.

Huomattavaa on, että omaa kekoa ei ole keretty käyttämään ohjelmassa!

##Lähteet