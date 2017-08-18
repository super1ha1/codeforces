package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int component, h, w;
    private static Map<Integer, List<Map.Entry>> resultMap = new HashMap<>();
    public static void main(String[] args) throws Exception {

//        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int count = 0;
        while (sc.hasNext()){
            w = sc.nextInt();
            h = sc.nextInt();
            if(w == 0 && h == 0){
                break;
            }
            sc.nextLine();
            count++;

            int[][] array = new int[h][w];
            for(int i = 0; i < h; i++){
                String line = sc.nextLine();
                for(int j = 0; j < w; j++){
                    if (line.charAt(j) == '.'){
                        array[i][j] = -1;
                    }else if(line.charAt(j) == 'X'){
                        array[i][j] = 1;
                    }else if(line.charAt(j) == '*'){
                        array[i][j] = 0;
                    }
                }
            }
            process(array);
            for(int i = 2; i <= component; i++){
                List<Map.Entry> list = resultMap.get(i);
            }
            List<Integer> resultList = new ArrayList<>(resultMap.values());
            Collections.sort(resultList);

            System.out.println(String.format("Throw %d", count));
            System.out.println(resultList.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(" ")));
            if(sc.hasNext()){
                System.out.println("");
            }else {
                break;
            }
        }
    }

    private static void process(int[][] array) {
        component = 1;
        resultMap.clear();
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(array[i][j] == 0){
                    component++;
                    array[i][j] = component;
                    resultMap.put(component, new ArrayList<>());
                    dfs(i, j, array);
                }else if(array[i][j] == 1){
                    component++;
                    array[i][j] = component;
                    List<Map.Entry> newList = new ArrayList<>();
                    newList.add(new AbstractMap.SimpleEntry<>(i, j));
                    resultMap.put(component, newList);
                    dfs(i, j, array);
                }
            }
        }
    }

    private static void dfs(int row, int col, int[][] array) {
        int lowX = row - 1 >= 0 ? row - 1 : 0;
        int lowY = col - 1 >= 0 ? col - 1 : 0;
        int highX = row + 1 <= h -1 ? row + 1 : h -1;
        int highY = col + 1 <= w +1 ? col + 1: w -1;

        //same col
        for(int i = lowX; i <= highX; i++){
            if(array[i][col] == 0){
                array[i][col] = component;
                dfs(i, col, array);
            }else if(array[i][col] == 1){
                resultMap.get(component).add(new AbstractMap.SimpleEntry<>(i, col));
                array[i][col] = component;
                dfs(i, col, array);
            }
        }

        //same row
        for(int j = lowY; j <= highY; j++){
            if(array[row][j] == 0){
                array[row][j] = component;
                dfs(row, j, array);
            }else if(array[row][j] == 1){
                resultMap.get(component).add(new AbstractMap.SimpleEntry<>(row, j));
                array[row][j] = component;
                dfs(row, j, array);
            }
        }
    }


}

