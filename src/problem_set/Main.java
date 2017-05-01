package problem_set;



import java.util.*;

public class Main {

    static int[] array = new int[]{5,10,12,13,3,6,12,10,3,5,9};
    static int[] array1 = new int[]{1,9,2,12,4,13,3};
    static int[] array3 = new int[]{7, 12, 2, 9, 3, 5, 2, 4};
    static int[] array2 = new int[]{10, 12, 4, 12, 9, 7, 7, 9, 7, 10, 5, 12, 4, 6, 8, 5, 13, 6, 1, 2, 5, 10, 8, 12, 6, 10, 13, 2, 1, 7, 9, 10};
    static int[] arrayAll = new int[]{10, 12, 4, 12, 9, 7, 7, 9, 7, 10, 5, 12, 4, 6, 8, 5, 13, 6, 1, 2, 5, 10, 8, 12, 6, 10, 13, 2, 1, 7, 9, 10, 3, 13, 10, 11, 3, 10, 10, 11, 7, 6, 4, 11, 7, 9, 9, 10, 10, 3, 5, 10, 2, 11, 4, 6, 4, 2, 9, 12, 2, 8, 7, 12, 3, 1, 6, 9, 5, 10, 13, 11, 12, 10, 6, 1, 3, 10, 12, 9, 6, 9, 5, 3, 6, 12, 2, 12, 12, 6, 8, 5, 12, 8, 10, 12, 11, 9, 12, 5, 11, 9, 13, 4, 11, 7, 11, 8, 12, 1, 8, 6, 5, 7, 1, 10, 3, 4, 11, 10, 4, 12, 1, 1, 9, 1, 12, 1, 11, 5, 6, 10, 11, 6, 7, 5, 12, 6, 10, 7, 7, 10, 4, 13, 2, 3, 1, 1, 11, 13, 11, 10, 3, 2, 3, 8, 7, 8, 10, 9, 9, 12, 10, 11, 10, 11, 1, 5, 12, 2, 10, 7, 1, 8, 9, 3, 12, 10, 10, 5, 10, 1, 4, 6, 2, 4, 7, 1, 1, 5, 11, 11, 7, 9, 7, 6, 1, 8, 9, 2, 13, 5, 12, 5, 13, 12, 3, 2, 7, 11, 6, 12, 5, 12, 5, 11, 3, 9, 13, 7, 12, 2, 9, 3, 5, 2, 4, 9, 8, 2, 4, 12, 13, 8, 9, 12, 7, 3, 7, 10, 11, 5, 9, 1, 13, 7, 11, 5, 6, 5, 11, 3, 1, 13, 8, 9, 11, 7, 5, 6, 6, 12, 4, 8, 13, 5, 5, 10, 2, 8, 7, 2, 6, 4, 1, 13, 9, 11, 5, 7, 6, 4, 8, 6, 7, 8, 10, 6, 4, 7, 12, 2, 3, 5, 4, 5, 4, 5, 2, 7, 1, 8, 3, 7, 12, 3, 9, 3, 10, 8, 5, 8, 3, 3, 9, 13, 2, 12, 12, 9, 11, 2, 4, 2, 9, 7, 3, 8, 4, 9, 3, 6, 3, 7, 5, 13, 13, 7, 9, 8, 8, 12, 2, 1, 9, 2, 12, 4, 13, 3, 10, 11, 13, 2, 10, 5, 7, 10, 12, 1, 12, 6, 10, 2, 7, 5, 2, 9, 6, 13, 3, 12, 4, 7, 2, 13, 7, 13, 4, 10, 8, 1, 10, 13, 4, 10, 4, 10, 3, 2, 2, 13, 3, 8, 1, 2, 9, 8, 2, 1, 8, 5, 3, 3, 7, 12, 10, 13, 5, 9, 3, 4, 7, 1, 9, 1, 12, 10, 13, 6, 5, 10, 2, 12, 13, 4, 12, 7, 11, 12, 13, 6, 11, 3, 2, 7, 6, 4, 2, 9, 13, 12, 9, 6, 9, 3, 10, 13, 10, 12, 5, 12, 2, 12, 9, 6, 13, 7, 13, 5, 3, 12, 3, 9, 12, 7, 4, 11, 3, 3, 12, 8, 10, 8, 7, 8, 9, 5, 7, 8, 13, 3, 8, 7, 5, 5, 8, 9, 1, 13, 8, 1, 2, 1, 4, 5, 4, 12, 9, 5, 10, 1, 8, 3, 1, 13, 10, 13, 12, 6, 9, 10, 2, 6, 6, 4, 12, 6, 2, 1, 4, 11, 11, 7, 1, 2, 5, 1, 11, 9, 2, 10, 12, 11, 10, 12, 8, 2, 1, 5, 4, 2, 12, 9, 5, 7, 3, 6, 9, 3, 3, 10, 5, 9, 12, 4, 2, 8, 2, 3, 8, 11, 12, 10, 2, 9, 6, 4, 9, 5, 5, 6, 5, 8, 9, 5, 2, 5, 2, 6, 4, 12, 6, 8, 13, 4, 4, 13, 5, 9, 1, 7, 13, 4, 9, 8, 7, 10, 2, 3, 5, 6, 7, 5, 3, 7, 13, 7, 13, 1, 4, 2, 7, 8, 8, 3, 6, 8, 5, 5, 8, 6, 3, 6, 6, 9, 9, 5, 13, 10, 8, 2, 11, 4, 10, 13, 10, 3, 11, 8, 10, 6, 12, 7, 4, 7, 7, 8, 1, 9, 2, 3, 11, 7, 10, 11, 9, 12, 3, 8, 13, 5, 3, 11, 8, 6, 13, 1, 11, 10, 8, 7, 4, 11, 9, 6, 6, 10, 13, 9, 4, 5, 3, 6, 10, 13, 1, 6, 11, 11, 7, 6, 13, 10, 13, 9, 9, 4, 1, 3, 3, 10, 11, 13, 4, 2, 4, 4, 1, 12, 2, 2, 7, 12, 11, 12, 1, 1, 7, 1, 6, 2, 2, 6, 5, 4, 11, 9, 2, 13, 5, 11, 13, 4, 13, 6, 2, 9, 8, 13, 2, 2, 2, 11, 3, 12, 6, 5, 5, 6, 4, 7, 1, 13, 9, 8, 1, 10, 13, 10, 6, 8, 2, 10, 7, 3, 9, 1, 7, 8, 1, 3, 9, 3, 1, 8, 1, 8, 10, 6, 3, 9, 2, 2, 10, 7, 11, 9, 3, 5, 7, 2, 1, 9, 11, 7, 3, 10, 3, 6, 2, 13, 10, 8, 10, 8, 1, 7, 13, 1, 8, 13, 2, 13, 8, 8, 4, 5, 11, 3, 4, 6, 12, 6, 10, 9, 13, 3, 1, 2, 1, 1, 6, 1, 1, 8, 1, 8, 13, 13, 8, 2, 7, 10, 6, 13, 6, 4, 4, 5, 8, 9, 2, 6, 5, 7, 10, 8, 11, 3, 11, 8, 4, 3, 7, 9, 9, 2, 5, 13, 7, 6, 6, 3, 2, 13, 8, 9, 7, 5, 9, 4, 7, 13, 11, 3, 11, 13, 13, 2, 8, 12, 7, 7, 6, 7, 9, 1, 7, 12, 2, 8, 6, 11, 8, 9, 10, 13, 13, 5, 8, 12, 9, 11, 6, 12, 5, 5, 13, 3, 12, 6, 5, 7, 3, 9, 4, 9, 1, 1, 11, 11, 4, 5, 8, 9, 12, 1, 7, 13, 11, 4, 10, 8, 8, 9, 2, 1, 8, 12, 13, 4, 8, 3, 2, 5, 1, 1, 8, 13, 4, 1, 11, 12, 13, 7, 12, 11, 11, 10, 11, 6, 5, 11, 5, 12, 2, 5, 3, 13, 7, 7, 4, 6, 13, 8, 1, 6, 7, 4, 7, 2, 9, 5, 6, 1, 11, 12, 1, 8};
    static int counter = 0;
    static int currentIndex = 0;

