package com.sujiakeji.merchant.dto.common;

public class QueryFilterDto {

    private String field;

    private String operator;

    private String condition;

    private Object value;

    public QueryFilterDto() {
    }

    public QueryFilterDto(String field, String operator, Object value) {
        this.field = field;
        this.operator = operator;
        this.value = value;
    }

    public QueryFilterDto(String condition, Object value) {
        this.condition = condition;
        this.value = value;
    }

    public String getField() {
        return field;
    }

    public void setField(String field) {
        this.field = field;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "QueryFilterDto{" +
                "field='" + field + '\'' +
                ", operator='" + operator + '\'' +
                ", condition='" + condition + '\'' +
                ", value=" + value +
                '}';
    }
}
