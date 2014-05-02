package model.eight_queens_puzzle.genetic_solver;

import api.genetic_algorithm.AbstractChromosome;
import api.genetic_algorithm.AbstractGeneticAlgorithm;
import api.genetic_algorithm.AbstractPopulation;

import java.util.ArrayList;

/**
 * The GeneticEightQueensSolver class.
 * @author Omer
 *
 */
public class GeneticEightQueensSolver extends AbstractGeneticAlgorithm
{

    /**
     * the class' constructor.
     * @param sizeOf_Population - the size of the population
     * @param mutationProbability - the probablility of mutations
     * @param elitism - the elitism
     * @param maxFitness - the maximal fitness of a chromosome
     * @param numOf_Generations - the number of generations
     */
    public GeneticEightQueensSolver(int sizeOf_Population, int mutationProbability, int elitism, int maxFitness, int numOf_Generations)
    {
        
        super(sizeOf_Population, mutationProbability, elitism, maxFitness, numOf_Generations);
    }

    @Override
    protected AbstractPopulation createPopulation(int sizeOf_Population)
    {

        return new EightQueensPopulation(sizeOf_Population);
    }

    @Override
    protected AbstractPopulation createPopulation(ArrayList<AbstractChromosome> chromosomes)
    {
        
        return new EightQueensPopulation(chromosomes);
    }
}
