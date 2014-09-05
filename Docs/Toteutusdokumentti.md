Ohjelman yleisrakenne

Ohjelmassa on pakkaukset tietorakenteet, algoritmi ja gui. Tietorakenteet-pakkaus sisältää toteutettuja tietorakenteita (tällä hetkellä prioriteettikeko ja jono), algoritmi sisältää neljä eri reittialgoritmia (Breadth first, Dijkstra, Greedy Best First ja A-star) ja niihin liittyviä luokkia ja Gui-pakkaus sisältää käyttöliittymäluokkia.

Prioriteettikeon aikavaativuusanalyysi:

public void lisaa(E lisattava) {
        koko++;							//vakioaikainen, suoritetaan kerran
        int i = koko - 1;					//vakioaikainen, suoritetaan kerran
        taulukko[i] = lisattava;				//vakioaikainen, suoritetaan kerran

        while (i > 0 && (suurempi(i, vanhempi(i)) == i)) {	//suoritetaan enimmillään puun korkeuden verran eli log(n)
            swap(i, vanhempi(i));				//vakioaikainen
            i = vanhempi(i);					//vakioaikainen
        }									
        taulukko[i] = lisattava;				//vakioaikainen, suoritetaan kerran
}

Kekoon lisäämisen aikavaativuus on siis O(log(n))

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

Keosta seuraavan alkion poistamisen aikavaativuus on siis O(log(n))

    private void heapify(int i) {

        int right = oikeaLapsi(i);				//vakioaikainen, suoritetaan kerran
        int left = vasenLapsi(i);				//vakioaikainen, suoritetaan kerran
        if (right <= koko) {					//vakioaikainen, suoritetaan kerran
            int largest = suurempi(left, right);		//vakioaikainen, suoritetaan kerran

            if (suurempi(largest, i) == largest) {		//vakioaikainen, suoritetaan kerran
                swap(i, largest);				//vakioaikainen, suoritetaan kerran
                heapify(largest);				//rekursiivinen kutsu tähän metodiin
            }

        } else if (left == koko && (suurempi(left, i) == left)){//vakioaikainen, suoritetaan kerran
            swap(i, left);					//vakioaikainen, suoritetaan kerran
        }
    }

Heapify-metodi on muilta osin vakioaikainen mutta se kutsuu itseään. Rekursiivisia metodikutsuja tulee lineaarinen määrä binaaripuun korkeuteen nähden joten metodin aikavaativuus on O(log(n)).

Jono

