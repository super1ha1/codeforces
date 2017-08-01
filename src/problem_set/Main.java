package problem_set;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static Map<String, Character> map = createMap();

    private static Map<String,Character> createMap() {
        Map<String, Character> map = new HashMap<String, Character>();
        map.put(".-",'A');
        map.put(".---",'J');
        map.put("...",'S');
        map.put(".----",'1');
        map.put(".-.-.-",'.');
        map.put("---...",':');
        map.put("-...",'B');
        map.put("-.-",'K');
        map.put("-",'T');
        map.put("..---",'2');
        map.put("--..--",',');
        map.put("-.-.-.",';');
        map.put("-.-.",'C');
        map.put(".-..",'L');
        map.put("..-",'U');
        map.put("...--",'3');
        map.put("..--..",'?');
        map.put("-...-",'=');
        map.put("-..",'D');
        map.put("--",'M');
        map.put("...-",'V');
        map.put("....-",'4');
        map.put(".----.",'\'');
        map.put(".-.-.",'+');
        map.put(".",'E');
        map.put("-.",'N');
        map.put(".--",'W');
        map.put(".....",'5');
        map.put("-.-.--",'!');
        map.put("-....-",'-');
        map.put("..-.",'F');
        map.put("---",'O');
        map.put("-..-",'X');
        map.put("-....",'6');
        map.put("-..-.",'/');
        map.put("..--.-",'_');
        map.put("--.",'G');
        map.put(".--.",'P');
        map.put("-.--",'Y');
        map.put("--...",'7');
        map.put("-.--.",'(');
        map.put(".-..-.",'"');
        map.put("....",'H');
        map.put("--.-",'Q');
        map.put("--..",'Z');
        map.put("---..",'8');
        map.put("-.--.-",')');
        map.put(".--.-.",'@');
        map.put("..",'I');
        map.put(".-.",'R');
        map.put("-----",'0');
        map.put("----.",'9');
        map.put(".-...",'&');
        return map;
    }

    public static void main(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        int testCase = sc.nextInt();
        sc.nextLine();
        for(int i = 0; i < testCase; i++){
            String line = sc.nextLine();
            process(line, i + 1);
            if(i + 1 < testCase){
                System.out.println("");
            }
        }
    }

    private static void process(String line, int n) {
        String output = "";
        String[] splitWord = line.split("  ");
        for(int i  = 0; i < splitWord.length; i++){
            String word = splitWord[i];
            String[] chars = word.split(" ");
            for(int j = 0; j < chars.length; j++){
                String c = chars[j];
                if(map.keySet().contains(c)){
                    output += map.get(c);
                }else {
//                    System.out.println("Not recognize: index: " + j +  " str: " + c);
                }
            }
            if(i + 1 < splitWord.length){
                output += " ";
            }
        }
        System.out.println("Message #" + n);
        System.out.println(output);
    }

}

