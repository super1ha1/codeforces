package problem_set;

import java.util.*;

public class Main {

    private static int n;
    private static double newMin;
    private static PriorityQueue<Map.Entry<Double, Map.Entry<Integer, Integer>>> queue = new PriorityQueue<>(Comparator.comparingDouble(Map.Entry::getKey));
    private static int[] pset;
    private static List<Map.Entry<Double, Double>> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();

        while (testCase-- > 0){
            n = sc.nextInt();
            list.clear();
            queue.clear();

            for(int j = 0 ; j < n; j++){
                double x = sc.nextDouble();
                double y = sc.nextDouble();
                list.add(new AbstractMap.SimpleEntry<>(x, y));
            }

            process();

            System.out.println(String.format("%.2f", newMin));

            if(testCase > 0){
                System.out.println("");
            }
        }

    }

    private static void process() {
        initSet(n);
        newMin = 0.0;
        for(int i = 0; i < n; i++){
            for (int j = i + 1; j < n; j++){
                Map.Entry<Double, Double> first = list.get(i);
                Map.Entry<Double, Double> second = list.get(j);
                queue.add(new AbstractMap.SimpleEntry<>(getDistance(first, second), new AbstractMap.SimpleEntry<>(i, j)));
            }
        }
        while (!queue.isEmpty()){
            Map.Entry<Double, Map.Entry<Integer, Integer>> entry = queue.poll();
            Map.Entry<Integer, Integer> pair = entry.getValue();
            double weight = entry.getKey();

            if(!isSameSet(pair.getKey(), pair.getValue())){
                newMin += weight;
                unionSet(pair.getKey(), pair.getValue());
            }
        }
    }

    private static Double getDistance(Map.Entry<Double, Double> first, Map.Entry<Double, Double> second) {
        return Math.sqrt(Math.pow(Math.abs(first.getKey() - second.getKey()), 2)  + Math.pow(Math.abs(first.getValue() - second.getValue()), 2));
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

