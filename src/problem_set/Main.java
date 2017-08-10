package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    private static Map<Integer, Map<Character, Integer>> map = new HashMap<>();
    private static List<Character> list = Arrays.asList('i','v','x','l','c');
    private static int[][] totalChar = new int[101][5];
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        prepareMap();
        int M = sc.nextInt();
        while (M > 0){
            printNumberEachChar(M);
            M = sc.nextInt();
        }
    }

    private static void prepareMap() {
        for(int i = 1; i <= 100; i++){
            map.put(i, prepareEachValue(i));
        }
        for(int i = 1; i <= 100; i++){
            for(int j = 0; j < 5; j++){
                totalChar[i][j] = totalChar[i-1][j] + map.get(i).get(list.get(j));
            }
        }
    }

    private static Map<Character, Integer> prepareEachValue(int value) {
        Map<Character, Integer> currentMap = new HashMap<>();
        for(char c: list){
            currentMap.put(c, 0);
        }
        int firstDigit = value / 10;
        int secondDigit = value % 10;

        String romanValue = getFirstValue(firstDigit) + getSeconValue(secondDigit);

        char[] array = romanValue.toCharArray();
        for(char c: array){
            if(currentMap.keySet().contains(c)){
                int currentValue = currentMap.get(c);
                currentMap.put(c, currentValue + 1);
            }
        }
        return currentMap;
    }

    private static String getSeconValue(int secondDigit) {
        switch (secondDigit){
            case 1:
                return "i";
            case 2:
                return "ii";
            case 3:
                return "iii";
            case 4:
                return "iv";
            case 5:
                return "v";
            case 6:
                return "vi";
            case 7:
                return "vii";
            case 8:
                return "viii";
            case 9:
                return "ix";
        }
        return "";
    }

    private static String getFirstValue(int firstDigit) {
        switch (firstDigit){
            case 1:
                return "x";
            case 2:
                return "xx";
            case 3:
                return "xxx";
            case 4:
                return "xl";
            case 5:
                return "l";
            case 6:
                return "lx";
            case 7:
                return "lxx";
            case 8:
                return "lxxx";
            case 9:
                return "xc";
            case 10:
                return "c";
        }
        return "";
    }

    private static void printNumberEachChar(int M) {
        System.out.println(String.format("%d: %d i, %d v, %d x, %d l, %d c", M, totalChar[M][0],
                totalChar[M][1], totalChar[M][2], totalChar[M][3], totalChar[M][4]));
    }

}

