package sample;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Controller {

    public Controller(){

    }

    @FXML
    public Pane pane;

    @FXML
    public Button btnBubble, btnInsertion, btnSelection, btnMerge, btnQuick, btnHeap;


    @FXML
    public void initialize() {

//        int[] array = RandomArrays.generateRandomArray(10, 0, 100);
        int[] array = {90,70,50,40,5,8,6,3,3,1};
        GraphicalSort sort = new GraphicalSort();

        for(int i=0; i<array.length; i++)
        {
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.BLUE);
            rectangle.setWidth(30);
            rectangle.setHeight(array[i]);
            System.out.println(rectangle.getHeight());
            rectangle.setX(50 + 40*i);
            rectangle.setY(0);
            rectangle.setId(String.valueOf(i));
            pane.getChildren().add(rectangle);
        }

//
//
//        Rectangle rectangle1 = new Rectangle();
//        rectangle1.setFill(Color.BLUE);
//        rectangle1.setWidth(30);
//        rectangle1.setHeight(90);
//        System.out.println(rectangle1.getHeight());
//        rectangle1.setX(50);
//        rectangle1.setY(0);
//        pane.getChildren().add(rectangle1);
//
//        Rectangle rectangle = new Rectangle();
//        rectangle.setFill(Color.BLUE);
//        rectangle.setWidth(30);
//        rectangle.setHeight(40);
//        System.out.println(rectangle.getHeight());
//        rectangle.setX(50 + 40*1);
//        rectangle.setY(0);
//        pane.getChildren().add(rectangle);
//
//        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(3), rectangle1);
//        translateTransition.setByX(40);
//        translateTransition.setCycleCount(5);
//        translateTransition.setAutoReverse(true);
//
//        translateTransition.play();

          sort.bubbleSort(array, pane);

    }

    public void onBtnBubbleClick(ActionEvent actionEvent) {
        //do bubble sort stuff
    }

    public void swap(int i, int j, int[] array){

    }
}
