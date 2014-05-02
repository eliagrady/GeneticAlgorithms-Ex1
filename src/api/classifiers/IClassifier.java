package api.classifiers;

import controller.Pair;

import java.util.ArrayList;

/**
 * The IClassifier class.
 * @author Omer
 *
 */
public interface IClassifier
{

    /**
     * trains the classifier.
     * @param examples - the examples to train with
     */
    public void train(ArrayList<Pair<double[], Integer>> examples);
    
    /**
     * validates the classifier.
     * @param examples - the examples to validate with
     * @return the percentage of success
     */
    public double validate(ArrayList<Pair<double[], Integer>> examples);
    
    /**
     * classifies a given set of examples.
     * @param examples - the examples to classify
     * @return the classifications of the examples
     */
    public int[] classify(ArrayList<double[]> examples);
}
