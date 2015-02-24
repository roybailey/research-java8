package research.sparkjava;

import static spark.Spark.*;

import spark.*;

public class SparkJavaApp {
    public static void main(String[] args) {

        get("/hello", (request, response) -> {
            return "Hello World!";
        });

    }
}
