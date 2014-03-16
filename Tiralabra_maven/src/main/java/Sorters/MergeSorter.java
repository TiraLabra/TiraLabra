

package Sorters;

import java.util.Arrays;

public class MergeSorter {
    
    public int[] mergeSort(int[] arrayToSort){
        if(arrayToSort.length<2){
            return arrayToSort;
        }
        else{
            int[] uusi = new int[arrayToSort.length];
            int keski = (int) arrayToSort.length/2;
            int[] left = mergeSort(Arrays.copyOfRange(arrayToSort, 0, keski));
            int[] right = mergeSort(Arrays.copyOfRange(arrayToSort, keski, arrayToSort.length));
            int i = 0;
            int j = 0;
            int k = 0;
            while(i<left.length&&j<right.length){
                if(left[i]<right[j]){
                    uusi[k]=left[i];
                    k++;
                    i++;
                }
                else{
                    uusi[k]=right[j];
                    k++;
                    j++;
                }
            }
            while(i<left.length){
                uusi[k]=left[i];
                i++;
                k++;
            }
            while(j<right.length){
                uusi[k]=right[j];
                j++;
                k++;
            }
            return uusi;
        }
    }
    
}
