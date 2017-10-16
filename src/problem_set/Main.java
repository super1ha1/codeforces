package problem_set;

import java.util.Scanner;

public class Main {

    private int number, n;
    private int[] values;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        number = sc.nextInt();
        for (int route = 1; route <= number; route++) {
            n = sc.nextInt();
            values = new int[n - 1];
            for (int i = 0; i < n - 1; i++) {
                values[i] = sc.nextInt();
            }
            int maxSoFar = Integer.MIN_VALUE;
            int maxEndHere = 0;
            int start = n;
            int end = 1;

            int startLocal = 1;
            int endLocal;

            for (int i = 0; i < n - 1; i++) {
                maxEndHere += values[i];

                if (maxEndHere > maxSoFar) {
                    maxSoFar = maxEndHere;
                    endLocal = i + 2;

                    start = startLocal;
                    end = endLocal;
                }

                if (maxEndHere == maxSoFar) {
                    endLocal = i + 2;

                    // check length
                    if(endLocal - startLocal > end - start){
                        start = startLocal;
                        end = endLocal;
                    } else if(endLocal - startLocal == end - start){
                        // no thing
                    }
                }

                if (maxEndHere < 0) {
                    maxEndHere = 0;
                    startLocal = i + 2;
                }
            }

            if (maxSoFar < 0) {
                System.out.println(String.format("Route %d has no nice parts", route));
            } else {
                System.out.println(String.format("The nicest part of route %d is between stops %d and %d", route, start,
                                end));
            }
        }
    }
}

