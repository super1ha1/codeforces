package problem_set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final int largeValue = 10000;
    private int[] subsequences;
    private List<Integer> list;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        list = new ArrayList<>();
        while (sc.hasNext()) {
            list.add(sc.nextInt());
        }

        subsequences = new int[list.size()];
        int max = -1;
        int index = -1;
        for (int i = 0; i < list.size(); i++) {
            int value = LIS(i);
            if (value >= max) {
                index = i;
                max = value;
            }
        }

        List<Integer> result = new ArrayList<>();
        int value = list.get(index);
        result.add(value);
        int j = index - 1;
        while (j >= 0) {
            if (list.get(j) < value) {
                value = list.get(j);
                result.add(value);
            }
            j--;
        }
        System.out.println(result.size());
        System.out.println("-");
        Collections.reverse(result);
        for (int i : result) {
            System.out.println(i);
        }
    }

    private int LIS(int index) {
        if (subsequences[index] > 0) {
            return subsequences[index];
        }

        int value = 1;
        for (int i = 0; i < index; i++) {
            if (list.get(i) < list.get(index)) {
                value = Math.max(value, 1 + LIS(i));
            }
        }
        return subsequences[index] = value;
    }
}

