package problem_set;

import java.math.BigInteger;
import java.util.*;

public class Main {

    private static List<BigInteger> fibonaci = new ArrayList<>();
    private static List<BigInteger> sumfibonaci = new ArrayList<>();
    private static BigInteger bigIntegerTwo = BigInteger.valueOf(2);
    public static void main(String[] args) throws Exception {

        prepareFibonaci(1510);

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        int counter = 0;
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if(n <= 0){
                break;
            }
            System.out.println(String.format("Set %d:", ++counter));
            System.out.println(String.format("%d", calculate(n)));
        }
    }

    private static BigInteger calculate(int line) {
        BigInteger startNumber = sumfibonaci.get(line -1);
        BigInteger totalRange = fibonaci.get(line);
        boolean divideByTwo = totalRange.mod(bigIntegerTwo).equals(BigInteger.ZERO);
        BigInteger toBeAdd = divideByTwo ?
                totalRange.divide(bigIntegerTwo) :
                totalRange.add(BigInteger.ONE).divide(bigIntegerTwo);
        return startNumber.add(toBeAdd).subtract(BigInteger.ONE);
    }

    public static void prepareFibonaci(int max) {
        fibonaci.add(BigInteger.ZERO);
        fibonaci.add(BigInteger.ONE);
        sumfibonaci.add(BigInteger.ZERO);
        sumfibonaci.add(BigInteger.ONE);
        for(int i = 2; i <= max; i++){
            fibonaci.add(fibonaci.get(i-1).add(fibonaci.get(i -2)));
            sumfibonaci.add(fibonaci.get(i).add(sumfibonaci.get(i-1)));
        }
    }

}

