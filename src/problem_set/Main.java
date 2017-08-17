package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private static final List<Integer> PRIME = Arrays.asList(3, 5, 7, 11, 13, 17, 19, 23, 29, 31);
    private static List<List<Integer>> result;
    private static final Map<Integer, List<List<Integer>>> map = new HashMap<>();
    public static void main(String[] args) throws Exception {

//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int count = 0;
        preprocess();
        while (sc.hasNext()){
            int n = sc.nextInt();
            count++;

            process(n, count);

            if(sc.hasNext()){
                System.out.println("");
            }else {
                break;
            }
        }
    }

    private static void preprocess() {
        List<Integer> list1 = Arrays.asList(1);
        List<Integer> list2 = Arrays.asList(1, 2);
        List<List<Integer>> result1 = new ArrayList<>();
        List<List<Integer>> result2 = new ArrayList<>();
        result1.add(list1);
        result2.add(list2);
        map.put(1, result1);

        for(int i = 2; i <= 16; i++){
            List<List<Integer>> newList = createList(i);
            map.put(i, newList);
        }
    }

    private static List<List<Integer>> createList(int n) {
        result = new ArrayList<>();

        Set<Integer> currentSet = new HashSet<>();
        for(int i = 2; i<= n; i++){
            currentSet.add(i);
        }
        List<Integer> comingList = new ArrayList<>();
        comingList.add(1);
        backTrack(currentSet, comingList);

        return result;
    }

    private static void process(int n, int count) {
        System.out.println(String.format("Case %d:", count));
        List<String> resultList = new ArrayList<>();
        for(List<Integer> list: map.get(n)){
            String s = list.stream().map(i -> String.valueOf(i)).collect(Collectors.joining(" "));
            if(!resultList.contains(s)){
                resultList.add(s);
            }
        }
        for(List<Integer> list: map.get(n)){
            String output = "1 ";
            for(int i = list.size() -1 ; i >= 1; i--){
                output+= list.get(i)  + " ";
            }
            output = output.trim();
            if(!resultList.contains(output)){
                resultList.add(output);
            }
        }
        for(String s: resultList){
            System.out.println(s);
        }
    }

    private static boolean backTrack(Set<Integer> currentSet, List<Integer> comingList) {

        if(currentSet.size() == 1){
            int value = currentSet.iterator().next();
            if(PRIME.contains(value + 1)){
                comingList.add(value);
                result.add(comingList);
                return true;
            }else {
                return false;
            }
        }

        if(comingList.size() - currentSet.size() == 0 || comingList.size() - currentSet.size() == 1){
            //check if this solution found before
            if(isSame(comingList)){
                return false;
            }
        }

        int currentValue = comingList.get(comingList.size() - 1);
        List<Integer> possible = PRIME.stream().map(i -> i - currentValue)
                .filter(i -> i > 0)
                .filter(i -> currentSet.contains(i))
                .collect(Collectors.toList());
        for(int i: possible){
            comingList.add(i);
            currentSet.remove(i);
            if (backTrack(new HashSet<>(currentSet), new ArrayList<>(comingList))){
                //do nothing for now, continue
            }
            comingList.remove(comingList.size() -1);
            currentSet.add(i);
        }
        return false;
    }

    private static boolean isSame(List<Integer> comingList) {
        for(List<Integer> list: result){
            int i = 1;
            while (i < comingList.size()){
                if(comingList.get(i) == list.get(i) || comingList.get(i) == list.get(list.size() -i)){
                    i++;
                }else {
                    break;
                }
            }
            if(i == comingList.size()){
                return true;
            }
        }
        return false;
    }
}

