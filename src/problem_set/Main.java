package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static Set<Integer> resultSet = new HashSet<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0) {
            int n = sc.nextInt();
            double value = 1.0 * n;
            double result = (((((567 * value)/9) + 7492) * 235)/47) - 498;
            System.out.println((Math.abs((int)result)/10) %10);
        }
    }
}

