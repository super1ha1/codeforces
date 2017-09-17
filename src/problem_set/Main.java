package problem_set;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class Main {


    private static int maxAttack;
    private static final List<Set<Integer>> maxAttackResultList = new ArrayList<>();
    private static final List<Player> inputPlayList = new ArrayList<>();
    private static final List<Integer> fixIndexList = new ArrayList<>();

    private static final Comparator<Player> playerComparatorByName = Comparator.comparing(o -> o.name);

    public static void main(String[] args) throws Exception {

        for(int i = 0; i < 10; i++){
            fixIndexList.add(i);
        }

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int T = sc.nextInt();
        sc.nextLine();
        for(int i = 1; i <= T; i++){
            maxAttackResultList.clear();
            inputPlayList.clear();

            for(int j = 0; j < 10; j++){
                String line = sc.nextLine().trim();
                String[] array = line.split("[\\s]+");
                Player player = new Player(array[0], Integer.valueOf(array[1]), Integer.valueOf(array[2]));
                inputPlayList.add(player);
            }
            System.out.println(String.format("Case %d:", i));
            process();
        }
    }

    private static void process() {
        maxAttack();
        if(maxAttackResultList.size() == 1){
            Set<Integer> defendSet = getDefendFromAttack(maxAttackResultList.get(0));
            printResult(setToSortedListByName(maxAttackResultList.get(0)));
            printResult(setToSortedListByName(defendSet));
        }else {
            List<Set<Integer>> maxDefendList = maxDefendList();
            if(maxDefendList.size() == 1){
                Set<Integer> defendSet = getDefendFromAttack(maxDefendList.get(0));
                printResult(setToSortedListByName(maxDefendList.get(0)));
                printResult(setToSortedListByName(defendSet));
            }else {
                Map<String, String> resultMap = new HashMap<>();
                for(Set<Integer> set: maxDefendList){
                    String attack = getNamePlayer(setToSortedListByName(set));
                    String defend = getNamePlayer(setToSortedListByName(getDefendFromAttack(set)));
                    resultMap.put(attack, defend);
                }
                List<String> attackSting = new ArrayList<>(resultMap.keySet());
                Collections.sort(attackSting);
                System.out.println("(" + attackSting.get(0) + ")");
                System.out.println("(" + resultMap.get(attackSting.get(0)) + ")");
            }
        }
    }

    private static Set<Integer> getDefendFromAttack(Set<Integer> integers) {
        return fixIndexList.stream().filter(i -> !integers.contains(i)).collect(Collectors.toSet());
    }

    private static List<Player> setToSortedListByName(Set<Integer> set) {
        List<Player> localList = set.stream().map(inputPlayList::get).collect(Collectors.toList());
        localList.sort(playerComparatorByName);
        return localList;
    }

    private static void printResult(List<Player> result) {
        System.out.println("(" + getNamePlayer(result) + ")");
    }

    private static String getNamePlayer(List<Player> result) {
        return result.stream().map(i -> i.name).collect(Collectors.joining(", "));
    }

    private static List<Set<Integer>> maxDefendList() {
        int maxDefend = Integer.MIN_VALUE;
        List<Set<Integer>> defendList = new ArrayList<>();
        for(Set<Integer> set: maxAttackResultList){
            Set<Integer> defendSet = getDefendFromAttack(set);
            int value = getDefendValue(defendSet);
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
        backTrack(new HashSet<>(), new ArrayList<>(fixIndexList));
    }

    private static int getDefendValue(Set<Integer> set) {
        int value = 0;
        for(Integer playerIndex: set){
            value += inputPlayList.get(playerIndex).defend;
        }
        return value;
    }

    private static void backTrack(Set<Integer> currentSet, List<Integer> leftOverList){
        if(currentSet.size() == 5){
            int value = getAttackValue(currentSet);
            if(value == maxAttack){
                if(notYetResult(currentSet)){
                    maxAttackResultList.add(currentSet);
                }
            } else if(value > maxAttack){
                maxAttack = value;
                maxAttackResultList.clear();
                maxAttackResultList.add(currentSet);
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
        for(Set<Integer> set: maxAttackResultList){
            if(set.equals(currentSet)){
                return false;
            }
        }
        return true;
    }

    private static int getAttackValue(Set<Integer> currentList) {
        int value = 0;
        for(Integer player: currentList){
            value += inputPlayList.get(player).attack;
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

