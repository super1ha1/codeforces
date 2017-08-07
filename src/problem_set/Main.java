package problem_set;

import java.util.*;

public class Main {

    private static int[] colors;
    private static List<Integer> resultList = new ArrayList<>();
    private static Map<Integer, List<String>> map = new HashMap<>();

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int M = sc.nextInt();
        while (M-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            sc.nextLine();
            map.clear();
            for(int i = 0; i < m; i++){
                processEachLine(sc.nextLine());
            }
            sortMap(map);
            if(M > 0){
                System.out.println("");
            }
        }
    }

    private static void sortMap(Map<Integer, List<String>> map) {
        List<Integer> list = new ArrayList<>(map.keySet());
        Collections.sort(list);
        for(int i = 0; i < list.size(); i++){
            for(String s: map.get(list.get(i))){
                System.out.println(s);
            }
        }
    }

    private static void processEachLine(String line) {
        int value = getSortedness(line);
        if(!map.keySet().contains(value)){
            map.put(value, new ArrayList<>());
        }
        map.get(value).add(line);
    }

    private static int getSortedness(String line) {
        char[] array = line.trim().toCharArray();
        int count = 0;
        for(int i = 0; i < array.length - 1; i++){
            for(int j = i + 1; j < array.length; j++){
                if(array[j] < array[i]){
                    count++;
                }
            }
        }
        return count;
    }

}

