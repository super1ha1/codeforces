package problem_set;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static BitSet bitset = new BitSet(100010);
    private static List<Long> primeList = new ArrayList<>();
    private static long sieve_ll;

    private static long n;
    private static int k;
    private static int[] visit, allocation;
    public static void main(String[] args) throws Exception {
            sieve(100000);
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
             n = sc.nextLong();
             k = sc.nextInt();
            if(k == 0){
                System.out.println(n);
            }else {
                List<Long> factors = getFactors(n);
                int counter2 = k;
                int counter5 = k;
                for(long value: factors){
                    if(value == 2){
                        counter2--;
                    }
                    if(value == 5){
                        counter5--;
                    }
                }
                if(counter2 > 0 && counter5 > 0){
                    long result = n * (long) Math.pow(2, counter2) * (long) Math.pow(5, counter5);
                    System.out.println(result);
                }else {
                    System.out.println(n);
                }

            }
        }
    }

    private static void sieve(long bound) {
        sieve_ll = bound  + 1;
        bitset.set(0, bitset.size(), true);
        bitset.set(0, false);
        bitset.set(1, false);

        for(long i = 2; i <= sieve_ll; i++){
            if(bitset.get((int) i)) {
                for (long j = i * i; j <= sieve_ll; j += i) {
                    bitset.set((int) j, false);
                }
                primeList.add(i);
            }
        }
    }

    private static List<Long> getFactors(long n){
        List<Long> factors = new ArrayList<>();
        int index = 0;
        long value = primeList.get(index);
        while (n != 1 && value * value <= n){
            while (n % value == 0){
                n = n /value;
                factors.add(value);
            }
            value = primeList.get(++index);
        }
        if(n != 1){
            factors.add(n);
        }
        return factors;
    }

    private static long process(long n) {
        int counter = getCounter(n);
        if(counter >= k){
            return n;
        }else {
            n = n * (long) Math.pow(10, k - counter);
            return n;
        }
    }

    private static int getCounter(long n) {
        int counter = 0;
        while (n % 10 == 0){
            counter++;
            n = n/10;
        }
        return counter;
    }

    private static void update(long n, int k) {
        while (true){
            for(int i = 1; i <= 9; i++){
                long value = i * (long) Math.pow(10, k);
                if(value % n == 0){
                    System.out.println(value);
                    return;
                }
            }
            k++;
        }
    }
}

