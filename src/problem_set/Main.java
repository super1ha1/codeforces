package problem_set;

import java.util.*;

public class Main {

    private static Map<Integer, Integer> map = new HashMap<>();
    private static int currentComponent = 0;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        sc.nextLine();
        sc.nextLine();
        while (testCase-- > 0){
            List<String> input = new ArrayList<>();
            List<String> eachQuery = new ArrayList<>();

            while (true){
                if(sc.hasNext()){
                    String line = sc.nextLine().trim();
                    if(line.length() == 0){
                        break;
                    }else if(line.matches("[A-Z]*")){
                        input.add(line);
                    }else if(line.matches("\\d.*\\d")){
                        eachQuery.add(line);
                    }else {
                        break;
                    }
                }else {
                    break;
                }
            }

            if(input.size() == 0 || eachQuery.size() == 0){
                continue;
            }

            int[][] array = processArray(input);
            for(String s: eachQuery){
                processEachQuery(s, array);
            }

            if(testCase > 0){
                System.out.println("");
            }
        }
    }

    private static void processEachQuery(String s, int[][] array) {
        if(!s.matches("\\d.*\\d")){
            return;
        }
        String[] matches = s.split(" ");
        int row = Integer.valueOf(matches[0]) - 1;
        int col = Integer.valueOf(matches[1]) - 1;

        int n = array.length;
        int m = array[0].length;
        if(row < 0 || row >= n || col < 0 || col >= m){
            System.out.println("0");
            return;
        }

        int value = array[row][col];
        if(value < 1 || !map.keySet().contains(value)){
            System.out.println("0");
            return;
        }
        System.out.println(map.get(value));
    }

    private static int[][] processArray(List<String> input) {
        int n = input.size();
        int m = input.get(0).length();
        int [][] array = new int[n][m];
        for(int i = 0; i < n; i++){
            String line = input.get(i);
            for(int j = 0; j < m; j++){
                if(line.charAt(j) == 'L'){
                    array[i][j] = -1;
                }else if (line.charAt(j) == 'W'){
                    array[i][j] = 0;
                }
            }
        }
        map.clear();
        currentComponent = 0;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(array[i][j] == 0){ // not visit yet
                    currentComponent++;
                    array[i][j] = currentComponent;
                    dfs(i, j, array);
                }
            }
        }
        for(int i = 1; i <= currentComponent; i++){
            map.put(i, countValue(i, array));
        }
        return array;
    }

    private static int countValue(int value, int[][] array) {
        int n = array.length;
        int m = array[0].length;
        int count = 0;
        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                if(array[i][j] == value){
                    count++;
                }
            }
        }
        return count;
    }

    private static void dfs(int i, int j, int[][] array) {
        int n = array.length;
        int m = array[0].length;
        int lowX = i - 1 >= 0 ? i -1 : 0;
        int highX = i + 1 <= n -1 ? i + 1 : n-1;
        int lowY = j - 1 >= 0 ? j -1 : 0;
        int highY = j + 1 <= m -1 ? j + 1 : m -1;

        for(int row = lowX; row <= highX; row++){
            for(int col = lowY; col <= highY; col++){
                if(array[row][col] == 0){
                    array[row][col] = currentComponent;
                    dfs(row, col, array);
                }
            }
        }
    }
}