    public static void main(String[] args) throws Exception {
        List<Integer> list = new ArrayList<Integer>();
        for(int i : arrayAll){
            list.add(i);
        }

        List<Integer> updatedList = process(list);
//        printResult(list, updatedList.size(), 0);
        List<Integer> previousList = new ArrayList<Integer>();

        while (updatedList.size() >= 4){
            int previousCounter = counter;
            previousList = updatedList;
            updatedList = process(updatedList);
//            printResult(previousList, updatedList.size(), previousCounter);
        }
        System.out.println(counter);
    }

    private static void printResult(List<Integer> oldList, int newSize, int previousCounter) {
        String output = "";
        int userMin = 0, userMax = 0, dealerMax = 0;
        for(int i = 0; i < oldList.size() - newSize; i++){
            output += oldList.get(i) + " ";
            if(i % 2 == 0 || i > 3){
                userMin += parseValue1As1(oldList.get(i));
                userMax += parseValue1As11(oldList.get(i));
            }else {
                dealerMax += parseValue1As11(oldList.get(i));
            }
        }
        output += "--- userMin: " + userMin + " userMax: " + userMax + " dealerMax: " + dealerMax;
        boolean update = counter > previousCounter;
        System.out.println("Round " + counter + " - " + output);

    }

    public static List<Integer> process(List<Integer> list){
        int user1 = parseValue1As1(list.get(0)) + parseValue1As1(list.get(2));
        int user2 = parseValue1As11(list.get(0)) + parseValue1As11(list.get(2));

        int maxDealer = parseValue1As11(list.get(1)) + parseValue1As11(list.get(3));
        int maxUser = Math.max(user1, user2);
        int minUser = Math.min(user1, user2);

        List<Integer> newList =  list.subList(4, list.size());
        if(maxDealer == 21 ){
            return newList;
        }

        if((minUser <= 21 && minUser > maxDealer) || (maxUser <= 21 && maxUser > maxDealer) || (minUser <= 21 && maxDealer > 21)){
            counter++;
            return newList;
        }

        int result = check(maxDealer, maxUser, newList);
        if(result > 0){
            counter++;
            return newList.subList(result, newList.size());
        }

        result = check(maxDealer, minUser, newList);
        if(result > 0){
            counter++;
            return newList.subList(result, newList.size());
        }

        return newList;
    }

