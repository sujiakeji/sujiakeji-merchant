package com.sujiakeji.merchant.util.mybatis;

import com.sujiakeji.merchant.constant.CommonConstants;
import com.sujiakeji.merchant.dto.common.QueryFilterDto;
import com.google.common.base.CaseFormat;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.base.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Component
@Scope("prototype")
public class MybatisUtils {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private String regex;

    private String tableName;

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public List<QueryFilterDto> convertQueryFilters(List<QueryFilterDto> dtoList) {
        List<QueryFilterDto> newDtoList = new ArrayList<>();
        if (dtoList != null && dtoList.size() > 0) {
            newDtoList = new ArrayList<>();
            for (QueryFilterDto dto : dtoList) {
                String field = dto.getField();
                if (!Strings.isNullOrEmpty(field)) {
                    String underscoreField = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, dto.getField());
                    String convertedField = convertField(underscoreField);
                    String operator = dto.getOperator();
                    String condition = convertedField + CommonConstants.SPACE + operator + CommonConstants.SPACE;
                    logger.debug("condition: {}", condition);
                    Object value = dto.getValue();
                    if (value instanceof String) {
                        value = CommonConstants.SINGLE_QUOTE + String.valueOf(value) + CommonConstants.SINGLE_QUOTE;
                        logger.debug("value: {}", value);
                    }
                    QueryFilterDto newDto = new QueryFilterDto(condition, value);
                    newDtoList.add(newDto);
                }
            }
        }
        return newDtoList;
    }

    public String convertPageOrder(String order) {
        String result = null;
        Splitter commaSplitter = Splitter.on(CommonConstants.COMMA).trimResults().omitEmptyStrings();
        if (Strings.isNullOrEmpty(order)) {
            order = "id";
        }
        String underscoreOrder = CaseFormat.LOWER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, order);
        List<String> splitOrders = commaSplitter.splitToList(underscoreOrder);
        List<String> newOrders = new ArrayList<>();
        for (String splitOrder : splitOrders) {
            String newOrder = convertField(splitOrder);
            if (!Strings.isNullOrEmpty(newOrder)) {
                if (splitOrder.startsWith(CommonConstants.MINUS)) {
                    newOrder = convertField(splitOrder.substring(1)) + " desc";
                }
                newOrders.add(newOrder);
            }
        }
        result = Joiner.on(CommonConstants.COMMA).join(newOrders);
        return result;
    }

    public String convertField(String field) {
        if (Strings.isNullOrEmpty(regex)) {
            return field;
        }
        String convertedField = null;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(field);
        if (matcher.matches()) {
            convertedField = matcher.group(1) + "s." + matcher.group(2);
        } else {
            convertedField = tableName + "." + field;
        }
        return convertedField;
    }

}