package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    static final int LARGE_INT = -1000000000;
    static int [][] can_reach, price;
    static int M, C;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        String line;
        List<char[]> list = new ArrayList<char[]>();
        while (sc.hasNext()){
            line = sc.nextLine().trim();
            if(line.equals("#")){
                break;
            }
            if(line.startsWith("e")){
                process(list);
                list.clear();
            }else {
                list.add(getInput(line));
            }
        }
    }

    private static char[] getInput(String line) {
        char[] array = new char[5];
        String[] lines = line.split(",");
        for(String s: lines){
            char first = s.charAt(0);
            switch (first){
                case 'r':
                    array[0] = s.charAt(2);
                    break;
                case 'o':
                    array[1] = s.charAt(2);
                    break;
                case 'y':
                    array[2] = s.charAt(2);
                    break;
                case 'g':
                    array[3] = s.charAt(2);
                    break;
                case 'b':
                    array[4] = s.charAt(2);
                    break;
                default:
                    break;
            }
        }
        return array;
    }

    private static void process(List<char[]> list) {


        int minChange = Integer.MAX_VALUE;
        int currentIndex = -1;
        for(int i = 0 ; i < list.size(); i++){
            char[] currentChars = list.get(i);
            int value = getValue(currentChars, list);
            if(value < minChange){
                minChange = value;
                currentIndex = i;
            }
        }
        System.out.println(currentIndex + 1);
    }

    private static int getValue(char[] currentChars, List<char[]> list) {
        int count = 0 ;
        for(char[] chars: list){
            count += compare2Chars(currentChars, chars);
        }
        return count;
    }

    private static int compare2Chars(char[] currentChars, char[] chars) {
        int count = 0;
        for(int i = 0; i < currentChars.length; i++){
            if(currentChars[i] != chars[i]){
                count++;
            }
        }
        return count;
    }

}

