package eightQueens;
import api.Chromosome;
import api.Fitness;

import java.util.Random;

/**
 * EightQueenModel
 * Created by: Elia Grady , Omer feldberg
 * ID : 300907060
 */
public class EightQueenModel implements Chromosome
{
    //Class constant, shouldn't be changed (used for literal replacement)
    private static final int NUM_OF_QUEENS = 8;
    private Queen[] queens;
    private EightQueenFitness fitness = null;
    // randomizes the positions
    private static Random random = new Random();




    /**
     * Default constructor (similar to "new EightQueenModel(null)")
     */
    public EightQueenModel() {
        this(null);
        //Making random extra random?
        random.setSeed(random.nextLong());
    }

    private class EightQueenFitness implements Fitness {
        private int grade;
        public EightQueenFitness() {
            grade = countConflictingQueens();
        }
        @Override
        /**
         * Compares this object with the specified object for order.  Returns a
         * negative integer, zero, or a positive integer as this object is less
         * than, equal to, or greater than the specified object.
         * <p/>
         * <p>The implementor must ensure <tt>sgn(x.compareTo(y)) ==
         * -sgn(y.compareTo(x))</tt> for all <tt>x</tt> and <tt>y</tt>.  (This
         * implies that <tt>x.compareTo(y)</tt> must throw an exception iff
         * <tt>y.compareTo(x)</tt> throws an exception.)
         * <p/>
         * <p>The implementor must also ensure that the relation is transitive:
         * <tt>(x.compareTo(y)&gt;0 &amp;&amp; y.compareTo(z)&gt;0)</tt> implies
         * <tt>x.compareTo(z)&gt;0</tt>.
         * <p/>
         * <p>Finally, the implementor must ensure that <tt>x.compareTo(y)==0</tt>
         * implies that <tt>sgn(x.compareTo(z)) == sgn(y.compareTo(z))</tt>, for
         * all <tt>z</tt>.
         * <p/>
         * <p>It is strongly recommended, but <i>not</i> strictly required that
         * <tt>(x.compareTo(y)==0) == (x.equals(y))</tt>.  Generally speaking, any
         * class that implements the <tt>Comparable</tt> interface and violates
         * this condition should clearly indicate this fact.  The recommended
         * language is "Note: this class has a natural ordering that is
         * inconsistent with equals."
         * <p/>
         * <p>In the foregoing description, the notation
         * <tt>sgn(</tt><i>expression</i><tt>)</tt> designates the mathematical
         * <i>signum</i> function, which is defined to return one of <tt>-1</tt>,
         * <tt>0</tt>, or <tt>1</tt> according to whether the value of
         * <i>expression</i> is negative, zero or positive.
         *
         * @param o the object to be compared.
         * @return a negative integer, zero, or a positive integer as this object
         * is less than, equal to, or greater than the specified object.
         * @throws NullPointerException if the specified object is null
         * @throws ClassCastException   if the specified object's type prevents it
         *                              from being compared to this object.
         */
        @Override
        public int compareTo(Fitness other) {
            return this.grade - ((EightQueenFitness) other).grade;
        }

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


    public Fitness getFitness() {
        return new EightQueenFitness();
    }
    @Override
    public void setFitness(Fitness other) {
        //TODO remove unused 'set'
        this.fitness = (EightQueenFitness) other;
    }

    @Override
    public void mutate() {
        queens[random.nextInt(NUM_OF_QUEENS)].setCol(random.nextInt(NUM_OF_QUEENS));
    }

    @Override
    public Chromosome[] crossover(Chromosome other) {
        Queen[] otherQueens = ((EightQueenModel) other).getQueens();

        //Cross 1:

        //Cross 2:

        return null;

    }
}