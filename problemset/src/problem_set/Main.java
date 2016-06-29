package problem_set;

import java.io.BufferedReader;
import java.io.FileReader;
import java.text.DecimalFormat;
import java.util.*;


public class Main {

    private static  Scanner sc = new Scanner(System.in);
    private static DecimalFormat df=new DecimalFormat("0.000000");
    public static void main(String[] args){
        int n  = sc.nextInt();
        String temp = sc.nextLine();
        String first, second;
        Set<String> firstSubstringSet;
        while (n-- > 0){
            first = sc.nextLine();
            second = sc.nextLine();
            firstSubstringSet = getSubStringSet(first);
            boolean result = false;
            if(firstSubstringSet != null){
                for(String s: firstSubstringSet){
                    if(second.contains(s)) {
                        result = true;
                    }
                }
            }

            String str = result ? "YES" : "NO";
            System.out.println(str);
        }

    }

    private static Set<String> getSubStringSet(String first) {
        if(first == null || first.length() == 0) return  null;
        Set<String> result = new TreeSet<>();
        for( int i = 0; i < first.length(); i++){
            for( int j = i + 1; j <= first.length(); j++){
                result.add(first.substring(i, j));
            }
        }
//        for( String s: result){
//            System.out.println(s);
//        }
        return result;

    }


}