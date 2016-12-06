package problem_set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;


public class Main {

    private static  Scanner sc = new Scanner(System.in);
    private static DecimalFormat df=new DecimalFormat("0.000000");
    public static void main(String[] args){
        while (true){
            int n  = sc.nextInt();
            if(n == -1) break;
            System.out.println(isPrime(n));
        }

    }

    public static int isPrime(long n){
        long k = n-1;
        int t = 0;
        while (k % 2 == 0){
            t++;
            k/=2;
        }

        if(n > 2 && n%2 == 0 ) return 0;
        if(n > 3 && n%3 == 0 ) return 0;
        if(n > 5 && n%5 == 0 ) return 0;
        if(n > 7 && n%7 == 0 ) return 0;

        if(sus(61, t, k, n) &&  sus(7, t, k,n) && sus(3, t, k ,n)){
            return 1;
        }
        return 0;
    }

    private static boolean sus(long b, long t, long k, long n) {
        long prod = 1;
        while (k  > 0){
            if(k %2 == 1) prod = (prod * b) % n;
            k/=2;
            b = (b * b) % n;
        }
        if(prod == 1) return true;

        for( int i = 0; i <= t; i++){
            if(prod == n -1 ) return true;
            prod = (prod * prod) %n;
        }
        return false;
    }


}