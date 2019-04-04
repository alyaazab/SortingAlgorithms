package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class Controller {

    public Controller(){

    }

    int[] array;


    @FXML
    public Pane pane;

    @FXML
    public Slider sliderSpeed;

    @FXML
    public Button btnBubble, btnInsertion, btnSelection, btnMerge, btnQuick, btnHeap;

    GraphicalSort sort = new GraphicalSort();


    @FXML
    public void initialize() {

//        PlotLineGraph.plotTimeComplexity();


        array = RandomArrays.generateRandomArray(20, 0, 100);
//        int[] array = {90,70,60,50,40,100,120,30,170,20};
//        GraphicalSort sort = new GraphicalSort();

        System.out.println("pane children size = " + pane.getChildren().size());
        for (int i = 0; i < array.length; i++) {
            Rectangle rectangle = new Rectangle();
            rectangle.setFill(Color.BLUE);
            rectangle.setWidth(30);
            rectangle.setHeight(array[i]);
            System.out.println(rectangle.getHeight());
            rectangle.setX(50 + 40 * i);
            rectangle.setY(50);
            rectangle.setId(String.valueOf(i));
            pane.getChildren().add(rectangle);
        }

//        array = sort.quickSort(array, 0, array.length - 1, pane);
//        sort.sequentialTransition.play();

//        sort.mergeSort(array, 0, array.length-1, pane);
//        sort.sequentialTransition.play();
        sort.printArray(array);

    }

    public void onBtnBubbleClick(ActionEvent actionEvent) {
        sort.bubbleSort(array, pane);
    }

    public void onBtnInsertionClick(ActionEvent actionEvent) {
    }


    public void onBtnSelectionClick(ActionEvent actionEvent) {
    }

    public void onBtnMergeClick(ActionEvent actionEvent) {
    }

    public void onBtnQuickClick(ActionEvent actionEvent) {
    }

    public void onBtnHeapClick(ActionEvent actionEvent) {
    }

    public void onBtnPlayClick(ActionEvent actionEvent) {
        sort.sequentialTransition.play();
    }

    public void onBtnPauseClick(ActionEvent actionEvent) {
        sort.sequentialTransition.pause();
    }
}
