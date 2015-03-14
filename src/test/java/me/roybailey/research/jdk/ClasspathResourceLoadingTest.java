package me.roybailey.research.jdk;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Difference between Class' and ClassLoader's getResourceAsStream()
 * is in the way the path-to-resource is defined.
 * In the case of  Class class getResourcesAsStream() accepts either the
 * relative or the absolute path of the resource.
 * On the other hand ClassLoader's getResourceAsStream() method accepts
 * only the absolute path to the resource and because of this,
 * if we used "/testdata/test.properties" wouldn't be found and
 * getResourceAsStream() would return null
 */
public class ClasspathResourceLoadingTest {

    @Test
    public void testClassResourceLoadingFromClasspath() throws IOException {

        InputStream resourcesStream = ClasspathResourceLoadingTest.class.getResourceAsStream("/testdata/test.properties");
        Properties properties = new Properties();
        properties.load(resourcesStream);
        assertThat(properties.get("property.name"), is("value"));
        System.out.println(properties.get("property.name"));
    }

    @Test
    public void testClassLoaderResourceLoadingFromClasspath() throws IOException {

        InputStream resourcesStream = ClasspathResourceLoadingTest.class.getClassLoader().getResourceAsStream("testdata/test.properties");
        Properties properties = new Properties();
        properties.load(resourcesStream);
        assertThat(properties.get("property.name"), is("value"));
        System.out.println(properties.get("property.name"));
    }
}
