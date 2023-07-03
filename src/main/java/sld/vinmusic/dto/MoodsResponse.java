package sld.vinmusic.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import java.util.List;

@SuperBuilder
public class MoodsResponse extends BaseResponse{
    private List<MoodResponse> moodList;
}
