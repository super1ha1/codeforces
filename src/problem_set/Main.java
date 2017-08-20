package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<Integer> primeList = new ArrayList<>();
    private static long sieve_ll;
    private static BitSet bitSet = new BitSet(20000010);
    private static int n;
    private static List<Map.Entry<Integer, Integer>> twinPrimes = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        sieve(20000000);
        processTwinPrime();

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            int n = sc.nextInt();
            Map.Entry<Integer, Integer> entry = twinPrimes.get(n - 1);
            if(entry != null){
                System.out.println(String.format("(%d, %d)", entry.getKey(), entry.getValue()));
            }
        }

    }

    private static void processTwinPrime() {
        for(int i = 0; i < primeList.size() - 1; i++){
            if(primeList.get(i + 1) - primeList.get(i) == 2){
                twinPrimes.add(new AbstractMap.SimpleEntry<>(primeList.get(i), primeList.get(i + 1)));
            }
        }
    }


    private static void sieve(long size) {
        sieve_ll = size + 1;
        bitSet.set(0, bitSet.size(), true);
        bitSet.set(0, false);
        bitSet.set(1, false);

        for (long i = 2; i <= sieve_ll; i++){
            if(bitSet.get((int)i)){
                for(long j = i * i; j <= sieve_ll; j += i){
                    bitSet.set((int) j, false);
                }
                primeList.add((int)i);
            }
        }
    }

    private static List<Long> primeFactor(long n) {
        List<Long> resultList = new ArrayList<>();
        int index = 0;
        long value = primeList.get(index);
        while (n != 1 && value * value <= n){
            while (n % value == 0){
                n /= value;
                resultList.add(value);
            }
            value = primeList.get(++index);
        }

        if(n != 1){
            resultList.add(n);
        }
        return resultList;
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

}

