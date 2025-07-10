package com.spring.study.typeconverter.converter;

import com.spring.study.typeconverter.type.IpPort;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class ConverterTest {
//    @Test
//    void stringToInteger() {
//        StringToIntegerConverter converter = new StringToIntegerConverter();
//        Integer result = converter.convert("10");
//        assertThat(result).isEqualTo(10);
//    }
//
//    @Test
//    void integerToString() {
//        IntegerToStringConverter converter = new IntegerToStringConverter();
//        String result = converter.convert(10);
//        assertThat(result).isEqualTo("10");
//    }

    @Test
    void ipPortToString() {
        IpPortToStringConverter converter = new IpPortToStringConverter();
        IpPort source = new IpPort("127.0.0.1", 8080);
        String result = converter.convert(source);
        assertThat(result).isEqualTo("127.0.0.1:8080");
    }
}
