package me.roybailey.research.fizzbuzz;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Tests for FizzBuzz coding exercise
 */
public class FizzBuzzTest {

    /**
     * Step 1:
     * <p>
     * Write some code that prints out the following for a contiguous range of numbers:
     * the number
     * 'fizz' for numbers that are multiples of 3
     * 'buzz' for numbers that are multiples of 5
     * 'fizzbuzz' for numbers that are multiples of 15
     * e.g. if I run the program over a range from 1-20 I should get the following output
     * 1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz
     * </p>
     */
    @Test
    public void testFizzBuzzStep1() {
        String expected = "1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz";
        String actual = new FizzBuzzStep1().generateFizzBuzzString(1, 20);
        assertNotNull(actual);
        assertThat(actual.trim(), is(expected));
    }

    /**
     * Step 2:
     * <p>
     * Enhance your existing FizzBuzz solution to perform the following:
     * If the number contains a three you must output the text 'lucky'. This overrides any existing behaviour
     * e.g. if I run the program over a range from 1-20 I should get the following output
     * 1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz
     * </p>
     */
    @Test
    public void testFizzBuzzStep2() {
        String expected = "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz";
        String actual = new FizzBuzzStep2().generateFizzBuzzString(1, 20);
        assertNotNull(actual);
        assertThat(actual.trim(), is(expected));
    }

    /**
     * Step 3:
     * Enhance your existing solution to perform the following:
     * <p>
     * Produce a report at the end of the program showing how many times the following were output
     * <ul>
     * <li>fizz</li>
     * <li>buzz</li>
     * <li>fizzbuzz</li>
     * <li>lucky</li>
     * <li>an integer</li>
     * </ul>
     * <p>
     * e.g. if I run the program over a range from 1-20 I should get the following output
     * 1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz
     * fizz: 4
     * buzz: 3
     * fizzbuzz: 1
     * lucky: 2
     * integer: 10
     */
    @Test
    public void testFizzBuzzStep3() {
        Map<String, AtomicInteger> report = new HashMap<>();
        String expected = "1 2 lucky 4 buzz fizz 7 8 fizz buzz 11 fizz lucky 14 fizzbuzz 16 17 fizz 19 buzz";
        String actual = new FizzBuzzStep3().generateFizzBuzzString(1, 20, report);
        assertNotNull(actual);
        assertThat(actual.trim(), is(expected));
        assertThat(report.get("fizz").get(), is(4));
        assertThat(report.get("buzz").get(), is(3));
        assertThat(report.get("fizzbuzz").get(), is(1));
        assertThat(report.get("lucky").get(), is(2));
        assertThat(report.get("integer").get(), is(10));
    }
}
