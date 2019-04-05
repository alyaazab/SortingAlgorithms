package sample;

import javafx.animation.*;
import javafx.scene.Node;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;



public class GraphicalSort {

    SequentialTransition sequentialTransition = new SequentialTransition();
    Pane pane;
    int index1 = 0, index2=0;


    private int getCurrentRectangleIndex(int index){
        for (int k = 0; k < pane.getChildren().size(); k++) {
            Node node = pane.getChildren().get(k);

            if(node instanceof Rectangle && Integer.parseInt(node.getId())==index)
                    return k;
        }
        return 0;
    }


    private Rectangle getCurrentRectangle(int i){
        int index = getCurrentRectangleIndex(i);
        return (Rectangle) pane.getChildren().get(index);
    }

    private void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;

        Rectangle rect1 = getCurrentRectangle(i);
        Rectangle rect2 = getCurrentRectangle(j);

        TranslateTransition tt1 = addXTranslateTransition(rect1, i, j, 1);
        TranslateTransition tt2 = addXTranslateTransition(rect2, i, j, -1);

        swapTwoNodes(i, j);

        sequentialTransition.getChildren().add(new ParallelTransition(tt1, tt2));
    }

    private void swapTwoNodes(int i, int j){
        index1 = getCurrentRectangleIndex(i);
        index2 = getCurrentRectangleIndex(j);


        pane.getChildren().get(index1).setId(j+"");
        pane.getChildren().get(index2).setId(i+"");
        Node temp1 = pane.getChildren().get(index1);
        pane.getChildren().remove(index1);
        pane.getChildren().add(index2, temp1);
    }

    private TranslateTransition addXTranslateTransition(Rectangle rectangle, int i, int j, int sign){

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5/Controller.animationSpeed), rectangle);
        translateTransition.setByX(sign*40* (j-i));
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        return translateTransition;

    }

    private TranslateTransition addYTranslateTransition(Rectangle rectangle, int sign){

        TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5/Controller.animationSpeed), rectangle);
        translateTransition.setByY(sign*100);
        translateTransition.setCycleCount(1);
        translateTransition.setAutoReverse(true);

        return translateTransition;

    }



    private FillTransition addFillTransition(Rectangle rectangle, Color color){

        FillTransition fillTransition = new FillTransition();
        fillTransition.setShape(rectangle);
        fillTransition.setDuration(Duration.seconds(1/Controller.animationSpeed));
        fillTransition.setToValue(color);
        fillTransition.setCycleCount(1);
        fillTransition.setAutoReverse(true);

        return fillTransition;

    }


    public int[] bubbleSort(int[] array, Pane pane){
        Rectangle rectangleA, rectangleB;
        FillTransition ft1, ft2, ft;
        this.pane = pane;


        for(int i=0; i<array.length-1; i++)
        {
            for(int j=0; j<array.length-i-1; j++)
            {
                rectangleA = getCurrentRectangle(j);
                rectangleB = getCurrentRectangle(j+1);
                ft1 = addFillTransition(rectangleA, Color.GOLD);
                ft2 = addFillTransition(rectangleB, Color.GOLD);
                sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));

                if(array[j] > array[j+1])
                    swap(j, j+1, array);

                ft1 = addFillTransition(rectangleA, Color.BLUE);
                ft2 = addFillTransition(rectangleB, Color.BLUE);
                sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
                sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5/Controller.animationSpeed)));
            }

            ft = addFillTransition(getCurrentRectangle(array.length-i-1), Color.BLACK);
            sequentialTransition.getChildren().add(ft);
            sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5/Controller.animationSpeed)));
        }
        sequentialTransition.getChildren().add(addFillTransition(getCurrentRectangle(0), Color.BLACK));

        return array;
    }


    public void printArray(int[] array){

        for(int i=0; i<array.length; i++)
            System.out.print(array[i] + " ");

        System.out.println();
    }

    public int[] selectionSort(int[] array, Pane pane){
        this.pane = pane;
        int minIndex=0;
        Rectangle rect1, rect2;
        FillTransition ft1, ft2;

        for(int i=0; i<array.length-1; i++)
        {
            minIndex = i;

            for(int j=i+1; j<array.length; j++)
            {
                //make minimum rect red
                //make current rect yellow to compare with
                rect1 = getCurrentRectangle(minIndex);
                ft1 = addFillTransition(rect1, Color.RED);

                rect2 = getCurrentRectangle(j);
                ft2 = addFillTransition(rect2, Color.GOLD);
                sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
                sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));


                //if current rect < min rect
                //make new min rect red
                //make old min rect blue again
                if(array[j] < array[minIndex])
                {
                    ft1 = addFillTransition(getCurrentRectangle(j), Color.RED);
                    ft2 = addFillTransition(getCurrentRectangle(minIndex), Color.BLUE);
                    sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
                    minIndex = j;
                }
                else
                {
                    ft2 = addFillTransition(getCurrentRectangle(j), Color.BLUE);
                    sequentialTransition.getChildren().add(ft2);
                }

                sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));

            }

            ft1 = addFillTransition(getCurrentRectangle(i), Color.RED);
            sequentialTransition.getChildren().add(ft1);
            swap(i, minIndex, array);
            ft1 = addFillTransition(getCurrentRectangle(minIndex), Color.BLUE);
            ft2 = addFillTransition(getCurrentRectangle(i), Color.BLACK);
            sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
            sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5/Controller.animationSpeed)));

            printArray(array);
        }
        sequentialTransition.getChildren().add(addFillTransition(getCurrentRectangle(array.length-1), Color.BLACK));
        return array;
    }


    public int[] heapsort(int[] array, Pane pane){

        this.pane = pane;
        int heapsize = array.length;

        buildHeap(array);

        for(int i=0; i<array.length; i++)
            heapsize = deleteMax(array, heapsize);

        return array;
    }

    private int deleteMax(int[] array, int heapsize) {
        FillTransition ft1 = addFillTransition(getCurrentRectangle(0), Color.GOLD);
        FillTransition ft2 = addFillTransition(getCurrentRectangle(heapsize-1), Color.GOLD);
        sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));

        swap(0, heapsize-1, array);

        ft1 = addFillTransition(getCurrentRectangle(0), Color.BLUE);
        ft2 = addFillTransition(getCurrentRectangle(heapsize-1), Color.BLACK);
        sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
        sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));

        heapsize--;
        maxHeapify(array, heapsize, 0);
        return heapsize;
    }

    private int[] buildHeap(int[] array){
        for(int i = array.length/2 -1; i>=0; i--)
            maxHeapify(array, array.length, i);

        return array;
    }

    private void maxHeapify(int[] array, int length, int i) {
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i +2;

        if(left < length && array[left] > array[largest])
            largest = left;

        if(right < length && array[right] > array[largest])
            largest = right;

        if(largest != i)
        {
            FillTransition ft1 = addFillTransition(getCurrentRectangle(i), Color.GOLD);
            FillTransition ft2 = addFillTransition(getCurrentRectangle(largest), Color.GOLD);
            sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
            swap(i, largest, array);
            ft1 = addFillTransition(getCurrentRectangle(i), Color.BLUE);
            ft2 = addFillTransition(getCurrentRectangle(largest), Color.BLUE);
            sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
            sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));
            maxHeapify(array, length, largest);
        }
    }


    public int[] quickSort(int[] array, int low, int high, Pane pane){

        this.pane = pane;

        //make sure that there is more than 1 element in subarray
        if(low < high)
        {
            //partition the array to find the position of the pivot
            int pivotIndex = partition(array, low, high);
            //sort the left and right subarrays recursively
            quickSort(array, low, pivotIndex-1, pane);
            quickSort(array, pivotIndex+1, high, pane);
        }

        return array;
    }


    private int partition(int[] array, int low, int high){

        //let the last element be the pivot
        int pivot = array[high];
        int i = low-1;
        FillTransition ft1, ft2;

        sequentialTransition.getChildren().add(addFillTransition(getCurrentRectangle(high), Color.RED));

        //iterate from beginning to end-1 of subarray
        for(int j=low; j<high; j++)
        {
            //place values lower than the pivot at the beginning of the array
            //values greater than the pivot will be moved to the end of the array

            if(array[j] <= pivot)
            {
                i++;
                ft1 = addFillTransition(getCurrentRectangle(i), Color.GOLD);
                ft2 = addFillTransition(getCurrentRectangle(j), Color.GOLD);
                sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
                swap(i, j, array);
                ft1 = addFillTransition(getCurrentRectangle(i), Color.BLUE);
                ft2 = addFillTransition(getCurrentRectangle(j), Color.BLUE);
                sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
                sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));
            }

        }

        //place the pivot in its correct position
        ft1 = addFillTransition(getCurrentRectangle(i+1), Color.GOLD);
        ft2 = addFillTransition(getCurrentRectangle(high), Color.GOLD);
        sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
        swap(i+1, high, array);
        ft1 = addFillTransition(getCurrentRectangle(high), Color.BLUE);
        ft2 = addFillTransition(getCurrentRectangle(i+1), Color.BLACK);

        sequentialTransition.getChildren().add(new ParallelTransition(ft1, ft2));
        sequentialTransition.getChildren().add(new PauseTransition(Duration.seconds(0.5)));


        //return the index of the pivot
        return i+1;
    }


    public int[] insertionSort(int[] array, Pane pane){

        this.pane = pane;
        TranslateTransition tt;
        FillTransition ft, ft1;

        //array = {7, 3, 5, 4, 8, 0, 9, 6, 1, 2}

        //place the new element in sorted sub-array

        int element = 0, j=0, temp=0;

        ft = addFillTransition(getCurrentRectangle(0), Color.BLACK);
        sequentialTransition.getChildren().add(ft);


        for(int i=1; i<array.length; i++)
        {
            element = array[i];

            //to compare between array[i] and previous elements
            j=i-1;

            ft = addFillTransition(getCurrentRectangle(j+1), Color.RED);
            tt = addYTranslateTransition(getCurrentRectangle(j+1), 1);
            sequentialTransition.getChildren().add(ft);
            sequentialTransition.getChildren().add(tt);

            //make sure j is not out of bounds
            //as long as array[j] > element, we need to move the elements in sorted sub-array
            while(j>=0 && array[j]>element)
            {
                ft = addFillTransition(getCurrentRectangle(j), Color.GOLD);
                sequentialTransition.getChildren().add(ft);

                //move current element 1 place over
                // array[j+1] = array[j];
                swap(j+1, j, array);

                ft = addFillTransition(getCurrentRectangle(j+1), Color.BLACK);
                sequentialTransition.getChildren().add(ft);

                //decrement j to check the other elements in sorted sub-array
                j--;
            }

            tt = addYTranslateTransition(getCurrentRectangle(j+1), -1);
            ft = addFillTransition(getCurrentRectangle(j+1), Color.BLACK);
            sequentialTransition.getChildren().add(tt);
            sequentialTransition.getChildren().add(ft);

            printArray(array);
        }

        return array;
    }


    public int[] mergeSort(int[] array, int left, int right, Pane pane){

        this.pane = pane;
        //split the left side of the array until its size is 1
        //go back recursively and split the right side of the array
        if(left < right)
        {
            mergeSort(array, left, (left+right)/2, pane);
            mergeSort(array, (left+right)/2 +1, right, pane);
            merge(array, left, right);
        }

        return array;

    }

    private int[] merge(int[] array, int left, int right)
    {
        System.out.println("left = " + left + "     right = " + right);
        System.out.println("array is: ");
        printArray(array);

        int middle = (left+right)/2;
        int[] array1 = new int[middle-left+1];
        int[] array2 = new int[right-middle];
        TranslateTransition tt;
        FillTransition ft;


        //move elements of array down
        for(int i=left; i<=middle; i++)
        {
            array1[i-left] = array[i];
            sequentialTransition.getChildren().add(addYTranslateTransition(getCurrentRectangle(i), 1));
        }

        for(int i=middle+1; i<=right; i++)
        {
            array2[i - middle - 1] = array[i];
            sequentialTransition.getChildren().add(addYTranslateTransition(getCurrentRectangle(i), 1));
        }

        System.out.println("leftarr is ");
        printArray(array1);
        System.out.println("rightarr is ");
        printArray(array2);

        int i=left, j=0, k=0;

        while(j<array1.length && k<array2.length)
        {
            //move the smallest element up
            if(array1[j] <= array2[k])
            {
                sequentialTransition.getChildren().add(addYTranslateTransition(getCurrentRectangle(j+left), -1));
                sequentialTransition.getChildren().add(addXTranslateTransition(getCurrentRectangle(j+left), i, j+left, -1));

                array[i] = array1[j];

                j++;
            } else {
                sequentialTransition.getChildren().add(addYTranslateTransition(getCurrentRectangle(k+middle+1), -1));
                sequentialTransition.getChildren().add(addXTranslateTransition(getCurrentRectangle(k+middle+1), i, k+middle+1, -1));
                array[i] = array2[k];

                k++;
            }
            i++;
        }

        //move the other elements up
        while(j<array1.length)
        {
            sequentialTransition.getChildren().add(addYTranslateTransition(getCurrentRectangle(j+left), -1));
            sequentialTransition.getChildren().add(addXTranslateTransition(getCurrentRectangle(j+left), i, j+left, -1));
            swapTwoNodes(j+left, i);
            System.out.println("j = " + array1[j]);
            System.out.println("j + left= " + array[j+left]);

            array[i] = array1[j];
            i++;
            j++;
        }

        while(k<array2.length)
        {
            sequentialTransition.getChildren().add(addYTranslateTransition(getCurrentRectangle(k+middle+1), -1));
            sequentialTransition.getChildren().add(addXTranslateTransition(getCurrentRectangle(k+middle+1), i, k+middle+1, -1));
            swapTwoNodes(k+middle+1, i);
            System.out.println("k = " + array2[k]);
            System.out.println("k + mid+1= " + array[k+middle+1]);
            array[i] = array2[k];
            i++;
            k++;
        }

        return array;
    }


}
