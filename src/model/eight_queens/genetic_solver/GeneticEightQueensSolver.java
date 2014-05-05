package model.eight_queens.genetic_solver;

import java.util.ArrayList;

import api.ga.AbstractChromosome;
import api.ga.AbstractGeneticAlgorithm;
import api.ga.AbstractPopulation;

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
