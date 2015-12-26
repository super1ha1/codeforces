import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.*;

public  class Main
{
    public static void main (String args[])  throws Exception// entry point from OS
    {
        int n ;
        Map<Integer , Integer> map = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        int num;
        for ( int i = 1; i <= n; i++){
            num = sc.nextInt();
            map.put(num, i);
        }
        BigInteger sum = BigInteger.valueOf(0);
        for( int i = 1; i < n; i++){
            sum = sum.add(BigInteger.valueOf(Math.abs(map.get(i + 1) - map.get(i))));
        }
        System.out.println(sum);
    }

}