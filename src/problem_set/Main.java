package problem_set;

import java.io.File;
import java.util.BitSet;
import java.util.Scanner;

public class Main {

    private static BitSet firstBitSet = new BitSet(1000000010);
    private static BitSet secondBitSet = new BitSet(1000000010);

    private static long a, b, x, y, d;
    public static void main(String[] args) throws Exception {

//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()){
            a =sc.nextLong();
            b = sc.nextLong();
            extendEuclid(a, b);
            update();
            System.out.println(String.format("%d %d %d", x, y, d));
        }
    }

    private static void update() {
        if(Math.abs(x) < Math.abs(y)){
            // increase x, reduce y
            
        } else if(Math.abs(x) > Math.abs(y)){
            // decrease x, increase y

        } else {
            //do nothing
        }
    }

    private static void extendEuclid(long a, long b) {
        if(b == 0){
            x = 1;
            y = 0;
            d = a;
            return;
        }
        extendEuclid(b, a % b);
        long x1 = y;
        long y1 = x - (a/b) * y;
        x = x1;
        y = y1;
    }

}

