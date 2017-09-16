package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final Map<Integer, List<String>> lengMap = new HashMap<>();
    private static final Map<String, List<String>> map = new HashMap<>();
    private static final Map<String, Map<String, String>> resultMap = new HashMap<>();
    private static boolean[] updateMapLength = new boolean[20];
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int counter = 0;
        lengMap.clear();
        map.clear();
        resultMap.clear();

        while (sc.hasNext()) {
            String word = sc.nextLine().trim();
            if (word.isEmpty()) {
                break;
            } else {
                if (!lengMap.containsKey(word.length())) {
                    lengMap.put(word.length(), new ArrayList<>());
                }
                lengMap.get(word.length()).add(word);
            }
        }

        while (sc.hasNext()) {
            String query = sc.nextLine().trim();
            if (query.isEmpty()) {
                break;
            } else {
                if (counter > 0) {
                    System.out.println("");
                }
                processQuery(query);
                counter++;
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

        if(source.equals(destination)){
            System.out.println(source);
            return;
        }

        if(source.length() != destination.length()){
            System.out.println("No solution.");
            return;
        }

        int len = source.length();
        updateMapLength(len);

        if(!map.containsKey(source) || !map.containsKey(destination)){
            System.out.println("No solution.");
            return;
        }

        if(!resultMap.containsKey(source)){
            Queue<String> queue = new LinkedList<>();
            queue.add(source);
            Map<String, String> parentMap = new HashMap<>();
            parentMap.put(source, null);

            while (!queue.isEmpty()){
                String current = queue.poll();
                if(map.containsKey(current)){
                    for(String neighbor: map.get(current)){
                        if(!parentMap.containsKey(neighbor)){
                            parentMap.put(neighbor, current);
                            queue.add(neighbor);
                        }
                    }
                }
            }
            resultMap.put(source, parentMap);
        }

        if(resultMap.get(source).containsKey(destination)){
            List<String> traceList = new ArrayList<>();
            traceList.add(destination);

            Map<String, String> parentMapLocal = resultMap.get(source);
            while (true){
                String parent = parentMapLocal.get(traceList.get(traceList.size() -1));
                traceList.add(parent);
                if(parent.equals(source)){
                    break;
                }
            }
            Collections.reverse(traceList);
            for(String member: traceList){
                System.out.println(member);
            }
        }else {
            System.out.println("No solution.");
        }
    }

    private static void updateMapLength(int len) {
        if(updateMapLength[len]){
           return;
        }
        List<String> currentSet = lengMap.get(len);
        for (String s : currentSet) {
            for (String other : currentSet) {
                if (!other.equals(s) && transform(s, other)) {
                    if (!map.containsKey(s)) {
                        map.put(s, new ArrayList<>());
                    }
                    if (!map.containsKey(other)) {
                        map.put(other, new ArrayList<>());
                    }

                    map.get(s).add(other);
                    map.get(other).add(s);
                }
            }
        }
        updateMapLength[len] = true;
    }
}

