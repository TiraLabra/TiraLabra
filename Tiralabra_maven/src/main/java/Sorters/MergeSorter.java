

package Sorters;

import java.util.Arrays;

public class MergeSorter {
    
    public int[] mergeSort(int[] arrayToSort){
        //taulukko on jo järjestyksessä jos <2
        if(arrayToSort.length<2){
            return arrayToSort;
        }
        else{
            int[] uusi = new int[arrayToSort.length];
            int keski = (int) arrayToSort.length/2;
            int[] left = mergeSort(Arrays.copyOfRange(arrayToSort, 0, keski));
            int[] right = mergeSort(Arrays.copyOfRange(arrayToSort, keski, arrayToSort.length));
            int vasemmanTaulukonIndeksi = 0;
            int oikeanTaulukonIndeksi = 0;
            int lopullisentaulukonIndeksi = 0;
            while(vasemmanTaulukonIndeksi<left.length&&oikeanTaulukonIndeksi<right.length){
                if(left[vasemmanTaulukonIndeksi]<right[oikeanTaulukonIndeksi]){
                    uusi[lopullisentaulukonIndeksi]=left[vasemmanTaulukonIndeksi];
                    lopullisentaulukonIndeksi++;
                    vasemmanTaulukonIndeksi++;
                }
                else{
                    uusi[lopullisentaulukonIndeksi]=right[oikeanTaulukonIndeksi];
                    lopullisentaulukonIndeksi++;
                    oikeanTaulukonIndeksi++;
                }
            }
            while(vasemmanTaulukonIndeksi<left.length){
                uusi[lopullisentaulukonIndeksi]=left[vasemmanTaulukonIndeksi];
                vasemmanTaulukonIndeksi++;
                lopullisentaulukonIndeksi++;
            }
            while(oikeanTaulukonIndeksi<right.length){
                uusi[lopullisentaulukonIndeksi]=right[oikeanTaulukonIndeksi];
                oikeanTaulukonIndeksi++;
                lopullisentaulukonIndeksi++;
            }
            return uusi;
        }
    }
    
}
