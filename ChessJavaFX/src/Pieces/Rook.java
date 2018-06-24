package Pieces;

import Default.Tile;

import java.io.Serializable;

public class Rook extends Piece implements Serializable {
    public int row;
    public int col;

    public Rook() {
    }

    public Rook(String color, int row, int col) {
        super(color, row, col);
        this.row = row;
        this.col = col;
    }

    @Override
    public String getIcon() {
        if (this.color.equals("white")) {
            return "♖";
        } else {
            return "♜";
        }
    }

    @Override
    public boolean isValid(Tile tile, int activeRow, int activeCol) {
        if (activeRow >= 0 && activeRow <= 7 && activeCol >= 0 && activeCol <= 7 && tile.isOccupied()) {
            if (tile.getCol() == activeCol && tile.getRow() == activeRow)
                return false;

            return tile.getRow() == activeRow || tile.getCol() == activeCol;
        }

        return false;
    }

    @Override
    public int getRow() {
        return row;
    }

    @Override
    public void setRow(int row) {
        this.row = row;
    }

    @Override
    public int getCol() {
        return col;
    }

    @Override
    public void setCol(int col) {
        this.col = col;
    }
}
