package com.sujiakeji.merchant.controller;

import com.sujiakeji.merchant.config.StorageConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    StorageConfig storageConfig;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public String test() {
        return storageConfig.getBaseUrl();
    }

}
