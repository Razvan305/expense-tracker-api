package com.razvan.expensetracker.mappers;

import com.razvan.expensetracker.dtos.ExpenseRequestDto;
import com.razvan.expensetracker.dtos.ExpenseResponseDto;
import com.razvan.expensetracker.entities.Expense;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ExpenseMapper {

    ExpenseResponseDto toResponseDto(Expense expense);

    List<ExpenseResponseDto> toResponseDtoList(List<Expense> expenses);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Expense toEntity(ExpenseRequestDto dto);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateEntityFromDto(ExpenseRequestDto dto, @MappingTarget Expense expense);
}
