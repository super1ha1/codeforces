

import java.util.*;


public class MainTest {
    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);

        System.out.println("Enter the string: ");
        String str = sc.nextLine();

        System.out.println("Enter the key: ");
        int key = sc.nextInt();

        String encrypt = encryptString(str, key);
        System.out.println("Encrypted string: " + encrypt);
        System.out.println("Ori string: " + encryptString(encrypt, key * -1));
    }

    private static String encryptString(String str, int key) {
        if(key < 0){
            key = 26 - (-key %26);
        }

        String result = "";
        for( int i = 0 ; i < str.length(); i++){
            char c = str.charAt(i);
            result += encryptChar(c, key);
        }
        return result;

    }

    private static String encryptChar(char c, int key) {
        if( !Character.isLetter(c))
            return "";

        if(Character.isUpperCase(c)){
            char newChar = (char) ( 'A' +  ((c  -'A' + key) % 26 )) ;
            return String.valueOf(newChar);
        }

        if(Character.isLowerCase(c)){
            char newChar = (char) ( 'a' + ((c -'a' + key) % 26));
            return String.valueOf(newChar);
        }
        return "";
    }

    private static int findMatch(int n) {
        int matches = 0 ;
        while (n > 1){
            int k = n /2;
            int left = n % 2;
            matches += k ;
            n = left + k;
        }
        return matches;
    }

    private static char toLower(char ch){

        String s = "Aafasdfds";
        s.toUpperCase();
        System.out.println(" s upper: " + s.toUpperCase());

        System.out.println(" s: " + s);
        if( ch >= 'A' && ch <= 'Z'){
            return (char)((ch - 'A' + 'a'));
        }
        return ch;
    }


}