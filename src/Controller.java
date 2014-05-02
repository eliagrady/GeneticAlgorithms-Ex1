import eightQueens.EightQueenModel;
import eightQueens.GAEightQueenSolver;
import eightQueens.RandomEightQueenSolver;

/**
 * Created by Elia on 20/04/2014.
 */
public class Controller {



    /**
     * Default constructor
     */
    public Controller() {
        init();
    }

    /**
     * Initializes this controller
     */
    public void init() {

    }

    public void outputRandomEightQueenSolution() {
        RandomEightQueenSolver reqs = new RandomEightQueenSolver();
        System.out.println(EightQueenModel.eightQueensPuzzleToString(reqs.getSolution()));
    }
    public void outputGAEightQueenSolution() {
        GAEightQueenSolver gaeqs = new GAEi
    }


}
