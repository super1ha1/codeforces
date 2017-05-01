package problem_set;

import java.io.FileReader;
import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws  Exception{
        Scanner sc = new Scanner(new FileReader("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/problemset/src/problem_set/in.txt"));
//        Scanner sc = new Scanner(System.in);
        int testCase = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < testCase; i++){
            String line = sc.nextLine();
            BigInteger order = BigInteger.valueOf(Long.valueOf(sc.nextLine().trim()));
            compute(line, order.add(BigInteger.ONE));
        }
    }

    private static void compute(String line, BigInteger order) {
        char[] sortedChar = line.toCharArray();
        Arrays.sort(sortedChar);
        String sorted = new String(sortedChar);

        String output = "";
        while (output.length() < line.length()){
            int currentLength = output.length();
            BigInteger factor = getFactor(sorted.length() - 1);
            BigInteger index = order.divide(factor);

            if(index.compareTo(BigInteger.valueOf(sorted.length())) > 0){
                index = index.mod(BigInteger.valueOf(sorted.length()));
            }

            int indexToBeReplace = (index.intValue() - 1 >= 0) ? index.intValue() -1 : 0;
            output += sorted.charAt(indexToBeReplace);
            sorted = sorted.substring(0,indexToBeReplace) + sorted.substring(indexToBeReplace + 1);
            order = order.subtract(factor.multiply(BigInteger.valueOf(indexToBeReplace)));
        }

        System.out.println(output);
    }

    private static BigInteger getFactor(int currentLength) {
        BigInteger result = BigInteger.ONE;
        for(int i = 2; i <= currentLength; i++){
            result = result.multiply(BigInteger.valueOf(i));
        }
        return result;
    }


}