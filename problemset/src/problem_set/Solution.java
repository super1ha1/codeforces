package problem_set;

import java.io.*;
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int [] array = new int[n];

        for( int i = 0 ; i < n; i++){
            array[i] = sc.nextInt();
        }

        int count = 0 ;
        for(int i = 0 ; i <= n-2; i++){
            int nextIndex = Arrays.binarySearch(array, i, n, array[i] + k);
            if(nextIndex > 0){
                int secondIndex = Arrays.binarySearch(array, nextIndex, n, array[nextIndex] + k);
                if(secondIndex > 0){
                    count ++;
                }
            }
        }
        System.out.println(count);
    }
}