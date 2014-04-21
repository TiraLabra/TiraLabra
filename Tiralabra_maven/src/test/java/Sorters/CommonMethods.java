/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Sorters;

import java.util.Random;

/**
 *
 * @author Admin
 */
public class CommonMethods {
    private Random randomizer;
    
    public CommonMethods(){
        randomizer = new Random();
    }
    
    public int[] giveRandomArray(){
        int length = 100;
        int[] array = new int[length];
        for(int i = 0; i < array.length; i++){
            array[i] = randomizer.nextInt(length);
        }
        return array;
    }
    
    public int[] giveArrayOfOne(){
        int length = 1;
        int[] array = new int[length];
        for(int i = 0; i < array.length; i++){
            array[i] = 4;
        }
        return array;
    }
    
    public int[] giveArrayOfOneSolution(){
        return new int[]{4};
    }
    
    public int[] giveArrayOfNone(){
        return new int[0];
    }
    
    public int[] giveArrayOfNoneSolution(){
        return new int[0];
    }
    
    public int[] giveArrayInOrder(){
        int length = 100;
        int[] array = new int[length];
        for(int i = 0; i < array.length; i++){
            array[i] = i+1;
        }
        return array;
    }
    
    
    public int[] giveArrayInReverseOrder(){
        int length = 100;
        int[] array = new int[length];
        for(int i = 0; i < array.length; i++){
            array[i] = 100-i;
        }
        return array;
    }
    
    public int[] giveArrayOfAllSameNumbers(){
        int length = 100;
        int[] array = new int[length];
        for(int i = 0; i < array.length; i++){
            array[i] = 6;
        }
        return array;
    }
    
    public int[] giveLargeArrayOfRandomNumbers(){
        int length = 10000000;
        int[] array = new int[length];
        for(int i = 0; i < array.length; i++){
            array[i] = randomizer.nextInt(length);
        }
        return array;
    }
    
    public int[] giveAHardCodedArray(){
        return new int[]{4,4,4,2,2,8,8,325,55,55,55,55,55,55,344,1,1,1,1,1,1,5,1,1,1,1,1,1,1,576};
    }
    
    public int[] giveAHardCodedArraySolution(){
        return new int[]{1,1,1,1,1,1,1,1,1,1,1,1,1,2,2,4,4,4,5,8,8,55,55,55,55,55,55,325,344,576};
    }
}
