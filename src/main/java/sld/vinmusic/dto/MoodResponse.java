package sld.vinmusic.dto;

import lombok.experimental.SuperBuilder;
import sld.vinmusic.entity.Mood;
import sld.vinmusic.entity.MoodDetail;

@SuperBuilder
public class MoodResponse{
    private Mood mood;
    private Long vinCount;
}
