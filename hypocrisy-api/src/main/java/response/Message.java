package response;

import java.io.Serializable;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/10 00:11
 * @Description:
 */
public class Message implements Serializable {
    /** @Description: 响应状态码 可从ResponseCodeType类中获取 */
    private Integer responseCode;

    /** @Description: 响应信息 */
    private Object responseMessage;

    /** @Description: 响应状态 true 成功， false 错误 */
    private Boolean status;

    public Message() {
    }

    public Message(Integer responseCode, Object responseMessage, Boolean status) {
        this.responseCode = responseCode;
        this.responseMessage = responseMessage;
        this.status = status;
    }

    public Integer getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(Integer responseCode) {
        this.responseCode = responseCode;
    }

    public Object getResponseMessage() {
        return responseMessage;
    }

    public void setResponseMessage(Object responseMessage) {
        this.responseMessage = responseMessage;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
