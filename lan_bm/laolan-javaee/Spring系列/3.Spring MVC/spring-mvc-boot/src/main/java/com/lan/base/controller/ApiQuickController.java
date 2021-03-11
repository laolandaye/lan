package com.lan.base.controller;

import com.lan.base.exception.ApiQuickException;
import com.lan.base.service.ApiQuickService;
import com.lan.base.exception.group.SaveValidGroup;
import com.lan.base.utils.BaseResultVo;
import com.lan.base.vo.ApiQuickVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;

@RestController
@RequestMapping("/api/openapi/apiQuick")
public class ApiQuickController {

    @Autowired
    private ApiQuickService apiQuickService;

    @PostMapping(value = "/sqlQuery")
    @ResponseBody
    public BaseResultVo sqlQuery(
            @RequestBody @Valid ApiQuickVo vo
    ) {
        return apiQuickService.saveSqlQuery(vo);
    }

    @PostMapping(value = "/defaultSqlQuery")
    @ResponseBody
    public BaseResultVo defaultSqlQuery(
            @RequestBody @Validated(value = { Default.class }) ApiQuickVo vo
    ) {
        return apiQuickService.saveSqlQuery(vo);
    }

    @PostMapping(value = "/saveSqlQuery")
    @ResponseBody
    public BaseResultVo saveSqlQuery(
            @RequestBody @Validated(value = { SaveValidGroup.class, Default.class }) ApiQuickVo vo) {
        return apiQuickService.saveSqlQuery(vo);
    }

}
