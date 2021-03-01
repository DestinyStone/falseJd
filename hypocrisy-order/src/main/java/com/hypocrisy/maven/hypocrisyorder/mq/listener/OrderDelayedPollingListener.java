package com.hypocrisy.maven.hypocrisyorder.mq.listener;

import com.alibaba.fastjson.JSONObject;
import com.hypocrisy.maven.hypocrisyorder.entity.PollingParam;
import com.hypocrisy.maven.hypocrisyorder.mq.name.OrderDelayedName;
import com.hypocrisy.maven.hypocrisyorder.service.OmsOrderService;
import com.rabbitmq.client.Channel;
import feign.service.FeignAlipayService;
import lombok.SneakyThrows;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import response.type.ResponseCodeType;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/5 19:00
 * @Description:
 */
@Component
public class OrderDelayedPollingListener {

    @Autowired
    private FeignAlipayService feignAlipayService;

    @Autowired
    private OmsOrderService omsOrderService;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @SneakyThrows
    @RabbitListener(queues = OrderDelayedName.ORDER_QUEUE_DELAYED)
    public void OrderDelayed(Message message, Channel channel) {
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        try {
            PollingParam pollingParam = JSONObject.parseObject(message.getBody(), PollingParam.class);
            response.Message response = feignAlipayService.queryOrderAlipayStatus(pollingParam.getOrderNo());
            System.out.println(pollingParam.getCurrentNotifyNumber());
            // 如果已支付， 则修改订单号为 1
            if (response.getResponseCode() == ResponseCodeType.TRADE_SUCCESS) {
                omsOrderService.updateOrderStatus(pollingParam.getOrderNo(), 1);
                channel.basicReject(deliveryTag, false);
                return;
            }

            // 如果超出重试次数 则关闭订单
            if (pollingParam.isExceedMaxNotifyNumber()) {
                omsOrderService.updateOrderStatus(pollingParam.getOrderNo(), 5);
                channel.basicReject(deliveryTag, false);
                return;
            }

            // 重新发送
            pollingParam.setCurrentNotifyNumber(pollingParam.getCurrentNotifyNumber() + 1);
            rabbitTemplate.convertAndSend(OrderDelayedName.ORDER_EXCHANGE_DELAYED, "", JSONObject.toJSONString(pollingParam), message1 -> {
                MessageProperties messageProperties = message1.getMessageProperties();
                messageProperties.setDelay(pollingParam.getDelayedTime());
                return message1;
            });
            channel.basicAck(deliveryTag, false);

        }catch (Exception e) {
            e.printStackTrace();
            channel.basicReject(deliveryTag, false);
        }
    }
}
