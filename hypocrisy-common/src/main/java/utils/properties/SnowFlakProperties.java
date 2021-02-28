package utils.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import utils.IdUtils;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 16:14
 * @Description:
 */
@Configuration
@ConfigurationProperties(
        prefix = "snowflak"
)
@EnableConfigurationProperties(IdUtils.class)
public class SnowFlakProperties {

    private long workerId;

    private long datacenterId;

    public long getWorkerId() {
        return workerId;
    }

    public void setWorkerId(long workerId) {
        this.workerId = workerId;
    }

    public long getDatacenterId() {
        return datacenterId;
    }

    public void setDatacenterId(long datacenterId) {
        this.datacenterId = datacenterId;
    }
}
