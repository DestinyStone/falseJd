package response.type;

/**
 * @Auther: ASUS
 * @Date: 2020/10/10 00:15
 * @Description: 响应状态码类型
 */
public class ResponseCodeType {

    /** @Description: 正常请求 */
    public static Integer SUCCESS = 20000;
    /** @Description: 参数错误 */
    public static Integer PARAM_ERROR = 40001;
    /** @Description: 未知错误 */
    public static final Integer UN_KNOW_ERROR = 49999;

    /**          登录相关         **/

    /** @Description: 登录成功 */
    public static Integer LOGIN_SUCCESS=20000;
    /** @Description: 登录状态中 */
    public static Integer LOGIN_STATUS = 21000;
    /** @Description: 未登录状态 */
    public static Integer NO_LOGIN_STATUS = 21001;
    /** @Description: Token过期 */
    public static Integer TOKEN_EXPIRED = 21002;
    /** @Description: 手机号码已被注册 */
    public static Integer PHONE_ALREADY_ACTIVE = 21003;


    /** @Description: 权限不足 */
    public static Integer PERMISSION_DENIED = 41002;

    /** @Description: 验证码失效 */
    public static Integer VERITY_CODE_LOSE = 41001;

    /**      商品相关     */

    /** @Description: 商品未销售 */
    public static Integer NO_COMMODITY = 31001;

    /** @Description: 商品库存不足 */
    public static Integer NO_COMMODITY_STORE = 31002;


    /** @Description: 表单提交频繁 */
    public static Integer OFTEN_COMMIT = 31003;
    public static Integer TRADE_NOT_EXIST = 31004;
    public static Integer WAIT_BUYER_PAY = 31005;
    public static Integer TRADE_SUCCESS = 31006;


}
