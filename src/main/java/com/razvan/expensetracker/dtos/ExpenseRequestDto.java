package com.razvan.expensetracker.dtos;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExpenseRequestDto {

    private String description;
    private BigDecimal amount;
}
