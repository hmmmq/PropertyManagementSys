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
public class Park implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer carid;

    private LocalDateTime reserveTime;

    private String username;

    private Integer userid;

    private String status;
}
