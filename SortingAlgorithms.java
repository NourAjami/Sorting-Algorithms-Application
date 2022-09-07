//Name: Nour Ajami 

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class SortingAlgorithms {
    public static void main(String args[]) { 

        String order = args[0];
        String algorithm = args[2];
        String outputfile = args[3];
        Integer[] intArray = new Integer[Integer.parseInt(args[1])];

        if(args.length != 4) {
            System.out.println("You have eneter an invalid number of arguments \nPlease enter a valid number of arguments in the format: (order) - (size) - (algorithm) - (outputfile)");
            System.exit(1);
         }

        //This code was publicly from: https://docs.oracle.com/javase/tutorial/essential/environment/cmdLineArgs.html
        // This will check if the user enters an incorrect number of arguments, could be greater than or less than 4.
        int firstArgument;
        if(args.length > 0) {
            try {
                firstArgument = Integer.parseInt(args[1]);
            } 
            catch(NumberFormatException exception) {
                    System.err.println("The argument (size) that you have eneterd" +  " (" + args[1] + ")" + " "+ "must be an integer." +"\n" + "Please try again by entering a positive number");
                    System.exit(1);
                }
        }

        int arg3 = Integer.parseInt(args[1]);
            if(arg3 <= 0) {
                System.out.println("The argument (size) that you have eneterd" +  " (" + arg3 + ")" + " "+ "must be a positive number." +"\n" + "Please try again by entering a positive number");
                    System.exit(1);
            }

            //Checking if the user has enetered the correct input for the order
            if(!"Ascending".equalsIgnoreCase(order) && !"Descending".equalsIgnoreCase(order) && !"Random".equalsIgnoreCase(order)) {
            System.out.println("The argument (order) that you have entered" + " " + "(" + args[0] + ")" + " " + "must be one of the following inputs: (Ascending) (Descending) (Random)");
            System.exit(1);

            }
                //Checking if the user has entered the correct input for the algorithm
                if(!"Selection".equalsIgnoreCase(algorithm) && !"Insertion".equalsIgnoreCase(algorithm) && !"Merge".equalsIgnoreCase(algorithm) && !"Quick".equalsIgnoreCase(algorithm) ) {
                System.out.println("The argument (algorithm) that you have entered" + " " + "(" + args[2] + ")" + " " + "must be one of the following inputs: (Selection) (Insertion) (Merge) (Quick)");
                    System.exit(1);
            }

            //Checking if the user did not enter a .txt file 
            if(!outputfile.endsWith(".txt")){
                System.out.println("Error occured - Please enter a name with the following format: (outputFileName.txt)");
                System.out.println("Exiting program");
                System.exit(1);
            }

            int max = Integer.MAX_VALUE;

            //Populating random numbers into the array
            for (int i = 0; i < intArray.length; i++) {
                intArray[i] = (int)(Math.random() * max);
            }

            //Checking which order is chosen and sorting the algorithm based on the order
            if(order.equalsIgnoreCase("Ascending")) {
                Arrays.sort(intArray);

            }
            else if (order.equalsIgnoreCase("Descending")) {
               Arrays.sort(intArray, Collections.reverseOrder());
            }
            else if (order.equalsIgnoreCase("Random")) {
                List<Integer> intList = Arrays.asList(intArray);
                Collections.shuffle(intList);
                intList.toArray(intArray); 
            }

            //For printing the elements and checking
            // for (int j = 0; j<intArray.length; j++) {
            //     System.out.println(intArray[j]);
            // }
            
            long totalTime;
            float time;

        //If the user has entered selection sort
        if("Selection".equalsIgnoreCase(algorithm)) {
            long startTimer = System.nanoTime();
            selectionSort(intArray);
            long endTimer = System.nanoTime();
            totalTime = endTimer - startTimer;
            time = (float)totalTime / 1_000_000_000;
            System.out.println("The selection algorithm took a total time of:" + time + " seconds");
            creatingFile(outputfile);
            writingFile(intArray, outputfile);
        }
        
        //If the user has entered insertion sort
        else if("Insertion".equalsIgnoreCase(algorithm)) {
            long startTimer = System.nanoTime();
            insertionSort(intArray);
            long endTimer = System.nanoTime();
            totalTime = endTimer - startTimer;
            time = (float)totalTime / 1_000_000_000;
            System.out.println("The insertion algorithm took a total time of:" + time + " seconds");
            creatingFile(outputfile);
            writingFile(intArray, outputfile);
        }
        //If the user has entered merge sort
        else if("Merge".equalsIgnoreCase(algorithm)) {
            long startTimer = System.nanoTime();
            mergeSort(intArray, 0, intArray.length-1);
            long endTimer = System.nanoTime();
            totalTime = endTimer - startTimer;
            time = (float)totalTime / 1_000_000_000;
            System.out.println("The merge algorithm took a total time of:" + time + " seconds");
            creatingFile(outputfile);
            writingFile(intArray, outputfile);

        }
        //If the user has entered quick sort
        else if("Quick".equalsIgnoreCase(algorithm)) {
            long startTimer = System.nanoTime();
            quickSort(intArray, 0, intArray.length-1);
            long endTimer = System.nanoTime();
            totalTime = endTimer - startTimer;
            time = (float)totalTime / 1_000_000_000;
            System.out.println("The quick algorithm took a total time of:" + time + " seconds");
            creatingFile(outputfile);
            writingFile(intArray, outputfile);

        }

    }

//The selectionSort method was written from the teaching, influence, and code of public website of Paul McDonald 
//The link is: https://sirpaulmcd.com/tutorials-cheat-sheets/data-structures-and-algorithms/sorting-algorithms/#selection-sort
public static void selectionSort(Integer arraySort[]) {
    int temp = 0;
    int minIndex = 0;
    //Moving boundary of unsorted subarray one by one
    for(int i = 0 ; i < arraySort.length-1; i++) {
        
         minIndex = i;

        //Finding the minumum element in unsorted array
        for(int j = i+1; j < arraySort.length; j++) {
            if(arraySort[j] < arraySort[minIndex]) {
            minIndex = j;
            }
        }
        
        //Swapping items        
        temp = arraySort[minIndex];
        arraySort[minIndex] = arraySort[i]; 
        arraySort[i] = temp;         
 
    }
}

//The insertionSort method was written from the teaching, influence, and code of the public website of Paul McDonald 
//The link is: https://sirpaulmcd.com/tutorials-cheat-sheets/data-structures-and-algorithms/sorting-algorithms/#selection-sort
public static void insertionSort(Integer arraySort[]) {

    //moving the boundary of unsroted subarray one by one
    for (int i = 1; i < arraySort.length; i++) {
    
    //Store first element of unsorted subarray
    int current = arraySort[i];
    
    int j = i-1;

    while(j >= 0 && current < arraySort[j]) {
        arraySort[j+1] = arraySort[j];
        j--;
    }

    //Inserting the stored element into the index before the shifted larger neighbors
    arraySort[j+1] = current;

    }
}

//The mergeSort method was heavily written from the teaching, influence, and code of the online udemy course that I purchased "Data Structures and Algorithms: Deep Dive Using Java"
//The link is: https://www.udemy.com/course/data-structures-and-algorithms-deep-dive-using-java/
public static void mergeSort(Integer[] arraySort, int startArray , int endArray) {
    if (endArray - startArray < 2){
        return;
    }
    int mid = (startArray + endArray)/2;
    mergeSort(arraySort, startArray, mid);
    mergeSort(arraySort, mid, endArray);
    merge(arraySort, startArray, mid, endArray);
}

//The merge method was heavily written from the teaching, influence, and code of the online udemy course that I purchased "Data Structures and Algorithms: Deep Dive Using Java"
//The link is: https://www.udemy.com/course/data-structures-and-algorithms-deep-dive-using-java/
public static void merge (Integer[] arraySort, int startArray, int mid, int endArray){
    if(arraySort[mid - 1] <= arraySort[mid]){
        return;
    }
    int i = startArray;
    int j = mid;
    int tempIndex = 0;
    Integer[] temp = new Integer[endArray - startArray];
    while(i < mid && j < endArray){
        temp[tempIndex++] = arraySort[i] <= arraySort[j] ? arraySort[i++] : arraySort[j++]; // Acts as an "if" statement in order to position/sort the smaller elements (between the left and right subarrays)
    }
    System.arraycopy(arraySort, i, arraySort, startArray + tempIndex, mid - i);
    System.arraycopy(temp, 0, arraySort, startArray, tempIndex);
}



//The quickSort method was HEAVILY written from the teaching, influence, and code of the public website of Paul McDonald 
//https://sirpaulmcd.com/tutorials-cheat-sheets/data-structures-and-algorithms/sorting-algorithms/#description-4
private static void quickSort(Integer[] arraySort, int leftIndex, int rightIndex) 
{
    // If subarray only contains one element, stop recursion
    if (leftIndex < rightIndex) 
    { 
        // Partition array and recieive partition index
        int pIndex = partition(arraySort, leftIndex, rightIndex); 
        // Apply quicksort to left and right subarrays
        quickSort(arraySort, leftIndex, pIndex-1); 
        quickSort(arraySort, pIndex+1, rightIndex); 
    } 
} 

//The partition method was HEAVILY written from the teaching, influence, and code of the public website of Paul McDonald 
//https://sirpaulmcd.com/tutorials-cheat-sheets/data-structures-and-algorithms/sorting-algorithms/#description-4
private static int partition (Integer[] arraySort, int startIndex, int endIndex) 
{
    // Select random element as pivot and swap to last index
    Random rand = new Random();
    int randomIndex = rand.nextInt(endIndex - startIndex) + startIndex;
    swap(arraySort, randomIndex, endIndex); 
    int pivot = arraySort[endIndex];
    // Create partition index used to swap smaller values into left subarray
    int pIndex = (startIndex - 1); 
    // For every element in the given range...
    for (int j = startIndex; j <= endIndex - 1; j++) 
    { 
        // If element is smaller than or equal to pivot...
        if (arraySort[j] <= pivot) 
        { 
            pIndex++;
            // Swap into left subarray
            swap(arraySort, pIndex, j);
        } 
    }
    // Swap pivot to end of the left subarray
    swap(arraySort, pIndex+1, endIndex);
    return pIndex + 1; 
}

private static void swap(Integer[] arraySort, int index1, int index2)
{
    int temp = arraySort[index1];
    arraySort[index1] = arraySort[index2];
    arraySort[index2] = temp;
}

//The creatingFile method below was created using the public website w3school
//The link is: https://www.w3schools.com/java/java_files_create.asp
public static void creatingFile(String outPutFile) {
try{
    File file_OutPut = new File(outPutFile);
    if(file_OutPut.createNewFile()) {
        System.out.println("The file is created:" + file_OutPut.getName());
    }
    else{
        System.out.println("The file with that name already exists");
    }
}
    catch (IOException e) {
        System.out.println("An error has occurered, please try again");
        e.printStackTrace();

        }
    
}

//The writingFile method below was created using the public website w3school
//The link is: https://www.w3schools.com/java/java_files_create.asp
public static void writingFile(Integer[] arraySort, String outPutFile) {
try {
    FileWriter fileWrite = new FileWriter(outPutFile);
    for(int i = 0; i < arraySort.length; i++){
        fileWrite.write(arraySort[i]+"\n");
    }
        fileWrite.close();
}
catch (IOException e) {
    System.out.println("An error has occurred.");
    e.printStackTrace();
    }
}

}
