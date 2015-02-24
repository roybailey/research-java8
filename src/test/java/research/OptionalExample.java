package research;

import org.junit.Test;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Created by roybailey on 15/04/2014.
 */
public class OptionalExample {

    public Optional<String> getOptionalEmpty() {
        return Optional.empty();
    }

    public Optional<String> getOptionalAsNull() {
        return Optional.ofNullable(null);
    }

    public Optional<String> getOptionalAsValue() {
        return Optional.of("Test");
    }

    @Test
    public void testOptional() {

        assertThat(getOptionalEmpty().isPresent(), is(false));
        assertThat(getOptionalAsNull().isPresent(), is(false));
        assertThat(getOptionalAsValue().isPresent(), is(true));

        assertThat(getOptionalAsValue().get(), is("Test"));

        assertThat(getOptionalEmpty().orElse("N/A"), is("N/A"));
        assertThat(getOptionalAsNull().orElse("N/A"), is("N/A"));
        assertThat(getOptionalAsValue().orElse("N/A"), is("Test"));
    }
}
