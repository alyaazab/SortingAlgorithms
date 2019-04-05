package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.w3c.dom.css.Rect;

import java.util.Arrays;


public class Controller {

    public Controller(){

    }

    int[] array = {70, 10, 20, 80, 30, 90, 60, 100};



    @FXML
    public Pane pane;

    @FXML
    public Spinner spinnerArraySize;

    @FXML
    public Slider sliderSpeed;

    @FXML
    public Button btnBubble, btnInsertion, btnSelection, btnMerge, btnQuick, btnHeap, btnApply;

    GraphicalSort sort = new GraphicalSort();
    public static double animationSpeed;


    @FXML
    public void initialize() {

        SpinnerValueFactory<Integer> valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 20, 10);
        spinnerArraySize.setValueFactory(valueFactory);

    }

    public void resetPane(){
        for(int i=0; i<pane.getChildren().size(); i++)
        {
            Node node = pane.getChildren().get(i);
            if(node instanceof Rectangle)
            {
                pane.getChildren().remove(i);
                i--;
            }
        }

        System.out.println("pane children size = " + pane.getChildren().size());
        for (int i = 0; i < array.length; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.BLUE);
            rectangle.setWidth(30);
            rectangle.setHeight(array[i]);
            System.out.println(rectangle.getHeight());
            rectangle.setX(50 + 40 * i);
            rectangle.setY(120);
            rectangle.setId(String.valueOf(i));
            pane.getChildren().add(rectangle);
        }

    }

    public void onBtnApplyClick(ActionEvent actionEvent) {
//        array = RandomArrays.generateRandomArray((int) spinnerArraySize.getValue(), 1, 100);
//        array = {70, 10, 20, 80, 30, 90, 60, 100};
        resetPane();

    }

    public void onBtnBubbleClick(ActionEvent actionEvent) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        sort.sequentialTransition.stop();
        resetPane();
        sort.sequentialTransition.getChildren().remove(0, sort.sequentialTransition.getChildren().size()-1);

        animationSpeed = sliderSpeed.getValue();
        sort.bubbleSort(arrayCopy, pane);
    }

    public void onBtnInsertionClick(ActionEvent actionEvent) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        sort.sequentialTransition.stop();
        resetPane();

        sort.sequentialTransition.getChildren().remove(0, sort.sequentialTransition.getChildren().size()-1);

        animationSpeed = sliderSpeed.getValue();
        sort.insertionSort(arrayCopy, pane);
    }


    public void onBtnSelectionClick(ActionEvent actionEvent) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        sort.sequentialTransition.stop();
        resetPane();

        sort.sequentialTransition.getChildren().remove(0, sort.sequentialTransition.getChildren().size()-1);

        animationSpeed = sliderSpeed.getValue();
        sort.selectionSort(arrayCopy, pane);
    }

    public void onBtnMergeClick(ActionEvent actionEvent) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        sort.sequentialTransition.stop();
        resetPane();

        sort.sequentialTransition.getChildren().remove(0, sort.sequentialTransition.getChildren().size()-1);

        animationSpeed = sliderSpeed.getValue();
        sort.mergeSort(arrayCopy, 0, arrayCopy.length-1, pane);
    }

    public void onBtnQuickClick(ActionEvent actionEvent) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        sort.sequentialTransition.stop();
        resetPane();

        sort.sequentialTransition.getChildren().remove(0, sort.sequentialTransition.getChildren().size()-1);

        animationSpeed = sliderSpeed.getValue();
        sort.quickSort(arrayCopy, 0, arrayCopy.length-1, pane);
    }

    public void onBtnHeapClick(ActionEvent actionEvent) {
        int[] arrayCopy = Arrays.copyOf(array, array.length);

        sort.sequentialTransition.stop();
        resetPane();

        sort.sequentialTransition.getChildren().remove(0, sort.sequentialTransition.getChildren().size()-1);
        animationSpeed = sliderSpeed.getValue();
        sort.heapsort(arrayCopy, pane);
    }

    public void onBtnPlayClick(ActionEvent actionEvent) {
        sort.sequentialTransition.play();
    }

    public void onBtnPauseClick(ActionEvent actionEvent) {
        sort.sequentialTransition.pause();
    }
}
