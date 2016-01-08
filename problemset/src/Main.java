import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.*;

public  class Main
{
    public static void main (String args[])  throws Exception// entry point from OS
    {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileReader("in.txt"));
        int n, m;
        while (sc.hasNextInt()){
            n = sc.nextInt();
            m = sc.nextInt();
            int a ;
            int [] array = new int[m];

            while (sc.hasNextInt()){
                a = sc.nextInt();
                array[a - 1 ] = 1;
            }

            boolean yes = true;
            for ( int i  =0 ; i < m; i++){
                if(array[i] == 0){
                    yes = false;
                    break;
                }
            }
            String s = yes ? "YES" : "NO" ;
            System.out.println(s);

        }

    }

}