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
import java.util.concurrent.ExecutorService;

/**
 * The OldMainToBeRenamed class,
 * used for testing.
 * @author Omer
 *
 */
public class Controller implements Runnable
{
    private final Viewer viewer;
    protected ExecutorService threadPool;
    public Config configPart1;
    public Config configPart2;

    protected Controller(ExecutorService pool) {
        this.viewer = new Viewer(this);
        this.threadPool = pool;
        configPart1 = new Config();
        configPart2 = new Config();
    }


    //Start of configuration inner-class

    public class Config {
        /**
         * Config constructor
         */
        public Config() {

        }
        // part one parameters
        private int sizeOf_Population = 1500;

        public int getSizeOf_Population() {
            return sizeOf_Population;
        }

        public void setSizeOf_Population(int sizeOf_Population) {
            this.sizeOf_Population = sizeOf_Population;
        }

        public int getMutationProbability() {
            return mutationProbability;
        }

        public void setMutationProbability(int mutationProbability) {
            this.mutationProbability = mutationProbability;
        }

        public int getElitism() {
            return elitism;
        }

        public void setElitism(int elitism) {
            this.elitism = elitism;
        }

        public int getMaxFitness() {
            return maxFitness;
        }

        public void setMaxFitness(int maxFitness) {
            this.maxFitness = maxFitness;
        }

        public int getNumOf_Generations() {
            return numOf_Generations;
        }

        public void setNumOf_Generations(int numOf_Generations) {
            this.numOf_Generations = numOf_Generations;
        }

        public String getBaseFolder() {
            return baseFolder;
        }

        public void setBaseFolder(String baseFolder) {
            this.baseFolder = baseFolder;
        }

        public URL getLocation() {
            return location;
        }

        public void setLocation(URL location) {
            this.location = location;
        }

        public String getBaseMain() {
            return baseMain;
        }

        public void setBaseMain(String baseMain) {
            this.baseMain = baseMain;
        }

        public String getTrainExamples_FilePath() {
            return trainExamples_FilePath;
        }

        public void setTrainExamples_FilePath(String trainExamples_FilePath) {
            this.trainExamples_FilePath = trainExamples_FilePath;
        }

        public String getValidateOne_FilePath() {
            return validateOne_FilePath;
        }

        public void setValidateOne_FilePath(String validateOne_FilePath) {
            this.validateOne_FilePath = validateOne_FilePath;
        }

        public String getValidateTwo_FilePath() {
            return validateTwo_FilePath;
        }

        public void setValidateTwo_FilePath(String validateTwo_FilePath) {
            this.validateTwo_FilePath = validateTwo_FilePath;
        }

        public String getTestExamples_FilePath() {
            return testExamples_FilePath;
        }

        public void setTestExamples_FilePath(String testExamples_FilePath) {
            this.testExamples_FilePath = testExamples_FilePath;
        }

        public String getOutputFilePath() {
            return outputFilePath;
        }

        public void setOutputFilePath(String outputFilePath) {
            this.outputFilePath = outputFilePath;
        }

        public IActivationFunction getActivationFunction() {
            return activationFunction;
        }

        public void setActivationFunction(IActivationFunction activationFunction) {
            this.activationFunction = activationFunction;
        }

        public double getLearningRate() {
            return learningRate;
        }

        public void setLearningRate(double learningRate) {
            this.learningRate = learningRate;
        }

        public double getMomentum() {
            return momentum;
        }

        public void setMomentum(double momentum) {
            this.momentum = momentum;
        }

        public int getNumOf_Epochs() {
            return numOf_Epochs;
        }

        public void setNumOf_Epochs(int numOf_Epochs) {
            this.numOf_Epochs = numOf_Epochs;
        }

        public int getNumOf_TrainExamples() {
            return numOf_TrainExamples;
        }

        public void setNumOf_TrainExamples(int numOf_TrainExamples) {
            this.numOf_TrainExamples = numOf_TrainExamples;
        }

