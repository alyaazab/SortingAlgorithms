package sample;

public class Sort {

    public int[] quickSort(int[] array, int low, int high){

        //make sure that there is more than 1 element in subarray
        if(low < high)
        {
            //partition the array to find the position of the pivot
            int pivotIndex = partition(array, low, high);
            //sort the left and right subarrays recursively
            quickSort(array, low, pivotIndex-1);
            quickSort(array, pivotIndex+1, high);
        }

        return array;
    }


    private int partition(int[] array, int low, int high){

        //let the last element be the pivot
        int pivot = array[high];
        int i = low-1;

        //iterate from beginning to end-1 of subarray
        for(int j=low; j<high; j++)
        {
            //place values lower than the pivot at the beginning of the array
            //values greater than the pivot will be moved to the end of the array
            printArray(array);

            if(array[j] <= pivot)
            {
                i++;
                swap(i, j, array);
            }
        }

        //place the pivot in its correct position
        swap(i+1, high, array);

        //return the index of the pivot
        return i+1;
    }
    public int[] heapsort(int[] array){

        int heapsize = array.length;

        //given a randomized array, build a maxHeap to later sort it in ascending order
        buildHeap(array);

        //iterate over all of the elements
        //delete maximum element (place it at end of array)
        for(int i=0; i<array.length; i++)
            heapsize = deleteMax(array, heapsize);

        return array;
    }

    private int deleteMax(int[] array, int heapsize) {

        //swap the root with the last element of the heap
        swap(0, heapsize-1, array);
        //decrement the heapsize to indicate that the last element of the array is in its correct position
        heapsize--;
        //call maxheapify on the root to restore the maxheap's properties
        maxHeapify(array, heapsize, 0);
        return heapsize;
    }

    private int[] buildHeap(int[] array){

        //start at the last parent and go down to the first parent
        //maxHeapify each parent
        for(int i = array.length/2 -1; i>=0; i--)
            maxHeapify(array, array.length, i);

        return array;
    }

    private void maxHeapify(int[] array, int length, int i) {
        //find the largest node between the parent and its children
        int largest = i;
        int left = 2*i + 1;
        int right = 2*i +2;

        if(left < length && array[left] > array[largest])
            largest = left;

        if(right < length && array[right] > array[largest])
            largest = right;

        //if parent isn't the largest, swap it with its largest child
        //call maxHeapify on the largest element
        if(largest != i)
        {
            swap(i, largest, array);
            maxHeapify(array, length, largest);
        }
    }

    private void swap(int i, int j, int[] array) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }


    //Selection Sort
    //Time Complexity = O(n^2)
    //Sorted in place
    //Beginning of array is always sorted (elements from 0 to i)
    public int[] selectionSort(int[] array){
        //iterate over the array
        //select the smallest element
        //place it at the end of the sorted array

        int temp;
        int minIndex=0;

        for(int i=0; i<array.length-1; i++)
        {
            //set current element as minimum to compare it with other elements
            minIndex = i;
            for(int j=i+1; j<array.length; j++)
            {
                //if current element is less than minimum element, set minimum = current
                if(array[j] < array[minIndex])
                    minIndex = j;
            }

            //swap the current element with the minimum element
            temp = array[i];
            array[i] = array[minIndex];
            array[minIndex] = temp;

            printArray(array);
        }

        return array;
    }

    //Insertion Sort
    //Time Complexity = O(n^2)
    //Worst Case: array is reverse sorted
    //Best Case = O(n) ---> when array is already sorted
    //Sorted in place
    //Beginning of array is always sorted (elements from 0 to i)
    public int[] insertionSort(int[] array){

        //array = {7, 3, 5, 4, 8, 0, 9, 6, 1, 2}

        //place the new element in sorted sub-array

        int element = 0, j=0, temp=0;

        for(int i=1; i<array.length; i++)
        {
            //element = 3
            element = array[i];

            //to compare between array[i] and previous elements
            j=i-1;

            //make sure j is not out of bounds
            //as long as array[j] > element, we need to move the elements in sorted sub-array
            while(j>=0 && array[j]>element)
            {
                //move current element 1 place over
                array[j+1] = array[j];
                //decrement j to check the other elements in sorted sub-array
                j--;
            }

            //places the element in its correct position
            array[j+1] = element;

            printArray(array);
        }

        return array;
    }


    //Bubble Sort
    //Time Complexity = O(n^2)
    //Best Case = O(n) ---> when array is already sorted
    //Worst Case occurs when array is reverse sorted
    //Sorted in place
    //End of array is always sorted ("i" elements at the end of the array)
    public int[] bubbleSort(int[] array){

        int temp = 0;
        int sorted;

        //length-1 because the array is already sorted when we get to the last pass
        for(int i=0; i<array.length-1; i++)
        {
            sorted = 1;

            //length-1 to avoid array index out of bounds exception
            //length-1-i because the last "i" elements are correct and do not need to be resorted
            for(int j=0; j<array.length-i-1; j++)
            {
                //if any 2 consecutive elements are in the wrong order, swap them
                if(array[j] > array[j+1])
                {
                    sorted = 0;
                    temp = array[j];
                    array[j] = array[j+1];
                    array[j+1] = temp;
                }
            }

            if(sorted==1)
                break;

            printArray(array);
        }
        return array;
    }


    public int[] mergeSort(int[] array, int left, int right){

        //split the left side of the array until its size is 1
        //go back recursively and split the right side of the array
        if(left < right)
        {
            mergeSort(array, left, (left+right)/2);
            mergeSort(array, (left+right)/2 +1, right);
            merge(array, left, right);
        }

        return array;

    }

    private int[] merge(int[] array, int left, int right)
    {
        int middle = (left+right)/2;
        int[] array1 = new int[middle-left+1];
        int[] array2 = new int[right-middle];


        for(int i=left; i<=middle; i++)
            array1[i-left] = array[i];

        for(int i=middle+1; i<=right; i++)
            array2[i-middle-1] = array[i];


        int i=left, j=0, k=0;

        while(j<array1.length && k<array2.length)
        {
            if(array1[j] <= array2[k])
            {
                array[i] = array1[j];
                j++;
            } else {
                array[i] = array2[k];
                k++;
            }
            i++;
        }

        while(j<array1.length)
        {
            array[i] = array1[j];
            i++;
            j++;
        }

        while(k<array2.length)
        {
            array[i] = array2[k];
            i++;
            k++;
        }

        return array;
    }

    public void printArray(int[] array){

        for(int i=0; i<array.length; i++)
            System.out.print(array[i] + " ");

        System.out.println();
    }

}
