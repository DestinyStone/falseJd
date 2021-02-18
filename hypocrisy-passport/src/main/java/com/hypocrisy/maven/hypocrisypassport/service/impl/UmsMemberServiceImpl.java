package com.hypocrisy.maven.hypocrisypassport.service.impl;

import bean.UmsMember;
import bean.UmsPermission;
import com.hypocrisy.maven.hypocrisypassport.mapper.UmsMemberMapper;
import com.hypocrisy.maven.hypocrisypassport.mapper.UmsPermissonMapper;
import com.hypocrisy.maven.hypocrisypassport.service.UmsMemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Auther: DestinyStone
 * @Date: 2021/2/17 17:50
 * @Description:
 */
@Service
public class UmsMemberServiceImpl implements UmsMemberService {

    @Autowired
    private UmsPermissonMapper umsPermissonMapper;

    @Autowired
    private UmsMemberMapper umsMemberMapper;

    @Override
    public UmsMember selectByUsername(String username) {
        UmsMember umsMemberQuery = UmsMember.builder().username(username).build();
        UmsMember umsMemberResult = umsMemberMapper.selectOne(umsMemberQuery);
        return umsMemberResult;
    }

    @Override
    public String selectMemberPermission(String permissionId) {
        UmsPermission umsPermissionQuery = UmsPermission.builder().id(permissionId).build();
        UmsPermission umsPermissionResult = umsPermissonMapper.selectOne(umsPermissionQuery);
        return umsPermissionResult.getCode();
    }

}
