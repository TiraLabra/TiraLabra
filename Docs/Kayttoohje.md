#Käyttöohje

## Oppimisdata

Oppimisdata sijoitetaan learning_data-kansioon tekstitiedostoihin. Ohjelma käy läpi
kaikki tämän kansion tiedostot. Tekstin kielen tulee olla tagattu seuraavalla
syntaksilla:

@ja日本語はまだまだ結構認識しやすいだろうな。@fiMutta eikö se mene helposti sekaisin kiinan
kielen kanssa?@enNonsense! They may look similar to you and some of the characters
are indeed the same, but they use a lot of completely different characters too!@svÄr
det så? Intressant!

@-merkin jälkeen tulee siis kaksimerkkinen kielikoodi (suositus: ISO-639) kielen
vaihdoskohdassa. Merkkiyhdistelmää @@ käytetään ilmaisemaan ät-merkkiä.

Ohjausmerkin @ on oltava ASCIIta tai UTF-8:aa (@-merkin tapauksessa ne ovat sama
asia). Suosittelemme käyttämään merkistökoodauksena UTF-8:aa.


## API
Tsekkaa godoc osoitteesta:

http://godoc.org/github.com/golddranks/TiraLabra/src
