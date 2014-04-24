package eightQueens;

import api.Chromosome;
import api.Fitness;

/**
 * PACKAGE_NAME
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 * @deprecated moved to be represented in the model
 */
public class EightQueenChromosome implements Chromosome {
    private Queen[] queens = new Queen[8];

    public EightQueenChromosome(Queen[] queens) {
        this.queens = queens;
        if(queens == null) {
            this.queens = initQueens(queens);
        }
    }

    private Queen[] initQueens(Queen[] queens) {
        queens = new Queen[8];
        return null;
    }

    @Override
    public Fitness getFitness() {
        return null;
    }

    @Override
    public void setFitness(Fitness fitness) {

    }

    @Override
    public void mutate() {
        //TODO how should this be implemented? I've added something - not quite sure it's good enough:

    }

    @Override
    public Chromosome[] crossover(Chromosome other) {
        return new Chromosome[0];
    }
}
