
import java.io.*;
import java.util.*;

public class Main {

    public static final int N = -1000001;
    public static void main(String[] args) throws Exception{
        // Use BufferedReader rather than RandomAccessFile; it's much faster
        BufferedReader f = new BufferedReader(new FileReader("test.in"));
        // input file name goes above
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter("test.out")));
        // Use StringTokenizer vs. readLine/split -- lots faster
        int i1 =0, i2 = 0;
        String line;
        try {
            while (( line = f.readLine()) != null){
                StringTokenizer st = new StringTokenizer(line);
                // Get line, break into tokens
                if( st.hasMoreTokens()){
                    i1 = Integer.parseInt(st.nextToken());
                    continue;
                }
                if( st.hasMoreTokens()){
                    i2 = Integer.parseInt(st.nextToken());
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } finally {
            f.close();

        }
        System.out.println("i1: "  + i1 + " i2: " + i2);


        out.println(i1+i2);                           // output result
        out.println(10);
        out.close();
    }
}