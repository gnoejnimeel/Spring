package com.spring.study.typeconverter.converter;

import com.spring.study.typeconverter.type.IpPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.convert.converter.Converter;

/**
 * 문자 -> 숫자 컨버터
 */
@Slf4j
public class StringToIpPortConverter implements Converter<String, IpPort> {
    @Override
    public IpPort convert(String source) {
        log.info("convert source={}", source);
        //127.0.0.1:8080
        String[] split = source.split(":");
        //127.0.0.1, 8080
        String ip = split[0];
        //127.0.0.1
        int port = Integer.parseInt(split[1]);
        //8080
        return new IpPort(ip, port);
    }
}
