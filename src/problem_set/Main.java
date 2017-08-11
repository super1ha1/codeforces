package problem_set;

import java.util.*;

public class Main {

    private static Map<Integer, Map<Character, Integer>> map = new HashMap<>();
    private static List<Character> list = Arrays.asList('i','v','x','l','c');
    private static int[][] totalChar = new int[101][5];
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int sum = n;
            int left = n / k;
            int leftOver = n - left * k;
            while (left > 0){
                sum += left;
                left = left + leftOver;
                int newLeft = left/k;
                leftOver = left - newLeft * k;
                left = newLeft;
            }
            System.out.println(sum);
        }
    }
}

