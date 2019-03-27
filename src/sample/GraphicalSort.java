package sample;

import javafx.animation.PathTransition;
import javafx.animation.TranslateTransition;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.control.Control;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;
import org.w3c.dom.css.Rect;

import java.awt.*;


public class GraphicalSort {

    Pane pane;
    private void swap(int i, int j, int[] array) {

        System.out.println("swapping:");
        System.out.println("i = " + i);
        System.out.println("j = " + j);

        Rectangle rect1 = null;
        Rectangle rect2 = null;
        int index1 = 0, index2=0;


        for (int k = 0; k < pane.getChildren().size(); k++) {
            Node node = pane.getChildren().get(k);

            if(node instanceof Rectangle) {
                if(Integer.parseInt(node.getId())==i)
                {
                    rect1 = (Rectangle) node;
                    index1 = k;

                    System.out.println("rect 1 id = " + rect1.getId() + "    node id: " + node.getId());
                    System.out.println("k = " + k);
                }
                if(Integer.parseInt(node.getId())==j)
                {
                    rect2 = (Rectangle) node;
                    index2=k;
                    System.out.println("rect 1 id = " + rect2.getId() + "    node id: " + node.getId());
                    System.out.println("k = " + k);
                }
            }
        }

//        for(Node node: pane.getChildren())
//        {
//            if(node instanceof Rectangle) {
//                if(Integer.parseInt(node.getId())==i)
//                {
//                    System.out.println("move this rect");
//                    rect1 = (Rectangle) node;
//                    node.setId(j+"");
//                }
//                if(Integer.parseInt(node.getId())==j)
//                {
//                    rect2 = (Rectangle) node;
//                    node.setId(i+"");
//
//                }
//            }
//        }

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(1), rect1);
        translateTransition.setByX(40);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        translateTransition.play();

        TranslateTransition translateTransition2 = new TranslateTransition(Duration.seconds(1), rect2);
        translateTransition2.setByX(-40);
        translateTransition2.setCycleCount(1);
        translateTransition2.setAutoReverse(true);

        translateTransition2.play();

        pane.getChildren().get(index1).setId(j+"");
        pane.getChildren().get(index2).setId(i+"");

        Node temp1 = pane.getChildren().get(index1);
        pane.getChildren().remove(index1);
        pane.getChildren().add(index2, temp1);

        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //Bubble Sort
    //Time Complexity = O(n^2)
    //Best Case = O(n) ---> when array is already sorted
    //Worst Case occurs when array is reverse sorted
    //Sorted in place
    //End of array is always sorted ("i" elements at the end of the array)
    public int[] bubbleSort(int[] array, Pane pane){
        printArray(array);

        this.pane = pane;
        int temp;
        int sorted;
        int numOfSwaps=0;

        //length-1 because the array is already sorted when we get to the last pass
        for(int i=0; i<array.length-1; i++)
        {
            sorted = 1;

            //length-1 to avoid array index out of bounds exception
            //length-1-i because the last "i" elements are sorted
            for(int j=0; j<array.length-i-1; j++)
            {
                //if any 2 consecutive elements are in the wrong order, swap them
                if(array[j] > array[j+1])
                {
                    sorted = 0;
                    swap(j, j+1, array);
                    numOfSwaps++;
                    if(numOfSwaps==2)
                        break;
                }
            }
            if(numOfSwaps==2)
                break;
            if(sorted==1)
                break;

            printArray(array);
        }
        return array;
    }

    public void printArray(int[] array){

        for(int i=0; i<array.length; i++)
            System.out.print(array[i] + " ");

        System.out.println();
    }


}
