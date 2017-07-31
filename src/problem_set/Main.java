package problem_set;

import java.util.Scanner;

public class Main {

//    private static Scanner sc;
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) throws Exception {
//        sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));

        while (true){
            int n = sc.nextInt();
            if(n == 0){
                break;
            }
            sc.nextLine();
            char[][] array = new char[n][n];
            for(int i = 0; i < n; i++){
                String line = sc.nextLine();
                array[i] = line.trim().toCharArray();
            }
            System.out.println(process(array));
        }
    }

    private static int process(char[][] array) {
        int len = array.length;
        return 0;
    }

}

