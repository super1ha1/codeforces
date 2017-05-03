package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    static final int LARGE_INT = -1000000000;
    static int [][] memo, price;
    static int M, C;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        //        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0){
            M  = sc.nextInt();
            C = sc.nextInt();
            memo = new int[210][25];
            resetMemo(memo);
            price = new int[25][25];
            for(int i = 0 ; i < C; i++){
                price[i][0] = sc.nextInt();
                for(int j = 1 ; j <= price[i][0]; j++){
                    price[i][j] = sc.nextInt();
                }
            }
            //            printInput(price);
            processInput();
        }
    }

    private static void resetMemo(int[][] memo) {
        for(int i = 0; i < memo.length; i++){
            for(int j = 0; j < memo[i].length; j++){
                memo[i][j] = -1;
            }
        }
    }

    private static int shop(int moneyLeft, int garmentId){
        if(moneyLeft < 0){
            return LARGE_INT;
        }
        if(garmentId == C ){
            return M - moneyLeft;
        }
        if(memo[moneyLeft][garmentId] != -1){
            return memo[moneyLeft][garmentId];
        }
        int maxValue = LARGE_INT;
        for(int model = 1 ; model <= price[garmentId][0]; model++){
            maxValue = Math.max(maxValue, shop(moneyLeft - price[garmentId][model], garmentId + 1));
        }
        return (memo[moneyLeft][garmentId] = maxValue);
    }

    private static void processInput() {
        int maxValue = shop(M, 0);
        if(maxValue < 0){
            System.out.println("no solution");
        }else {
            System.out.println(maxValue);
        }
    }

    private static void printInput(int[][] input) {
        String output = "";
        for(int i = 0 ; i < input.length; i++){
            output = "";
            for(int j = 0; j < input[i].length; j++){
                output += " " + input[i][j];
            }
            System.out.println(output);
        }
    }

}

