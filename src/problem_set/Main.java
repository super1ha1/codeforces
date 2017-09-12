package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static List<String> list = new ArrayList<>();
    private static Map<Integer, List<Integer>> map = new HashMap<>();
    private static int[] visit;
    private static Set<Integer> availableChar = new HashSet<>();
    private static List<Integer> resultList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            String line = sc.nextLine().trim();
            if(line.equalsIgnoreCase("#")){
                break;
            }else {
                list.add(line);
            }
        }

        processInput();
    }

    private static void processInput() {
        for(String line: list){
            char[] array = line.toCharArray();
            for(char c: array){
                availableChar.add(c - 'A');
            }
        }
        for(int i = 0; i < list.size() -1; i++){
            String current = list.get(i);
            String next = list.get(i + 1);
            updateMap(current, next);
        }

        visit = new int[26];
        for(int i: availableChar){
            if(visit[i] == 0){
                topoSort(i);
            }
        }

        Collections.reverse(resultList);
        System.out.println(resultList.stream().map(i -> String.valueOf((char) ('A' + i)))
                .collect(Collectors.joining("")));
    }

    private static void topoSort(int node) {
        visit[node] = 1;
        if(map.keySet().contains(node)){
            for(int i: map.get(node)){
                if(visit[i] == 0){
                    topoSort(i);
                }
            }
        }

        resultList.add(node);
    }

    private static void updateMap(String current, String next) {
        int index = 0;
        while (index < current.length() && index < next.length()
                && current.charAt(index) == next.charAt(index)) {
            index++;
        }
        if(index == current.length() && current.length() <= next.length()){
            return;
        }

        int valueIndex = index - 1 >= 0 ? index - 1 : 0;
        int firstChar = current.charAt(valueIndex ) - 'A';
        int second = next.charAt(valueIndex) - 'A';
        if(!map.keySet().contains(firstChar)){
            map.put(firstChar, new ArrayList<>());
        }
        map.get(firstChar).add(second);
    }

}

