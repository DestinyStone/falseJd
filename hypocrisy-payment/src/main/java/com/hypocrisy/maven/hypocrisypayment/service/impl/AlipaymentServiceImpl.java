package com.hypocrisy.maven.hypocrisypayment.service.impl;

import com.alipay.api.AlipayClient;
import com.alipay.api.domain.AlipayTradePrecreateModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradePrecreateRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradePrecreateResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.hypocrisy.maven.hypocrisypayment.ali.response.AlipayResponseType;
import com.hypocrisy.maven.hypocrisypayment.service.PaymentService;
import lombok.SneakyThrows;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import response.Message;
import response.type.ResponseCodeType;
import sun.misc.BASE64Encoder;
import utils.QrcodeUtils;
import utils.RedisUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 17:56
 * @Description:
 */
@Component
public class AlipaymentServiceImpl implements PaymentService {

    @Autowired
    private AlipayClient alipayClient;

    private String orderQrcodeRediskey = "oms:order:qrcode:orderNo:";

    private String alipayLogoPos = "classpath:static/alipaylogo.jpg";

    @SneakyThrows
    @Override
    public String createQrcode(String orderSn, BigDecimal totalAmount) {
        String qrcode = null;

        // 从redis中获取
        qrcode = RedisUtils.get(orderQrcodeRediskey + orderSn);
        if (StringUtils.isBlank(qrcode)) {
            qrcode = alipayCreateQrcode(orderSn, totalAmount);
            if (!StringUtils.isBlank(qrcode))
                RedisUtils.set(orderQrcodeRediskey + orderSn, qrcode, 30L, TimeUnit.SECONDS);
        }
        byte[] qrcodeBytes = QrcodeUtils.createQrcode(qrcode, 200, this.getAlipayLogo());
        return "data:image/jpeg;base64," + new BASE64Encoder().encode(qrcodeBytes);
    }

    @Override
    @SneakyThrows
    public Message queryOrderAlipayStatus(String orderNo) {
        AlipayTradeQueryRequest alipayTradeQueryRequest = new AlipayTradeQueryRequest();
        AlipayTradeQueryModel alipayTradeQueryModel = new AlipayTradeQueryModel();
        alipayTradeQueryModel.setOutTradeNo(orderNo);
        alipayTradeQueryRequest.setBizModel(alipayTradeQueryModel);
        AlipayTradeQueryResponse response = alipayClient.execute(alipayTradeQueryRequest);
        if (!response.isSuccess()) {
            if ((AlipayResponseType.TRADE_NOT_EXIST.toString().equals(response.getSubCode())))
                return new Message(ResponseCodeType.TRADE_NOT_EXIST, "未开始交易", true);
            return new Message(ResponseCodeType.UN_KNOW_ERROR, null, false);
        }
        if (AlipayResponseType.WAIT_BUYER_PAY.toString().equals(response.getTradeStatus())){
            String payRoles = response.getBuyerLogonId();
            HashMap<String, String> responseMessage = new HashMap<>();
            responseMessage.put("name", payRoles);
            responseMessage.put("message", "支付中");
            return new Message(ResponseCodeType.WAIT_BUYER_PAY, responseMessage, true);
        }

        if (AlipayResponseType.TRADE_SUCCESS.toString().equals(response.getTradeStatus())) {
            return new Message(ResponseCodeType.TRADE_SUCCESS, "已支付", true);
        }

        if(AlipayResponseType.TRADE_FINISHED.toString().equals(response.getTradeStatus())) {
            return new Message(ResponseCodeType.TRADE_SUCCESS, "交易已关闭", true);
        }

        return null;
    }

    /**
     * 生成alipay的支付二维码
     * @param orderSn
     * @param totalAmount
     * @return
     */
    @SneakyThrows
    private String alipayCreateQrcode(String orderSn, BigDecimal totalAmount) {
        AlipayTradePrecreateRequest alipayTradePrecreateRequest = new AlipayTradePrecreateRequest();
        AlipayTradePrecreateModel alipayTradePrecreateModel = new AlipayTradePrecreateModel();
        alipayTradePrecreateModel.setSubject(orderSn);
        alipayTradePrecreateModel.setOutTradeNo(orderSn);
        alipayTradePrecreateModel.setTotalAmount(totalAmount.toString());
        alipayTradePrecreateModel.setTimeoutExpress("24h");
        alipayTradePrecreateRequest.setBizModel(alipayTradePrecreateModel);


        AlipayTradePrecreateResponse execute = alipayClient.execute(alipayTradePrecreateRequest);
        if (!execute.isSuccess()) return null;

        return execute.getQrCode();
    }

    private File getAlipayLogo() throws FileNotFoundException {
        return ResourceUtils.getFile(alipayLogoPos);
    }
}
