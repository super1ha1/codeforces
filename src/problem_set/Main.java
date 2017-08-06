package problem_set;

import java.io.File;
import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] colors;
    private static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int convert = convert(n);
            System.out.println(n + " converts to " + convert);
        }
    }

    private static int convert(int n) {
        byte[] array =  ByteBuffer.allocate(4).putInt(n).array();
        byte[] reverse = new byte[4];
        for(int i  = 0; i < 4; i++){
            reverse[i] = array[3 - i];
        }
        int reverInt = ByteBuffer.wrap(reverse).getInt();
        return reverInt;
    }

    private static int convert2(int n) {
        byte[] array =  BigInteger.valueOf(n).toByteArray().clone();
        byte[] reverse = new byte[4];
        for(int i  = 0; i < 4; i++){
            reverse[i] = array[3 - i];
        }
        int reverInt = new BigInteger(reverse).intValue();
        return reverInt;
    }
}

