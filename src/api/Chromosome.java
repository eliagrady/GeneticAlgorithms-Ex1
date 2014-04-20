package api;

public interface Chromosome {
	public Fitness getFittness();
	public void setFitness(Fitness fitness);
	@Override
	public String toString();
}
