import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.math.BigInteger;
import java.util.*;

public  class Main
{
    public static void main (String args[])  throws Exception// entry point from OS
    {
        Scanner sc = new Scanner(System.in);
//        Scanner sc = new Scanner(new FileReader("in.txt"));
        int n, m, c;
        int count = 1;
        while (sc.hasNextInt()){
            n = sc.nextInt();
            if( n == 0 )
                break;
            m = sc.nextInt();
            c = sc.nextInt();

            int [] device = new int[n];
            int [] device_on = new int[n];
            int [] sequence = new int[m];

            int sum = 0 , max = 0 ;
            boolean blown = false;

            for( int i = 0 ; i < n; i++){
                device[i] = sc.nextInt();
                device_on[i] = 0;
            }

            for( int i =0 ; i < m; i++){
                sequence[i] = sc.nextInt();
            }

            for( int i = 0 ; i < m; i++){
                device_on[sequence[i] -1 ] =  device_on[sequence[i] -1 ] == 0 ? 1 : 0 ;
                sum = getSum(device, device_on);
                if( sum > max ) max = sum;
                if( sum > c ){
                    blown = true;
                    break;
                }
            }
            System.out.println("Sequence " + count);
            if(blown){
                System.out.println("Fuse was blown.");
            }else {
                System.out.println("Fuse was not blown.");
                System.out.println("Maximal power consumption was " + max + " amperes.");
            }
            System.out.println();
            count++;
        }

    }
    public static int getSum(int [] device, int[] device_on)
    {
        int sum = 0 ;
        for ( int i = 0 ; i < device.length; i++){
            if( device_on[i] == 1)
                sum += device[i];
        }
        return sum;
    }
}