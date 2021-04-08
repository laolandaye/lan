package net.xdclass.factory.abst.abstfactory;

import net.xdclass.factory.abst.product.Pay;
import net.xdclass.factory.abst.product.Refund;
import net.xdclass.factory.abst.product.WechatPay;
import net.xdclass.factory.abst.product.WechatRefund;

/**
 * 小滴课堂,愿景：让技术不再难学
 *
 * @Description
 * @Author 二当家小D
 * @Remark 有问题直接联系我，源码-笔记-技术交流群
 * @Version 1.0
 **/

public class WechatOrderFactory implements OrderFactory {
    @Override
    public Pay createPay() {
        return new WechatPay();
    }

    @Override
    public Refund createRefund() {
        return new WechatRefund();
    }
}
