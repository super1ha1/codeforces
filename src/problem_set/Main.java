package problem_set;

import java.util.*;

public class Main {

    private static List<Map.Entry<Long, Long>> bees = new ArrayList<>();
    public static void main(String[] args) throws Exception {

        prepareBee();

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNext()) {
            long n = sc.nextLong();
            if(n < 0){
                break;
            }
            if(n >= bees.size()){
                continue;
            }
            System.out.println(String.format("%d %d", bees.get((int)n).getKey(),
                    bees.get((int)n).getKey() + bees.get((int)n).getValue()));
        }
    }

    private static void prepareBee() {
        bees.add(new AbstractMap.SimpleEntry<>(0L, 1L));
        int index = 0;
        while (true){
            index++;
            Map.Entry<Long, Long> previous = bees.get(index -1);
            long nextMale = previous.getKey() + previous.getValue();
            long nextFemale = previous.getKey() + 1;
            bees.add(new AbstractMap.SimpleEntry<>(nextMale, nextFemale));

            if(nextMale > Integer.MAX_VALUE || nextFemale > Integer.MAX_VALUE){
                break;
            }
        }
    }

}

