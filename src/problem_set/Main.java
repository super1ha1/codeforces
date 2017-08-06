package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static int[][][] array;
    private static final int NOT_APPLICABLE = -1;
    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] visited;
    private static int[] colors;
    private static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        while (testCase-- > 0){
            int n = sc.nextInt();
            int k = sc.nextInt();
            map.clear();

            if(k == 0){
                //if no connection, all node can be colored
                noConnection(n);
                continue;
            }

            for(int i = 1; i <= n; i++){
                map.put(i, new ArrayList<>());
            }

            for(int i = 0 ; i < k ; i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                map.get(first).add(second);
                map.get(second).add(first);
            }

            process(map);
        }
    }

    private static void noConnection(int n) {
        System.out.println(n);
        String output = "";
        for(int i = 1; i <= n; i++){
            output += i;
            if(i < n){
                output += " ";
            }
        }
        System.out.println(output);
    }

    private static void process(Map<Integer, List<Integer>> map) throws Exception{
        int size = map.keySet().size();
        resultList.clear();
        for(int i = 1 ; i <= size; i++){
            System.out.println("DFS: " + i);
            if(map.get(i).size() == 0){
                //if node has no connection, x process further
                continue;
            }
            visited = new int[size + 1];
            colors = new int[size + 1];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(i);
            recursiveDFS(queue, map);
        }
        System.out.println(resultList.size());
        System.out.println(resultList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static boolean recursiveDFS(Queue<Integer> queue, Map<Integer, List<Integer>> map) throws Exception {
        if(queue.isEmpty()){
            return true;
        }
        int currentIndex = queue.poll();
        if(visited[currentIndex] == 0) {
            System.out.println("Visit: " + currentIndex + "------------------------");
            List<Integer> list = map.get(currentIndex).stream().filter(integer -> visited[integer] == 0 && !queue.contains(integer)).collect(Collectors.toList());
            queue.addAll(list);
            System.out.println("Current queue: " + queue.stream().map(String::valueOf).collect(Collectors.joining(" ")));
            visited[currentIndex] = 1;
            //not visit yet

            if (isOkToColorBlack(currentIndex, map)) {
                //set white
                colors[currentIndex] = -1;
                System.out.println("Mark: " + currentIndex + " color: " + colors[currentIndex]);

                if(recursiveDFS(new LinkedList<>(queue), map)){
                    //measure result
                    measureResult(map.keySet().size());
                }
                visited[currentIndex] = 0;

                //set black
                colors[currentIndex] = 1;
                System.out.println("Mark: " + currentIndex + " color: " + colors[currentIndex]);
                if(recursiveDFS(new LinkedList<>(queue), map)){
                    //measure result
                    measureResult(map.keySet().size());
                }
                visited[currentIndex] = 0;

            } else {
                //white only
                colors[currentIndex] = -1;
                System.out.println("Mark: " + currentIndex + " color: " + colors[currentIndex]);
                if(recursiveDFS(new LinkedList<>(queue), map)){
                    //measure result
                    measureResult(map.keySet().size());
                }
                visited[currentIndex] = 0;
            }
            return false;
        }else {
            return false;
        }
    }

    private static void measureResult(int size) {
        System.out.println("Measure result here: " + IntStream.range(0, colors.length).filter(i -> i > 0).mapToObj(i -> String.valueOf(colors[i])).collect(Collectors.joining(" ")));

        List<Integer> list = new ArrayList<>();
        for(int index = 1; index <= size; index++){
            if(colors[index] != -1){
                list.add(index);
            }
        }
        if(list.size() > resultList.size()){
            resultList.clear();
            resultList.addAll(list);
        }
    }

    private static void dfs(int index, Map<Integer, List<Integer>> map) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(index);
        while (!queue.isEmpty()){
            int currentIndex = queue.poll();
            if(visited[currentIndex] == 0){
                visited[currentIndex] = 1;
                if(isOkToColorBlack(currentIndex, map)){
                    //missing greedy case: either can try black or white, have to try both > achieve complete search
                    for(int j = -1; j <= 1; j += 2){
                        colors[currentIndex] = j;
                        System.out.println("Visit: " + currentIndex + " color: " + colors[currentIndex]);
                        queue.addAll(map.get(currentIndex).stream().filter(i -> visited[i] == 0).collect(Collectors.toList()));
                    }
                }else {
                    //white
                    colors[currentIndex] = -1;
                    System.out.println("Visit: " + currentIndex + " color: " + colors[currentIndex]);
                    queue.addAll(map.get(currentIndex).stream().filter(i -> visited[i] == 0).collect(Collectors.toList()));
                }
            }
        }
    }

    private static boolean isOkToColorBlack(int currentIndex, Map<Integer, List<Integer>> map) {
        List<Integer> neighborBlackColorList = map.get(currentIndex).stream().filter(i -> colors[i] == 1).collect(Collectors.toList());
        return neighborBlackColorList.size() == 0;
    }

}

