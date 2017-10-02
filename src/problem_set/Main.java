package problem_set;

import java.util.Scanner;

public class Main {
    private int n, h;
    private int[] allocation;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        for(int i = 0; i < testCase; i++){
            n = sc.nextInt();
            h = sc.nextInt();
            allocation = new int[n];
            backTrack(0, n - h, h);

            if(i < testCase - 1){
                System.out.println("");
            }
        }
    }

    private void backTrack(int count, int numberZero, int numberOne) {
        if(count == n){
            printSolution();
            return;
        }

        if(numberZero > 0){
            //try 0
            allocation[count] = 0;
            backTrack(count + 1, numberZero - 1, numberOne);

        }

        if(numberOne > 0){
            //try 1
            allocation[count] = 1;
            backTrack(count + 1, numberZero, numberOne - 1);
        }
    }

    private void printSolution() {
        String output = "";
        for(int i = 0; i < n; i++){
            output += allocation[i];
        }
        System.out.println(output);
    }
}

