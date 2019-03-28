package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

    @FXML
    public Pane pane;

    @FXML
    public Button btnBubble, btnInsertion, btnSelection, btnMerge, btnQuick, btnHeap;


    @FXML
    public void initialize() {

//        int[] array = RandomArrays.generateRandomArray(10, 0, 100);
////        int[] array = {90,70,60,50,40,100,120,30,170,20};
//        GraphicalSort sort = new GraphicalSort();
//
//        System.out.println("pane children size = " + pane.getChildren().size());
//        for(int i=0; i<array.length; i++)
//        {
//            Rectangle rectangle = new Rectangle();
//            rectangle.setFill(Color.BLUE);
//            rectangle.setWidth(30);
//            rectangle.setHeight(array[i]);
//            System.out.println(rectangle.getHeight());
//            rectangle.setX(50 + 40*i);
//            rectangle.setY(0);
//            rectangle.setId(String.valueOf(i));
//            pane.getChildren().add(rectangle);
//        }
//
//          array = sort.quickSort(array, 0, array.length-1, pane);
//        sort.sequentialTransition.play();
//          sort.printArray(array);

//        double[] x = {1,2,3,4,5,6,7};
//        double[] y = {1,2,3,4,5,6,7};
//        for(int i =0; i<y.length;++i) {
//            y[i] = x[i]*x[i];
//        }

        Sort sort = new Sort();


        final XYSeries bubbleSort = new XYSeries("Bubble");
        final XYSeries insertionSort = new XYSeries("Insertion");
        final XYSeries selectionSort = new XYSeries("Selection");

        for(int i=10; i<100; i++)
        {
            int[] array1 = RandomArrays.generateRandomArray(i);
            int[] array2 = Arrays.copyOf(array1, i);
            int[] array3 = Arrays.copyOf(array1, i);

            long startTime = System.nanoTime();
            sort.bubbleSort(array1);
            long endTime   = System.nanoTime();
            long totalTime = endTime - startTime;
            bubbleSort.add(i, totalTime);

            startTime = System.nanoTime();
            sort.insertionSort(array2);
            endTime   = System.nanoTime();
            totalTime = endTime - startTime;
            insertionSort.add(i, totalTime);

            startTime = System.nanoTime();
            sort.selectionSort(array3);
            endTime   = System.nanoTime();
            totalTime = endTime - startTime;
            selectionSort.add(i, totalTime);

        }

        final XYSeriesCollection dataset = new XYSeriesCollection();
        dataset.addSeries(bubbleSort);
        dataset.addSeries(insertionSort);
        dataset.addSeries(selectionSort);

//        final XYSeries firefox = new XYSeries( "Firefox" );
//        firefox.add( 1.0 , 1.0 );
//        firefox.add( 2.0 , 4.0 );
//        firefox.add( 3.0 , 3.0 );
//
//        final XYSeries chrome = new XYSeries( "Chrome" );
//        chrome.add( 1.0 , 4.0 );
//        chrome.add( 2.0 , 5.0 );
//        chrome.add( 3.0 , 6.0 );
//
//        final XYSeries iexplorer = new XYSeries( "InternetExplorer" );
//        iexplorer.add( 3.0 , 4.0 );
//        iexplorer.add( 4.0 , 5.0 );
//        iexplorer.add( 5.0 , 4.0 );

//        final XYSeriesCollection dataset = new XYSeriesCollection( );
//        dataset.addSeries( firefox );
//        dataset.addSeries( chrome );
//        dataset.addSeries( iexplorer );

        JFreeChart xylineChart = ChartFactory.createXYLineChart(
                "Time Complexity",
                "Size",
                "Time",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        int width = 640;   /* Width of the image */
        int height = 480;  /* Height of the image */
        File XYChart = new File( "XYLineChart.jpeg" );
        try {
            ChartUtilities.saveChartAsJPEG( XYChart, xylineChart, width, height);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public void onBtnBubbleClick(ActionEvent actionEvent) {
        //do bubble sort stuff
    }

}
