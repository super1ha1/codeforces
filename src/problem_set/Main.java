package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private Map<Integer, List<Integer>> map = new HashMap<>();
    private int n;
    private int [] allocation;
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (true){
            n = sc.nextInt();
            if(n == 0){
                break;
            }
            int edge = sc.nextInt();
            map.clear();
            allocation = new int[n];

            for(int i = 0; i < edge; i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                if(!map.containsKey(first)){
                    map.put(first, new ArrayList<>());
                }
                if(!map.containsKey(second)) {
                    map.put(second, new ArrayList<>());
                }
                map.get(first).add(second);
                map.get(second).add(first);
            }

            boolean possible = backTrack(0);
            System.out.println(possible ? "BICOLORABLE." : "NOT BICOLORABLE.");
        }

    }

    private boolean backTrack(int count) {
        if(count == n){
            return true;
        }
        for(int color = 1; color <= 2; color++){
            if(canColor(count, color)){
                allocation[count] = color;
                if(backTrack(count + 1)){
                    return true;
                }
                allocation[color] = 0;
            }
        }
        return false;
    }

    private boolean canColor(int index, int color) {
        if(map.containsKey(index)){
            List<Integer> list = map.get(index);
            for(int neighbor: list){
                if(allocation[neighbor] == color){
                    return false;
                }
            }
        }
        return true;
    }
}

