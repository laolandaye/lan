package com.lan.base.exception;


/**
 * @author krz
 * @date 2020/03/18
 * @desc 全局异常定义信息
 */
public final class GlobalExceptionDefine {

    private static final Integer INIT_COMMON_ERR_CODE = 100000;
    private static final Integer INIT_USER_ERR_CODE = 110000;
    private static final Integer INIT_PROVIDER_CODE = 120000;
    private static final Integer INIT_API_QUICK_CODE = 130000;

    /**
     * 公共异常信息
     * 异常码区间 [100000-110000)
     */
    public enum Common {
        PARAM_IS_NULL(INIT_COMMON_ERR_CODE, "参数为空"),
        START_NOT_MORE_THAN_END(INIT_COMMON_ERR_CODE + 1, "开始时间不能大于结束时间"),
        EXPORT_NUM_LARGE(INIT_COMMON_ERR_CODE+2, "导出数量过大，请条件筛选后再导出"),
        OPERATION_EXCEPTION(INIT_USER_ERR_CODE + 19, "不合法的操作指令"),
        ;
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        Common(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    public enum CommonException{
        Not_EXIST(INIT_USER_ERR_CODE,"该ID查询结果为NULL");
        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }
        CommonException(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    /**
     * 用户管理模块异常定义
     * 异常码区间 [120000-130000)
     */

    public enum PROVIDER {
        CERTIFICATEFile_IS_NOT_FOUND(INIT_PROVIDER_CODE,"该供应商资质文件不存在"),
        CERT_DOWNLOAD_FAIL(INIT_PROVIDER_CODE + 2, "供应商资质文件下载失败"),
        CERTIFICATEFile_IS_NOT_UPLOAD(INIT_PROVIDER_CODE + 3, "供应商资质文件未上传"),
        USER_NOT_VERIFIED(INIT_PROVIDER_CODE + 4, "用户还未实名认证");

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        PROVIDER(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

    /**
     * 快速创建管理模块异常定义
     * 异常码区间 [130000-140000)
     */

    public enum ApiQuickEnum {
        RESULT_NOT_LESS_ONE(INIT_API_QUICK_CODE + 1,"返回结果 显示字段个数 不能少于1");

        private int code;
        private String msg;

        public int getCode() {
            return code;
        }

        public String getMsg() {
            return msg;
        }

        ApiQuickEnum(int code, String msg) {
            this.code = code;
            this.msg = msg;
        }
    }

}
