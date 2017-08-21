package problem_set;

import java.io.File;
import java.net.CookieHandler;
import java.util.*;

public class Main {

    private static BitSet firstBitSet = new BitSet(1000000010);
    private static BitSet secondBitSet = new BitSet(1000000010);

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//                Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()) {
            int n = sc.nextInt();
            boolean result = true;
            setup();
            for (int i = 0; i < n; i++) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                if (isFirstOK(start, end)) {
                    firstBitSet.set(start, end, true);
                } else if (isSecondOK(start, end)) {
                    secondBitSet.set(start, end, true);
                } else {
                    result = false;
                }
            }
            System.out.println(result ? "YES" : "NO");
        }
    }

    private static boolean isSecondOK(int start, int end) {
        return secondBitSet.get(start, end).cardinality() == 0;
    }

    private static void setup() {
        firstBitSet.set(0, firstBitSet.size(), false);
        secondBitSet.set(0, firstBitSet.size(), false);
    }

    public static boolean isFirstOK(int start, int end) {
        return firstBitSet.get(start, end).cardinality() == 0;
    }
}

