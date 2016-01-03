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
        int H, U, D, F;
        String output = "";
        while (sc.hasNextInt()){
            H = sc.nextInt();
            if( H == 0 )
                break;
            U = sc.nextInt();
            D = sc.nextInt();
            F = sc.nextInt();
            int count = 0;
            double decrease = 1.0 * (U * F ) /100;
            double currentU = U, HDay = 0, HNight = 0;
            boolean success = false;
            while (true){
                currentU = U - decrease * count ;
                if( currentU <= 0) currentU = 0;
                HDay = HNight + currentU;
                if( HDay > H){
                    success = true;
                    break;
                }
                HNight = HDay - D;
                if(HNight < 0){
                    success= false;
                    break;
                }
                count++;
//                System.out.println(" after day: " + count + " U: " + currentU + " Hday: " + HDay + " Night: " + HNight );
            }
//            System.out.println(" after day: " + count + " U: " + currentU + " Hday: " + HDay + " Night: " + HNight );
            String s = (success ? "success" : "failure") + " on day " + (count+ 1) + "\n";
            output += s;
        }
        System.out.println(output);

    }
}