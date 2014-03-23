package Sorters;

public class ShellSorter {
        
    public int[] shellSort(int[] arrayToSort){
        int[] gaps = countGaps(arrayToSort.length);
        int currentGap;
        int tempj = 0;
        for(int w = 0; w<gaps.length; w++){
           currentGap = gaps[w];
           for(int i = currentGap; i < arrayToSort.length; i++){
               for (int k = currentGap; k < arrayToSort.length; k++) {
			int j = k;
			int temp = arrayToSort[k];
			while (j >= currentGap && arrayToSort[j - currentGap] > temp) {
				arrayToSort[j] = arrayToSort[j - currentGap];
				j = j - currentGap;
			}
			arrayToSort[j] = temp;
		}
           }
           
        }
      return arrayToSort;  
        
    }
    
    public int[] countGaps(int length){
        int[] temp = new int[30];
        int numberOfGaps = 0;
        for(int i = 0; ((int)length/Math.pow(2,i+1) > 1)&&(i<=30); i++){
             temp[i]=(int) (length/Math.pow(2,i+1));
             numberOfGaps++;
        }
        if((numberOfGaps == 0)||(temp[numberOfGaps -1] != 1) ){
            temp[numberOfGaps] = 1;    
            numberOfGaps++;    
            }
        
        int[] gaps = new int[numberOfGaps];
        for(int i = 0; i < numberOfGaps; i++){
            gaps[i] = temp[i];
            
        }
        return gaps;
    }
    
    
}
