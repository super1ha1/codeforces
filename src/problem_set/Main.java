package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] num, low, dfs_parent;
    private static int  dfsNumberCounter;
    private static Stack<Integer> stack = new Stack<>();
    private static List<List<Integer>> resultList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        int counter = 0;
        while (true){
            int n = sc.nextInt();
            if (n <= 0){
                break;
            }
            sc.nextLine();

            if(counter > 0){
                System.out.println();
            }
            counter++;
            map.clear();
            resultList.clear();

            while (n-- > 0){
                String line = sc.nextLine();
                String[] lines = line.trim().split(" ");
                int base = lines[lines.length -1].charAt(0) - 'A';
                if(!map.keySet().contains(base)){
                    map.put(base, new ArrayList<>());
                }
                for(int i  = 0; i < lines.length -1; i++){
                    int current = lines[i].charAt(0) - 'A';
                    if(!map.keySet().contains(current)){
                        map.put(current, new ArrayList<>());
                    }
                    map.get(base).add(current);
                }
            }
            process();
        }
    }

    private static void process() {
        num = new int[26];
        low = new int[26];
        dfsNumberCounter = 1;
        for (int c: map.keySet()){
            if(num[c] == 0){
                stack.clear();
                tarjanSCC(c);
            }
        }

        for(List<Integer> list: resultList){
            Collections.sort(list);
        }
        Collections.sort(resultList, Comparator.comparing(o -> o.get(0)));
        for(List<Integer> list: resultList){
            System.out.println(list.stream().map(i -> String.valueOf((char) ('A' + i))).collect(Collectors.joining(" ")));
        }
    }

    private static void tarjanSCC(int node) {
        num[node] = dfsNumberCounter;
        low[node] = dfsNumberCounter;
        dfsNumberCounter++;
        stack.push(node);

        for(int i: map.get(node)){
            if(num[i] == 0){
                tarjanSCC(i);
                low[node] = Math.min(low[node], low[i]);
            }else if(stack.contains(i)){
                low[node] = Math.min(low[node], num[i]);
            }
        }

        if(num[node] == low[node]){
            List<Integer> result = new ArrayList<>();
            while (!stack.isEmpty() && stack.peek() != node){
                result.add(stack.pop());
            }
            result.add(stack.pop());
            resultList.add(result);
        }
    }

}

