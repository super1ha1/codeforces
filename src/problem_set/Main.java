package problem_set;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        int testCase = sc.nextInt();
        int count = 0;
        while (testCase-- > 0){
            int N = sc.nextInt();
            int K = sc.nextInt();
            int P = sc.nextInt();
            int result = ((P % N) + K) % N;
            if(result == 0) result = N;
            System.out.println(String.format("Case %d: %d", ++count, result));
        }
    }
}

