package Default;

import Pieces.Piece;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.io.*;
import java.util.ArrayList;

public class Main extends Application {


    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        HBox topMenu = new HBox();
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(topMenu);

//        Three boards
        Boards masterBoard = new Boards();
        masterBoard.boardArray.add(new Board(0));
        masterBoard.boardArray.add(new Board(1));
        masterBoard.boardArray.add(new Board(2));

        borderPane.setLeft(masterBoard.boardArray.get(0));
        borderPane.setCenter(masterBoard.boardArray.get(1));
        borderPane.setRight(masterBoard.boardArray.get(2));

        Scene scene = new Scene(borderPane);


//        serializing
        Button saveButton = new Button("Save");
        saveButton.setOnAction(e -> {
            try {
//                pieces
                FileOutputStream fos = new FileOutputStream("test.ser");
                ObjectOutputStream oos = new ObjectOutputStream(fos);

                FileOutputStream fosC = new FileOutputStream("testC.ser");
                ObjectOutputStream oosC = new ObjectOutputStream(fosC);

                FileOutputStream fosR = new FileOutputStream("testR.ser");
                ObjectOutputStream oosR = new ObjectOutputStream(fosR);
//                turn
                FileOutputStream fosturn = new FileOutputStream("turn.ser");
                ObjectOutputStream oosturn = new ObjectOutputStream(fosturn);

                ArrayList<Piece> pieces = new ArrayList();
                ArrayList<Piece> piecesC = new ArrayList();
                ArrayList<Piece> piecesR = new ArrayList();
                String turn = masterBoard.boardArray.get(0).whoseTurn;

                for (int i = 0; i < 8; i++) {
                    for (int j = 0; j < 8; j++) {
                        if (masterBoard.boardArray.get(0).getBoard()[i][j].isOccupied()) {
                            pieces.add(masterBoard.boardArray.get(0).getBoard()[i][j].getPiece());
                        }
                        if (masterBoard.boardArray.get(1).getBoard()[i][j].isOccupied()) {
                            piecesC.add(masterBoard.boardArray.get(1).getBoard()[i][j].getPiece());
                        }
                        if (masterBoard.boardArray.get(2).getBoard()[i][j].isOccupied()) {
                            piecesR.add(masterBoard.boardArray.get(2).getBoard()[i][j].getPiece());
                        }
                    }
                }
//                turn
                oosturn.writeObject(turn);
                oosturn.flush();
                oosturn.close();
//                pieces
                oos.writeObject(pieces);
                oosC.writeObject(piecesC);
                oosR.writeObject(piecesR);
                oos.flush();
                oos.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        });

        Button loadButton = new Button("Load");
        loadButton.setOnAction(e -> {
            try {
//                turn
                FileInputStream fis = new FileInputStream("test.ser");
                ObjectInputStream oin = new ObjectInputStream(fis);

                FileInputStream fisC = new FileInputStream("testC.ser");
                ObjectInputStream oinC = new ObjectInputStream(fisC);

                FileInputStream fisR = new FileInputStream("testR.ser");
                ObjectInputStream oinR = new ObjectInputStream(fisR);

                FileInputStream fisturn = new FileInputStream("turn.ser");
                ObjectInputStream ointurn = new ObjectInputStream(fisturn);

                ArrayList<Piece> pieces = new ArrayList();
                ArrayList<Piece> piecesC = new ArrayList();
                ArrayList<Piece> piecesR = new ArrayList();

                pieces = (ArrayList) oin.readObject();
                piecesC = (ArrayList) oinC.readObject();
                piecesR = (ArrayList) oinR.readObject();

                //TODO: make it x3
                masterBoard.boardArray.get(0).redraw(pieces);
                masterBoard.boardArray.get(1).redraw(piecesC);
                masterBoard.boardArray.get(2).redraw(piecesR);
                masterBoard.boardArray.get(0).whoseTurn = (String) ointurn.readObject();
            } catch (IOException | ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        });

        topMenu.getChildren().addAll(saveButton, loadButton);

        scene.getStylesheets().add("style/styles.css");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
