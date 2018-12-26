package org.wm.apilab.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.wm.apilab.model.SysRole;
import org.wm.apilab.model.SysUser;
import org.wm.apilab.model.Token;

@SuppressWarnings("rawtypes")
public interface UserService {

    ResponseEntity getUser(int id);

    ResponseEntity register(SysUser user);
    
    boolean checkToken(Token token);
    
    SysUser findByName(String username);
    
    List<SysRole> selectByUserId(int userId);
}
