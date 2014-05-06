package controller;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Created by Elia on 02/05/2014.
 */
public class Main {

    /**
     * the main method of the class.
     * @param args - ignored.
     */
    public static void main(String[] args)
    {
        ExecutorService pool = Executors.newCachedThreadPool();

        Thread controllerThread = new Thread(new Controller(pool));

        //Future controllerFuture = pool.submit(controllerThread);
        pool.execute(controllerThread);
//        while(!controllerFuture.isDone()) {
//            //keep alive
//        }

    }
}
