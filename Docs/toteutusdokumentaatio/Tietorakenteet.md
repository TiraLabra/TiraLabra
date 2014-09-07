##Prioriteettikeko

Prioriteettikeon aikavaativuusanalyysi:
```java
public void lisaa(E lisattava) {
        koko++;	                                                //vakioaikainen, suoritetaan kerran
        int i = koko - 1;					//vakioaikainen, suoritetaan kerran
        taulukko[i] = lisattava;				//vakioaikainen, suoritetaan kerran
        while (i > 0 && (suurempi(i, vanhempi(i)) == i)) {	//suoritetaan enimmillään puun korkeuden verran eli log(n)
                swap(i, vanhempi(i));				//vakioaikainen
                i = vanhempi(i);				//vakioaikainen
        }									
        taulukko[i] = lisattava;				//vakioaikainen, suoritetaan kerran
}
```

Kekoon lisäämisen aikavaativuus on siis O(log(n))
```java
public E seuraava() {
        if (koko == 0) {					//vakioaikainen, suoritetaan kerran
            return null;					//vakioaikainen, suoritetaan kerran
        }
        E max = (E) (taulukko[0]);				//vakioaikainen, suoritetaan kerran
        taulukko[0] = taulukko[koko - 1];			//vakioaikainen, suoritetaan kerran
        koko--;							//vakioaikainen, suoritetaan kerran
        heapify(0);						//O(log(n)), suoritetaan kerran
        return max;						//vakioaikainen, suoritetaan kerran
}
```


Keosta seuraavan alkion poistamisen aikavaativuus on siis O(log(n))

```java
private void heapify(int i) {
        int right = oikeaLapsi(i);				//vakioaikainen, suoritetaan kerran
        int left = vasenLapsi(i);				//vakioaikainen, suoritetaan kerran
        if (right <= koko) {					//vakioaikainen, suoritetaan kerran
                int largest = suurempi(left, right);		//vakioaikainen, suoritetaan kerran
                if (suurempi(largest, i) == largest) {		//vakioaikainen, suoritetaan kerran
                        swap(i, largest);			//vakioaikainen, suoritetaan kerran
                        heapify(largest);			//rekursiivinen kutsu tähän metodiin
                }
        } else if (left == koko && (suurempi(left, i) == left)){//vakioaikainen, suoritetaan kerran
                swap(i, left);					//vakioaikainen, suoritetaan kerran
        }
}
```

Heapify-metodi on muilta osin vakioaikainen mutta se kutsuu itseään. Rekursiivisia metodikutsuja tulee lineaarinen määrä binaaripuun korkeuteen nähden joten metodin aikavaativuus on O(log(n)).

##Jono

Jonoon lisättäessä taulukko tulee joskus täyteen ja sitä täytyy kasvattaa. Tarkastellaan taulukon kasvattamisen aikavaativuutta:

```java
private void kasvataTaulukkoa() {
        //tehdään uusi taulukko joka on kaksi kertaa vanhan kokoinen, alussa head ja tail 0
        Object[] uusiTaulukko = new Object[taulukonKoko*2];             //O(n), suoritetaan kerran
        int uusiHead = 0;                                               //vakioaikainen, suoritetaan kerran
        int uusiTail = 0;                                               //vakioaikainen, suoritetaan kerran
        //niin kauan kuin vanhassa taulukossa riittää tavaraa (ei ole tyhjä), otetaan sieltä tavaraa pois ja siirretään uuteen
        while (!tyhja()) {                                              //suoritetaan n kertaa
            uusiTaulukko[uusiTail] = taulukko[head];                    //vakioaikainen
            uusiTail++;                                                 //vakioaikainen
            head = seuraavaPaikka(head);                                //vakioaikainen
        }
        //lopuksi korvataan vanhat head, tail, taulukko ja taulukonKoko uusilla
        head = uusiHead;                                                //vakioaikainen
        tail = uusiTail;                                                //vakioaikainen
        taulukko=uusiTaulukko;                                          //vakioaikainen
        taulukonKoko = taulukonKoko*2;                                  //vakioaikainen
    }
```

Taulukon kasvattamisen aikavaativuus on siis O(n).

