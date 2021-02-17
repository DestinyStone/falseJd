package bean.custom;

import java.io.Serializable;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/14 11:27
 * @Description:
 */
public enum  SelectCheckEnum implements Serializable {

    ALL(3),
    STATUS0(0),
    STATUS1(1),
    REJECT(2);

    int i;
    SelectCheckEnum(int i) {
        this.i = i;
    }
}
