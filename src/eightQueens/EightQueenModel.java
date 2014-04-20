package eightQueens;
import java.util.Random;

/**
 * EightQueenModel
 * Created by: Elia Grady , Omer feldberg
 * ID : 300907060
 */
public class EightQueenModel {
    //Class constant, shouldn't be changed (used for literal replacement)
    private static final int NUM_OF_QUEENS = 8;
    private Queen[] queens;

    /**
     * Default constructor (similar to "new EightQueenModel(null)")
     */
    public EightQueenModel() {
        this(null);
    }

    public EightQueenModel(Queen[] queens) {
        this.queens = queens;
        if(queens == null) {
            initQueens();
        }
    }

    private void initQueens()
    {
        queens = new Queen[NUM_OF_QUEENS];
        for (int i = 0; i < queens.length; i++) {
            queens[i] = new Queen(i,i); //Diagonal for no reason
        }
    }


    /**
     * Count how many queens are conflicting in the board
     * If Queen A threaten Queen B, then Queen B also threaten Queen A, therefore divided by 2
     * @return the number of conflicts in the current assignment.
     * varies between 0 (solution) and NUM_OF_QUEENS (full conflict)
     */
    public int countConflictingQueens() {
        int counter = 0;
        for (int i = 1 ; i < queens.length ; i++) {
            for (int j=1 ; i < queens.length ; j++) {
                if(queens[i].conflicts(queens[j])) {
                    counter++;
                }
            }
        }
        return counter/2;
    }

    /**
     * randomize queens positions
     * made synchronized for thread safety
     */
    public synchronized Queen[] randomizeQueens()
    {
        if(queens == null) {
            initQueens();
        }
        // randomizes the positions
        Random random = new Random();
        random.setSeed(random.nextLong());
        for (int i = 0; i < NUM_OF_QUEENS; ++i)
        {
            queens[i].setCol(random.nextInt(NUM_OF_QUEENS));
        }

        // returns the positions
        return queens;
    }

    /**
     * builds an eight queens puzzle board.
     * @param queens - the queens positions
     * @return the board
     */
    private static String[][] buildBoard(Queen[] queens)
    {

        // builds the board
        String[][] board = new String[NUM_OF_QUEENS][NUM_OF_QUEENS];

        // places blanks
        for (int i = 0; i < NUM_OF_QUEENS; ++i)
        {
            for (int j = 0; j < NUM_OF_QUEENS; ++j)
            {
                //Fill the board with spaces
                board[i][j] = " ";
            }
        }

        // places the queens
        for (int i = 0; i < NUM_OF_QUEENS; ++i)
        {
            board[i][queens[i].getCol()] = "Q";
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
        StringBuilder sb = new StringBuilder();
        sb.append("  ---------------------------------\n");

        // makes one string out of the board
        for (int i = 0; i < NUM_OF_QUEENS; ++i)
        {

            sb.append((NUM_OF_QUEENS - i) + " | ");

            for (int j = 0; j < 8; ++j)
            {

                // adds the current piece
                sb.append(board[i][j]);

                if (j < 7)
                    sb.append(" | ");

            }

            sb.append(" |\n  ---------------------------------\n");
        }
        sb.append("    a   b   c   d   e   f   g   h\n");

        // returns the string
        return sb.toString();
    }

    /**
     * returns a board with the queens placed by the given positions as a string.
     * @param queens - the queens positions
     * @return the board as a string
     */
    public static String eightQueensPuzzleToString(Queen[] queens)
    {
        // returns the puzzle board as a string
        String[][] board = buildBoard(queens);
        return boardToString(board);
    }

    public Queen[] getQueens() {
        return queens;
    }

    /**
     * I've left Omer's solution detection almost identical to initial implementation.
     * Can be further optimized
     * made synchronized for thread safety
     * @return true iff the queens assignment is a valid solution to the eight queen
     */
    public synchronized boolean validSolution() {
        //validates the queens positions
        for (int i = 0; i < (NUM_OF_QUEENS - 1); ++i)
        {

            for (int j = i + 1; j < NUM_OF_QUEENS; ++j)
            {

                // checks for column collision
                if (queens[i].getCol() == (queens[j].getCol()))
                {
                    return false;
                }

                // checks for diagonal collision
                if (Math.abs(i - j) == Math.abs(queens[i].getCol() - queens[j].getCol()))
                {
                    return false;
                }
            }
        }
        // no collisions found
        return true;
    }
}