package me.roybailey.research.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public class IfElseRemoval {

    // ------------------------------------------------------------
    // Java7 code using if/else to handle null, empty, and 1 or many items
    // ------------------------------------------------------------

    public String buildExpressionJava7(List<Integer> list1, List<Integer> list2) {

        String expression = "";

        String list1Expression = buildList1ExpressionJava7(list1);
        String list2Expression = buildList2ExpressionJava7(list2);

        if (list1Expression == null && list2Expression != null) {
            expression = list2Expression;
        }

        if (list1Expression != null && list2Expression == null) {
            expression = list1Expression;
        }

        if (list1Expression != null && list2Expression != null) {
            expression = list1Expression + " and " + list2Expression;
        }

        return expression;

    }

    private String buildList1ExpressionJava7(List<Integer> list1) {
        String expression = null;
        if (list1 != null && !list1.isEmpty()) {
            expression = "(";
            for (int i = 0; i < list1.size(); i++) {
                if (i != list1.size() - 1) {
                    expression = expression + "list1:" + list1.get(i) + " or ";
                } else {
                    expression = expression + "list1:" + list1.get(i);
                }
            }
            expression = expression + ")";
        }
        return expression;
    }

    private String buildList2ExpressionJava7(List<Integer> list2) {
        String expression = null;
        if (list2 != null && !list2.isEmpty()) {
            expression = "!(";
            for (int i = 0; i < list2.size(); i++) {
                if (i != list2.size() - 1) {
                    expression = expression + "list2:" + list2.get(i) + " or ";
                } else {
                    expression = expression + "list2:" + list2.get(i);
                }
            }
            expression = expression + ")";
        }
        return expression;
    }

    // ------------------------------------------------------------
    // Java8 code removal of if/else
    // ------------------------------------------------------------

    public String buildExpressionJava8(List<Integer> list1, List<Integer> list2) {

        return Stream.of(buildList1Expression(list1), buildList2Expression(list2)).filter(op -> op.isPresent()).map(op -> op.get())
                .reduce((result, s) -> result + " and " + s).orElse("");

    }

    private Optional<String> buildList1Expression(List<Integer> list1) {
        return Optional.ofNullable(list1).flatMap(
                list -> list.stream().map(i -> "list1:" + i).reduce((result, s) -> result + " or " + s).map(s -> "(" + s + ")"));

    }

    private Optional<String> buildList2Expression(List<Integer> list2) {
        return Optional.ofNullable(list2).flatMap(
                list -> list.stream().map(i -> "list2:" + i).reduce((result, s) -> result + " or " + s).map(s -> "!(" + s + ")"));
    }

    // ------------------------------------------------------------
    // Tests the two versions
    // ------------------------------------------------------------

    @Test
    public void testIfElseRemovalJava7() {
        String both = buildExpressionJava7(Arrays.asList(10, 20, 30), Arrays.asList(40, 50, 60, 70));
        System.out.println("Java7 Both: "+both);
        String secondEmpty = buildExpressionJava7(Arrays.asList(10, 20, 30), Collections.<Integer>emptyList());
        System.out.println("Java7 Empty Second: "+secondEmpty);
        String firstEmpty = buildExpressionJava7(Collections.<Integer>emptyList(), Arrays.asList(40, 50, 60, 70));
        System.out.println("Java7 Empty First: "+firstEmpty);
        String secondNull = buildExpressionJava7(Arrays.asList(10, 20, 30), null);
        System.out.println("Java7 Second Null: "+secondNull);
        String firstNull = buildExpressionJava7(null, Arrays.asList(40, 50, 60, 70));
        System.out.println("Java7 First Null: "+firstNull);
        String bothOnlyOne = buildExpressionJava7(Arrays.asList(10), Arrays.asList(40));
        System.out.println("Java7 Both Only One: "+bothOnlyOne);
    }

    @Test
    public void testIfElseRemovalJava8() {
        String both = buildExpressionJava8(Arrays.asList(10, 20, 30), Arrays.asList(40, 50, 60, 70));
        System.out.println("Java8 Both: "+both);
        String secondEmpty = buildExpressionJava8(Arrays.asList(10, 20, 30), Collections.<Integer>emptyList());
        System.out.println("Java8 Empty Second: "+secondEmpty);
        String firstEmpty = buildExpressionJava8(Collections.<Integer>emptyList(), Arrays.asList(40, 50, 60, 70));
        System.out.println("Java8 Empty First: "+firstEmpty);
        String secondNull = buildExpressionJava8(Arrays.asList(10, 20, 30), null);
        System.out.println("Java8 Second Null: "+secondNull);
        String firstNull = buildExpressionJava8(null, Arrays.asList(40, 50, 60, 70));
        System.out.println("Java8 First Null: "+firstNull);
        String bothOnlyOne = buildExpressionJava8(Arrays.asList(10), Arrays.asList(40));
        System.out.println("Java8 Both Only One: "+bothOnlyOne);
    }
}

