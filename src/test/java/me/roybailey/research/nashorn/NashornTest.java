package me.roybailey.research.nashorn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import me.roybailey.research.lambda.LambdaTest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.script.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.*;

/**
 * Set of Nashorn tests to show interoperability of Java8 and Nashorn Javascript.
 */
public class NashornTest {

    private static void LOG(Object message) {
        System.out.println("JAVA: " + message);
    }

    public static String nashornWillCallThis(String name) {
        LOG("Called from javascript to print in Java:  " + name);
        return "returned from java to print from javascript";
    }

    public static void nashornTypesInJava(Object object) {
        LOG("javascript called java with object type: " + object.getClass());
    }

    String JAVASCRIPT_TEST_PARTNER = "src/test/java/"
            + this.getClass().getPackage().getName().replace(".", File.separator)
            + "/" + this.getClass().getSimpleName() + ".js";
    ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
    Invocable invocable = (Invocable) engine;

    @After
    public void finish() throws InterruptedException {
        // allow console logging to finish
        Thread.sleep(100);
    }

    @Test
    public void testNashorn() throws ScriptException {
        engine.eval("print('Hello World!');");
    }

    @Test
    public void testHelloNashorn() throws Exception {
        engine.eval(new FileReader(JAVASCRIPT_TEST_PARTNER));
        Object result = invocable.invokeFunction("helloNashornFromJava", "Peter Parker");
        LOG(result);
        LOG(result.getClass());
    }

    @Test
    public void testJavaTypesInNashorn() throws Exception {
        engine.eval(new FileReader(JAVASCRIPT_TEST_PARTNER));

        invocable.invokeFunction("javaTypesInNashorn", new Date());
        // [object java.markdown.Date]

        invocable.invokeFunction("javaTypesInNashorn", LocalDateTime.now());
        // [object java.time.LocalDateTime]

        invocable.invokeFunction("javaTypesInNashorn", new LambdaTest.Person("J", 5));
        // [me.roybailey.research.lambda.LambdaTest$Person]

        Map<String, Object> mapData = new HashMap<>();
        mapData.put("testString", "Hello");
        mapData.put("testNumber", 105);
        invocable.invokeFunction("javaTypesInNashorn", mapData);
        // [java.util.HashMap]

    }

    @Test
    public void testThereAndBackAgain() throws Exception {
        engine.eval(new FileReader(JAVASCRIPT_TEST_PARTNER));
        // call JS and have it call my object method to get some data
        invocable.invokeFunction("thereAndBackAgain", this);
    }

    public Map<String, Object> thereAndBackAgainJavaObject() {
        Map<String, Object> mapData = new HashMap<>();
        mapData.put("StringValue", "There And Back Again Worked!");
        mapData.put("NumberValue", 105);
        return mapData;
    }

    public void thereAndBackAgainScriptObject(ScriptObjectMirror mapData) {
        mapData.put("StringValue", "There And Back Again Worked!");
        mapData.put("NumberValue", 105);
    }

    @Test
    public void testGlobalDependencyInjection() throws Exception {
        engine.eval(new FileReader(JAVASCRIPT_TEST_PARTNER));

        // Here we are trying to inject a global service and test whether our
        // JavaScript can access it as a global variable after startup injection
        Map<String, Object> mapDependencyInjection = new HashMap<>();
        mapDependencyInjection.put("service", (Runnable) () -> LOG("This is Java called from Nashorn through a global variable dependency injected object"));
        mapDependencyInjection.put("globalNumber", 75);
        Bindings bindings = new SimpleBindings();
        bindings.put("ioc", mapDependencyInjection);

        engine.eval("var global = this");
        engine.setBindings(bindings, ScriptContext.GLOBAL_SCOPE);

        invocable.invokeFunction("testGlobalDependencyInjection");
    }

    public List<String> getListOfStrings() {
        List<String> listStrings = new ArrayList<>();
        listStrings.add("Anna");
        listStrings.add("Bert");
        listStrings.add("Carl");
        return listStrings;
    }

    public Map<String, Object> getMapOfValues() {
        Map<String, Object> map = new HashMap<>();
        map.put("testString", "hello");
        map.put("testNumber", 100);
        map.put("testBoolean", true);
        return map;
    }

    public ScriptObjectMirror getScriptObject(ScriptObjectMirror som) {
        LOG(som.get("nashorn"));
        LOG(som.get("response"));
        som.put("response", "HI!");
        som.put("gift", "$$$$");
        return som;
    }

    @Test
    public void testJavaCollections() throws Exception {
        engine.eval(new FileReader(JAVASCRIPT_TEST_PARTNER));
        invocable.invokeFunction("testJavaCollections", this);
    }
}
