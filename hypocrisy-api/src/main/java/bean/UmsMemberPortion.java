package bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/24 11:40
 * @Description:
 */
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UmsMemberPortion implements Serializable {

    private String id;
    private String username;
    private String nickname;

}
