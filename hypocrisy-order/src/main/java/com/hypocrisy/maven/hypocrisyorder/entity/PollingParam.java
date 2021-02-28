package com.hypocrisy.maven.hypocrisyorder.entity;

import lombok.Data;

import java.util.HashMap;

/**
 * @Auther: DestinyStone
 * @Date: 2020/11/5 20:49
 * @Description:
 */
@Data
public class PollingParam {

    public String orderNo;

    public HashMap<String, Object> notifyInterval;

    public Integer currentNotifyNumber;

    public Integer maxNotifyNumber;

    public Integer getDelayedTime() {
        if (notifyInterval.get(currentNotifyNumber + "") == null) return  -2;
        return new Integer(notifyInterval.get(currentNotifyNumber + "").toString());
    }

    public PollingParam() {
        init();
    }

    public Integer getCurrentNotifyNumber() {
        if (currentNotifyNumber <= maxNotifyNumber) {
            return currentNotifyNumber;
        }
        return -1;
    }

    public void init() {
        notifyInterval.put("1", 1000 * 60 * 2);   // 2分钟
        notifyInterval.put("2", 1000 * 60 * 4);   // 4分钟
        notifyInterval.put("3", 1000 * 60 * 8);   // 8分钟
        notifyInterval.put("4", 1000 * 60 * 15);   // 15分钟
        notifyInterval.put("5", 1000 * 60 * 30);   // 30分钟
        notifyInterval.put("6", 1000 * 60 * 60);   // 1 小时
        notifyInterval.put("7", 1000 * 60 * 60 * 1.5);   // 1.5小时
        notifyInterval.put("8", 1000 * 60 * 60 * 3);   // 3小时
        notifyInterval.put("9", 1000 * 60 * 60 * 6);   // 2小时
        notifyInterval.put("10", 1000 * 60 * 60 * 12);   // 2小时
        notifyInterval.put("11", 1000 * 60 * 60 * 24);   // 2小时
        setNotifyInterval(notifyInterval);
        setCurrentNotifyNumber(0);
        setMaxNotifyNumber(notifyInterval.entrySet().size());
    }

    public boolean isExceedMaxNotifyNumber() {
        return this.getCurrentNotifyNumber() == -1 ? true : false;
    }
}
