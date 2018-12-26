package org.wm.apilab.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.wm.apilab.model.SysRole;

@Mapper
public interface SysRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysRole record);

    SysRole selectByPrimaryKey(Long id);

    List<SysRole> selectAll();

    int updateByPrimaryKey(SysRole record);
    
    List<SysRole> selectByUserId(Long userId);
}