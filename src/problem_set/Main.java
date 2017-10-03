package problem_set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private int chamber, specimen;
    private double average, imbalance;
    private List<Integer> specimensList = new ArrayList<>();
    private List<List<Integer>> result = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int counter = 0;
        while (sc.hasNext()) {
            chamber = sc.nextInt();
            specimen = sc.nextInt();
            specimensList.clear();
            result.clear();
            counter++;

            for (int i = 0; i < specimen; i++) {
                specimensList.add(sc.nextInt());
            }

            average = ((double) specimensList.stream().reduce(0, (a, b) -> a + b)) / chamber;

            if (specimen <= chamber) {
                for (int i = 0; i < specimen; i++) {
                    List<Integer> list = new ArrayList<>();
                    list.add(specimensList.get(i));
                    result.add(list);
                }
                for (int i = specimen; i < chamber; i++) {
                    List<Integer> list = new ArrayList<>();
                    result.add(list);
                }
            } else {
                for (int i = specimen; i < 2 * chamber; i++) {
                    specimensList.add(0);
                }
                Collections.sort(specimensList);
                for (int i = 0; i < chamber; i++) {
                    List<Integer> list = new ArrayList<>();
                    if(specimensList.get(i) > 0){
                        list.add(specimensList.get(i));
                    }
                    list.add(specimensList.get(2 * chamber - 1 - i));
                    result.add(list);
                }
            }

            imbalance = 0.0;
            for(int i = 0; i < chamber; i++){
                if(result.get(i).size() == 0){
                    imbalance += average;
                } else {
                    imbalance += Math.abs(average - result.get(i).stream().reduce(0, (a, b) -> a + b));
                }
            }
            System.out.println(String.format("Set #%d", counter));
            for(int i = 0; i < chamber; i++){
                if(result.get(i).size() > 0){
                    System.out.println(String.format(" %d: %s", i, result.get(i).stream().map(String::valueOf).collect(Collectors.joining(" "))));
                } else {
                    System.out.println(String.format(" %d:", i));
                }
            }
            System.out.println(String.format("IMBALANCE = %.5f", imbalance));
            System.out.println("");
        }
    }
}

