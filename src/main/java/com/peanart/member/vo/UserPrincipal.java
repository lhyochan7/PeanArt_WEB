package com.peanart.member.vo;


import java.util.Arrays;
import java.util.Collection;

import lombok.Getter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@ToString
@Getter
public class UserPrincipal implements UserDetails {

    private User user;
    private MemberVO memberVO;

    public UserPrincipal(MemberVO memberVO) {
        this.memberVO = memberVO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Arrays.asList(new UserGrant());
    }

    @Override
    public String getPassword() {
        return memberVO.getUsrPw();
    }

    @Override
    public String getUsername() {
        return memberVO.getUsrId();
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
        return memberVO.getActive() == 1;
    }

    public String getId() {
        return memberVO.getUsrId();
    }

    public String getName() {
        return memberVO.getUsrName();
    }
}
