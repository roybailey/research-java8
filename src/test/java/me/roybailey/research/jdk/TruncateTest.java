package me.roybailey.research.jdk;

import org.junit.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;


public class TruncateTest {

    public String truncate(String string, int length) {
        return Stream.of(string.split("\n"))
                .map(s -> s.substring(0, Math.min(s.length(), length)))
                .collect(Collectors.joining("\n"));
    }

    @Test
    public void testJava8Truncate() {
        String actual = truncate("Really big long string", 4);
        System.out.println(actual);
        assertThat(actual, is("Real"));
    }
}
