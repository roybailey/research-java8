package me.roybailey.research.nashorn;

import jdk.nashorn.api.scripting.ScriptObjectMirror;
import org.junit.After;
import org.junit.Test;
import me.roybailey.research.lambda.LambdaTest;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by roybailey on 18/04/2014.
 */
public class NashornTest {

    public static String jsWillCallThis(String name) {
        System.out.println("Called from javascript to print in Java:  " + name);
        return "returned from java to print from javascript";
    }

    public static void jsTypesInJava(Object object) {
        System.out.println("javascript called java with object type: " + object.getClass());
    }

    @After
    public void finish() throws InterruptedException {
        Thread.sleep(100);
    }

    @Test
    public void testNashorn() throws ScriptException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval("print('Hello World!');");
    }

    @Test
    public void testNashornFile() throws FileNotFoundException, ScriptException, NoSuchMethodException {
        ScriptEngine engine = new ScriptEngineManager().getEngineByName("nashorn");
        engine.eval(new FileReader("src/test/java/" + this.getClass().getPackage().getName().replace(".", File.separator) + "/NashornTest.js"));

        Invocable invocable = (Invocable) engine;

        Object result = invocable.invokeFunction("fun1", "Peter Parker");
        System.out.println(result);
        System.out.println(result.getClass());

        invocable.invokeFunction("fun2", new Date());
        // [object java.markdown.Date]

        invocable.invokeFunction("fun2", LocalDateTime.now());
        // [object java.time.LocalDateTime]

        invocable.invokeFunction("fun2", new LambdaTest.Person("J", 5));
        // [me.roybailey.research.lambda.LambdaTest$Person]

        Map<String,Object> mapData = new HashMap<>();
        mapData.put("testString","Hello");
        mapData.put("testNumber",105);
        invocable.invokeFunction("fun2", mapData);
        // [java.util.HashMap]

        // call JS and have it call my object method to get some data
        invocable.invokeFunction("thereAndBackAgain", this);
    }

    public Map<String,Object> thereAndBackAgain() {
        Map<String,Object> mapData = new HashMap<>();
        mapData.put("StringValue","There And Back Again Worked!");
        mapData.put("NumberValue",105);
        return mapData;
    }

    public void thereAndBackAgainScriptObject(ScriptObjectMirror mapData) {
        mapData.put("StringValue","There And Back Again Worked!");
        mapData.put("NumberValue",105);
    }
}
