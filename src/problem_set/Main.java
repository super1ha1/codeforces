package problem_set;

import java.util.*;

public class Main {

    private static Map<String, List<String>> map = new HashMap<>();
    private static Map<String, Map<String, Integer>> resultMap = new HashMap<>();
    private static List<String> finalResultList = new ArrayList<>();
    private static List<String> resultPrint = new ArrayList<>();
    private static int  M, N, P;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        finalResultList.add("SHIPPING ROUTES OUTPUT");
        finalResultList.add("");
        int testCase = sc.nextInt();
        for (int i = 1; i <= testCase; i++) {
            M = sc.nextInt();
            N = sc.nextInt();
            P = sc.nextInt();
            sc.nextLine();

            map.clear();
            resultMap.clear();
            resultPrint.clear();

            //getWarehouse
            String setWareHouse = sc.nextLine().trim();
            for (String s : setWareHouse.split(" ")) {
                if (!map.containsKey(s)) {
                    map.put(s, new ArrayList<>());
                }
            }

            //get connection
            for (int j = 0; j < N; j++) {
                String connection = sc.nextLine().trim();
                String[] array = connection.split(" ");
                String first = array[0];
                String second = array[1];
                map.get(first).add(second);
                map.get(second).add(first);
            }

            //get request
            for (int j = 0; j < P; j++) {
                String request = sc.nextLine();
                String [] array = request.split("[\\s]+");
                int size = Integer.valueOf(array[0].trim());
                String source = array[1];
                String destination = array[2];

                if(!resultMap.containsKey(source)){
                    Map<String, Integer> distanceMap = new HashMap<>();
                    distanceMap.put(source, 0);
                    Queue<String> queue = new LinkedList<>();
                    queue.add(source);

                    while (!queue.isEmpty()){
                        String current = queue.poll();
                        if(map.containsKey(current)){
                            for(String neighbor: map.get(current)){
                                if(!distanceMap.containsKey(neighbor)){
                                    distanceMap.put(neighbor, distanceMap.get(current) + 1);
                                    queue.add(neighbor);
                                }
                            }
                        }
                    }
                    resultMap.put(source, distanceMap);
                }

                Map<String, Integer> actualResult = resultMap.get(source);
                if(actualResult.containsKey(destination)){
                    int distance = resultMap.get(source).get(destination);
                    int result = size * distance * 100;
                    resultPrint.add(String.format("$%d", result));
                }else {
                    resultPrint.add("NO SHIPMENT POSSIBLE");
                }
            }
            finalResultList.add(String.format("DATA SET  %d", i));
            finalResultList.add("");
            if(resultPrint.size() > 0){
                finalResultList.addAll(resultPrint);
            }
            finalResultList.add("");
        }
        finalResultList.add("END OF OUTPUT");
        for (String s: finalResultList){
            System.out.println(s);
        }
    }
}

