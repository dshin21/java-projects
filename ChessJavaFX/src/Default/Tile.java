package Default;

import Pieces.Piece;
import javafx.scene.control.Button;

import java.io.Serializable;

public class Tile extends Button implements Serializable {

    private int row;
    private int col;
    private Piece piece;

    public Tile(int row, int col, boolean color, int dimension) {
        super();
        this.row = row;
        this.col = col;
        this.getStyleClass().add("chess-space");

        if(dimension != 1){
            if (color) {
                this.getStyleClass().add("chess-space-white");
            } else {
                this.getStyleClass().add("chess-space-black");
            }
        }else{
            if (color) {
                this.getStyleClass().add("chess-space-white-mid");
            } else {
                this.getStyleClass().add("chess-space-black-mid");
            }
        }

    }

    public boolean isOccupied() {
        return this.piece != null;
    }


    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public Piece getPiece() {
        return piece;
    }

    public void setPiece(Piece piece) {
        this.piece = piece;
    }
}
