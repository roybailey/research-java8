package me.roybailey.research.jdk;

import org.junit.Test;

import java.util.HashMap;
import java.util.IdentityHashMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by roybailey on 08/06/2015.
 */
public class IdentityHashMapTest {

    /**
     * Test to show difference between HashMap and IdentityHashMap in Java
     */
    @Test
    public void testIdenitityHashMap() {
        //
        // IdentityHashMap uses == and not equals() hashCode() methods
        //
        HashMap<String,String> regularMap = new HashMap<>();
        IdentityHashMap<String, String> identityMap = new IdentityHashMap<>();

        identityMap.put("sony", "bravia");
        regularMap.put("sony", "bravia");
        identityMap.put(new String("sony"), "mobile");
        regularMap.put(new String("sony"), "mobile");

        //size of identityMap should be 2 here because two strings are different objects
        System.out.println("IdentityHashMap: " + identityMap);
        System.out.println("HashMap: " + regularMap);
        assertThat(identityMap.size(), is(2));
        assertThat(regularMap.size(), is(1));

        identityMap.put("sony", "videogame");

        // size of identityMap still should be 2 because "sony" and "sony" is same object
        System.out.println("IdentityHashMap: " + identityMap);
        assertThat(identityMap.size(), is(2));

    }
}
