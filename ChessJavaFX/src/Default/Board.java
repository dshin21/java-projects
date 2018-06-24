package Default;

import Pieces.*;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;

class Board extends Boards implements Serializable {
    private Tile[][] board = new Tile[8][8];


    public Tile[][] getBoard() {
        return board;
    }

    private int boardNum;

    public int getBoardNum() {
        return boardNum;
    }

    Board(int boardNum) {

        this.boardNum = boardNum;

        for (int col = 0; col < board[0].length; col++) {
            for (int row = 0; row < board[1].length; row++) {
//                coloring the tiles
                boolean white = ((row + col) % 2) != 0;
                board[row][col] = new Tile(row, col, white, boardNum);
//                placing the pieces
                board[row][col].setStyle("-fx-font: 40 arial");
                if (getBoardNum() == 0)
                    placePieces(row, col);

                if (true)
                    this.add(board[row][col], row, 7 - col);
                else
                    this.add(board[row][col], 7 - row, col);

                final int activeRow = row;
                final int activeCol = col;


                board[row][col].setOnAction(e -> {
                    masterActiveCol = activeCol;
                    masterActiveRow = activeRow;
                    System.out.println("row: " + masterActiveRow + " col: " + masterActiveCol + " Dimension: " + boardNum);

                    processMove(masterActiveRow, masterActiveCol);

                    if (clickedTile == null) {
                        clickedTile = board[masterActiveRow][masterActiveCol];
                        clickedDimension = this.boardNum;
                    } else {
                        clickedTile = null;
                    }
                });
            }
        }
    }

