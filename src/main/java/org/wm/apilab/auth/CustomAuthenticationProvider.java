package org.wm.apilab.auth;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import org.wm.apilab.model.SysRole;
import org.wm.apilab.model.SysUser;
import org.wm.apilab.service.UserService;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserService userService;
    
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        // 取得帳號、密碼
        String name = authentication.getName();
        String password = authentication.getCredentials().toString();
        SysUser user = userService.findByName(name);
        List<SysRole> roleList = userService.selectByUserId(Math.toIntExact(user.getId()));
        
        if (user == null) throw new BadCredentialsException("無此用戶!");
        
        // 登入驗證
        if (name.equals(user.getUsername()) && password.equals(user.getPassword())) {

            // 設定權限、角色
            ArrayList<GrantedAuthority> authorities = new ArrayList<>();
            if (roleList != null)
                roleList.forEach(role -> authorities.add( new GrantedAuthorityImpl(role.getRole()) ));
            
            // 回傳token
            Authentication auth = new UsernamePasswordAuthenticationToken(name, password, authorities);
            return auth;
        }else {
            throw new BadCredentialsException("密碼錯誤!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
