package api.genetic_algorithm;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * The Population class,
 * an abstraction that represents a population.
 * @author Omer
 *
 */
public abstract class AbstractPopulation
{

    // the class' constant
    private final Random RANDOM = new Random();
    
    // the class' variables
    ArrayList<AbstractChromosome> chromosomes;
    ArrayList<Integer> roulette;
    
    
    
    /**
     * the class' constructor.
     * @param chromosomes - the chromosomes for the population
     */
    public AbstractPopulation(ArrayList<AbstractChromosome> chromosomes)
    {
        
        // initializes the population
        this.chromosomes = chromosomes;
        
        // builds the selection roulette wheel
        this.buildRoulette();
    }
    
    /**
     * the class' constructor,
     * creates randomized population.
     * @param sizeOf_Population - the size of the population
     */
    public AbstractPopulation(int sizeOf_Population)
    {
        
        // creates the population
        this.chromosomes = new ArrayList<AbstractChromosome>(sizeOf_Population);
        for (int i = 0; i < sizeOf_Population; ++i)
        {
            
            this.chromosomes.add(this.createRandomizedChromosome());
        }
        
        // builds the selection roulette wheel
        this.buildRoulette();
    }
    
    /**
     * builds the selection roulette wheel.
     */
    private void buildRoulette()
    {
        
        // builds the roulette
        this.roulette = new ArrayList<Integer>();
        
        int numOf_Chromosomes = this.chromosomes.size();
        for (int i = 0; i < numOf_Chromosomes; ++i)
        {
            
            int tickets = (int) this.chromosomes.get(i).getFitness();
            for (int j = 0; j < tickets; ++j)
            {
                
                this.roulette.add(i);
            }
        }
    }
    
    /**
     * @return a randomized chromosome
     */
    protected abstract AbstractChromosome createRandomizedChromosome();
    
    /**
     * naturally selects a chromosome.
     * @return the randomly selected chromosome
     */
    public AbstractChromosome selectRandomizedChromosome()
    {
        
        // returns the selected chromosome
        int index = RANDOM.nextInt(this.roulette.size());
        return this.chromosomes.get(this.roulette.get(index));
    }
    
    /**
     * @return the chromosome with the highest fitness in the population
     */
    public AbstractChromosome getBestChromosome()
    {
        
        return Collections.max(this.chromosomes);
    }
    
    /**
     * @return the average fitness of the population
     */
    public double getAverageFitness()
    {
        
        // sums all of the fitness of the chromosomes in the population
        double sum = 0;
        for (AbstractChromosome chromosome : this.chromosomes)
        {
            
            sum += chromosome.getFitness();
        }
        
        // returns the average fitness
        return (sum / this.chromosomes.size());
    }
}
