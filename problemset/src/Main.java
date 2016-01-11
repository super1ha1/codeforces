import java.io.*;
import java.math.BigInteger;
import java.util.*;

public  class Main
{
    public static void main (String args[])  throws Exception// entry point from OS
    {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileReader("in.txt"));
        long n , m;
        n = sc.nextLong();
        m = sc.nextLong();
        long sum = 0;
        long modValue =(long) (Math.pow(10, 9) + 7);
        for( int i = 1; i <= m; i++){
            long mod = n % i;
            if( mod > modValue){
                mod = mod % modValue;
            }
            sum = sum + mod;
            if( sum > modValue){
                sum = sum % modValue;
            }
        }

        System.out.println(sum);
    }


}