package service;

import bean.UmsMember;
import response.Message;

/**
 * @Auther: DestinyStone
 * @Date: 2020/10/17 21:27
 * @Description:
 */
public interface UmsMemberService {
    /**
     * 验证是否能够激活
     * @param email
     * @param verityCode
     * @return
     */
    boolean verityCode(String email, String verityCode);

    /**
     * 插入邮箱
     * @param email
     * @return
     */
    Message insertEmail(String email);

    /**
     * 更新状态为 1
     * @param email
     * @return
     */
    Message updateStautsTo1(String email);

    /**
     * 查询邮箱激活状态
     * @param email
     * @return
     */
    Message selectEmailActiveStatus(String email);

    /**
     * 查询数据库中是否存在用户名
     * @param username
     * @return
     */
    int selectByUsernameReturnCount(String username);

    /**
     * 根据邮箱插入用户
     * @param username
     * @param password
     * @param email
     * @return
     */
    Message addUserByEmail(String username, String password, String email);

    /**
     * 更具用户名查询用户
     * @param username
     * @return
     */
    UmsMember selectByUsername(String username);

    /**
     * 查询用户权限
     * @param permissionId
     * @return
     */
    String selectMemberPermission(String permissionId);

    /**
     * 返回用户id
     * @param username
     * @return
     */
    String selectByUsernameReturnId(String username);

    /**
     * 该手机号是否激活， true， 已激活， false， 未激活
     * @param phone
     * @return
     */
    boolean isActiveByPhone(String phone);

    /**
     * 检查是否验证通过
     * @param phone
     * @param verifyCode
     * @return
     */
    boolean isVerifySuccess(String phone, String verifyCode);

    /**
     * 插入手机号
     * @param phone
     * @return
     */
    Message insertPhone(String phone);

    /**
     * 根据手机号添加用户
     * @param username
     * @param password
     * @param phone
     * @return
     */
    Message addUserByPhone(String username, String password, String phone);
}
