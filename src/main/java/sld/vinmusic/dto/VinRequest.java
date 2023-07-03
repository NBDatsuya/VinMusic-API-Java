package sld.vinmusic.dto;

import lombok.Data;
import sld.vinmusic.entity.Vinlike;

@Data
public class VinRequest extends Vinlike {
    private boolean like;
}
