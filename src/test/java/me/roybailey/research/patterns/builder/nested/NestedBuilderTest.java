package me.roybailey.research.patterns.builder.nested;

/**
 * @author roybailey
 */
public class NestedBuilderTest {

    public void testNestedBuilder() {
        Person person = Person.Builder().withName("Anna").withAge(20).build();

    }
}
