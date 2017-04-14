package problem_set;

import com.sun.org.apache.regexp.internal.RE;

import java.io.FileReader;
import java.util.*;


public class Main {

    private static String [] first = {"B", "P", "F", "V"};
    private static String [] second = {"C", "S","K","G","J","Q","X","Z"};
    private static String [] third = {"D", "T"};
    private static String [] forth = {"L"};
    private static String [] fifth = {"M", "N"};
    private static String [] six = {"R"};
    private static Map<String, Integer> map = new HashMap<String, Integer>();
    public static void main(String[] args) throws  Exception{
//        Scanner sc = new Scanner(new FileReader("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/problemset/src/problem_set/in.txt"));
        prepareMap();
        Scanner sc = new Scanner(System.in);
        printPair("NAME", "SOUNDEX CODE");
        while (sc.hasNext()){
            String line = sc.nextLine().trim();
            String output = computeOutput(line);
            printPair(line, output);
        }
        System.out.println("                   END OF OUTPUT");

    }

    private static void prepareMap() {
        for(String s: first){
            map.put(s, 1);
        }
        for(String s: second){
            map.put(s, 2);
        }
        for(String s: third){
            map.put(s, 3);
        }
        for(String s: forth){
            map.put(s, 4);
        }
        for(String s: fifth){
            map.put(s, 5);
        }
        for(String s: six){
            map.put(s, 6);
        }

    }

    private static String computeOutput(String input) {
        String output = "" + input.charAt(0);
        for(int i = 1; i < input.length(); i++){
            if(map.containsKey(String.valueOf(input.charAt(i)))){
                int number = map.get(String.valueOf(input.charAt(i)));

                if(map.containsKey(String.valueOf(input.charAt(i -1)))){
                    int previous = map.get(String.valueOf(input.charAt(i -1)));
                    if(number == previous){
                        continue;
                    }
                }
                output += String.valueOf(number);

            }
        }
        while (output.length() < 4){
            output += "0";
        }

        return output.substring(0,4);
    }

    private static void printPair(String name, String output) {
        String newLine = "         " + name;
        while (newLine.length() < 34){
            newLine += " ";
        }
        newLine += output;
        System.out.println(newLine);
    }

}