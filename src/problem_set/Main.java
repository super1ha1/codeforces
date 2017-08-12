package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static Map<Integer, Node> result = new HashMap<>();
    private static List<Node> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));


        while (sc.hasNext()){
            int n = sc.nextInt();
            map.clear();
            result.clear();
            list.clear();

            for(int i = 1; i <= n; i++){
                map.put(i, new ArrayList<>());
            }
            for(int i  = 0; i < n-1; i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                map.get(first).add(second);
                map.get(second).add(first);
            }

            dfs(map, n);
        }
    }

    private static void dfs(Map<Integer, List<Integer>> map, int n) {
        Queue<Integer> queue = new LinkedList<>();
        queue.add(1);
        int[] visited = new int[n + 1];
        result.put(1, new Node(1, 1.0, 0));
        while (!queue.isEmpty()){
            int currentIndex = queue.poll();
            if(visited[currentIndex] == 0){
                visited[currentIndex] = 1;
                Node currentNode = result.get(currentIndex);

                List<Integer> connected = map.get(currentIndex);
                List<Integer> tobeAdd = connected.stream().filter(i -> visited[i] == 0).collect(Collectors.toList());

                int size = tobeAdd.size();
                if(size == 0){
                    list.add(currentNode);
                }

                double nextProb = currentNode.prob *  1.0 / size;
                double nextValue = currentNode.value + 1;

                for(int i: tobeAdd){
                    result.put(i, new Node(i, nextProb, nextValue));
                }

                queue.addAll(tobeAdd);
            }
        }
        double result = 0.0;
        for(Node node: list){
            result += node.prob * node.value;
        }
        System.out.println(String.format("%.15f", result));
    }

    private static class Node {
        public int node;
        public double prob;
        public double value;
        public Node(){

        }

        public Node(int node, double prob, double value){
            this.node = node;
            this.prob = prob;
            this.value = value;
        }
    }
}

