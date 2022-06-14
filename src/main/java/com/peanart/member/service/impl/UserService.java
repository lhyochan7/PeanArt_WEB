package com.peanart.member.service.impl;

import com.peanart.member.dao.RoleMapper;
import com.peanart.member.dao.UserMapper;
import com.peanart.member.dao.UserRoleMapper;
import com.peanart.member.vo.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public MemberVO findUserByLoginId(String usrId) {
        return userMapper.findUserByLoginId(usrId);
    }

    public void saveUser(MemberVO memberVO) {

        memberVO.setUsrPw(bCryptPasswordEncoder.encode(memberVO.getUsrPw()));
        userMapper.setUserInfo(memberVO);

        Role role = roleMapper.getRoleInfo("USER");
        MemberVO user = userMapper.findUserByLoginId(memberVO.getUsrId());
        UserRole userRole = new UserRole();
        userRole.setRoleId(role.getId());
        userRole.setUserId(user.getUsrSeq());

        userRoleMapper.setUserRoleInfo(userRole);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MemberVO memberVO = userMapper.findUserByLoginId(username);
        return new UserPrincipal(memberVO);
    }
}
