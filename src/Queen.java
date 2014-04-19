/**
 * PACKAGE_NAME
 * Created by: Elia Grady
 * ID : 300907060
 * Username:  gradyel
 */
public class Queen {
    private int row;
    private int col;

    public Queen (int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }


    public boolean conflicts(Queen queen) {
        int deltaCol = this.col-queen.col;
        int deltaRow = this.row-queen.row;
        if(deltaCol == 0 && deltaRow == 0) {
            return false; //Same piece
        }
        if(deltaCol == deltaRow || deltaCol == -deltaRow) {
            return true; //Diagonally conflicting
        }
        if(deltaRow == 0 || deltaCol == 0) {
            return true; //Same row or col - conflicting
        }
        return false;
    }
}
