package type;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/28 17:59
 * @Description:
 */
public enum PaymentType {

    ALI_PAY(0),
    WECHAT_PAY(1);

    private int i;
    PaymentType(int i) {
        this.i = i;
    }
}
