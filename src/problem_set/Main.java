package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private int total, n;
    private int [] value, allocation;
    private int maxSum;
    private List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));


        while (sc.hasNext()){
            total = sc.nextInt();
            n = sc.nextInt();
            value = new int[n];
            allocation = new int[n];

            for (int i = 0; i < n; i++){
                value[i] = sc.nextInt();
            }

            resultList.clear();
            maxSum = 0;
            backTrack(0, 0);
            System.out.println(getList() + " sum:" + maxSum);
        }

    }

    private String getList() {
        return resultList.stream().map(String::valueOf).collect(Collectors.joining(" "));
    }

    private void backTrack(int counter, int currentSum) {
        if(counter == n){
            if(currentSum <= total && currentSum > maxSum){
                maxSum = currentSum;
                resultList.clear();
                for(int i = 0; i < n; i++){
                    if(allocation[i] == 1){
                        resultList.add(value[i]);
                    }
                }
            }
            return;
        }

        allocation[counter] = 1;
        backTrack(counter + 1, currentSum + value[counter]);

        allocation[counter] = 0;
        backTrack(counter + 1, currentSum);

    }
}

