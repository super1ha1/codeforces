package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static BitSet bitSet = new BitSet(10000010);
    private static List<Long> primeList = new ArrayList<>();
    private static long size_ll;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        generatePrime(10000000);
        while (sc.hasNext()){
            int n = sc.nextInt();
            int c = sc.nextInt();
            List<Long> resultList = getList(n, c);
            System.out.println(n + " " + c + ": " +
                    resultList.stream().sorted()
                            .map(String::valueOf)
                            .collect(Collectors.joining(" ")));
            System.out.println("");
        }
    }

    private static List<Long> getList(int n, int c) {
        List<Long> newList = primeList.stream().filter(i -> i <= n).collect(Collectors.toList());
        newList.add(0, 1L);
        int currentSize = newList.size();
        if(currentSize % 2 == 0){
            if(currentSize <= 2 * c){
                return newList;
            }
            int mid = currentSize/2;
            return IntStream.range(1, currentSize).filter(i -> i >= mid - c + 1 && i <= mid + c)
                    .mapToObj(i -> newList.get(i -1)).collect(Collectors.toList());
        }else {
            if(currentSize <= 2 * c -1){
                return newList;
            }
            int mid = (currentSize + 1)/2;
            return IntStream.range(1, currentSize).filter(i -> i >= mid - c  + 1 && i <= mid + c -1)
                    .mapToObj(i -> newList.get(i -1)).collect(Collectors.toList());
        }
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

