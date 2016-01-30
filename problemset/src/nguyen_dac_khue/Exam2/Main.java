package nguyen_dac_khue.Exam2;

import java.io.*;
import java.util.*;

/*
    1) The algorithm used to solve the game problem:
    Step 1:
        - construct the graph of cities using  an Adjacency Matrix
        - use a array to store list of festive cities:
            - if festiveCities[i] == 1, then city i is a festive city

    Step 2: for each query:
        - if qi = 1, update the festiveCities array
        . if qi = 2, find and print the shortest distance from the city ci to the closest festive city:
            -  using Bread First Search algorithm, since each edge is bidirectional and has length of 1.

    2) Time complexity:
        O( V + E) where V is number of vertex and E is number of edges
        since we have V = n with n is number of cites, and E = n -1;
        => Time complexity: O(n)

       Space complexity: O(n ^ 2) where n is number of cities

    3) Advantage and disadvantage of the algorithm:
    - advantage:
           + If there is a solution, BFS will definitely find it out.
           + If there is more than one solution then BFS can find the minimal one that requires less number of steps.

    - disadvantage:
           + The main drawback of Breadth first search is its memory requirement.
                + Since each level of the tree must be saved in order to generate the next level,
                + and the amount of memory is proportional to the number of nodes stored,

          + If the solution is farther away from the root, breath first search will consume lot of time.
 */

public  class Main
{
    static int[][] graph;
    static int[] festiveCities;
    static int numCities, numQuery;
    public static void main(String[] args) {
        try {
            // TODO: Implement your program

            Scanner sc = new Scanner (System.in);
            while (sc.hasNextInt()){
                numCities = sc.nextInt() ;
                numQuery = sc.nextInt() ;

                // construct the graph Adjacency Matrix
                graph = new int[numCities + 1][numCities + 1];
                for( int i = 0 ; i < numCities - 1; i++){
                    int firstCity = sc.nextInt();
                    int secondCity = sc.nextInt();
                    graph[firstCity][secondCity] =1;
                    graph[secondCity][firstCity] =1;
                }

                // construct the festiveCities array
                festiveCities = new int[numCities + 1];
                festiveCities[1] = 1;

                // process each query
                for( int i = 0 ; i < numQuery; i++){
                    int first = sc.nextInt();
                    int second = sc.nextInt();
                    if (first  == 1) {
                        festiveCities[second] = 1;
                    } else {
                        breadFirstSearch(graph, festiveCities, second);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void printCities(int[] festiveCities) {
        System.out.println("Cities: ");
        for (int i = 1 ; i <= numCities; i++){
            System.out.print(festiveCities[i]);
        }
        System.out.println("");
    }

    private static void printGraph(int[][] graph) {
        System.out.println("Graph: ");
        for (int i = 1 ; i <= numCities; i++){
            for( int j = 1 ; j <= numCities; j++){
                System.out.print(graph[i][j]);
            }
            System.out.println("");
        }
    }

    public static  void breadFirstSearch(int [][] graph, int[] festiveCities, int start){
        Queue<Integer> q = new LinkedList<Integer> () ;
        int [] visited = new int[numCities + 1];
        Arrays.fill(visited, -1);
        visited[start] = 0;
        q.offer(start);

        while (!q.isEmpty()) {
            int cur = q.poll() ;
            if (festiveCities[cur] == 1) {
                System.out.println(visited[cur]);
                break;
            }
            for (int n : getUnvisitedNode(graph, cur) ) {
                if (visited[n] == -1) {
                    visited[n] = visited[cur] + 1;
                    q.add(n) ;
                }
            }
        }
    }

    private static Vector<Integer> getUnvisitedNode(int[][] graph, int cur) {
        Vector<Integer> vector = new Vector<Integer>();
        for ( int i = 1; i <= numCities; i++ ){
            if(graph[cur][i] == 1){
                vector.add(i);
            }
        }
        return vector;
    }

    private static void printVector(Vector<Integer> vector) {
        System.out.println("Vector: ");
        for ( int i = 0 ; i < vector.size(); i++){
            System.out.print(vector.get(i));
        }
        System.out.println("");

    }
}