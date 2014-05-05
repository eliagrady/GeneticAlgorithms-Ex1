package model.eight_queens.genetic_solver;

import java.util.ArrayList;

import api.ga.AbstractChromosome;
import api.ga.AbstractPopulation;

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
