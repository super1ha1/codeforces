package problem_set;

import java.io.File;
import java.util.*;

public class Main {


    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int n = sc.nextInt();
        sc.nextLine();
        String line = sc.nextLine().trim();
        char[] chars = line.toCharArray();

        int[] arrayZero = new int[n];
        for(int i = 0; i < n; i++){
            int value = Integer.valueOf(String.valueOf(chars[i]));
            if(value == 1){
                arrayZero[i] += 1;
            } else {
                arrayZero[i] += -1;
            }
            if(i > 0){
                arrayZero[i] += arrayZero[i -1];
            }
        }

        int max = 0;
        for(int j = n -1; j >= 0; j--){
            if(arrayZero[j] == 0 && j + 1 > max){
                max = j + 1;
                break;
            }
            for(int i = 0; i <= j; i++){
                if(Math.abs(arrayZero[j] - arrayZero[i]) == 0){
                    max = Math.max(max, j - i);
                    break;
                }
            }
        }
        System.out.println(max);
    }
}



