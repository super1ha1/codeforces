package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] colors;
    private static List<Integer> resultList = new ArrayList<>();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

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

            for(int i = 0 ; i < k ; i++) {
                int first = sc.nextInt();
                int second = sc.nextInt();
                map.get(first).add(second);
                map.get(second).add(first);
            }
            //separated connect component first
            List<List<Integer>> diffList = separateList(map);

            process(diffList, map);
        }
    }

    private static List<List<Integer>> separateList(Map<Integer, List<Integer>> map) {
        int size = map.keySet().size();
        int[] mark = new int[size + 1];
        int count = 0;
        Queue<Integer> queue = new LinkedList<>();
        List<List<Integer>> diffList = new ArrayList<>();
        List<Integer> list = new ArrayList<>();
        for(int i = 1;i <= size; i++){
            if(mark[i] == 0){
                list = new ArrayList<>();
                diffList.add(list);
                count++;
            }
            if(mark[i] != 0){
                continue;
            }
            queue.clear();
            queue.add(i);
            while (!queue.isEmpty()){
                int currentIndex  = queue.poll();
                if(mark[currentIndex] == 0){
                    queue.addAll(map.get(currentIndex).stream().filter(index -> !queue.contains(index) && mark[index] == 0).collect(Collectors.toList()));
                    mark[currentIndex] = count;
                    list.add(currentIndex);
                }
            }
        }
        return diffList;
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

    private static void process(List<List<Integer>> diffList, Map<Integer, List<Integer>> map) throws Exception {
        List<Integer> finalList = new ArrayList<>();
        for(List<Integer> currentList: diffList){
            finalList.addAll(updateList(currentList, map));
        }
        System.out.println(finalList.size());
        System.out.println(finalList.stream().sorted().map(String::valueOf).collect(Collectors.joining(" ")));
    }

    private static List<Integer> updateList(List<Integer> currentList, Map<Integer, List<Integer>> map) throws Exception {
        int size = map.keySet().size();
        resultList.clear();
        if(currentList.size() == 1){
            resultList.addAll(currentList);
        } else {
            for(int i: currentList){
//                System.out.println("DFS: " + i);
                if(map.get(i).size() == 0){
                    //if node has no connection, x process further
                    continue;
                }
                colors = new int[size + 1];
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                List<Integer> list = new ArrayList<>();
                recursiveDFS(queue, map, list);
            }
        }
        return new ArrayList<>(resultList);
    }

    private static boolean recursiveDFS(Queue<Integer> queue, Map<Integer, List<Integer>> map, List<Integer> visitedList) throws Exception {
        if(queue.isEmpty()){
            return true;
        }
        int currentIndex = queue.poll();
        visitedList.add(currentIndex);
//        System.out.println("Visit: " + currentIndex + "------------------------");
        List<Integer> list = map.get(currentIndex).stream().filter(integer -> !queue.contains(integer) && !visitedList.contains(integer)).collect(Collectors.toList());
        queue.addAll(list);
//        System.out.println("Current queue: " + queue.stream().map(String::valueOf).collect(Collectors.joining(" ")));

        if (isOkToColorBlack(currentIndex, map)) {
            //set white
            colors[currentIndex] = -1;
//            System.out.println("Mark: " + currentIndex + " color: " + colors[currentIndex]);

            if(recursiveDFS(new LinkedList<>(queue), map, new ArrayList<>(visitedList))){
                measureResult(map.keySet().size());
            }

            //set black
            colors[currentIndex] = 1;
//            System.out.println("Mark: " + currentIndex + " color: " + colors[currentIndex]);
            if(recursiveDFS(new LinkedList<>(queue), map, new ArrayList<>(visitedList))){
                measureResult(map.keySet().size());
            }

        } else {
            //white only
            colors[currentIndex] = -1;
//            System.out.println("Mark: " + currentIndex + " color: " + colors[currentIndex]);
            if(recursiveDFS(new LinkedList<>(queue), map, new ArrayList<>(visitedList))){
                measureResult(map.keySet().size());
            }
        }
        return false;
    }

    private static void measureResult(int size) {
//        System.out.println("Measure result here: " + IntStream.range(0, colors.length).filter(i -> i > 0).mapToObj(i -> String.valueOf(colors[i])).collect(Collectors.joining(" ")));

        List<Integer> list = new ArrayList<>();
        for(int index = 1; index <= size; index++){
            if(colors[index] == 1){
                list.add(index);
            }
        }
        if(list.size() > resultList.size()){
//            System.out.println("MAX CASE HERE");
            resultList.clear();
            resultList.addAll(list);
        }
    }

    private static boolean isOkToColorBlack(int currentIndex, Map<Integer, List<Integer>> map) {
        List<Integer> neighborBlackColorList = map.get(currentIndex).stream().filter(i -> colors[i] == 1).collect(Collectors.toList());
        return neighborBlackColorList.size() == 0;
    }

}

