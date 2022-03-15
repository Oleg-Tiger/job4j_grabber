package ru.job4j.template;

import static org.junit.Assert.assertThat;
import static org.hamcrest.Matchers.is;
import org.junit.Ignore;
import org.junit.Test;
import java.util.HashMap;
import java.util.Map;

public class GeneratorTest {

    @Ignore
    @Test
    public void whenGenerated() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String>  args  = new HashMap() {{
                put("name", "Oleg");
                put("subject", "you");
            }};
        String rsl = generator.produce(template, args);
        assertThat(rsl, is("I am a Oleg, Who are you?"));
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenNoKeysInMap() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}, Who are ${subject}?";
        Map<String, String>  args  = new HashMap() {{
            put("name", "Oleg");
        }};
        generator.produce(template, args);
    }

    @Ignore
    @Test(expected = IllegalArgumentException.class)
    public void whenExtraKeysInMap() {
        Generator generator = new StringGenerator();
        String template = "I am a ${name}";
        Map<String, String>  args  = new HashMap() {{
            put("name", "Oleg");
            put("subject", "you");
        }};
        generator.produce(template, args);
    }
}