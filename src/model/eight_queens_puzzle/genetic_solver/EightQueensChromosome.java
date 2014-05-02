package model.eight_queens_puzzle.genetic_solver;

import controller.Pair;
import api.genetic_algorithm.AbstractChromosome;

import java.util.ArrayList;
import java.util.Random;

/**
 * The EightQueensChromosome class.
 * @author Omer
 *
 */
@SuppressWarnings("unchecked")
public class EightQueensChromosome extends AbstractChromosome
{

    // the class' constants
    private final int NUNBER_OF_QUEENS = 8;
    private final int MAX_FITNESS = 28;
    private final Random RANDOM = new Random();
    
    
    
    /**
     * the class' constructor,
     * initializes the chromosome randomly.
     */
    public EightQueensChromosome()
    {
        
        super();
    }

    /**
     * the class' constructor,
     * initializes the chromosome using a given sequence.
     * @param sequence - a given sequence.
     */
    public EightQueensChromosome(Object sequence)
    {
        
        super(sequence);
    }

    @Override
    protected Object createRandomizedSequence()
    {

        return this.getRandomQueensPositions();
    }

    @Override
    protected double calculateFitness()
    {

        // calculates and returns the fitness
        return (MAX_FITNESS - this.countConflicts((ArrayList<Integer>) this.getSequence()));
    }

    @Override
    //@SuppressWarnings("unchecked")
    public Pair<AbstractChromosome, AbstractChromosome> crossover(AbstractChromosome other)
    {

        // the method's variables
        ArrayList<Integer> thisSequence = (ArrayList<Integer>) this.getSequence();
        ArrayList<Integer> otherSequence = (ArrayList<Integer>) other.getSequence();
        
        int chromosomeLength = thisSequence.size();
        ArrayList<Integer> firstOffspringSequence = new ArrayList<Integer>(chromosomeLength);
        ArrayList<Integer> secondOffspringSequence = new ArrayList<Integer>(chromosomeLength);
        
        // creates two new offsprings by crossing the parents
        for (int i = 0; i < chromosomeLength; ++i)
        {
            
            int currentChoice = RANDOM.nextInt(2);
            
            if (0 == currentChoice)
            {
                
                firstOffspringSequence.add(thisSequence.get(i));
                secondOffspringSequence.add(otherSequence.get(i));
            }
            
            else
            {
                
                firstOffspringSequence.add(otherSequence.get(i));
                secondOffspringSequence.add(thisSequence.get(i));
            }
        }
        
        // returns the offsprings
        return new Pair<AbstractChromosome, AbstractChromosome>(new EightQueensChromosome(firstOffspringSequence),
                                                                new EightQueensChromosome(secondOffspringSequence));
    }
    
    @Override
    public void executeMutation()
    {
        
        // the method's variables
        ArrayList<Integer> sequence = (ArrayList<Integer>) this.getSequence();
        int chosenRow = RANDOM.nextInt(NUNBER_OF_QUEENS);
        int chosenColumn = RANDOM.nextInt(NUNBER_OF_QUEENS);
        
        // mutates
        sequence.set(chosenRow, chosenColumn);
    }
    
    /**
     * @return random queens positions
     */
    private ArrayList<Integer> getRandomQueensPositions()
    {
     
        // randomizes the positions
        ArrayList<Integer> queensPositions = new ArrayList<Integer>(NUNBER_OF_QUEENS);
        Random random = new Random();
        for (int i = 0; i < NUNBER_OF_QUEENS; ++i)
        {
            
            queensPositions.add(random.nextInt(NUNBER_OF_QUEENS));
        }
        
        // returns the positions
        return queensPositions;
    }
    
    /**
     * counts the number of queens conflicts.
     * @param queensPositions - queens positions
     * @return the number of conflicts
     */
    private int countConflicts(ArrayList<Integer> queensPositions)
    {
        
        // counts conflicts
        int numOf_Conflicts = 0;
        for (int i = 0; i < (NUNBER_OF_QUEENS - 1); ++i)
        {
            
            for (int j = i + 1; j < NUNBER_OF_QUEENS; ++j)
            {
                
                // checks for column collision
                if (queensPositions.get(i).equals(queensPositions.get(j)))
                {
                    
                    ++numOf_Conflicts;
                }
                
                // checks for diagonal collision
                if (Math.abs(i - j) == Math.abs(queensPositions.get(i).intValue() - queensPositions.get(j).intValue()))
                {
                    
                    ++numOf_Conflicts;
                }
            }
        }
        
        // returns the number of conflicts
        return numOf_Conflicts;
    }
}
