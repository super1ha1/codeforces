import java.util.Arrays;
import java.util.Scanner;

public class Main  {
    public static void main (String [] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int [] arr = new int[n];
        for ( int i = 0 ; i < n ; i ++){
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        int rel = 0 ;
        int i = 0, j = n-1 ;
        while(i < j ){
            if (arr[i] + arr[j] <= 4){
                arr[j] += arr[i];
                i++;
            }else{
                rel++;
                j--;
            }
        }
        System.out.println(rel + 1);


    }
}
