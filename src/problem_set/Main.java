package problem_set;

import java.util.*;

public class Main {

    private static BitSet bitSet = new BitSet(10000010);
    private static List<Long> primeList = new ArrayList<>();
    private static long[] result = new long[10000010];
    private static long[] countValue = new long[10000010];
    private static long size_ll;

    public static void main(String[] args) throws Exception {

        sieve(10000000);
        prepareCount(10000000);

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            System.out.println(result[n]);
        }
    }

    private static void prepareCount(int bound) {
        //prepare count value
        countValue[0] = 1;
        countValue[1] = 1;
        countValue[2] = 1;
        int currentNumber = 2;
        while (true){
            currentNumber++;
            countValue[currentNumber] = countValue[currentNumber -1] + primeFactor(currentNumber).size();
            if(countValue[currentNumber] >= bound){
                break;
            }
        }

        //calculate n
        long currentN = 1, currentCount = 0;
        for(int i = 0; i < countValue.length; i++){
            if(countValue[i] <= currentN){
                currentCount++;
            }else {
                for(long index = currentN; index < countValue[i]; index++){
                    result[(int)index] = currentCount;
                }
                currentN = countValue[i];
                currentCount = i + 1;
            }
        }
    }

    private static void sieve(long bound) {
        size_ll = bound + 1;
        bitSet.set(0, bitSet.size(), true);
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
}

