package problem_set;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            int maxSoFar = -1000000;
            int maxEndHere = 0;
            for (int i = 0; i < n; i++) {
                maxEndHere += sc.nextInt();
                if (maxEndHere > maxSoFar) {
                    maxSoFar = maxEndHere;
                }
                if (maxEndHere < 0) {
                    maxEndHere = 0;
                }
            }
            if (maxSoFar <= 0) {
                System.out.println("Losing streak.");
            } else {
                System.out.println(String.format("The maximum winning streak is %d.", maxSoFar));
            }
        }
    }
}

