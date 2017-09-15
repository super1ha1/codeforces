package problem_set;

import java.util.*;

public class Main {

    private static final Map<String, Integer> resultMap = new HashMap<>();
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz";
    private static int counter, expectedLen;
    public static void main(String[] args) throws Exception {

        prepare();
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            String line = sc.nextLine().trim();
            if(resultMap.containsKey(line)){
                System.out.println(resultMap.get(line));
            }else {
                System.out.println(0);
            }
        }
    }

    private static void prepare() {
        counter = 1;
        for(int len = 1; len <= 5; len++){
            expectedLen = len;
            backTrack("", ALPHABET);
        }
    }

    private static void backTrack(String currentString, String leftOver){
        if(currentString.length() == expectedLen){
            resultMap.put(currentString, counter);
            counter++;
            return;
        }
        for(int i  = 0; i < leftOver.length(); i++){
            if(currentString.length() == 0 || (currentString.charAt(currentString.length() -1) < leftOver.charAt(i))){
                currentString += leftOver.substring(i, i + 1);
                backTrack(currentString, leftOver.substring(0, i) + leftOver.substring(i + 1));
                currentString = currentString.substring(0, currentString.length() -1);
            }
        }
    }
}

