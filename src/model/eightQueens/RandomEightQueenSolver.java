package model.eight_queens_puzzle.eightQueens;

import java.util.concurrent.*;

/**
 * Created by Elia on 20/04/2014.
 */
public class RandomEightQueenSolver extends EightQueenSolver {
    private EightQueenModel model;
    private volatile Queen[] solution = null;

    public RandomEightQueenSolver() {
        model = new EightQueenModel();


    }

    @Override
    public Queen[] getSolution() {
        //Search thread declaration
        final Runnable searchForSolutionTask = new Thread() {
            @Override public void run() {
                //Do search here
                //Start with some solution:
                model.randomizeQueens();
                while (!model.validSolution()) {
                    model.randomizeQueens();
                }
                //assign a new solution
                solution = model.getQueens();
            }

        };
        //Executor init
        final ExecutorService executor = Executors.newSingleThreadExecutor();
        final Future<?> future = executor.submit(searchForSolutionTask);
        executor.shutdown();
        try {
            future.get(18000, TimeUnit.MILLISECONDS);
        }
        catch (InterruptedException ie) {
            ie.printStackTrace();
        }
        catch (ExecutionException ee) {
            ee.printStackTrace();
        }
        catch (TimeoutException te) {
            //te.printStackTrace();
            //time limit reached
        }


        //Start off with only one thread



        //Can fail! test this!
        assert solution != null;
        return solution;
    }

}
