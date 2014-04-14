/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sanapuuro.datastructures;

import java.util.List;

/**
 *
 * @author skaipio
 */
public class Util {
    
    public static int pickNumberThatIsFarthestFromPowerOfTwo(int[] numbers){
        int numberFarthest = 0;
        int farthestDistanceFromPowerOfTwo = 0;
        for (int n : numbers){
            int closestPowerOfTwo = findClosestPowerOfTwoTo(n);
            int distanceFromPowerOfTwo = Math.abs(closestPowerOfTwo-n);
            if (distanceFromPowerOfTwo > farthestDistanceFromPowerOfTwo){
                farthestDistanceFromPowerOfTwo = distanceFromPowerOfTwo;
                numberFarthest = n;
            }
        }
        return numberFarthest;
    }
    
    public static int findClosestPowerOfTwoTo(int n){
        int previousPowerOfTwo = 1;
        int powerOfTwo = 1;
        while(powerOfTwo < n){
            previousPowerOfTwo = powerOfTwo;
            powerOfTwo = powerOfTwo*2;
        }
        if (Math.abs(n-previousPowerOfTwo) < Math.abs(n-powerOfTwo)){
            return previousPowerOfTwo;
        }else{
            return powerOfTwo;
        }
    }

    /**
     * Finds two prime numbers close to n.
     *
     * @param n Number to find by
     * @return Two prime numbers close to n
     */
    public static int[] findPrimesCloseTo(int n) {
        int previousPrime = 1;
        int[] twoPrimes = new int[2];
        int i = previousPrime + 1;

        while (twoPrimes[0] == 0 || twoPrimes[1] == 0) {
            boolean isPrime = isPrime(i);
            if (isPrime) {
                if (i <= n) {
                    previousPrime = i;
                } else {
                    if (twoPrimes[0] == 0) {
                        twoPrimes[0] = previousPrime;
                        previousPrime = i;
                    } else {
                        twoPrimes[1] = previousPrime;
                    }
                }
            }
            i++;
        }

        return twoPrimes;
    }

    /**
     * Evaluates if n is a prime number.
     *
     * @param n Number to evaluate.
     * @return True if n is a prime number, false otherwise.
     */
    public static boolean isPrime(int n) {
        if (n <= 1 || n % 2 == 0) {
            return false;
        }

        for (int i = 3; i * i <= n; i += 2) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }
    
    /**
     * Creates a new MyHashSet from the objects in the given list.
     * @param <T> Type of objects.
     * @param objs List of objects to add to the set.
     * @param hashFunc Hash function for the set.
     * @return A new MyHashSet of type T.
     */
    public static <T> MyHashSet<T> convertListToMyHashSet(List<T> objs, HashFuncs<T> hashFunc){
        MyHashSet<T> set = new MyHashSet<>(objs.size(), hashFunc);
        for (T obj : objs){
            set.add(obj);
        }
        return set;
    }
}
