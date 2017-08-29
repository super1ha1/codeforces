package problem_set;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {

        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new File("C:\\toolbar_local\\workspace\\Testing\\codeforces\\in.txt"));
//        Scanner sc = new Scanner(new File("/Users/dackhue.nguyen/toolbar_local/workspace/codeforces/in.txt"));

        int counter = 0;
        while (sc.hasNext()){
            int Z = sc.nextInt();
            int I = sc.nextInt();
            int M = sc.nextInt();
            int L = sc.nextInt();
            if(Z == 0 && I == 0 && M == 0 && L == 0){
                break;
            }
            System.out.println(String.format("Case %d: %d", ++counter, calculate(Z, I, M, L)));
        }
    }

    private static int calculate(int z, int i, int m, int length) {
        int tortoise, bare;
        tortoise = function(z, i, m, length);
        bare = function(z, i, m, function(z, i, m, length));
        while (tortoise != bare){
            tortoise = function(z, i, m, tortoise);
            bare = function(z, i, m, function(z, i, m, bare));
        }

        int u = 0;
        bare = length;
        while (bare != tortoise){
            bare = function(z, i, m, bare);
            tortoise = function(z, i, m, tortoise);
            u++;
        }

        int mu = 1;
        tortoise = function(z, i, m , bare);
        while (tortoise != bare){
            tortoise = function(z, i, m, tortoise);
            mu++;
        }

        return mu;
    }

    private static int function(int z, int i, int m, int l){
        return (z * l + i) % m;
    }
}

