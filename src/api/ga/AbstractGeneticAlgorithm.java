package api.ga;

import java.util.ArrayList;
import java.util.Random;

import api.Pair;

/**
 * The AbstractGeneticAlgorithm class.
 * @author Omer
 *
 */
public abstract class AbstractGeneticAlgorithm
{

    // the class' variables
    private int sizeOf_Population;
    private int mutationProbability;
    private int elitism;
    private int maxFitness;
    private int numOf_Generations;
    private ArrayList<String> report;
    
    
    
    /**
     * the class' constructor.
     * @param sizeOf_Population - the size of the population
     * @param mutationProbability - the probability of mutations
     * @param elitism - the elitism
     * @param maxFitness - the maximal fitness of a chromosome
     * @param numOf_Generations - the number of generations
     */
    public AbstractGeneticAlgorithm(int sizeOf_Population, int mutationProbability, int elitism, int maxFitness, int numOf_Generations)
    {
        
        super();
        this.sizeOf_Population = sizeOf_Population;
        this.mutationProbability = mutationProbability;
        this.elitism = elitism;
        this.maxFitness = maxFitness;
        this.numOf_Generations = numOf_Generations;
        this.report = new ArrayList<String>();
    }

    /**
     * calculates the offsprings of a given population
     * @param population - the population
     * @return the offsprings
     */
    protected ArrayList<AbstractChromosome> getOffsprings(AbstractPopulation population)
    {
        
        // builds the new population
        final Random RANDOM = new Random();
        ArrayList<AbstractChromosome> chromosomes = new ArrayList<AbstractChromosome>();
        
        // elitism
        AbstractChromosome elite = population.getBestChromosome(); 
        for (int i = 0; i < elitism; ++i)
        {
            
            chromosomes.add(elite);
        }
        
        //
        int numOf_Crossvers = (sizeOf_Population / 2) - elitism;
        for (int i = 0; i < numOf_Crossvers; ++i)
        {
            
            AbstractChromosome father = population.selectRandomizedChromosome();
            AbstractChromosome mother = population.selectRandomizedChromosome();
            
            Pair<AbstractChromosome, AbstractChromosome> offsprings = father.crossover(mother);
            AbstractChromosome firstOffspring = offsprings.getLeft();
            AbstractChromosome secondOffspring = offsprings.getRight();
            
            int firstOffspringMutationChance = RANDOM.nextInt(mutationProbability);
            int secondOffspringMutationChance = RANDOM.nextInt(mutationProbability);
            
            if (0 == firstOffspringMutationChance)
            {
                
                firstOffspring.mutate();
            }
            
            if (0 == secondOffspringMutationChance)
            {
                
                secondOffspring.mutate();
            }
            
            chromosomes.add(firstOffspring);
            chromosomes.add(secondOffspring);
        }
        
        // returns the chromosomes
        return chromosomes;
    }
    
    /**
     * creates a population
     * @param sizeOf_Population - the size of the population
     * @return the population
     */
    protected abstract AbstractPopulation createPopulation(int sizeOf_Population);
    
    /**
     * creates a population
     * @param chromosomes - chromosomes to populate with
     * @return the population
     */
    protected abstract AbstractPopulation createPopulation(ArrayList<AbstractChromosome> chromosomes);
    
    /**
     * @return a the best solution found
     */
    public Object getSolution()
    {

        // creates the initial population
        AbstractPopulation population = this.createPopulation(this.sizeOf_Population);
        
        // initializes the report
        this.report = new ArrayList<String>();
        this.report.add("Generation,Best,Average");

        // runs for numOf_Generations
        for (int i = 0; i < this.numOf_Generations; ++i)
        {
            
            // updates the report
            this.report.add(new String((i+1) + "," + population.getBestChromosome().getFitness() + "," + population.getAverageFitness()));
            
            // founds the best solution
            if (population.getBestChromosome().getFitness() >= this.maxFitness)
            {
                
                break;
            }
            
            // builds the next population
            population = this.createPopulation(this.getOffsprings(population));
        }

        // returns a viable solution
        return population.getBestChromosome().getSequence();
    }
    
    /**
     * @return the report
     */
    public ArrayList<String> getReport()
    {
        
        return this.report;
    }
}
