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
        sieve(10000010);
//        prepare();
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()){
            long n = sc.nextLong();
            if(n == 0){
                break;
            }
            if(n < 0){
                System.out.println(String.format("%d = %d x ", n, -1) + getList(-1 * n));
            }else {
                System.out.println(String.format("%d = ", n)  + getList(n));
            }
        }
    }

    private static String getList(long n) {
        List<Long> list = primeFactor(n);
        return list.stream().map(String::valueOf).collect(Collectors.joining(" x "));
    }

    private static void prepare() {
        map.put(2, new AbstractMap.SimpleEntry<>(1, 1));
        for(int i = 4; i <= 100000000; i += 2){
            BigInteger bigInteger = BigInteger.valueOf(i);
            int half = i/2;
            for(int j = half; j <= i; j += 2){
                if(isPrime(j) && isPrime(i -j)){
                    map.put(i, new AbstractMap.SimpleEntry<>(i - j, j));
                }
            }
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

