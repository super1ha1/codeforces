

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;


public class MainTest {

    private static  Scanner sc = new Scanner(System.in);

    public static void main(String[] args){
        Entry list = getListEntry();
        printList(list);


    }

    public static Entry getListEntry(){
        Entry list = null;
        while (true){
            Entry entry = getEntry();
            if(entry == null) break;
            entry.next = list;
            list = entry;

        }
        return list;
    }

    public static void printList(Entry list){
        Entry temp = list;
        while (temp != null){
            System.out.println(temp.toString());
            temp = temp.next;
        }
    }

    public static Entry getEntry(){

            System.out.println("enter name: ");
            String name = sc.nextLine();
            if(name.equals("")){
                return  null;
            }
            Entry entry = new Entry();
            entry.name = name;
            entry.next = null;
            return entry;

    }

    public static class Entry{
         String name;
         Entry next;

        public Entry(){

        }

        public String toString(){
            return "name: " + name;
        }
    }

}