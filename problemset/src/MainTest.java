

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class MainTest {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        int [] array = {1, 2, 3, 4, 9 , 10, 15, 20, 25};
        System.out.println(binarySearch(array, 0, array.length -1, 25));

    }

    public static int binarySearch (int[] array, int low, int high, int key){
        if(array.length == 0 ) return -1;
        if( high < low ) return  -1;

        int mid = low + (high - low) /2;
        if( array[mid] == key)
            return mid;
        else if( array[mid] < key){
            return binarySearch(array, mid+1, high, key);
        }else return binarySearch(array, low, mid -1, key);


    }


}