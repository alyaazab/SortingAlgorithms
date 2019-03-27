package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
        int[] array = {90,70,60,50,40,100,120,30,170,20};
        GraphicalSort sort = new GraphicalSort();

        System.out.println("pane children size = " + pane.getChildren().size());
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

          sort.bubbleSort(array, pane);

    }

    public void onBtnBubbleClick(ActionEvent actionEvent) {
        //do bubble sort stuff
    }

}
