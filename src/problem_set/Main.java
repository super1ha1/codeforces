package problem_set;

import java.math.BigInteger;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            int B = sc.nextInt();
            int P = sc.nextInt();
            int M = sc.nextInt();
            System.out.println(BigInteger.valueOf(B).modPow(BigInteger.valueOf(P), BigInteger.valueOf(M)));
        }
    }
}

