package com.sujiakeji.merchant.util.common;

import com.sujiakeji.merchant.dto.common.QueryFilterDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class QueryUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ObjectMapper lowerCamelObjectMapper;

    public List<QueryFilterDto> convertToDtoList(String filters) throws IOException {
        List<QueryFilterDto> queryFilterDtoList = null;
        if (!Strings.isNullOrEmpty(filters)) {
            queryFilterDtoList = lowerCamelObjectMapper.readValue(
                    filters, lowerCamelObjectMapper.getTypeFactory().constructCollectionType(
                            List.class, QueryFilterDto.class));
        }
        return queryFilterDtoList;
    }

}