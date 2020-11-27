package com.lan.javase._11反射.反射注解应用;

public enum Table2Sy {

    RY_CLGJ("ry_clgj", "cph,gjlx,jl_sj"), // 车辆轨迹: 车牌号+轨迹类型+记录时间
    RY_CLXX("ry_clxx", "cp_bh"), // 车辆信息: 车牌号
    RY_CXXX("ry_cxxx", "sfzhm,gxlx,gxr_sfzhm,hbh_cch,lg_mc,rz_sj,rz_fh,ly"), // 出行信息: 身份证号+关系类型+关系人身份证号+航班号/车牌号+旅馆名称+入住时间+入住房号+来源
    RY_CYM("ry_cym", "sfzhm,cym"), // 曾用名: 身份证号+曾用名
    RY_DZXX("ry_dzxx", "sfzhm,dzlx,xxdz"), // 地址信息: 身份证号码+地址类型+详细地址
    RY_JCXX("ry_jcxx", "sfzhm"), // 人员信息: 身份证号码
    RY_KDXX("ry_kdxx", "xdr_dh,jjr_dh,sjr_dh,xd_sj,jj_sj,sj_sj"), // 快递信息: 下单手机号+寄件手机号+收件手机号+下单时间+寄件时间+收件时间
    RY_SHGX("ry_shgx", "zh,gxzhlx,gxzh"), // 社会关系: 账号+关系账号类型+关系账号
    RY_SJHM("ry_sjhm", "sfzhm,sjhm"), // 手机号码: 身份证号码+手机号码
    RY_TLMX("ry_tlmx", "bd_hmlx,bd_hm,dd_hmlx,dd_hm,th_lx,tl_kssj,tl_sc"), // 通联明细: 本端号码类型+本端号码+对端号码类型+对端号码+通话类型+通联开始时间+通联时长
    RY_WZXF("ry_wzxf", "wz_cph,wz_sj"), // 违章消分: 违章车牌号+违章时间
    RY_XNZHXX("ry_xnzhxx", "sfzhm,xnzhlx_dm,xnzh"), // 虚拟账号信息: 身份证号码+账号类型+账号
    RY_ZJJYMX("ry_zjjymx", "bd_haomalx,bd_haoma,dd_haomalx,dd_haoma,jiaoyilx,jiaoyisj,jiaoyije"), // 资金交易明细: 本端号码类型+本端号码+对端号码类型+对端号码+交易类型+交易时间+交易金额
    RY_ZJXX("ry_zjxx", "sfzhm,zjlx,zjzh"), // 资金信息: 身份证号码+资金类型+资金账号
    XYR_JCXX_TPDZ("xyr_jcxx_tpdz", "sfzhm,xyrtp_img"), // 嫌疑人头像: 图片
    JQHZ_SZXX("jqhz_szxx", "id_card,sj,hd_ds"); // 人员轨迹：身份证号+活动时间+活动地点

    private final String name;

    private final String sy;

    Table2Sy(String name, String sy) {
        this.name = name;
        this.sy = sy;
    }

    public String getName() {
        return name;
    }

    public String getSy() {
        return sy;
    }

    public static String findSyByName(String name) {
        for (Table2Sy value : Table2Sy.values()) {
            if (value.getName().equals(name)) {
                return value.getSy();
            }
        }
        return null;
    }

}
