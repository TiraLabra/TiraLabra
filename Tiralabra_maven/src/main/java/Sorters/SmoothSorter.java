package Sorters;

/**
 * 
 * Smoothsort on vertailujärjestämisealgoritmi joka käyttää aputietorakenteenaan kekoa.
 * Smoothsort on kekojärjestämisen variaatio, jolla saavutetaan parempi aikavaativuus lähes järjestetyillä taulukoilla perinteiseen kekojärjestämiseen verrattuna.
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
        int length = arrayToSort.length;
        int orderLength = countOrderLength(length);
        int[] orders = new int[orderLength];
        int trees = 0;

        trees = fromLeftToRight(arrayToSort, orders, trees);

        fromRightToLeft(arrayToSort, orders, trees);

    }

    private int fromLeftToRight(int[] arrayToSort, int[] orders, int trees) {
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

    private void fromRightToLeft(int[] arrayToSort, int[] orders, int trees) {
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
     * 
     * @param n Järjestettävän taulukon pituus
     * @return 
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
