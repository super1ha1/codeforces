package problem_set;

import com.sun.xml.internal.bind.v2.util.QNameMap;

import java.io.File;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()) {
            int n = sc.nextInt();
            int next = sc.nextInt();
            String result = next % 2 == 0 ? "0" : "1";
            for(int i = 1; i < n; i++){
                int value = sc.nextInt();
                result += String.valueOf(value % 2);
            }
            int count = 0;
            while (result.length() > 0){
                count++;
                AbstractMap.SimpleEntry<Integer, Integer> pair = getPairOdd(result, count % 2);
                int lastIndexOf1;
                if(count % 2 == 0){
                     lastIndexOf1 = result.lastIndexOf('1');
                }else {
                     lastIndexOf1 = result.lastIndexOf('0');
                }
                String first = result.substring(0, lastIndexOf1 < 0 ? 0 : lastIndexOf1);
                String second = result.substring(lastIndexOf1 + 1 > result.length() ? result.length() : lastIndexOf1 + 1, result.length());
                if(first.length() > second.length()){
                    result = second;
                }else {
                    result = first;
                }

                count++;
            }
            System.out.println(count % 2 == 0 ? "Second" : "First");
        }

    }

    private static AbstractMap.SimpleEntry<Integer, Integer>  getPairOdd(String result, int turn) {
        int[] array = new int[result.length()];
        array[0] = 1;
        for(int i = 1; i < array.length; i++){
            int value = 1;
            for(int j = 0; j < i; j++){
                if(((int) result.charAt(j) + value) % 2 == 1){
                    value++;
                }
            }
        }
        return null;
    }

    private static boolean canDeliver(Map<Character, Integer> map, int k) {
        for(char c: map.keySet()){
            if(map.get(c) > k){
                return false;
            }
        }
        return true;
    }

}

