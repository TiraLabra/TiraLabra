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
    
    public boolean checkThatArraysAreTheSame(int[] array1, int[] array2){
        if(array1.length == array2.length){
            for(int i = 0; i < array1.length; i++){
                if(!(array1[i]==array2[i])){
                    return false;
                }
            }
        }
        return true;
    }
    
    public int[] giveRandomArray(){
        int length = randomizer.nextInt(100);
        int[] array = new int[length];
        for(int i = 0; i < array.length; i++){
            array[i] = randomizer.nextInt(length);
        }
        return array;
    }
}
