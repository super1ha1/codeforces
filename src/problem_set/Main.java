package problem_set;

import java.util.Scanner;

public class Main {

    private int[] array;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
        //        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        int counter = 0;
        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            counter++;
            System.out.println(String.format("Case %d:", counter));
            array = new int[n];
            for (int i = 0; i < n; i++) {
                array[i] = sc.nextInt();
            }
            int m = sc.nextInt();
            for (int i = 0; i < m; i++) {
                int query = sc.nextInt();
                System.out.println(String.format("Closest sum to %d is %d.", query, nearest(query)));
            }
        }
    }

    private int nearest(int query) {
        int n = array.length;
        int bestSoFar = Integer.MAX_VALUE - 1000;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (Math.abs(array[i] + array[j] - query) < Math.abs(bestSoFar - query)) {
                    bestSoFar = array[i] + array[j];
                }
            }
        }
        return bestSoFar;
    }
}

