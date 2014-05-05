package controller;

import model.StopWatch;
import model.eight_queens_puzzle.EightQueensVisualizer;
import model.eight_queens_puzzle.genetic_solver.GeneticEightQueensSolver;
import model.eight_queens_puzzle.random_solver.RandomEightQueensSolver;

import java.util.ArrayList;

/**
 * The PartOne class.
 * @author Omer
 *
 */
public class PartOne
{
        
    // the class' variables
    private int sizeOf_Population;
    private int mutationProbability;
    private int elitism;
    private int maxFitness;
    private int numOf_Generations;
        
    
    
    /**
     * the class' constructor.
     * @param sizeOf_Population - the size of the population
     * @param mutationProbability - the probability of mutations
     * @param elitism - the elitism
     * @param maxFitness - the maximal fitness of a chromosome
     * @param numOf_Generations - the number of generations
     */
    public PartOne(int sizeOf_Population, int mutationProbability, int elitism,
            int maxFitness, int numOf_Generations)
    {
        
        super();
        this.sizeOf_Population = sizeOf_Population;
        this.mutationProbability = mutationProbability;
        this.elitism = elitism;
        this.maxFitness = maxFitness;
        this.numOf_Generations = numOf_Generations;
    }

    /**
     * finds a solution to the eight queens problem randomly.
     */
    public void randomEightQueensSolver()
    {

        // starts a stop watch
        StopWatch sw = new StopWatch();
 
        // finds an eight queens puzzle solution
        RandomEightQueensSolver solver = new RandomEightQueensSolver();
        ArrayList<Integer> queensPositions = solver.getSolution();
        
        // prints the solution
        System.out.println(EightQueensVisualizer.eightQueensPuzzleToString(queensPositions));
        System.out.println("\nTotal Run Time: " + sw.getTime() + "\n");
    }
        
    /**
     * finds a solution to the eight queens problem using GA.
     * @return the report
     */
    @SuppressWarnings("unchecked")
    public ArrayList<String> geneticEightQueensSolver()
    {
        
        // starts a stop watch
        StopWatch sw = new StopWatch();
        
        // finds an eight queens puzzle solution
        GeneticEightQueensSolver solver = new GeneticEightQueensSolver(sizeOf_Population, mutationProbability, elitism, maxFitness, numOf_Generations);
        System.out.println(EightQueensVisualizer.eightQueensPuzzleToString((ArrayList<Integer>) solver.getSolution()));
        System.out.println("\nTotal Run Time: " + sw.getTime() + "\n");

        // returns the report
        return solver.getReport();
    }
}
