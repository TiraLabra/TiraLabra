
package com.mycompany.tiralabra_maven.performance_test;

/**
 * Computes the running time of an algorithm.
 * @author gabriel
 */
public abstract class Timer {
    
    /**
     * Returns the running time of the algorithm specified in the timedOperation method.
     * @return the running time of an algorithm
     */
    public long computeTime(){
        long totalTime = 0;
        int iterations = 5;
        for (int i = 0; i < iterations; i++) {
            long startTime = System.currentTimeMillis();
            timedOperation();
            long endTime = System.currentTimeMillis();
            totalTime += (endTime - startTime);
        }
        return totalTime / iterations;
    }

    /**
     * Specifies the algorithm whose running time is to be computed.
     */
    protected abstract void timedOperation();
            
}
