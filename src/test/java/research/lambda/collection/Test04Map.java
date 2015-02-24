package research.lambda.collection;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

/**
 * @author MikeW
 */
public class Test04Map {

    public static void main(String[] args) {
        List<Person> pl = Person.createShortList();

        SearchCriteria search = SearchCriteria.getInstance();

        // Calc average age of pilots old style
        System.out.println("== Calc Old Style ==");
        int sum = 0;
        int count = 0;

        for (Person p : pl) {
            if (p.getAge() >= 23 && p.getAge() <= 65) {
                sum = sum + p.getAge();
                count++;
            }
        }

        long average = sum / count;
        System.out.println("Total Ages: " + sum);
        System.out.println("Average Age: " + average);


        // Get sum of ages
        System.out.println("\n== Calc New Style ==");
        long totalAge = pl
                .stream()
                .filter(search.getCriteria("allPilots"))
                .mapToInt(p -> p.getAge())
                .sum();

        // Get average of ages
        OptionalDouble averageAge = pl
                .parallelStream()
                .filter(search.getCriteria("allPilots"))
                .mapToDouble(p -> p.getAge())
                .average();

        System.out.println("Total Ages: " + totalAge);
        System.out.println("Average Age: " + averageAge.getAsDouble());

        System.out.println("\n== Compute ==");
        // We'll be using this simple map
        // Unfortunately, still no map literals in Java 8..
        Map<String, Integer> map = new HashMap<>();
        map.put("A", 1);
        map.put("B", 2);
        map.put("C", 3);
        // Compute a new value for the existing key
        System.out.println(map.compute("A", (k, v) -> v == null ? 42 : v + 41));
        System.out.println(map);
        // This will add a new (key, value) pair
        System.out.println(map.compute("X", (k, v) -> v == null ? 42 : v + 41));
        System.out.println(map);

        System.out.println("\n== Merge ==");
        // We'll be using this simple map
        // Unfortunately, still no map literals in Java 8..
        Map<String, String> textMap = new HashMap<>();
        textMap.put("A", "AAA");
        textMap.put("B", "BBB");
        textMap.put("C", "CCC");
        System.out.println(textMap);
        // merge takes a key and value and function to operate on the incoming value is exists
        textMap.merge("A", "-MERGED", String::concat);
        System.out.println(textMap);
        // above broken into code steps
        String key = "B";
        String msg = "-MERGED";
        String value = textMap.get(key);
        if(value == null)
            textMap.put(key, msg);
        else
            textMap.put(key, value.concat(msg));
        System.out.println(textMap);

        System.out.println("B="+textMap.getOrDefault("B","NOTFOUND"));
        System.out.println("X="+textMap.getOrDefault("X","NOTFOUND"));
    }

}
