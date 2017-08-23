package problem_set;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static List<String> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()) {
            String line = sc.nextLine().trim();
            if(line.matches("\\d+")){
                list.add(line);
            }else {
                process();
                list.clear();
            }
            if(!sc.hasNext()){
                process();
            }
        }
    }

    private static void process() {
        if(list.size() != 3){
            return;
        }
        long b = Long.valueOf(list.get(0));
        long p = Long.valueOf(list.get(1));
        long m = Long.valueOf(list.get(2));
        while (b >= m){
            b = b % m;
        }
        if( b == 0 || b == 1){
            System.out.println(b);
            return;
        }
        long mod1 = find(b, m);
        long left = p % mod1;
        long result = 1;
        for(int i = 0; i < left; i++){
            result = (result * b) % m;
        }
        System.out.println(result);
    }

    private static long find(long b, long m) {
        int i = 1;
        long value = (long) Math.pow(b, i);
        while (value % m != 1){
            while (value >= m){
                value = value % m;
            }
            i++;
            value = (value * b) % m;
        }
        return i;
    }

}

