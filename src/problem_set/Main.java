package problem_set;

import java.io.File;
import java.util.*;

public class Main {

    static final int LARGE_INT = -1000000000;
    static int [][] can_reach, price;
    static int first, k, tempMax = Integer.MIN_VALUE;
    static List<List<Integer>> adList = new ArrayList<List<Integer>>();
    static List<Integer> resultList = new ArrayList<Integer>();
    static List<Integer> tempList = new ArrayList<Integer>();
    static List<Integer> notConnectedList = new ArrayList<Integer>();
    static int[][] track;
    static int min;
    static int previousMin = 0;
    //    static Scanner sc = new Scanner(System.in);

    private static Map<Integer, List<Integer>> map = new HashMap<Integer, List<Integer>>();
    public static void main(String[] args) throws Exception {
//        int [] input = {2,3,6};
//        int [] input = {4,1,2};
        int [] input = {3,3,8};
//        int [] input = {5,2,8};
//        int [] input = {3,5,14};
//        int [] input = {1,16,1};
        calculate(input[0], input[1], input[2]);
        System.out.println(min);
    }

    private static void calculate(int row, int col, int people) {
        min = Integer.MAX_VALUE;
        int[][] visit = new int[row][col];
        track = new int[row][col];
        backTrack(visit, people);
    }

    private static boolean backTrack(int [][] visit, int people) {
        if(people == 0 || full(visit)){
            min = Math.min(min, takeMin(visit));
            return true;
        }
        int[] nextPoint = getNextPoint(visit);
        visit[nextPoint[0]][nextPoint[1]] = 1;
        updateTrack(visit);
        if(backTrack(visit, people -1)){
            return true;
        }
        visit[nextPoint[0]][nextPoint[1]] = 0;

        return false;
    }

    private static void updateTrack(int[][] visit) {
        for(int i = 0; i < visit.length; i++){
            for(int j = 0; j < visit[0].length; j++){
                track[i][j] = getSum(visit, i, j);
            }
        }
    }

    private static int getSum(int[][] visit, int i, int j) {
        int sum = 0;
        int top = i > 0 ? i-1 : 0;
        int bottom = i + 1 < track.length ? i+ 1 : i;
        int left = j > 0 ? j-1 : 0;
        int right = j + 1 < track[0].length ? j + 1 : j;
        for(int row = top; row <= bottom; row++){
            for(int col = left; col <= right; col++){
                if(!(row == i && col == j) && !((Math.abs(row - i) == 1 && Math.abs(col - j) == 1)) && visit[row][col] == 1){
                    sum++;
                }
            }
        }
        return sum;
    }

    private static int[] getNextPoint(int [][]visit) {
        int minCount = Integer.MAX_VALUE;
        int[] result = new int[2];
        for(int i = 0; i < track.length; i++){
            for(int j = 0; j < track[0].length; j++){
                if(visit[i][j] == 0 && track[i][j] <= minCount){
                    minCount = track[i][j];
                    result[0] = i;
                    result[1] = j;
                }
            }
        }
        return result;
    }

    private static boolean full(int[][] visit) {
        for(int i = 0; i < visit.length; i++){
            for(int j = 0; j < visit[0].length; j++){
                if(visit[i][j] == 0){
                   return false;
                }
            }
        }
        return true;
    }

    private static int takeMin(int[][] visit) {
        Set<Point> set = new HashSet<Point>();
        for(int i = 0; i < visit.length; i++){
            for(int j = 0; j < visit[0].length; j++){
                if(visit[i][j] == 1){
                    int top = i > 0 ? i-1 : 0;
                    int bottom = i + 1 < visit.length ? i+ 1 : i;
                    int left = j > 0 ? j-1 : 0;
                    int right = j + 1 < visit[0].length ? j + 1 : j;
                    for(int row = top; row <= bottom; row++){
                        for(int col = left; col <= right; col++){
                            if(!(row == i && col == j) && !((Math.abs(row - i) == 1 && Math.abs(col - j) == 1)) && visit[row][col] == 1){
                                set.add(new Point(i, j, row, col));
                            }
                        }
                    }
                }
            }
        }
        return set.size();
    }

    static class Point {
        public int x1;
        public int y1;
        public int x2;
        public int y2;

        public Point(int x1, int y1, int x2, int y2) {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }

        @Override
        public int hashCode() {
            return this.x1 + this.x2 + this.y1 + this.y2;
        }

        @Override
        public boolean equals(Object obj) {
            if(obj == this) return true;
            if ((obj == null) || (obj.getClass() != this.getClass()))
                return false;
            Point that = (Point) obj;

            return (
                            that.x1 == this.x1 &&
                            that.x2 == this.x2 &&
                            that.y1 == this.y1 &&
                            that.y2 == this.y2
            );
        }
    }
    private static void process(int start, int ttl, int count) {
        int node = getCount(start, ttl);
        System.out.println("Case " + count + ": " + node + " nodes not reachable from node " + start + " with TTL = " + ttl + ".");
    }

    private static int getCount(int start, int ttl) {
        Set<Integer> set = new HashSet<Integer>();
        Queue<Integer> queue = new LinkedList<Integer>();
        for(int i: map.get(start)){
            queue.add(i);
            set.add(i);
        }
        while (!queue.isEmpty()){
            for(int i: map.get(queue.poll())){
                set.add(i);
            }
        }
        return map.keySet().size() - set.size();
    }


}

