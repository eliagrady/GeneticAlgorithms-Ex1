package eightQueens;

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

    /**
     * Should be unused according to the idea Omer had. //TODO decide on removal
     * @return
     */
    public int getRow() {
        return row;
    }

    /**
     * Returns the column of the queen
     * @return an integer representing the column location of the queen
     */
    public int getCol() {
        return col;
    }


    /**
     * Returns true iff this queen is conflicting with another, given queen
     * @param queen the other queen to check conflict with
     * @return true iff the queens threaten one another (same col,row or diagonal)
     */
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

    public void setCol(int col) {
        this.col = col;
    }
}
