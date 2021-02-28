package utils;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import utils.properties.SnowFlakProperties;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 16:11
 * @Description:
 */
public class IdUtils {

    private static SnowFlakProperties snowFlakProperties;

    public static String getSnowflakeId() {
        Snowflake snowflake = IdUtil.getSnowflake(snowFlakProperties.getWorkerId(), snowFlakProperties.getDatacenterId());
        long id = snowflake.nextId();
        return id + "";
    }

    @Autowired
    private void setSnowFlakProperties(SnowFlakProperties snowFlakProperties) {
        IdUtils.snowFlakProperties = snowFlakProperties;
    }
}
