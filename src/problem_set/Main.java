package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    private static int maxAttack;
    private static List<Set<Integer>> resultList = new ArrayList<>();
    private static List<Player> list = new ArrayList<>();
    private static Comparator<Player> playerComparator = Comparator.comparing(o -> o.name);

    public static void main(String[] args) throws Exception {

//        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int T = sc.nextInt();
        sc.nextLine();
        for(int i = 1; i <= T; i++){
            resultList.clear();
            list.clear();

            for(int j = 0; j < 10; j++){
                String line = sc.nextLine().trim();
                String[] array = line.split("[\\s]+");
                Player player = new Player(array[0], Integer.valueOf(array[1]), Integer.valueOf(array[2]));
                list.add(player);
            }
            System.out.println(String.format("Case %d:", i));
            process();
        }
    }

    private static void process() {
        maxAttack();
        if(resultList.size() == 1){
            printResult(setToSortedList(resultList.get(0)));
        }else {
            List<Set<Integer>> maxDefendList = maxDefendList();
            if(maxDefendList.size() == 1){
                printResult(setToSortedList(maxDefendList.get(0)));
            }else {
                List<List<Player>> finalPlayer = new ArrayList<>();
                for(Set<Integer> set: maxDefendList){
                    finalPlayer.add(setToSortedList(set));
                }
                finalPlayer.forEach(singleList -> singleList.sort(playerComparator));
                finalPlayer.sort((o1, o2) -> {
                    int index = 0;
                    while (playerComparator.compare(o1.get(index), o2.get(index)) == 0) {
                        index++;
                    }
                    return playerComparator.compare(o1.get(index), o2.get(index));
                });
            }
        }
    }

    private static List<Player> setToSortedList(Set<Integer> set) {
        List<Player> localList = set.stream().map(i -> list.get(i)).collect(Collectors.toList());
        localList.sort(playerComparator);
        return localList;
    }

    private static void printResult(List<Player> result) {

    }

    private static List<Set<Integer>> maxDefendList() {
        int maxDefend = Integer.MIN_VALUE;
        List<Set<Integer>> defendList = new ArrayList<>();
        for(Set<Integer> set: resultList){
            int value = getMaxDefend(set);
            if(value > maxDefend){
                maxDefend = value;
                defendList.clear();
                defendList.add(set);
            }else if(value == maxDefend){
                defendList.add(set);
            }
        }
        return defendList;
    }

    private static void maxAttack() {
        maxAttack = Integer.MIN_VALUE;
        List<Integer> valueList = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            valueList.add(i);
        }
        backTrack(new HashSet<>(), valueList);
    }

    private static int getMaxDefend(Set<Integer> set) {
        int value = 0;
        for(Integer player: set){
            value += list.get(player).defend;
        }
        return value;
    }

    private static void backTrack(Set<Integer> currentSet, List<Integer> leftOverList){
        if(currentSet.size() == 5){
            int value = getValue(currentSet);
            if(value == maxAttack){
                if(notYetResult(currentSet)){
                    resultList.add(currentSet);
                }
            }else if(value > maxAttack){
                maxAttack = value;
                resultList.clear();
                resultList.add(currentSet);
            }
            return;
        }
        List<Integer> integrationList = new ArrayList<>(leftOverList);
        for(Integer player: integrationList){
            currentSet.add(player);
            leftOverList.remove(player);

            backTrack(new HashSet<>(currentSet), new ArrayList<>(leftOverList));

            currentSet.remove(player);
            leftOverList.add(player);
        }
    }

    private static boolean notYetResult(Set<Integer> currentSet) {
        for(Set<Integer> set: resultList){
            if(set.equals(currentSet)){
                return false;
            }
        }
        return true;
    }

    private static int getValue(Set<Integer> currentList) {
        int value = 0;
        for(Integer player: currentList){
            value += list.get(player).attack;
        }
        return value;
    }

    private static class Player {
        public final String name;
        public final int attack, defend;
        public Player(String name, int attack, int defend){
            this.name = name;
            this.attack = attack;
            this.defend = defend;
        }
    }
}

