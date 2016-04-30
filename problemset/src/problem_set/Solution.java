package problem_set;

import java.io.*;
import java.util.*;
import java.text.*;
import java.math.*;
import java.util.regex.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int c[] = new int[n];
        for(int c_i=0; c_i < n; c_i++){
            c[c_i] = in.nextInt();
        }

        int current = 0, count = 0;

        while (true){
            if(current == n-1) break;

            if(current+2 <= n-1 && c[current + 2] == 0) {
                current += 2;
                count++;
                if(current == n-1) break;
            }
            else if( current +1 <= n-1 && c[current + 1] == 0) {
                current += 1;
                count++;
                if(current == n-1) break;
            }
            else {
                break;
            }
        }

        if(current == n-1){
            System.out.println(count);
        }

    }
}