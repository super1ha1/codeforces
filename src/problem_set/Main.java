package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    static Scanner sc = new Scanner(System.in);
    static List<Integer> list = new ArrayList<Integer>();
//    static List<Set<Integer>> setList = new ArrayList<Set<Integer>>();

    public static void main(String[] args) throws Exception {
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        int n = sc.nextInt();
        while (n > 0){
            list.clear();
//            setList.clear();
            for(int i = 0; i < n; i++){
                list.add(sc.nextInt());
            }
            Collections.sort(list);
            process();
            n = sc.nextInt();
            if(n == 0){
                break;
            }else {
                System.out.println("");
            }
        }
    }

    private static void process() {
        List<Integer> left = new ArrayList<Integer>();
        recursive(left, list);
    }

    private static List<Integer> getList(List<Integer> list, int index) {
        List<Integer> newList = new ArrayList<Integer>();
        for(int i = 0; i <= list.size() - index; i++){
            newList.add(list.get(i));
        }
        return newList;
    }

    private static boolean recursive(List<Integer> currentList, List<Integer> left) {
        if(currentList.size() == 6){
            printList(currentList);
            return true;
        }
        if(left.size() == 0){
            return false;
        }
        for(int i = 0 ; i < left.size(); i++){
            int member = left.get(i);
            if(currentList.size() > 0 && member < currentList.get(currentList.size() - 1)){
                continue;
            }
            currentList.add(member);
            left.remove(i);
            if(recursive(currentList, left)){

            }else {

            }
            currentList.remove(currentList.size() - 1);
            left.add(i, member);
        }
        return false;
    }

    private static void printList(List<Integer> currentList) {
//        Set<Integer> currentSet = new HashSet<Integer>();
//        currentSet.addAll(currentList);
//
//        for(Set<Integer> set: setList){
//            if(set.containsAll(currentSet)){
//                return;
//            }
//        }
//        setList.add(currentSet);

        String output = "";
        for(int i: currentList){
            output += i + " ";
        }
        System.out.println(output.trim());
    }
}

