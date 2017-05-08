package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    static final int LARGE_INT = -1000000000;
    static int [][] can_reach, price;
    static int n, k, sum = 0;
    static List<List<Integer>> adList = new ArrayList<List<Integer>>();
    static List<Integer> resultList = new ArrayList<Integer>();
    static List<Integer> tempList = new ArrayList<Integer>();
    static int[] visisted;
    //    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0){
            n = sc.nextInt();
            k = sc.nextInt();
            adList.clear();
            visisted = new int[n+1];
            for(int i = 0 ; i <= n ; i++){
                List<Integer> newList = new ArrayList<Integer>();
                adList.add(newList);
            }
            for(int i = 0 ; i < k; i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                adList.get(first).add(second);
                adList.get(second).add(first);
            }
            processInput();
            printResult();
        }
    }

    private static void printResult() {
        Collections.sort(resultList);
        System.out.println(resultList.size());
        String output = "";
        for(int i: resultList){
            output += i + " ";
        }
        System.out.println(output.trim());
    }



    private static void processInput() {
        int maxValue = Integer.MIN_VALUE;
        List<Integer> currentList = new ArrayList<Integer>();
        for(int i = 1; i < adList.size(); i++){

            tempList.clear();
            currentList.clear();

            recursive(i, currentList);

            if(tempList.size() > maxValue){
                maxValue = tempList.size();
                resultList.clear();
                resultList.addAll(tempList);

                //take all alone node
                for(int j = 1 ; j <= n; j++){
                    if(visisted[j] != 1){
                        resultList.add(j);
                    }
                }
            }
        }

    }

    private static void recursive(int nodeIndex, List<Integer> currentList) {
        if(currentList.size() == n || visisted[nodeIndex] == 1 ){
            return;
        }

        visisted[nodeIndex] = 1;
        currentList.add(nodeIndex);

        if(okToMark(nodeIndex)){
            tempList.add(nodeIndex);
        }else {

        }

        for(int neighbor: adList.get(nodeIndex)){
            if(visisted[neighbor] != 1){
                //mark as black
                recursive(neighbor, currentList);
            }
        }

    }

    private static boolean okToMark(int nodeIndex) {
        for(int neighbor: adList.get(nodeIndex)){
            if(visisted[neighbor] == 1 && tempList.contains(neighbor)){
                return false;
            }
        }
        return true;
    }


}

