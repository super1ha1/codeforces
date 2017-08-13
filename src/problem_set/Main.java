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

        int count = 0;
        while (sc.hasNext()){
            int R = sc.nextInt();
            int N = sc.nextInt();
            if(R == 0 && N == 0 ) {
                break;
            }
            count++;
            int left = R - N;
            if(left <= 0){
                System.out.println("Case " +  count + ": 0");
            }else {
                double need = (left * 1.0)/N;
                if(need > 26.0){
                    System.out.println("Case " +  count + ": impossible");
                }else {
                    int result = (int) Math.ceil(need);
                    System.out.println("Case " +  count + ": " + result);
                }
            }
        }
    }
}

