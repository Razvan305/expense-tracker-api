package com.razvan.expensetracker.services;

import com.razvan.expensetracker.ExpensetrackerApplication;
import com.razvan.expensetracker.dtos.ExpenseRequestDto;
import com.razvan.expensetracker.dtos.ExpenseResponseDto;
import com.razvan.expensetracker.entities.Expense;
import com.razvan.expensetracker.mappers.ExpenseMapper;
import com.razvan.expensetracker.repositories.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExpenseService {

    private final ExpenseRepository expenseRepository;
    private final ExpenseMapper expenseMapper;

    public ExpenseResponseDto addExpense(String description, BigDecimal amount) {
        ExpenseRequestDto dto = ExpenseRequestDto.builder()
                .description(description)
                .amount(amount)
                .build();
        Expense expense = expenseMapper.toEntity(dto);
        Expense saved = expenseRepository.save(expense);
        return expenseMapper.toResponseDto(saved);
    }

    public ExpenseResponseDto updateExpense(Long id, String description, BigDecimal amount) {
        Expense expense = expenseRepository.findById(id)
                .orElse(null);
        ExpenseRequestDto dto = ExpenseRequestDto.builder()
                .description(description)
                .amount(amount)
                .build();

        expenseMapper.updateEntityFromDto(dto, expense);
        Expense updated = expenseRepository.save(expense);
        return expenseMapper.toResponseDto(updated);
    }

    public ExpenseResponseDto deleteExpense(Long id) {
        Expense expense = expenseRepository.findById(id)
                .orElse(null);
        if (expense == null)
            return null;
        expenseRepository.deleteById(id);

        ExpenseResponseDto responseDto = new ExpenseResponseDto();
        responseDto.setId(expense.getId());

        return responseDto;
    }

    public List<ExpenseResponseDto> getAllExpenses() {
        List<Expense> expenses = expenseRepository.findAllByOrderByCreatedAtDesc();
        return expenseMapper.toResponseDtoList(expenses);
    }

    public BigDecimal getTotalSummary() {
        return expenseRepository.sumAllExpenses();
    }

    public BigDecimal getMonthlySummary(int month) {
        if(month < 1 || month > 12) {
            return null;
        }
        int currentYear = LocalDate.now().getYear();
        YearMonth yearMonth = YearMonth.of(currentYear, month);

        LocalDateTime startOfMonth = yearMonth.atDay(1).atStartOfDay();
        LocalDateTime startOfNextMonth = yearMonth.plusMonths(1).atDay(1).atStartOfDay();

        return expenseRepository.sumExpensesBetween(startOfMonth, startOfNextMonth);
    }
}
