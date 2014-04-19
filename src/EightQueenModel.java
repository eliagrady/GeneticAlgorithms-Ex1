/**
 * PACKAGE_NAME
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public class EightQueenModel {
    private Queen[] queens = new Queen[8];

    public EightQueenModel(Queen[] queens) {
        this.queens = queens;
        if(queens == null) {
            this.queens = initQueens(queens);
        }
    }

    private Queen[] initQueens(Queen[] queens) {
        return new Queen[8];
    }

    public int countConflictingQueens() {
        int counter = 0;
        for (int i = 1 ; i < queens.length ; i++) {
            for (int j=1 ; i < queens.length ; j++) {
                queens[i].conflicts(queens[j])?counter++:counter;
            }
        }
    }


}
