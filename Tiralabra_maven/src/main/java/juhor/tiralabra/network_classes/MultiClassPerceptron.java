
package juhor.tiralabra.network_classes;

import juhor.tiralabra.data_structures.TrainingData;
import juhor.tiralabra.data_structures.Vector;

/**
 * Single layer multiclass perceptron. This one can classify datapoints into multiple different classes.
 * This time we have multiple weight vectors; one for each class. 
 * 
 * @author juhorim
 */
public class MultiClassPerceptron {

    Vector[] weights; //we actually need multiple weight vectors, one for each class
    TrainingData trainingData;

    public MultiClassPerceptron(TrainingData data, int numOfClasses) {
        trainingData = data;
        weights = new Vector[numOfClasses];

        for (int i = 0; i < weights.length; i++) {
            weights[i] = new Vector(trainingData.getVectorDimensions());
        }
    }
    
    /**
     * With multiclass perceptrons learning is a bit different task. For each vector in training data,
     * we calculate the dot products with ALL the weight vectors, and determine which one has the largest value.
     * Datapoint will be classified to the class assosiated with that weight vector. If the answer wasn't correct,
     * vector of the datapoint will be added to the correct weight vector, and substracted from the wrong one.
     * 
     */
    public void learn() {
        int errors = 1;
        while (errors != 0) {
            errors = 0;

            for (int i = 0; i < trainingData.getNumOfVectors(); i++) {
                double largestProduct = -1;
                int indexLargest = 0;
                Vector v = new Vector(trainingData.getVector(i));

                for (int j = 0; j < weights.length; j++) {
                    double z = weights[j].dotProduct(v);

                    if (z > largestProduct) {
                        largestProduct = z;
                        indexLargest = j;
                    }
                }

                if (indexLargest != trainingData.getOutput(i)[0]) {
                    weights[indexLargest].substract(v);
                    weights[(int) trainingData.getOutput(i)[0]].add(v);
                    errors++;
                }

            }
        }
    }
    /**
     * After learning we can use the perceptron to check other vectors.
     * @param d
     * @return 
     */
    public int classify(double[] d) {
        double largest = -1;
        int out = 0;

        for (int i = 0; i < weights.length; i++) {
            double z = weights[i].dotProduct(new Vector(d));

            if (z > largest) {
                largest = z;
                out = i;
            }
        }

        return out;
    }
}
