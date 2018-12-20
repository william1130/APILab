package org.wm.apilab.dao;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.wm.apilab.model.Token;

@Mapper
public interface TokenMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Token record);

    Token selectByPrimaryKey(Long id);

    List<Token> selectAll();

    int updateByPrimaryKey(Token record);
}