package problem_set;

import java.util.BitSet;
import java.util.Scanner;

public class Main {

    private static BitSet bitset = new BitSet(100);

    private static int n;
    private static int[] visit, allocation;
    public static void main(String[] args) throws Exception {
        sieve(100);

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int counter = 1;
        while (sc.hasNext()){
            n = sc.nextInt();

            if(counter > 1){
                print("");
            }
            print(String.format("Case %d:", counter));

            allocation = new int[50];
            visit = new int[50];
            allocation[0] = 1;
            visit[1] = 1;
            backTrack(1);
            counter++;
        }
    }

    private static void print(String s){
        System.out.println(s);
    }
    private static void backTrack(int current){
        if(current == n && bitset.get(allocation[n-1] + allocation[0])){
            String output = "";
            for(int i = 0; i < n; i++){
                output += allocation[i];
                if(i != n -1){
                    output += " ";
                }
            }
            print(output);
            return;
        }

        for(int i = 2; i <= n; i++){
            if(visit[i] == 0 && bitset.get(allocation[current -1] + i)) {
                visit[i] = 1;
                allocation[current] = i;
                backTrack(current + 1);
                visit[i] = 0;
            }
        }
    }

    private static void sieve(int bound){
        bitset.set(0, bitset.size(), true);
        bitset.set(0, false);
        bitset.set(1, false);

        for(int i = 0; i <= bound; i++){
            if(bitset.get(i)){
                for(int j = i * i; j <= bound; j += i){
                    bitset.set(j, false);
                }
            }
        }
    }
}

