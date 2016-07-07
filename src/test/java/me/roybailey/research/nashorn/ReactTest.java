package me.roybailey.research.nashorn;

import jdk.nashorn.api.scripting.NashornScriptEngine;

import javax.script.ScriptEngineManager;
import java.io.FileReader;

/**
 * Created by roybailey on 09/12/2014.
 */
public class ReactTest {
    private NashornScriptEngine nashorn;

    // Constructor, sets up React and the Component
    public ReactTest() throws Throwable {
        nashorn = (NashornScriptEngine) new ScriptEngineManager().getEngineByName("nashorn");
        // React depends on the "global" variable
        nashorn.eval("var global = this");
        // eval react.js
        nashorn.eval(new FileReader("src/test/java/research/nashorn/nashorn-polyfill.js"));
        nashorn.eval(new FileReader("src/test/java/research/nashorn/react/react.js"));
        // This would also be an external JS file
        nashorn.eval(new FileReader("src/test/java/research/nashorn/ReactTest.js"));
    }

    public String render(String text) {
        try {
            Object html = nashorn.invokeFunction("renderFromServer", text);
            return String.valueOf(html);
        } catch (Exception e) {
            throw new IllegalStateException("failed to render react component", e);
        }
    }

    public static void main(String... args) throws Throwable {
        ReactTest test = new ReactTest();
        String html = test.render("Hello React from Server");
        System.out.println(html);
    }
}

