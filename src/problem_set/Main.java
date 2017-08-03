package problem_set;

import java.util.Scanner;

public class Main {

    private static int[][][] array;
    private static final int NOT_APPLICABLE = -1;

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));
        while (sc.hasNextInt()){
            int T = sc.nextInt();
            int R = sc.nextInt();
            int H = sc.nextInt();
            array = new int[T][R][H];

            //get travel
            for(int travel = 0; travel < T; travel++){
                int price = sc.nextInt();

                // update price
                for(int restaurant = 0; restaurant < R; restaurant++){
                    for(int hotel = 0; hotel < H; hotel++){
                        if(array[travel][restaurant][hotel] >= 0) {
                            array[travel][restaurant][hotel] += price;
                        }
                    }
                }

                //update can match
                for(int restaurant = 0; restaurant < R; restaurant++){
                    int match = sc.nextInt();
                    if(match == 1){
                        for(int hotel = 0; hotel < H; hotel++){
                            array[travel][restaurant][hotel] = NOT_APPLICABLE;
                        }
                    }
                }
            }

            //get restaurant
            for(int restaurant = 0; restaurant < R; restaurant ++){
                int price = sc.nextInt();

                //update price
                for(int travel = 0; travel < T; travel++){
                    for(int hotel = 0; hotel < H; hotel++){
                        if(array[travel][restaurant][hotel] >= 0) {
                            array[travel][restaurant][hotel] += price;
                        }
                    }
                }

                //update match
                for(int hotel = 0; hotel < H; hotel++){
                    int match = sc.nextInt();
                    if(match == 1){
                        for(int travel = 0; travel < T; travel++){
                            array[travel][restaurant][hotel] = NOT_APPLICABLE;
                        }
                    }
                }
            }

            //get hotel
            for(int hotel = 0; hotel < H; hotel++){
                int price = sc.nextInt();

                //update price
                for(int travel = 0; travel < T; travel++){
                    for(int restaurant = 0; restaurant < R; restaurant++){
                        if(array[travel][restaurant][hotel] >= 0) {
                            array[travel][restaurant][hotel] += price;
                        }
                    }
                }

                //update match
                for(int travel = 0; travel < T; travel++){
                    int match = sc.nextInt();
                    if(match == 1){
                        for(int restaurant = 0; restaurant < R; restaurant++){
                            array[travel][restaurant][hotel] = NOT_APPLICABLE;
                        }
                    }
                }
            }

            //process input
            process(array, T, R, H);

        }
    }

    private static void process(int[][][] array, int T, int R, int H) {
        int minPrice = Integer.MAX_VALUE;
        int currentTravel = -1, currentRestaurant = -1, currentHotel = -1;
        for(int travel = 0; travel < T; travel++) {
            for (int restaurant = 0; restaurant < R; restaurant++) {
                for (int hotel = 0; hotel < H; hotel++) {
                    if (array[travel][restaurant][hotel] > NOT_APPLICABLE && array[travel][restaurant][hotel] < minPrice) {
                        minPrice = array[travel][restaurant][hotel];
                        currentTravel = travel;
                        currentRestaurant = restaurant;
                        currentHotel = hotel;
                    }
                }
            }
        }
        if(currentTravel > -1 && currentRestaurant > -1 && currentHotel > -1){
            System.out.println(currentTravel + " " + currentRestaurant + " " + currentHotel + ":" + minPrice);
        }else {
            System.out.println("Don't get married!");
        }
    }

}

