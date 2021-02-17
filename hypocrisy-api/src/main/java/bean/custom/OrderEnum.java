package bean.custom;

import java.io.Serializable;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/14 11:29
 * @Description:
 */
public enum OrderEnum implements Serializable {

    ASC(0),
    DESC(1);

    int i;

    OrderEnum(int i) {
        this.i = i;
    }
}
