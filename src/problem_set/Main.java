package problem_set;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            int n = sc.nextInt();
            int k = sc.nextInt();
            int [] array = new int[n];
            for(int i  = 0; i < n; i++){
                array[i] = sc.nextInt();
            }
            int currentK = k;
            int left = 0;
            int days = 0;
            int sum = 0;
            for(int i  = 0; i < n; i++){
                sum = array[i] + left;
                if(sum > 8){
                    currentK = currentK - 8;
                    left =  (sum - 8);
                }else {
                    currentK = currentK - sum;
                    left = 0;
                }
                if(currentK <= 0){
                    days = i;
                    break;
                }
            }
            if(currentK <= 0){
                System.out.println(days + 1);
            }else {
                System.out.println("-1");
            }
        }
    }
}

