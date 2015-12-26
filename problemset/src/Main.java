import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.*;

public  class Main
{
    public static void main (String args[])  throws Exception// entry point from OS
    {
        int n , p, q;
        String s;
        char [] chars;
        Scanner sc = new Scanner(System.in);


            n = sc.nextInt();
            p = sc.nextInt();
            q = sc.nextInt();
            s = sc.next();
            chars = s.toCharArray();
            int p_div = -1, q_div = -1;
            for ( int i = 0; i <= n/p; i++){
                p_div = i;
                if( (n - p * p_div) % q == 0 ){
                    q_div = (n - p * p_div) / q;
                    break;
                }
            }
            if (q_div != -1) {
                System.out.println( q_div + p_div);
                for( int i = 0 ; i < q_div; i ++){
                    System.out.println(s.substring(q * i, q * i + q));
                }
                for( int i = 0 ; i < p_div; i++){
                    System.out.println(s.substring(p * i + q * q_div, q * q_div + p * i + p ));
                }
            }else
                System.out.println(-1);

    }

}