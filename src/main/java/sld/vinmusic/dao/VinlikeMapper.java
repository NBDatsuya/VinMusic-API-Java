package sld.vinmusic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import sld.vinmusic.entity.Vinlike;

@Mapper
public interface VinlikeMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByID(Long id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(Vinlike row);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    Vinlike selectByPrimaryKey(Long id);
    Vinlike selectByMoodAndAccount(Vinlike vinlike);
    List<Vinlike> selectAll();

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(Vinlike row);
}