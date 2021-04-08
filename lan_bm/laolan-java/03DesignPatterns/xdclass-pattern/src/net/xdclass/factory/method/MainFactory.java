//package net.xdclass.factory.method;
//
//import net.xdclass.factory.method.factory.AliPayFactory;
//import net.xdclass.factory.method.factory.PayFactory;
//import net.xdclass.factory.method.factory.WechatPayFactory;
//import net.xdclass.factory.method.product.Pay;
//
//public class MainFactory {
//
//    public static void main(String[] args) {
//
//
//        //工厂
//        PayFactory aliPayFactory = new AliPayFactory();
//        Pay aliPay = aliPayFactory.getPay();
//        aliPay.unifiedorder();
//
//
//        PayFactory wechatPayFactory = new WechatPayFactory();
//        Pay wechatPay = wechatPayFactory.getPay();
//        wechatPay.unifiedorder();
//
//
//
//    }
//}
