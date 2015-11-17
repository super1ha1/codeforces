import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Main {

    public static void main(String [] args){
        System.out.println("Result: ");


    }
    public static void bubbleSort(int [] numbers){
        int n = numbers.length;
        if( n <= 1)
            return;

        boolean swapped ;
        do {
            swapped = false;
            for ( int i = 1; i < n ; i++){
                if( numbers[i-1] > numbers[i]){
                    int tmp = numbers[i];
                    numbers[i] = numbers[i-1];
                    numbers[i-1] = tmp;
                    swapped = true;
                }
            }
            n= n-1;
        }while (swapped);
    }
    public static void optimizedBubbleSort(int [] numbers){
        int n = numbers.length;
        if( n <= 1)
            return;
        int newValue;
        do {
            newValue = 0 ;
            for ( int i = 1; i < n ; i++){
                if( numbers[i-1] > numbers[i]){
                    int tmp = numbers[i];
                    numbers[i] = numbers[i-1];
                    numbers[i-1] = tmp;
                    newValue = i;
                }
            }
            n = newValue;
        }while (n > 0 );
    }
    public static void insertionSort(int [] numbers){
        int n = numbers.length;
        if( n <= 1)
            return;
        for ( int i = 1; i < n; i++){
            for ( int j = i; j >= 1; j--){
                if( numbers[j-1] > numbers[j] ){
                    int temp = numbers[j];
                    numbers[j] = numbers[j-1];
                    numbers[j-1] = temp;
                }
            }
        }
    }
    public static void quickSort(int [] numbers, int low,int high){
        int n = numbers.length;
        if( n <= 1)
            return;
        if( low < high){
            int partition = partition(numbers, low, high);
            quickSort(numbers, low, partition -1);
            quickSort(numbers, partition + 1, high);
        }
    }

    private static int partition(int[] numbers, int low, int high) {
        int i = low;
        int pivot = numbers[high];
        for ( int j = low; j < high; j++){
            if( numbers[j] <= pivot){
                swap(numbers, i, j);
                i++;
            }
        }
        swap(numbers, i, high);
        return i;
    }

    private static void swap(int [] numbers, int i, int j) {
        int temp = numbers[j];
        numbers[j] = numbers[i];
        numbers[i] = temp;
    }

    public static void mergeSort(int [] numbers){
        int [] B = new int[numbers.length];
        split(numbers, 0, numbers.length, B);
    }

    private static void split(int[] A, int low, int high, int[] B) {
        if( high - low < 2)
            return;
        int mid = (low + high)/2;
        split(A, low, mid, B);
        split(A, mid, high, B);
        merge(A, low, mid, high, B);
        copyArray(A, low, high, B);
    }

    private static void copyArray(int[] a, int low, int high, int[] b) {
        for ( int i = low; i < high; i++){
            a[i] = b[i];
        }
    }

    private static void merge(int[] A, int low, int mid, int high, int[] B) {
        int firstIndex = low;
        int secondIndex = mid;
        for ( int i = low; i < high; i++){
            if(firstIndex < mid && (A[firstIndex] <= A[secondIndex]  || secondIndex >= high)){
                B[i] = A[firstIndex];
                firstIndex++;
            }else {
                B[i] = A[secondIndex];
                secondIndex++;
            }
        }
    }

    public static int binarySearch(int[] A, int low, int high, int key){
        while (low < high){
            int mid = (low + high) /2;
            if( A[mid] < key)
                low= mid + 1;
            else if( A[mid] > key)
                high = mid -1;
            else
                return mid;
        }
        return -1;
    }
    public static int optimizedBinarySearch(int[] A, int low, int high, int key){
        while (low < high){
            int mid = low + (high - low)/2;
            if( A[mid] < key)
                low = mid + 1;
            else
                high = mid;
        }
        if( low == high && A[low] == key)
            return low;
        return -1;
    }
}
