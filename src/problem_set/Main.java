package problem_set;

import java.util.*;

public class Main {

    private static int COL, ROW, numberOfNode;
    private static int newMin;
    private static PriorityQueue<Map.Entry<Integer, Map.Entry<Integer, Integer>>> edgeList = new PriorityQueue<>(Comparator.comparingInt(Map.Entry::getKey));
    private static int[] pset;
    private static int[][] square;
    private static Map<Integer, Map.Entry<Integer, Integer>> nodeMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();

        while (testCase-- > 0){
            COL = sc.nextInt();
            ROW = sc.nextInt();
            sc.nextLine();

            square = new int[ROW][COL];
            numberOfNode = 0;
            edgeList.clear();
            nodeMap.clear();

            for (int i = 0; i < ROW; i++) {
                String line = sc.nextLine();
                for (int j = 0; j < line.length(); j++) {
                    switch (line.charAt(j)) {
                        case '#':
                            square[i][j] = -1;
                            break;
                        case 'S':
                            square[i][j] = ++numberOfNode;
                            nodeMap.put(numberOfNode, new AbstractMap.SimpleEntry<>(i, j));
                            break;
                        case 'A':
                            square[i][j] = ++numberOfNode;
                            nodeMap.put(numberOfNode, new AbstractMap.SimpleEntry<>(i, j));
                            break;
                        default:
                            break;
                    }
                }
            }

            bfs();
            process();
            System.out.println(newMin);
        }

    }

    private static void bfs() {
        for(int currentNode = 1; currentNode <= numberOfNode; currentNode++){
            Map.Entry<Integer, Integer> entry = nodeMap.get(currentNode);

            Queue<Map.Entry<Integer, Integer>> queue = new LinkedList<>();
            queue.add(entry);

            int[][] distanceMap = new int[ROW][COL];
            for(int i = 0; i < ROW; i++){
                Arrays.fill(distanceMap[i], -1);
            }

            distanceMap[entry.getKey()][entry.getValue()] = 0;

            while (!queue.isEmpty()){
                Map.Entry<Integer, Integer> newNode = queue.poll();
                int row = newNode.getKey();
                int col = newNode.getValue();
                int lowX =  row -1 >= 0 ? row -1 : 0;
                int lowY =  col -1  >= 0 ? col -1 : 0;
                int highX = row + 1 <= ROW - 1 ? row + 1 : ROW -1;
                int highY = col + 1 <= COL - 1 ? col + 1 : COL -1;

                //top down, same col
                for(int newRow = lowX; newRow <= highX; newRow++){
                    if(newRow != row && square[newRow][col] != -1 && distanceMap[newRow][col] == -1){
                        distanceMap[newRow][col] = distanceMap[row][col] + 1;
                        if(square[newRow][col] > 0){
                            edgeList.add(new AbstractMap.SimpleEntry<>(distanceMap[newRow][col], new AbstractMap.SimpleEntry<>(currentNode, square[newRow][col])));
                        }
                        queue.add(new AbstractMap.SimpleEntry<>(newRow, col));
                    }
                }

                //left right, same row
                for(int newCol = lowY; newCol <= highY; newCol++){
                    if(newCol != col && square[row][newCol] != -1 && distanceMap[row][newCol] == -1){
                        distanceMap[row][newCol] = distanceMap[row][col] + 1;
                        if(square[row][newCol] > 0){
                            edgeList.add(new AbstractMap.SimpleEntry<>(distanceMap[row][newCol],
                                            new AbstractMap.SimpleEntry<>(currentNode, square[row][newCol])));
                        }
                        queue.add(new AbstractMap.SimpleEntry<>(row, newCol));
                    }
                }
            }
        }
    }

    private static void process() {
        initSet(numberOfNode);
        newMin = 0;

        while (!edgeList.isEmpty()){
            Map.Entry<Integer, Map.Entry<Integer, Integer>> entry = edgeList.poll();
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

