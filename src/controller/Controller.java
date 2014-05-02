package controller;

import api.ann.activation_functions.BoundedReLU;
import api.ann.activation_functions.IActivationFunction;
import com.google.common.primitives.Ints;

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
    protected static class Config {
        // part one parameters
        private static int sizeOf_Population = 1500;
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
        while (true) {
            //listen to commands and remain alive
        }
    }
}
