package org.wm.apilab.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wm.apilab.dao.SysRoleMapper;
import org.wm.apilab.dao.SysUserMapper;
import org.wm.apilab.dao.SysUserRoleMapper;
import org.wm.apilab.model.ErrorResult;
import org.wm.apilab.model.SysRole;
import org.wm.apilab.model.SysUser;
import org.wm.apilab.model.SysUserRole;
import org.wm.apilab.service.UserService;
import org.wm.apilab.utils.Const;
import org.wm.apilab.utils.JSONUtils;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private SysRoleMapper sysRoleMapper;
    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Override
    public String getUser(int id) {
        System.out.println("check cache flow...");
        SysUser user = sysUserMapper.selectByPrimaryKey((long) id);
        
        if (user != null) {
            return JSONUtils.fillResultString(Const.RC_000, user);
        }

        return JSONUtils.fillResultString(Const.RC_906, null);
    }
    
    public SysUser findByName(String username) {
        return sysUserMapper.findByName(username);
    }

    @Override
    @Transactional
    public ResponseEntity register(SysUser user) {
        ResponseEntity entry;
        if (findByName(user.getUsername()) != null) {
            ErrorResult result = new ErrorResult();
            result.setStatus(403);
            result.setResult("帳號已存在");
            entry = ResponseEntity.status(400).body(result);
        } else {
            sysUserMapper.insert(user);
            SysUserRole userRole = new SysUserRole();
            userRole.setRoleId((long)2);
            userRole.setUserId((long) user.getId());
            sysUserRoleMapper.insert(userRole);
            
            SysUser result = sysUserMapper.selectByPrimaryKey(user.getId());
            if (user.getStatus() == 9) { //TODO rollback test
                String a = null;
                a.indexOf('c');
            }
            entry = ResponseEntity.ok(result);
        }
        return entry;
    }

    @Override
    public List<SysRole> selectByUserId(int userId) {
        return sysRoleMapper.selectByUserId((long) userId);
    }

}
