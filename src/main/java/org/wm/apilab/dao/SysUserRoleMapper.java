package org.wm.apilab.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.wm.apilab.model.SysUserRole;

@Mapper
public interface SysUserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(SysUserRole record);

    SysUserRole selectByPrimaryKey(Long id);

    List<SysUserRole> selectAll();

    int updateByPrimaryKey(SysUserRole record);
}