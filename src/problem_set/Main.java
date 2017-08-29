package problem_set;

import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int counter = 0;
        int testCase = sc.nextInt();
        while (testCase-- > 0){
            long n = sc.nextLong();
            System.out.println(String.format("Case #%d: %d is ", ++counter, n) + (isHappyNumber(n) ? "a Happy number." : "an Unhappy number."));
        }

    }

    private static boolean isHappyNumber(long n) {
        long tortoise, bare;
        tortoise = function(n);
        bare = function(function(n));
        if( tortoise == 1 || bare == 1){
            return true;
        }
        while (tortoise != bare){
            tortoise = function(tortoise);
            bare = function(function(bare));
            if(tortoise == 1 || bare == 1){
                return true;
            }
        }
        return false;
    }

    private static long function(long n){
        long sum = 0;
        char[] array = String.valueOf(n).toCharArray();
        for(char c: array){
            sum += (long) Math.pow(Integer.valueOf(String.valueOf(c)), 2);
        }
        return sum;
    }
}

