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
 * @author demo
 * @since 2024-10-21
 */
@Getter
@Setter
public class Fee implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer id;

    private Integer userid;

    private String type;

    private Integer amount;

    private LocalDateTime deadline;
}
