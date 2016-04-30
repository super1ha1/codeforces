package problem_set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;


public class Main {

    private static  Scanner sc = new Scanner(System.in);
    private static DecimalFormat df=new DecimalFormat("0.000000");
    public static void main(String[] args){
        int n  = sc.nextInt();
        int[] M = new int[n];
        int[] R = new int[n];
        for( int i = 0 ; i < n; i++){
            M[i] = sc.nextInt();
        }
        for( int i = 0 ; i < n; i++){
            R[i] = sc.nextInt();
        }

        double left = 1.0, result = 0.0;
        for( int i = 0 ; i < n; i++){
            result += left * 1/M[i];
            left = left - (double) 1/M[i];
        }

//        0.666667
        System.out.println(df.format(result));

    }


}