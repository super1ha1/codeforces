
import javax.xml.stream.events.Characters;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main {

    public static final int N = 1000001;
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);
        int [] arr = new int[N];
        arr[2] = 1 ;
        for ( int i = 2 ; i < N; i++){
            if( arr[i] == 0){
                arr[i] = 1;
            }
            for( int j = i+i; j < N ; j += i ){
                arr[j] = -1;
            }
        }

        int n = sc.nextInt();
        for (int i = 2 ; i < N; i++){
            if( arr[i] == -1 && arr[n- i] == -1){
                System.out.println(i + " " + (n-i) );
                break;
            }
        }


    }




}