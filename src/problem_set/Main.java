package problem_set;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    private List<Integer> inputList;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()) {
            inputList = new ArrayList<>();
            while (true){
                int value = sc.nextInt();
                if(value == -999999) {
                    break;
                }
                inputList.add(value);
            }
            if(inputList.size() == 0) {
                continue;
            }

            BigInteger maxValue = BigInteger.valueOf(Long.MIN_VALUE);
            for(int i = 0; i < inputList.size(); i++) {
                BigInteger max = BigInteger.valueOf(inputList.get(i));
                BigInteger value = BigInteger.valueOf(inputList.get(i));
                for (int j = i + 1; j < inputList.size(); j++) {
                    value = value.multiply(BigInteger.valueOf(inputList.get(j)));
                    if(value.compareTo(max) > 0) {
                        max = value;
                    }
                }

                if(max.compareTo(maxValue) > 0)  {
                    maxValue = max;
                }
            }
            System.out.println(maxValue);
        }
    }
}

