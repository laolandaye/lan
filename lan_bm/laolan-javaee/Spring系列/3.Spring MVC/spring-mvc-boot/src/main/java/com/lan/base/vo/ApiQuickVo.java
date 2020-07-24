package com.lan.base.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.util.ArrayList;

@Data
@NoArgsConstructor
public class ApiQuickVo {

    @NotBlank(message = "apiCode 不为空, 必填")
    private String apiCode;

    @NotBlank(message = "requestUrl 不为空, 必填")
    private String requestUrl;

    @NotBlank(message = "apiLabel 不为空, 必填")
    private String apiLabel;

    @NotBlank(message = "remark 不为空, 必填")
    private String remark;

    @NotBlank(message = "authType 不为空, 必填")
    private String authType;

    @NotBlank(message = "dbuserId 不为空, 必填", groups = ApiQuickTestValidGroup.class)
    private String dbuserId;    // 数据源 id

    @NotBlank(message = "tabId 不为空, 必填", groups = ApiQuickTestValidGroup.class)
    private String tabId;

    @NotBlank(message = "tabName 不为空, 必填", groups = ApiQuickTestValidGroup.class)
    private String tabName;

    private ArrayList<ReqBo> reqs;
    private ArrayList<RespBo> resps;

}
