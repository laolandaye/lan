package com.lan.base.service;

import com.lan.base.exception.ApiQuickException;
import com.lan.base.exception.GlobalExceptionDefine;
import com.lan.base.utils.BaseResultVo;
import com.lan.base.vo.ApiQuickVo;
import com.lan.base.vo.RespBo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class ApiQuickService {

    @Transactional
    public BaseResultVo saveSqlQuery(ApiQuickVo vo) {
        BaseResultVo brv = new BaseResultVo();

        List<RespBo> resps = vo.getResps();
        if(CollectionUtils.isEmpty(resps)) {
            throw new ApiQuickException(GlobalExceptionDefine.ApiQuickEnum.RESULT_NOT_LESS_ONE.getCode(), GlobalExceptionDefine.ApiQuickEnum.RESULT_NOT_LESS_ONE.getMsg());
        }
        return brv;
    }

}
