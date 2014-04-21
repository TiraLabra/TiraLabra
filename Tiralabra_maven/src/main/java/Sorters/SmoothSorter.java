package Sorters;

/**
 * 
 * Smoothsort on vertailujärjestämisealgoritmi joka käyttää aputietorakenteenaan kekoa.
 * Smoothsort on kekojärjestämisen variaatio, jolla saavutetaan parempi aikavaativuus lähes järjestetyillä taulukoilla perinteiseen kekojärjestämiseen verrattuna.
 * Smoothsortin periaatteena on muodostaa yhden keon sijasta useiden kekojen "metsä" käyttäen apuna leonardon sarjan numeroita. 
 * 
 * @author nkostiai
 */
public final class SmoothSorter {
    
    /**
     * Taulukko johon talletetaan ensimmäiset 40 numeroa Leonardon sarjasta. Numeroita käytetään määrittelemään osakekojen kokoja.
     */
    private final int[] leonardoNumbers;
    
    
    public SmoothSorter() {
        leonardoNumbers = formLeonardoSequence();
    }
    
    /**
     * Smoothsortin päämetodi.
     * @param arrayToSort Järjestettävä taulukko.
     */
    public void smoothSort(int[] arrayToSort) {
        if(arrayToSort.length > 1){
        int length = arrayToSort.length;
        int orderLength = countOrderLength(length);
        int[] orders = new int[orderLength];

        int trees = formLeonardoHeap(arrayToSort, orders, 0);

        breakDown(arrayToSort, orders, trees);
        }

    }
    
    /**
     * Muodostetaan taulukosta leonardo-heap.
     * @param arrayToSort Taulukko, jota järjestetään.
     * @param orders Taulukko johon talletetaan muodostuvien sisäisten kekojen koot.
     * @param trees Sisäisten kekojen määrä.
     * @return Muodostuneiden puiden määrä.
     */
    private int formLeonardoHeap(int[] arrayToSort, int[] orders, int trees) {
        for (int i = 0; i < arrayToSort.length; i++) {
            if (trees > 1 && orders[trees - 2] == orders[trees - 1] + 1) {
                trees--;
                orders[trees - 1]++;
            } else if (trees > 0 && orders[trees - 1] == 1) {
                orders[trees++] = 0;
            } else {
                orders[trees++] = 1;
            }
            findAndSift(arrayToSort, i, trees - 1, orders);
        }
        return trees;
    }
    
    /**
     * Suoritetaan lopullinen järjestäminen. Leonardo-heapista otetaan suurin elementti, asetetaan se taulukkoon ja pienennetään heapin kokoa.
     * Tämän jälkeen korjataan jäljelle jääneen heapin kekoehto.
     * @param arrayToSort Taulukko, jota järjestettään.
     * @param orders Taulukko, johon on talletettu sisäisten puiden koot.
     * @param trees Sisäisten puiden määrä.
     */
    private void breakDown(int[] arrayToSort, int[] orders, int trees) {
        for (int i = arrayToSort.length - 1; i > 0; i--) {
            if (orders[trees - 1] <= 1) {
                trees--;
            } else {
                int rightIndex = i - 1;
                int leftIndex = rightIndex - leonardoNumbers[orders[trees - 1] - 2];

                trees++;
                orders[trees - 2]--;
                orders[trees - 1] = orders[trees - 2] - 1;

                findAndSift(arrayToSort, leftIndex, trees - 2, orders);
                findAndSift(arrayToSort, rightIndex, trees - 1, orders);
            }
        }
    }
    /**
     * Apumetodi, jonka avulla pidetään yllä kekoehtoa.
     * Metodi etsii seuraavaksi suurimman alkion taulukosta ja asettaa sen heapin päällimmäiseksi.
     * @param arrayToSort Taulukko, jota järjestetään.
     * @param index Kohta, josta taulukkoa käydään läpi.
     * @param tree Monennettako puuta käsitellään.
     * @param orders Sisäisten puiden koot.
     */
    private void findAndSift(int[] arrayToSort, int index, int tree, int[] orders) {
        int value = arrayToSort[index];
        while (tree > 0) {
            int pivot = index - leonardoNumbers[orders[tree]];
            if (arrayToSort[pivot] <= value) {
                break;
            } else if (orders[tree] > 1) {
                int rightIndex = index - 1;
                int leftIndex = rightIndex - leonardoNumbers[orders[tree] - 2];
                if (arrayToSort[pivot] <= arrayToSort[leftIndex] || arrayToSort[pivot] <= arrayToSort[rightIndex]) {
                    break;
                }

            }
            arrayToSort[index] = arrayToSort[pivot];
            index = pivot;
            tree--;
        }
        arrayToSort[index] = value;
        siftDown(arrayToSort, index, orders[tree]);
    }
    /**
     * Apumetodi, joka korjaa annetun sisäisen keon kekoehdon.
     * @param arrayToSort Taulukko, jota järjestetään.
     * @param index Taulukon indeksi.
     * @param order Sisäisen keon koko.
     */
    public void siftDown(int[] arrayToSort, int index, int order) {
        int value = arrayToSort[index];
        while (order > 1) {
            int rightIndex = index - 1;
            int leftIndex = rightIndex - leonardoNumbers[order - 2];
            if (value >= arrayToSort[leftIndex] && value >= arrayToSort[rightIndex]) {
                break;
            } else if (arrayToSort[leftIndex] <= arrayToSort[rightIndex]) {
                arrayToSort[index] = arrayToSort[rightIndex];
                index = rightIndex;
                order = order - 2;
            } else {
                arrayToSort[index] = arrayToSort[leftIndex];
                index = leftIndex;
                order--;
            }
        }
        arrayToSort[index] = value;
    }
    
    /**
     * Lasketaan montako leonardon puuta kekoon muodostuu maksimissaan.
     * @param n Järjestettävän taulukon pituus
     * @return Laskettu määrä.
     */
    public int countOrderLength(int n) {
        return (int) (Math.log(n) / Math.log(2)) * 2;
       
    }
    
    /**
     * Lasketaan ensimmäiset neljäkymmentä leonardon lukua taulukkoon.
     * @return 40 ensimmäisen leonardon luvun taulukko.
     */
    public int[] formLeonardoSequence() {
        int[] leonardo = new int[40];
        leonardo[0] = leonardo[1] = 1;
        for (int i = 2; i < leonardo.length; i++) {
            leonardo[i] = 1 + leonardo[i - 1] + leonardo[i - 2];
        }
        return leonardo;
    }

}
