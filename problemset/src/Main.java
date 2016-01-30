import java.io.*;
import java.math.BigInteger;
import java.util.*;

public  class Main
{
    static int [][]fnet;
    static int [][] cap;
    public static void main (String args[])  throws Exception// entry point from OS
    {
        int n = 7;

        //Init all = 0
        fnet = new int[n][n];
        cap = new int[n][n];

        // set capacity:
        Scanner sc = new Scanner(new FileReader("in.txt"));
        int first, second,  value;
        for (int i = 0 ; i < 9 ; i++){
            first = sc.nextInt();
            second = sc.nextInt();
            value = sc.nextInt();
            cap[first][second] = value;
        }

        int flow = fordFulkerson(n, 0, 6);
        System.out.println("Flow; " + flow);

    }

    public static int fordFulkerson(int n, int s, int t) {
        //ASSUMES: cap[u][v] stores capacity of edge (u,v). cap[u][v] = 0 for no edge.
        //Initialise the flow network so that fnet[u][v] = 0 for all u,v
        int flow = 0; //no flow yet

        while (true) {
            //Find an augmenting path using BFS
            int[] prev = new int[n];
            Arrays.fill(prev, -1);
            LinkedList<Integer> queue = new LinkedList<Integer>();
            prev[s] = -2;
            queue.add(s);
            while (!queue.isEmpty() && prev[t] == -1) {
                int u = queue.poll();
                for (int v = 0; v < n; v++) {
                    if (prev[v] == -1) { //not seen yet
                        if (fnet[v][u] > 0 || fnet[u][v] < cap[u][v]) {
                            prev[v] = u;
                            queue.add(v);
                        }
                    }
                }
            }
            //See if we couldn't find any path to t (t has no parents)
            if (prev[t] == -1) break;

            //Get the bottleneck capacity;
            int bot = Integer.MAX_VALUE;
            for (int v = t, u = prev[v]; u >= 0; v = u, u = prev[v]) {
                if (fnet[v][u] > 0)  //prefer a backward edge over a forward
                    bot = Math.min(bot, fnet[v][u]);
                else //must be a forward edge otherwise
                    bot = Math.min(bot, cap[u][v] - fnet[u][v]);
            }

            //update the flow network
            for (int v = t, u = prev[v]; u >= 0; v = u, u = prev[v]) {
                if (fnet[v][u] > 0) //backward edge -> subtract
                    fnet[v][u] -= bot;
                else //forward edge -> add
                    fnet[u][v] += bot;
            }

            //Sent 'bot' amount of flow from s to v, so update the flow
            flow += bot;
        }
        return flow;
    }
}