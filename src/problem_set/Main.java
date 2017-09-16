package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static final Map<Integer, List<String>> lengMap = new HashMap<>();
    private static final Map<String, List<String>> map = new HashMap<>();
    private static final Map<String, Map<String, Integer>> resultMap = new HashMap<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        sc.nextLine();
        sc.nextLine();

        while (testCase-- > 0){
            lengMap.clear();
            map.clear();
            resultMap.clear();

            while (true){
                String word = sc.nextLine().trim();
                if(word.equalsIgnoreCase("*")){
                    break;
                } else {
                    if(!lengMap.containsKey(word.length())){
                        lengMap.put(word.length(), new ArrayList<>());
                    }
                    lengMap.get(word.length()).add(word);
                }
            }
            for(int i: lengMap.keySet()){
                List<String> currentSet = lengMap.get(i);
                for(String s: currentSet){
                    for(String other: currentSet){
                        if(!other.equals(s) && transform(s, other)){
                            if(!map.containsKey(s)){
                                map.put(s, new ArrayList<>());
                            }
                            if(!map.containsKey(other)){
                                map.put(other, new ArrayList<>());
                            }

                            map.get(s).add(other);
                            map.get(other).add(s);
                        }
                    }
                }
            }
            while (sc.hasNext()){
                String query = sc.nextLine().trim();
                if(query.isEmpty()){
                    break;
                }else {
                    processQuery(query);
                }
            }

            if(testCase > 0){
                System.out.println("");
            }
        }
    }

    private static boolean transform(String s, String other) {
        if(map.containsKey(s) && map.get(s).contains(other)){
            return false;
        }
        int counter = 0;
        for(int i = 0; i < s.length(); i++){
            if(s.charAt(i) != other.charAt(i)){
                counter++;
            }
        }
        return counter == 1;
    }

    private static void processQuery(String s) {
        String[] array = s.split(" ");
        String source = array[0];
        String destination = array[1];

        if(!resultMap.containsKey(source)){
            Queue<String> queue = new LinkedList<>();
            queue.add(source);
            Map<String, Integer> distanceMap = new HashMap<>();
            distanceMap.put(source, 0);

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

        if(resultMap.get(source).containsKey(destination)){
            int distance = resultMap.get(source).get(destination);
            System.out.println(String.format("%s %s %d", source, destination, distance));
        }
    }
}

