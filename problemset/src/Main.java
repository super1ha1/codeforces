import java.lang.reflect.Array;
import java.util.*;

public class Main {

    public static void main(String [] args){
        System.out.println("Result: ");
        String str;
        for ( int i = 0 ; i <= 100; i++){
            if( i % 15 == 0)
                str = "FizzBuzz";
            else if(i % 5 == 0)
                str = "Buzz";
            else if ( i % 3 == 0)
                str = "Fizz";
            else
                str = String.valueOf(i);
            System.out.println(str);
        }
    }

}