    public void redraw(List<Piece> pieces) {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (this.getBoard()[i][j] != null) {
                    this.getBoard()[i][j].setPiece(null);
                    this.getBoard()[i][j].setText("");
                }
            }
        }

        for (Piece piece : pieces) {
            if (piece != null)
                this.setPiece(piece, piece.getCol(), piece.getRow(), boardNum);
        }
    }

    protected boolean isValidMove(Piece piece, Tile tile, int boardNum) {
        if (!piece.color.equals(whoseTurn)) {
            return false;
        }
        if (boardNum != (this.boardNum - 1) && boardNum != (this.boardNum + 1) && boardNum != (this.boardNum))
            return false;
        if ((tile.getPiece() == null || !piece.color.equals(tile.getPiece().color))) {
            return piece.isValid(boardArray.get(clickedDimension).board[clickedTile.getRow()][clickedTile.getCol()], tile.getRow(), tile.getCol());
        }
        return false;
    }

    //TODO: make it account for all boards
    protected void processMove(int activeRow, int activeCol) {
        if (clickedTile != null) {
            clickedPiece = boardArray.get(clickedDimension).board[clickedTile.getRow()][clickedTile.getCol()].getPiece();
            if (clickedPiece != null) {
                if (isValidMove(clickedPiece, board[activeRow][activeCol], clickedDimension)) {
                    setPiece(clickedPiece, activeRow, activeCol, this.boardNum);
                    releasePiece(activeRow, activeCol);
                    whoseTurn = (whoseTurn.equals("white")) ? "black" : "white";
                }
            }
        }
    }

    //TODO: make it account for all boards
    protected void releasePiece(int curRow, int curCol) {
        if (!(clickedTile.getRow() == curRow && clickedTile.getCol() == curCol)) {
            boardArray.get(clickedDimension).board[clickedTile.getRow()][clickedTile.getCol()].setPiece(null);
            boardArray.get(clickedDimension).board[clickedTile.getRow()][clickedTile.getCol()].setText("");
            //process the pieces array
            Iterator itr = pieceArray.iterator();
            while (itr.hasNext()) {
                Piece piece = (Piece) itr.next();
                if (piece.row == clickedTile.getRow() && piece.col == clickedTile.getCol()) {
                    piece.row = curRow;
                    piece.col = curCol;
                }
            }
        }
    }

    //TODO: make it account for all boards
    protected void setPiece(Piece clickedPiece, int activeRow, int activeCol, int boardNum) {
        boardArray.get(boardNum).getBoard()[activeRow][activeCol].setPiece(clickedPiece);
        boardArray.get(boardNum).board[activeRow][activeCol].getPiece().setRow(activeCol);
        boardArray.get(boardNum).board[activeRow][activeCol].getPiece().setCol(activeRow);
        boardArray.get(boardNum).board[activeRow][activeCol].setText(clickedPiece.getIcon());
    }


    protected void placePieces(int col, int row) {
        switch (row) {
            case 1:
            case 6:
                initPawn(col, row);
                break;
            case 0:
            case 7:
                initRook(col, row);
                initKnight(col, row);
                initBishop(col, row);
                initKing(col, row);
                initQueen(col, row);
                break;
            default:
                break;
        }
    }

    private void initPawn(int col, int row) {
        if (row == 1) {
            Piece whitePawn = new Pawn("white", row, col);
            board[col][row].setText(whitePawn.getIcon());
            board[col][row].setPiece(whitePawn);
            pieceArray.add(whitePawn);
        } else {
            Piece blackPawn = new Pawn("black", row, col);
            board[col][row].setText(blackPawn.getIcon());
            board[col][row].setPiece(blackPawn);
            pieceArray.add(blackPawn);
        }
    }

    private void initRook(int col, int row) {
        if (row == 0 && (col == 0 || col == 7)) {
            Piece whiteRook = new Rook("white", row, col);
            board[col][row].setText(whiteRook.getIcon());
            board[col][row].setPiece(whiteRook);
            pieceArray.add(whiteRook);
        }
        if (row == 7 && (col == 0 || col == 7)) {
            Piece blackRook = new Rook("black", row, col);
            board[col][row].setText(blackRook.getIcon());
            board[col][row].setPiece(blackRook);
            pieceArray.add(blackRook);
        }
    }

    private void initKnight(int col, int row) {
        if (row == 0 && (col == 1 || col == 6)) {
            Piece whiteKnight = new Knight("white", row, col);
            board[col][row].setText(whiteKnight.getIcon());
            board[col][row].setPiece(whiteKnight);
            pieceArray.add(whiteKnight);
        }
        if (row == 7 && (col == 1 || col == 6)) {
            Piece blackKnight = new Knight("black", row, col);
            board[col][row].setText(blackKnight.getIcon());
            board[col][row].setPiece(blackKnight);
            pieceArray.add(blackKnight);
        }
    }

    private void initBishop(int col, int row) {
        if (row == 0 && (col == 2 || col == 5)) {
            Piece whiteBishop = new Bishop("white", row, col);
            board[col][row].setText(whiteBishop.getIcon());
            board[col][row].setPiece(whiteBishop);
            pieceArray.add(whiteBishop);
        }
        if (row == 7 && (col == 2 || col == 5)) {
            Piece blackBishop = new Bishop("black", row, col);
            board[col][row].setText(blackBishop.getIcon());
            board[col][row].setPiece(blackBishop);
            pieceArray.add(blackBishop);
        }
    }

    private void initKing(int col, int row) {
        if (row == 0 && (col == 4)) {
            Piece whiteKing = new King("white", row, col);
            board[col][row].setText(whiteKing.getIcon());
            board[col][row].setPiece(whiteKing);
            pieceArray.add(whiteKing);
        }
        if (row == 7 && (col == 4)) {
            Piece blackKing = new King("black", row, col);
            board[col][row].setText(blackKing.getIcon());
            board[col][row].setPiece(blackKing);
            pieceArray.add(blackKing);
        }
    }

    private void initQueen(int col, int row) {
        if (row == 0 && (col == 3)) {
            Piece whiteQueen = new Queen("white", row, col);
            board[col][row].setText(whiteQueen.getIcon());
            board[col][row].setPiece(whiteQueen);
            pieceArray.add(whiteQueen);
        }
        if (row == 7 && (col == 3)) {
            Piece blackQueen = new Queen("black", row, col);
            board[col][row].setText(blackQueen.getIcon());
            board[col][row].setPiece(blackQueen);
            pieceArray.add(blackQueen);
        }
    }
}
