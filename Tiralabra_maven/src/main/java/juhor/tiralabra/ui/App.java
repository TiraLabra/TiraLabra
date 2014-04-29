package juhor.tiralabra.ui;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import juhor.tiralabra.data_structures.TrainingData;
import juhor.tiralabra.data_structures.Vector;
import juhor.tiralabra.network_classes.MLPLayer;
import juhor.tiralabra.network_classes.MLPNetwork;
import juhor.tiralabra.network_classes.MultiClassPerceptron;
import juhor.tiralabra.network_classes.Perceptron;

/**
 * This is the user interface class.
 * @author juhorim
 */
public class App {

    private static MLPNetwork mlp;
    private static FileManager fm;
    private static double[][] inputs;
    private static double[][] outputs;
    
    /**
     * Here we train the MLP, and measure the time training took.
     * @param ins
     * @param outs
     * @param learnRate
     * @param maxError
     * @param maxIterations 
     */
    public static void trainMLP(double[][] ins, double[][] outs, double learnRate, double maxError, int maxIterations) {
        System.out.println("Training the multilayered perceptron, this might take a while... ");
        long begin = System.currentTimeMillis();
        mlp.train(ins, outs, learnRate, maxError, maxIterations);
        long end = System.currentTimeMillis();
        System.out.println("Training complete, it took " + (end - begin) + "ms.");

    }
    
    /**
     * When user chooses perceptron this one is fired
     */
    public static void tron() {
        try {
            TrainingData data = new TrainingData(inputs, outputs);
            Perceptron tron = new Perceptron(data, 0.5);
            System.out.println("Training perceptron, this might take a while");
            
            long start = System.currentTimeMillis();
            tron.learn();
            long end = System.currentTimeMillis();
            System.out.println("Training completed it took " + (end-start) + "ms");
            System.out.println("Now we try to classify the remaining images...");
            File dirFalse = new File("images/false");
            File dirTrue = new File("images/true");

            File[] filesF = dirFalse.listFiles();
            File[] filesT = dirTrue.listFiles();

            int numOfErrors = 0;
            int i = 0;
            for (File file : filesF) {
                double[] in = fm.readImage(file);

                boolean out = tron.test(in);

                if (out == true) {
                    numOfErrors++;
                }
                i++;
            }

            for (File file : filesT) {
                double[] in = fm.readImage(file);

                boolean out = tron.test(in);

                if (out == false) {
                    numOfErrors++;
                }
            }

            System.out.println("Done. I counted " + numOfErrors + " false classifications");
        } catch (IOException e) {

        }

    }
    
    /**
     * When user chooses MLP, this method is fired
     * @param scanner 
     */
    public static void MLPtron(Scanner scanner) {
        try {
            System.out.println("Number of hidden layers: ");
            int numOfLayers = scanner.nextInt()+1;

            int[] nodes = new int[numOfLayers];
            for (int i = 1; i < numOfLayers; i++) {
                System.out.println("Number of nodes in " + (i ) + ". hidden layer: ");
                nodes[i] = scanner.nextInt();
            }
            nodes[numOfLayers-1] = 1;

            mlp = new MLPNetwork(nodes, 27*27);

            System.out.println("Enter the rate of learning: ");
            double learnRate = scanner.nextDouble();
            System.out.println("Enter the maximum error: ");
            double maxError = scanner.nextDouble();
            System.out.println("Enter number of iterations: ");
            int numOfIterations = scanner.nextInt();

            trainMLP(inputs, outputs, learnRate, maxError, numOfIterations);

            System.out.println("Now we try to classify the remaining images...");
            File dirFalse = new File("images/false");
            File dirTrue = new File("images/true");

            File[] filesF = dirFalse.listFiles();
            File[] filesT = dirTrue.listFiles();

            int numOfErrors = 0;
            int i = 0;
            for (File file : filesF) {
                double[] in = fm.readImage(file);

                double[] out = mlp.feedForward(in);

                if (out[1] > 0.5) {
                    numOfErrors++;
                }
                i++;
            }

            for (File file : filesT) {
                double[] in = fm.readImage(file);

                double[] out = mlp.feedForward(in);

                if (out[1] < 0.5) {
                    numOfErrors++;
                }
            }

            System.out.println("Done. I counted " + numOfErrors + " false classifications");
        } catch (IOException e) {

        }
    }
    
    /**
     * method that runs the whole show = creates the datasets for training.
     */
    public static void run() {
        fm = new FileManager();
        Scanner scanner = new Scanner(System.in);
        System.out.println("MLP or perceptron? (M or P): ");
        String MorP = scanner.nextLine();

        try {

            System.out.println("Enter the number of 'false' samples (0-850): ");
            int numOfFalseSamples = scanner.nextInt();
            double[][] f;
            System.out.println("Enter the number of 'true' samples (0-120): ");
            int numOfTrueSamples = scanner.nextInt();
            double[][] t;

            f = fm.readImagesFromDirectory(new File("images/false"), 0, numOfFalseSamples);
            t = fm.readImagesFromDirectory(new File("images/true"), 0, numOfTrueSamples);
            inputs = new double[f.length + t.length][27 * 27];
            outputs = new double[f.length + t.length][1];
            System.arraycopy(f, 0, inputs, 0, f.length);
            for (int i = 0; i < f.length; i++) {
                double[] val = {0};
                outputs[i] = val;
            }
            System.arraycopy(t, 0, inputs, f.length, t.length);
            for (int i = f.length; i < outputs.length; i++) {
                double[] val = {1};
                outputs[i] = val;
            }

            shuffle();

            if (MorP.equals("M")) {
                MLPtron(scanner);
            } else if (MorP.equals("P")) {
                tron();
            }

        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static void shuffle() {
        Random r = new Random();
        for (int i = inputs.length - 1; i > 0; i--) {
            int index = r.nextInt(i);

            double[] tmp = inputs[index];
            inputs[index] = inputs[i];
            inputs[i] = tmp;

            tmp = outputs[index];
            outputs[index] = outputs[i];
            outputs[i] = tmp;
        }

    }

    public static void main(String[] args) {


        run();
    }
}
