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
 * @since 2024-11-06
 */
@Getter
@Setter
public class Inform implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userid;

    private String message;

    private LocalDateTime informTime;

    private String status;
}
