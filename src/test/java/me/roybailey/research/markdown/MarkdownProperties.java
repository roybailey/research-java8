package me.roybailey.research.markdown;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Example of loading properties, including multi-line code blocks, from markdown files.
 *
 * @author Roy Bailey
 */
public class MarkdownProperties {

    /**
     * Regular expression to extract code blocks using markdown ` char tokens
     */
    public static final String PATTERN_CODE_BLOCK = "([^`]*[`]{1,3})([^`]*)([`]{1,3}[^`]*)";

    /**
     * Matching helper
     *
     * @param pattern the regular expression to match
     * @param group   the group from the regular expression to extract
     * @param payload the payload to match on
     * @return list of extracted group values
     */
    public static List<String> match(String pattern, int group, String payload) {
        List<String> code = new ArrayList<>();
        Pattern compiled = Pattern.compile(pattern);
        Matcher matcher = compiled.matcher(payload);
        while (matcher.find() && matcher.group(group).length() > 0) {
            code.add(matcher.group(group));
        }
        return code;
    }

    /**
     * Loads properties from markdown, based on simple pairing of code blocks.
     *
     * @param markdown Path object to markdown file content.
     * @return map of properties extracted from markdown.
     * @throws IOException
     */
    public static Map<String, String> load(Path markdown) throws IOException {
        Map<String, String> result = new HashMap<>();
        StringBuilder name = new StringBuilder();
        StringBuilder buffer = new StringBuilder();

        Files.lines(markdown, StandardCharsets.UTF_8)
                .forEach(line -> {
                    buffer.append(line).append('\n');
                    match(PATTERN_CODE_BLOCK, 2, buffer.toString()).stream()
                            .filter(code -> code.trim().length() >= 0)
                            .forEach(code -> {
                                if (name.length() == 0) {
                                    name.append(code);
                                } else {
                                    result.put(name.toString(), code);
                                    name.setLength(0);
                                }
                                buffer.setLength(0);
                            });
                });
        return result;
    }

}
