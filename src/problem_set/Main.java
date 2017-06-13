package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    static final int LARGE_INT = -1000000000;
    static int [][] can_reach, price;
    static int first, k, tempMax = Integer.MIN_VALUE;
    static List<List<Integer>> adList = new ArrayList<List<Integer>>();
    static List<Integer> resultList = new ArrayList<Integer>();
    static List<Integer> tempList = new ArrayList<Integer>();
    static List<Integer> notConnectedList = new ArrayList<Integer>();
    static int[] visisted;
    //    static Scanner sc = new Scanner(System.in);

    private static Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        int count = 1;
        while (sc.hasNext()){
            int numberOfPair = sc.nextInt();
            if(numberOfPair == 0){
                break;
            }
            map.clear();
            while (numberOfPair-- > 0){
                int first = sc.nextInt();
                int second = sc.nextInt();
                if(!map.keySet().contains(first)) {
                    map.put(first, new ArrayList<Integer>());
                }
                map.get(first).add(second);

                if(!map.keySet().contains(second)){
                    map.put(second, new ArrayList<Integer>());
                }
                map.get(second).add(first);
            }

            while (true){
                int start = sc.nextInt();
                int ttl = sc.nextInt();
                if(start == 0 && ttl == 0){
                    break;
                }
                process(start, ttl, count++);
            }
        }
    }

    private static void process(int start, int ttl, int count) {
        int node = getCount(start, ttl);
        System.out.println("Case " + count + ": " + node + " nodes not reachable from node " + start + " with TTL = " + ttl + ".");
    }

    private static int getCount(int start, int maxDepth) {
        Set<Integer> set = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.add(start);
        int elementToDepthIncrease = 1, nextElementToDepthIncrease = 0, currentDepth = 0;
        while (!queue.isEmpty()){
            int currentNode = queue.poll();
            List<Integer> neighbors = map.get(currentNode);
            nextElementToDepthIncrease += neighbors != null ? neighbors.size() : 0;

            if(--elementToDepthIncrease == 0){
                if(++currentDepth > maxDepth){
                    break;
                }
                elementToDepthIncrease = nextElementToDepthIncrease;
                nextElementToDepthIncrease = 0;
            }

            if(neighbors != null){
                //process node
                for(int i: neighbors){
                    set.add(i);
                    queue.add(i);
                }
            }
        }

        return map.keySet().size() - set.size();
    }


}

