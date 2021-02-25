package security.realm;

import bean.UmsMember;
import bean.UmsMemberPortion;
import feign.service.FeignUmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import security.details.UmsMemberDetails;

import java.util.List;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/24 11:37
 * @Description:
 */
public class UserDetailsServiceRealm implements UserDetailsService {

    @Autowired
    private FeignUmsMemberService feignUmsMemberService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UmsMember umsMember = feignUmsMemberService.selectByUsername(username);

        String permission = feignUmsMemberService.selectMemberPermission(umsMember.getPermissionId());

        UmsMemberPortion userPortion = createUserPortion(umsMember);
        List<GrantedAuthority> test = AuthorityUtils.commaSeparatedStringToAuthorityList(permission);
        System.out.println(new BCryptPasswordEncoder().encode("1314LOVE"));
        UmsMemberDetails umsMemberDetails = new UmsMemberDetails(userPortion, umsMember.getPassword(), test);
        return umsMemberDetails;
    }

    public UmsMemberPortion createUserPortion(UmsMember umsMember) {
        return UmsMemberPortion.builder()
                .id(umsMember.getId())
                .username(umsMember.getUsername())
                .nickname(umsMember.getNickname()).build();
    }
}
















