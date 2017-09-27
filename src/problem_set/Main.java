package problem_set;

import java.util.*;

public class Main {
    private  String first, second;
    private int result;
    private final int[] dx = new int[]{-2, -1,  1,  2,  2, 1, -1, -2};
    private final int[] dy = new int[]{-1, -2, -2, -1,  1, 2,  2,   1};
    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
                Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //         Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        while (sc.hasNext()){
            String line = sc.nextLine().trim();
            String[] array = line.split("[\\s]+");
            first = array[0];
            second = array[1];
            if(first.equals(second)){
                result = 0;
            }else {
                Queue<String> queue = new LinkedList<>();
                queue.add(first);
                Map<String, Integer> distanceMap = new HashMap<>();
                distanceMap.put(first, 0);

                while (!queue.isEmpty()){
                    String current = queue.poll();
                    for(String neighbor: getPossibleMoves(current)) {
                        if(!distanceMap.containsKey(neighbor)){
                            distanceMap.put(neighbor, distanceMap.get(current) + 1);
                            queue.add(neighbor);
                        }
                    }
                }
                result = distanceMap.getOrDefault(second, 0);
            }
            System.out.println(String.format("To get from %s to %s takes %d knight moves.", first, second, result));
        }
    }

    private String[] getPossibleMoves(String current) {
        List<String> list = new ArrayList<>();
        int row = Integer.valueOf(String.valueOf(current.charAt(1))) - 1;
        int col = current.charAt(0) - 'a';

        for(int index = 0; index < dx.length; index++){
            int x = row + dx[index];
            int y = col + dy[index];
            if(0 <= x && x <= 7 && 0 <= y && y <= 7){
                list.add(String.valueOf((char)('a' + y) + String.valueOf(x + 1)));
            }
        }
        return list.toArray(new String[0]);
    }
}

