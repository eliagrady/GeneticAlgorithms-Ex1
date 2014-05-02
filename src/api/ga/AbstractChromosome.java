package api.ga;

import api.Pair;

/**
 * The AbstractChromosome class,
 * an abstraction that represents a chromosome
 * @author Omer
 *
 */
public abstract class AbstractChromosome implements Comparable<AbstractChromosome>
{
 
    // the class' variables
    private Object sequence;
    private double fitness;
    
    
    
    /**
     * the class' constructor,
     * initializes the chromosome randomly.
     */
    public AbstractChromosome()
    {
        
        this.sequence = this.createRandomizedSequence();
        this.fitness = this.calculateFitness();
    }
    
    /**
     * the class' constructor,
     * initializes the chromosome using a given sequence.
     * @param sequence - a given sequence.
     */
    public AbstractChromosome(Object sequence)
    {
        
        this.sequence = sequence;
        this.fitness = this.calculateFitness();
    }
    
    /**
     * creates a randomized sequence.
     * @return randomized sequence
     */
    protected abstract Object createRandomizedSequence();
    
    /**
     * @return the sequence of this chromosome
     */
    public Object getSequence()
    {
        
        return this.sequence;
    }
    
    /**
     * calculates the fitness of this chromosome.
     * @return the fitness
     */
    protected abstract double calculateFitness();
    
    /**
     * @return the fitness of this chromosome
     */
    public double getFitness()
    {
        
        return this.fitness;
    }
    
    /**
     * crosses two chromsomes.
     * @return two offsprings
     */
    public abstract Pair<AbstractChromosome, AbstractChromosome> crossover(AbstractChromosome other);

    /**
     * executes a mutation.
     */
    protected abstract void executeMutation();
    
    /**
     * mutates this chromosome.
     */
    public void mutate()
    {
        
        // executes a mutation and updates the fitness
        this.executeMutation();
        this.fitness = this.calculateFitness();
    }

    @Override
    public int compareTo(AbstractChromosome other)
    {

        double thisFitenss = this.getFitness();
        double otherFitness = other.getFitness();
        
        if (thisFitenss > otherFitness)
        {
            
            return 1;
        }
        
        else if (thisFitenss == otherFitness)
        {
            
            return 0;
        }
        
        return -1;
    }
}
