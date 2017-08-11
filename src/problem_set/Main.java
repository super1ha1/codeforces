package problem_set;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            int sum = 0;
            int round = n/m;
            int left = n - round * m;
            int newTotal = round + left;
            sum += round;
            while (newTotal > 1){
                round = newTotal / m;
                left = newTotal - round * m;
                newTotal = round + left;
                sum += round;
                if(newTotal > 1 && newTotal < m){
                    break;
                }
            }

            if(newTotal == 1){
                System.out.println(sum);
            }else {
                System.out.println("cannot do this");
            }
        }
    }
}

