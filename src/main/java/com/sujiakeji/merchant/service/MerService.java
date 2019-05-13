package com.sujiakeji.merchant.service;

import com.sujiakeji.merchant.domain.Merchant;
import com.sujiakeji.merchant.dto.common.PageRequestDto;
import com.sujiakeji.merchant.dto.common.QueryFilterDto;
import com.sujiakeji.merchant.mapper.MerMapper;
import com.sujiakeji.merchant.util.common.DateTimeUtils;
import com.sujiakeji.merchant.util.mybatis.MybatisUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class MerService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MerMapper merMapper;

    @Autowired
    private MybatisUtils mybatisUtils;

    @Autowired
    private DateTimeUtils dateTimeUtils;

    @Transactional
    public Merchant create(Merchant merchant) {
        if (merchant != null) {
            merchant.setCreateDate(dateTimeUtils.getCurrentDateTime());
        }
        merMapper.insert(merchant);
        Long merId = merchant.getId();
        return findById(merId);
    }

    public Merchant findById(Long id) {
        if (id == null) {
            return null;
        }
        List<QueryFilterDto> dtoList = new ArrayList<>();
        dtoList.add(new QueryFilterDto("id", "=", id));
        return find("full", dtoList);
    }

    public Merchant find(String queryType, List<QueryFilterDto> queryFilterDtoList) {
        List<QueryFilterDto> filterList = mybatisUtils.convertQueryFilters(queryFilterDtoList);
        Map<String, Object> params = new HashMap<>();
        params.put("queryType", queryType);
        params.put("filterList", filterList);
        return merMapper.selectOne(params);
    }

    public Integer count(List<QueryFilterDto> queryFilterDtoList) {
        List<QueryFilterDto> filterList = mybatisUtils.convertQueryFilters(queryFilterDtoList);
        Map<String, Object> params = new HashMap<>();
        params.put("filterList", filterList);
        return merMapper.selectCount(params);
    }

    public List<Merchant> list(String queryType, List<QueryFilterDto> queryFilterDtoList, PageRequestDto pageRequestDto) {
        List<QueryFilterDto> filterList = mybatisUtils.convertQueryFilters(queryFilterDtoList);
        String order = pageRequestDto.getOrder();
        String convertedOrder = mybatisUtils.convertPageOrder(order);
        Integer offset = pageRequestDto.getOffset();
        Integer size = pageRequestDto.getSize();
        Map<String, Object> params = new HashMap<>();
        params.put("queryType", queryType);
        params.put("filterList", filterList);
        params.put("order", convertedOrder);
        params.put("offset", offset);
        params.put("size", size);
        return merMapper.selectList(params);
    }

    @Transactional
    public Merchant update(Merchant merchant) {
        Merchant updatedMerchant = null;
        Long merId = merchant.getId();
        Merchant existingMerchant = findById(merId);
        if (existingMerchant != null) {
            merchant.setUpdateDate(dateTimeUtils.getCurrentDateTime());
            merMapper.update(merchant);
            updatedMerchant = findById(merId);
        }
        return updatedMerchant;
    }

    @Transactional
    public Boolean delete(List<QueryFilterDto> queryFilterDtoList) {
        if (count(queryFilterDtoList) == 0) {
            return false;
        }
        List<QueryFilterDto> filterList = mybatisUtils.convertQueryFilters(queryFilterDtoList);
        Map<String, Object> params = new HashMap<>();
        params.put("filterList", filterList);
        merMapper.delete(params);
        Boolean result = false;
        if (count(queryFilterDtoList) == 0) {
            result = true;
        }
        return result;
    }

    @Transactional
    public Boolean deleteById(Long id) {
        if (id == null) {
            return null;
        }
        List<QueryFilterDto> dtoList = new ArrayList<>();
        dtoList.add(new QueryFilterDto("id", "=", id));
        return delete(dtoList);
    }

}