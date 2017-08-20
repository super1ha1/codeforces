package problem_set;

import java.io.File;
import java.math.BigDecimal;
import java.util.*;

public class Main {

    private static int j;
    private static double value;
    private static long numerator, denominator;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

            int count = 0;
            while (sc.hasNext()){
            j = sc.nextInt();
            if(j == -1){
                break;
            }
            value = sc.nextDouble();
            if(j < 0){
                continue;
            }
            count++;
            String str = String.format("%.9f", value);
            while (str.lastIndexOf('0') == str.length() - 1){
                str = str.substring(0, str.length() - 1);
            }
            String fraction = str.split("\\.")[1];
            int k = fraction.length() - j;
            String repeated = fraction.substring(k);
            str = str + repeated;
            value = Double.valueOf(str);

            if(j == 0){
                numerator = (long) (Math.pow(10, k) * value);
                denominator = (long) (Math.pow(10, k));
            }else {
                long firstPart = (long)(Math.pow(10, k + j) * value);
                long secondPart = (long) (Math.pow(10, k) * value);
                numerator =  firstPart - secondPart;
                denominator = (long) (Math.pow(10, k + j) - Math.pow(10, k));
            }
            long gcd = gcd(numerator, denominator);
            numerator = numerator/gcd;
            denominator = denominator/gcd;
            System.out.println(String.format("Case %d: %d/%d", count, numerator, denominator));
        }

    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

}

