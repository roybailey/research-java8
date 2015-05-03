package me.roybailey.research.fizzbuzz;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * EE Coding Test
 * <p>
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
public class FizzBuzzStep3 {

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
        System.out.println(String.format("Generating FizzBuzz (step 3) between %d and %d", from, upto));
        // stream the output here so it can handle any range without memory issues...
        Map<String, AtomicInteger> report = new HashMap<>();
        new FizzBuzzStep3().generateFizzBuzzStream(from, upto, report).forEach(it -> System.out.print(it + " "));
        System.out.println();
        report.entrySet().stream().forEach(entry -> System.out.println(entry.getKey() + ": " + entry.getValue().get()));
    }

    /**
     * Generates a stream of strings according to FizzBuzz step 3 rules
     *
     * @param from   inclusive from
     * @param upto   inclusive upto
     * @param report map of report counters
     * @return stream of string values
     */
    Stream<String> generateFizzBuzzStream(int from, int upto, final Map<String, AtomicInteger> report) {
        // check arguments
        if (report == null) {
            throw new IllegalArgumentException("Missing report map");
        }
        if (upto < from) {
            throw new IllegalArgumentException("Bad number range");
        }
        // setup report map to ensure zero value entries (and avoid null checking)...
        report.clear();
        report.put("fizz", new AtomicInteger());
        report.put("buzz", new AtomicInteger());
        report.put("fizzbuzz", new AtomicInteger());
        report.put("lucky", new AtomicInteger());
        report.put("integer", new AtomicInteger());

        return IntStream.range(from, upto + 1).<String>mapToObj(value -> {
            String result = String.valueOf(value);
            // check highest value rule first...
            if (result.contains("3")) {
                report.get("lucky").incrementAndGet();
                result = "lucky";
            } else if (value % 15 == 0) {
                report.get("fizzbuzz").incrementAndGet();
                result = "fizzbuzz";
            } else if (value % 5 == 0) {
                report.get("buzz").incrementAndGet();
                result = "buzz";
            } else if (value % 3 == 0) {
                report.get("fizz").incrementAndGet();
                result = "fizz";
            } else {
                report.get("integer").incrementAndGet();
            }
            return result;
        }).sequential();
    }

    /**
     * Generates a string according to FizzBuzz step 1 rules
     *
     * @param from   inclusive from
     * @param upto   inclusive upto
     * @param report
     * @return fizzbuzz string value
     */
    String generateFizzBuzzString(int from, int upto, Map<String, AtomicInteger> report) {
        StringBuilder builder = new StringBuilder();
        // concatenate the stream of fizzbuzz results into single string...
        generateFizzBuzzStream(from, upto, report).forEach(it -> builder.append(it).append(" "));
        return builder.toString();
    }
}
