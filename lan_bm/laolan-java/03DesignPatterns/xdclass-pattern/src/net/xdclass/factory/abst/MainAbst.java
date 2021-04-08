package net.xdclass.factory.abst;

import net.xdclass.factory.abst.abstfactory.OrderFactory;

public class MainAbst {

    public static void main(String[] args) {
        //抽象工厂
        OrderFactory orderFactory = FactoryProducer.getFactory("ALI");
        orderFactory.createPay().unifiedorder();
        orderFactory.createRefund().refund();
    }
}
