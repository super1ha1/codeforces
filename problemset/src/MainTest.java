

import java.util.*;


public class MainTest {
    public static void main(String[] args){
       Scanner sc = new Scanner(System.in);
        int n, b,p;
        n = sc.nextInt();
        b = sc.nextInt();
        p = sc.nextInt();
        int bottleEachMatch = 2 * b + 1;

        int matches = findMatch(n);
        System.out.println( (matches * bottleEachMatch) + " " + ( n * p));
    }

    private static int findMatch(int n) {
        int matches = 0 ;
        while (n > 1){
            int k = n /2;
            int left = n % 2;
            matches += k ;
            n = left + k;
        }
        return matches;
    }


}