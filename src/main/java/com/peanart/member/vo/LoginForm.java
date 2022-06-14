package com.peanart.member.vo;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Getter@Setter@ToString@Data
public class LoginForm implements UserDetails {
    private String usrId;
    private String usrPw;
    private String userAuth;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList((new SimpleGrantedAuthority(this.userAuth)));
    }

    @Override
    public String getPassword() {
        return this.usrPw;
    }

    @Override
    public String getUsername() {
        return this.usrId;
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
