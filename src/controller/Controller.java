package controller;

import api.ann.activation_functions.BoundedReLU;
import api.ann.activation_functions.IActivationFunction;
import com.google.common.primitives.Ints;
import view.Viewer;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;

/**
 * The OldMainToBeRenamed class,
 * used for testing.
 * @author Omer
 *
 */
public class Controller implements Runnable
{
    private final Viewer viewer;

    public Controller() {
        this.viewer = new Viewer(this);
    }


    //Start of configuration inner-class

    protected static class Config {
        // part one parameters
        private static int sizeOf_Population = 1500;

        public static int getSizeOf_Population() {
            return sizeOf_Population;
        }

        public static void setSizeOf_Population(int sizeOf_Population) {
            Config.sizeOf_Population = sizeOf_Population;
        }

        public static int getMutationProbability() {
            return mutationProbability;
        }

        public static void setMutationProbability(int mutationProbability) {
            Config.mutationProbability = mutationProbability;
        }

        public static int getElitism() {
            return elitism;
        }

        public static void setElitism(int elitism) {
            Config.elitism = elitism;
        }

        public static int getMaxFitness() {
            return maxFitness;
        }

        public static void setMaxFitness(int maxFitness) {
            Config.maxFitness = maxFitness;
        }

        public static int getNumOf_Generations() {
            return numOf_Generations;
        }

        public static void setNumOf_Generations(int numOf_Generations) {
            Config.numOf_Generations = numOf_Generations;
        }

        public static String getBaseFolder() {
            return baseFolder;
        }

        public static void setBaseFolder(String baseFolder) {
            Config.baseFolder = baseFolder;
        }

        public static URL getLocation() {
            return location;
        }

        public static void setLocation(URL location) {
            Config.location = location;
        }

        public static String getBaseMain() {
            return baseMain;
        }

        public static void setBaseMain(String baseMain) {
            Config.baseMain = baseMain;
        }

        public static String getTrainExamples_FilePath() {
            return trainExamples_FilePath;
        }

        public static void setTrainExamples_FilePath(String trainExamples_FilePath) {
            Config.trainExamples_FilePath = trainExamples_FilePath;
        }

        public static String getValidateOne_FilePath() {
            return validateOne_FilePath;
        }

        public static void setValidateOne_FilePath(String validateOne_FilePath) {
            Config.validateOne_FilePath = validateOne_FilePath;
        }

        public static String getValidateTwo_FilePath() {
            return validateTwo_FilePath;
        }

        public static void setValidateTwo_FilePath(String validateTwo_FilePath) {
            Config.validateTwo_FilePath = validateTwo_FilePath;
        }

        public static String getTestExamples_FilePath() {
            return testExamples_FilePath;
        }

        public static void setTestExamples_FilePath(String testExamples_FilePath) {
            Config.testExamples_FilePath = testExamples_FilePath;
        }

        public static String getOutputFilePath() {
            return outputFilePath;
        }

        public static void setOutputFilePath(String outputFilePath) {
            Config.outputFilePath = outputFilePath;
        }

        public static IActivationFunction getActivationFunction() {
            return activationFunction;
        }

        public static void setActivationFunction(IActivationFunction activationFunction) {
            Config.activationFunction = activationFunction;
        }

        public static double getLearningRate() {
            return learningRate;
        }

        public static void setLearningRate(double learningRate) {
            Config.learningRate = learningRate;
        }

        public static double getMomentum() {
            return momentum;
        }

        public static void setMomentum(double momentum) {
            Config.momentum = momentum;
        }

        public static int getNumOf_Epochs() {
            return numOf_Epochs;
        }

        public static void setNumOf_Epochs(int numOf_Epochs) {
            Config.numOf_Epochs = numOf_Epochs;
        }

        public static int getNumOf_TrainExamples() {
            return numOf_TrainExamples;
        }

        public static void setNumOf_TrainExamples(int numOf_TrainExamples) {
            Config.numOf_TrainExamples = numOf_TrainExamples;
        }

