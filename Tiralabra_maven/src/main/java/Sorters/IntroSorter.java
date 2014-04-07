package Sorters;

/**
 *
 * @author nkostiai
 * 
 * Introsort on hybridi-järjestämisealgoritmi, joka käyttää hyväkseen sekä quicksortia, että heapsortia.
 * Introsort aloitetaan quicksortilla ja rekursiosyvyyden noustessa tarpeeksi korkealle algoritmi vaihtaa heapsorttiin.
 * Algoritmi implementoi myös insertionsortin, jota käytetään taulukon ollessa tarpeeksi pieni.
 * 
 */
public class IntroSorter {
    
    /**
     * Taulukon minimikoko. Taulukon ollessa pienempi järjestäminen hoidetaan insertion sortilla.
     */
    private final int sizeThreshold = 16;
    
    
    /**
     * Introsortin aloitusmetodi. Metodi laskee taulukon pituuden perusteella quicksortin rekursiosyvyyden.
     * @param arrayToSort Taulukko joka järjestetään.
     */
    public void introSort(int[] arrayToSort) {
        int depthLimit = (int) Math.floor((Math.log(arrayToSort.length) / Math.log(2)));
        intro_QuickSort(arrayToSort, 0, arrayToSort.length, depthLimit);
    }
    
    
    /**
     * Quicksortin päämetodi. Järjestää taulukkoa quicksortilla niin kauan kunnes rekursiosyvyys menee yli määritellyn rajan. Tämän jälkeen loput taulukosta järjestetään lisäysjärjestämisellä. 
     * @param arrayToSort Taulukko joka järjestetään.
     * @param first Järjestämisen aloitusindeksi.
     * @param last Järjestämisen lopetusindeksi.
     * @param depthLimit Rekursion maksimisyvyys.
     */
    private void intro_QuickSort(int[] arrayToSort, int first, int last, int depthLimit) {
        int median;
        while (last - first > sizeThreshold) {
            if (depthLimit == 0) {
                intro_HeapSort(arrayToSort, first, last);
                return;
            }
            depthLimit--;
            median = medianOfThree(arrayToSort, first, first + ((last - first) / 2) + 1, last - 1);
            int pivot = partition(arrayToSort, first, last, median);
            intro_QuickSort(arrayToSort, pivot, last, depthLimit);
            last = pivot;
        }
        intro_InsertionSort(arrayToSort, first, last);
    }
    
    /**
     * Quicksortin pivotin määrittelymetodi.
     * Jakaa taulukon alkiot niin, että mediaania pienemmät alkiot ovat järjestettävän alueen toisella puolella ja suuremmat toisella puolella.
     * @param array Taulukko, jota järjestetään.
     * @param first Järjestettävän osuuden ensimmäinen alkio.
     * @param last Järjestettävän osuuden viimeinen alkio.
     * @param median Taulukon kolmesta luvusta laskettu mediaani.
     * @return Käytettävän pivotin indeksi.
     */
    private int partition(int[] array, int first, int last, int median) {
        int i, j;
        i = first;
        j = last;
        while (true) {
            while (array[i] < median) i++;
	    j=j-1;
	    while (median < array[j]) j=j-1;
	    if(!(i < j))
	      return i;
	    GlobalMethods.exchange(array,i,j);
	    i++;

        }
    }
    
    /**
     * Heapsortin päämetodi
     * @param array Järjestettävä taulukko.
     * @param first Järjestettävän osuuden ensimmäinen indeksi.
     * @param last Järjestettävän osuuden viimeinen indeksi.
     */
    public void intro_HeapSort(int[] array, int first, int last) {
        int gap = last - first;
        //build heap
        intro_buildHeap(array, gap, first);
        //sort the array
        for (int i = gap; i > 1; i = i - 1) {
            GlobalMethods.exchange(array, first, first + i - 1);
            heapify(array, 1, i - 1, first);
        }
    }
    
    /**
     * Heapsortin apumetodi, joka rakentaa olemassaolevasta taulukosta kekoehdon toteuttavan taulukon.
     * Metodi toimii kutsumalla toistuvasti Heapify -metodia jokaiselle tarvittavalle alkiolle.
     * @param array Taulukko josta keko rakennetaan.
     * @param last Järjestettävän osuuden viimeinen indeksi.
     * @param first Järjestettävän osuuden ensimmäinen indeksi.
     */
    private void intro_buildHeap(int[] array, int last, int first) {
        for (int i = last / 2; i >= 1; i = i - 1) {
            heapify(array, i, last, first);
        }
    }
    /**
     * Heapsortin heapify -metodi. Metodi järjestää taulukon alkiot niin, että alkioiden järjestys noudattaa kekoehtoa.
     * 
     * @param array Taulukko, jonka alkioita järjestetään.
     * @param index Taulukon kohta, josta lähdetään liikkeelle.
     * @param last Järjestettävän alueen viimeinen indeksi.
     * @param first Järjestettävän alueen ensimmäinen indeksi.
     */
    private void heapify(int[] array, int index, int last, int first) {
        int d = array[first + index - 1];
        int child;
        while (index <= last / 2) {
            child = 2 * index;
            if (child < last && array[first + child - 1] < array[first + child]) {
                child++;
            }
            if (d >= array[first + child - 1]) {
                break;
            }
            array[first + index - 1] = array[first + child - 1];
            index = child;
        }
        array[first + index - 1] = d;
    }

    /**
     * Quicksortin apuna käytettävän lisäysjärjestämismetodi.
     * 
     * @param arrayToSort Järjestettävä taulukko.
     * @param first Järjestettävän osuuden ensimmäinen alkio.
     * @param last Järjestettävän osuuden viimeinen alkio.
     */
    public void intro_InsertionSort(int[] arrayToSort, int first, int last) {
        int j, temp;
        for (int i = first; i < last; i++) {
            j = i;
            temp = arrayToSort[i];
            while (j != first && temp < arrayToSort[j - 1]) {
                arrayToSort[j] = arrayToSort[j - 1];
                j--;
            }
            arrayToSort[j] = temp;
        }
    }
    
    /**
     * Metodi joka laskee taulukon arvojen perusteella kolmen arvon mediaanin.
     * @param array Taulukko josta mediaani lasketaan.
     * @param first Ensimmäinen indeksi, josta mediaani lasketaan.
     * @param mid Keskimmäinen indeksi, josta mediaani lasketaan.
     * @param last Viimeinen indeksi, josta mediaani lasketaan.
     * @return Mediaani.
     */
    public int medianOfThree(int[] array, int first, int mid, int last) {
        if (((array[first] <= array[mid]) && array[last] >= array[mid]) || (array[first] >= array[mid]) && array[last] <= array[mid]) {
            return array[mid];
        } else if (((array[first] <= array[last]) && array[mid] >= array[last]) || (array[first] >= array[last]) && array[mid] <= array[last]) {
            return array[last];
        } else {
            return array[first];
        }
    }

}
