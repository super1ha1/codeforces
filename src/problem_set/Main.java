package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static long numComponent, n, sum;
    private static int[][] array;
    private static char[][] chars;
    private static List<String> queryList = new ArrayList<>();
    private static List<Integer> primeList = new ArrayList<>();
    private static long sieve_ll;
    private static BitSet bitSet = new BitSet(10000010);
    private static int first, second;
    public static void main(String[] args) throws Exception {

        sieve(10000000);

//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            first = sc.nextInt();
            second = sc.nextInt();

            if(first == 0 && second == 0){
                break;
            }
            int min = Math.min(first, second);
            int max = Math.max(first, second);

            List<List<Integer>> resultList = new ArrayList<>();
            List<Integer> updatedList = primeList.stream().filter(i -> i >= min && i <= max).collect(Collectors.toList());
            int i = 0;
            while (i <= updatedList.size() -3){
                int distance = updatedList.get(i + 1) - updatedList.get(i);
                int j = i + 1;
                while (j <= updatedList.size() - 2 && updatedList.get(j + 1) - updatedList.get(j) == distance){
                    j++;
                }
                if(j > i + 1){
                    List<Integer> newList = new ArrayList<>();
                    for(int index = i; index <= j; index++){
                        newList.add(updatedList.get(index));
                    }
                    resultList.add(newList);
                    i = j;
                }else {
                    i = i + 1;
                }
            }
            for(List<Integer> l: resultList){
                String s = l.stream().map(String::valueOf).collect(Collectors.joining(" "));
                System.out.println(s);
                if (s.equals("12641 12647 12653")){
//                    System.out.println(first + " " + second);
                }

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

