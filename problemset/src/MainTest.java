

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class MainTest {

    public static void main(String[] args){

        BufferedReader reader = readFile("doc.txt");
        Map<String, Set<String>> words = new HashMap<>();

        try {
            while (true){
                String line = reader.readLine();
                if( line == null) break;
                line.trim().replaceAll("( )+", " ");
                for ( String word: line.split(" ")){
                    String key = sortString(word);
                    if(words.keySet().contains(key)){
                        words.get(key).add(word);
                    }else {
                        Set<String> newSet = new HashSet<>();
                        newSet.add(word);
                        words.put(key, newSet);
                    }
                }
            }
            System.out.println("total size: " + words.size());
            String maxKey = "";
            int maxSize = 0;
            for( String key: words.keySet()){
                System.out.print("key: " + key);
                for( String s: words.get(key)){
                    System.out.print(" " + s);
                }
                if(words.get(key).size() > maxSize){
                    maxKey = key;
                    maxSize = words.get(key).size();
                }
                System.out.println();
            }
            System.out.println("max: " + maxKey + " size: " + maxSize);

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public static String sortString( String s){
        char [] array = removeNonChar(s);

        for( int i = 0 ; i < array.length; i++){
            int minIndex = i;
            for( int j = i+1 ; j < array.length; j++){
                if(array[j] < array[minIndex])
                    minIndex = j;
            }
            swap(array, i, minIndex);
        }
        return new String(array);
    }

    public static char [] removeNonChar(String s){
        char [] array = s.toLowerCase().toCharArray();
        String rel = "";
        for( int i = 0 ; i < array.length; i++){
            if( Character.isLetter(array[i])){
                if('a' <= array[i] && array[i] <= 'z') {
                    rel += array[i];
                }
            }
        }
        return rel.toCharArray();
    }

    public static void swap(char [] array, int i, int j ){
        char temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    public static BufferedReader readFile (String name){
        BufferedReader reader = null;

        try {
            reader = new BufferedReader(new FileReader((name)));
        }catch (Exception e){
            e.printStackTrace();
        }
        return reader;
    }



}