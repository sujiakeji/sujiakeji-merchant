package com.sujiakeji.merchant.mapper;

import com.sujiakeji.merchant.domain.Merchant;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component
public interface MerMapper {

    Integer insert(Merchant merchant);

    Merchant selectOne(Map<String, Object> params);

    Integer selectCount(Map<String, Object> params);

    List<Merchant> selectList(Map<String, Object> params);

    Integer update(Merchant merchant);

    void delete(Map<String, Object> params);

}