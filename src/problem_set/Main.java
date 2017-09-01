package problem_set;

import java.math.BigInteger;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static BitSet bitSet = new BitSet(10000010);
    private static List<Long> primeList = new ArrayList<>();
    private static long sieve_ll;
    private static Map<Integer, Map.Entry<Integer, Integer>> map = new HashMap<>();
    public static void main(String[] args) throws Exception {
        sieve(10000000);
//        prepare();
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()){
            int first = sc.nextInt();
            int second = sc.nextInt();
            int min = Math.min(first, second);
            int max = Math.max(first, second);
            if(max <= sieve_ll){
                List<Long> updated = primeList.stream().filter(i -> i >= min && i <= max).collect(Collectors.toList());
                calculate(updated);
            }else {
                //get max
                if(min % 2 == 0){
                    min++;
                }
                int previousPrime = -1;
                int nextPrime = -1;
                int counter = min;
                while (counter <= max){
                    if(isPrime(counter)){

                    }
                }
            }

        }
    }

    private static void calculate(List<Long> updated) {
        if(updated.size() <= 1){
            System.out.println("There are no adjacent primes.");
            return;
        }
        Map.Entry<Long, Long> close = null;
        Map.Entry<Long, Long> distant = null;
        long min = Long.MAX_VALUE;
        long max = Long.MIN_VALUE;
        for(int i = 0; i < updated.size() - 1; i++){
            long currentDiff = updated.get(i + 1) - updated.get(i);
            if(currentDiff > max){
                max = currentDiff;
                distant = new AbstractMap.SimpleEntry<Long, Long>(updated.get(i), updated.get(i + 1));
            }
            if(currentDiff < min){
                min = currentDiff;
                close = new AbstractMap.SimpleEntry<Long, Long>(updated.get(i), updated.get(i + 1));
            }
        }
        if(close != null && distant != null){
            System.out.println(String.format("%d,%d are closest, %d,%d are most distant.", close.getKey(), close.getValue(), distant.getKey(), distant.getValue()));
        }
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
        bitSet.set(0, (int) sieve_ll, true);
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