        public int getNumOf_ValidationExamples() {
            return numOf_ValidationExamples;
        }

        public void setNumOf_ValidationExamples(int numOf_ValidationExamples) {
            this.numOf_ValidationExamples = numOf_ValidationExamples;
        }

        public int getNumOf_Attributes() {
            return numOf_Attributes;
        }

        public void setNumOf_Attributes(int numOf_Attributes) {
            this.numOf_Attributes = numOf_Attributes;
        }

        public int getNumOf_ClassValues() {
            return numOf_ClassValues;
        }

        public void setNumOf_ClassValues(int numOf_ClassValues) {
            this.numOf_ClassValues = numOf_ClassValues;
        }

        public ArrayList<Integer> getSizesOf_Layers() {
            return sizesOf_Layers;
        }

        public void setSizesOf_Layers(ArrayList<Integer> sizesOf_Layers) {
            this.sizesOf_Layers = sizesOf_Layers;
        }

        private int mutationProbability = 100;
        private int elitism = 2;
        private int maxFitness = 28;
        private int numOf_Generations = 3000;

        // files paths
        private  String baseFolder = System.getProperty("user.dir")+File.separator+"src";
        private  URL location = Main.class.getProtectionDomain().getCodeSource().getLocation();
        private  String baseMain = location.getFile();

        private  String trainExamples_FilePath = "C:/Users/Omer/Desktop/train.txt";
        private  String validateOne_FilePath = "C:/Users/Omer/Desktop/validate1.txt";
        private  String validateTwo_FilePath = "C:/Users/Omer/Desktop/validate2.txt";
        private  String testExamples_FilePath = "C:/Users/Omer/Desktop/test.txt";
        private  String outputFilePath = "C:/Users/Omer/Desktop/output.txt";
        //private static final String attributesFilePath = "C:/Users/Omer/Desktop/attributes.txt";

        // part two parameters
        private IActivationFunction activationFunction = new BoundedReLU(1);
        private double learningRate = 0.001;
        private double momentum = 0.2;
        private int numOf_Epochs = 50;

        private int numOf_TrainExamples = 40000;
        private int numOf_ValidationExamples = 10000;
        private int numOf_Attributes = 784;
        private int numOf_ClassValues = 10;
        private ArrayList<Integer> sizesOf_Layers
                = new ArrayList<Integer>(Ints.asList(new int[]{
                this.numOf_Attributes, 100, this.numOf_ClassValues }));

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
    private void partOne(Config config)
    {

        // creates part one API
        PartOne partOne = new PartOne(
                config.sizeOf_Population,
                config.mutationProbability,
                config.elitism,
                config.maxFitness,
                config.numOf_Generations);

        // finds a random solution
        partOne.randomEightQueensSolver();

        // finds a genetic solution
        writeToFile(partOne.geneticEightQueensSolver(), null);
    }

    /**
     * part two of the exercise.
     */
    private static void partTwo(Config config)
    {

        // creates part two API
        PartTwo partTwo = new PartTwo(
                config.trainExamples_FilePath,
                config.validateOne_FilePath,
                config.validateTwo_FilePath,
                config.testExamples_FilePath,
                config.sizesOf_Layers,
                config.activationFunction,
                config.learningRate,
                config.momentum,
                config.numOf_Epochs,
                config.numOf_TrainExamples,
                config.numOf_ValidationExamples,
                config.numOf_Attributes,
                config.numOf_ClassValues);

        // runs ANN
        partTwo.ann();
    }

    @Override
    public void run() {
        viewer.run();
    }

    public void runPartOne() {
        final Runnable task = new Thread() {
            @Override public void run() {
                partOne(configPart1);
            }
        };
        threadPool.submit(task);
    }

    public void runPartTwo() {
        final Runnable task = new Thread() {
            @Override public void run() {
                partTwo(configPart2);
            }
        };
        threadPool.submit(task);
    }



}
