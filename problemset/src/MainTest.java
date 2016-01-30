import org.junit.Test;
import sun.reflect.generics.tree.Tree;
import sun.reflect.generics.tree.TypeTree;
import sun.reflect.generics.visitor.TypeTreeVisitor;

import java.util.*;

import static org.junit.Assert.*;

/**
 *
 */
public class MainTest {
    static int count  = 0 ;
    public static void main(String[] args){
//        ListPermutation("abcd");
        ListPermuteSet("abcd");
        System.out.println("count: " + count);
    }

    public static void RecPermute(String soFar, String rest){
        if(rest.equals("")){
            count++;
            System.out.println(soFar );
            return;
        }
        for( int i = 0 ; i < rest.length() ; i ++){
            String next = soFar + rest.charAt(i);
            String remaining = rest.substring(0, i) + rest.substring(i+1);
            RecPermute(next, remaining);
        }
    }

    public static void ListPermutation(String s){
        RecPermute("", s);
    }

    public static void RecPermuteSet(String soFar, String rest){
        if( rest.equals("")){
            count++;
            System.out.println(soFar);
            return;
        }
        RecPermuteSet(soFar + rest.charAt(0), rest.substring(1) );
        RecPermuteSet(soFar , rest.substring(1) );
    }

    public static void ListPermuteSet(String s){
        RecPermuteSet("", s);
    }
}