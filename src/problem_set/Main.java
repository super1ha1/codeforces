package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static int numComponent, n, sum;
    private static int[][] array;
    private static char[][] chars;
    private static List<String> queryList = new ArrayList<>();
    private static List<Integer> primeList = new ArrayList<>();
    private static long sieve_ll;
    private static BitSet bitSet = new BitSet(10000000);
    public static void main(String[] args) throws Exception {

        sieve(1000000);

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            n = sc.nextInt();
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
        int value = getA();
        if(value > -1){
            System.out.println(String.format("%d = %d + %d", n, value, n - value));
        }else {
            System.out.println("Goldbach's conjecture is wrong.");
        }
    }

    private static int getA() {
        for(int i = 1; i < primeList.size(); i++){
            int value = primeList.get(i);
            if(value > n/2){
                break;
            }
            int other = n - value;
            if(bitSet.get(other)){
                return value;
            }
        }
        return -1;
    }

}

