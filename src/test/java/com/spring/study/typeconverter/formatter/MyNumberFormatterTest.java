package com.spring.study.typeconverter.formatter;

import com.spring.study.typeconverter.controller.MyNumberFormatter;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.text.ParseException;
import java.util.Locale;

public class MyNumberFormatterTest {
    MyNumberFormatter formatter = new MyNumberFormatter();

    @Test
    void parse() throws ParseException {
        Number result = formatter.parse("1,000", Locale.KOREA);
        Assertions.assertThat(result).isEqualTo(1000L);
    }

    @Test
    void print() {
        String result = formatter.print(1000, Locale.KOREA);
        Assertions.assertThat(result).isEqualTo("1,000");
    }
}
