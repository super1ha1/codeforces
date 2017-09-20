package problem_set;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private  int[] attack, defend;
    private  String[] names;
    private int[] visit;
    private int maxAttack, maxDefend;
    private String minAttackNames, defendNames;
    private boolean firstTime;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        sc.nextLine();
        int counter = 0;
        while (testCase-- > 0){
            counter++;
            attack = new int[10];
            defend = new int[10];
            names = new String[10];
            visit = new int[10];
            firstTime = false;

            for(int i = 0; i < 10; i++){
                String line = sc.nextLine().trim();
                String[] array = line.split("[\\s]+");
                names[i] = array[0];
                attack[i] = Integer.parseInt(array[1]);
                defend[i] = Integer.parseInt(array[2]);
            }

            backtrack(0);

            System.out.println(String.format("Case %d:", counter));
            System.out.println(minAttackNames);
            System.out.println(defendNames);
        }
    }

    private void backtrack(int current) {
        if(current == 5){
            if(!firstTime){
                setUpNewValue();
                firstTime = true;
                return;
            }

            int attackValue = getAttackValueVisit();
            if(attackValue > maxAttack){
                setUpNewValue();
            }else if(attackValue == maxAttack){
                int defendValue = getDefendValueVisit();
                if(defendValue > maxDefend){
                    setUpNewValue();
                }else if(defendValue == maxDefend){
                    if(getAttackNamesCurrentVisit().compareTo(minAttackNames) < 0){
                        setUpNewValue();
                    }
                }
            }
            return;
        }

        for(int i = 0; i < 10; i++){
            if(visit[i] == 0){
                visit[i] = 1;
                backtrack(current + 1);
                visit[i] = 0;
            }
        }
    }

    private void setUpNewValue() {
        maxAttack = getAttackValueVisit();
        maxDefend = getDefendValueVisit();
        minAttackNames = getAttackNamesCurrentVisit();
        defendNames = getDefendNamesCurrentVisit();
    }

    private int getAttackValueVisit() {
        int total = 0;
        for(int i = 0; i < 10; i++){
            if(visit[i] == 1){
                total += attack[i];
            }
        }
        return total;
    }

    private int getDefendValueVisit() {
        int total = 0;
        for(int i = 0; i < 10; i++){
            if(visit[i] == 0){
                total += defend[i];
            }
        }
        return total;
    }

    private String getAttackNamesCurrentVisit() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            if(visit[i] == 1){
                list.add(names[i]);
            }
        }
        Collections.sort(list);
        return "(" + list.stream().collect(Collectors.joining(", ")) + ")";
    }

    private String getDefendNamesCurrentVisit() {
        List<String> list = new ArrayList<>();
        for(int i = 0; i < 10; i++){
            if(visit[i] == 0){
                list.add(names[i]);
            }
        }
        Collections.sort(list);
        return "(" + list.stream().collect(Collectors.joining(", ")) + ")";
    }
}

