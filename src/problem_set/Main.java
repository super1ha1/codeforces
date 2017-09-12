package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static Map<Integer, Map<Integer, Integer>> resultMap = new HashMap<>();
    private static int source;
    private static int ttl;
    private static int counter;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        counter = 0;
        while (sc.hasNext()){
            int n = sc.nextInt();
            if(n == 0){
                break;
            }
            map.clear();
            resultMap.clear();
            for(int i = 0; i < n; i++){
                int first = sc.nextInt();
                int second = sc.nextInt();
                if(!map.containsKey(first)){
                    map.put(first, new ArrayList<>());
                }
                if(!map.containsKey(second)){
                    map.put(second, new ArrayList<>());
                }

                map.get(first).add(second);
                map.get(second).add(first);
            }

            while (true){
                source = sc.nextInt();
                ttl = sc.nextInt();
                if(source == 0 && ttl == 0){
                    break;
                }
                counter++;
                process();
            }
        }
    }

    private static void process() {
        if(!resultMap.containsKey(source)){
            Queue<Integer> queue = new LinkedList<>();
            queue.add(source);
            Map<Integer, Integer> distanceMap = new HashMap<>();
            distanceMap.put(source, 0);

            while (!queue.isEmpty()){
                int node = queue.poll();

                if(map.containsKey(node)){
                    for(int neighbor: map.get(node)){
                        if(!distanceMap.containsKey(neighbor)){
                            distanceMap.put(neighbor, distanceMap.get(node) + 1);
                            queue.add(neighbor);
                        }
                    }
                }
            }

            resultMap.put(source, distanceMap);
        }
       int notVisit = resultMap.get(source).values().stream().reduce(0, (sum, nextValue) -> {
          if(nextValue > ttl){
              return sum + 1;
          }
          return sum;
       });

        notVisit += map.keySet().size() - resultMap.get(source).keySet().size();

        if(!map.containsKey(source)){
            notVisit = map.keySet().size();
        }

        System.out.println(String.format("Case %d: %d nodes not reachable from node %d with TTL = %d.", counter, notVisit, source, ttl));
    }

}

