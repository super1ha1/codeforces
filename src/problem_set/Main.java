package problem_set;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static BitSet bitSet = new BitSet(10000010);
    private static List<Long> primeList = new ArrayList<>();
    private static long sieve_ll;

    public static void main(String[] args) throws Exception {
        sieve(10000010);
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        int testCase = sc.nextInt();
        while (testCase-- > 0){
           long n = sc.nextLong();
           System.out.println(findSmith(n));
        }
    }

    private static long findSmith(long n) {
        n++;
        while (true){
            if(!isPrime(n) && sumDigit(n)){
                return n;
            }
            n++;
        }
    }

    private static boolean sumDigit(long n) {
        int valueN = value(n);
        List<Long> factors = primeFactor(n);
        int valueSum = factors.stream().mapToInt(e -> value(e)).sum();
        return valueN == valueSum;
    }

    private static int value(long n){
        int valueN = 0;
        for(char c: String.valueOf(n).toCharArray()){
            valueN += Integer.valueOf(String.valueOf(c));
        }
        return valueN;
    }

    private static List<Long> primeFactor(long n) {
        List<Long> list = new ArrayList<>();
        int index = 0;
        long value = primeList.get(index);
        while (n != 1 && value * value <= n){
            while (n % value == 0){
                n = n /value;
                list.add(value);
            }
            value = primeList.get(++index);
        }

        if(n != 1){
            list.add(n);
        }
        return list;
    }

    private static boolean isPrime(long n) {
        if(n <= sieve_ll){
            return bitSet.get((int) n);
        }
        for(int i = 0; i < primeList.size(); i++){
            if(n % primeList.get(i) == 0){
                return false;
            }
        }
        return true;
    }

    private static void sieve(long bound){
        sieve_ll = bound + 1;
        bitSet.set(0, bitSet.size(), true);
        bitSet.set(0, false);
        bitSet.set(1, false);

        for(long i = 2; i <= sieve_ll; i++){
            if(bitSet.get((int) i)){
                for(long j = i * i; j <= sieve_ll; j += i){
                    bitSet.set((int) j, false);
                }
                primeList.add(i);
            }
        }
    }

}

