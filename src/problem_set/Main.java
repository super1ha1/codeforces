package problem_set;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()) {
            String line = sc.nextLine().trim();
            String[] array = line.split("[\\s]+");
            System.out.println(isSubSequence(array[0], array[1]) ? "Yes" : "No");
        }
    }

    private boolean isSubSequence(String s, String t) {
        if (t.length() < s.length()) {
            return false;
        }
        while (s.length() > 0) {
            char first = s.charAt(0);
            int index = t.indexOf(first);
            if(index < 0){
                return false;
            }
            s = s.substring(1);
            t = t.substring(index + 1);
        }
        return true;
    }
}

