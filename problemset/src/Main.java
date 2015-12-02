import javax.xml.soap.Text;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

public class Main {

    public static void main(String [] args) throws  Exception{
        Scanner sc = new Scanner(new FileReader("in.txt"));
        StringBuilder sb = new StringBuilder();
        while (sc.hasNextInt()){
            int a = sc.nextInt();
            int b = sc.nextInt();
            int i = a < b ? a : b;
            int j = a >= b ? a : b;
            int max = 0 , current;
            for ( int k = i; k <= j; k++){
                current = check(k);
                if (current > max)
                    max = current;
            }
//            System.out.println(i + " " + j + " " + max);
            sb.append(i + " " + j + " " + max + System.lineSeparator());
        }
        writeOutput(sb);
        System.out.println("compare: " + checkOutput());

    }
    public static int check(int n){
        int count = 1;
        while (n != 1){
            if( n %2 ==0 )
                n = n/2;
            else n = 3 * n + 1;
            count ++;
        }
        return count;
    }
    public static int checkOutput(){
        String expected = null, output = null, line;
        BufferedReader reader = null;
        Charset charset = Charset.forName("US-ASCII");
        StringBuilder sb = new StringBuilder();
        Path expectedFile = Paths.get("expected.txt");
        Path outputFile = Paths.get("out.txt");
        try{
            reader = Files.newBufferedReader(expectedFile, charset);
            while ( (line = reader.readLine()) != null){
                sb.append(line + System.lineSeparator());
            }
            expected = sb.toString();
//            System.out.println("Expected: " + expected);
            sb = new StringBuilder();
            reader = Files.newBufferedReader(outputFile, charset);
            while ( (line = reader.readLine()) != null){
                sb.append(line + System.lineSeparator());
            }
            output = sb.toString();
//            System.out.println("Output: " + output);

        }catch (IOException x){
            System.err.format("IO Exception %s%n", x);
        }
        return output.compareTo(expected);

    }
    public static void writeOutput(StringBuilder sb){
        Charset charset = Charset.forName("US-ASCII");
        Path outputFile = Paths.get("out.txt");
        String output = sb.toString();
        try {
            BufferedWriter bufferedWriter = Files.newBufferedWriter(outputFile, charset);
            bufferedWriter.write(output, 0, output.length());
            bufferedWriter.close();
        } catch (IOException x) {
            System.err.format("IOException: %s%n", x);
        }
    }
}
