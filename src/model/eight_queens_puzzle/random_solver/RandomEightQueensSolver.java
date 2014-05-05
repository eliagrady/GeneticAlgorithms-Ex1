package model.eight_queens_puzzle.random_solver;

import java.util.ArrayList;
import java.util.Random;

import api.IEightQueensSolver;

/**
 * "Brute force" algorithm for the eight queens problem.
 * @author Omer
 *
 */
public class RandomEightQueensSolver implements IEightQueensSolver
{

    // the class' constant
    private final int NUMBER_OF_QUEENS = 8;
    
    
    
    /**
     * the class' constructor.
     */
    public RandomEightQueensSolver()
    {
        
        super();
    }

    /**
     * @return random queens positions
     */
    private ArrayList<Integer> getRandomQueensPositions()
    {
     
        // randomizes the positions
        ArrayList<Integer> queensPositions = new ArrayList<Integer>();
        Random random = new Random();
        for (int i = 0; i < NUMBER_OF_QUEENS; ++i)
        {
            
            queensPositions.add(random.nextInt(NUMBER_OF_QUEENS));
        }
        
        // returns the positions
        return queensPositions;
    }
    
    /**
     * validates queens positions.
     * @param queensPositions - queens positions
     * @return true if the positioning is valid and false if not
     */
    private boolean areQueensPositionsValid(ArrayList<Integer> queensPositions)
    {
        
        // validates the queens positions
        for (int i = 0; i < (NUMBER_OF_QUEENS - 1); ++i)
        {
            
            for (int j = i + 1; j < NUMBER_OF_QUEENS; ++j)
            {
                
                // checks for column collision
                if (queensPositions.get(i).equals(queensPositions.get(j)))
                {
                    
                    return false;
                }
                
                // checks for diagonal collision
                if (Math.abs(i - j) == Math.abs(queensPositions.get(i).intValue() - queensPositions.get(j).intValue()))
                {
                    
                    return false;
                }
            }
        }
        
        // no collisions found
        return true;
    }
    
    @Override
    public ArrayList<Integer> getSolution()
    {
        
        // finds and returns a viable solution
        ArrayList<Integer> queensPositions = this.getRandomQueensPositions();
        
        while (!this.areQueensPositionsValid(queensPositions))
        {
            
            queensPositions = this.getRandomQueensPositions();
        }
        
        return queensPositions;
    }
}
