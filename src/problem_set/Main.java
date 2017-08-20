package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static long numComponent, n, sum;
    private static int[][] array;
    private static char[][] chars;
    private static List<String> queryList = new ArrayList<>();
    private static List<Integer> primeList = new ArrayList<>();
    private static long sieve_ll;
    private static BitSet bitSet = new BitSet(10000010);
    public static void main(String[] args) throws Exception {

        sieve(10000000);

//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            n = Long.valueOf(sc.nextLine().trim());
            if(n == 0){
                break;
            }
            process();
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

    private static void process() {
        long result = -1;

        if(isPrime(n)){
            result = -1;
        }else {
            Set<Long> divisorList = new HashSet<>(primeFactor(n));
            if(divisorList.size() > 1){
                result = divisorList.stream().mapToLong(Long::valueOf).max().getAsLong();
            }else {
                result = -1;
            }
            if(result == n){
                result = -1;
            }
        }
        System.out.println(result);
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

