package problem_set;

import java.io.File;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        sc.nextLine();
        int count = 0;
        for(int i = 0 ; i < testCase; i++){
            String firstLine = sc.nextLine().trim();
            String secondLine = sc.nextLine().trim();
            BigInteger first = new BigInteger(firstLine, 2);
            BigInteger second = new BigInteger(secondLine, 2);
            System.out.println(String.format("Pair #%d: ", ++count) +
                    (isLove(first, second) ? "All you need is love!" : "Love is not all you need!"));
        }
    }

    private static boolean isLove(BigInteger first, BigInteger second) {
        BigInteger gcd = gcd(first, second);
        return gcd.compareTo(BigInteger.ONE) >= 1;
    }

    private static BigInteger gcd(BigInteger first, BigInteger second) {
        return second.equals(BigInteger.ZERO) ? first : gcd(second, first.mod(second));
    }
}

