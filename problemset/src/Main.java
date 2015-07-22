
import javax.xml.stream.events.Characters;
import java.util.*;

public class Main {

    public static final int N = 1000001;
    public static void main(String[] args) {
        Scanner sc = new Scanner( System.in);

        int a = sc.nextInt();
        int b = sc.nextInt();
        int rel = a;
        while( a >= b){
            a = a/b + a%b;
            rel += a;
        }
        System.out.print(rel);

    }




}