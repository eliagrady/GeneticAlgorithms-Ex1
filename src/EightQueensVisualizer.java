package eight_queens_puzzle;

import java.util.ArrayList;

/**
 * Allows to visualize an eight queens puzzle solutions.
 * @author Omer
 *
 */
public class EightQueensVisualizer
{

    // the class' constant
    private static final int QUEENS_NUMBER = 8;
    
    
    
    /**
     * builds an eight queens puzzle board.
     * @param queensPositions - the queens positions
     * @return the board
     */
    private static String[][] buildBoard(ArrayList<Integer> queensPositions)
    {
        
        // builds the board
        String[][] board = new String[QUEENS_NUMBER][QUEENS_NUMBER];
        
        // places blanks
        for (int i = 0; i < QUEENS_NUMBER; ++i)
        {
            
            for (int j = 0; j < QUEENS_NUMBER; ++j)
            {
                
                board[i][j] = " ";
            }
        }

        // places the queens
        for (int i = 0; i < QUEENS_NUMBER; ++i)
        {
            
            board[i][queensPositions.get(i)] = "Q";
        }
        
        // returns the board
        return board;
    }
    
    /**
     * converts a given board to a string.
     * @param board - a given board to convert
     * @return the string
     */
    private static String boardToString(String[][] board)
    {
        
        // the method's variables
        String string = "  ---------------------------------\n";
        
        // makes one string out of the board
        for (int i = 0; i < QUEENS_NUMBER; ++i)
        {
            
            string += (QUEENS_NUMBER - i) + " | ";
            
            for (int j = 0; j < 8; ++j)
            {
                
                // adds the current piece
                string += board[i][j];
                
                if (j < 7)
                    string += " | ";

            }
            
            string += " |\n  ---------------------------------\n";
        }
        string += "    a   b   c   d   e   f   g   h\n";
        
        // returns the string
        return string;
    }
    
    /**
     * returns a board with the queens placed by the given positions as a string.
     * @param queensPositions - the queens positions
     * @return the board as a string
     */
    public static String eightQueensPuzzleToString(ArrayList<Integer> queensPositions)
    {
        
        // returns the puzzle board as a string
        String[][] board = buildBoard(queensPositions);
        return boardToString(board);
    }
}
