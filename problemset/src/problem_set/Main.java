package problem_set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;


public class Main {

    public static void main(String[] args) throws  Exception{
//        Scanner sc = new Scanner(new FileReader("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/problemset/src/problem_set/in.txt"));
        Scanner sc = new Scanner(System.in);
        String output = "";
        while (sc.hasNext()){
           String input = sc.nextLine();
           output += printnextLine(input);
           if(sc.hasNext()){
               output += '\n';
           }
        }
        System.out.println(output);
    }

    private static String printnextLine(String input) {
        StringBuilder output = new StringBuilder();
        String word = "";
        for(int i = 0 ; i < input.length(); i++) {
            if(input.charAt(i) == ' '){
                output.append(getReverse(word));
                output.append(input.charAt(i));
                word = "";
            }else {
                word += input.charAt(i);
            }
        }
        output.append(getReverse(word));
        return output.toString();
    }

    private static String getReverse(String word) {
        if(word == null || word.length() == 0){
            return "";
        }
        String output = "";
        for(int i = word.length() -1 ; i >= 0; i--){
            output+= word.charAt(i);
        }
        return output;
    }

}