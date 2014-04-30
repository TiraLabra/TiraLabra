/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sanapuuro.benchmarks;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import sanapuuro.hashfunctions.HashFunction;

/**
 *
 * @author skaipio
 */
public class Util {
    public static float[] getCollisionsWithFunction(List<String> words, HashFunction<String> function){
        Map<Integer, Integer> collisionCounts = new HashMap<>();
        for (String word : words) {
            int hash = function.getHash(word);
            if (!collisionCounts.containsKey(hash)) {
                collisionCounts.put(hash, 0);
            } else {
                int count = collisionCounts.get(hash);
                collisionCounts.put(hash, count+1);
            }
        }
        int max = 0;
        int collisions = 0;
        for (int count : collisionCounts.values()) {
            collisions += count;
            max = Math.max(count, max);
        }
        float average = 1.0f*(collisions)/collisionCounts.size();
        return new float[]{collisions, average, max};
    }
    
    public static void printCollisionData(String functionName, float[] collisionData){
        System.out.println(functionName);
        System.out.println("Total collisions: " + (int)collisionData[0]);
        System.out.println("Average collision chain length: " + collisionData[1]);
        System.out.println("Max collisions for same hash: " + (int)collisionData[2]);
        System.out.println("");
    }
}
