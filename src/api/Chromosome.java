package api;

public interface Chromosome {
    /**
     * Returns the Fitness of this chromosome
     * @return the Fitness of this chromosome
     */
	public Fitness getFitness();

    /**
     * Sets a new fitness for the chromosome
     * @param fitness new fitness for this chromosome
     */
	public void setFitness(Fitness fitness);
	public void mutate();
    public Chromosome[] crossover(Chromosome other);


    /**
     * Returns a string representation of this chromosome.
     * Must be implemented by the individual chromosome, as this method is implementation dependent.
     * @return a string representation of this chromosome
     */
    @Override
    public String toString();

}
