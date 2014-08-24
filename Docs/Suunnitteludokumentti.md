#Tiedon Tiivistäminen

##Toiminta
Pakkaaja käy läpi annetun tekstin ja laskee merkkien esiintymistiheyden. Merkeistä tehdään solmuja,
jotta niistä voidaan tehdä puu. Solmuista tehdään minimikeko, josta otetaan kaksi merkkiä kerrallaan
merkeistä tehdään uusi solmu, jonka esiintymistiheys on solmujen esiintymistiheyksien summa. Uusi
solmu on solmujen vanhempi binääripuussa, joka solmuista tehdään.
Alkuperäiset solmut poitetaan keosta. Uusi solmu lisätään kekoon. Kun keossa on jäljellä vain yksi solmu,
on se binääripuun juuri. Nyt saadussa binääripuussa juurta lähimpänä olevat lehdet esiintyvät alkuperäisessä 
tekstissä useiten. Jokainen merkki saa oman binäärilukunsa paikkansa suhteen puussa.
Kun merkki etsitään puusta rekursiivisesti, lähdetään liikkeelle juuresta. Vasemmalle mentäessä tulee binäärilukuun
numero nolla ja oikealle mentäessä numero yksi. Kun vastaan tulee solmu, jolla ei ole lapsia,
on se merkki. Jos merkki on löydetty menemällä juuresta ensin vasemmalle sitten oikealle, on sen 
binääriluku 01.

Purkamiseen tarvitsee puun, josta pakkaus on generoitu. Puuta mennään numeroiden mukaan alaspäin,
kunnes kohdataan solmu, jolla ei ole lapsia.

##Suorituskyky

##Puutteet ja parannusehdotukset

##Lähteet