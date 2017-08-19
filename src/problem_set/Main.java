package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static int numComponent, h, w, secondRoundNumberComponent;
    private static Map<Integer, List<Map.Entry>> resultMap = new HashMap<>();
    private static int[][] array;
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
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

            array = new int[h][w];
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
            process();

            List<Integer> resultList = new ArrayList<>();
            for(int i = 2; i <= numComponent; i++){
                List<Map.Entry> currentList = resultMap.get(i);
                for(Map.Entry entry: currentList){
                    array[(int) entry.getKey()][(int) entry.getValue()] = 0;
                }
                processSecondRound();
                resultList.add(secondRoundNumberComponent);
            }

            Collections.sort(resultList);

            System.out.println(String.format("Throw %d", count));
            System.out.println(resultList.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(" ")));
            System.out.println("");
        }
    }

    private static void processSecondRound() {
        secondRoundNumberComponent = 0;
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(array[i][j] == 0) {
                    floodFillSecondRound(i, j, ++secondRoundNumberComponent);
                }
            }
        }
    }

    private static void process() {
        numComponent = 1;
        resultMap.clear();
        for(int i = 0; i < h; i++){
            for(int j = 0; j < w; j++){
                if(array[i][j] == 0 || array[i][j] == 1) {
                    resultMap.put(numComponent + 1, new ArrayList<>());
                    floodFill(i, j, ++numComponent);
                }
            }
        }
    }

    private static void floodFill(int row, int col, int color) {
        if(array[row][col] == 1){
            resultMap.get(color).add(new AbstractMap.SimpleEntry<>(row, col));
        }
        array[row][col] = color;

        //visit all neighbor
        int lowX = row - 1 >= 0 ? row - 1 : 0;
        int lowY = col - 1 >= 0 ? col - 1 : 0;
        int highX = row + 1 <= h -1 ? row + 1 : h -1;
        int highY = col + 1 <= w - 1 ? col + 1: w -1;

        //same col
        for(int i = lowX; i <= highX; i++){
            if(array[i][col] == 0 || array[i][col] == 1) {
                floodFill(i, col, color);
            }
        }

        //same row
        for(int j = lowY; j <= highY; j++){
            if(array[row][j] == 0 || array[row][j] == 1){
                floodFill(row, j, color);
            }
        }
    }


    private static void floodFillSecondRound(int row, int col, int color) {
        array[row][col] = color;

        //visit all neighbor
        int lowX = row - 1 >= 0 ? row - 1 : 0;
        int lowY = col - 1 >= 0 ? col - 1 : 0;
        int highX = row + 1 <= h -1 ? row + 1 : h -1;
        int highY = col + 1 <= w - 1 ? col + 1: w -1;

        //same col
        for(int i = lowX; i <= highX; i++){
            if(array[i][col] == 0) {
                floodFillSecondRound(i, col, color);
            }
        }

        //same row
        for(int j = lowY; j <= highY; j++){
            if(array[row][j] == 0){
                floodFillSecondRound(row, j, color);
            }
        }
    }
}

