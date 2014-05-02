package model.eight_queens.random_solver;

import java.util.ArrayList;

/**
 * The IEightQueensSolver inteface.
 * @author Omer
 *
 */
public interface IEightQueensSolver
{

    /**
     * @return a viable solution to the eight queens problem
     */
    public ArrayList<Integer> getSolution();
}
