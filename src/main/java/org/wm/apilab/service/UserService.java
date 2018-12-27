package org.wm.apilab.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.wm.apilab.model.SysRole;
import org.wm.apilab.model.SysUser;

@SuppressWarnings("rawtypes")
public interface UserService {

    String getUser(int id);

    ResponseEntity register(SysUser user);
    
    SysUser findByName(String username);
    
    List<SysRole> selectByUserId(int userId);
}
