package problem_set;

import java.util.*;

public class Main {

    private static long sieve_ll;
    private static List<Long> primeList = new ArrayList<>();
    private static BitSet bitSet = new BitSet(10000010);

    public static void main(String[] args) throws Exception {
        sieve(10000000);

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int k = sc.nextInt();
            if(n == 0 && k == 0){
                break;
            }
            System.out.println(process(n, k));
        }
    }

    private static long process(int n, int k) {
        if(k == 0){
            return 0;
        }
        if(k == 1){
            return n;
        }
        List<Long> numerator = new ArrayList<>();
        for(int i = n - k + 1 ; i <= n; i++){
            numerator.addAll(primeFactors(i));
        }
        Collections.sort(numerator);

        List<Long> denominator = new ArrayList<>();
        for(int i = 2; i <= k; i++){
            denominator.addAll(primeFactors(i));
        }
        Collections.sort(denominator);
        for(long i: denominator){
            if(numerator.contains(i)){
                numerator.remove(Long.valueOf(i));
            }
        }

        long value = 1;
        for(long i: numerator){
            value = value * i;
        }
        return value;
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

    private static List<Long> primeFactors(long n){
        List<Long> list = new ArrayList<>();
        int index = 0;
        long value = primeList.get(index);

        while (n != 1 && value * value <= n){
            while (n % value == 0){
                n = n / value;
                list.add(value);
            }
            value = primeList.get(++index);
        }

        if(n != 1){
            list.add(n);
        }
        return list;
    }
}

