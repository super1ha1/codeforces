

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class MainTest {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        System.out.println(C(10, 8));
    }

    public static int C(int n, int k){
        if( k == 0 || k == n){
            return 1;
        }else
            return C(n-1, k-1) + C(n-1, k);
    }


}