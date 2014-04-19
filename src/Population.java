
public interface Population {
	/**
	 * Adds a specified individual to the population.
	 * @param individual the individual to add
	 */
	public void	add(Chromosome individual);
	/**
	 * Adds a list of individuals to the population.
	 * @param individuals an array of individuals to add
	 */
	public void	addAll(Chromosome[] individuals);
	/**
	 * Gets an array of all individuals in the population.
	 * @return an array of all individuals in the population.
	 */
	public Chromosome[]	getAllMembers();
	/**
	 * Gets an individual at the specifies index from the population.
	 * If this population does not support an ordering, this method may throw an UnsupportedOperationException.
	 * @param index the index of the individual to get
	 * @return
	 */
	public Chromosome getMember(int index);
	
	/**
	 * Gets the size of the population.
	 * @return the size of the population.
	 */
	public int getSize();
}
