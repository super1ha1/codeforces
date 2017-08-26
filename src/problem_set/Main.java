package problem_set;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<String> list = new ArrayList<>();
    private static List<BigInteger> fibonaci = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        caculateFibonaci(5000);

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if(n < 0){
                continue;
            }
            System.out.println(String.format("The Fibonacci number for %d is %d", n, fibonaci.get(n)));
        }
    }

    public static void caculateFibonaci(int max){
        fibonaci.add(BigInteger.ZERO);
        fibonaci.add(BigInteger.ONE);
        for(int i = 2; i <= max; i++){
            fibonaci.add(fibonaci.get(i-1).add(fibonaci.get(i-2)));
        }
    }
}

