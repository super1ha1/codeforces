package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static int n, m, k, oldMin, newMin;
    private static PriorityQueue<Map.Entry<Integer, Map.Entry<Integer, Integer>>> queue = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));
    private static int[] pset;
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));


        int counter = 0;
        while (sc.hasNext()){
            n = sc.nextInt();
            oldMin = 0;
            for(int i = 0 ;i < n-1; i++){
                int firstPoint = sc.nextInt();
                int secondPoint = sc.nextInt();
                int weight = sc.nextInt();
                oldMin += weight;
            }

            queue.clear();
            k = sc.nextInt();
            for(int i = 0; i < k; i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                int weight = sc.nextInt();
                queue.add(new AbstractMap.SimpleEntry<>(weight, new AbstractMap.SimpleEntry<>(first, second)));
            }

            m = sc.nextInt();
            for(int i = 0; i < m; i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                int weight = sc.nextInt();
                queue.add(new AbstractMap.SimpleEntry<>(weight, new AbstractMap.SimpleEntry<>(first, second)));
            }

            if(counter > 0){
                System.out.println("");
            }

            process();

            System.out.println(oldMin);
            System.out.println(newMin);

            counter++;
        }

    }

    private static void process() {
        initSet(n);
        newMin = 0;
        while (!queue.isEmpty()){
            Map.Entry<Integer, Map.Entry<Integer, Integer>> entry = queue.poll();
            Map.Entry<Integer, Integer> pair = entry.getValue();
            int weight = entry.getKey();

            if(!isSameSet(pair.getKey(), pair.getValue())){
                newMin += weight;
                unionSet(pair.getKey(), pair.getValue());
            }
        }
    }

    private static void initSet(int n) {
        pset = new int[n + 10];
        for(int i = 0; i <= n; i++){
            pset[i] = i;
        }
    }

    private static boolean isSameSet(int i, int j) {
        return findSet(i) == findSet(j);
    }

    private static int findSet(int i){
        return (pset[i] == i ? i : (pset[i] = findSet(pset[i])));
    }

    private static void unionSet(int i, int j){
        pset[findSet(i)] = findSet(j);
    }
}

