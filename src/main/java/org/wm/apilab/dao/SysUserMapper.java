package org.wm.apilab.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.wm.apilab.model.SysUser;

@Mapper
public interface SysUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUser record);

    SysUser selectByPrimaryKey(Long id);

    List<SysUser> selectAll();

    int updateByPrimaryKey(SysUser record);

    SysUser findByName(String username);
}