    private static int check(int maxDealer, int maxUser, List<Integer> newList) {
        currentIndex = 0;
        if(recursiveCheck(maxDealer, maxUser, newList, 0)){
            return currentIndex + 1;
        }
        return -1;
    }

    private static boolean recursiveCheck(int maxDealer, int maxUser, List<Integer> newList, int index){
        if(index >= newList.size()){
            return false;
        }
        int value = newList.get(index);
        int low = parseValue1As1(value);
        int high = parseValue1As11(value);

        if(test(maxDealer, maxUser + low) || test(maxDealer, maxUser + high)){
            currentIndex = index;
//            System.out.println("return true here: dealer" + maxDealer + " userMin " +  (maxUser + low) + " useMax " + (maxUser + high));
            return true;
        }
        boolean shouldLow = shouldContinue(maxUser, low);
        boolean shouldHigh = shouldContinue(maxUser, high);
        if(shouldHigh || shouldLow){
            boolean lowSide = false, highSide = false;
            if(shouldLow){
                lowSide = recursiveCheck(maxDealer, maxUser +low, newList, index + 1);
                if(lowSide){
                    return true;
                }
            }

            if(shouldHigh){
                highSide = recursiveCheck(maxDealer, maxUser + high, newList, index + 1);
                if(highSide){
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean shouldContinue(int maxUser, int value) {
        return (maxUser + value <= 21);
    }

    private static boolean test(int maxDealer, int value) {
        if(value > 21){
            return false;
        }
        if(value <= 21 && value > maxDealer){
            return true;
        }
        return false;
    }

    private static int parseValue1As1(int value){
        if(value == 12 || value == 13 || value == 11){
            return 10;
        }
        return value;
    }
    private static int parseValue1As11(int value){
        if(value == 1){
            return 11;
        }
        if(value == 12 || value == 13 || value == 11){
            return 10;
        }
        return value;
    }
}

