package com.lan.base.vo;

import com.lan.base.exception.group.SaveValidGroup;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class ApiQuickVo {

    @NotBlank(message = "apiCode 不为空, 必填")
    private String apiCode;

    @NotBlank(message = "requestUrl 不为空, 必填")
    private String requestUrl;

    @NotNull(message = "apiLabel 不为空, 必填")
    private Integer apiLabel;

    @NotBlank(message = "remark 不为空, 必填")
    private String remark;

    @NotBlank(message = "authType 不为空, 必填")
    private String authType;

    @NotBlank(message = "dbuserId 不为空, 必填", groups = SaveValidGroup.class)
    private String dbuserId;    // 数据源 id

    @NotBlank(message = "tabId 不为空, 必填", groups = SaveValidGroup.class)
    private String tabId;

    @NotBlank(message = "tabName 不为空, 必填", groups = SaveValidGroup.class)
    private String tabName;


}
