package me.roybailey.research.lambda;

import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Test memorizing functions (or caching function results)
 */
public class FunctionMemorization {

    Map<String, Long> pizzaDao;

    Function<String, Long> getPizzaIdentifierFromName = name -> {
        long pizzaId = -1;
        pizzaId = pizzaDao.get(name);
        System.out.println("Select ID from PIZZA where name = '" + name + "' = "+pizzaId);
        try {
            Thread.sleep(100);
        } catch (InterruptedException ignore) {
            ignore.printStackTrace();
        }
        return pizzaId;
    };

    @Before
    public void setupPizzaDao() {
        this.pizzaDao = new ConcurrentHashMap<>();
        pizzaDao.put("Cheese",1L);
        pizzaDao.put("Pepperoni",2L);
        pizzaDao.put("Farmhouse",3L);
        pizzaDao.put("Spicy",4L);
    }

    @Test
    public void testPizzaFunctionWithoutCaching() {
        System.out.println("Without Caching");
        assertThat(getPizzaIdentifierFromName.apply("Cheese"), is(1L));
        assertThat(getPizzaIdentifierFromName.apply("Cheese"), is(1L));
        assertThat(getPizzaIdentifierFromName.apply("Cheese"), is(1L));
        assertThat(getPizzaIdentifierFromName.apply("Pepperoni"), is(2L));
        assertThat(getPizzaIdentifierFromName.apply("Pepperoni"), is(2L));
        assertThat(getPizzaIdentifierFromName.apply("Pepperoni"), is(2L));
        assertThat(getPizzaIdentifierFromName.apply("Farmhouse"), is(3L));
        assertThat(getPizzaIdentifierFromName.apply("Farmhouse"), is(3L));
        assertThat(getPizzaIdentifierFromName.apply("Farmhouse"), is(3L));
        assertThat(getPizzaIdentifierFromName.apply("Spicy"), is(4L));
        assertThat(getPizzaIdentifierFromName.apply("Spicy"), is(4L));
        assertThat(getPizzaIdentifierFromName.apply("Spicy"), is(4L));
    }

    public static <X, Y> Function<X, Y> memorize(Function<X, Y> fn) {
        Map<X, Y> pp = new ConcurrentHashMap<X, Y>();
        return (a) -> pp.computeIfAbsent(a, fn);
    }

    @Test
    public void testPizzaFunctionWithCaching() {
        System.out.println("With Caching");
        Function<String, Long> cachePizzaID = memorize(getPizzaIdentifierFromName);
        assertThat(cachePizzaID.apply("Cheese"), is(1L));
        assertThat(cachePizzaID.apply("Cheese"), is(1L));
        assertThat(cachePizzaID.apply("Cheese"), is(1L));
        assertThat(cachePizzaID.apply("Pepperoni"), is(2L));
        assertThat(cachePizzaID.apply("Pepperoni"), is(2L));
        assertThat(cachePizzaID.apply("Pepperoni"), is(2L));
        assertThat(cachePizzaID.apply("Farmhouse"), is(3L));
        assertThat(cachePizzaID.apply("Farmhouse"), is(3L));
        assertThat(cachePizzaID.apply("Farmhouse"), is(3L));
        assertThat(cachePizzaID.apply("Spicy"), is(4L));
    }
}
