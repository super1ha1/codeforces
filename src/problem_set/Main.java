package problem_set;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()) {
            int n = sc.nextInt();
            int[] array = new int[n];
            for(int i = 0; i < n; i++){
                array[i] = sc.nextInt();
            }
            System.out.println(test(array) ? "Yes" : "No");
        }
    }

    private static boolean test(int[]array) {
        if(isOdd(array, 0, array.length - 1)){
            return true;
        }
        for(int i = 1; i < array.length - 2 ; i += 2){
            int first = backtrack(array, 0, i -1);
            int second = backtrack(array, i, array.length -1);
            if(first > 0 && second > 0 && (first + second) % 2 == 1){
                return true;
            }
        }
        return false;
    }

    private static int backtrack(int[] array, int start, int end) {
        if(isOdd(array, start, end)){
            return 1;
        }
        for(int i = start + 1; i < end - 1 ; i += 2){
            int first = backtrack(array, start, i - 1);
            int second = backtrack(array, i, end);
            if(first > 0 && second > 0){
                return first + second;
            }
        }
        return -1;
    }

    private static boolean isOdd(int[] array, int start, int end) {
        if((end - start + 1) % 2 == 0){
            return false;
        }
        if(array[start] % 2 == 0 || array[end] % 2 == 0){
            return false;
        }
        return true;
    }
}

