package kiosk;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.net.*;
import java.io.*;


public class Contoller {
    @FXML private GridPane gridPane;

    public void setSeat(Table table){
        Integer minRow = 0;
        Integer maxRow = 0;
        switch (table.getnPersons()){
            case 1:
            case 2:
                minRow = 0;
                maxRow = 1;
                break;
            case 3:
            case 4:
                minRow = 2;
                maxRow = 3;
                break;
            case 5:
            case 6:
                minRow = 4;
                maxRow = 5;
                break;
            case 7:
            case 8:
                minRow = 6;
                maxRow = 7;
                break;
            case 9:
            case 10:
                minRow = 8;
                maxRow = 9;
                break;
        }
        for(int row = minRow; row < maxRow; row++) {
            for(int col = 1; col < 5; col++) {
                Node node = this.getNodeByRowColumnIndex(row,col);
            }
        }


    }

    public Node getNodeByRowColumnIndex (final int row, final int column) {
        Node result = null;
        ObservableList<Node> childrens = this.gridPane.getChildren();

        for (Node node : childrens) {
            if(this.gridPane.getRowIndex(node) == row && this.gridPane.getColumnIndex(node) == column) {
                result = node;
                break;
            }
        }

        return result;
    }

}
