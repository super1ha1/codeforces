package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    static final int LARGE_INT = -1000000000;
    static int [][] can_reach, price;
    static int n, k, currentIndex, maxValue;
    static List<List<Integer>> adList = new ArrayList<List<Integer>>();
    static List<Integer> resultList = new ArrayList<Integer>();
    static List<Integer> blackList = new ArrayList<Integer>();
    static List<Integer> whiteList = new ArrayList<Integer>();
    static List<Integer> notConnectList = new ArrayList<Integer>();
    static int[] visisted;
    //    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/macbook/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0){
            n = sc.nextInt();
            k = sc.nextInt();
            adList.clear();
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

            for(int i = 1; i < adList.size(); i++){
                if(adList.get(i).size() == 0){
                    notConnectList.add(i);
                }
            }

            processInput();
            printResult();
        }
    }

    private static void printResult() {
        resultList.addAll(notConnectList);
        Collections.sort(resultList);
        System.out.println(resultList.size());
        String output = "";
        for(int i: resultList){
            output += i + " ";
        }
        System.out.println(output.trim());
    }



    private static void processInput() {
        maxValue = Integer.MIN_VALUE;
        for(int i = 1; i < adList.size(); i++){

            blackList.clear();
            whiteList.clear();
            visisted = new int[n+1];

            currentIndex = i;
            recursive(i);

        }

    }

    private static void recursive(int nodeIndex) {
        if(blackList.size() + whiteList.size() + notConnectList.size() == n){
            if(blackList.size() >= maxValue){
                maxValue = blackList.size();
                resultList.clear();
                resultList.addAll(blackList);
            }
        }
        if(visisted[nodeIndex] == 1 ){
            return;
        }

        visisted[nodeIndex] = 1;

        for(int i = 0; i <= 1; i++){
            if(i == 0){
                blackList.add(nodeIndex);
                for(int neighbor: adList.get(nodeIndex)){
                    if(visisted[neighbor] != 1){
                        //mark as black
                        recursive(neighbor);
                    }
                }
                blackList.remove(Integer.valueOf(nodeIndex));
            }else {
                whiteList.add(nodeIndex);
                for(int neighbor: adList.get(nodeIndex)){
                    if(visisted[neighbor] != 1){
                        //mark as black
                        recursive(neighbor);
                    }
                }
                whiteList.remove(Integer.valueOf(nodeIndex));
            }
        }

    }

    private static boolean okToMark(int nodeIndex) {
        for(int neighbor: adList.get(nodeIndex)){
            if(visisted[neighbor] == 1 && blackList.contains(neighbor)){
                return false;
            }
        }
        return true;
    }


}

