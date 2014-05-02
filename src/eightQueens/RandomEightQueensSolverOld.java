package eightQueens;

import java.util.ArrayList;
import java.util.Random;

/**
 * "Brute force" algorithm for the eight queens problem.
 * @author Omer
 * @deprecated replaced by RandomEightQueensSolver
 */
public class RandomEightQueensSolverOld
{

    // the class' constant
    private static final int QUEENS_NUMBER = 8;
    
    
    
    /**
     * the class' constructor.
     */
    public RandomEightQueensSolverOld()
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
        random.setSeed(random.nextLong());
        for (int i = 0; i < QUEENS_NUMBER; ++i)
        {
            
            queensPositions.add(random.nextInt(QUEENS_NUMBER));
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
        for (int i = 0; i < (QUEENS_NUMBER - 1); ++i)
        {
            
            for (int j = i + 1; j < QUEENS_NUMBER; ++j)
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
    
    /**
     * @return a viable solution to the eight queens problem
     */
    public ArrayList<Integer> getSoluion()
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
