package sld.vinmusic.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import sld.vinmusic.entity.Mood;
@Mapper
public interface MoodMapper {
    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int deleteByPrimaryKey(Long id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int insert(Mood row);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    Mood selectByPrimaryKey(Long id);

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    List<Mood> selectAll();

    /**
     * @mbg.generated generated automatically, do not modify!
     */
    int updateByPrimaryKey(Mood row);
}