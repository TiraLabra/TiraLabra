##Toteutusdokumentaatio

###Ohjelman yleisrakenne
Ohjelman rakenne on mavenin määrittelemä projektirakenne. Kaikki työssä 
implementoidut hakupuut hyödyntävät Solmu-luokkaa, joten se on eritelty eri kansioon/pakettiin hakupuista.
Itse hakupuille on oma kansionsa/pakettinsa. Hakupuu toteutuksina on luokat 
BinaarinenHakupuu, AVLpuu ja SplayPuu. Lisäksi on HakupuuRajapinta, jonne on
määritelty puiden metodeja. SplayPuu ja AVLpuu laajentavat BinaarinenHakupuu-luokkaa.
Puihin voi säilöä Javan Object olioita, eli käytännössä ihan mitä vain dataa.

###Saavutetut aika- ja tilavaativuudet

###Suorituskyky ja O-analyysivertailu

###Puutteet ja parannusehdotukset
Perintä ei välttämättä ole paras mahdollinen tapa ratkaista ongelmaa. Toinen lähestysmistapa olisi ollut lähteä liikkeelle "yleishyödyllisistä metodeista" ja rakentaa kaikki puut implementoimaan suoraan rajapintaa ja
hyödyntämällä "yleishyödyllisiä metodeja" jonkin muun luokan kautta. Koodissa on myös toistoa, mitä tulisi karsia, jotta koodi olisi mahdollisimman yksinkertaista luettavaa ulkopuoliselle.

###Lähteet
* wikipedia.org
* Tietorakenteet ja algoritmit -kurssin materiaali
* https://noppa.oulu.fi/noppa/kurssi/811312a/luennot/811312A_opiskelumateriaali_-_perustietorakenteet.pdf
