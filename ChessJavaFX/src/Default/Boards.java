package Default;

import Pieces.Piece;
import javafx.scene.layout.GridPane;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

class Boards extends GridPane implements Serializable {
    public static List<Board> boardArray = new ArrayList<>();
    public static String whoseTurn = "white";
    public List<Piece> pieceArray = new ArrayList();
    public static int  clickedDimension;
    public static Tile clickedTile;
    public static Piece clickedPiece;
    public static int masterActiveCol;
    public static int masterActiveRow;

    Boards() {
        super();
    }
}
