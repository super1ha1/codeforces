package problem_set;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static BitSet bitSet = new BitSet(100010);
    private static List<Long> primeList = new ArrayList<>();
    private static long sieve_ll;
    private static int[] array = new int[10010];
    public static void main(String[] args) throws Exception {
        sieve(10010);
        prepare();
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int min = Math.min(a, b);
            int max = Math.max(a, b);
            int counter = 0;
            for(int i = min; i <= max; i++){
                if(array[i] == 1){
                    counter++;
                }
            }
            System.out.println(String.format("%.2f", ((double) counter * 100)/(max - min + 1)));
        }
    }

    private static void prepare() {
        for(int i = 0; i <= 10000; i++){
            long value = i * i + i + 41;
            if(isPrime(value)){
                array[i] = 1;
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

