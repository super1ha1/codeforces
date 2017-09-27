package problem_set;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private int n, m;
    private Map<Integer, List<Integer>> map = new HashMap<>();
    private Map<Integer, Map<Integer, Integer>> resultMap = new HashMap<>();
    private List<String> output = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //         Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()) {
            map.clear();
            resultMap.clear();
            output.clear();
            output.add("-----");

            n = Integer.valueOf(sc.nextLine());
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine().trim();
                String[] array = line.split("-");
                int first = Integer.valueOf(array[0]);
                if (!map.containsKey(first)) {
                    map.put(first, new ArrayList<>());
                }
                if(array.length > 1){
                    for (String s : array[1].split(",")) {
                        map.get(first).add(Integer.valueOf(s));
                    }
                }
            }

            m = Integer.valueOf(sc.nextLine());
            for (int i = 0; i < m; i++) {
                String line = sc.nextLine().trim();
                String[] array = line.split("[\\s]+");
                int start = Integer.valueOf(array[0]);
                int finish = Integer.valueOf(array[1]);

                if (map.containsKey(start)) {
                    if(start == finish){
                        output.add(String.valueOf(start));
                    } else {
                        if (!resultMap.containsKey(start)) {
                            Queue<Integer> queue = new LinkedList<>();
                            queue.add(start);
                            Map<Integer, Integer> parentMap = new HashMap<>();
                            parentMap.put(start, -1);

                            while (!queue.isEmpty()) {
                                int current = queue.poll();
                                if (map.containsKey(current)) {
                                    for (int neighbor : map.get(current)) {
                                        if (!parentMap.containsKey(neighbor)) {
                                            parentMap.put(neighbor, current);
                                            queue.add(neighbor);
                                        }
                                    }
                                }
                            }
                            resultMap.put(start, parentMap);
                        }

                        Map<Integer, Integer> parentMap = resultMap.get(start);
                        if (parentMap.containsKey(finish)) {
                            List<Integer> resultList = new ArrayList<>();
                            resultList.add(finish);
                            int nextParent = parentMap.get(resultList.get(resultList.size() - 1));
                            while (nextParent != start) {
                                resultList.add(nextParent);
                                nextParent = parentMap.get(resultList.get(resultList.size() - 1));
                            }
                            resultList.add(start);
                            Collections.reverse(resultList);
                            output.add(resultList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
                        } else {
                            output.add("connection impossible");
                        }
                    }
                } else {
                    output.add("connection impossible");
                }
            }

            for (String s : output) {
                System.out.println(s);
            }
        }
    }

}

