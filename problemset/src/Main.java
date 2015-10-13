
import javax.xml.stream.events.Characters;
import java.util.*;

public class Main {

    public static final int N = -1000001;
    public static void main(String[] args) {
        int [] arr = {6, 9, 7 , 12, 15, 19, 14 , 16};
        int [] A = new int[arr.length-1];
        for (int i = 0 ; i < arr.length -1 ; i++){
            A[i] = arr[i+1] - arr[i];
        }
        int max = divide(A, 0, A.length-1 );
        System.out.println("sum: " + max );
    }
    public static int divide(int[] A, int low, int high){
        if( low == high){
            return A[low];
        }else {
            int mid = (int) (low + high)/2;
            int leftSum = divide(A, low, mid);
            int rightSum = divide(A, mid +1, high);
            int crossSum = crossSum(A, low, high);
            if( leftSum >= rightSum && leftSum >= crossSum)
                return leftSum;
            else if ( rightSum >= leftSum && rightSum >= crossSum)
                return rightSum;
            else return crossSum;
        }
    }

    private static int crossSum(int[] A, int low, int high) {
        int leftMax = N, rightMax = N;
        int sum = 0;
        int i, j;
        int mid = (int) (low + high)/2;
        for ( i = mid; i >= low; i-- ){
            sum += A[i];
            if( sum > leftMax){
                leftMax = sum;
            }
        }
        sum = 0;
        for (j = mid+1; j <= high; j++ ){
            sum+= A[j];
            if(sum > rightMax){
                rightMax = sum;
            }
        }
        System.out.println("Cross max: " + (leftMax + rightMax));
        return leftMax + rightMax;
    }

}