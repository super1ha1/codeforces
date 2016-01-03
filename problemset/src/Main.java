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
        int n;
        n = Integer.valueOf(sc.nextLine());
        int a, b;
        for ( int i = 0 ; i < n; i++){

            Vector<Integer> index = new Vector<>();
            Vector<String> value = new Vector<>();
            String first = sc.nextLine();
            String indexStr = sc.nextLine();
            String valueStr = sc.nextLine();

            for( String s: indexStr.split(" ")){
                index.add(Integer.valueOf(s));
            }
            for( String s: valueStr.split(" ")){
                value.add(s);
            }
//            printInt(index);
//            printDouble(value);
            for ( int j = 1 ; j <= index.size(); j++){
                System.out.println(value.elementAt(index.indexOf(j)));
            }
            System.out.println();

        }


    }
    public static void printInt(Vector<Integer> integers){
        for( Integer integer: integers){
            System.out.print(integer);
        }
        System.out.println();
    }
    public static void printDouble(Vector<String> integers){
        for( String integer: integers){
            System.out.print(integer);
        }
        System.out.println();
    }
}