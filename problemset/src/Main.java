
import javax.xml.stream.events.Characters;
import java.util.*;

public class Main {

    public static final int N = 1000001;
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        int col = sc.nextInt();
        int row = sc.nextInt();
        int result = 0 ;
        while (row >0 && col > 0){
            int n = col * row;
            int dec = row + col - 1;
//            int add = n % dec == 0 ? 0 : 1;
//            int rel = add + ( n / dec);

            result ++;
            row--;
            col--;
        }
//        System.out.println("rel : " + result);
        if( result % 2 == 1 ){
            System.out.println("Akshat");
        }else {
            System.out.println("Malvika");

        }




    }


    public static  boolean check(String s, String t){
        boolean rel = false;
        int i = 0 ;
        char [] s1 = s.toCharArray();
        char [] t1 = t.toCharArray();
        if( s.length() == t.length()){
            int n = s.length() ;
            while (i <= n - 1 && s1[i] == t1[n-1 - i] ){
                i++;

            }
            if( i == n  ){
                rel = true;
            }
        }

        return rel;
    }




}