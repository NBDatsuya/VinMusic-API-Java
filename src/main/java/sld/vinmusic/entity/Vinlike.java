package sld.vinmusic.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Table: vinlike
 */
@Data
public class Vinlike implements Serializable {
    /**
     * Column: id
     * Type: BIGINT
     */
    private Long id;

    /**
     * Column: mood_id
     * Type: BIGINT
     */
    private Long moodId;

    /**
     * Column: av_id
     * Type: BIGINT
     */
    private Long avId;
}