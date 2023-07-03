package sld.vinmusic.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * Table: account
 */
@Data
public class Account implements Serializable {
    /**
     * Column: id
     * Type: BIGINT
     */
    private Long id;

    /**
     * Column: a_name
     * Type: VARCHAR(255)
     */
    private String aName;

    /**
     * Column: pwd
     * Type: VARCHAR(255)
     */
    private String pwd;
}