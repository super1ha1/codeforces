
import java.io.*;
import java.util.*;

public class Main {
    public static final int N = 100;

    public static void main(String[] args) throws Exception{
        String s = "ATTGAGA";
//        Set symbol = new HashSet<Character>();
//        symbol.add('A');
//        symbol.add('T');
//        symbol.add('G');
//        Set A =new HashSet<Integer>();
//        Set T =new HashSet<Integer>();
//        Set G =new HashSet<Integer>();
//        SIP(s, symbol, A, T, G);
//        printSet(A);
//        printSet(T);
//        printSet(G);
        SIP(s);
    }
    public static void SIP(String s, Set symbol, Set A, Set T, Set G){
        char [] chars = s.toCharArray();
        for( int i = 0 ; i < chars.length; i ++){
           switch (chars[i]){
               case 'A':
                   A.add(i+1);
                   break;
               case 'T':
                   T.add(i+1);
                   break;
               case 'G':
                   G.add(i+1);
                   break;
               default:
                   break;
           }}}
    public static void printSet(Set<Integer> set){
        for(Integer i: set){
            System.out.print(i + " ");
        }
        System.out.print("\n");
    }
    public static void SIP(String s){
        char [] chars = s.toCharArray();
        Set symbol= new HashSet<Character>();
        HashMap<Character, Set> map = new HashMap<Character, Set>();
        for( int i = 0 ; i < chars.length; i ++){
            if(!symbol.contains(chars[i])){
                symbol.add(chars[i]);
                Set newSet = new HashSet<Integer>();
                newSet.add(i+1);
                map.put(chars[i], newSet);
            }else {
                Set existSet = map.get(chars[i]);
                existSet.add(i+1);
            }
        }
        for(Character c : map.keySet()){
            printSet(map.get(c));
        }
    }
}