package com.kun.sc.enums;

public enum ScCodeEnum {

    SUCCESS("2", "成功"),
    TEST("50", "心跳测试"),
    EXIT("99", "退出"),
    FAIL("1001", "失败");

    private final String state;

    private final String name;

    ScCodeEnum(String state, String name) {
        this.state = state;
        this.name = name;
    }

    public String getState() {
        return this.state;
    }

    public String getName() {
        return this.name;
    }

}
