package problem_set;

import java.util.*;

public class Main {

    private int n, m;
    private int[][] distance, weight;
    private int[] dx = new int[] {-1, 0, 1, 0};
    private int[] dy = new int[] {0, -1, 0, 1};

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0){
            n = sc.nextInt();
            m = sc.nextInt();
            weight = new int[n][m];
            distance = new int[n][m];
            for(int i = 0; i < n; i++){
                for(int j = 0; j < m; j++){
                    weight[i][j] = sc.nextInt();
                    distance[i][j] = Integer.MAX_VALUE;
                }
            }

            distance[0][0] = weight[0][0];
            PriorityQueue<Map.Entry<Integer, Map.Entry<Integer, Integer>>> priorityQueue = new PriorityQueue<>(
                            Comparator.comparing(Map.Entry::getKey));
            priorityQueue.add(new AbstractMap.SimpleEntry<>(distance[0][0], new AbstractMap.SimpleEntry<>(0, 0)));

            while (!priorityQueue.isEmpty()){
                Map.Entry<Integer, Map.Entry<Integer, Integer>> entry = priorityQueue.poll();
                Map.Entry<Integer, Integer> position = entry.getValue();
                int entryDistance = entry.getKey();

                if(distance[position.getKey()][position.getValue()] == entryDistance){
                    for(Map.Entry<Integer, Integer> neighbor: getNeighBor(position)){

                        if(entryDistance + weight[neighbor.getKey()][neighbor.getValue()] < distance[neighbor.getKey()][neighbor.getValue()]){
                            distance[neighbor.getKey()][neighbor.getValue()] = entryDistance + weight[neighbor.getKey()][neighbor.getValue()];
                            priorityQueue.add(new AbstractMap.SimpleEntry<>(distance[neighbor.getKey()][neighbor.getValue()],
                                            new AbstractMap.SimpleEntry<>(neighbor)));
                        }
                    }
                }
            }
            System.out.println(distance[n-1][m-1]);
        }
    }

    private List<Map.Entry<Integer, Integer>> getNeighBor(Map.Entry<Integer, Integer> position) {
        List<Map.Entry<Integer, Integer>> list = new ArrayList<>();
        int x = position.getKey();
        int y = position.getValue();
        for(int i = 0; i < 4; i++){
            int nextX = x + dx[i];
            int nextY = y + dy[i];
            if(0 <= nextX && nextX < n && 0 <= nextY && nextY < m){
                list.add(new AbstractMap.SimpleEntry<>(nextX, nextY));
            }
        }
        return list;
    }

}

