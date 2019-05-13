package com.sujiakeji.merchant.util.common;

import com.sujiakeji.merchant.constant.CommonConstants;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;

import java.text.SimpleDateFormat;
import java.util.TimeZone;

public class JsonFactory {

    public static ObjectMapper createSnakeObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper()
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE)
                .setDateFormat(new SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT_PATTERN))
                .setTimeZone(TimeZone.getTimeZone(CommonConstants.DEFAULT_TIME_ZONE));
        return objectMapper;
    }

    public static ObjectMapper createLowerCamelObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper()
                .setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY)
                .setPropertyNamingStrategy(PropertyNamingStrategy.LOWER_CAMEL_CASE)
                .setDateFormat(new SimpleDateFormat(CommonConstants.DEFAULT_DATE_FORMAT_PATTERN))
                .setTimeZone(TimeZone.getTimeZone(CommonConstants.DEFAULT_TIME_ZONE));
        return objectMapper;
    }

}
