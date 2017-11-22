package problem_set;

import java.util.ArrayList;
import java.util.Collections;
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
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int value = -1;
        try {
            inputList = new ArrayList<>();
            while (sc.hasNext()) {
                value = sc.nextInt();
                inputList.add(value);
            }
        } catch (Exception e) {
            System.out.println("value: " + value);
            e.printStackTrace();
        }

        int n = inputList.size();
        List<Integer> indexList = new ArrayList<>();
        indexList.add(0);
        int[] parent = new int[n];
        for(int i = 0; i < n; i++){
            parent[i] = -1;
        }

        for (int i = 1; i < n; i++) {
            int currentValue = inputList.get(i);
            int lastValue = inputList.get(indexList.get(indexList.size() - 1));
            if (currentValue > lastValue) {
                // case 3: value larger than all in inputList
                parent[i] = indexList.get(indexList.size() - 1);
                indexList.add(i);

            } else {
                // case 2: in the middle
                int indexMinLarger = customBinarySearch(indexList, currentValue);
                indexList.add(indexMinLarger + 1, i);
                indexList.remove(indexMinLarger);

                if(indexMinLarger > 0) {
                    parent[i] = indexList.get(indexMinLarger -1);
                }
            }
        }

        List<Integer> resultList = new ArrayList<>();
        int parentIndex = indexList.get(indexList.size() -1);
        resultList.add(inputList.get(parentIndex));
        while (true) {
            parentIndex = parent[parentIndex];
            if(parentIndex == -1) {
                break;
            }
            resultList.add(inputList.get(parentIndex));
        }

        Collections.reverse(resultList);
        System.out.println(resultList.size());
        System.out.println("-");
        for (int i : resultList) {
            System.out.println(i);
        }
    }

    private int customBinarySearch(List<Integer> list, int currentValue) {
        return binarySearch(list, 0, list.size() -1, currentValue);
    }

    private int binarySearch(List<Integer> list, int low, int high, int value) {
        if(high == low) {
            if(inputList.get(list.get(low)) >= value) {
                return low;
            } else {
                return low + 1;
            }
        }

        int mid = (high + low) /2;
        int midValue = inputList.get(list.get(mid));
        if(midValue < value) {
            return binarySearch(list, mid + 1, high, value);
        } else if(midValue > value) {
            return binarySearch(list, low, mid, value);
        } else {
            return mid;
        }
    }
}

