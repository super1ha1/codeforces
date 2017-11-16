package problem_set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private List<Integer> list;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int value = -1;
        try {
            list = new ArrayList<>();
            while (sc.hasNext()) {
                value = sc.nextInt();
                list.add(value);
            }
        } catch (Exception e) {
            System.out.println("value: " + value);
            e.printStackTrace();
        }
        int maxLength = 1, bestEnd = 0;
        int n = list.size();
        int[] dp = new int[n];
        int[] prev = new int[n];
        dp[0] = 1;
        prev[0] = -1;

        for (int i = 1; i < n; i++) {
            dp[i] = 1;
            prev[i] = -1;
            for (int j = i - 1; j >= 0; j--) {
                if (dp[j] + 1 > dp[i] && list.get(j) < list.get(i)) {
                    dp[i] = dp[j] + 1;
                    prev[i] = j;
                }
            }
            if (dp[i] >= maxLength) {
                maxLength = dp[i];
                bestEnd = i;
            }
        }
        List<Integer> result = new ArrayList<>();
        int index = bestEnd;
        int currentValue = list.get(index);
        result.add(currentValue);

        while (true) {
            index = prev[index];
            if (index < 0) {
                break;
            }
            currentValue = list.get(index);
            result.add(currentValue);
        }

        Collections.reverse(result);
        System.out.println(result.size());
        System.out.println("-");
        for (int i : result) {
            System.out.println(i);
        }
    }
}

