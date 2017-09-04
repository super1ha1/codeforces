package problem_set;

import java.util.*;

public class Main {

    private static List<Long> primeList = new ArrayList<>();
    private static  long sieve_ll;
    private static BitSet bitSet = new BitSet(10000010);
    public static void main(String[] args) throws Exception {
        sieve(1000000);
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()){
            int n = sc.nextInt();
            process(n);
        }
    }

    private static void process(int n) {
        if(isPrime(n)){
            if(isEmirp(n)){
                System.out.println(String.format("%d is emirp.", n));
            }else {
                System.out.println(String.format("%d is prime.", n));
            }
        }else {
            System.out.println(String.format("%d is not prime.", n));
        }
    }

    private static boolean isEmirp(int n) {
        char[] chars = String.valueOf(n).toCharArray();
        String out = "";
        for(int j = chars.length -1; j >= 0; j--){
            out += String.valueOf(chars[j]);
        }
        int reverse = Integer.valueOf(out);
        return n != reverse &&  isPrime(reverse);
    }

    private static void sieve(long bound) {
        sieve_ll  = bound + 1;
        bitSet.set(0, (int) sieve_ll, true);
        bitSet.set(0, false);
        bitSet.set(1, false);

        for(long i = 2; i <= sieve_ll; i++) {
            if (bitSet.get((int) i)) {
                for (long j = i * i; j <= sieve_ll; j += i) {
                    bitSet.set((int) j, false);
                }
                primeList.add(i);
            }
        }
    }

    private static boolean isPrime(long n){
        if(n <= sieve_ll){
            return bitSet.get((int) n);
        }
        for(long i: primeList){
            if(n % i == 0){
                return false;
            }
        }
        return true;
    }
}

