package com.lan.base.controller;

import com.lan.base.exception.ApiQuickException;
import com.lan.base.service.ApiQuickService;
import com.lan.base.utils.ApiQuickSaveValidGroup;
import com.lan.base.utils.BaseResultVo;
import com.lan.base.vo.ApiQuickVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.groups.Default;

@Controller
@RequestMapping("/api/openapi/apiQuick")
public class ApiQuickController {

    @Autowired
    private ApiQuickService apiQuickService;

    @RequestMapping(value = "/testSqlQuery", method = RequestMethod.POST)
    @ResponseBody
    public BaseResultVo testSqlQuery(@RequestBody @Validated(value = { Default.class }) ApiQuickVo vo, BindingResult results) {
        BaseResultVo brv = new BaseResultVo();
        try {
            if (results.hasErrors()) {
                brv.setResultMsg("9999",results.getFieldError().getDefaultMessage());
                return brv;
            }
            return brv;
        } catch (ApiQuickException aqe) {
            aqe.printStackTrace();
            brv.setResultMsg("" + aqe.getCode(), aqe.getMsg());
            return brv;
        } catch (Exception e) {
            e.printStackTrace();
            brv.setResultMsg("9999","失败");
            return brv;
        }
    }

    @RequestMapping(value = "/saveSqlQuery", method = RequestMethod.POST)
    @ResponseBody
    public BaseResultVo saveSqlQuery(@RequestBody @Validated(value = { ApiQuickSaveValidGroup.class, Default.class }) ApiQuickVo vo, BindingResult results) {
        BaseResultVo brv = new BaseResultVo();
        try {
            if (results.hasErrors()) {
                brv.setResultMsg("9999",results.getFieldError().getDefaultMessage());
                return brv;
            }
            return apiQuickService.saveSqlQuery(vo);
        } catch (ApiQuickException aqe) {
            aqe.printStackTrace();
            brv.setResultMsg("" + aqe.getCode(), aqe.getMsg());
            return brv;
        } catch (Exception e) {
            e.printStackTrace();
            brv.setResultMsg("9999","失败");
            return brv;
        }
    }

}
