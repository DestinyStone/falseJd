package com.hypocrisy.maven.hypocrisypayment.ali.response;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/3 11:06
 * @Description:
 */
public enum AlipayResponseType {

    TRADE_NOT_EXIST("ACQ.TRADE_NOT_EXIST"),  // 未开始交易

    WAIT_BUYER_PAY("WAIT_BUYER_PAY"),  // 支付中
    TRADE_SUCCESS("TRADE_SUCCESS"),   // 已支付
    TRADE_CLOSED("TRADE_CLOSED"),    // 交易关闭
    TRADE_FINISHED("TRADE_FINISHED");  // 交易结束

    String i;
    AlipayResponseType(String i) {
        this.i = i;
    }


    @Override
    public String toString() {
        if (i.equals(AlipayResponseType.TRADE_NOT_EXIST.i))
            return AlipayResponseType.TRADE_NOT_EXIST.i;
        return super.toString();
    }
}
