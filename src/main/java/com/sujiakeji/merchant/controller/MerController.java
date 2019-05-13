package com.sujiakeji.merchant.controller;

import com.sujiakeji.merchant.domain.Merchant;
import com.sujiakeji.merchant.dto.common.PageRequestDto;
import com.sujiakeji.merchant.dto.common.PageResponseDto;
import com.sujiakeji.merchant.dto.common.QueryFilterDto;
import com.sujiakeji.merchant.service.MerService;
import com.sujiakeji.merchant.util.common.QueryUtils;
import com.sujiakeji.merchant.validator.MerValidator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/mer")
public class MerController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private MerService merService;

    @Autowired
    private QueryUtils queryUtils;

    @Autowired
    private MerValidator merValidator;

    @Autowired
    private ObjectMapper lowerCamelObjectMapper;

    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(merValidator);
    }

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public Merchant create(@RequestBody @Validated Merchant merchant,
                           BindingResult bindingResult,
                           HttpServletResponse httpResponse) throws IOException {
        if (bindingResult.hasErrors()) {
            List<String> codes = new ArrayList<>();
            for (FieldError error : bindingResult.getFieldErrors()) {
                String code = error.getCode();
                logger.debug("code: {}'", code);
                codes.add(code);
            }
            httpResponse.setStatus(HttpStatus.BAD_REQUEST.value());
            String content = lowerCamelObjectMapper.writeValueAsString(codes);
            httpResponse.getWriter().append(content);
            httpResponse.getWriter().close();
            return null;
        }
        try {
            return merService.create(merchant);
        } catch (Exception e) {
            httpResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
            httpResponse.getWriter().append(e.getMessage());
            httpResponse.getWriter().close();
            return null;
        }
    }

    @RequestMapping(value = "/getById", method = RequestMethod.GET)
    public Merchant getById(@RequestParam(value = "id", required = false) Long id) {
        return merService.findById(id);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public Merchant get(@RequestParam(value = "filters", required = false) String filters,
                        HttpServletResponse response) {
        String queryType = "full";
        List<QueryFilterDto> queryFilterDtoList = null;
        try {
            queryFilterDtoList = queryUtils.convertToDtoList(filters);
        } catch (IOException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        return merService.find(queryType, queryFilterDtoList);
    }

    @RequestMapping(value = "/count", method = RequestMethod.GET)
    public Long count(@RequestParam(value = "filters", required = false) String filters,
                      HttpServletResponse response) {
        List<QueryFilterDto> queryFilterDtoList = null;
        try {
            queryFilterDtoList = queryUtils.convertToDtoList(filters);
        } catch (IOException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        Long count = merService.count(queryFilterDtoList).longValue();
        return count;
    }

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public PageResponseDto<Merchant> list(@RequestParam(value = "page", required = false) Integer page,
                                          @RequestParam(value = "size", required = false) Integer size,
                                          @RequestParam(value = "order", required = false) String order,
                                          @RequestParam(value = "filters", required = false) String filters,
                                          HttpServletResponse response) {
        String queryType = "full";
        List<QueryFilterDto> queryFilterDtoList = null;
        try {
            queryFilterDtoList = queryUtils.convertToDtoList(filters);
        } catch (IOException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }
        PageRequestDto pageRequestDto = new PageRequestDto(page, size, order);
        List<Merchant> items = merService.list(queryType, queryFilterDtoList, pageRequestDto);
        Long count = merService.count(queryFilterDtoList).longValue();
        PageResponseDto<Merchant> pageResponseDto = new PageResponseDto<Merchant>(page, size, count, items);
        return pageResponseDto;
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public Merchant update(@RequestBody Merchant merchant,
                           HttpServletResponse response) {
        Merchant updatedMerchant = null;
        try {
            updatedMerchant = merService.update(merchant);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return updatedMerchant;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public Boolean delete(@RequestParam(value = "filters", required = false) String filters,
                          HttpServletResponse response) {
        Boolean result = null;
        List<QueryFilterDto> queryFilterDtoList = null;
        try {
            queryFilterDtoList = queryUtils.convertToDtoList(filters);
        } catch (IOException e) {
            response.setStatus(HttpStatus.BAD_REQUEST.value());
        }

        try {
            result = merService.delete(queryFilterDtoList);
        } catch (Exception e) {
            response.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        }
        return result;
    }

}
