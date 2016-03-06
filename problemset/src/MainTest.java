

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
            entry.setNext(list);
            list = entry;

        }
        return list;
    }

    public static void printList(Entry list){
        Entry temp = list;
        while (temp != null){
            System.out.println(temp.toString());
            temp = temp.getNext();
        }
    }

    public static Entry getEntry(){

            System.out.println("enter name: ");
            String name = sc.nextLine();
            if(name.equals("")){
                return  null;
            }
            Entry entry = new Entry();
            entry.setName(name);
            entry.setNext(null);
            return entry;

    }

    public static class Entry{
        private String name;
        private Entry next;

        public Entry(){

        }

        public void setName(String name){
            this.name = name;
        }

        public String getName(){
            return this.name;
        }

        //getter and setter for next

        public Entry getNext() {
            return next;
        }

        public void setNext(Entry next) {
            this.next = next;
        }

        public String toString(){
            return "name: " + this.name;
        }
    }

}