package problem_set;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
//    private static Scanner sc;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
//        sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));

        int count = 0;
        while (true){
            int N = sc.nextInt();
            if(N == 0){
                break;
            }
            if(count > 0){
                System.out.println("");
            }
            process(N);
            count++;
        }
    }

    private static void process(int N) {
        boolean hasResult = false;
        for(int i = 1234; i <= 98765; i++){
            int j = i * N;
            if(j > 98765){
                break;
            }
            if(check(i, j)){
                hasResult = true;
                System.out.println(String.format("%05d / %05d = %d", j, i, N));
            }
        }
        if(!hasResult){
            System.out.println(String.format("There are no solutions for %d.", N));
        }
    }

    private static boolean check(int i, int j) {
        String figure = String.format("%05d%05d", i, j);
        char[] array = figure.toCharArray();
        Arrays.sort(array);
        String sortedStr = new String(array);
        return "0123456789".equals(sortedStr);
    }
}

