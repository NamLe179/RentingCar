package com.example.car.config;

import com.example.car.converter.HopDongChoThueStatusConverter;
import com.example.car.converter.OtoStatusConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Configuration
public class CustomConverter implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new Converter<String, Date>() {
            private final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            @Override
            public Date convert(String source) {
                try {
                    return sdf.parse(source);
                } catch (ParseException e) {
                    throw new IllegalArgumentException("Invalid date format");
                }
            }
        });
        registry.addConverter(new HopDongChoThueStatusConverter());
        registry.addConverter(new OtoStatusConverter());
    }
}