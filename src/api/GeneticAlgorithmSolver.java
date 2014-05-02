package api;

/**
 * Created by Elia on 20/04/2014.
 */
public interface GeneticAlgorithmSolver {
    public abstract Population initializePopulation(int initialPopulationSize);
    public abstract Chromosome getFittest(int numberOfGenerations, float crossoverRate, float mutationRate);
}
