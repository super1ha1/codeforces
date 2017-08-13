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

        while (sc.hasNext()){
            int n  = sc.nextInt();
            if(n == 0){
                break;
            }
            resultSet.clear();
            calculate(n);
            if(resultSet.size() > 0){
                int min = Integer.MAX_VALUE;
                for(int i: resultSet){
                    if(i < min){
                        min = i;
                    }
                }
                int x = (int) Math.cbrt(n + min * min * min);
                System.out.println(x + " " + min);
            }else {
                System.out.println("No solution");
            }
        }
    }

    private static void calculate(int n) {
        Set<Integer> divisibleByN = new HashSet<>();
        int square = (int) Math.sqrt(n);
        for(int i = 1; i <= square; i++){
            if(n % i == 0){
                divisibleByN.add(i);
                divisibleByN.add(n/i);
            }
        }
        for(int A: divisibleByN){
            int D = n/A;
            makeXY(A, D);
        }
    }

    private static void makeXY(int a, int d) {
        int threeB = d - a * a;
        if(threeB < 0 || threeB % 3 != 0){
            return;
        }
        int B = threeB/3;
        int delta = a * a + 4 * B;
        double sqrt = Math.sqrt(delta);
        int intsqrt = (int) sqrt;
        if(intsqrt  * 1.0 != sqrt){
            return;
        }
        if((intsqrt - a > 0) && (intsqrt - a) % 2 == 0){
            int firstY = (int) (intsqrt - a)/2;
            resultSet.add(firstY);
        }
    }
}

