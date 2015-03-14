package me.roybailey.research.markdown;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


/**
 * Tests for resource loading.
 */
public class MarkdownPropertiesTest {

    public static final String PATTERN_CODE_BLOCK = "([^`]*[`]{1,3})([^`]*)([`]{1,3}[^`]*)";

    @Test
    public void testMarkdownCodeRegularExpressionWithSingleBlock() {
        String sample1 = "### Embedded code (within some other markdown `create-record` like this)";
        String sample2 = "* Verify ___no code blockst___ ***mean the line*** will be ignored";
        String sample3 = "> Verify three quotes (use ```update-record```) instead of single quotes";
        String sample4 = "`delete-record`";

        List<String> match1 = MarkdownProperties.match(PATTERN_CODE_BLOCK, 2, sample1);
        List<String> match2 = MarkdownProperties.match(PATTERN_CODE_BLOCK, 2, sample2);
        List<String> match3 = MarkdownProperties.match(PATTERN_CODE_BLOCK, 2, sample3);
        List<String> match4 = MarkdownProperties.match(PATTERN_CODE_BLOCK, 2, sample4);

        System.out.println(match1);
        System.out.println(match2);
        System.out.println(match3);
        System.out.println(match4);

        assertThat(match1.get(0), is("create-record"));
        assertThat(match2.size(), is(0));
        assertThat(match3.get(0), is("update-record"));
        assertThat(match4.get(0), is("delete-record"));
    }

    @Test
    public void testMarkdownCodeRegularExpressionWithMultiBlock() {
        String sample1 = "`name`=`value`";
        String sample2 = "`name`=`value` and `sample` = `something` oh my!";
        String sample3 = "handle ```\n" +
                "multi-line\n" +
                "code-blocks\n" +
                "with var.names = {var.values}``` correctly correctly";
        String sample4 = "handle mixed single and double quotes with multi-line" +
                "`name`=\n``` \n" +
                "multi-line\n" +
                "code-blocks\n" +
                "with var.names = {var.values}\n```\n correctly oh my!";

        List<String> match1 = MarkdownProperties.match(PATTERN_CODE_BLOCK, 2, sample1);
        List<String> match2 = MarkdownProperties.match(PATTERN_CODE_BLOCK, 2, sample2);
        List<String> match3 = MarkdownProperties.match(PATTERN_CODE_BLOCK, 2, sample3);
        List<String> match4 = MarkdownProperties.match(PATTERN_CODE_BLOCK, 2, sample4);

        System.out.println(match1);
        System.out.println(match2);
        System.out.println(match3);
        System.out.println(match4);

        assertThat(match1.get(0), is("name"));
        assertThat(match1.get(1), is("value"));
        assertThat(match2.get(0), is("name"));
        assertThat(match2.get(1), is("value"));
        assertThat(match2.get(2), is("sample"));
        assertThat(match2.get(3), is("something"));
        assertThat(match3.get(0), is("\n" +
                "multi-line\n" +
                "code-blocks\n" +
                "with var.names = {var.values}"));
        assertThat(match4.get(0), is("name"));
        assertThat(match4.get(1), is(" \n" +
                "multi-line\n" +
                "code-blocks\n" +
                "with var.names = {var.values}\n"));
    }


    @Test
    public void testMarkdownParsing() throws IOException, InterruptedException {
        Map<String, String> markdownCode = MarkdownProperties.load(Paths.get(
                "src/test/java",
                this.getClass().getPackage().getName().replace(".", File.separator),
                this.getClass().getSimpleName() + ".md"));

        markdownCode.entrySet().stream().forEach(System.out::println);

        assertThat(markdownCode.size(), is(9));
        assertThat(markdownCode.get("create-record"), is("CREATE bla bla bla"));
        assertThat(markdownCode.get("read-record"), is("READ bla WHERE bla.id = {id}"));
        assertThat(markdownCode.get("update-record"), is("\nUPDATE bla\nSET bla\nWHERE bla.id = {id}\n"));
        assertThat(markdownCode.get("delete-record"), is("\nDELETE bla\nWHERE bla.id = {id}\n"));
        assertThat(markdownCode.get("simple"), is("roger that"));
        assertThat(markdownCode.get("server.name"), is("mighty.mike.com"));
        Thread.sleep(200);
    }

}
