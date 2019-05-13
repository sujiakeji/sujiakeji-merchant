package com.sujiakeji.merchant;

import com.sujiakeji.merchant.domain.Merchant;
import com.sujiakeji.merchant.mapper.MerMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class MerchantMapperTests {

    @Autowired
    private MerMapper merMapper;

    @Test
    public void createMer() {
        Merchant merchant = new Merchant();
        merchant.setMerName("测试MerName");
        merMapper.insert(merchant);
    }

    @Test
    public void selectList() {
        Map<String, Object> params = new HashMap<>();
        params.put("queryType", "full");
        params.put("offset", 0);
        params.put("size", 10);

        List<Merchant> merchants = merMapper.selectList(params);
        assertNotNull(merchants);
        assertTrue(!merchants.isEmpty());
    }

    @Test
    public void selectOne() {
        Map<String, Object> params = new HashMap<>();
        params.put("queryType", "full");

        Merchant merchant = merMapper.selectOne(params);
        assertNotNull(merchant);
        assertEquals("测试MerName", merchant.getMerName());
    }
}