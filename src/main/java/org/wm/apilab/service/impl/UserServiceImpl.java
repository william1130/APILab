package org.wm.apilab.service.impl;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wm.apilab.dao.SysUserMapper;
import org.wm.apilab.dao.TokenMapper;
import org.wm.apilab.model.ErrorResult;
import org.wm.apilab.model.SysUser;
import org.wm.apilab.model.Token;
import org.wm.apilab.service.UserService;
import org.wm.apilab.utils.Md5Utils;

@Service
@SuppressWarnings("rawtypes")
public class UserServiceImpl implements UserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private TokenMapper tokenMapper;
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseEntity getUser(int id) {
        SysUser user = sysUserMapper.selectByPrimaryKey((long) id);
        redisTemplate.opsForValue().set(String.valueOf(id), user);
        if (user != null) {
            return ResponseEntity.status(200).body(user);
        }
        ErrorResult errorResult = new ErrorResult();
        errorResult.setError("無此用戶!");
        errorResult.setStatus(404);
        return ResponseEntity.status(404).body(errorResult);
    }
    
    public SysUser findByName(String username) {
        return sysUserMapper.findByName(username);
    }

    @Override
    @Transactional
    public ResponseEntity register(SysUser user) {
        String password = Md5Utils.EncoderByMd5(user.getPassword());
//        user.setPassword(password);
        ResponseEntity entry;
        if (findByName(user.getUsername()) != null) {
            ErrorResult result = new ErrorResult();
            result.setStatus(403);
            result.setResult("帳號已存在");
            entry = ResponseEntity.status(400).body(result);
        } else {
            sysUserMapper.insert(user);
            Token token = new Token();
            token.setToken(UUID.randomUUID().toString());
            token.setUserId((long) user.getId());
            tokenMapper.insert(token);
            Token result = tokenMapper.selectByPrimaryKey(user.getId());
            if (user.getStatus() == 9) { //rollback test
                String a = null;
                a.indexOf('c');
            }
            entry = ResponseEntity.ok(result);
        }
        return entry;
    }

    @Override
    public boolean checkToken(Token token) {
        return token.getToken()
                .equals(tokenMapper.selectByPrimaryKey(token.getUserId()).getToken());
    }

}
