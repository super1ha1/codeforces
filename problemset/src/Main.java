import java.io.FileReader;
import java.util.*;

class Main
{
    public static void main (String args[])  throws Exception// entry point from OS
    {
        Scanner sc = new Scanner(System.in);
        int N, R;
        String line, updateLine;
        String [] ar;
        String name, indexString;
        int base, Cd, D, count, index;
        Element element;
        Map<String, Element> map = new HashMap<String, Element>();
        int[][] array;
        int [] addressIndex;
        N = sc.nextInt();
        R = sc.nextInt();
        line = sc.nextLine();
        while (N-- > 0){
            line = sc.nextLine();
            line = line.trim().replaceAll("( )+", " ");
            ar = line.split(" ");

            name = ar[0];
            base = Integer.parseInt(ar[1]);
            Cd = Integer.parseInt(ar[2]);
            D = Integer.parseInt(ar[3]);
            array = new int[D][2];
            for( count = 4, index = 0; index<D; index++){
                array[index][0] = Integer.parseInt(ar[count++]);
                array[index][1] = Integer.parseInt(ar[count++]);
            }
            element = new Element(name, base, Cd, D, array);
            map.put(name, element);
        }
        while (R -- > 0){
            line = sc.nextLine();
            line = line.trim().replaceAll("( )+", " ");
            ar = line.split(" ");

            name = ar[0];
            indexString ="[";
            addressIndex = new int[ar.length -1];
            for( count = 1, index = 0; index<ar.length -1 ; index++){
                addressIndex[index] = Integer.parseInt(ar[count++]);
                indexString += String.valueOf(addressIndex[index]);
                if(index != ar.length -2 ) indexString += ", ";
            }
            indexString += "] = ";
            Element ele = map.get(name);
            if(ele != null)
                System.out.println( name + indexString + ele.address(addressIndex));
        }
    }

     static void print(Element element) {
        System.out.println("name: " + element.name);
        for ( int i = 0 ; i < element.D; i++){
            System.out.println("array: " + element.UpperLower[i][0] + " " + element.UpperLower[i][1]);
        }
    }

     static class Element{
        String name;
        int base;
        int Cd;
        int D;
        int[][] UpperLower;
        int[] CArray;
        int C0;
         Element(String name, int base, int Cd, int D, int[][] array){
            this.name = name;
            this.base = base;
            this.Cd = Cd;
            this.D = D;
            this.UpperLower = array;
            this.CArray = new int[D + 1];
            CArray[D] = Cd;
            int sum = Cd * UpperLower[D-1][0];
            for( int i = D-1; i >= 1; i--){
                CArray[i] = CArray[i+1] * (UpperLower[i][1] - UpperLower[i][0] + 1);
                sum += CArray[i] * UpperLower[i-1][0];
            }
            C0 = base - sum;
        }
         int address(int [] array){
            int sum = 0 ;
            for ( int i = 0 ; i < array.length; i++){
                sum += array[i] * CArray[i+1];
            }
            return C0 + sum;
        }
    }
}