Jonoon lisäämisen aikavaativuus:

```java
public void lisaa(E lisattava) {
        if (taysi()) {                                  //vakioaikainen, suoritetaan kerran
                kasvataTaulukkoa();                     //O(n), suoritetaan harvoin
        }
        taulukko[tail] = lisattava;                     //vakioaikainen, suoritetaan kerran
        tail = seuraavaPaikka(tail);                    //vakioaikainen, suoritetaan kerran
}
```
    
Jonoon lisäämisen aikavaativuus on siis yleensä vakioaikainen, mutta joskus harvoin O(n). (nk. "Tasoitetulla analyysillä" voitaisiin selvittää formaalisti tämän operaation aikavaativuus, mutta jossain sanottiin että se on syventävien kurssien asiaa joten uskon.)

Alkion ottaminen jonosta:
```java
    public E otaJonosta() {
        E palautus = (E) taulukko[head];                //vakioaikainen, suoritetaan kerran
        head = seuraavaPaikka(head);                    //vakioaikainen, suoritetaan kerran
        return palautus;                                //vakioaikainen, suoritetaan kerran
    }
```

Alkion ottaminen jonosta on selvästi vakioaikainen operaatio.
##Lista

Samaan tapaan kuin jonossa, tarkastellaan ensin listassa taulukon koon kasvattamisen aikavaativuutta:
```java
    private void kasvataTaulukkoa() {
        Object[] uusiTaulukko = new Object[taulukonKoko * 2];           //O(n), suoritetaan kerran
        System.arraycopy(taulukko, 0, uusiTaulukko, 0, taulukonKoko);   //O(n), suoritetaan kerran
        this.taulukko = uusiTaulukko;                                   //vakioaikainen, suoritetaan kerran
        this.taulukonKoko = taulukonKoko * 2;                           //vakioaikainen, suoritetaan kerran
    }
```
Taulukon koon kasvattaminen on siis selvästi lineaarisen (O(n)) ajan vievä operaatio.

Tarkastellaan nyt Listaan lisäämisen aikavaativuutta:
```java
    public void add(E lisattava) {
        if (this.koko == this.taulukonKoko) {                           //vakioaikainen, suoritetaan kerran
            kasvataTaulukkoa();                                         //O(n), suoritetaan harvoin
        }
        this.taulukko[koko] = lisattava;                                //vakioaikainen, suoritetaan kerran
        koko++;                                                         //vakioaikainen, suoritetaan kerran
    }
```

Kuten Jonon tapauksessa, myös listan tapauksessa listaan lisääminen on yleensä vakioaikainen operaatio, mutta joskus harvoin vie aikaa O(n). 

Listasta poistaminen indeksin perusteella:

```java
    public void remove(int i) {
        //siirretään taulukon loppu-osaa yhdellä vasemmalle
        int siirrettavia = koko - i - 1;                                        //vakioaikainen, suoritetaan kerran
        if (siirrettavia > 0) {                                                 //vakioaikainen, suoritetaan kerran
            System.arraycopy(taulukko, i + 1, taulukko, i, siirrettavia);       //O(n), suoritetaan kerran
        }
        koko--;                                                                 //vakioaikainen, suoritetaan kerran
        taulukko[koko] = null; //roskienkerääjä kerää poistetun pois            //vakioaikainen, suoritetaan kerran
    }
```

Listasta poistamisen aikavaativuus on selvästi O(n).

Contains-metodi, eli tarkistus sille sisältääkö lista annettua alkiota:

```java
    public boolean contains(Object o) {
        E otus;                                         //vakioaikainen, suoritetaan kerran
        try {  
            otus = (E) o;                               //vakioaikainen, suoritetaan kerran
        } catch (Exception e) {
            return false;
        }
        for (int i = 0; i < koko; i++) {                //suoritetaan n kertaa
            if (((E) taulukko[i]).equals(otus)) {       //vakioaikainen
                return true;                            //vakioaikainen
            }
        }
        return false;                                   //vakioaikainen
    }
```
Tarkistuksessa käydään pahimmassa tapauksessa kaikki listan alkiot läpi, jolloin operaation aikavaativuus on O(n)

