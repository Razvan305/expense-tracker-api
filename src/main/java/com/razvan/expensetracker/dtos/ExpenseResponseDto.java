package com.razvan.expensetracker.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseResponseDto {

    private Long id;
    private String description;
    private BigDecimal amount;

    @JsonFormat(pattern = "dd-MM-yyy HH:mm:ss")
    private LocalDateTime createdAt;
}
