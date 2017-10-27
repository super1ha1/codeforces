package problem_set;

import java.util.*;

public class Main {

    private static final int largeValue = 1000;
    private int[][] coins;
    private int value;
    private int[][] dp;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0) {
            int n = sc.nextInt();
            coins = new int[n][2];
            value = sc.nextInt();

            for (int i = 0; i < n; i++) {
                coins[i][0] = sc.nextInt();
                coins[i][1] = sc.nextInt();
            }
            List<Map.Entry<Integer, Integer>> square = findSquare(value);
            int min = largeValue;

            for (Map.Entry<Integer, Integer> entry: square) {
                min = Math.min(min, findCoin(entry.getKey(), entry.getValue()));
            }

            if (min > 0 && min < largeValue) {
                System.out.println(min);
            } else {
                System.out.println("not possible");
            }
        }
    }

    private int findCoin(int x, int y) {
        dp = new int[310][310];
        dp[0][0] = 0;
        return topDownDP(x, y);
    }

    private int topDownDP(int x, int y) {
        if(x < 0 || y < 0){
            return largeValue;
        }
        if(x == 0 && y == 0){
            return 0;
        }

        if(dp[x][y] > 0){
            return dp[x][y];
        }
        int min = largeValue;
        for(int[] coinValue: coins){
            min = Math.min(min, 1 + topDownDP(x - coinValue[0], y - coinValue[1]));
        }
        return dp[x][y] = min;
    }

    private List<Map.Entry<Integer, Integer>> findSquare(int value) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        for (int i = 0; i <= value; i++) {
            int square = value * value - i * i;
            if (isPerfectSquare(square)) {
                list.add(new AbstractMap.SimpleEntry<>(i, (int) Math.sqrt(square)));
            }
        }
        return list;
    }

    private boolean isPerfectSquare(int square) {
        int root = (int) Math.sqrt(square);
        return square == root * root;
    }
}

