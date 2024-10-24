package com.example.demo.entity;

import java.io.Serializable;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author demo
 * @since 2024-10-21
 */
@Getter
@Setter
public class House implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer ownerid;

    private String type;

    private Integer area;

    private String address;

    private String status;
}
