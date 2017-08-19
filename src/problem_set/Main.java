package problem_set;

import java.io.File;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {

    private static int numComponent, height, width;
    private static int[][] array;
    private static char[][] chars;
    private static List<String> queryList = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            String line = sc.nextLine().trim();
            if(line.equals("%")){
                process();
            }else {
                queryList.add(line);
            }
            if(!sc.hasNext()){
                process();
                break;
            }
        }
    }

    private static void process() {
        height = queryList.size();
        if(height == 0){
            return;
        }
        width = queryList.get(0).split(" ").length;
        array = new int[height][width];
        chars = new char[height][width];
        for(int i = 0; i < height; i++){
            String line = queryList.get(i);
            String[] temp = line.split(" ");
            for(int j = 0; j < width; j++){
                chars[i][j] = temp[j].charAt(0);
            }
        }

        numComponent = 0;
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                if(array[i][j] == 0){
                    floodfill(i, j, ++numComponent);
                }
            }
        }

        int[] maxValue = new int[width];
        for(int j = 0; j < width; j++){
            for(int i = 0; i < height; i++){
                if(array[i][j] > maxValue[j]){
                    maxValue[j] = array[i][j];
                }
            }
        }

        List<String> resultList = new ArrayList<>();
        for(int i = 0; i < height; i++){
            String currentRow = "";
            for(int j = 0; j < width; j++){
                int maxLen = String.valueOf(maxValue[j]).length();
                int currentLen = String.valueOf(array[i][j]).length();
                while (currentLen < maxLen){
                    currentRow += " ";
                    currentLen++;
                }
                currentRow += array[i][j];
                if(j < width - 1) {
                    currentRow += " ";
                }
            }
            resultList.add(currentRow);
        }

        for(String s: resultList){
            System.out.println(s);
        }
        System.out.println("%");
        queryList.clear();
    }

    private static void floodfill(int row, int col, int color) {
        array[row][col] = color;

        //neigh bor
        int lowX = row - 1 >= 0 ? row - 1 : 0;
        int lowY = col - 1 >= 0 ? col - 1 : 0;
        int highX = row + 1  <= height - 1 ? row + 1 : height -1;
        int highY = col + 1 <= width - 1 ? col + 1 : width -1;

        for(int i = lowX; i <= highX; i++){
            for(int j = lowY; j <= highY; j++){
                if(array[i][j] == 0 && chars[i][j] == chars[row][col]){
                    floodfill(i, j, numComponent);
                }
            }
        }
    }

}