        public static int getNumOf_ValidationExamples() {
            return numOf_ValidationExamples;
        }

        public static void setNumOf_ValidationExamples(int numOf_ValidationExamples) {
            Config.numOf_ValidationExamples = numOf_ValidationExamples;
        }

        public static int getNumOf_Attributes() {
            return numOf_Attributes;
        }

        public static void setNumOf_Attributes(int numOf_Attributes) {
            Config.numOf_Attributes = numOf_Attributes;
        }

        public static int getNumOf_ClassValues() {
            return numOf_ClassValues;
        }

        public static void setNumOf_ClassValues(int numOf_ClassValues) {
            Config.numOf_ClassValues = numOf_ClassValues;
        }

        public static ArrayList<Integer> getSizesOf_Layers() {
            return sizesOf_Layers;
        }

        public static void setSizesOf_Layers(ArrayList<Integer> sizesOf_Layers) {
            Config.sizesOf_Layers = sizesOf_Layers;
        }

        private static int mutationProbability = 100;
        private static int elitism = 2;
        private static int maxFitness = 28;
        private static int numOf_Generations = 3000;

        // files paths
        private static String baseFolder = System.getProperty("user.dir")+File.separator+"src";
        private static URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
        private static String baseMain = location.getFile();

        private static String trainExamples_FilePath = "C:/Users/Omer/Desktop/train.txt";
        private static String validateOne_FilePath = "C:/Users/Omer/Desktop/validate1.txt";
        private static String validateTwo_FilePath = "C:/Users/Omer/Desktop/validate2.txt";
        private static String testExamples_FilePath = "C:/Users/Omer/Desktop/test.txt";
        private static String outputFilePath = "C:/Users/Omer/Desktop/output.txt";
        //private static final String attributesFilePath = "C:/Users/Omer/Desktop/attributes.txt";

        // part two parameters
        private static IActivationFunction activationFunction = new BoundedReLU(1);
        private static double learningRate = 0.001;
        private static double momentum = 0.2;
        private static int numOf_Epochs = 50;

        private static int numOf_TrainExamples = 40000;
        private static int numOf_ValidationExamples = 10000;
        private static int numOf_Attributes = 784;
        private static int numOf_ClassValues = 10;
        private static ArrayList<Integer> sizesOf_Layers
                = new ArrayList<Integer>(Ints.asList(new int[]{
                Config.numOf_Attributes, 100, Config.numOf_ClassValues }));

    }


    //End of configuration inner-class


    /**
     * writes to file.
     * @param strings - an array list of strings to write to the file
     * @param outputFilePath
     */
    private static void writeToFile(ArrayList<String> strings, String outputFilePath)
    {
        
        // saves the report to file
        try
        {
            
            PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath));
            for (String string : strings)
            {
                
                writer.println(string);
            }
            
            writer.close();
        }
        
        catch (Exception e) {}
    }
           
    /**
     * part one of the exercise.
     */
    private static void partOne()
    {
        
        // creates part one API
        PartOne partOne = new PartOne(
                Config.sizeOf_Population,
                Config.mutationProbability,
                Config.elitism,
                Config.maxFitness,
                Config.numOf_Generations);
        
        // finds a random solution
        partOne.randomEightQueensSolver();
        
        // finds a genetic solution
        writeToFile(partOne.geneticEightQueensSolver(), null);
    }
    
    /**
     * part two of the exercise.
     */
    private static void partTwo()
    {

        // creates part two API
        PartTwo partTwo = new PartTwo(
                Config.trainExamples_FilePath,
                Config.validateOne_FilePath,
                Config.validateTwo_FilePath,
                Config.testExamples_FilePath,
                Config.sizesOf_Layers,
                Config.activationFunction,
                Config.learningRate,
                Config.momentum,
                Config.numOf_Epochs,
                Config.numOf_TrainExamples,
                Config.numOf_ValidationExamples,
                Config.numOf_Attributes,
                Config.numOf_ClassValues);
        
        // runs ANN
        partTwo.ann();
    }

    @Override
    public void run() {
        viewer.run();
    }
}
