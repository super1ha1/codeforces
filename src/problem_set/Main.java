package problem_set;

import java.util.*;
import java.util.stream.Collectors;

public class Main {

    private int m, low;
    private List<Edge> resultList = new ArrayList<>();
    private List<Edge> list = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        Main main = new Main();
        main.run(args);
    }

    private void run(String[] args) throws Exception {
        Scanner sc = new Scanner(System.in);
//                        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
        //                 Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int testCase = sc.nextInt();
        for (int i = 0; i < testCase; i++) {
            m = sc.nextInt();
            resultList.clear();
            list.clear();

            while (true) {
                int start = sc.nextInt();
                int end = sc.nextInt();
                if (start == 0 && end == 0) {
                    break;
                }
                if(end <= 0 || start >= m){

                } else {
                    list.add(new Edge(start, end));
                }
            }

            low = 0;

            while (true){
                List<Edge> updatedList = list.stream().filter(e -> e.start <= low && e.end > low).collect(Collectors.toList());
                if(updatedList.size() == 0){
                    break;
                }
                Collections.sort(updatedList, (o1, o2) -> o2.end - o1.end);
                Edge selectedEdge = updatedList.get(0);
                resultList.add(selectedEdge);
                low = selectedEdge.end;
            }

            if(low >= m) {
                Collections.sort(resultList, Comparator.comparingInt(o -> o.start));
                System.out.println(resultList.size());
                for(Edge e: resultList){
                    System.out.println(e.start + " " + e.end);
                }
            } else {
                System.out.println(0);
            }
            if (i < testCase - 1) {
                System.out.println("");
            }
        }
    }

    static class Edge {
        public final int start, end;

        public Edge(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
}

