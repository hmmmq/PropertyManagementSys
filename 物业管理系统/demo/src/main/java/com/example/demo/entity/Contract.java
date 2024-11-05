package com.example.demo.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author xxx
 * @since 2024-11-05
 */
@Getter
@Setter
public class Contract implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private String name;

    private Integer signUserid;

    private String signUsername;

    private String type;

    private LocalDateTime signTime;

    private String status;
}
