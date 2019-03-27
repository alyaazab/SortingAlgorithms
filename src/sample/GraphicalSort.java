package sample;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;


public class GraphicalSort {

    SequentialTransition sequentialTransition = new SequentialTransition();

    Rectangle rect1 = null;
    Rectangle rect2 = null;
    int index1 = 0, index2=0;

    Pane pane;


    private int getCurrentRectangle(int index){
        for (int k = 0; k < pane.getChildren().size(); k++) {
            Node node = pane.getChildren().get(k);
            System.out.println("this node is a " + node.getId());
            if(node instanceof Rectangle) {
                if(Integer.parseInt(node.getId())==index)
                {
                    return k;
                }
            }
        }
        return 0;
    }

    private void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        index1 = getCurrentRectangle(i);
        index2 = getCurrentRectangle(j);

        rect1 = (Rectangle) pane.getChildren().get(index1);
        rect2 = (Rectangle) pane.getChildren().get(index2);


        TranslateTransition tt1 = addPositiveTranslateTransition(rect1);
        TranslateTransition tt2 = addNegativeTranslateTransition(rect2);
        FillTransition ft1 = addFillTransition(rect1);
        FillTransition ft2 = addFillTransition(rect2);
        FillTransition rft1 = removeFillTransition(rect1);
        FillTransition rft2 = removeFillTransition(rect2);

        pane.getChildren().get(index1).setId(j+"");
        pane.getChildren().get(index2).setId(i+"");

        Node temp1 = pane.getChildren().get(index1);
        pane.getChildren().remove(index1);
        pane.getChildren().add(index2, temp1);

        sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
        sequentialTransition.getChildren().add(new ParallelTransition(tt1, tt2));
        sequentialTransition.getChildren().add(new ParallelTransition(rft1, rft2));
        sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));
    }


    private TranslateTransition addPositiveTranslateTransition(Rectangle rectangle){

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), rectangle);
        translateTransition.setByX(40);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        return translateTransition;

    }

    private TranslateTransition addNegativeTranslateTransition(Rectangle rectangle){

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), rectangle);
        translateTransition.setByX(-40);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        return translateTransition;

    }

    private FillTransition addFillTransition(Rectangle rectangle){

        FillTransition fillTransition = new FillTransition();
        fillTransition.setShape(rectangle);
        fillTransition.setDuration(new Duration(500));
        fillTransition.setToValue(Color.GOLD);
        fillTransition.setCycleCount(1);
        fillTransition.setAutoReverse(true);

        return fillTransition;

    }


    private FillTransition removeFillTransition(Rectangle rectangle){

        FillTransition removeFillTransition = new FillTransition();
        removeFillTransition.setShape(rectangle);
        removeFillTransition.setDuration(new Duration(500));
        removeFillTransition.setToValue(Color.BLUE);
        removeFillTransition.setCycleCount(1);
        removeFillTransition.setAutoReverse(true);

        return removeFillTransition;

    }




    public int[] bubbleSort(int[] array, Pane pane){
        printArray(array);
        int indexA = 0, indexB=0;
        Rectangle rectangleA, rectangleB;
        FillTransition fillTransitionA, fillTransitionB, removeFillTransitionA, removeFillTransitionB;
        this.pane = pane;
        int sorted;

        for(int i=0; i<array.length-1; i++)
        {
            sorted = 1;
            for(int j=0; j<array.length-i-1; j++)
            {
                if(array[j] > array[j+1])
                {
                    sorted = 0;
                    swap(j, j+1, array);
                }
                else
                {
                    indexA = getCurrentRectangle(j);
                    indexB = getCurrentRectangle(j+1);
                    rectangleA = (Rectangle) pane.getChildren().get(indexA);
                    fillTransitionA = addFillTransition(rectangleA);
                    rectangleB = (Rectangle) pane.getChildren().get(indexB);
                    fillTransitionB = addFillTransition(rectangleB);
                    sequentialTransition.getChildren().add(new ParallelTransition(fillTransitionA, fillTransitionB));
                    sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));
                    removeFillTransitionA = removeFillTransition(rectangleA);
                    removeFillTransitionB = removeFillTransition(rectangleB);

                    sequentialTransition.getChildren().add(new ParallelTransition(removeFillTransitionA, removeFillTransitionB));
                    sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));


                }

            }

            if(sorted==1)
                break;

            printArray(array);
        }

        System.out.println("seq size = " + sequentialTransition.getChildren().size());
        sequentialTransition.play();
        return array;
    }


    public void printArray(int[] array){

        for(int i=0; i<array.length; i++)
            System.out.print(array[i] + " ");

        System.out.println();
    }
    
}
