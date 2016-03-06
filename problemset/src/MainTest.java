

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class MainTest {

    public static void main(String[] args){

        Scanner sc = new Scanner(System.in);
        listPermute("abc");
    }

    public static void recPermute( String soFar, String rest){
        if( rest.equals("")){
            System.out.println(soFar);
        }else {
            for( int i = 0 ; i < rest.length(); i++){
                String next = soFar + rest.charAt(i);
                String remain = rest.substring(0, i) + rest.substring(i+1);
                recPermute(next, remain);
            }
        }
    }

    public static void listPermute(String s){
        recPermute("", s);
    }


}