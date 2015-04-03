package me.roybailey.research.guava;

import com.google.common.reflect.ClassPath;
import org.junit.Test;

import java.io.IOException;

/**
 * Created by roybailey on 01/04/2015.
 */
public class GuavaTest {

    @Test
    public void testClassPathWalker() throws IOException {
        ClassPath classpath = ClassPath.from(this.getClass().getClassLoader());
        for (ClassPath.ClassInfo classInfo : classpath.getTopLevelClasses("me.roybailey.research")) {
            System.out.println(classInfo.getName());
        }
    }
}
