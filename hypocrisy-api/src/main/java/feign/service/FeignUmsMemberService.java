package feign.service;

import bean.UmsMember;
import bean.UmsMemberReceiveAddress;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 20:59
 * @Description:
 */
@FeignClient(name = "hypocrisy-passport")
public interface FeignUmsMemberService {

    /**
     * 更具用户名查询用户
     * @param username
     * @return
     */
    @GetMapping("/selectByUsername")
    UmsMember selectByUsername(@RequestParam("username") String username);

    /**
     * 查询用户权限
     * @param permissionId
     * @return
     */
    @GetMapping("selectMemberPermission")
    String selectMemberPermission(@RequestParam("permissionId")String permissionId);

    @PostMapping("/selectOne")
    UmsMemberReceiveAddress selectOne(@RequestBody UmsMemberReceiveAddress umsMemberReceiveAddressQuery);
}
