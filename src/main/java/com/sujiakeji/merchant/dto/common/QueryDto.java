package com.sujiakeji.merchant.dto.common;

import java.util.List;

public class QueryDto {

    private Integer page;

    private Integer size;

    private Integer offset;

    private String order;

    private List<FilterDto> filters;

    public QueryDto(Integer page, Integer size, String order) {
        if (page == null || page < 0) {
            this.page = 0;
        } else {
            this.page = page;
        }
        if (size == null || size < 0) {
            this.size = 10;
        } else if (size > 100) {
            this.size = 100;
        } else {
            this.size = size;
        }
        this.offset = this.page * this.size;
        this.order = order;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public class FilterDto {

        private String field;

        private String operator;

        private String condition;

        private Object value;

        public FilterDto() {
        }

        public FilterDto(String field, String operator, Object value) {
            this.field = field;
            this.operator = operator;
            this.value = value;
        }

        public FilterDto(String condition, Object value) {
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
    }
}
