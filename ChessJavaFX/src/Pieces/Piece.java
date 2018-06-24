package Pieces;

import Default.Tile;

import java.io.Serializable;

public abstract class Piece implements Serializable {

    public int row;
    public int col;

    public String color;

    public Piece() {
    }

    public Piece(String color, int row, int col) {
        this.color = color;
    }

    public abstract String getIcon();

    public abstract boolean isValid(Tile tile, int activeRow, int activeCol);

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
}
