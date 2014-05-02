package model.eight_queens_puzzle.genetic_solver;

import api.genetic_algorithm.AbstractChromosome;
import api.genetic_algorithm.AbstractPopulation;

import java.util.ArrayList;

/**
 * The EightQueensPopulation class.
 * @author Omer
 *
 */
public class EightQueensPopulation extends AbstractPopulation
{

    /**
     * the class' constructor.
     * @param chromosomes - the chromosomes for the population
     */
    public EightQueensPopulation(ArrayList<AbstractChromosome> chromosomes)
    {
        
        super(chromosomes);
    }

    /**
     * the class' constructor,
     * creates randomized population.
     * @param sizeOf_Population - the size of the population
     */
    public EightQueensPopulation(int sizeOf_Population)
    {
        
        super(sizeOf_Population);
    }

    @Override
    protected AbstractChromosome createRandomizedChromosome()
    {

        return new EightQueensChromosome();
    }
}
