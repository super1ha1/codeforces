import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.*;

public  class Main
{
    public static void main (String args[])  throws Exception// entry point from OS
    {
//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new FileReader("in.txt"));
        int n, m, c;
        n = Integer.valueOf(sc.nextLine());
        int a, b;
        for ( int i = 0 ; i < n; i++){
            Vector<Integer> index = new Vector<>();
            Vector<Integer> value = new Vector<>();
            String first = sc.nextLine();
            String indexStr = sc.nextLine();
            String valueStr = sc.nextLine();

            System.out.println("index; " + indexStr);
            System.out.println("value; " + valueStr);
            for( String s: indexStr.split(" ")){
                System.out.print(s);

//                index.add(Integer.valueOf(s));
            }
            for( String s: valueStr.split(" ")){
                System.out.print(s);

//                value.add(Integer.valueOf(s));
            }
            print(index);
            print(value);
        }


    }
    public static void print(Vector<Integer> integers){
        for( Integer integer: integers){
            System.out.print(integer);
        }
        System.out.println();
    }
}