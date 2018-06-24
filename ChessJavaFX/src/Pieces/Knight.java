package Pieces;

import Default.Tile;

import java.io.Serializable;

public class Knight extends Piece implements Serializable {
    public int row;
    public int col;

    public Knight() {
    }

    public Knight(String color, int row, int col) {
        super(color, row, col);
        this.row = row;
        this.col = col;
    }

    @Override
    public String getIcon() {
        if (this.color.equals("white")) {
            return "♘";
        } else {
            return "♞";
        }
    }

    @Override
    public boolean isValid(Tile tile, int activeRow, int activeCol) {
        if (activeRow >= 0 && activeRow <= 7 && activeCol >= 0 && activeCol <= 7 && tile.isOccupied()) {
            if (tile.getCol() == activeCol && tile.getRow() == activeRow)
                return false;

            return (tile.getRow() + 2) == activeRow || (tile.getCol() + 2) == activeCol
                    || (tile.getRow() - 2) == activeRow || (tile.getCol() - 2) == activeCol;
        }
        return false;
    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }
}
