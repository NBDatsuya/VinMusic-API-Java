package sld.vinmusic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import sld.vinmusic.entity.Account;

@Mapper
public interface AccountMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(Account row);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    Account selectById(Long id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<Account> selectAll();

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(Account row);
}