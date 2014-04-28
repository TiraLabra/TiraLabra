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
 * Hello world!
 *
 */
public class App {

    /**
     * private static void measureLearning(int numOfInput, int[] numOfNodes,
     * double distance) { Random rand = new Random();
     *
     * double[][] outputs = new double[numOfInput][1];
     *
     * double[][] class0 = new double[numOfInput / 2][2]; for (int i = 0; i <
     * class0.length; i++) { double x = distance / 2 + 3 * rand.nextDouble();
     * double[] xset = {x}; class0[i] = xset; outputs[i][0] = 0; }
     *
     * double[][] class1 = new double[numOfInput / 2][2]; for (int i = 0; i <
     * class1.length; i++) { double x = -distance / 2 - 3 * rand.nextDouble();
     * double[] xset = {x}; class1[i] = xset; outputs[numOfInput / 2 + i][0] =
     * 1; }
     *
     * double[][] inputs = new double[numOfInput][2]; System.arraycopy(class0,
     * 0, inputs, 0, class0.length); System.arraycopy(class1, 0, inputs,
     * class0.length, class1.length);
     *
     * trainMLP(outputs, inputs, numOfNodes); //perceptronTraining(outputs,
     * inputs); //MCPTraining(outputs, inputs);
     *
     * }
     *
     * private static void trainMLP(double[][] outs, double[][] ins, int[]
     * numOfNodes) { MLPNetwork tron = new MLPNetwork(numOfNodes);
     *
     * long start = System.currentTimeMillis(); tron.train(ins, outs, 0.01,
     * 0.01, 100000); long end = System.currentTimeMillis();
     *
     * System.out.println("MLP training took: " + (end - start)); }
     *
     * private static void perceptronTraining(double[][] outs, double[][] ins) {
     * TrainingData data = new TrainingData(ins, outs); Perceptron tron = new
     * Perceptron(data, 0.5);
     *
     * long start = System.currentTimeMillis(); tron.learn(); long end =
     * System.currentTimeMillis();
     *
     * System.out.println("Perceptron training took: " + (end - start)); }
     *
     * private static void MCPTraining(double[][] outs, double[][] ins) {
     * TrainingData data = new TrainingData(ins, outs); MultiClassPerceptron
     * tron = new MultiClassPerceptron(data, 2);
     *
     * long start = System.currentTimeMillis(); tron.learn(); long end =
     * System.currentTimeMillis();
     *
     * System.out.println("Multi Class Perceptron training took: " + (end -
     * start));
     *
     * }
     */
    private static MLPNetwork mlp;
    private static FileManager fm;
    private static double[][] inputs;
    private static double[][] outputs;

    public static void trainMLP(double[][] ins, double[][] outs, double learnRate, double maxError, int maxIterations) {
        System.out.println("Training the multi-layered perceptron, this might take a while... ");
        long begin = System.currentTimeMillis();
        double e = mlp.train(outs, outs, learnRate, maxError, maxIterations);
        long end = System.currentTimeMillis();
        System.out.println("Training complete, it took " + (end - begin) + "ms. Final value of error function: " + e);

    }

    public static void run() {
        fm = new FileManager();
        try {
            Scanner scanner = new Scanner(System.in);
            System.out.println("Number of layers: ");
            int numOfLayers = scanner.nextInt();

            int[] nodes = new int[numOfLayers];
            for (int i = 0; i < numOfLayers; i++) {
                System.out.println("Number of nodes in " + (i + 1) + ". layer: ");
                nodes[i] = scanner.nextInt();
            }

            mlp = new MLPNetwork(nodes, 27*27);

            System.out.println("Enter the number of 'false' samples (0-850): ");
            int numOfFalseSamples = scanner.nextInt();
            double[][] f;
            System.out.println("Enter the number of 'true' samples (0-120): ");
            int numOfTrueSamples = scanner.nextInt();
            double[][] t;
            
            f = fm.readImagesFromDirectory(new File("images/false"),0, numOfFalseSamples);
            t = fm.readImagesFromDirectory(new File("images/true"),0, numOfTrueSamples);
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
            f = new double[dirFalse.listFiles().length - numOfFalseSamples][27*27];
            t = new double[dirTrue.listFiles().length - numOfTrueSamples][27*27];
            
            File[] filesF = dirFalse.listFiles();
            File[] filesT = dirTrue.listFiles();
            
            int numOfErrors = 0;
            int i = 0;
            for(File file: filesF){
                double[] in = fm.readImage(file);
                System.out.println(i);
                double[] out = mlp.feedForward(in);
                
                if(out[1] > 0.5){
                    numOfErrors++;
                }
                i++;
            } 
            
            for(File file: filesT){
                double[] in = fm.readImage(file);
                
                double[] out = mlp.feedForward(in);
                
                if(out[1] < 0.5){
                    numOfErrors++;
                }
            }
            
            System.out.println("Done. I counted " + numOfErrors + " false classifications");
            
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
        /**
         * int[] numOfInputs = {3 ,6, 1}; System.out.println("Testing with
         * constant distance 3"); for(int i = 1; i < 10; i++){
         * measureLearning(i*1000, numOfInputs, 0.02); }
         */
        /**
        double[] imArray = new double[27 * 27];

        FileManager fm = new FileManager();
        try {
            imArray = fm.readImage(new File("im1.png"));
        } catch (IOException e) {
            System.out.println("Could not read file");
        }

        System.out.println(Arrays.toString(imArray));
        */
        
        run();
    }
}
