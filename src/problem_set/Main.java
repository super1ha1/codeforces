package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static long  n, counter;
    private static long sieve_ll;
    private static List<Long> primesList = new ArrayList<>();
    private static BitSet bitSet = new BitSet(10000010);
    private static Map<Long, Long> map = new HashMap<>();
    private static List<Long> valueList = new ArrayList<>();
    private static int indexRange;
    public static void main(String[] args) throws Exception {

        sieve(1000000);
        processList(12158598919L);
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//                Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            n = sc.nextLong();
            if(n == 0){
                break;
            }
            if(n == 1){
                System.out.println("0/1");
                continue;
            } else if(n == 2){
                System.out.println("1/1");
                continue;
            }
            long nextRangeN = getNextRangeN(n);
            if(nextRangeN > -1) {
                if(nextRangeN == 2){
                    //cannot reach here
                } else { //nextRangeN > n
                    long nextNumber = map.get(nextRangeN);
                    long previousNumber = nextNumber - 1;
                    long lowerBound = valueList.get(indexRange - 1);
                    Map.Entry<Integer, Integer> pair = null;
                    if(lowerBound == n){
                        pair = getLastPair(previousNumber );
                    }else if(lowerBound < n){
                        pair = getPair(nextNumber, n - lowerBound);
                    }
                    if(pair != null){
                        System.out.println(String.format("%d/%d", pair.getKey(), pair.getValue()));
                    }
                }
            }
        }
    }

    private static Map.Entry<Integer, Integer> getLastPair(long previousNumber) {
        for(long i = previousNumber - 1; i >= 1; i--){
            if(gcd(i, previousNumber) == 1){
                return new AbstractMap.SimpleEntry<>((int)i, (int) previousNumber);
            }
        }
        return null;    }

    private static Map.Entry<Integer, Integer> getPair(long previousNumber, long lowerBound) {
        int count = 0;
        for(int i = 1; i < previousNumber; i++){
            if(gcd(i, previousNumber) == 1){
                count++;
                if(count == lowerBound){
                    return new AbstractMap.SimpleEntry<>(i, (int) previousNumber);
                }
            }
        }
        return null;
    }

    private static long getNextRangeN(long n) {
        for(int i = 0; i < valueList.size(); i++){
            long value = valueList.get(i);
            if(value > n){
                indexRange = i;
                return value;
            }
        }
        return -1;
    }

    private static void processList(long n) {
        counter = 2;
        map.put(2L, 1L);
        for(int i = 2; i <= 1000000; i++){
            long euler = eulerPhi(i);
            counter = counter + euler;
            map.put(counter, (long) i);
            if(counter > n){
                break;
            }
        }
        valueList.addAll(map.keySet());
        Collections.sort(valueList);
    }

    private static long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    private static void sieve(long bound) {
        sieve_ll = bound + 1;
        bitSet.set(0, bitSet.size(), true);
        bitSet.set(0, false);
        bitSet.set(1, false);

        for(long i = 2; i <= sieve_ll; i++){
            if(bitSet.get((int)i)){
                for(long j = i * i; j <= sieve_ll; j += i){
                    bitSet.set((int)j, false);
                }
                primesList.add(i);
            }
        }
    }

    private static long eulerPhi(long n) {
        Set<Long> uniqueSet = new HashSet<>(primeFactors(n));
        long result = n;
        for(long i: uniqueSet){
            result = result - result/i;
        }
        return result;
    }

    private static List<Long> primeFactors(long n) {
        List<Long> factorsList = new ArrayList<>();
        int index = 0;
        long factor = primesList.get(index);
        while (n != 1 && factor * factor <= n){
            while (n % factor == 0){
                n = n /factor;
                factorsList.add(factor);
            }
            factor = primesList.get(++index);
        }

        if(n != 1){
            factorsList.add(n);
        }
        return factorsList;
    }
}

