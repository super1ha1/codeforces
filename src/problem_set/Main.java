package problem_set;

import java.math.BigInteger;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            if(n == 0 && k == 0){
                break;
            }
            System.out.println(process(n, k));
        }
    }

    private static int process(int n, int k) {
        if(k == 0){
            return 0;
        }
        BigInteger value = BigInteger.ONE;
        for(int i = n - k + 1 ; i <= n; i++){
            value = value.multiply(BigInteger.valueOf(i));
        }

        BigInteger denominator = BigInteger.ONE;
        for(int i = 2; i <= k; i++){
            denominator = denominator.multiply(BigInteger.valueOf(i));
        }
        value = value.divide(denominator);
        return value.intValueExact();
    }
}

