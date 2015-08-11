
import javax.xml.stream.events.Characters;
import java.util.*;

public class Main {

    public static final int N = 1000001;
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        int row = 0 , col = 0 , n;
        for ( int i = 1; i <= 5; i ++){
            for ( int j = 1; j <= 5; j++){
                n = sc.nextInt();
                if( n == 1){
                    row = i;
                    col = j ;
                }
            }
        }

        System.out.print(Math.abs(row -3 ) + Math.abs(col -3));

    }

    public static int getHour(int a , int b){
        int mod, rel;
        mod = a % b;
        rel = a /b ;
        if ( rel == 0) return  mod;
        else return a   + getHour(rel , b);
    }




}