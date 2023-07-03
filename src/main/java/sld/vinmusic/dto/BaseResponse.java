package sld.vinmusic.dto;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
public class BaseResponse {
    private boolean ok;
    private String msg;
    private int code;
}
