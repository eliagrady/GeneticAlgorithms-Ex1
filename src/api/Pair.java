package api;
/**
 * The Pair class.
 * @param <T> first type
 * @param <U> second type
 */
public class Pair<T, U> {

    // the class' variables
    private T left;
    private U right;

    
    
    /**
     * the class constructor.
     * @param left - the left member of the pair
     * @param right - the right member of the pair
     */
    public Pair(T left, U right) {
        this.left = left;
        this.right = right;
    }

    /**
     * @return the left member
     */
    public T getLeft() {
        return left;
    }

    /**
     * @return the right member
     */
    public U getRight() {
        return right;
    }

    /**
     * @param left - new left parameter
     */
    public void setLeft(T left)
    {
    
        this.left = left;
    }

    /**
     * @param right - new right parameter
     */
    public void setRight(U right)
    {
    
        this.right = right;
    }
}
