import java.io.IOException;
import java.util.*;

class Main
{
    public static void main (String args[])  // entry point from OS
    {
        Scanner sc = new Scanner(System.in);
        String line, temp;
        char [] chars;
        StringBuilder sb;

        boolean first = false;
        while (sc.hasNext()){
            line = sc.nextLine();
            chars = line.toCharArray();
            sb = new StringBuilder();
            for (int i = 0 ; i < chars.length; i++){
                if( chars[i] == '"'){
                    first = !first;
                    temp = first ? "``" : "''";
                    sb.append(temp);
                }else {
                    sb.append(chars[i]);
                }
            }
            System.out.println(sb.toString());
        }
    }
}