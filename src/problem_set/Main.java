package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static Set<Integer> resultSet = new HashSet<>();
    private static BitSet bitSet = new BitSet(10000010);
    private static List<Long> primeList = new ArrayList<>();
    private static long size_ll;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        generatePrime(10000000);
        long testCase = sc.nextLong();
        while (testCase-- > 0){
            long low = sc.nextLong();
            long high = sc.nextLong();
            long maxIndex = -1;
            long maxValue = -1;
            for(long i = low; i <= high; i++){
                List<Long> factors = primeFactors(i);
                long value = getValue(factors);
                if(value > maxValue){
                    maxIndex = i;
                    maxValue = value;
                }
            }
            System.out.println(String.format("Between %d and %d, %d has a maximum of %d divisors.", low, high, maxIndex, maxValue));
        }
    }

    private static long getValue(List<Long> factors) {
        Set<Long> set = new HashSet<>(factors);
        long result = 1;
        for(Long currentLong: set){
            List<Long> newList = factors.stream().filter(i -> Objects.equals(i, currentLong)).collect(Collectors.toList());
            result = result * (newList.size() + 1);
        }
        return result;
    }

    private static List<Long> primeFactors(long n){
        List<Long> factors = new ArrayList<>();
        int currentIndex = 0;
        long dfp = primeList.get(currentIndex);
        while (n != 1 && dfp * dfp <= n){
            while (n % dfp == 0){
                n = n/dfp;
                factors.add(dfp);
            }
            dfp = primeList.get(++currentIndex);
        }
        if(n != 1){
            factors.add(n);
        }
        return factors;
    }

    private static boolean isPrime(long n){
        if(n < size_ll){
            return bitSet.get((int) n);
        }
        for(long i: primeList){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }

    private static void generatePrime(long upbound) {
        size_ll = upbound + 1;
        primeList.clear();
        bitSet.clear();
        bitSet.flip(0,bitSet.size());
        bitSet.set(0, false);
        bitSet.set(1, false);
        for(long i = 2; i <= size_ll; i++){
            if(bitSet.get((int) i)){
                for(long j = i * i; j <= size_ll; j += i){
                    bitSet.set((int) j, false);
                }
                primeList.add(i);
            }
        }
    }
}

