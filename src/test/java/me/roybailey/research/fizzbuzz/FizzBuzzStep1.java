package me.roybailey.research.fizzbuzz;

import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * EE Coding Test
 * <p>
 * Step 1:
 * <p>
 * Write some code that prints out the following for a contiguous range of numbers:
 * the number
 * 'fizz' for numbers that are multiples of 3
 * 'buzz' for numbers that are multiples of 5
 * 'fizzbuzz' for numbers that are multiples of 15
 * e.g. if I run the program over a range from 1-20 I should get the following output
 * 1 2 fizz 4 buzz fizz 7 8 fizz buzz 11 fizz 13 14 fizzbuzz 16 17 fizz 19 buzz
 */
public class FizzBuzzStep1 {

    /**
     * FizzBuzz (step 1) program execution.
     *
     * @param args, one integer will be upper limit, two integers will lower and upper range
     */
    public static void main(String[] args) {

        int from = 1, upto = 20;
        try {
            // two arguments provides the range from and upto...
            if (args.length >= 2) {
                from = Integer.parseInt(args[0]);
                upto = Integer.parseInt(args[1]);
            }
            // one argument provides the upper limit...
            if (args.length == 1) {
                upto = Integer.parseInt(args[0]);
            }
        } catch (Exception err) {
            System.err.println("Error parsing inputs which must be valid integer values");
            System.exit(-1);
        }
        System.out.println(String.format("Generating FizzBuzz (step 1) between %d and %d", from, upto));
        // stream the output here so it can handle any range without memory issues...
        new FizzBuzzStep1().generateFizzBuzzStream(from, upto).forEach(it -> System.out.print(it + " "));
    }

    /**
     * Generates a stream of strings according to FizzBuzz step 1 rules
     *
     * @param from inclusive from
     * @param upto inclusive upto
     * @return stream of string values
     */
    Stream<String> generateFizzBuzzStream(int from, int upto) {

        if (upto < from) {
            throw new IllegalArgumentException("Bad number range");
        }
        return IntStream.range(from, upto + 1).<String>mapToObj(value -> {
            String result = String.valueOf(value);
            // check highest value rule first...
            if (value % 15 == 0) {
                result = "fizzbuzz";
            } else if (value % 5 == 0) {
                result = "buzz";
            } else if (value % 3 == 0) {
                result = "fizz";
            }
            return result;
        }).sequential();
    }

    /**
     * Generates a string according to FizzBuzz step 1 rules
     *
     * @param from inclusive from
     * @param upto inclusive upto
     * @return fizzbuzz string value
     */
    String generateFizzBuzzString(int from, int upto) {
        StringBuilder builder = new StringBuilder();
        // concatenate the stream of fizzbuzz results into single string...
        generateFizzBuzzStream(from, upto).forEach(it -> builder.append(it).append(" "));
        return builder.toString();
    }
}
