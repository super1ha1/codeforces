package problem_set;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final int largeValue = 1000000;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        //        Scanner sc = new Scanner(System.in);
        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            sc.nextLine();
            int[][] young = new int[27][27];
            int[][] old = new int[27][27];

            for (int i = 1; i <= 26; i++) {
                for (int j = 1; j <= 26; j++) {
                    young[i][j] = largeValue;
                    old[i][j] = largeValue;
                }
            }

            for (int i = 0; i < n; i++) {
                String[] lines = sc.nextLine().trim().split("[\\s+]");
                int first = lines[2].charAt(0) - 'A' + 1;
                int second = lines[3].charAt(0) - 'A' + 1;
                boolean isYoung = lines[0].equals("Y");
                boolean isBidirectional = lines[1].equals("B");
                int value = Integer.valueOf(lines[4]);

                if (first == second) {
                    continue;
                }
                if (isBidirectional) {
                    if (isYoung) {
                        young[first][second] = value;
                        young[second][first] = value;
                    } else {
                        old[first][second] = value;
                        old[second][first] = value;
                    }
                } else {
                    if (isYoung) {
                        young[first][second] = value;
                    } else {
                        old[first][second] = value;
                    }
                }
            }

            for (int k = 1; k <= 26; k++) {
                for (int i = 1; i <= 26; i++) {
                    for (int j = 1; j <= 26; j++) {
                        // for young
                        young[i][j] = Math.min(young[i][j], young[i][k] + young[k][j]);
                        old[i][j] = Math.min(old[i][j], old[i][k] + old[k][j]);
                    }
                }
            }

            String[] lines = sc.nextLine().trim().split("[\\s+]");
            int meLocation = lines[0].charAt(0) - 'A' + 1;
            int profLocation = lines[1].charAt(0) - 'A' + 1;

            int min = largeValue;
            List<String> meetingPoint = new ArrayList<>();
            if (meLocation == profLocation) {
                min = 0;
                meetingPoint.add(String.valueOf((char) (meLocation - 1 + 'A')));
            }
            for (int k = 1; k <= 26; k++) {
                int currentValue = young[meLocation][k] + old[profLocation][k];
                if (currentValue < min) {
                    min = currentValue;
                    meetingPoint.clear();
                    meetingPoint.add(String.valueOf((char) (k - 1 + 'A')));
                } else if (currentValue == min && currentValue < largeValue) {
                    meetingPoint.add(String.valueOf((char) (k - 1 + 'A')));
                }
            }
            if (min < largeValue) {
                System.out.println(String.format("%d %s", min, meetingPoint.stream().collect(Collectors.joining(" "))));
            } else {
                System.out.println("You will never meet.");
            }
        }
    }
}

