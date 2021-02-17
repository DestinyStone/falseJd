package service;

import bean.UmsMemberReceiveAddress;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/28 10:52
 * @Description:
 */
public interface UmsMemberReceiveAddressService {
    /**
     * 插入用户地址
     * @param umsMemberReceiveAddress
     * @param userId
     * @return
     */
    Message insertAndSetUserId(UmsMemberReceiveAddress umsMemberReceiveAddress, String userId);

    /**
     * 根据用户id查询地址
     * @param userId
     * @return
     */
    Message selectByUserId(String userId);

    /**
     * 根据地址id删除地址
     * @param userId
     * @param id
     * @return
     */
    Message deleteByUserIdAndId(String userId, String id);

    /**
     * 设置默认地址
     * @param userId
     * @param id
     * @return
     */
    Message updateDefaultStatusByUserIdAndId(String userId, String id);
}
