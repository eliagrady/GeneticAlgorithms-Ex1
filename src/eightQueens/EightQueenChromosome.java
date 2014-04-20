package eightQueens;

import api.Chromosome;
import api.Fitness;

/**
 * PACKAGE_NAME
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
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
    public Fitness getFittness() {
        return null;
    }

    @Override
    public void setFitness(Fitness fitness) {

    }
}
