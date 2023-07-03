package sld.vinmusic.dao;

import org.apache.ibatis.annotations.Mapper;
import sld.vinmusic.entity.MoodDetail;

@Mapper
public interface MoodDetailMapper {
    MoodDetail selectByMood(long id);
}
