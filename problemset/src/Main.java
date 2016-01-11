import java.io.*;
import java.math.BigInteger;
import java.util.*;

public  class Main
{
    public static void main (String args[])  throws Exception// entry point from OS
    {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileReader("in.txt"));
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();

        long [] min = new long[n];
        long [][] array = new long [n][m];
        for ( int i = 0 ; i < n; i++){
            for(int j = 0 ; j < m; j++){
                array[i][j] = sc.nextLong();
                if( j == 0 ) min[i] = array[i][j];
                if( array[i][j] < min[i])
                    min[i] = array[i][j];
            }
        }
        int max_index = -1;
        long max_value = -1;
        for ( int i = 0 ;i < n; i++){
            if( i == 0 ){
                max_value = min[i];
                max_index = i;
            }
            if( min[i] > max_value){
                max_value = min[i];
                max_index = i;
            }
        }
        System.out.println(max_value);
    }

}