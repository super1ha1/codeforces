package problem_set;

import java.util.*;

public class Main {

    private static final int largeValue = 10000;
    private Map<Integer, List<Integer>> map = new HashMap<>();
    private int[] num, low, parent;
    private int counter;
    private List<Map.Entry<Integer, Integer>> resultList = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()) {
            int n = sc.nextInt();
            if (n == 0) {
                System.out.println("0 critical links");
                System.out.println("");
                continue;
            }
            sc.nextLine();
            map.clear();
            resultList.clear();

            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                String[] arrays = line.trim().split("[\\s]+");
                int index = Integer.valueOf(arrays[0]);
                if (!map.containsKey(index)) {
                    map.put(index, new ArrayList<>());
                }
                int numberOfConnection = Integer.valueOf(arrays[1].substring(1, arrays[1].length() - 1));
                if (numberOfConnection > 0) {

                    for (int j = 2; j < arrays.length; j++) {
                        int value = Integer.valueOf(arrays[j]);
                        if (!map.containsKey(value)) {
                            map.put(value, new ArrayList<>());
                        }
                        map.get(index).add(value);
                        map.get(value).add(index);
                    }
                }
            }
            num = new int[n];
            low = new int[n];
            parent = new int[n];
            counter = 0;
            for (int i = 0; i < n; i++) {
                if (num[i] == 0) {
                    dfs(i);
                }
            }

            resultList = new ArrayList<>(new HashSet<>(resultList));
            Collections.sort(resultList, Comparator.comparingInt(Map.Entry::getKey));
            System.out.println(String.format("%d critical links", resultList.size()));
            if (resultList.size() > 0) {
                for (Map.Entry<Integer, Integer> entry : resultList) {
                    System.out.println(String.format("%d - %d", entry.getKey(), entry.getValue()));
                }
            }
            System.out.println("");
        }
    }

    private void dfs(int index) {
        counter++;
        num[index] = counter;
        low[index] = counter;
        if (map.containsKey(index)) {
            for (int neighbor : map.get(index)) {
                if (num[neighbor] == 0) {
                    parent[neighbor] = index;
                    dfs(neighbor);

                    if (low[neighbor] > num[index]) {
                        int min = Math.min(neighbor, index);
                        int max = Math.max(neighbor, index);
                        resultList.add(new AbstractMap.SimpleEntry<>(min, max));
                    }

                    low[index] = Math.min(low[index], low[neighbor]);

                } else if (parent[index] != neighbor) {
                    low[index] = Math.min(low[index], num[neighbor]);
                }
            }
        }
    }
}

