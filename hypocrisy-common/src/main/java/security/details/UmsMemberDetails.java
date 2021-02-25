package security.details;

import bean.UmsMemberPortion;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/24 11:52
 * @Description:
 */
public class UmsMemberDetails implements UserDetails {

    private UmsMemberPortion umsMemberPortion;
    private String password;
    private Collection<GrantedAuthority> authorities;

    public UmsMemberDetails(UmsMemberPortion umsMemberPortion, String password, Collection<GrantedAuthority> authorities) {
        this.umsMemberPortion = umsMemberPortion;
        this.password = password;
        this.authorities = authorities;
    }

    public UmsMemberPortion getUmsMemberPortion() {
        return umsMemberPortion;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }
    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return umsMemberPortion.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
