package problem_set;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {

    private static final int largeValue = 10000;

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (true) {
            int n = sc.nextInt();
            if (n == 0) {
                break;
            }
            sc.nextLine();
            int[][] young = new int[26][26];
            int[][] old = new int[26][26];
            for (int i = 0; i < 26; i++) {
                for (int j = 0; j < 26; j++) {
                    young[i][j] = largeValue;
                    old[i][j] = largeValue;
                }
            }

            int maxIndex = 0;
            for (int i = 0; i < n; i++) {
                String line = sc.nextLine();
                String[] lines = line.trim().split("[\\s]+");
                boolean isYoung = lines[0].charAt(0) == 'Y';
                boolean isBidirection = lines[1].charAt(0) == 'B';
                int start = lines[2].charAt(0) - 'A';
                int end = lines[3].charAt(0) - 'A';
                int weight = Integer.valueOf(lines[4]);

                if (start > maxIndex) {
                    maxIndex = start;
                }
                if (end > maxIndex) {
                    maxIndex = end;
                }

                if (isYoung) {
                    young[start][end] = weight;
                    if (isBidirection) {
                        young[end][start] = weight;
                    }
                } else {
                    old[start][end] = weight;
                    if (isBidirection) {
                        old[end][start] = weight;
                    }
                }
            }
            String[] location = sc.nextLine().trim().split("[\\s]+");
            int meLocation = location[0].charAt(0) - 'A';
            int profLocation = location[1].charAt(0) - 'A';
            if (meLocation > maxIndex) {
                maxIndex = meLocation;
            }
            if (profLocation > maxIndex) {
                maxIndex = profLocation;
            }

            int minDistance = largeValue;
            List<Integer> meetingPoints = new ArrayList<>();
            if (meLocation == profLocation) {
                minDistance = 0;
                meetingPoints.add(meLocation);
            }

            // floyd warshall
            for (int k = 0; k <= maxIndex; k++) {
                for (int i = 0; i <= maxIndex; i++) {
                    for (int j = 0; j <= maxIndex; j++) {
                        young[i][j] = Math.min(young[i][j], young[i][k] + young[k][j]);
                        old[i][j] = Math.min(old[i][j], old[i][k] + old[k][j]);
                    }
                }
            }

            young[meLocation][meLocation] = 0;
            old[profLocation][profLocation] = 0;

            for (int k = 0; k <= maxIndex; k++) {
                int value = young[meLocation][k] + old[profLocation][k];
                if (value < minDistance) {
                    minDistance = value;
                    meetingPoints.clear();
                    meetingPoints.add(k);

                } else if (value == minDistance && value < largeValue) {
                    meetingPoints.add(k);
                }
            }

            meetingPoints = new ArrayList<>(new HashSet<>(meetingPoints));

            if (meetingPoints.isEmpty()) {
                System.out.println("You will never meet.");
            } else {
                System.out.println(String.format("%d %s", minDistance,
                                meetingPoints.stream().map(i -> String.valueOf((char) ('A' + i)))
                                                .collect(Collectors.joining(" "))));
            }
        }
    }
}

