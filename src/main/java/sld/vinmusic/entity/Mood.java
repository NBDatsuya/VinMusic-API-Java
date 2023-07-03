package sld.vinmusic.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Table: mood
 */
@Data
public class Mood implements Serializable {
    /**
     * Column: id
     * Type: BIGINT
     */
    private Long id;

    /**
     * Column: a_id
     * Type: BIGINT
     */
    private Long aId;

    /**
     * Column: content
     * Type: LONGTEXT
     */
    private String content;

}