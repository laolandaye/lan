package net.xdclass.factory.abst.abstfactory;

import net.xdclass.factory.abst.product.Pay;
import net.xdclass.factory.abst.product.Refund;

public interface OrderFactory {



    Pay createPay();


    Refund createRefund();


}
